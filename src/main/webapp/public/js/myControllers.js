const preURL_get = "http://localhost:4000/textjson/";
const preURL_post = "/test/qrcode/";
// 模板管理  接口
var deleteQrcodeURL = preURL_post + 'deleteQrcode';   										//  删除   模板
var getQrcodeURL = preURL_get+'checkqrcode.json';
var sureupdateQrcodeURL =  preURL_post + 'updateQrcode';  											//  获取   所有的模板信息
var updateQrcodeURL =  preURL_post + 'updateQrcode';  										//  修改   模板
var createQrcodeURL = preURL_post + 'createQrcode';  										//  生成永久二维码
var newQrcodeURL = preURL_post + 'newQrcode';   													//  新建   模板
// 分润管理  接口
var getProfitURL = preURL_get + 'profitManage.json';   //获取    一级二级代理的分润比例和优惠券面额
var updateProfitURL = preURL_post + 'saveditExFirst';									 //修改   一级二级代理的分润比例和优惠券面额
var getGoodsURL = preURL_get + 'goodsProfit.json';     //获取   商品分类  的销售提成比例
var deleteGoodsURL = preURL_post + 'deleteQrcode';												 //删除   商品分类
var updateGoodsURL = deleteGoodsURL;																		 //修改   商品分类

// 权限管理的接口
// 角色管理  获 增 删 改
var getPrivilegeURL = preURL_get + 'getAllPrivilege.json';    // 获取 权限表
var getRoleURL = preURL_get + 'getAllRole.json';    			// 获取 角色表
var addRoleURL = preURL_post + 'addRole';    			// 新增 角色
var deleteRoleURL = preURL_post + 'deleteRole';    // 删除 角色
var updateRoleURL = preURL_post + 'updateRole';    // 修改 角色
// 用户管理  获 增 删 改
var getUserURL = preURL_get + 'getAllSystemUser.json';    			// 获取 用户表
var addUserURL = preURL_post + 'addUser';    			// 新增  用户
var deleteUserURL = preURL_post + 'deleteUser';    // 删除 用户
var updateUserURL = preURL_post + 'updateUser';    // 修改 用户


// 账单管理的接口
var getBillURL = preURL_post + 'getBill';   //获取 第n页的订单记录
var getMyBillURL = preURL_post + 'getMyBill';   //获取 用户为xx的 第n页的订单记录
var getBillMoneyURL = preURL_get + 'getBillMoney.json'; //获取总订单 的金额信息
var getBillUserURL = preURL_post + 'getBillUser';  //  获取用户为xx的用户信息
var getBillByIdURL = preURL_post + 'getBillById';   //通过订单号查询 订单
var getBillByDateURL = preURL_post + 'getBillByDate';   //通过时间段查询 订单
var getBillMoneyByDateURL = preURL_post + 'getBillMoneyByDate';
var getBillMoneyByIdURL = preURL_post + 'getBillMoneyById';


// 主页面 的controller
hjcr.controller('hjcrCtrl',function($rootScope,$scope,$location,$http){
	$scope.tableTitle = "欢迎使用";
	$scope.$on('$stateChangeSuccess', function(){
		if($location.path() === "/checkQrcode"){
			$scope.tableTitle = "模板管理-查看模板";
		}else if($location.path() === "/createQrcode"){
			$scope.tableTitle = "模板管理-生成永久二维码";
		}else if($location.path() === "/roleManage"){
			$scope.tableTitle = "权限管理-角色管理";
		}else if($location.path() === "/userManage"){
			$scope.tableTitle = "权限管理-用户管理";
		}else if($location.path() === "/rightsManage"){
			$scope.tableTitle = "权限管理-权限管理";
		}else if($location.path() === "/billManage"){
			$scope.tableTitle = "账单管理";
		}else if($location.path() === "/myBill"){
			$scope.tableTitle = "账单管理-个人账单";
		}else if($location.path() === "/profitManage"){
			$scope.tableTitle = "分润管理";
		}else if($location.path() === "/dataStatistic"){
			$scope.tableTitle = "数据统计";
		}else if($location.path() === "/systemLog"){
			$scope.tableTitle = "系统管理-系统日志";
		}else if($location.path() === "/dataBackup"){
			$scope.tableTitle = "系统管理-数据自动备份";
		}else if($location.path() === "/welcome"){
			$scope.tableTitle = "欢迎使用";
		}else if($location.path() === "/newQrcode"){
			$scope.tableTitle = "模板管理-查看模板-新建模板";
		}else if($location.path() === "/updateQrcode"){
			$scope.tableTitle = "模板管理-查看模板-修改模板";
		}
	});
});

