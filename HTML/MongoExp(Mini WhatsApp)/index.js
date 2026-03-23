const express=require("express");
const app=express();
const mongoose=require("mongoose");
const path=require("path");
const Chat=require("./models/chat.js");
const methodOverride=require("method-override");

class ExpressError extends Error {
    constructor(statusCode, message) {
        super(message);
        this.statusCode=statusCode;
    }
}

// Wrap async route handlers so rejected promises go to Express error middleware.
const wrapAsync=(fn)=>{
    return (req,res,next)=>{
        fn(req,res,next).catch(next);
    };
};

app.set("views",path.join(__dirname,"views"));
app.set("view engine","ejs");
app.use(express.static(path.join(__dirname,"public")));
app.use(express.urlencoded({extended:true}));
app.use(methodOverride("_method"));

main()
.then(()=>{console.log("connection successful")
})
.catch(err => {
    console.log("Database connection failed", err);
    process.exit(1);
});

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
app.get("/chats",wrapAsync(async(req,res)=>{
    let chats=await Chat.find();
    res.render("index.ejs",{chats});
}));

//Show Route
app.get("/chats/:id",wrapAsync(async(req,res)=>{
    let{id}=req.params;
    if(!mongoose.isValidObjectId(id)){
        throw new ExpressError(400,"Invalid chat id");
    }
    let chat=await Chat.findById(id);
    if(!chat){
        throw new ExpressError(404,"Chat not found");
    }
    res.render("show.ejs",{chat});
}));

//New Route
app.get("/chats/new",(req,res)=>{
    res.render("new.ejs");
})

//create route

app.post("/chats",wrapAsync(async(req,res)=>{
    let {from, to, msg}=req.body;
    if(!from || !to || !msg){
        throw new ExpressError(400,"from, to and msg are required");
    }
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
    await newChat.save();
    res.redirect("/chats");
}));

//Edit ROute
app.get("/chats/:id/edit",wrapAsync(async(req,res)=>{
    let{id}=req.params;
    if(!mongoose.isValidObjectId(id)){
        throw new ExpressError(400,"Invalid chat id");
    }
    let chat=await Chat.findById(id);
    if(!chat){
        throw new ExpressError(404,"Chat not found");
    }
    res.render("edit.ejs",{chat});
}));

//Update Route
app.put("/chats/:id",wrapAsync(async(req,res)=>{
    let{id}=req.params;
    let {msg:newMsg}=req.body;
    if(!mongoose.isValidObjectId(id)){
        throw new ExpressError(400,"Invalid chat id");
    }
    if(!newMsg){
        throw new ExpressError(400,"msg is required");
    }
    let updatedChat=await Chat.findByIdAndUpdate(
        id,
        {msg:newMsg,updated_at:new Date()},{runValidators:true,returnDocument:"after"}
    );
    if(!updatedChat){
        throw new ExpressError(404,"Chat not found");
    }
    res.redirect("/chats");
}));

//Delete Route
app.delete("/chats/:id",wrapAsync(async(req,res)=>{
    let {id}=req.params;
    if(!mongoose.isValidObjectId(id)){
        throw new ExpressError(400,"Invalid chat id");
    }
    let deletedChat=await Chat.findByIdAndDelete(id);
    if(!deletedChat){
        throw new ExpressError(404,"Chat not found");
    }
    res.redirect("/chats");
}));

app.get("/",(req,res)=>{
    res.send("root is working");
})

// Catch all unmatched routes and forward a 404 error.
app.use((req,res,next)=>{
    next(new ExpressError(404,"Page not found"));
});

// Centralized error handler for all thrown/forwarded errors.
app.use((err,req,res,next)=>{
    let {statusCode=500,message="Internal Server Error"}=err;
    res.status(statusCode).send(message);
});

app.listen(8080,()=>{
    console.log("Server is listening on port 8080");
})