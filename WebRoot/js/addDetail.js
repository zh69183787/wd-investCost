document.write("<script language='javascript' src='../js/json2.js'></script>");
function addDetailFunc(obj,id){
	var getValue = $(obj).parent().children("input[type=text]").val().replace(/(^\s*)|(\s*$)/g,'').replace("请选择单位/部门","");
	if(getValue==""){
		alert("请选择单位/部门");
		$(obj).parent().children("input[type=text]").focus();
	}else if(getValue.indexOf("\"")!=-1){
		alert("单位/部门不能包含引号！");
		$(obj).parent().children("input[type=text]").focus();
	}else if(getValue.indexOf(":")!=-1){
		alert("单位/部门不能包含冒号！");
		$(obj).parent().children("input[type=text]").focus();
	}else if(getValue.indexOf(",")!=-1){
		alert("单位/部门不能包含逗号！");
		$(obj).parent().children("input[type=text]").focus();
	}else if(id==""){
		alert("此单位不存在！");
		$(obj).parent().children("input[type=text]").focus();
	}else{
		var linesHtml = "";
		$.ajax({
			type : 'post',
			url : '/investCost/contract/findLineByCompanyId.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				param:id
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				if(object!=null && object.length>0){
					var linesArray = new Array();
					for(var i=0,len=object.length; i<len; i++){
						linesArray.push(object[i].objectName);
					}
					if(linesArray.length>0){
						if(linesArray.length==1){
							linesHtml = "&nbsp;&nbsp;<select class='input_small'><option value='"+linesArray[0]+"'>"+linesArray[0]+"</option></select>";
						}else{
							linesHtml = "&nbsp;&nbsp;<select class='input_small'><option value=''>---请选择---</option>";
							for(var i=0;i<linesArray.length;i++){
								linesHtml += "<option value='"+linesArray[i]+"'>"+linesArray[i]+"</option>";
							}
							linesHtml += "</select>";
						}
					}
					
					var addHtml = "<p><input type='checkbox' id='detailCheck'/>&nbsp;&nbsp;<input type='text' class='input_small' id='detailInput' onblur='checkValueInput(this)' style='display:inline;text-align:right'/>&nbsp;(万元)&nbsp;&nbsp;<img src='"+
							getRootPath()+"/images/arrow_left.jpg' width='20px' height='15px' style='display:inline'/>"+linesHtml+"&nbsp;&nbsp;<span style='display:inline'>" +getValue + "</span><input type='hidden' id='inputCompanyId' value='"+id+"'/></p>";
					$(obj).parent().append(addHtml);
					var num = $(obj).parent().find("p").length-1;
					$(obj).parent().children("p:eq("+num+")").children("#detailInput").focus();
					$(obj).parent().children("input[type=text]").val("");
				}else{
					var addHtml = "<p><input type='checkbox' id='detailCheck'/>&nbsp;&nbsp;<input type='text' class='input_small' id='detailInput' onblur='checkValueInput(this)' style='display:inline;text-align:right'/>&nbsp;(万元)&nbsp;&nbsp;<img src='"+
							getRootPath()+"/images/arrow_left.jpg' width='20px' height='15px' style='display:inline'/>"+linesHtml+"&nbsp;&nbsp;<span style='display:inline'>" +getValue + "</span><input type='hidden' id='inputCompanyId' value='"+id+"'/></p>";
					$(obj).parent().append(addHtml);
					var num = $(obj).parent().find("p").length-1;
					$(obj).parent().children("p:eq("+num+")").children("#detailInput").focus();
					$(obj).parent().children("input[type=text]").val("");
				}
			}
		});	
	}
}

function deleteDetail(obj){
	var num = 0;
	$("input[id=detailCheck]").each(function(index){
		if($(this).attr("checked")=="checked"){
			num += 1;
			$(this).parent("p").remove();
		}
	});
	if(num==0){
		alert("请选择要删除的资金来源！");
	}
}

function checkValueInput(obj){
	if(!$.isNumeric($(obj).val())){
		alert("资金来源金额请输入数字！");
		$(obj).focus();
	}
}

function saveDetail(obj){
	var saveValue = "";
	var lineName = "";
	if($(obj).parent().find("p").length>0){
		saveValue = "{\"moneySource\":[";
		$(obj).parent().children("p").each(function(index){
			if(index>0){
				saveValue += ",";
			}
			lineName = "none";
			if($(this).find("select").length!=0){
				lineName = $(this).find("select").val();
			}
			saveValue += "{\"unitId\":\""+$(this).children("#inputCompanyId").val()+"\"," +
					"\"unitName\":\""+$(this).children("span").html()+"\"," +	
					"\"lineName\":\""+lineName+"\"," +	
					"\"money\":\""+parseFloat($(this).children("#detailInput").val()).toFixed(6)+"\"}";
		});
		saveValue += "]}";
	}
	//console.log(saveValue);
	$(obj).val(saveValue);
}

