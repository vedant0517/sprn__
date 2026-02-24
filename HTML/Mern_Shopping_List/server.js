const express=require("express");
const mongoose=require("mongoose");
const bodyParser=require("body-parser");

const items=require("./routes/api/items");

const app=express();


//bodyParser
app.use(bodyParser.json());

//db congig
const db=require("./config/key").mongoURL;

//connect to Mongo
mongoose.connect(db)
  .then(() => console.log('MongoDB Connected...'))
  .catch(err => console.log('MongoDB connection error:', err));

  //Use routes
  app.use("/api/items",items)

//Server Port
const PORT = process.env.PORT || 5000;

app.listen(PORT, () => console.log(`Server started on port ${PORT}`));