class Student{
    constructor(name,age,marks){
        this.name=name;
        this.age=age;
        this.marks=marks;
    }
    talk(){
        console.log(`Hi,I am ${this.name}`);
    }
}


let s1=new Student("Vedant",20,95);

class Teacher{
    constructor(name,age,subject){
        this.name=name;
        this.age=age;
        this.subject=subject;
    }
    talk(){
        console.log(`Hi,I am ${this.name}`);
    }
}