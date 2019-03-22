document.getElementById('rect').addEventListener('click', printPosition)

document.getElementById('relative-rect').addEventListener('click', printPosition)

function getPosition(e) {
    var rect = e.target.getBoundingClientRect();
    var x = e.clientX - rect.left;
    var y = e.clientY - rect.top;
    return {
        x,
        y
    }
}

function printPosition(e) {
    var pos = getPosition(e);
    console.log("logClick x:" + pos.x);
    console.log("logClick y:" + pos.y);

}
