const URI = `http://localhost:${PORT}/professor/login`;
const PORT = 8080;
const form = document.querySelector("#form_login");

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const data = {
        email: form.email.value,
        senha: form.senha.value
    };

    await fetch(URI, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Email ou senha incorretos');
        }
        return response.json();
    })
    .then(data => {
        window.localStorage.setItem('professor', JSON.stringify(data));
        window.location.href = 'turmas.html';

    })
    .catch(error => {
        console.error('Erro ao fazer login, tenta novamente mais tarde!', error);
    });
});