// 模板管理
// 显示二维码 的controller
hjcr.controller('checkQCtrl',function($scope,$http){

	// 显示所有的模板
	$http.get(getQrcodeURL)
		.success(function(response){
			auth(response);
			$scope.qrcodes=response.resultParm.allTemplate;
		}).error(function(response){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
	});

	// 修改模板
	$scope.updateQrcode =  function(templateId){
		sessionStorage.templateId = templateId;
	};

	// 删除模板
	$scope.showModal = false;
	$scope.deleteQrcode = function(templateId,index){
		$scope.showModal = !$scope.showModal;
		$scope.templateId = templateId;
		$scope.templateIndex = index;
	};
	$scope.deleteTemplate =function(){
		$http.post(deleteQrcodeURL,{templateId:$scope.templateId})
		.success(function(response){
			auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			$scope.qrcodes.splice($scope.templateIndex,1);
		}).error(function(response){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
		$scope.showModal = !$scope.showModal;
	}
});

// 新建二维码 的controller
hjcr.controller('newQCtrl',function($scope,$http){
	$scope.newQrcodeImg = null;
	$scope.showQrcode = false;
	$scope.showToux = false;
	$scope.templateConfirm = false;
	//模板信息
	var template = {
		"templateQrcodeHigh":520/820,
		"templateQrcodeWide":150/450,
		"templateHeadImgHigh":250/820,
		"templateHeadImgWide":180/450,
		"templateQrcodeSize":150/450,
	};
  //显示选择的模板图片
	$scope.uploadImage = function(value){
		 document.querySelector('#template-name').focus();
		 $scope.newQrcodeImg = $('#select-file').val();
		 var prevDiv = document.getElementsByClassName('templateImg')[0];
	   if (value && value[0])
	   {
			  var reader = new FileReader();
			  reader.onload = function(evt){
				  prevDiv.innerHTML = '<img class="templateImg" src="' + evt.target.result + '" />';
				}
		  	reader.readAsDataURL(value[0]);
		 }
		 document.querySelector('#select-file').blur();
 		 document.querySelector('#template-name').blur();
	}
  //显示或移除二维码、头像
	$scope.showQ = function(value){$scope.showQrcode = value;}
	$scope.showT = function(value){$scope.showToux = value;}
  //移动二维码和头像的位置
	$scope.changePosition = function(obj,sent,getdivid){
	  var dmW = document.documentElement.clientWidth || document.body.clientWidth;
	  var dmH = document.documentElement.clientHeight || document.body.clientHeight;
	  var sent = sent || {};
	  var l = sent.l || 0;
	  var r = sent.r || dmW - obj.offsetWidth;
	  var t = sent.t || 0;
	  var b = sent.b || dmH - obj.offsetHeight;
	  var n = sent.n || 10;
	  obj.onmousedown = function (ev){
	    var oEvent = ev || event;
	    var sentX = oEvent.clientX - obj.offsetLeft;
	    var sentY = oEvent.clientY - obj.offsetTop;
	    document.onmousemove = function (ev){
	      var oEvent = ev || event;

	      var slideLeft = oEvent.clientX - sentX;
	      var slideTop = oEvent.clientY - sentY;

	      if(slideLeft <= l){
	        slideLeft = l;
	      }
	      if(slideLeft >= r){
	        slideLeft = r;
	      }
	      if(slideTop <= t){
	        slideTop = t;
	      }
	      if(slideTop >= b){
	        slideTop = b;
	      }

	      obj.style.left = slideLeft + 'px';
	      obj.style.top = slideTop + 'px';

				if(getdivid==='qrcode'){
					template.templateQrcodeWide = slideLeft/450;
					template.templateQrcodeHigh = slideTop/820;
				}
				else if (getdivid==='toux') {
					template.templateHeadImgWide = slideLeft/450;
					template.templateHeadImgHigh = slideTop/820;
				}
				return false;
	    };
	    document.onmouseup = function (){
	      document.onmousemove = null;
	      document.onmouseup = null;
	    }

	    return false;
	  }
	}
	$scope.move = function(getdivid) {
	  var oDiv = document.getElementById(getdivid);
	  var oParent = document.getElementsByClassName('edit-template')[0];
	  var sent = {
	    l: 0, //设置div在父元素的活动范围，10相当于给父div设置padding-left：10;
	    r: oParent.offsetWidth - oDiv.offsetWidth, // offsetWidth:当前对象的宽度， offsetWidth = width+padding+border
	    t: 0,
	    b: oParent.offsetHeight - oDiv.offsetHeight,
	    n: 0
	  }
	  $scope.changePosition(oDiv, sent,getdivid);
	}
  //确认上传模板
	$scope.submitTemplate = function(){
		if($("#qrcodeImg").width()!=null && $("#qrcodeImg").width()!=0 && $("#qrcodeImg").width()!=undefined){
			template.templateQrcodeSize = $("#qrcodeImg").width()/450;
		}
		template.templateConfirm = $scope.templateConfirm===true?1:0;
		var templateFormDate = new FormData(document.getElementById("myForm"));
		templateFormDate.append("templateQrcodeHigh",template.templateQrcodeHigh);
		templateFormDate.append("templateQrcodeWide",template.templateQrcodeWide);
		templateFormDate.append("templateHeadImgHigh",template.templateHeadImgHigh);
		templateFormDate.append("templateHeadImgWide",template.templateHeadImgWide);
		templateFormDate.append("templateQrcodeSize",template.templateQrcodeSize);
		templateFormDate.append("templateConfirm",template.templateConfirm);
		var xhr = new XMLHttpRequest();
    xhr.onload=function(event)
    {
      if  ( ( xhr.status >= 200 && xhr.status < 300) || xhr.status == 304)   //上传成功
      {
				alertMes("上传成功!",'success','fa-check');
      }
			else if(xhr.status == 401){
				alertMes("您没有权限，操作失败!",'danger','fa-bolt');
			}
      else
      {
        alertMes("上传失败!",'warning','fa-warning');
      }
    };
		xhr.open("POST", newQrcodeURL,true);
		xhr.send(templateFormDate);
	}
});


//修改二维码 的controller
hjcr.controller('updateQCtrl',function($scope,$http){
	$scope.template = null;
	//显示要修改的模板的原始信息
	$http.post(sureupdateQrcodeURL,{templateId:sessionStorage.templateId})
		.success(function(response){
			$scope.template = response.resultParm.allTemplate;
			var prevDiv = document.getElementsByClassName('templateImg')[0];
			prevDiv.innerHTML = '<img class="templateImg" src="' + $scope.template.templatePath + '" />';
			$scope.showQrcode = true;
			$scope.showToux = true;
			// 设置二维码大小位置  头像位置
			$("#qrcode").css({
					width:$scope.template.templateQrcodeSize*450,
					height:$scope.template.templateQrcodeSize*450,
					top:$scope.template.templateQrcodeHigh*820,
					left:$scope.template.templateQrcodeWide*450
			})
			$("#toux").css({
				top:$scope.template.templateHeadImgHigh*820,
				left:$scope.template.templateHeadImgWide*450,
			});
		}).error(function(){
			alertMes("请求得不到响应，请稍后重试...",'warning','fa-warning');
	});
	//显示或移除二维码、头像
	$scope.showQ = function(value){$scope.showQrcode = value;}
	$scope.showT = function(value){$scope.showToux = value;}
	//移动二维码和头像的位置
	$scope.changePosition = function(obj,sent,getdivid){
	  var dmW = document.documentElement.clientWidth || document.body.clientWidth;
	  var dmH = document.documentElement.clientHeight || document.body.clientHeight;
	  var sent = sent || {};
	  var l = sent.l || 0;
	  var r = sent.r || dmW - obj.offsetWidth;
	  var t = sent.t || 0;
	  var b = sent.b || dmH - obj.offsetHeight;
	  var n = sent.n || 10;
	  obj.onmousedown = function (ev){
	    var oEvent = ev || event;
	    var sentX = oEvent.clientX - obj.offsetLeft;
	    var sentY = oEvent.clientY - obj.offsetTop;
	    document.onmousemove = function (ev){
	      var oEvent = ev || event;

	      var slideLeft = oEvent.clientX - sentX;
	      var slideTop = oEvent.clientY - sentY;

	      if(slideLeft <= l){
	        slideLeft = l;
	      }
	      if(slideLeft >= r){
	        slideLeft = r;
	      }
	      if(slideTop <= t){
	        slideTop = t;
	      }
	      if(slideTop >= b){
	        slideTop = b;
	      }

	      obj.style.left = slideLeft + 'px';
	      obj.style.top = slideTop + 'px';

				if(getdivid==='qrcode'){
					$scope.template.templateQrcodeWide = slideLeft/450;
					$scope.template.templateQrcodeHigh = slideTop/820;
				}
				else if (getdivid==='toux') {
					$scope.template.templateHeadImgWide = slideLeft/450;
					$scope.template.templateHeadImgHigh = slideTop/820;
				}
				return false;
	    };
	    document.onmouseup = function (){
	      document.onmousemove = null;
	      document.onmouseup = null;
	    }

	    return false;
	  }
	}
	$scope.move = function(getdivid) {
	  var oDiv = document.getElementById(getdivid);
	  var oParent = document.getElementsByClassName('edit-template')[0];
	  var sent = {
	    l: 0, //设置div在父元素的活动范围，10相当于给父div设置padding-left：10;
	    r: oParent.offsetWidth - oDiv.offsetWidth, // offsetWidth:当前对象的宽度， offsetWidth = width+padding+border
	    t: 0,
	    b: oParent.offsetHeight - oDiv.offsetHeight,
	    n: 0
	  }
	  $scope.changePosition(oDiv, sent,getdivid);
	}
	//确认修改模板
	$scope.submitTemplate = function(){
		$scope.template.templateName=$("#templateName").val();
		$scope.template.templateQrcodeSize = $("#qrcodeImg").width()/450;
		$http.post(updateQrcodeURL,$scope.template)
		.success(function(response){
			auth(response);
			alertMes(response.resultInfo,'success','fa-check');
		}).error(function(){
			alertMes("修改失败,请稍后重试...",'warning','fa-warning');
		});
	}
});

// 生成永久二维码 的controller
hjcr.controller('createQCtrl',function($scope,$http){
   $scope.createQ = false;
	 $scope.createQImg = null;
	 $scope.showTip = false;
	 $scope.checkMobile = function () {
		 	var phone = $(".phone").val();
	    if(!(/^1[34578]\d{9}$/.test(phone))){
	        return true;
	    }
			return false;
	 }
	 $scope.sureCreate = function() {
		$scope.showTip = $scope.checkMobile();
		if($scope.showTip===false){
			$http.post(createQrcodeURL,{userTelephone:$scope.phone})
			.success(function(response){
  			auth(response);
				alertMes(response.resultInfo,'success','fa-check');
				$scope.createQImg=response.templatePath;
				$scope.createQ = true;
			}).error(function(){
				alertMes("请求未得到响应，请稍后重试！",'warning','fa-warning');
				$scope.createQ = false;
			});
		}
	 }
});


// 分润管理 的controller
hjcr.controller('profitCtrl',function($scope,$http){
	$http.get(getProfitURL)
	.success(function(response){
  	auth(response);
		$scope.money=response.resultParm;
	}).error(function(){
		alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
	});
	$http.get(getGoodsURL)
	.success(function(response){
  	auth(response);
		$scope.productions=response;
	}).error(function(){
		alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
	});
	// 修改一级代理的优惠券面额
	$scope.ifEditExFirst = false;
	$scope.editExFirst = function(){
		$scope.ifEditExFirst = !$scope.ifEditExFirst;
	}
	$scope.saveditExFirst = function(){
		var save_exFirst = $("#save-exFirst").val();
		$http.post(updateProfitURL,{extensionMoneyFirst:save_exFirst})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			$scope.ifEditExFirst = !$scope.ifEditExFirst;
			$scope.money.extensionMoneyFirst = save_exFirst;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}
	// 修改二级代理的优惠券面额
	$scope.ifEditExSecond = false;
	$scope.editExSecond = function(){
		$scope.ifEditExSecond = !$scope.ifEditExSecond;
	}
	$scope.saveditExSecond = function(){
		var save_exSecond = $("#save-exSecond").val();
		$http.post(updateProfitURL,{extensionMoneySecond:save_exSecond})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			$scope.ifEditExSecond = !$scope.ifEditExSecond;
			$scope.money.extensionMoneySecond = save_exSecond;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}
	// 修改一级代理的销售分配比例
	$scope.ifEditOrderFirst = false;
	$scope.editOrderFirst = function(){
		$scope.ifEditOrderFirst = !$scope.ifEditOrderFirst;
	}
	$scope.savedOrderFirst = function(){
		var save_orderFirst = $("#save-orderFirst").val()/100;
		$http.post(updateProfitURL,{orderMoneyFirst:save_orderFirst})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			$scope.ifEditOrderFirst = !$scope.ifEditOrderFirst;
			$scope.money.orderMoneyFirst = save_orderFirst;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}
	// 修改二级代理的销售分配比例
	$scope.ifEditOrderSecond = false;
	$scope.editOrderSecond = function(){
		$scope.ifEditOrderSecond = !$scope.ifEditOrderSecond;
	}
	$scope.savedOrderSecond = function(){
		var save_orderSecond = $("#save-orderSecond").val()/100;
		$http.post(updateProfitURL,{orderMoneySecond:save_orderSecond})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			$scope.ifEditOrderSecond= !$scope.ifEditOrderSecond;
			$scope.money.orderMoneySecond = save_orderSecond;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}

	// 新增商品类别
	$scope.addNewGoods = function(){
			var addhtml = "<tr>"+
											"<td>"+"<input type='text'>"+"</td>"+
											"<td>"+"<input type='number' min='0'>%"+"</td>"+
											"<td>"+
												"<a  class='btn btn-success btn-xs save'><i class='fa fa-save'></i> 确认</a>"+
		                    "<a  class='btn btn-warning btn-xs cancel'><i class='fa fa-times'></i> 取消</a>"+
											"</td>"+
								    "</tr>";
			$(addhtml).prependTo("#goods-distribution");
	}
	// 删除商品类别
	$scope.showModal = false;
	$scope.showDelModel = function(id){
		$scope.showModal = true;
		$scope.deleteId = id;
	}
	$scope.closeModel = function(){
		$scope.showModal = false;
	}
	$scope.deleteGoods = function(){
		$http.post(deleteGoodsURL,{orderMoneyId:$scope.deleteId})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			$scope.showModal = false;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
			$scope.showModal = false;
		});
	}
	// 修改商品类别
	$scope.updateGoods = function(index,id){
		$scope.productions[index].editActive = !$scope.productions[index].editActive;
	}
	$scope.sureupdateGoods = function(index,id){
		$http.post(updateGoodsURL,{
			orderMoneyId:id,
			orderMoneyName:$scope.productions[index].newOrderMoneyName,
			orderMoneyDistribution:$scope.productions[index].newOrderMoneyDistribution/100
		})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			$scope.productions[index].orderMoneyName = $scope.productions[index].newOrderMoneyName;
			$scope.productions[index].orderMoneyDistribution = $scope.productions[index].newOrderMoneyDistribution/100;
			$scope.productions[index].editActive = !$scope.productions[index].editActive;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}
});

