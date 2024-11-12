#include <iostream>
#include <string>

using namespace std;

int main() {

    string palavra = "pneumoultramicroscopicossilicovulcanoconiotico";
    char letras[26], ansi = 65;
    int posicaoAlfabeto;

    for (int i = 0; i < 26; i++) {
        letras[i] = ansi;
        ansi++;
    }

    for (int i = 0; i < palavra.size(); i++) {

        posicaoAlfabeto = 0;
        char letraAtual = toupper(palavra[i]);

        for (int j = 0; j < 26; j++) {
            if (letraAtual == letras[j]) {
                posicaoAlfabeto = j + 1;
                break;
            }
        }

        cout << posicaoAlfabeto << " ";

    }

    return 0;
}
