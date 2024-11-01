#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

const int linha = 4, coluna = 3, pares = (linha * coluna)/2;

int auxiliarPares = pares, linhaAle, colunaAle, pontuacao = 0, x1, x2, y1, y2;
char TABGAB[linha][coluna], TABSEC[linha][coluna], letra = 65;

void preencherTabuleiros() {
    srand(time(NULL));

    for (int i = 0; i < linha; i++) {
        for (int j = 0; j < coluna; j++) {
            TABGAB[i][j] = '0';   
            TABSEC[i][j] = '*';   
        }
    }
    

    while (auxiliarPares > 0) {
        for (int i = 0; i < 2; i++) {
            do {
                linhaAle = rand() % linha;
                colunaAle = rand() % coluna;
            } while (TABGAB[linhaAle][colunaAle] != '0');
            
            TABGAB[linhaAle][colunaAle] = letra;
        }
        letra++;
        auxiliarPares--;
    }
}

void jogo() {

    while (pontuacao < pares) {
        
        cout << "Pontuacao: " << pontuacao << endl;
        
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++){
                cout << TABGAB[i][j] << " ";
            }
            cout << endl;
        }
        
        cout << endl;
        
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++){
                cout << TABSEC[i][j] << " ";
            }
            cout << endl;
        }

        cout << "Selecione a primeira carta, digite a linha e coluna: ";
        cin >> x1 >> y1;
        cout << endl;

        TABSEC[x1][y1] = TABGAB[x1][y1];

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++){
                cout << TABSEC[i][j] << " ";
            }
            cout << endl;
        }


        cout << "Selecione a segunda carta, digite a linha e coluna: ";
        cin >> x2 >> y2;
        cout << endl;

        TABSEC[x2][y2] = TABGAB[x2][y2];

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++){
                cout << TABSEC[i][j] << " ";
            }
            cout << endl;
        }

        if (TABSEC[x1][y1] != TABSEC[x2][y2]) {
            cout << "Cartas diferentes, tente novamente!" << endl;
            TABSEC[x1][y1] = '*';
            TABSEC[x2][y2] = '*';
        } else {
            cout << "Parabéns, você encontrou 1 par de cartas" << endl;
            pontuacao++;
        }
    }                
}

int main() {
    
    preencherTabuleiros();
    jogo();

    return 0;
}