// 权限管理
// 角色管理 controller
hjcr.controller('roleCtrl',function($scope,$http){
	$scope.showModal = false;
	// 获取角色表
	$http.get(getRoleURL)
		.success(function(response){
  		auth(response);
			$scope.roles=response.resultParm.roleList;
		}).error(function(){
			alert("请求得不到响应，请稍后重试...");
	});
	// 获取权限表
	$http.get(getPrivilegeURL)
		.success(function(response){
  		auth(response);
			$scope.privileges=response.resultParm.privilegeList;
		}).error(function(){
			alert("请求得不到响应，请稍后重试...");
	});

	// 是否显示权限
	$scope.showPrivilegeFuc = function(index){
		$scope.roles[index].editActive = !$scope.roles[index].editActive;
	}


	//显示删除角色确认框
	$scope.deleteRole = function(id){
		$scope.showModal = !$scope.showModal;
		$scope.deleteRoleId = id;
	}
	$scope.closeModel = function(){
		$scope.showModal = !$scope.showModal;
	}
	// 确认删除角色
	$scope.sureDeleteRole = function(){
		$http.post(deleteRoleURL,{
				id:$scope.deleteRoleId
			})
			.success(function(response){
  			auth(response);
				alertMes(response.resultInfo,'info','fa-check');
				$scope.showModal = !$scope.showModal;
				$http.get(getRoleURL)
					.success(function(response){
  auth(response);
						$scope.roles=response.resultParm.roleList;
					}).error(function(){
						alert("请求未得到响应！请稍后刷新重试。");
				});
			}).error(function(){
				$scope.showModal = !$scope.showModal;
				alert("请求未得到响应！请稍后刷新重试。");
		});
	}


	// 显示 添加角色  对话框
	$scope.showRoleModel = false;
	$scope.addRole = function(){
		$scope.showRoleModel = !$scope.showRoleModel;
	}
	// 确认添加角色
	$scope.submitRole = function(){
		var formElement = document.getElementById("roleForm");
		var xhr = new XMLHttpRequest();
    xhr.onload=function(event)
    {
      if  ( ( xhr.status >= 200 && xhr.status < 300) || xhr.status == 304)   //上传成功
      {
				alertMes('添加角色成功！','success','fa-check');
				$http.get(getRoleURL)
					.success(function(response){
  auth(response);
						$scope.roles=response.resultParm.roleList;
					}).error(function(){
						alert("错误！请刷新重试。");
				});
      }
      else
      {
        alert("添加失败！");
      }
    };
		xhr.open("POST", addRoleURL,true);
		xhr.send(new FormData(formElement));
		$scope.showRoleModel = !$scope.showRoleModel;
	}


	// 显示 修改角色 名称和 角色   的 对话框
	$scope.updateRoleModel = false;
	$scope.updateRoleName = function(id){
		$scope.updateRoleModel = !$scope.updateRoleModel;
		$scope.updateRoleId = id;
	}
	// 确认修改 角色的名称和说明
	$scope.sureUpdate = function(){
		$http.post(updateRoleURL,{
			id:$scope.updateRoleId,
			rolename:$scope.roleName,
			describe:$scope.roleDescribe
		})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'info','fa-check');
			$http.get(getRoleURL)
				.success(function(response){
  				auth(response);
					$scope.roles=response.resultParm.roleList;
				}).error(function(){
					alert("请求得不到响应，请稍后重试...");
			});
		}).error(function(){
			alert("请求得不到响应，请稍后重试...");
		});
		$scope.updateRoleModel = !$scope.updateRoleModel;
	}


	// 对角色的权限进行增删
	$scope.updateRole = function(id,privilege){
		$http.post(updateRoleURL,{
				roleId:id,
				privilegeId:privilege
			})
			.success(function(response){
  auth(response);
				alertMes(response.resultInfo,'info','fa-check');
				var edit = new Array();
				for (var i = 0; i < $scope.roles.length; i++) {
					edit[i] = $scope.roles[i].editActive;
				}
				$http.get(getRoleURL)
					.success(function(response){
  auth(response);
						$scope.roles=response.resultParm.roleList;
						for (var i = 0; i < $scope.roles.length; i++) {
							$scope.roles[i].editActive = edit[i];
						}
					}).error(function(){
						alert("请求得不到响应，请稍后重试...");
				});
			}).error(function(){
				alert("请求得不到响应，请稍后重试...");
		});
	}


});

