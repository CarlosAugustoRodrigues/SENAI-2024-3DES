const cursorDot = document.querySelector('[data-cursor-dot]');
const cursorOutline = document.querySelector('[data-cursor-outline]');
const header = document.querySelector('header');
const logo = document.querySelector('.logo');
let date = new Date()
let currentYear = date.getFullYear();

document.querySelector('#year').textContent = currentYear;

window.addEventListener('mousemove', (e) => {
    const posX = e.clientX;
    const posY = e.clientY;

    cursorDot.style.left = `${posX}px`;
    cursorDot.style.top = `${posY}px`;

    cursorOutline.animate({
        left: `${posX}px`,
        top: `${posY}px`
    }, {duration: 700, fill: 'forwards'});
});

window.addEventListener('scroll', () => {
    header.classList.toggle('effect-blur', scrollY > 0);
    window.scrollY > 0 ? logo.style.filter = 'invert(1)' : logo.style.filter = 'invert(0)';
});