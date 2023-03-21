var btnSearch = document.querySelector(".search-btn")

btnSearch.addEventListener("click",function(){
    this.parentElement.classList.toggle("open")
    this.previousElementSibling.focus();
})

// var sourceWidth = document.getElementsByClassName("ddl-profile").offsetWidth;

var width = document.getElementsByClassName("ddl-profile-content").style.width ="300px";

console.log(width)


document.querySelector(".form-login").addEventListener("submit" , function(event){
    var password = document.getElementsByName("txtPass").values;
    var email = document.getElementsByName("txtEmail").values;
    var check = 0;
    if(password.length < 8){
        document.getElementById("pass-error").innerHTML = "Password must be at least 8 characters long";
        check++;
    }
    if(email.length < 8){
        document.getElementById("email-error").innerHTML = "Email must be at least 8 characters long";
        check++;
    }

    if(check >0){
        event.preventDefault();
    }

})