'use strict';

let tbody=document.getElementById("tbody")

fetch("/project1/viewEmpPending.html").then((Response) => {return Response.json()})
.then ((obj) =>{
    for (let x in obj){
        let form = obj[x];

        let tr = document.createElement("tr");
        let td = document.createElement("td");
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");

        var text = document.createTextNode(form.type);
        var text1 = document.createTextNode(form.status);
        var text2 = document.createTextNode(form.amount);
        var text3 = document.createTextNode("variable name");

        td.append(text);
        td1.append(text1);
        td2.append(text2);
        td3.append(text3);

        tr.appendChild(td);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);

        tbody.appendChild(tr);

        console.log(tr);
    }
})
.catch((err) => {
    console.log(err);
});