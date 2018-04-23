/**
 * 数组转json
 * Created by liuqi on 2017/5/22.
 */
(function($){
    $.extend({
        arrayToJson:function (formArray) {
            var dataArray = {};
            $.each(formArray,function(){
                if(dataArray[this.name]){
                    if(!dataArray[this.name].push){
                        dataArray[this.name] = [dataArray[this.name]];
                    }
                    dataArray[this.name].push(this.value || '');
                }else{
                    dataArray[this.name] = this.value || '';
                }
            });
            return JSON.stringify(dataArray);
        }
    });
})(jQuery);