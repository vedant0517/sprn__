#include<iostream>
using namespace std;
class sam{
    public:
    virtual void display(){
    cout<<"Display of Base"<<endl;
    }
};

class derived:public sam{
public:
    void display(){
    cout<<"Derived"<<endl;
    }


};


int main(){
sam s2,*s1;
s1=&s2;
s1->display();
derived d1;
s1=&d1;
s1->display();


return 0;
}
