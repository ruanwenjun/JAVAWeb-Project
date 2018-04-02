// JScript 文件
document.writeln('<iframe id=ddd____meizzDateLayer Author=wayx frameborder=0 scrolling=auto src="about:blank" style="position: absolute; width: 192; height: 261; z-index: 9998; display: none; border:2px solid #333333;" width=192 height=261></iframe>');
//window.frames.ddd____meizzDateLayer.document.close();		//解决ie进度条不结束的问题

var ddd____outObject;
var ddd____outButton;		//点击的按钮
var ddd____outDate="";		//存放对象的日期
var ddd____odatelayer=window.frames.ddd____meizzDateLayer.document.all;		//存放日历对象
function setwindows(tt,page,left,top,width,height) //主调函数
{
	if (arguments.length >  6){alert("对不起！传入本控件的参数太多！");return;}
	if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
	var dads  = document.all.ddd____meizzDateLayer.style;
	var th = tt;
	var ttop  = tt.offsetTop;     //TT控件的定位点高
	var thei  = tt.clientHeight;  //TT控件本身的高
	var tleft = tt.offsetLeft;    //TT控件的定位点宽
	var ttyp  = tt.type;          //TT控件的类型
	while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
	dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
	dads.left = tleft+left;
	dads.top = ttop+top;
	if(width>0)
	    dads.width=width;
	if(height>0)
	    dads.height=height;
	ddd____outObject = th;
	document.all.ddd____meizzDateLayer.style.display = 'block';
	document.all.ddd____meizzDateLayer.src=page;
	event.returnValue=false;
}
function setwindows2(page,width,height)
{
    var sHeight=window.screen.height-90;//获取除去顶部框架高度外的屏幕高度尺寸
    var dads  = document.all.ddd____meizzDateLayer.style;
    dads.width=width;
    dads.height=height;
    if(document.body.clientHeight>sHeight)//如果右侧可见区域的高度比当前屏幕分辨率的高度还要高
        dads.top=(document.body.clientHeight-sHeight)+(sHeight-height)/2;//当前顶部位置应该为除去隐藏在滚动条里面的高度，再减去显示在外的尺寸除以２
    else
        dads.top=(document.body.clientHeight-height)/2;
    dads.left=(document.body.clientWidth-width)/2;
    document.all.ddd____meizzDateLayer.style.display = 'block';
	document.all.ddd____meizzDateLayer.src=page;
	event.returnValue=false;
}
function setdialog(page,width,height)
{
    window.showModallessDialog(page,'','dialogWidth:'+width+'px;dialogHeight:'+height+'px;');
}
//关闭层
function closeLayer()
{
    document.all.ddd____meizzDateLayer.style.display = 'none';
	document.all.ddd____meizzDateLayer.src="about:blank";
}



/*function a(){
s = "网页可见区域宽："+ document.body.clientWidth;
s += "\r\n网页可见区域高："+ document.body.clientHeight;
s += "\r\n网页可见区域宽："+ document.body.offsetWidth +" (包括边线的宽)";
s += "\r\n网页可见区域高："+ document.body.offsetHeight +" (包括边线的宽)";
s += "\r\n网页正文全文宽："+ document.body.scrollWidth;
s += "\r\n网页正文全文高："+ document.body.scrollHeight;
s += "\r\n网页被卷去的上："+ document.body.scrollTop;
s += "\r\n网页被卷去的左："+ document.body.scrollLeft;
s += "\r\n网页正文部分上："+ window.screenTop;
s += "\r\n网页正文部分左："+ window.screenLeft;
s += "\r\n屏幕分辨率的高："+ window.screen.height;
s += "\r\n屏幕分辨率的宽："+ window.screen.width;
s += "\r\n屏幕可用工作区高度："+ window.screen.availHeight;
s += "\r\n屏幕可用工作区宽度："+ window.screen.availWidth;
s += "\r\n网页正文全文高："+ document.body.scrollHeight;
alert(s);
}
a();*/
