const menuLateral = document.querySelector('#menu-lateral');
const overlay = document.querySelector('#overlay');

function mostrarMenu() {
    menuLateral.classList.remove('menu-lateral-none');
    menuLateral.classList.add('animation-menu-lateral');

    overlay.classList.remove('overlay-none');
    overlay.classList.add('animation-overlay');
}

function fecharMenu() {
    menuLateral.classList.remove('animation-menu-lateral');
    menuLateral.classList.add('animation-menu-lateral-fechar');

    setTimeout(() => {
        menuLateral.classList.add('menu-lateral-none');
        overlay.classList.add('overlay-none');
        overlay.classList.remove('animation-overlay');
        overlay.classList.add('animation-overlay-tirar');

        setTimeout(() => {
            menuLateral.classList.remove('animation-menu-lateral-fechar');
            overlay.classList.remove('animation-overlay-tirar');
        }, 200)
    }, 200)
}