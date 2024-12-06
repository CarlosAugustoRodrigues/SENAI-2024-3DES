const btnMenu = document.querySelector('#btn-menu');
const btnCloseMenu = document.querySelector('#btn-close-menu');
const menu = document.querySelector('#menu');

btnMenu.addEventListener('click', (event) => {
    event.stopPropagation();
    menu.classList.add('show-menu');
    document.body.classList.add('block');
});

btnCloseMenu.addEventListener('click', (event) => {
    event.stopPropagation();
    menu.classList.remove('show-menu');
    setTimeout(() => {
        document.body.classList.remove('block');
    }, 300)
});

document.body.addEventListener('click', () => {
    if (menu.classList.contains('show-menu')) {
        menu.classList.remove('show-menu');
        setTimeout(() => {
            document.body.classList.remove('block');
        }, 300)
    }
});

menu.addEventListener('click', (event) => {
    event.stopPropagation();
});
