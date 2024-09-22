const URI = "http://localhost:8090/sisur/usuario";
const form = document.querySelector("#form-registro");

form.addEventListener("submit", async (e) => {
  e.preventDefault();

  if (form.senha.value !== form.senhac.value) {
    return alert("Senha diferentes!");
  }

  const formData = {
    nome: form.nome.value,
    email: form.email.value,
    senha: form.senha.value,
  };

  try {
    await fetch(URI, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    })
      .then((response) => response.json())
      .then((data) => console.log(data))
      
    form.reset();

    if (confirm("Conta registrada com sucesso!")) {
      window.location.href =
        "/assets/pages/paginas_sem_login/pagina_login/login.html";
    }
  } catch (error) {
    console.log(
      "Erro ao tentar se cadastrar! Tente novamente mais tarde.",
      error
    );
  }
});
