const URI = `http://localhost:${PORT}/turma`;
const PORT = 8080;
const divCard = document.querySelector('.div-card')[0];
const idProfessor = JSON.parse(window.localStorage.getItem("professor")).id;
var turmas = [];

async function fetchTurmas() {
    divCard.innerHTML = "";
    turmas = [];
    await fetch(`${URI}/${idProfessor}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => response.json())
        .then(data => data.forEach(turma => turmas.push(turma)))
        .then(() => {
            renderizarTurmas();
        })
}

function renderizarOcorrencias() {
    turmas.forEach((e) => {
        divCard.innerHTML += `
                <div class="card-turma">
                    <div>
                        <span id="nun_turma">T${e.numTurma}</span>
                        <span>-</span>
                        <p id="nome_turma">${e.nomeTurma}</p>
                    </div>
                    
                    <div class="div-btn">
                        <button>
                            <i class="bi bi-eye" onclick=visualizarTurma(${e.id})></i>
                        </button>
                        <button>
                            <i class="bi bi-trash" onclick="excluirTurma(${e.id})"></i>
                        </button>
                    </div>
                </div>
                `
    })
}