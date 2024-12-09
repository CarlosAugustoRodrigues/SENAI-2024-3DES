const URI = "http://localhost:3000/funcionario/registro";
const form = document.querySelector('#form-registration-employee');
const token = JSON.parse(window.localStorage.getItem("dadosFuncionario")).token;


form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const data = {
        nome: form.name.value,
        setor: form.sector.value,
        responsabilidade: form.responsibility.value,
        email: form.email.value,
        password: form.password.value
    };

    try {
        const registration = await fetch(URI, {
            method: 'POST',
            headers: {
                'Content-Type':'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(data)
        });

        if(registration.status == 201) {
            form.reset();
            alert('Funcionário registrado com sucesso!');
        }
        
    } catch(error) {
        console.error("Erro ao registrar funcionário!", error)
    }
})