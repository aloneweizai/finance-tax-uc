jQuery.extend({
    pop:function (){
        $.blockUI({message:'<img src="'+ctx+'/images/loading.gif" >',css:{zIndex:10000000}});
    },
    close:function (){
        $.unblockUI();
    },
    showLogin:function (){
        $('#modalLogin')[0].reset();
        $('#loginModal').modal({backdrop: 'static', keyboard: false});
    },
    hideLogin:function (){
        $('#loginModal').modal('hide');
    },
    iframeHeight:function (){
        var iframe = parent.document.getElementById("external-frame");
        if(typeof(iframe)!="undefined"&&iframe!=null){
            try{
                if (iframe.attachEvent) {
                    iframe.height=960;
                    var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
                    var heights=iframeWin.document.documentElement.scrollHeight+30 || iframeWin.document.body.scrollHeight+30;
                    //console.log("高度:"+heights);
                    iframe.height = heights>960?heights:960;
                } else {
                    iframe.height=960;
                    var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
                    var heights=iframeWin.document.documentElement.scrollHeight+30 || iframeWin.document.body.scrollHeight+30;
                    //console.log("高度:"+heights);
                    iframe.height = heights>960?heights:960;
                }
            }catch (e){
            }
        }
    },
    loadContent:function (op){
        $.pop();
        $.ajax({
            url: op,
            type: "get",
            dataType: 'html',
            success: function(re) {
                $('#right_conten').html(re);
                $.close();
            }
        });
    }
})