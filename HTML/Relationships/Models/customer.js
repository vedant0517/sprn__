const mongoose=require("mongoose");
const {Schema}=mongoose;

main()
.then(()=>console.log("connection successful"))
.catch(err => console.log(err));

async function main() {
  await mongoose.connect('mongodb://127.0.0.1:27017/relationDemo');
}


const orderSchema=new Schema({
    item:String,
    price:Number,
});

const customerSchema= new Schema({
    name:String,
    orders:[
        {
            type:Schema.Types.ObjectId,
            ref:"Order"
        }
    ],
});

const Order= mongoose.model("Order",orderSchema);
const Customer=mongoose.model("Customer",customerSchema);

const addCustomer = async () => {
    // let cust1=new Customer({
    //     name:"Vedant K",
    // });

    // let o1= await Order.findOne({item:"Icecream"});
    // let o2= await Order.findOne({item:"Samosa"});

    // cust1.orders.push(o1);
    // cust1.orders.push(o2);

    // let r=await cust1.save();
    // console.log(r);

    let r=await Customer.find({}).populate("orders");
    console.log(r[0]);

}
addCustomer();

// const addOrders= async()=>{
//     let res=await Order.insertMany([
//         {item:"Samosa", price:30},
//         {item:"Icecream",price:50},
//         {item:"Cold Drink",price:40}
//     ]);
//     console.log(res)
// };
// addOrders();