document.getElementById('closeb')
    .addEventListener('click', closeb);

var tfslider = document.getElementById('tfslider');

function hideSlider() {
    tfslider.style.visibility = 'hidden'
}

function closeb() {
    document.getElementById('back').style.display = 'block';
}

function showS(index) { // MouseEvent
    tfslider.style.visibility = 'visible';
    document.getElementById('back').style.display = 'none';
    var goSym = "=" + index;
    glide.go(goSym);
}

