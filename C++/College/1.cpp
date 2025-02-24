#include<iostream>
using namespace std;
class Sample{
int x;
    void putSquare(){
        cout<<"Square is:"<<x*x;
}
public:
    void setValue(){
        cout<<"Enter number";
        cin>>x;
        putSquare();
    }
};
    int main(){
        Sample s1;
        s1.setValue();
    }




