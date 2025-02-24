#include<iostream>
using namespace std;
class Average{
int x1,x2,x3;
    void avg(){
        cout<<"Average is:"<<(x1+x2+x3)/3 <<endl;
}
public:
    void getData(){
        cout<<"Enter 3 number to get average"<<endl;
        cin>>x1>>x2>>x3;
        avg();
    }
};
    int main(){
        Average s1,s2,s3;
        s1.getData();
        s2.getData();
        s3.getData();
        return 0;
    }
