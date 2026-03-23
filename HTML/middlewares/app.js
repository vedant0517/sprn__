const express = require("express");
const app = express();

class AppError extends Error {
	constructor(status, message) {
		super(message);
		this.status = status;
	}
}

// Logs details for every incoming request.
app.use((req, res, next) => {
	req.time = new Date(Date.now());
	console.log(req.method, req.hostname, req.path, req.time);
	next();
});

const checkToken=(req,res,next)=>{
	let {token}=req.query;
	if(token == "giveaccess"){
		return next();
	}
	next(new AppError(401, "ACCESS DENIED!"));
};

app.use("/api",checkToken,(req,res)=>{
	res.send("data");
});

app.get("/err", (req, res, next) => {
	next(new AppError(500, "Manual test error"));
});

app.use("/random", (req, res, next) => {
	console.log("I am only for random");
	next();
});

app.get("/", (req, res) => {
	res.send("I am root");
});

app.get("/random", (req, res) => {
	res.send("I am random page");
});

app.get("/admin",(req,res)=>{
	throw new AppError(500,"Access Forbidden")
})

app.use((req, res, next) => {
	next(new AppError(404, "Page Not found!"));
});

app.use((err, req, res, next) => {
	const statusCode = err.status || 500;
	const message = err.message || "Internal Server Error";
	res.status(statusCode).send(message);
});

app.listen(8080, () => {
	console.log("server is running to port 8080");
});