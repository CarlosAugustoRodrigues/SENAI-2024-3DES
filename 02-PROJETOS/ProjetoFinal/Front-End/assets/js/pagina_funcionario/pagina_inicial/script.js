import { Ocorrencia } from "../../classes/ocorrencia.js";

const nome = JSON.parse(window.localStorage.getItem("dados")).funcionario.nome;
const token = JSON.parse(window.localStorage.getItem("dados")).token;
const setor = JSON.parse(window.localStorage.getItem("dados")).funcionario.setor;

const URI = `http://localhost:8090/sisur/ocorrencia/${setor}`;
const divCards = document.querySelector("#div-cards");
var ocorrencias = [];

function carregarOcorrencias() {
    divCards.innerHTML = "";
    ocorrencias = [];
    fetch(URI, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
        .then((response) => response.json())
        .then((data) => {
            data.forEach((item) => {
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
    let ultimasOcorrencias = ocorrencias.slice(0,5);
    ultimasOcorrencias.forEach((e) => {
        divCards.appendChild(e.cardOcorrenciaFuncionario());
    });

    document.querySelector("#setor").textContent = setor;
}

function ordenarDataDesc() {
    ocorrencias.sort((a, b) => new Date(b.datahora) - new Date(a.datahora));
}

document.addEventListener("DOMContentLoaded", () => {
    const [, primeiroNome] = nome.match(/(\S+) /) || [];
    document.querySelector("#nome").textContent = primeiroNome;
    carregarOcorrencias();
});
