#include <iostream>
using namespace std;

class Average {
    int x1, x2, x3;

    float avg() {
        return (float)(x1 + x2 + x3) / 3;
    }

public:
    void getData() {
        cout << "Enter 3 numbers to calculate the average: ";
        cin >> x1 >> x2 >> x3;
        cout << "Average is: " << avg() << endl;
    }
};
int main() {
    Average s1, s2, s3;
    s1.getData();
    s2.getData();
    s3.getData();
    return 0;
}
