const modal =  document.querySelectorAll('dialog');
const body = document.querySelector('body');


// Modal Contato
function openModal() {
    body.style.overflowY = 'hidden'
    modal[0].showModal()
}
function closeModal() {
    modal[0].close()
}
modal[0].addEventListener('close', () => {
    body.style.overflowY = 'auto'
});