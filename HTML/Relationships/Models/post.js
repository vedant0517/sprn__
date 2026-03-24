const mongoose=require("mongoose");
const {Schema}=mongoose;

main()
.then(()=>console.log("connection successful"))
.catch(err => console.log(err));

async function main() {
  await mongoose.connect('mongodb://127.0.0.1:27017/relationDemo');
}


const userSchema=new Schema({
    username:String,
    email:String,
});

const postSchema= new Schema({
    content:String,
    likes:Number,
    user:{
        type:Schema.Types.ObjectId,
        ref:"User"
    }
});

const User= mongoose.model("User",userSchema);
const Post=mongoose.model("Post",postSchema);

// const addData= async () => {
//     // let user1=new User({
//     //     username:"rahulkumar",
//     //     email:"rahul@gmail.com"
//     // });

//     // let post1=new Post({
//     //     content:'Hello World!',
//     //     likes:150,
//     // });
//     // post1.user=user1;

//     // await user1.save();
//     // await post1.save();

//     let user1=await User.findOne({username:"rahulkumar"});

//     let post2=new Post({
//         content:'fuck Off!!',
//         likes:1500,
//     });
//     post2.user=user1;

//     await post2.save();
// }
// addData();

const getData= async ()=>{
    let result=await Post.findOne({}).populate("user","username");
    console.log(result);
}

getData();