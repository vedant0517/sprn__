#include<stdio.h>
int main()
{int a[]={1,2,3,4,5},j=4,i;//,first=0,last=4;
int b[5];


/*while(first<last){
    int temp=a[last];
    a[last]=a[first];
    a[first]=temp;

    first++;
    last--;
}
for(int i=0;i<5;i++){
    printf("%d ",a[i]);
}*/
for( i=0;i<5;i++){
    b[j]=a[i];
     printf("%d",b[j]);
    j--;
}

return 0;
}
