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
    var point = new BMapGL.Point(105.65630, 35.32886);
    map.centerAndZoom(point, 6);
    $.ajax({
        url: "/map/list",
        method: "post",
        success: function(data){
            var mapPointList=data.rows;
            var marker=new Array;
            var point=new Array;
            var color = new Array;
            var infoWindow=new Array;
            for(var i=0;i<mapPointList.length;i++){
                var x= mapPointList[i].x;
                var y= mapPointList[i].y;
                point[i] = new BMapGL.Point(x,y);
                // var greenicon = new BMapGL.icon();
                // 创建点标记
                color[i] = mapPointList[i].color;
                console.log(color[i]);
                if(color[i] == 'green'){
                    var myIcon = new BMapGL.Icon("static/map/img/maker_green.png", new BMapGL.Size(43, 45), {
                        // 指定定位位置。
                        // 当标注显示在地图上时，其所指向的地理位置距离图标左上
                        // 角各偏移10像素和25像素。您可以看到在本例中该位置即是
                        // 图标中央下端的尖角位置。
                        //anchor: new BMapGL.Size(10, 25),
                        // 设置图片偏移。
                        // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您
                        // 需要指定大图的偏移位置，此做法与css sprites技术类似。
                        //imageOffset: new BMapGL.Size(0, 0 - 25)   // 设置图片偏移
                    });
                    marker[i] = new BMapGL.Marker(point[i],{icon: myIcon});
                }else if(color[i] == 'black'){
                    var myIcon = new BMapGL.Icon("static/map/img/maker_black.png", new BMapGL.Size(43, 45), {
                        // 指定定位位置。
                        // 当标注显示在地图上时，其所指向的地理位置距离图标左上
                        // 角各偏移10像素和25像素。您可以看到在本例中该位置即是
                        // 图标中央下端的尖角位置。
                        //anchor: new BMapGL.Size(10, 25),
                        // 设置图片偏移。
                        // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您
                        // 需要指定大图的偏移位置，此做法与css sprites技术类似。
                        //imageOffset: new BMapGL.Size(0, 0 - 25)   // 设置图片偏移
                    });
                    marker[i] = new BMapGL.Marker(point[i], {icon: myIcon});
                }else if(color[i] == 'blue'){

                    var myIcon = new BMapGL.Icon("static/map/img/maker_blue.png", new BMapGL.Size(43, 45), {
                        // 指定定位位置。
                        // 当标注显示在地图上时，其所指向的地理位置距离图标左上
                        // 角各偏移10像素和25像素。您可以看到在本例中该位置即是
                        // 图标中央下端的尖角位置。
                        //anchor: new BMapGL.Size(10, 25),
                        // 设置图片偏移。
                        // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您
                        // 需要指定大图的偏移位置，此做法与css sprites技术类似。
                        //imageOffset: new BMapGL.Size(0, 0 - 25)   // 设置图片偏移
                    });
                    marker[i] = new BMapGL.Marker(point[i],{icon:myIcon});
                    // marker[i].setIcon(myIcon);
                }else if(color[i] == 'yellow'){
                    console.log("123124515");
                    var myIcon = new BMapGL.Icon("static/map/img/maker_yellow.png", new BMapGL.Size(43, 45), {
                        // 指定定位位置。
                        // 当标注显示在地图上时，其所指向的地理位置距离图标左上
                        // 角各偏移10像素和25像素。您可以看到在本例中该位置即是
                        // 图标中央下端的尖角位置。
                        //anchor: new BMapGL.Size(10, 25),
                        // 设置图片偏移。
                        // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您
                        // 需要指定大图的偏移位置，此做法与css sprites技术类似。
                        //imageOffset: new BMapGL.Size(0, 0 - 25)   // 设置图片偏移
                    });
                    marker[i] = new BMapGL.Marker(point[i], {icon: myIcon});
                }else{
                    var myIcon = new BMapGL.Icon("static/map/img/maker_red.png", new BMapGL.Size(43, 45), {
                        // 指定定位位置。
                        // 当标注显示在地图上时，其所指向的地理位置距离图标左上
                        // 角各偏移10像素和25像素。您可以看到在本例中该位置即是
                        // 图标中央下端的尖角位置。
                        // anchor: new BMapGL.Size(10, 25),
                        // 设置图片偏移。
                        // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您
                        // 需要指定大图的偏移位置，此做法与css sprites技术类似。
                        // imageOffset: new BMapGL.Size(0, 0 - 25)   // 设置图片偏移
                    });
                    marker[i] = new BMapGL.Marker(point[i],{icon:myIcon});
                }

                map.addOverlay(marker[i]);
                /*map.centerAndZoom(point[i], 13);*/
                //创建圆
                var circle = new Array;
                circle[i] = new BMapGL.Circle(point[i],750);
                map.addOverlay(circle[i]);

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
                    /*map.centerAndZoom(point[i], 15);*/
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
                        /*map.centerAndZoom(point[i], 15);*/
                        //创建圆
                        var circle = new Array;
                        circle[i] = new BMapGL.Circle(point[i],750);
                        map.addOverlay(circle[i]);
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
                            map.centerAndZoom(point[i], 15);


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