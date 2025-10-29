let p1=document.createElement('p');
p1.innerText="Hey I am red";
document.querySelector('body').append(p1);
p1.classList.add('red');

let h3=document.createElement('h3');
h3.innerText="Hey I am a blue h3!";
document.querySelector('body').append(h3);
h3.classList.add('blue');

let d=document.createElement('div');
let h1=document.createElement('h1');
let p2=document.createElement('p');

h1.innerText="I am in a div";
p2.innerText="Me too!!";

d.append(h1);
d.append(p2);

d.classList.add("divbox");

document.querySelector('body').append(d);
