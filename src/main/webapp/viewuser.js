'use strict';

let info = document.getElementById("userinfo");

fetch("/project1/login")
    .then((response) => {
    	console.log(response);
    	return response.json();
    	})
    .then((obj) => {
    	console.log(obj);
        for(let x in obj) {
            let li = document.createElement("li");
            li.innerText = `${x} : ${obj[x]}`;
            
            info.appendChild(li);
        }
    })
    .catch((err) => {
        console.log(err);
    });