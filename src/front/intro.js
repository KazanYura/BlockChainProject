function send() {
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/users/validate";
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let json = JSON.parse(xhr.responseText);
            localStorage.setItem("userId",json.id);
            localStorage.setItem("username",json.firstName);
            localStorage.setItem("pass",json.password);
            localStorage.setItem("balance",json.balance);
            window.location.href = "search.html";
        }
    };
    let data = JSON.stringify({"firstName": document.getElementById("firstName").value,
                                     "password": document.getElementById("pass").value});
    xhr.send(data);
}

function find_game() {
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/games/search";
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let json = JSON.parse(xhr.responseText);
            localStorage.setItem("hash",json.hash);
            window.location.href = "index.html";
        }
    };
    let data = JSON.stringify({"firstName": localStorage.getItem("username"),
        "password": localStorage.getItem("pass")});
    console.log(data);
    xhr.send(data);
}
function set_name() {
    document.getElementById("name").innerText = localStorage.getItem("username");
    document.getElementById("balance").innerText = localStorage.getItem("balance");
}
function make_bet() {
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/games/makebet";
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let json = JSON.parse(xhr.responseText);
            console.log("Bet was done!");

            for (let i = 0; i < json.users.length; i++) {
                console.log(i);
                if (localStorage.getItem("username") === json.users[i].firstName){
                    localStorage.setItem("balance",json.users[i].balance);
                }
            }
            set_name();
        }
    };
    let data = JSON.stringify({
        "userId":localStorage.getItem("username"),
        "pass":localStorage.getItem("pass"),
        "gameHash":localStorage.getItem("hash"),
        "bet":parseFloat(document.getElementById("bet").value) ,
        "number":parseInt(document.getElementById("number").value)
    });
    console.log(data);
    xhr.send(data);
}
function get_result() {
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/games/roll";
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let json = JSON.parse(xhr.responseText);
            document.getElementById("result").innerText = json.gameRes;
        }
    };
    let data = JSON.stringify({
        "userId":localStorage.getItem("username"),
        "pass":localStorage.getItem("pass"),
        "gameHash":localStorage.getItem("hash"),
        "bet":parseFloat(document.getElementById("bet").value) ,
        "number":parseInt(document.getElementById("number").value)
    });
    console.log(data);
    xhr.send(data);
}