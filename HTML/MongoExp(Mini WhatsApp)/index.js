const express=require("express");
const app=express();
const mongoose=require("mongoose");
const path=require("path");
const Chat=require("./models/chat.js");
const methodOverride=require("method-override");

app.set("views",path.join(__dirname,"views"));
app.set("view engine","ejs");
app.use(express.static(path.join(__dirname,"public")));
app.use(express.urlencoded({extended:true}));
app.use(methodOverride("_method"));

main()
.then(()=>{console.log("connection successful")
})
.catch(err => console.log(err));

async function main() {
  await mongoose.connect('mongodb://127.0.0.1:27017/whatsapp'); 
}

// let chat1=new Chat({
//     from:"vedant",
//     to:"namish",
//     msg:"I am learning mongoDb express.",
//     created_at: new Date()
// })

// chat1.save().then((res)=>
//     {console.log(res)})

//New Route
app.get("/chats",async(req,res)=>{
    let chats=await Chat.find();
    //console.log(chats);
    res.render("index.ejs",{chats});
})

//New Route
app.get("/chats/new",(req,res)=>{
    res.render("new.ejs");
})

//create route

app.post("/chats",(req,res)=>{
    let {from, to, msg}=req.body;
    const now=new Date();
    let newChat=new Chat(
        {
            from:from,
            to:to,
            msg:msg,
            created_at:now,
            updated_at:now
        }
    );
    newChat.save()
    .then(res=>{
        console.log("Chat was saved")
    })
    .catch(err=>{
        console.log(err)
    });
    res.redirect("/chats");
})

//Edit ROute
app.get("/chats/:id/edit",async(req,res)=>{
    let{id}=req.params;
    let chat=await Chat.findById(id);
    res.render("edit.ejs",{chat});
})

//Update Route
app.put("/chats/:id",async(req,res)=>{
    let{id}=req.params;
    let {msg:newMsg}=req.body;
    let updatedChat=await Chat.findByIdAndUpdate(
        id,
        {msg:newMsg,updated_at:new Date()},{runValidators:true,returnDocument:"after"}
    );
    console.log(updatedChat);
    res.redirect("/chats");
});

//Delete Route
app.delete("/chats/:id",async(req,res)=>{
    let {id}=req.params;
    let deletedChat=await Chat.findByIdAndDelete(id);
    console.log(deletedChat);
    res.redirect("/chats");
})

app.get("/",(req,res)=>{
    res.send("root is working");
})

app.listen(8080,()=>{
    console.log("Server is listening on port 8080");
})