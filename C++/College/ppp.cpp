#include<iostream>
#include<exception>
using namespace std;

int division(int x,int y){
    try{
        if(y==0)
            throw invalid_argument("Cannot divide by 0");
        return x/y;
    }
    catch(invalid_argument &e){
        throw;
    }
}
int main(){
    int di1,di2;
    try{
        di1=division(10,2);
        cout<<di1<<endl;
        di2=division(14,0);
        cout<<di2<<endl;

    }
    catch(exception &e){
    cout<<e.what()<<endl;
    }

}
