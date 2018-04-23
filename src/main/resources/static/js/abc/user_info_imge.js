
require(["../config"], function () {
    require(["jquery.full","abc.ajaxfileupload","jquery.Jcrop"], function ($) {
        //console.log("1")
        var jcrop_api;
        $('#imgcj').Jcrop({
            onChange: updatePreview,
            onSelect: updatePreview,
            aspectRatio: 1,
            // allowResize: false,
            allowSelect:false
        },function(){
            jcrop_api = this;
            jcrop_api.animateTo([50, 50, 200, 100]);
        });

        function updatePreview(c){
            if (parseInt(c.w) > 0){
                $('#width').val(c.w);  //c.w 裁剪区域的宽
                $('#height').val(c.h); //c.h 裁剪区域的高
                $('#x').val(c.x);  //c.x 裁剪区域左上角顶点相对于图片左上角顶点的x坐标
                $('#y').val(c.y);  //c.y 裁剪区域顶点的y坐标</span>
            }
        };



        $('#xztp').click(function (){
            $('#filepath').click();
        });

        $('#filepath').change(function (){
            var $file = $(this);
            var fileObj = $file[0];
            var windowURL = window.URL || window.webkitURL;
            var dataURL;
            if (fileObj && fileObj.files && fileObj.files[0]) {
                dataURL = windowURL.createObjectURL(fileObj.files[0]);
                //$img.attr('src', dataURL);
                jcrop_api.setImage(dataURL,function (){
                    this.setOptions({
                        aspectRatio: 1,
                        // allowResize: false,
                        allowSelect:false
                    })
                    this.animateTo([50, 50, 200, 100]);
                });
            } else {
                $.ajaxFileUpload({
                    url: ctx+"/userinfo/base64upload.html?filename=filepath",
                    secureuri: false,
                    fileElementId: 'filepath',//file标签的id
                    dataType: 'json',
                    data: { },
                    success: function (data) {
                        if(typeof(data.errorCode)=='undefined') {
                            if (typeof(data.soacode) == "undefined") {
                                //$('#zmbase64').val(data.base64);
                                $('#imgcj').attr("src","data:image/png;base64,"+data.base64)
                            } else {
                                abc.msg(res.message,{icon:2});
                            }
                        }else{
                            eval(res.js)
                        }
                    }
                });
            }
        });



        $('#sctx').click(function (){
            var file=$('#filepath').val();
            if(file==''){
                abc.msg("请选择头像图片!",{icon: 2});
                return ;
            }
            parent.$.pop();
            $.ajax({
                url: ctx+"/userinfo/uploadtx.html",
                type: 'POST',
                cache: false,
                data: new FormData($('#tx')[0]),
                processData: false,
                contentType: false
            }).done(function(res) {
                parent.$.close();
                if(typeof(res.errorCode)=='undefined') {
                    if (typeof(res.soacode) == "undefined") {
                        if(res.data.code=='2000'){
                            abc.msg("头像上传成功!",{icon: 1},function (){
                                if(typeof window.top.loc ==='undefined'){
                                    window.parent.location.href = ctx + "/index.html";
                                }else{
                                    window.parent.location.href = ctx + "/external_index.html";
                                }
                            });
                        }else{
                            abc.msg(res.data.message,{icon: 2});
                        }
                    } else {
                        abc.msg(res.data.message,{icon: 2});
                    }
                }else{
                    eval(res.js)
                }
            }).fail(function(res) {});
        });
    })
})