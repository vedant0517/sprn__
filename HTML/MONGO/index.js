const mongoose = require('mongoose');

main()
  .then((res)=>{
    console.log("Connecetion Success");
  })
  .catch(err => console.log(err));

async function main() {
  await mongoose.connect('mongodb://127.0.0.1:27017/test');
}

const userSchema=new mongoose.Schema({
    name:String,
    email:String,
    age:Number
});

const User=mongoose.model("User",userSchema);

// const user2=new User({name:"ved",email:"ved@gmail.com",age:18});

// user2.save().then((res)=>{
//     console.log(res);
// })
// .catch((err)=>{
//     console.log(err)
// });

// User.insertMany([
//     {name:"ved",email:"ved@gmail.com",age:18},
//     {name:"ved",email:"ved@gmail.com",age:18},
//     {name:"ved",email:"ved@gmail.com",age:18}
// ]).then((res)=>{
//     console.log(res);
// });

// User.find({age:{$gte:18}}).then((res)=>{
//     console.log(res);
// })
// .catch((err)=>{
//     console.log(err);
// })

// User.updateOne({age:{$gte:18}},{name:"vedant"}).then((res)=>{
//     console.log(res);
// })
// .catch((err)=>{
//     console.log(err);
// })

User.findOneAndUpdate({age:{$gte:18}},{name:"xyz"},{new:true}).then((res)=>{
    console.log(res);
})
.catch((err)=>{
    console.log(err);
})


