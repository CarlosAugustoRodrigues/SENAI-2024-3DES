export class Ocorrencia {
    constructor(
        descricao,
        rua,
        bairro,
        cep,
        datahora,
        setor
    ) {
        this.descricao = descricao;
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.datahora = new Date(datahora);
        this.setor = setor;
    }


    // Getters
    get getDescricao() {
        return this.descricao;
    }

    get getRua() {
        return this.rua;
    }

    get getBairro() {
        return this.bairro;
    }

    get getCep() {
        return this.cep;
    }

    get getDatahora() {
        return this.datahora;
    }

    get getSetor() {
        return this.setor;
    }

    // Setters
    set setDescricao(valor) {
        this.descricao = valor;
    }

    set setRua(valor) {
        this.rua = valor;
    }

    set setBairro(valor) {
        this.bairro = valor;
    }

    set setCep(valor) {
        this.cep = valor;
    }

    set setDatahora(valor) {
        this.datahora = valor;
    }

    set setSetor(valor) {
        this.setor = valor;
    }

    criarCard() {
        let div = document.createElement('div');
        div.classList.add('cards-destaque-sec');
        div.innerHTML = `
            <img src="./assets/img/logo.png">
        
            <div class="infos-ocorrencia">
                <div>
                    <p class="descricao">${this.getDescricao}</p>

                    <p class="endereco">Endereço: <span>${this.getRua + " - " + this.getBairro}</span></p>
                </div>

                <div>
                    <p class="data-hora">${this.formatarData()} <span>${this.formatarHora()}</span></p>
                </div>
            </div>`

        return div;
    }

    criarCardDestaque() {
        let div = document.createElement('div');
        div.classList.add('cards-destaque-pri');
        div.innerHTML = `
            <img src="./assets/img/logo.png">

            <div class="infos-ocorrencia">
                <div>
                    <p class="descricao">${this.descricao}</p>

                    <p class="endereco">Endereço: <span>${this.getRua + " - " + this.getBairro}</span></p>
                </div>

                <div>
                    <p class="data-hora">${this.formatarData()} <span>${this.formatarHora()}</span></p>
                </div>
            </div>`
        
        return div;
    }

    formatarData() {
        const dia = String(this.datahora.getDate()).padStart(2, '0');
        const mes = String(this.datahora.getMonth() + 1).padStart(2, '0');
        const ano = this.datahora.getFullYear();
        return `${dia}/${mes}/${ano}`;
    }
    formatarHora() {
        const horas = String(this.datahora.getHours()).padStart(2, '0');
        const minutos = String(this.datahora.getMinutes()).padStart(2, '0');
        return `${horas}:${minutos}`;
    }
}