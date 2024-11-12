#include <iostream>
#include <string>

using namespace std;

void sevenSegment(string hours) {
    int currentlyNumber;
    string numbersHours = hours;
    string display[3] = {};
    string numbers[11][3] = {
        {" _ ", "| |", "|_|"}, // 0
        {"   ", "  |", "  |"}, // 1
        {" _ ", " _|", "|_ "}, // 2
        {" _ ", " _|", " _|"}, // 3
        {"   ", "|_|", "  |"}, // 4
        {" _ ", "|_ ", " _|"}, // 5
        {" _ ", "|_ ", "|_|"}, // 6
        {" _ ", "  |", "  |"}, // 7
        {" _ ", "|_|", "|_|"}, // 8
        {" _ ", "|_|", " _|"}, // 9
        {"   ", " . ", " . "}, // :
    };

    for (int k = 0; k < 3; k++) {
        for (unsigned int i = 0; i < numbersHours.size(); i++) {
            if (numbersHours[i] == ':') currentlyNumber = 10;
            else currentlyNumber = numbersHours[i] - '0';
            
            display[k].append(numbers[currentlyNumber][k]);
        }
        cout << display[k] << endl;
    }
}

int main() {
    sevenSegment("23:59");
    return 0;
}