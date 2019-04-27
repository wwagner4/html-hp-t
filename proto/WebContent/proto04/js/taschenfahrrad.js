document.getElementById('back')
    .addEventListener('click', showSlider);

var slider1 = document.getElementById('slider1');

function hideSlider() {
    slider1.style.visibility = 'hidden'
}

function showSlider(e) {
    slider1.style.visibility = 'visible';
    var elem = e.target;
    var rect = elem.getBoundingClientRect();
    var pos = {x : e.clientX - rect.left, y : e.clientY - rect.top};
    var elemDim = {w : elem.offsetWidth, h: elem.offsetHeight};
    console.log("pos:" + pos.x + " " + pos.y);
    console.log("elemDim:" + elemDim.w + " " + elemDim.h);

    var index = indexFromPos(tiles_length, tiles_cols, tiles_tileSize, pos);
    console.log("            length: " + tiles_length + ",");
    console.log("            cols: " + tiles_cols + ",");
    console.log("            pos: " + JSON.stringify(pos) + ",");
    console.log("            should: " + "?" + ",");


    console.log("index: " + index);

    var goSym = "=" + index;
    console.log("going to: " + goSym);
    glide.go(goSym);

}


function indexFromPos(length, cols, tileSize, pos) {
    var rows = Math.ceil(length / cols);
    var h = tileSize * rows;
    var w = tileSize * cols;
    var posx = pos.x % w;
    var posy = pos.y % h;
    var x = Math.floor(posx / tileSize);
    var y = Math.floor(posy / tileSize);
    var n = y * cols + x;
    return n % length;
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
