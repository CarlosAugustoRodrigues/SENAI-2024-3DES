import { Ocorrencia } from "../../classes/ocorrencia.js";

const token = JSON.parse(window.localStorage.getItem("dados")).token;
const usuario = JSON.parse(window.localStorage.getItem("dados")).usuario.id;
const nome = JSON.parse(window.localStorage.getItem("dados")).usuario.nome;
const email = JSON.parse(window.localStorage.getItem("dados")).usuario.email;
const URI = `http://localhost:8090/sisur/ocorrencia/usuario/${usuario}`;
var ocorrencias = [];

function carregarOcorrencias() {
    ocorrencias = [];
    fetch(URI, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
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
            inserirDados();
        });
}

function inserirDados() {
    document.querySelector('#nome').textContent = nome;
    document.querySelector('#email').textContent = email;
    document.querySelector('#ocorrencia').textContent = ocorrencias.length;
}

document.addEventListener("DOMContentLoaded", () => {
    carregarOcorrencias();
});
