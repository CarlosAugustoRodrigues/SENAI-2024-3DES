const URI = "http://localhost:8090/sisur/usuario/login";
const form = document.querySelector("#form-login");

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const formData = {
        email: form.email.value,
        senha: form.senha.value
    }

    try {
        await fetch(URI, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            window.localStorage.setItem("dados", JSON.stringify(data));
        })

        form.reset();

    } catch(error) {
        console.log("Erro ao efetuar login: ", error)
    }
})