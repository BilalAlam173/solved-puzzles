#include <iostream>
using namespace std;
class position {
public:
    int row;
    int col;
};

position queenPosition[4];

int rowFirstQueen = 0;
int colFirstQueen = 0;
bool success = false;

int board[4][4] = {
    { 0, 0, 0, 0 },
    { 0, 0, 0, 0 },
    { 0, 0, 0, 0 }
};
bool diagonal(position queen, int row, int col)
{
    int count = 1;
    //downward right
    while (count < 4) {
        for (int i = queen.row + count; i < 4; i++) {
            for (int j = queen.col + count; j < 4; j++) {
                if (i == row || j == col) {
                    return false;
                }
            }
        }
        count++;
    }

    count = 1;
    //downward left
    while (count < 4) {
        for (int i = queen.row + count; i < 4; i++) {
            for (int j = queen.col - count; j >= 0; j--) {
                if (i == row || j == col) {
                    return false;
                }
            }
        }
        count++;
    }

    count = 1;
    //upward left
    while (count < 4) {
        for (int i = queen.row - count; i >= 4; i--) {
            for (int j = queen.col - count; j >= 0; j--) {
                if (i == row || j == col) {
                    return false;
                }
            }
        }
        count++;
    }

    count = 1;
    //upward right
    while (count < 4) {
        for (int i = queen.row - count; i >= 4; i--) {
            for (int j = queen.col + count; j < 4; j++) {
                if (i == row || j == col) {
                    return false;
                }
            }
        }
        count++;
    }

    return true;
}
bool checkAttack(int i, int j)
{
    for (int i = 0; i < 4; i++) {

        //same row
        if (queenPosition[i].row == i) {
            return false;
        }

        //same column
        if (queenPosition[i].col == j) {
            return false;
        }

        //same diagonal
        if (!diagonal(queenPosition[i], i, j)) {
            return false;
        }
    }
    return true;
}
void map()
{

    board[rowFirstQueen][colFirstQueen] = 1;
    queenPosition[0].row = rowFirstQueen;
    queenPosition[0].col = colFirstQueen;

    for (int queen = 1; queen < 4; queen++) {
        bool found = false;
        for (int i = rowFirstQueen; i < 4; i++) {
            for (int j = colFirstQueen; j < 4; j++) {
                if (checkAttack(i, j)) {
                    board[i][j] = 1;
                    queenPosition[queen].row = i;
                    queenPosition[queen].row = j;
                    found = true;
                }
            }
        }
        if (!found) {
            if (colFirstQueen < 4) {
                colFirstQueen++;
            }
            else {
                colFirstQueen = 0;
                rowFirstQueen++;
            }
            map();
        }
        else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    board[i][j];
                }
            }
        }
    }
}

int main()
{
    map();
    return 0;
}

