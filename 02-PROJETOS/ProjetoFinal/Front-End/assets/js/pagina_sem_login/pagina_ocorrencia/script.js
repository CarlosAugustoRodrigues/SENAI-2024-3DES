import { Ocorrencia } from "../../classes/ocorrencia.js";

const URI = "http://localhost:8090/sisur/ocorrencia";
const divCards = document.querySelector("#div-cards");
var ocorrencias = [];

function carregarOcorrencias() {
  divCards.innerHTML = "";
  ocorrencias = [];
  fetch(URI)
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
  ocorrencias.forEach((e) => {
    divCards.appendChild(e.cardOcorrencia());
  });
}

function ordenarDataDesc() {
  ocorrencias.sort((a, b) => new Date(b.datahora) - new Date(a.datahora));
}

document.addEventListener("DOMContentLoaded", () => {
  carregarOcorrencias();
});
