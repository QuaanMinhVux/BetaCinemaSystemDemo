var btnSearch = document.querySelector(".search-btn")

btnSearch.addEventListener("click",function(){
    this.parentElement.classList.toggle("open")
    this.previousElementSibling.focus();
})

var email = document.getElementById("Email")
var password = document.getElementById("Password")
var btnSubmit = document.getElementById("btnSubmit")
var jsonText = document.getElementById("jsontext")


//add click event listener, to get data when data is entered
jsonBtn.addEventListener("click", function(){
    //store data in JavaScript object
    var data = {
        "firstName":firstname.value,
        "lastName":lastname.value
    }
    //convert JavaScript object to JSON
    jsonText.innerHTML = JSON.stringify(data)
})