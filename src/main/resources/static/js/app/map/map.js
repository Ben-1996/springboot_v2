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
    $.ajax({
        url: "/map/list",
        method: "post",
        success: function(data){
            var mapPointList=data.rows;
            var marker=new Array;
            var point=new Array;
            var infoWindow=new Array;
            for(var i=0;i<mapPointList.length;i++){
                var x= mapPointList[i].x;
                var y= mapPointList[i].y;
                point[i] = new BMapGL.Point(x,y);
                map.centerAndZoom(point[i], 15);
                // 创建点标记
                marker[i] = new BMapGL.Marker(point[i]);
                map.addOverlay(marker[i]);
                // 创建信息窗口
                var opts = {
                    width: 200,
                    height: 100,
                    title: mapPointList[i].title
                };
                infoWindow[i] = new BMapGL.InfoWindow('地址：'+mapPointList[i].addr, opts);
                // 点标记添加点击事件
                marker[i].addEventListener('click', function () {
                    map.openInfoWindow(infoWindow[i], point[i]); // 开启信息窗口
                });
            }
        }});



})