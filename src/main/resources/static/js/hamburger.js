function getActive(){
    document.getElementById("toggle").className = "dactive";
    document.getElementById("menu").className = "visible";
    document.getElementsByClassName("dactive")[0].onclick = getToggle;
}
function getToggle(){
    document.getElementById("toggle").className = "toggle";
    document.getElementById("menu").className = "menu";
    document.getElementsByClassName("toggle")[0].onclick = getActive;
}