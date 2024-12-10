const funcionarioId = JSON.parse(window.localStorage.getItem('dadosFuncionario')).funcionario.id;
const token = JSON.parse(window.localStorage.getItem("dadosFuncionario")).token;
const form = document.querySelector('#form-occurrence');

async function fetchOccurrence() {
    document.querySelector('#div-occurrence').innerHTML = '';
    try {
        const fetchOccurrence = await fetch(`http://localhost:3000/ocorrencia/registrada/setor/${funcionarioId}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if(fetchOccurrence.status == 200) {
            const response = await fetchOccurrence.json();

            response.ocorrencias.forEach((e) => {
                renderOccurrence(
                    e.id, 
                    e.descricao, 
                    e.rua, 
                    e.bairro,
                    e.cidade,
                    e.estado,
                    e.cep,
                    e.data
                )
            });
        }
    } catch(error) {
        console.log('Erro ao buscar ocorrências', error);
    }
    
}

function renderOccurrence(id, descricao, rua, bairro, cidade, estado, cep, data) {
    const cardOccurrence = document.createElement('div');
    cardOccurrence.setAttribute('data-occurrence', id);
    cardOccurrence.classList.add('card-occurrence');

    cardOccurrence.innerHTML = `
        <div class="img-occurrence"></div>

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
              <p class="date">${formatDate(data) + ' - ' + formatHours(data)}</p>
              <button id="btn-trash" class="btn-trash" onclick="openModelOccurrence('${descricao}', '${rua}', '${bairro}', '${cidade}', '${cep}', ${id})">Analisar ocorrência</button>
            </div>
          </div>
        </div>
    `

    document.querySelector('#div-occurrence').appendChild(cardOccurrence);
}

function openModelOccurrence(descricao, rua, bairro, cidade, cep, id) {
    document.querySelector('#overlay').classList.add('show-overlay');
    setTimeout(() => {
        document.querySelector('#model-occurrence').classList.add('show');
        document.body.classList.add('block');
    }, 200)

    const form = document.querySelector('#form-occurrence');
    form.setAttribute('data-occurrence-id', id);
    form.description.value = descricao;
    form.street.value = rua;
    form.district.value = bairro;
    form.city.value = cidade;
    form.cep.value = cep;
}

function closeModelOccurrence() {
    const form = document.querySelector('#form-occurrence');
    form.setAttribute('data-occurrence-id', null);
    form.reset();
    document.querySelector('#model-occurrence').classList.remove('show');
    setTimeout(() => {
        document.querySelector('#overlay').classList.remove('show-overlay');
        document.body.classList.remove('block');
    }, 200)
}

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const data = {
        descricao: form.description.value,
        rua: form.street.value,
        bairro: form.district.value,
        cidade: form.city.value,
        cep: form.cep.value
    }

    const occurrenceId = form.getAttribute('data-occurrence-id');

    try {
        const occurrence = await fetch(`http://localhost:3000/ocorrencia/registrada/alterar_infos/${funcionarioId}/${occurrenceId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }, 
            body: JSON.stringify(data)
        });

        if(occurrence.status === 200) {
            const sendOccurrence = await fetch(`http://localhost:3000/ocorrencia/enviar_ocorrencia/${funcionarioId}/${occurrenceId}`, {
                method: 'PUT',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if(sendOccurrence.status === 200) {
                closeModelOccurrence();
                fetchOccurrence();
            }
        }
    } catch(error) {
        console.log('Erro ao atualizar ocorrência e enviar para o setor responsável', error)
    }
})

async function recuseOccurrence() {
    const idOccurrence = document.querySelector('#form-occurrence').getAttribute('data-occurrence-id');

    try {
        const fetchDelete = await fetch(`http://localhost:3000/ocorrencia/rejeitar/${funcionarioId}/${idOccurrence}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })

        if(fetchDelete.status === 200) {
            alert('Ocorrência rejeitada!');
            fetchOccurrence();
            closeModelOccurrence();
        }
    } catch(error) {
        console.log('Erro ao rejeitar ocorrência', error)
    }
    
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

document.addEventListener('DOMContentLoaded', () => {
    fetchOccurrence();
})