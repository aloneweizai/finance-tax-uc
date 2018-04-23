require(["../config"],function($){
    require(["jquery.full"],function($) {
        $("#confirmBtn").click(function(){
           var id = $(this).attr("confirm-id");
            $.ajax({
                type:"POST",
                url:ctx+"/orderback/receive/{id}",
                data:id,
                async:false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    if(typeof(data.errorCode)=='undefined') {
                        if (typeof(data.soacode) == "undefined") {
                            if (data.data.code == '2000') {
                                abc.msg('确认收货成功',{icon:1},function (){
                                    window.location.href = ctx + "/orderback/back.php";
                                });
                            } else {
                                abc.alert(data.data.message,{icon: 2});
                            }
                        } else {
                            abc.msg(data.message,{icon: 2});
                        }
                    }else{
                        eval(data.js)
                    }
                }
            })
        });
    })
})