function loadPage(obj,type){
	//console.log($(obj).val());
	if($(obj).val().indexOf("moneySource")!=-1){
		var jsonData = JSON.parse($(obj).val());
		var addHtml = "";
		var index = 0;
		
		
		if(type=="edit"){
			if(jsonData!=null && jsonData.moneySource!=null && jsonData.moneySource.length>0){
				for(var i=0,len=jsonData.moneySource.length; i<len; i++ ){
					$.ajax({
						type : 'post',
						url : '/investCost/contract/findLineByCompanyId.action?random='+Math.random(),
						dataType:'json',
						cache : false,
						async:false,
						data:{
							param:jsonData.moneySource[i].unitId
						},
						error:function(){
							alert("系统连接失败，请稍后再试！")
						},
						success:function(object){	//返回的线路jsonarray
							if(object!=null && object.length>0){
								if(object.length==1){
									linesHtml = "&nbsp;&nbsp;<select class='input_small'><option value='"+object[0].objectName+"'>"+object[0].objectName+"</option></select>";
								}else{
									linesHtml = "&nbsp;&nbsp;<select class='input_small'><option value=''>---请选择---</option>";
									for(var j=0;j<object.length;j++){
										if(object[j].objectName==jsonData.moneySource[i].lineName){
											linesHtml += "<option value='"+object[j].objectName+"' selected='selected'>"+object[j].objectName+"</option>";
										}else{
											linesHtml += "<option value='"+object[j].objectName+"'>"+object[j].objectName+"</option>";
										}
									}
									linesHtml += "</select>";
								}
								addHtml = "<p><input type='checkbox' id='detailCheck'/>&nbsp;&nbsp;<input type='text' class='input_small' id='detailInput' onblur='checkValueInput(this)' style='display:inline;text-align:right' value='"+jsonData.moneySource[i].money+"'/>&nbsp;(万元)&nbsp;&nbsp;<img src='"+
										getRootPath()+"/images/arrow_left.jpg' width='20px' height='15px' style='display:inline'/>"+linesHtml+"&nbsp;&nbsp;<span style='display:inline'>" +jsonData.moneySource[i].unitName + "</span><input type='hidden' id='inputCompanyId' value='"+jsonData.moneySource[i].unitId+"'/></p>";
								$(obj).parent().append(addHtml);
							}else{
								addHtml = "<p><input type='checkbox' id='detailCheck'/>&nbsp;&nbsp;<input type='text' class='input_small' id='detailInput' onblur='checkValueInput(this)' style='display:inline;text-align:right' value='"+jsonData.moneySource[i].money+"'/>&nbsp;(万元)&nbsp;&nbsp;<img src='"+
								getRootPath()+"/images/arrow_left.jpg' width='20px' height='15px' style='display:inline'/>&nbsp;&nbsp;<span style='display:inline'>" +jsonData.moneySource[i].unitName + "</span><input type='hidden' id='inputCompanyId' value='"+jsonData.moneySource[i].unitId+"'/></p>";
								$(obj).parent().append(addHtml);
							}
						}
					});	
				}
			}
		}else{
			if(jsonData!=null && jsonData.moneySource!=null && jsonData.moneySource.length>0){
				for(var i=0,len=jsonData.moneySource.length; i<len; i++ ){
					var moneySource = jsonData.moneySource[i];
					var pid = jsonData.moneySource[i].unitId;		//项目公司的id
					var lineName = jsonData.moneySource[i].lineName;
					
					var linesHtml="";
					if(lineName!='' && lineName!='none'){
						linesHtml = "&nbsp;("+lineName+")";
					}
					addHtml = "<p>"+moneySource.unitName+linesHtml+"："+moneySource.money+"&nbsp;(万元)</p>";
					$(obj).parent().append(addHtml);
				}
			}
		}
	}
}

function loadEditPage(obj){
	loadPage(obj,'edit');
}

function loadViewPage(obj){
	loadPage(obj,'view');
}

//replaceall方法
String.prototype.replaceAll  = function(s1,s2){  
  return this.replace(new RegExp(s1,"gm"),s2);   
}

//js获取项目名，如：/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    //return(localhostPaht+projectName);
    return(projectName);
}

function removeAll(obj){
	$(obj).parent("td").find("p").remove();
}
