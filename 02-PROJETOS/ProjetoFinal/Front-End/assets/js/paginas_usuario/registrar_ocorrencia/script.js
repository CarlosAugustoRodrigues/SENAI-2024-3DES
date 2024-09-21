const URI = "http://localhost:8090/sisur/ocorrencia";
const form = document.querySelector('#form-ocorrencia');
const usuario = JSON.parse(window.localStorage.getItem("dados")).usuario.id;
const token = JSON.parse(window.localStorage.getItem("dados")).token;

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const data= {
        descricao: form.descricao.value,
        rua: form.rua.value,
        bairro: form.bairro.value,
        cep: form.cep.value,
        setor: form.setor.value,
        usuario: usuario
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
    } 
    catch(error) {
        console.log("Erro ao registrar ocorrência: ", error);
    }
})