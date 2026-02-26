const { faker } = require('@faker-js/faker');
const mysql = require('mysql2');
const express=require("express");
const app = express();

const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  database: 'omega_app',
  password:'Mysql@123'
});

let getRandomUser = () =>{
  return [
   faker.string.uuid(),
   faker.internet.username(),
   faker.internet.email(),
   faker.internet.password(),
  ];
}



//  try{
//     connection.query(q,[data],(err,result)=>{
//      if(err) throw err;
//      console.log(result);
//   })
//  } catch(err){
//    console.log(err);
//  }

//  connection.end();

 app.get("/",(req,res)=>{
  let q=`select count(*) from user`;
   try{
    connection.query(q,(err,result)=>{
     if(err) throw err;
     console.log(result[0]["count(*)"]);
     res.send("Success");
  });
 } catch(err){
   console.log(err);
   res.send("some error in DB");
 }
 });


 app.listen("8080",()=>{
  console.log("server is listening to port 8080");
 })