const URI = 'http://localhost:3000/ocorrencia';
const divOccurrence = document.querySelector('#div-occurrence');
var occurrenceList = [];

async function fetchOccurrence() {
    occurrenceList = []

    try {
        const fetchResponse = await fetch(URI, {
            method: 'GET'
        });
    
        if(fetchResponse.status == 200) {
            const response = await fetchResponse.json();
            response.ocorrencias.forEach((e) => {
                occurrenceList.push(e);
            });
    
            sortDate();
            renderCardsOccurrence();
        }
        
    } catch(error) {
        console.log('Erro ao comunicar com servidor.', error);
    }
}

function renderCardsOccurrence() {
    let latestOccurrences = occurrenceList.slice(0, 3);

    latestOccurrences.forEach((e) => {
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
                    <p class="status">Situação: ${e.status}</p> 
                    <p class="date">${formatDate(e.data) + ' - ' + formatHours(e.data)}</p>
                </div>
            </div>
        `

        divOccurrence.appendChild(cardOccurrence);
    });
}

function sortDate() {
    occurrenceList.sort((a, b) => new Date(b.data) - new Date(a.data));
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