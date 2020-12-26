var program =[{"id":99,"text":"-请选择-"},{"id":1,"text":"英语"},{"id":2,"text":"语文"},{"id":3,"text":"数学"}];
var province =[{"id":99,"text":"-请选择-"},{"id":37,"text":"山东"},{"id":10,"text":"北京"},{"id":20,"text":"河北"},{"id":30,"text":"广东"}];
$(function () {
    /*var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?76968355bd5f8c263c65a1ad6dca8fa8";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();*/
    $('#area').combobox({
        //data:province,
        url:'/map/provincelist',
        editable: false,
        valueField:'id',
        textField:'text'
    });
    $('#program').combobox({
        //data:program,
        url:'/map/programlist',
        editable: false,
        valueField:'id',
        textField:'text'
    });
    var map = new BMapGL.Map("container");
    // 创建地图实例
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    map.enablePinchToZoom(true);
    map.clearOverlays();
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
                // 创建点标记
                marker[i] = new BMapGL.Marker(point[i]);
                map.addOverlay(marker[i]);
                map.centerAndZoom(point[i], 5);
                // 创建信息窗口
                var opts = {
                    width: 225,
                    height: 170,
                    enableMessage:true//设置允许信息窗发送短息
                };
                var content = '<span style="font-weight: bold">第' +mapPointList[i].joinindex+'位加盟商</span>'
                    +'<br><span>加盟商名称：'+mapPointList[i].name
                    +'</span><span><br>签约项目：'+mapPointList[i].program
                    +'</span><span><br>签约省份：'+mapPointList[i].province
                    +'</span><span><br>联系方式：'+mapPointList[i].tel
                    +'</span><span><br>签约日期：'+mapPointList[i].date
                    +'</span><span><br>学校地址：'+mapPointList[i].address;
                    +'</span>'
                addClickHandler(content,marker[i]);//标记点绑定信息窗口

                function addClickHandler(content,marker) {
                    marker.addEventListener("click", function (e) {
                            openInfo(content, e)
                        }
                    );
                }
                function openInfo(content,e){
                    var p = e.target;
                    var point = new BMapGL.Point(p.getPosition().lng, p.getPosition().lat);
                    var infoWindow = new BMapGL.InfoWindow(content,opts);  // 创建信息窗口对象
                    map.openInfoWindow(infoWindow,point); //开启信息窗口
                }
            }
        }
    });
    $('#cx').linkbutton({
        onClick: function () {
            $.ajax({
                url: "/map/list?province="+$('#area').combobox('getValue')+"&program="+$('#program').combobox('getValue')
                    +'&name='+$('#name').textbox('getValue'),
                method: "post",
                success: function(data){
                    var mapPointList=data.rows;
                    var marker=new Array;
                    var point=new Array;
                    var infoWindow=new Array;
                    map.clearOverlays();
                    for(var i=0;i<mapPointList.length;i++){
                        var x= mapPointList[i].x;
                        var y= mapPointList[i].y;
                        point[i] = new BMapGL.Point(x,y);
                        // 创建点标记
                        marker[i] = new BMapGL.Marker(point[i]);
                        map.addOverlay(marker[i]);
                        map.centerAndZoom(point[i], 5);
                        // 创建信息窗口
                        var opts = {
                            width: 225,
                            height: 170,
                            enableMessage:true//设置允许信息窗发送短息
                        };
                        var content = '<span style="font-weight: bold">第' +mapPointList[i].joinindex+'位加盟商</span>'
                            +'<br><span>加盟商名称：'+mapPointList[i].name
                            +'</span><span><br>签约项目：'+mapPointList[i].program
                            +'</span><span><br>签约省份：'+mapPointList[i].province
                            +'</span><span><br>联系方式：'+mapPointList[i].tel
                            +'</span><span><br>签约日期：'+mapPointList[i].date
                            +'</span><span><br>学校地址：'+mapPointList[i].address;
                        +'</span>'
                        addClickHandler(content,marker[i]);//标记点绑定信息窗口

                        function addClickHandler(content,marker) {
                            marker.addEventListener("click", function (e) {
                                    openInfo(content, e)
                                }
                            );
                        }
                        function openInfo(content,e){
                            var p = e.target;
                            var point = new BMapGL.Point(p.getPosition().lng, p.getPosition().lat);
                            var infoWindow = new BMapGL.InfoWindow(content,opts);  // 创建信息窗口对象
                            map.openInfoWindow(infoWindow,point); //开启信息窗口
                        }
                    }
                }
            });
        }
    })
    $('#cz').linkbutton({
        onClick(){
            $('#area').combobox('setValue',null);
            $('#program').combobox('setValue',null);
            $('#name').textbox('clear');
        }
    });

})