const employeeName = JSON.parse(window.localStorage.getItem("dadosFuncionario")).funcionario.nome;
const email = JSON.parse(window.localStorage.getItem("dadosFuncionario")).infoConta.email;
const sector = JSON.parse(window.localStorage.getItem("dadosFuncionario")).funcionario.setor;

document.addEventListener('DOMContentLoaded', () => {
    document.querySelector('#employeename').innerHTML = employeeName
    document.querySelector('#employeeemail').innerHTML = email
    document.querySelector('#employeesector').innerHTML = sector
})