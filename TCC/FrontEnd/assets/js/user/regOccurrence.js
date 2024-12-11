const form = document.querySelector("#form-register-occurrence");
const URI = `http://localhost:3000/ocorrencia`;
const usuarioId = JSON.parse(window.localStorage.getItem("dadosUsuario")).Usuario.id;
const token = JSON.parse(window.localStorage.getItem("dadosUsuario")).token;
const imageInput = document.querySelector("#images");

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const files = imageInput.files;
    if (files.length > 3) {
        alert('Você pode enviar no máximo 3 imagens.');
        return;
    }

    const data = new FormData();
    data.append('descricao', form.description.value);
    data.append('rua', form.street.value);
    data.append('bairro', form.district.value);
    data.append('cidade', form.city.value);
    data.append('cep', form.cep.value);
    data.append('setor', form.sector.value);
    data.append('usuario', usuarioId);

    for (let i = 0; i < files.length; i++) {
        data.append('images', files[i]);
    }

    try {
        const response = await fetch(URI, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
            },
            body: data,
        });

        if (response.status === 201) {
            form.reset();
            window.location.href = 'http://127.0.0.1:5500/FrontEnd/assets/pages/user/myOccurrences.html';
        }
    } catch (error) {
        console.error("Erro ao registrar ocorrência", error);
    }
});
