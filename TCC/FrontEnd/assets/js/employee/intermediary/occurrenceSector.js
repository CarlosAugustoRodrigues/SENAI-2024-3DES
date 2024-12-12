const sector = JSON.parse(window.localStorage.getItem("dadosFuncionario")).funcionario.setor;
const token = JSON.parse(window.localStorage.getItem("dadosFuncionario")).token;
const employeeId = JSON.parse(window.localStorage.getItem("dadosFuncionario")).funcionario.id;
const URI = 'http://localhost:3000';

async function fetchOccurrenceSector() {

    try {
        const response = await fetch(`${URI}/ocorrencia/setor/${employeeId}/${sector}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (response.status === 200) {
            const occurrence = await response.json();

            occurrence.ocorrencias.forEach((e) => {
                renderOccurrence(
                    e.id,
                    e.descricao,
                    e.rua,
                    e.bairro,
                    e.cidade,
                    e.estado,
                    e.cep,
                    e.data,
                    e.imagens[0].imagem
                )
            });
            console.log(occurrence);
        }
    } catch (error) {
        console.log('Erro ao buscar ocorrências', error);
    }

}

function renderOccurrence(id, descricao, rua, bairro, cidade, estado, cep, data, imagem) {
    const cardOccurrence = document.createElement('div');
    cardOccurrence.setAttribute('data-occurrence', id);
    cardOccurrence.classList.add('card-occurrence');

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
                <p class="date">${formatDate(data) + ' - ' + formatHours(data)}</p>
                <button id="btn-trash" class="btn-trash" 
                    onclick="openModelOccurrence('${descricao}', '${rua}', '${bairro}', '${cidade}', '${cep}', ${id})">
                    Visualizar ocorrência
                </button>            
            </div>
          </div>
        </div>
    `
    document.querySelector('#div-occurrence').appendChild(cardOccurrence);
}

async function openModelOccurrence(descricao, rua, bairro, cidade, cep, id) {
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

async function assign() {
    const occurrenceId = document.querySelector('#occurrence').getAttribute('data-occurrence');

    try {
        const response = await fetch(`${URI}/ocorrencia/atribuir/${employeeId}/${occurrenceId}`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if(response.status === 200) {
            if(confirm('Deseja atribuir esta ocorrência a você? Após à atribuição, apenas você terá acesso a essa ocorrência')) {
                alert('Ocorrência atribuida com sucesso');
                window.location.href = 'http://127.0.0.1:5500/FrontEnd/assets/pages/employee/intermediary/occurrenceEmployee.html';    
            }
        }
    } catch(error) {
        console.log('Erro ao atribuir', error);
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
    document.querySelector('#sector-employee').innerHTML = sector;
    fetchOccurrenceSector();
})