
var oDiv = document.getElementById('qrcode');
var aSpan = oDiv.getElementsByTagName('span');
var contain = document.getElementsByClassName('edit-template')[0];
for (var i = 0; i < aSpan.length; i++) {
  dragFn(aSpan[i]);
}

function dragFn(obj) {
    obj.onmousedown = function (ev) {
        var oEv = ev || event;

        var oldWidth = oDiv.offsetWidth;
        var oldHeight = oDiv.offsetHeight;
        var oldX = oEv.clientX;
        var oldY = oEv.clientY;
        var oldLeft = oDiv.offsetLeft;
        var oldTop = oDiv.offsetTop;

        contain.onmousemove = function (ev) {
            var oEv = ev || event;

            if (obj.className == 'tl') {
                oDiv.style.width = oldWidth - (oEv.clientX - oldX) + 'px';
                oDiv.style.height=oldHeight-(oEv.clientY-oldY)+'px';
                oDiv.style.left = oldLeft + (oEv.clientX - oldX) + 'px';
                oDiv.style.top = oldTop + (oEv.clientY - oldY) + 'px';
            }
            else if (obj.className == 'bl') {
                oDiv.style.width = oldWidth - (oEv.clientX - oldX) + 'px';
                oDiv.style.height=oldHeight+(oEv.clientY-oldY)+'px';
                oDiv.style.left = oldLeft + (oEv.clientX - oldX) + 'px';
                oDiv.style.bottom = oldTop + (oEv.clientY + oldY) + 'px';
            }
            else if (obj.className == 'tr') {
                oDiv.style.width = oldWidth + (oEv.clientX - oldX) + 'px';
                oDiv.style.height = oldHeight - (oEv.clientY - oldY)+'px';
                oDiv.style.right = oldLeft - (oEv.clientX - oldX) + 'px';
                oDiv.style.top = oldTop + (oEv.clientY - oldY) + 'px';
            }
            else if (obj.className == 'br') {
                oDiv.style.width = oldWidth + (oEv.clientX - oldX) + 'px';
                oDiv.style.height = oldHeight + (oEv.clientY - oldY)+'px';
                oDiv.style.right = oldLeft - (oEv.clientX - oldX) + 'px';
                oDiv.style.bottom = oldTop + (oEv.clientY + oldY) + 'px';
            }
            return false;
        };

        contain.onmouseup = function () {
            contain.onmousemove = null;
        };
        return false;
    };
}
