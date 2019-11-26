document.getElementById('closeb')
    .addEventListener('click', closeb);

var tfslider = document.getElementById('tfslider');

function hideSlider() {
    tfslider.style.visibility = 'hidden'
}

function closeb() {
    document.getElementById('back').style.display = 'block';
}

function showSlider(e) { // MouseEvent
    var elem = e.target;
    var elem_width = elem.clientWidth;
    var rect = elem.getBoundingClientRect();
    var pos = {x: e.clientX - rect.left, y: e.clientY - rect.top};
    tfslider.style.visibility = 'visible';
    document.getElementById('back').style.display = 'none';
    var tiles_tileSize1 = elem_width / tiles_cols;
    var index = indexFromPos(tiles_length, tiles_cols, tiles_tileSize1, pos);
    var goSym = "=" + index;
    glide.go(goSym);
}

function showS(index) { // MouseEvent
    tfslider.style.visibility = 'visible';
    document.getElementById('back').style.display = 'none';
    var goSym = "=" + index;
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
