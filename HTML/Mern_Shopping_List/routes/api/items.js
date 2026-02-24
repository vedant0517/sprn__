const express=require("express");
const router=express.Router();
//Item Model
const Item=require("../../model/Item");

//@routes GET api/items
//@desc Get All Items
//@access Public

router.get("/",(req,res)=>{
    Item.find()
    .sort({date:-1})
    .then(items=>res.json(items));
})

//@routes POST api/items
//@desc Create a Post
//@access Public

router.post("/",(req,res)=>{
    const newItem=new Item({
        name:req.body.name
    });

    newItem.save().then(item =>res.json(item));
})


//@routes delete api/items/:id
//@desc delete
//@access Public

router.delete("/:id",(req,res)=>{
    Item.findByIdAndDelete(req.params.id)
    .then(() =>res.json({success:true}))
    .catch(err => res.status(404).json({success:false}));

})

module.exports=router;