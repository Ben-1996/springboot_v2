$(function () {
    /*var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?76968355bd5f8c263c65a1ad6dca8fa8";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();*/
    var map = new BMapGL.Map("container");
    // 创建地图实例
    var point = new BMapGL.Point(116.404, 39.915);
    // 创建点坐标
    map.centerAndZoom(point, 15);
    // 初始化地图，设置中心点坐标和地图级别
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

    var pointlist={"code":200,"data":{"rows":[
                {xy:"116.404,39.015",title:"测试a",addr:"北京市王府井大街",province:"北京"},{xy:"116.900,39.015",title:"测试a",addr:"北京市王府井大街",province:"北京"},
            ]}}
    for(i=0;i<pointlist.data.rows.length;i++){
        var point = new BMapGL.Point(pointlist.data.rows[i].xy);
        map.centerAndZoom(point, 15);
        // 创建点标记
        var marker = new BMapGL.Marker(point);
        map.addOverlay(marker);
        // 创建信息窗口
        var opts = {
            width: 200,
            height: 100,
            title: pointlist.data.rows[i].title
        };
        var infoWindow = new BMapGL.InfoWindow('地址：'+pointlist.data.rows[i].addr, opts);
        // 点标记添加点击事件
        marker.addEventListener('click', function () {
            map.openInfoWindow(infoWindow, point); // 开启信息窗口
        });
    }

})