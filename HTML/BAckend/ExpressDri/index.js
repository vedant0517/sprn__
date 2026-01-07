const express = require("express");
const app = express();

console.dir(app);

let port=3000; //8080
app.listen(port,()=>{
    console.log(`App is listening on port ${port}`)
});


// app.use((req,res)=>{
//     console.log("request received");
//     res.send({
//         name:"apple",color:"red"
//     });
// });

app.get("/",(req,res)=> {
    res.send("Hello  I am root")
});

app.get("/apple",(req,res)=> {
    res.send("Apple Path")
});
app.get("/banana",(req,res)=> {
    res.send("banana Path")
});