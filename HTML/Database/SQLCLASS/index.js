const { faker } = require('@faker-js/faker');
const mysql = require('mysql2');
const express = require("express");
const app = express();
const path=require("path")
const methodOverride=require("method-override");

app.use(methodOverride("_method"));
app.use(express.urlencoded({extended:true}));
app.set("view engine","ejs");
app.set("views",path.join(__dirname,"/views"));

const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  database: 'omega_app',
  password: 'Mysql@123'
});

let getUser = () => {
  return [
   faker.string.uuid(),
   faker.internet.username(),
   faker.internet.email(),
   faker.internet.password(),
  ];
}

 app.get("/", (req, res) => {
  let q = `SELECT COUNT(*) FROM user`;
  try{
    connection.query(q, (err, result) => {
      if(err) throw err;
      let count=result[0]["COUNT(*)"];
      res.render("home.ejs",{count});
  });
  } catch(err){
    console.log(err);
    res.render("Error in DB");
  }
});

app.get("/user", (req, res) => {
  let q = `SELECT * FROM user`;
  try{
    connection.query(q, (err, users) => {
      if(err) throw err;
      res.render("showusers.ejs",{users});
  });
  } catch(err){
    console.log(err);
    res.send("Error in DB");
  }
});

//Edit route
app.get("/user/:id/edit", (req, res) => {
  let {id}=req.params;
  let q=`select * from user where id='${id}'`;

  try{
    connection.query(q,(err,result)=>{
      if(err) throw err;
      let user=result[0];
      res.render("edit.ejs",{user})
    });
  } catch(err){
    console.log(err);
    res.send("some error in DB");
  }
});

//update(DB) route
app.patch("/user/:id",(req,res)=>{
  let {id}=req.params;
  let {password: formPass, username: newUsername} = req.body;
  let q=`select * from user where id='${id}'`;

  try{
    connection.query(q,(err,result)=>{
      if(err) throw err;
      let user=result[0];
      if(formPass != user.password){
        res.send("Wrong Password!");
      } else {
        let q2 = `UPDATE user SET username='${newUsername}' WHERE id='${id}'`;
        connection.query(q2,(err,result)=>{
          if(err) throw err;
          res.redirect("/user");
        });
      }
    });
  } catch(err){
    console.log(err);
    res.send("some error in DB");
  }
});


 app.listen(8080, () => {
  console.log("server is listening to port 8080");
})

//Show Tables
// let q="SHOW TABLES";
//  try{
//     connection.query(q,(err,result)=>{
//      if(err) throw err;
//      console.log(result);
//      console.log(result.length);
//      console.log(result[0]);
//      console.log(result[1]);
//   })
//  } catch(err){
//    console.log(err);
//  }

//Insert new data
// let q = "INSERT INTO user(id,username,email,password) VALUES ?";
// let data = [];
// for(let i = 1; i <= 100; i++){
//   data.push(getUser());
// }

// try{
//   connection.query(q, [data], (err, result) => {
//     if(err) console.log("Insert Error:", err);
//     else console.log("Inserted", result.affectedRows, "rows");
//   });
// } catch(err){
//   console.log(err);
// }

// //connection.end();
//  app.get("/", (req, res) => {
//   let q = `SELECT COUNT(*) as total FROM user`;
//   try{
//     connection.query(q, (err, result) => {
//       if(err) {
//         console.log(err);
//         res.send("Error in DB");
//       } else {
//         res.send("Total users: " + result[0].total);
//       }
//     });
//   } catch(err){
//     console.log(err);
//     res.send("Error in DB");
//   }
// });

//  app.listen(8080, () => {
//   console.log("server is listening to port 8080");
// })