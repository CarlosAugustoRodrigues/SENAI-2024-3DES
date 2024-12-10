const token = JSON.parse(window.localStorage.getItem("dadosFuncionario")).token;
const URI = 'http://localhost:3000/funcionario';
const divEmployee = document.querySelector('#div-employee');
const formPassword = document.querySelector('#form-password');

async function fetchEmployee() {
    try {
        const fetchEmployee = await fetch(URI, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if(fetchEmployee.status == 200) {
            const response = await fetchEmployee.json();
            console.log(response)
            response.funcionarios.forEach( (e) => {
                renderEmployee(e.nome, e.infoConta[0].email, e.setor);
            });
        }
    } catch(error) {
        console.log('Erro ao buscar funcionários', error);
    }
}

function renderEmployee(name, email, sector) {
    const cardEmployee = document.createElement('div');
    cardEmployee.classList.add('card-employee');

    cardEmployee.innerHTML = `
        <img src="../../img/user.png">
        
        <div class="info-employee">
            <p>Nome: <span>${name}</span></p>
            <p>Email: <span>${email}</span></p>
            <p>Setor: <span>${sector}</span></p>
        </div>

        <div>
            <button onclick="openModelChangePassword('${email}')">Alterar senha</button>
        </div>
    `

    divEmployee.appendChild(cardEmployee);
}

function openModelChangePassword(email) {
    document.querySelector('#overlay').classList.add('show-overlay');

    setTimeout(() => {
        document.querySelector('#model-password').classList.add('show');
    }, 300)

    document.querySelector('#form-password').email.value = email
}

function closeModelChangePassword() {
    document.querySelector('#model-password').classList.remove('show');
    document.querySelector('#overlay').classList.remove('show-overlay');
    document.querySelector('#form-password').email.value = ''
}

formPassword.addEventListener('submit', async (e) => {
    e.preventDefault();

    const data = {
        senha: formPassword.password.value
    }

    try {
        const changePassword = await fetch(`${URI}/alterar_senha/${formPassword.email.value}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(data)
        });

        if(changePassword.status == 200) {
            alert('Senha alterada com sucesso!');

            closeModelChangePassword();
            fetchEmployee()
        }

    } catch(error) {
        console.log('Erro ao alterar senha', error);
    }    

})

document.addEventListener('DOMContentLoaded', () => {
    fetchEmployee();
});