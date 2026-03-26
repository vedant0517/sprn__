const mongoose=require("mongoose");
const {Schema}=mongoose;

main()
.then(()=>console.log("connection successful"))
.catch(err => console.log(err));

async function main() {
  await mongoose.connect('mongodb://127.0.0.1:27017/relationDemo');
}


const reviewSchema=new Schema({
    comment:String,
    rating:Number,
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