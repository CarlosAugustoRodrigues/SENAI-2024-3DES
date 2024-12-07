const URI = 'http://localhost:3000/login/funcionario';
const form = document.querySelector('#form-login');

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const data = {
        email: form.email.value,
        password: form.password.value
    };

    try {
        const login = await fetch(URI, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if(login.status == 200) {
            const response = await login.json();
            window.localStorage.setItem('dadosFuncionario', JSON.stringify(response));
            const role = JSON.parse(window.localStorage.getItem('dadosFuncionario')).infoConta.role;
            console.log(role)
            form.reset();

            if(role != 'ADMIN') {
                window.location.href = 'http://127.0.0.1:5500/FrontEnd/assets/pages/employee/index.html'
                return
            }

            window.location.href = 'http://127.0.0.1:5500/FrontEnd/assets/pages/admin/index.html'
              
        } else {
            alert('Email ou senha incorreta.');
        }
        
    } catch(error) {
        console.error('Erro ao fazer login', error);
    }
})