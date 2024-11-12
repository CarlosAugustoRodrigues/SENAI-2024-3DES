#include <iostream>

using namespace std;

int main() {

    int tamanhoMatriz = 5, valorInicial = 10;
    int matriz1[tamanhoMatriz][tamanhoMatriz] = {}, matriz2[tamanhoMatriz][tamanhoMatriz] = {}, matrizSoma[tamanhoMatriz][tamanhoMatriz] = {};

    for (int i = 0; i < tamanhoMatriz; i++){
        for (int j = 0; j < tamanhoMatriz; j++) {
            valorInicial += 1;
            
            matriz1[i][j] = valorInicial;
            matriz2[i][j] = valorInicial;

            matrizSoma[i][j] = matriz1[i][j] + matriz2[i][j];
            cout << matrizSoma[i][j] << " "; 
        }
        valorInicial++;
        cout << endl;
    }

    return 0;
}
