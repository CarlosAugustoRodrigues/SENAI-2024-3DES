import { Ocorrencia } from "./ocorrencia.js";

const URI = "http://localhost:8090/sisur/ocorrencia";
const divPrincipal = document.querySelector("#div-principal");
const divCards = document.querySelector("#div-cards");
var ocorrencias = [];

function carregarOcorrencias() {
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
            renderizarOcorrencias();
        });
}

function renderizarOcorrencias() {
}

document.addEventListener("DOMContentLoaded", () => {
    carregarOcorrencias();
})