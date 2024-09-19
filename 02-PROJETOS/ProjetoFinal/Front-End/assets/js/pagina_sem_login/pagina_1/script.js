import { Ocorrencia } from "../classe/ocorrencia.js";

const URI = "http://localhost:8090/sisur/ocorrencia";
const divPrincipal = document.querySelector("#div-principal");
var ocorrencias = [];

function carregarOcorrencias() {
    divPrincipal.innerHTML = "";
    let _divCards = document.createElement('div');
    _divCards.classList.add('div-cards');
    _divCards.id = 'div-cards';

    divPrincipal.appendChild(_divCards);
    ocorrencias = []
    fetch(URI)
        .then(response => response.json())
        .then(data => {
            data.forEach(item => {
                const ocorrencia = new Ocorrencia(
                    item.descricao,
                    item.rua,
                    item.bairro,
                    item.cep,
                    item.timestamp,
                    item.setor
                );
                ocorrencias.push(ocorrencia);
            });
            ordenarDataDesc();
            renderizarOcorrencias();
        });
}

function renderizarOcorrencias() {
    const divCards = document.querySelector("#div-cards");
    let ultimasOcorrencias = ocorrencias.slice(0, 3);

    if (ultimasOcorrencias.length > 0) {
        divPrincipal.insertAdjacentElement('afterbegin', ultimasOcorrencias[0].cardDestaqueInicial());
    }
    
    if(ultimasOcorrencias.length > 1) {
        for(let i = 1; i < ultimasOcorrencias.length; i++) {
            divCards.appendChild(ultimasOcorrencias[i].cardInicial());
        }
    }
}

function ordenarDataDesc() {
    ocorrencias.sort((a, b) => new Date(b.datahora) - new Date(a.datahora));
}

document.addEventListener("DOMContentLoaded", () => {
    carregarOcorrencias();
})