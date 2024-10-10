const URI = `http://localhost:${PORT}/professor`;
const PORT = 8080;
const form = document.querySelector("#form_registro");

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const data = {
        nome: form.nome.value,
        email: form.email.value,
        senha: form.senha.value
    }

    try {
        await fetch(URI, {
            method: 'POST',
            headers : {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data)
        })
        .then((response) => response.json())
        .then((data) => console.log(data))
        
        form.reset();
        window.location.href = "index.html"
    }
    catch (error){
        console.log(
            "Erro ao tentar se cadastrar! Tente novamente mais tarde.",
            error
          );
    }
});