//Tab选项卡
layui.use('element', function(){
var element = layui.element
    , $ = layui.jquery
;
//触发事件
var active = {
    tabAdd: function(url,id,name){
        //新增一个Tab项
        element.tabAdd('tab', {
            title: name//'新选项'+ (Math.random()*1000|0) //用于演示
            ,content: '内容'+ (Math.random()*1000|0)
            ,id: id//new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
        })
    }
    ,tabDelete: function(othis){
        //删除指定Tab项
        element.tabDelete('demo', '44'); //删除：“商品管理”
        othis.addClass('layui-btn-disabled');
    }
    ,tabChange: function(){
        //切换到指定Tab项
        element.tabChange('demo', '22'); //切换到：用户管理
    }
};
$('.site-demo-active').on('click', function(){
    var othis = $(this), type = othis.data('type');
    active[type] ? active[type].call(this, othis) : '';
});

//Hash地址的定位
var layid = location.hash.replace(/^#test=/, '');
element.tabChange('test', layid);

element.on('tab(test)', function(elem){
    location.hash = 'test='+ $(this).attr('lay-id');
});

});