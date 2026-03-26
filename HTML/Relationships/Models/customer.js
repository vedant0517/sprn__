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

// customerSchema.pre("findOneAndDelete",async()=>{
//     console.log("Pre Middleware")
// })

customerSchema.post("findOneAndDelete", async (customer) => {
    if (customer && customer.orders && customer.orders.length > 0) {
        let res = await Order.deleteMany({ _id: { $in: customer.orders } });
        console.log(res);
    }
});

const Order= mongoose.model("Order",orderSchema);
const Customer=mongoose.model("Customer",customerSchema);

const addCustomer = async () => {
    let cust=new Customer({
        name:"AnuXaka",
    });

    let newOrder=new Order({
        item:"Burger",
        price:250,
    })

    cust.orders.push(newOrder);

   

    await newOrder.save();
    await cust.save();

   console.log("new customer added");

}

const delCust=async()=>{
    let data=await Customer.findByIdAndDelete("69c57f51643dd1adfefa1be7");
    console.log(data);

}
// addCustomer();
delCust();

// const addOrders= async()=>{
//     let res=await Order.insertMany([
//         {item:"Samosa", price:30},
//         {item:"Icecream",price:50},
//         {item:"Cold Drink",price:40}
//     ]);
//     console.log(res)
// };
// addOrders();