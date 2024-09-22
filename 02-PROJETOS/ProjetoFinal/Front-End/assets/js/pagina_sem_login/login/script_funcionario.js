const URI = "http://localhost:8090/sisur/funcionario/login";
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

        const role = JSON.parse(window.localStorage.getItem("dados")).funcionario.role;
        console.log(role);

        if (role !== "ADMIN") {
            form.reset();
            window.location.href =
                "/assets/pages/paginas_funcionario/pagina_inicial/pagina_inicial.html";

            return;
        }

        form.reset();
        window.location.href =
            "/assets/pages/paginas_admin/registrar_funcionario/registrar_funcionario.html";
        
    } catch (error) {
        console.log("Erro ao efetuar login: ", error)
    }
})