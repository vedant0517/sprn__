const mongoose=require("mongoose");
const Chat=require("./models/chat.js");

main()
.then(()=>{console.log("connection successful")
})
.catch(err => console.log(err));

async function main() {
  await mongoose.connect('mongodb://127.0.0.1:27017/whatsapp'); 
}


let allChats=[
    {
    from:"piyush",
    to:"apurva",
    msg:"Hi How are you doing?",
    created_at: new Date()
    },
    {
    from:"harsh",
    to:"akan",
    msg:"Send me notes!!",
    created_at: new Date()
    },
     {
    from:"neha",
    to:"ritu",
    msg:"I am not attending lec today.",
    created_at: new Date()
    },
     {
    from:"parv",
    to:"amisha",
    msg:"Send me xyz number.",
    created_at: new Date()
    },
];

Chat.insertMany(allChats);
