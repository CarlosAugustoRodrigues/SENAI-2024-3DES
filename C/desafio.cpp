#include <iostream>
#include <string>

using namespace std;

void sevenSegment(string time)
{
    string numbersHours = "";
    string display[3] = {};
    string numbers[10][3] = {
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
    };

    for (int i = 0; i < time.size(); i++)
    {
        if (time[i] == ':')
        {
            numbersHours += "";
        }
        else
        {
            numbersHours += time[i];
        }
    }

    for (int i = 0; i < numbersHours.size(); i++)
    {
        int currentlyNumber = numbersHours[i] - '0';

            for (int k = 0; k < 3; k++)
            {
                display[k] = numbers[currentlyNumber][k];
                cout << display[k] << endl;
            }
    }
}

int main()
{
    sevenSegment("23:59");
    return 0;
}