const URI = "http://localhost:8090/sisur/funcionario";
const form = document.querySelector('#form-funcionario');
const token = JSON.parse(window.localStorage.getItem("dados")).token;

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    if (form.senha.value !== form.senhac.value) {
        return alert("Senha diferentes!");
    }

    const data= {
        nome: form.nome.value,
        email: form.email.value,
        senha: form.senha.value,
        setor: form.setor.value
    }

    try {
        await fetch(URI, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(data)
        })
        .then((response) => response.json())
        .then((data) => console.log(data))

        form.reset();
        alert("Funcionário cadastrado com sucesso!");
    } 
    catch(error) {
        console.log("Erro ao registrar ocorrência: ", error);
    }
})