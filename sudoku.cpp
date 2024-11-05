#include <iostream>

using namespace std;

int main() {
    int tabuleiro[3][3][3][3] = {};
    
    for (int i = 0; i < 3; i++) {
        cout << "i--------------------------------------------------"<< endl;;
        for (int j = 0; j < 3; j++) {
            cout << "j->";
            for (int k = 0; k < 3; k++) {
                cout << "k[";
                for (int l = 0; l < 3; l++) {
                    tabuleiro[i][j][k][l] = k;
                    cout << "l";
                    cout << "["<< tabuleiro[i][j][k][l] << "]";
                }
                cout << "] ";
            }
            cout << endl;
        }
        cout << endl;
    }

    return 0;
}