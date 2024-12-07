const usuarioId = JSON.parse(window.localStorage.getItem("dadosUsuario")).Usuario.id;
const token = JSON.parse(window.localStorage.getItem("dadosUsuario")).token;
const URI = `http://localhost:3000/ocorrencia/usuario/${usuarioId}`;
const divOccurrence = document.querySelector('#div-occurrence');
const form_messagem = document.querySelector('#form-message');
var occurrenceId;
var occurrenceList = [];

async function fetchOccurrence() {
    occurrenceList = []
    try {
        const fetchResponse = await fetch(URI, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if(fetchResponse.status == 200) {
            const response = await fetchResponse.json();
            response.ocorrencias.forEach((e) => {
                occurrenceList.push(e);
            });
    
            renderCardsOccurrence();
        }
        
    } catch (error) {
        console.log("Erro ao carregar ocorrências do usuario", error)
    }
    
}

function renderCardsOccurrence() {
    occurrenceList.forEach((e) => {
        const cardOccurrence = document.createElement('div');
        cardOccurrence.setAttribute('data-occurrence', e.id);
        cardOccurrence.classList.add('card-occurrence');

        cardOccurrence.innerHTML = `
            <div class="img-occurrence"></div>
                
            <div class="info-occurrence">
                <div>
                    <p class="description">${e.descricao}</p>
                    <p class="address">
                        <i class="bi bi-geo-alt-fill"></i>
                        ${e.rua + ' - ' + e.bairro + ' | ' + e.cidade + '/' + e.estado}
                    </p>    
                </div>
                <div>
                    <div>
                        <p class="status">Situação: ${e.status}</p> 
                        <p class="date">${formatDate(e.data) + ' - ' + formatHours(e.data)}</p>    
                    </div>
                    <div class="div-btn">
                        <button id="btn-trash" class="btn-trash" onclick="del(event)"><i class="bi bi-trash-fill"></i></button>
                        <button id="btn-chat" class="btn-chat" onclick="chat(event)"><i class="bi bi-chat-right-text-fill"></i></button>
                    </div>
                </div>
            </div>
        `

        divOccurrence.appendChild(cardOccurrence);
    });
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
        perfil: JSON.parse(window.localStorage.getItem("dadosUsuario")).infoConta.role,
        responsavel: usuarioId
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

function del(event) {
    const button = event.target.closest('button');
    const occurrence = button.closest('.card-occurrence');
    occurrenceId = occurrence.getAttribute('data-occurrence');

    if(confirm("Deseja realmente excluir a ocorrência? Após a confirmação, não é possível recuperar a ocorrência excluída!")) {
        delOccurrence(occurrenceId);
    }
    
    fetchOccurrence();
}

async function delOccurrence(occurrenceId) {
    try {
        const deleteOccurence = await fetch(`http://localhost:3000/ocorrencia/${usuarioId}/${occurrenceId}`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if(deleteOccurence.status == 200) {
            fetchOccurrence();
        }
    } catch(error) {
        console.log('Erro ao deletar', error)
    }
}

function scrollToBottom() {
    const divMessage = document.querySelector('#div-message');
    divMessage.scrollTop = divMessage.scrollHeight;
}


document.addEventListener('DOMContentLoaded', () => {
    fetchOccurrence();
})