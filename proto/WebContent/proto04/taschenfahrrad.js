document.getElementById('img1')
    .addEventListener('click', showSlider);

function showSlider(e) {
    var imgDim = imgDims[imgIdx];
    var elem = e.target;
    var rect = elem.getBoundingClientRect();
    var pos = {x : e.clientX - rect.left, y : e.clientY - rect.top};
    var elemDim = {w : elem.offsetWidth, h: elem.offsetHeight};
    console.log("pos:" + pos.x + " " + pos.y);
    console.log("elemDim:" + elemDim.w + " " + elemDim.h);
    console.log("imgDim:" + imgDim.w + " " + imgDim.h);

    var elemRatio = ratio(elemDim);
    var imgRatio = ratio(imgDim);

    console.log("ratios img:" + imgRatio + " elem:" + elemRatio);


    if (elemRatio < imgRatio) {
        console.log("border L R");
        var imgw = elemDim.h / imgRatio;
        var xoff = (elemDim.w - imgw) / 2.0;
        if (pos.x < xoff || pos.x > imgw + xoff) {
            back();
        } else if (pos.x > xoff && pos.x < xoff + imgw / 2.0) {
            left();
        } else {
            right();
        }
    } else {
        console.log("border T B");
        var imgh = elemDim.w * imgRatio;
        var yoff = (elemDim.h - imgh) / 2.0;
        if (pos.y < yoff || pos.y > imgh + yoff) {
            back();
        } else if (pos.x < elemDim.w / 2.0) {
            left();
        } else {
            right();
        }
    }
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