// 用户管理 controller
hjcr.controller('userCtrl',function($scope,$http){
	// 获取用户表
	$http.get(getUserURL)
		.success(function(response){
  auth(response);
			auth(response);
			$scope.users=response.resultParm.userList;
		}).error(function(){
			alert("请求未得到响应！请稍后刷新重试。");
	});
	// 获取角色表
	$http.get(getRoleURL)
		.success(function(response){
  auth(response);
			$scope.roles=response.resultParm.roleList;
		}).error(function(){
			alert("请求未得到响应！请稍后刷新重试。");
	});


	// 删除用户确认弹框
	$scope.showModal = false;
	$scope.deleteUser = function(id){
		$scope.showModal = !$scope.showModal;
		$scope.deleteUserId = id;
	};
	$scope.sureDeleteUser =function(){
		$http.post(deleteUserURL,{id:$scope.deleteUserId})
		.success(function(response){
  auth(response);
			alertMes(response.resultInfo,'info','fa-check');
			$http.get(getUserURL)
				.success(function(response){
  auth(response);
					$scope.users=response.resultParm.userList;
				}).error(function(){
					alert("请求未得到响应！请稍后刷新重试。");
			});
		}).error(function(){
			alert("请求未得到响应，请稍后重试。。。");
		});
		$scope.showModal = !$scope.showModal;
	}
	// 新增用户
	$scope.newUser = {};
	$scope.addUser = function(){
		$scope.showAddUser = !$scope.showAddUser;
	}
	$scope.sureAddUser = function(){
		$http.post(addUserURL,$scope.newUser)
			.success(function(response){
  auth(response);
				alertMes(response.resultInfo,'info','fa-check');
				$http.get(getUserURL)
					.success(function(response){
  auth(response);
						$scope.users=response.resultParm.userList;
					}).error(function(){
						alert("请求未得到响应！请稍后刷新重试。");
				});
				$scope.showAddUser = !$scope.showAddUser;
			}).error(function(){
				alert("请求未得到响应！请稍后刷新重试。");
		});
	}
	// 修改用户
	$scope.updateUser = function(index,id){
		$scope.users[index].editActive = !$scope.users[index].editActive;
	}
	$scope.sureUpdateUser = function(index,id){
		$http.post(updateUserURL,{
			id:id,
			roleId:$scope.users[index].newuserRole,
		})
		.success(function(response){
  auth(response);
			$scope.users[index].userRole = $scope.users[index].newuserRole.roleName;
			console.log(response);
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
		$scope.users[index].editActive = !$scope.users[index].editActive;
	}
});


// 账单管理
// 总订单控制器
hjcr.controller('billManageCtrl',function($scope,$http){
	// 获得第n页的订单信息  的方法
	$scope.getPageBill = function(num){
		if((num!=$scope.currentNum) && (num===1 || (num>1&&num<=$scope.bills.totalPage))){
			$scope.currentNum = num;
			console.log(num);
			$http.post(getBillURL,{page:num})
				.success(function(response){
  auth(response);
					$scope.bills=response;
					$scope.pageArr = new Array();
					for(var i=0;i<$scope.bills.totalPage;i++){
						$scope.pageArr[i] = i+1;
					}
				}).error(function(){
					alert("请求未得到服务器响应，请稍后重试...");
			});
		}
	}
	// 获得总订单金额信息
	$http.get(getBillMoneyURL)
		.success(function(response){
  auth(response);
			$scope.billMoney=response;
		}).error(function(){
			alert("请求未得到服务器响应，请稍后重试...");
	});
	// 自动调用获取第一页订单信息
	$scope.getPageBill(1);
	// 查看某个用户的相关订单信息
	$scope.checkMyBill = function(id){
		sessionStorage.userId = id;
	}
	// 按订单号查询订单
	$scope.checkBillById = function(id){
		if(id!=null && id!='' && id!=' '){
			console.log(id);
			$http.post(getBillByIdURL,{billId:id})
				.success(function(response){
  auth(response);
					$scope.bills=response;
					$scope.pageArr = new Array();
					for(var i=0;i<$scope.bills.totalPage;i++){
						$scope.pageArr[i] = i+1;
					}
				}).error(function(){
					alert("请求未得到服务器响应，请稍后重试...");
			});
			$http.post(getBillMoneyByIdURL,{billId:id})
				.success(function(response){
  auth(response);
					$scope.billMoney=response;
				}).error(function(){
					alert("请求未得到服务器响应，请稍后重试...");
			});
		}
	}
	// 按时间段查询订单
	$scope.checkBillByDate = function(){
		var date = $("#reservation").val();
		if(date!=null && date!='' && date!=' '){
			console.log(date);
			$http.post(getBillByDateURL,{billDate:date})
				.success(function(response){
  auth(response);
					$scope.bills=response;
					$scope.pageArr = new Array();
					for(var i=0;i<$scope.bills.totalPage;i++){
						$scope.pageArr[i] = i+1;
					}
				}).error(function(){
					alert("请求未得到服务器响应，请稍后重试...");
			});
			$http.post(getBillMoneyByDateURL,{billDate:date})
				.success(function(response){
  auth(response);
					$scope.billMoney=response;
				}).error(function(){
					alert("请求未得到服务器响应，请稍后重试...");
			});
		}
	}
	// 返回总订单表
	$scope.backToAllBill = function(){
		$http.post(getBillURL,{page:1})
			.success(function(response){
  auth(response);
				$scope.bills=response;
				$scope.pageArr = new Array();
				for(var i=0;i<$scope.bills.totalPage;i++){
					$scope.pageArr[i] = i+1;
				}
			}).error(function(){
				alert("请求未得到服务器响应，请稍后重试...");
		});
	}
});
// 个人订单控制器
hjcr.controller('myBillCtrl',function($scope,$http){
	// 获得用户id为id 的 第n页的订单信息  的方法
	$scope.getPageMyBill = function(num,id){
		if((num!=$scope.currentNum) && (num==1 || (num>1&&num<=$scope.bills.totalPage))){
			$scope.currentNum = num;
			console.log(num+' '+id);
			$http.post(getMyBillURL,{page:num,userId:id})
				.success(function(response){
  auth(response);
					$scope.bills=response;
					$scope.pageArr = new Array();
					for(var i=0;i<$scope.bills.totalPage;i++){
						$scope.pageArr[i] = i+1;
					}
				}).error(function(){
					alert("请求未得到服务器响应，请稍后重试...");
			});
		}
	}
	// 获得个人信息
	$http.post(getBillUserURL,{userId:sessionStorage.userId})
		.success(function(response){
  auth(response);
			$scope.user=response;
			$scope.user.userId = sessionStorage.userId;
		}).error(function(){
			alert("请求未得到服务器响应，请稍后重试...");
	});
	// 自动调用获取第一页订单信息
	$scope.getPageMyBill(1,sessionStorage.userId);
});
