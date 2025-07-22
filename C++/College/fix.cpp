#include <iostream>
using namespace std;

// Template class definition
template <class T>
class Largest {
private:
    T a, b, c;

public:
    // Constructor
    Largest(T x, T y, T z) {
        a = x;
        b = y;
        c = z;
    }

    // Function to return the largest
    void getLargest() {
        if(a>b)
        {
            if(a>c){
                cout<<"a is largest"<<a<<endl;
            }else
            {
            cout<<"c is largest"<<c<<endl;}
        }
        else if(b>c){
            cout<<"b is largest"<<b<<endl;
        }else{
        cout<<"c s=is largest"<<c;}
    }
};

int main() {
    // Testing with integers
    Largest<int> intObj(15, 9, 3);
    intObj.getLargest();


    Largest<float> floatObj(13.2, 7.5, 4.8);
     floatObj.getLargest();


    return 0;
}
