const mongoose = require('mongoose');

main()
  .then((res)=>{
    console.log("Connecetion Success");
  })
  .catch(err => console.log(err));

async function main() {
  await mongoose.connect('mongodb://127.0.0.1:27017/amazon');
}

const bookSchema=new mongoose.Schema({
    title:{
        type:String,
        required:true
    },
    author:{
        type:String,
    },
    price:{
        type:Number
    }
});

const Book=mongoose.model("Book",bookSchema);

let book2=new Book({
    author:"RD Sharma",
    price:1200
})

book2
    .save()
    .then((res)=>{
        console.log(res);
    })
    .catch((err)=>{
        console.log(err);
    })