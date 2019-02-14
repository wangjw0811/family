/**
 项目JS主入口
 以依赖layui的layer和form模块为例
 **/
layui.define(['layer', 'form', 'element'], function (exports) {

    var layer = layui.layer
        , form = layui.form
        , element = layui.element
        , $ = layui.jquery;

    $("#loginBtn").on('click', function () {
        layer.open({
            title: "登录",
            type: 2,
            content: '/toLogin',
            area: ['400px', '600px'],
            success: function (layero, index) {
                window.closeToLogin = function () {
                    layer.close(index)
                    window.location.reload();
                }
            }
        })
    });
    exports('index', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
      