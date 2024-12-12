const URI = 'http://localhost:3000';
const token = JSON.parse(window.localStorage.getItem("dadosFuncionario")).token;
const employeeId = JSON.parse(window.localStorage.getItem("dadosFuncionario")).funcionario.id;
const form_messagem = document.querySelector('#form-message');

async function fetchOccurrenceEmployee() {
    document.querySelector('#div-occurrence').innerHTML = '';
    try {
        const response = await fetch(`${URI}/ocorrencia/funcionario/${employeeId}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (response.status === 200) {
            const occurrence = await response.json();

            console.log(occurrence);
            occurrence.ocorrencias.forEach((e) => {
                renderOccurrence(
                    e.id,
                    e.descricao,
                    e.rua,
                    e.bairro,
                    e.cidade,
                    e.estado,
                    e.cep,
                    e.imagens[0].imagem,
                    e.data,
                    e.status
                )
            });
        }
    } catch (error) {
        console.log('Erro ao buscar ocorrencias atribuidas', error);
    }
}

function renderOccurrence(id, descricao, rua, bairro, cidade, estado, cep, imagem, data, status) {
    const cardOccurrence = document.createElement('div');
    cardOccurrence.setAttribute('data-occurrence', id);
    cardOccurrence.classList.add('card-occurrence');

    let statusString;

    if(status == 'ENVIADA_AO_SETOR') {
        statusString = 'Enviada ao setor'
    } else if(status == 'ENVIADA_PARA_ANALISE') {
        statusString = 'Enviada para analise'
    } else if(status == 'APROVADA') {
        statusString = 'Aprovada'
    } else if(status == 'Em_DESENVOLVIMENTO') {
        statusString = 'Em desenvolvimento'
    } else if(status == 'ENCERRADA') {
        statusString = 'Encerrada'
    }

    cardOccurrence.innerHTML = `
        <div class="img-occurrence" style="background-image: url(http://localhost:3000/uploads/${imagem});" ></div>
            
        <div class="info-occurrence">
            <div>
                <p class="description">${descricao}</p>
                <p class="address">
                    <i class="bi bi-geo-alt-fill"></i>
                    ${rua + ' - ' + bairro + ' | ' + cidade + '/' + estado}
                </p>    
            </div>
            <div>
                <div>
                    <p class="status">Situação: ${statusString}</p> 
                    <p class="date">${formatDate(data) + ' - ' + formatHours(data)}</p>    
                </div>
                <div class="div-btn">
                    <button id="btn-view" class="btn-view" onclick="view('${descricao}', '${rua}', '${bairro}', '${cidade}', '${cep}', '${id}')">
                        <i class="bi bi-eye-fill"></i>
                    </button>
                    <button id="btn-chat" class="btn-chat" onclick="chat(event)">
                        <i class="bi bi-chat-right-text-fill"></i>
                    </button>
                </div>
            </div>
        </div>
        `

    document.querySelector('#div-occurrence').appendChild(cardOccurrence);
}

async function view(descricao, rua, bairro, cidade, cep, id) {
    const response = await fetch(`http://localhost:3000/usuario/ocorrencia/${id}`, {
        method: 'GET'
    });
    const user = await response.json();
    document.querySelector('#username').innerHTML = user.usuario.nome;
    

    const responseImage = await fetch(`http://localhost:3000/imagens/ocorrencia/${id}`, {
        method: 'GET'
    });
    const images = await responseImage.json();
    images.forEach((e) => {
        const img = document.createElement('div');
        img.classList.add('img-occurrence');
        img.setAttribute('style', `background-image: url(http://localhost:3000/uploads/${e.imagem});`)
    
        document.querySelector('#div-images').appendChild(img);
    })

    document.querySelector('#overlay').classList.add('show-overlay');
    setTimeout(() => {
        document.querySelector('#model-occurrence').classList.add('show');
        document.body.classList.add('block');
    }, 200);

    document.querySelector('#description').innerHTML = descricao;
    document.querySelector('#street').innerHTML = rua;
    document.querySelector('#district').innerHTML = bairro;
    document.querySelector('#city').innerHTML = cidade;
    document.querySelector('#cep').innerHTML = cep;
}

function closeModelOccurrence() {
    document.querySelector('#div-images').innerHTML = '';
    document.querySelector('#description').innerHTML = '';
    document.querySelector('#street').innerHTML = '';
    document.querySelector('#district').innerHTML = '';
    document.querySelector('#city').innerHTML = '';
    document.querySelector('#cep').innerHTML = '';
    document.querySelector('#username').innerHTML = '';
    document.querySelector('#model-occurrence').classList.remove('show');
    setTimeout(() => {
        document.querySelector('#overlay').classList.remove('show-overlay');
        document.body.classList.remove('block');
    }, 200)
}

async function chat(event) {
    document.querySelector('#chat').classList.add('show-chat');
    document.body.classList.add('block');

    const button = event.target.closest('button');
    const occurrence = button.closest('.card-occurrence');
    occurrenceId = occurrence.getAttribute('data-occurrence');
    renderMessages(occurrenceId);
}

async function renderMessages(occurrenceId) {
    document.querySelector('#div-message').innerHTML = '';
    try {
        const fetchMessage = await fetch(`http://localhost:3000/mensagem/${occurrenceId}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if(fetchMessage.status == 200) {
            const response = await fetchMessage.json();

            response.mensagens.forEach((e) => {
                const divMessage = document.createElement('div');
                if (e.perfil == 'FUNCIONARIO') {
                    divMessage.classList.add('message-employee');
                } else {
                    divMessage.classList.add('message-user');
                }

                divMessage.innerHTML = `
                    <p>${e.mensagem}</p>
                `

                document.querySelector('#div-message').appendChild(divMessage);
            })
        }

        scrollToBottom();
    } catch (error) {
        console.log('Erro ao carregar menssagens', error);
    }
}

form_messagem.addEventListener('submit', async (e) => {
    e.preventDefault();

    const data = {
        mensagem: form_messagem.message.value,
        perfil: JSON.parse(window.localStorage.getItem("dadosFuncionario")).infoConta.role,
        responsavel: employeeId
    }

    try {
        const message = await fetch(`http://localhost:3000/mensagem/${occurrenceId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(data)
        });

        if(message.status == 201) {
            form_messagem.reset();

            renderMessages(occurrenceId)
        }        
        
    } catch(error) {
        console.log('Error submit message', error);
    }
})

function closeChat() {
    document.querySelector('#div-message').innerHTML = '';
    document.querySelector('#chat').classList.remove('show-chat');
    setTimeout(() => {
        document.body.classList.remove('block');
    }, 200)
}

function formatDate(date) {
    const dateTime = new Date(date);
    const day = String(dateTime.getDate()).padStart(2, '0');
    const month = String(dateTime.getMonth() + 1).padStart(2, '0');
    const year = dateTime.getFullYear();
    return `${day}/${month}/${year} `;
}

function formatHours(date) {
    const dateTime = new Date(date);
    const hours = String(dateTime.getHours()).padStart(2, '0');
    const minutes = String(dateTime.getMinutes()).padStart(2, '0');
    return `${hours}:${minutes} `;
}

function scrollToBottom() {
    const divMessage = document.querySelector('#div-message');
    divMessage.scrollTop = divMessage.scrollHeight;
}

document.addEventListener('DOMContentLoaded', () => {
    fetchOccurrenceEmployee();
})