#include<iostream>
using namespace std;
class BankAccount
{
private:
    string name;
    int accno;
    float balance;
public:
    BankAccount(string s,int a,float b){
    name=s;
    accno=a;
    balance=b;
    }

    BankAccount(BankAccount &acc){
    name=acc.name + " & Joint Holder";
    accno=acc.accno+1;
    balance=acc.balance;
    }

    void display(){
    cout<<"Name : "<<name<<endl;
    cout<<"Account no : "<<accno<<endl;
    cout<<"Balance : "<<balance<<endl;
    }

};
class SubjectB;

class SubjectA
{   int markA=96;
    friend int add(SubjectA,SubjectB);

};
class SubjectB
{   int markB=59;
    friend int add(SubjectA,SubjectB);

};

int add(SubjectA x,SubjectB y){
    return x.markA+y.markB;

}























int main(){
    BankAccount a1("Vedant Kapgate",100,5000.0);
    cout<<"Original Account"<<endl;
    a1.display();
    BankAccount jointAcc(a1);
     cout<<"Joint Account"<<endl;
     jointAcc.display();

     SubjectA a;
     SubjectB b;
     cout<<"Add is "<<add(a,b);


return 0;


}
