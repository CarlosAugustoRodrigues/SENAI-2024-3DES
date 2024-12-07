const URI = "http://localhost:3000/usuario/registro";
const form = document.querySelector('#form-registration');

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const data = {
        nome: form.name.value,
        telefone: form.telephone.value,
        email: form.email.value,
        password: form.password.value
    };

    try {
        const registration = await fetch(URI, {
            method: 'POST',
            headers: {
                'Content-Type':'application/json'
            },
            body: JSON.stringify(data)
        });

        if(registration.status == 201) {
            form.reset();
            window.location.href = 'http://127.0.0.1:5500/FrontEnd/assets/pages/guest/login.html';
        }
        
    } catch(error) {
        console.error("Erro ao registrar-se!", error)
    }
})