const URI = 'http://localhost:3000/login/usuario';
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
    
        const response = await login.json();
        window.localStorage.setItem('dadosUsuario', JSON.stringify(response));
        console.log(window.localStorage.getItem('dadosUsuario').infoConta.email);

        form.reset();
        window.location.href = 'http://127.0.0.1:5500/FrontEnd/assets/pages/user/index.html'
    } catch(error) {
        console.error('Erro ao tentar fazer login', error);
    }
})