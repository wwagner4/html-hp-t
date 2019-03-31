document.getElementById('back').addEventListener('click', printPosition);
var slider1 = document.getElementById('slider1');

function hideSlider() {
    slider1.style.visibility = 'hidden'
}

function printPosition(e) {
    slider1.style.visibility = 'visible';
    var elem = e.target;
    var rect = elem.getBoundingClientRect();
    var pos = {x : e.clientX - rect.left, y : e.clientY - rect.top};
    var elemDim = {w : elem.offsetWidth, h: elem.offsetHeight};
    console.log("pos:" + pos.x + " " + pos.y);
    console.log("elemDim:" + elemDim.w + " " + elemDim.h);
}

function ratio(dim) {
    return dim.h / dim.w;
}

function back() {
    console.log("BACK")
}
function left() {
    console.log("LEFT")
}
function right() {
    console.log("RIGHT")
}
