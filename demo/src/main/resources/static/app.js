var btnSearch = document.querySelector(".search-btn")

btnSearch.addEventListener("click",function(){
    this.parentElement.classList.toggle("open")
    this.previousElementSibling.focus();
})



//Send data form textbox-search
var textBoxValue = document.getElementsByName("txtSearch").value;
var xhr = new XMLHttpRequest();
xhr.open("POST", "MyServlet", true);
xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
xhr.onreadystatechange = function() {
  if (xhr.readyState === 4 && xhr.status === 200) {
    console.log(xhr.responseText);
  }
};
xhr.send("textSearch=" + textBoxValue);