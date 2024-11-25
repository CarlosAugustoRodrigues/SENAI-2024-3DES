const questionElement = document.querySelector('.question');
const answersElement = document.querySelector('.answers');
const spn_qts = document.querySelector('.spn-qts');
const text_finish = document.querySelector('.finish span');
const content = document.querySelector('.content');
const content_finish = document.querySelector('.finish');
const btn_restart = document.querySelector('.finish button');

const questions = [
    {
        question: 'Quanto é 1 + 2?',
        answers: [
            {option: '3', correct: true},
            {option: '4', correct: false},
            {option: '2', correct: false},
            {option: '4', correct: false}
        ],
    },
    {
        question: 'Quanto é 5 x 2?',
        answers: [
            {option: '5', correct: false},
            {option: '10', correct: true},
            {option: '7', correct: false},
            {option: '3,14', correct: false}
        ],
    },
    {
        question: 'Qual a hipotenusa de um triângulo retângulo com as medidas dos catetos sendo 4cm e 3cm?',
        answers: [
            {option: '0cm', correct: false},
            {option: '7cm', correct: false},
            {option: '5cm', correct: true},
            {option: '2cm', correct: false}
        ],
    },
    {
        question: 'Qual a fórmula para calcular a área de um triângulo?',
        answers: [
            {option: 'A = l x l', correct: false},
            {option: 'A = b x h', correct: false},
            {option: 'A = (b x h)/2', correct: true},
            {option: 'A = π x r²', correct: false}
        ],
    },
    {
        question: 'Qual é o resultado de 10 - 6 x 2?',
        answers: [
            {option: '-16', correct: false},
            {option: '2', correct: false},
            {option: '4', correct: false},
            {option: '-2', correct: true}
        ],
    },
    {
        question: 'Qual é o resultado de 3³?',
        answers: [
            {option: '9', correct: false},
            {option: '27', correct: true},
            {option: '3', correct: false},
            {option: '81', correct: false}
        ],
    },
    {
        question: 'Uma hora possui quantos segundos?',
        answers: [
            {option: '3600', correct: true},
            {option: '60', correct: false},
            {option: '900', correct: false},
            {option: '4300', correct: false}
        ],
    },
    {
        question: 'Qual é o resultado de 25% de 80?',
        answers: [
            {option: '15', correct: false},
            {option: '20', correct: true},
            {option: '25', correct: false},
            {option: '23', correct: false}
        ],
    },
    {
        question: 'Qual é o nome da figura geométrica que possui quatro lados iguais e quatro ângulos retos?',
        answers: [
            {option: 'Quadrado', correct: true},
            {option: 'Triangulo', correct: false},
            {option: 'Retangulo', correct: false},
            {option: 'Circulo', correct: false}
        ],
    },
    {
        question: 'Qual é o nome da figura geométrica que possui nove lados?',
        answers: [
            {option: 'Octógono', correct: false},
            {option: 'Pentágono', correct: false},
            {option: 'Heptágono', correct: false},
            {option: 'Eneágono', correct: true}
        ],
    }
];

let current_index = 0;
let questionsCorrect = 0;

function loadQuestion() {
    spn_qts.innerHTML = `${current_index + 1} / ${questions.length}`;
    const item = questions[current_index];
    answersElement.innerHTML = '';
    questionElement.innerHTML = item.question;

    item.answers.forEach((answer) => {
        const div = document.createElement('div');

        div.innerHTML = `
            <button class="answer" data-correct="${answer.correct}">${answer.option}</button>
        `;
        answersElement.appendChild(div);
    });

    document.querySelectorAll('.answer').forEach((button) => {
        button.addEventListener('click', nextQuestion);
    });
}

loadQuestion();

function nextQuestion(e) {
    if (e.target.getAttribute('data-correct') === 'true') {
        questionsCorrect++;
    }
    if (current_index < questions.length - 1) {
        current_index++;
        loadQuestion();
    } else {
        finish();
    }
}

function finish() {
    text_finish.innerHTML = `Você acertou ${questionsCorrect} de ${questions.length} questões!`;
    content.style.display = 'none';
    content_finish.style.display = 'flex';
}


btn_restart.onclick = () => {
    content.style.display = 'flex';
    content_finish.style.display = 'none';
    current_index = 0;
    questionsCorrect = 0;
    loadQuestion();
};