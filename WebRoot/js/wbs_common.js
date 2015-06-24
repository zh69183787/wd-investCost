//控制页面打开时候的展开层数
function openNodesByLevel(level){
	$("[id=show_common]").each(function(){
		if($(this).find("#hide_level").val()==1){
			$(this).attr("style","");
		}
		if($(this).find("#hide_level").val()<level){
			$(this).find("img").click();
			//showH($(this).find("#hide_id").val());
		}
	});
}

//保留刷新前的节点展开或收起状态
function shrinkHideNodes(){
	var hideNodes = $("#hideNodes").val().split(",");
	var img;
	for(var i=0;i<hideNodes.length;i++){
		img = $("#tbody").find("input[id=hide_id][value="+hideNodes[i]+"]").parents("tr").find("img");
		img.attr("id","minus");
		img.attr("src","../images/minus.png");
		img.parents("tr").attr("class","on");
		showH(hideNodes[i]);
	}
	window.location.href = "#"+$("#targetNode").val();
}

//展开或收缩功能
function showHierarchy(object){
	var hideNodes = $("#hideNodes").val();
	$targetTr = $(object).parent().parent().parent().parent();
	var id =  $targetTr.find("input[id=hide_id]").val();
	//****点击所在的tr行操作结束****
	
	//***开始隐藏下级所有****
	if($(object).attr("id")=="minus"){
		$(object).attr("src","../images/plus.png");
		$(object).attr("id","plus");
		$targetTr.removeClass("on");
		$targetTr.addClass("off");
		hideH(id);
	}else{
		$(object).attr("src","../images/minus.png");
		$(object).attr("id","minus");
		$targetTr.removeClass("off");
		$targetTr.addClass("on");
		showH(id);
	}

}