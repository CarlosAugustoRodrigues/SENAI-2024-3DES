const spanEmail = document.querySelector('#span-email');
const spanTelephone = document.querySelector('#span-telephone');
const textWelcome = document.querySelector('#text-welcome');

const nameUser = JSON.parse(window.localStorage.getItem("dadosUsuario")).Usuario.nome;
const telephone = JSON.parse(window.localStorage.getItem("dadosUsuario")).Usuario.telefone;
const email = JSON.parse(window.localStorage.getItem("dadosUsuario")).infoConta.email;

document.addEventListener('DOMContentLoaded', () => {
    textWelcome.innerHTML = `
        <h1>Bem-Vindo(a),<br>${nameUser}</h1>
    `;
    spanEmail.innerHTML = email;
    spanTelephone.innerHTML = telephone;
})