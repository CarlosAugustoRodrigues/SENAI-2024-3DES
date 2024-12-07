const form = document.querySelector("#form-register-occurrence");
const URI = `http://localhost:3000/ocorrencia`;
const usuarioId = JSON.parse(window.localStorage.getItem("dadosUsuario")).Usuario.id;
const token = JSON.parse(window.localStorage.getItem("dadosUsuario")).token

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const data = {
        descricao: form.description.value,
        rua: form.street.value,
        bairro: form.district.value,
        cidade: form.city.value,
        cep: form.cep.value,
        setor: form.sector.value,
        usuario: usuarioId
    }

    try {
        const occurrence = await fetch(URI, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(data)
        });
    
        if(occurrence.status == 201) {
            form.reset();
            window.location.href = 'http://127.0.0.1:5500/FrontEnd/assets/pages/user/myOccurrences.html'
        }
    } catch(error) {
        console.error("Erro ao registrar ocorrência", error);
    }
})