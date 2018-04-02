// JScript 文件
//关闭层
function CloseFrameDiv()
{
    window.parent.document.all.ddd____meizzDateLayer.style.display = 'none';
	window.parent.document.all.ddd____meizzDateLayer.src="about:blank";
}

//上传图片
function UploadImageItems(items,value,add)
{
    if(add==0)
        window.parent.document.getElementById(items).value=value;
    else
    {
        if(window.parent.document.getElementById(items).value=='')
            window.parent.document.getElementById(items).value=value;
        else
            window.parent.document.getElementById(items).value += ',' + value;
    }
}
//选择日历
function CalendarSelect(items,value)
{
    window.parent.document.getElementById(items).value=value;
}
//产品顶部连接点击效果
function ProductHeadClick(obj,position)
{
    for(var i=1;i<=6;i++)
    {
        document.getElementById('productInfoTable_'+String(i)).style.display='none';
        document.getElementById('productInfoTD_'+String(i)).className='productHead';
    }
    document.getElementById('productInfoTable_'+String(position)).style.display='block';
    obj.className='productHeadClick';
}
//返回上一页
function PageBack()
{
    history.back(-1);
}
//处理图片自适应大小
function imageAutoSize(obj,width){
    var image=new Image(); 
    image.src=obj.src;
	var imagew = image.width;
	var imageh = image.height;
	if(imagew>width){
		obj.height = (imageh*width)/imagew;
		obj.width = width;
	}
}

function check(obj,items)
{
	if(obj.checked)
	{
		if(document.getElementById(items).value=='')
			document.getElementById(items).value = obj.value;
		else
			document.getElementById(items).value += ","+obj.value;
	}
	else
	{
		var idStr = "";
		var idAr = document.getElementById(items).value.split(',');
		if(idAr.length>0)
		{
			for(var i=0;i<idAr.length;i++)
			{
				if(obj.value==idAr[i])
				{idStr = idStr;}
				else
				{
					if(idStr == "")
						idStr = idAr[i];
					else
						idStr += ","+idAr[i];
				}
			}
			document.getElementById(items).value =idStr;
		}
	}
}