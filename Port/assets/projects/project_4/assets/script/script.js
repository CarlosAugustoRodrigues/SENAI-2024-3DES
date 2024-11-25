const modal =  document.querySelectorAll('dialog');
const body = document.querySelector('body');
const buttons = document.querySelectorAll('main button');
const btnClose = document.querySelectorAll('.btn-close');


// Modal Contato
function openModal() {
    body.style.overflowY = 'hidden'
    modal[0].showModal()
}


buttons.forEach((button) => {
    button.addEventListener('click', () => {
        const btnId = button.getAttribute('data-button-id')

        if(btnId === '1'){
            body.style.overflow = 'hidden';
            modal[1].showModal();
        } else if (btnId === '2'){
            body.style.overflow = 'hidden';
            modal[2].showModal();
        } else if(btnId === '3'){
            body.style.overflow = 'hidden';
            modal[3].showModal();
        } else  if(btnId === '4'){
            body.style.overflow = 'hidden';
            modal[4].showModal();
        } else {
            body.style.overflow = 'hidden';
            modal[5].showModal();
        }
    });
});

btnClose.forEach((btnClose) => {
    btnClose.addEventListener("click", () => {
        const btnCloseId = btnClose.getAttribute('data-button-close-id')

        if(btnCloseId === '0') {
            modal[0].close()
        } else if(btnCloseId === '1') {
            modal[1].close()
        } else if(btnCloseId === '2') {
            modal[2].close()
        } else if(btnCloseId === '3') {
            modal[3].close()
        } else if(btnCloseId === '4') {
            modal[4].close()
        } else {
            modal[5].close()
        }

    });
});


modal.forEach((e) => {
    e.addEventListener('close', () => {
        body.style.overflowY = 'auto'
    });
});