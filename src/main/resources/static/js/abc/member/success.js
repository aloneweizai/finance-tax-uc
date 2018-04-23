require(["../../config"], function () {
    require(["jquery.full"], function ($) {
            var count=5;
            time();
            function time(){
                var times=document.getElementById("times");
                if (count == 0) {
                    times.innerHTML=count;
                    window.opener = null;//为了不出现提示框
                    window.close();//关闭窗口
                    return;
                } else {
                    times.innerHTML=count;
                    count--;
                }
                setTimeout(function() {
                        time() }
                    ,1000)
            }
    })
})