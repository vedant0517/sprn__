let todo = [];
let req = prompt("Please enter your request");

while(req !== "quit") {
    
    if(req === "list") {
        console.log("********** TO DO LIST **********");
        if(todo.length === 0) {
            console.log("No tasks in your list!");
        } else {
            for(let i = 0; i < todo.length; i++) {
                console.log(`${i}: ${todo[i]}`);
            }
        }
        console.log("*******************************");
    }
    
    else if(req === "add") {
        let newTask = prompt("Enter new task:");
        if(newTask) {
            todo.push(newTask);
            console.log(`"${newTask}" added to list!`);
        }
    }
    
    else if(req === "delete") {
        if(todo.length === 0) {
            console.log("No tasks to delete!");
        } else {
            console.log("Current tasks:");
            for(let i = 0; i < todo.length; i++) {
                console.log(`${i}: ${todo[i]}`);
            }
            let index = parseInt(prompt("Enter index of task to delete:"));
            if(index >= 0 && index < todo.length) {
                let removedTask = todo.splice(index, 1);
                console.log(`"${removedTask}" removed from list!`);
            } else {
                console.log("Invalid index!");
            }
        }
    }
    
    else {
        console.log("Invalid command! Please enter 'list', 'add', 'delete', or 'quit'");
    }
    
    req = prompt("Please enter your request");
}

console.log("Quitting app");
