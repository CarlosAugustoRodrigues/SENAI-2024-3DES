const hr = document.getElementById('hr');
const min = document.getElementById('min');
const sec = document.getElementById('seg');

function Relogio() {
    let data = new Date();
    let hours = data.getHours();
    let minutes = data.getMinutes();
    let seconds = data.getSeconds();

    if(hours < 10) {
        hr.innerHTML = '0' + hours;
    } else {
        hr.innerHTML = hours;
    }

    if(minutes < 10) {
        min.innerHTML = '0' + minutes;
    } else {
        min.innerHTML = minutes;
    }

    if(seconds < 10) {
        sec.innerHTML = '0' + seconds;
    } else {
        sec.innerHTML = seconds;
    }
}

Relogio();
setInterval(Relogio, 1000);
