const preURL_project = "/hjcr-wechat/";

// 模板管理  接口
var deleteQrcodeURL = preURL_project + 'deteleTemplate';   										//  删除   模板
var getQrcodeURL = preURL_project+'getAllTemplate';   												//  获取   所有的模板信息
var sureupdateQrcodeURL =  preURL_project + 'getTemplate';  										//  修改   模板
var updateQrcodeURL=preURL_project+'updateTemplate';                                                  //获取模板
var createQrcodeURL = preURL_project + 'getlastqrcode';  										//  生成永久二维码
var newQrcodeURL = preURL_project + 'addTemplate';   													//  新建   模板
// 分润管理  接口
var getProfitURL = preURL_project + 'profitManage.json';   //获取    一级二级代理的分润比例和优惠券面额
var updateProfitURL = preURL_project + 'saveditExFirst';									 //修改   一级二级代理的分润比例和优惠券面额
var getGoodsURL = preURL_project + 'getOrderMoney';     //获取   商品分类  的销售提成比例
var deleteGoodsURL = preURL_project + 'deteleOrderMoney';												 //删除   商品分类
var updateGoodsURL = preURL_project +'updataOrderMoney';																		 //修改   商品分类
// 权限管理的接口
var getPrivilegeURL = preURL_project + 'system/getAllPrivilege';    // 获取 权限表
var getRoleURL = preURL_project + 'system/getAllRole';    			// 获取 角色表
var addRoleURL = preURL_project + 'system/addRole';    			// 新增 角色
var updateRoleURL = preURL_project + 'system/updateRolePrivilege';    // 修改 角色权限
var deleteRoleURL = preURL_project + 'system/deleteRole';    // 删除 角色
var getUserURL = preURL_project + 'system/getAllSystemUser';    			// 获取 用户表
var addUserURL = preURL_project + 'system/addSystemUser';    			// 新增  用户
var updateUserURL = preURL_project + 'system/updateUserRole';    // 修改 用户 角色
var deleteUserURL = preURL_project + 'system/deleteSystemUser';    // 删除 用户
// 账单管理的接口
var getBillURL = preURL_project + 'getBill';   //获取 第n页的订单记录
var getMyBillURL = preURL_project + 'getMyBill';   //获取 用户为xx的 第n页的订单记录
var getBillMoneyURL = preURL_project + 'getBillMoney.json'; //获取总订单 的金额信息
var getBillUserURL = preURL_project + 'getBillUser';  //  获取用户为xx的用户信息
var getBillByIdURL = preURL_project + 'getBillById';   //通过订单号查询 订单
var getBillByDateURL = preURL_project + 'getBillByDate';   //通过时间段查询 订单
var getBillMoneyByDateURL = preURL_project + 'getBillMoneyByDate';
var getBillMoneyByIdURL = preURL_project + 'getBillMoneyById';


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
			$scope.qrcodes=response.resultParm.allTemplate;
		}).error(function(response){
			alert("请求得不到响应，请稍后刷新重试！");
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
			alert(response.resultInfo);
			console.log(response+' '+"删除了id为"+$scope.templateId+"的模板");
			// 这里可以对response做判断，判断是否有权限，有则只需下一步操作
			$scope.qrcodes.splice($scope.templateIndex,1);
		}).error(function(response){
			alert("请求得不到响应，请稍后刷新重试！");
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
	//确认上传模板
	$scope.submitTemplate = function(){
		//模板信息
		$scope.template = {
			"templateName":null,
			"templateQrcodeHigh":520/820,
			"templateQrcodeWide":150/450,
			"templateHeadImgHigh":250/820,
			"templateHeadImgWide":180/450,
			"templateQrcodeSize":150/450,
			"templateConfirm":false
		};
		$scope.template.templateName=$("#templateName").val();
		$scope.template.templateQrcodeSize = $("#qrcodeImg").width()/450;
		$scope.template.templateConfirm = $scope.templateConfirm;
		var templateFormDate = new FormData(document.getElementById("myForm"));
		for (var i in $scope.template) {
			if ($scope.template.hasOwnProperty(i) === true){
				templateFormDate.append(i,$scope.template[i]);
			}
		}
		$http.post(newQrcodeURL,templateFormDate)
		.success(function(response){
			alert("上传成功！");
			console.log($scope.template);
			console.log(templateFormDate);
		}).error(function(){
			alert("上传失败,请稍后重试...");
		});
	}
});

//修改二维码 的controller
hjcr.controller('updateQCtrl',function($scope,$http){
	$scope.template = null;
	//显示要修改的模板的原始信息
	console.log("1");
	$http.post(sureupdateQrcodeURL,{templateId:sessionStorage.templateId})
		.success(function(response){
			console.log("2");
			$scope.template = response.resultParm.allTemplate;
			console.log($scope.template);
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
			console.log("请求得不到响应，请稍后重试...");
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
		console.log($scope.template);
		$http.post(updateQrcodeURL,$scope.template)
		.success(function(response){
			alert("上传成功！");
		}).error(function(){
			alert("上传失败,请稍后重试...");
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
				$scope.createQImg=response.templatePath;
				$scope.createQ = true;
			}).error(function(){
				alert("请求未得到响应，请稍后重试！");
				$scope.createQ = false;
			});
		}
	 }
});


// 分润管理 的controller
hjcr.controller('profitCtrl',function($scope,$http){
	$http.get(getProfitURL)
	.success(function(response){
		$scope.money=response;
	}).error(function(){
		alert("系统内部错误");
	});
	$http.get(getGoodsURL)
	.success(function(response){
		$scope.productions=response;
	}).error(function(){
		alert("系统内部错误");
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
			console.log(response.data);
			$scope.ifEditExFirst = !$scope.ifEditExFirst;
			$scope.money.extensionMoneyFirst = save_exFirst;
		}).error(function(){
			alert("系统内部错误");
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
			console.log(response.data);
			$scope.ifEditExSecond = !$scope.ifEditExSecond;
			$scope.money.extensionMoneySecond = save_exSecond;
		}).error(function(){
			alert("系统内部错误");
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
			console.log(response.data);
			$scope.ifEditOrderFirst = !$scope.ifEditOrderFirst;
			$scope.money.orderMoneyFirst = save_orderFirst;
		}).error(function(){
			alert("系统内部错误");
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
			console.log(response.data);
			$scope.ifEditOrderSecond= !$scope.ifEditOrderSecond;
			$scope.money.orderMoneySecond = save_orderSecond;
		}).error(function(){
			alert("系统内部错误");
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
			$scope.showModal = false;
			console.log(response);
		}).error(function(){
			alert("系统内部错误");
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
			$scope.productions[index].orderMoneyName = $scope.productions[index].newOrderMoneyName;
			$scope.productions[index].orderMoneyDistribution = $scope.productions[index].newOrderMoneyDistribution/100;
			$scope.productions[index].editActive = !$scope.productions[index].editActive;
		}).error(function(){
			alert("系统内部错误");
		});
	}
});


// 权限管理
// 角色管理 controller
hjcr.controller('roleCtrl',function($scope,$http){
	$scope.showModal = false;
	// 获取角色表和权限表
	$http.get(getRoleURL)
		.success(function(response){
			$scope.roles=response.resultParm.roleList;
		}).error(function(){
			alert("错误！请刷新重试。");
	});
	$http.get(getPrivilegeURL)
		.success(function(response){
			$scope.privileges=response.resultParm.privilegeList;
		}).error(function(){
			alert("错误！请刷新重试。");
	});
	// 显示权限
	$scope.showPrivilegeFuc = function(index){
		$scope.roles[index].editActive = !$scope.roles[index].editActive;
	}
	//显示删除确认框
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
				console.log(response);
				$scope.showModal = !$scope.showModal;
				alert(response.resultInfo);
				console.log($scope.deleteRoleId);
			}).error(function(){
				$scope.showModal = !$scope.showModal;
				alert("系统内部错误");
		});
	}
	// 添加角色
	$scope.showRoleModel = false;
	$scope.addRole = function(){
		$scope.showRoleModel = !$scope.showRoleModel;
	}
	$scope.submitRole = function(){
		var formElement = document.getElementById("roleForm");
		var xhr = new XMLHttpRequest();
    xhr.onload=function(event)
    {
      if  ( ( xhr.status >= 200 && xhr.status < 300) || xhr.status == 304)   //上传成功
      {
        console.log("添加成功！");
      }
      else
      {
        console.log("添加失败！");
      }
    };
		xhr.open("POST", addRoleURL,true);
		xhr.send(new FormData(formElement));
		$scope.showRoleModel = !$scope.showRoleModel;
	}
	// 修改角色名字和描述
	$scope.updateRoleModel = false;
	$scope.updateRoleName = function(id){
		$scope.updateRoleModel = !$scope.updateRoleModel;
		$scope.updateRoleId = id;
	}
	$scope.sureUpdate = function(){
		$http.post(updateRoleNameURL,{
			roleId:$scope.updateRoleId,
			roleName:$scope.roleName,
			roleDescribe:$scope.roleDescribe
		})
		.success(function(response){
			alert("删除成功！");
			console.log(response);
			console.log($scope.updateRoleId);
			console.log($scope.roleName);
			console.log($scope.roleDescribe);
		}).error(function(){
			alert("系统内部错误");
		});
		$scope.updateRoleModel = !$scope.updateRoleModel;
	}
	// 修改 角色权限
	$scope.updateRole = function(id,privilege){
		$http.post(updateRoleURL,{
				roleId:id,
				privilegeId:privilege
			})
			.success(function(response){
				console.log(response);
				console.log(id + ' ' + privilege);
				var edit = new Array();
				for (var i = 0; i < $scope.roles.length; i++) {
					edit[i] = $scope.roles[i].editActive;
				}
				$http.get(getRoleURL)
					.success(function(response){
						$scope.roles=response;
						for (var i = 0; i < $scope.roles.length; i++) {
							$scope.roles[i].editActive = edit[i];
						}
					}).error(function(){
						alert("错误！请刷新重试。");
				});
			}).error(function(){
				alert("系统内部错误");
		});
	}
});
// 用户管理 controller
hjcr.controller('userCtrl',function($scope,$http){
	// 获取角色表和权限表
	$http.get(getUserURL)
		.success(function(response){
			$scope.users=response.resultParm.userList;
		}).error(function(){
			alert("错误！请刷新重试。");
	});
	$http.get(getRoleURL)
		.success(function(response){
			$scope.roles=response.resultParm.roleList;
		}).error(function(){
			alert("错误！请刷新重试。");
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
			alert(response.resultInfo);
			console.log(response);
			console.log($scope.deleteUserId);
		}).error(function(){
			alert("系统内部错误");
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
				alert(response.resultInfo);
				$http.get(getUserURL)
					.success(function(response){
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
			roleId:$scope.users[index].newuserRole
		})
		.success(function(response){
			$scope.users[index].userRole = $scope.users[index].newuserRole.roleName;
			alert(response.resultInfo);
			console.log(response);
		}).error(function(){
			alert("系统内部错误");
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
			$scope.user=response;
			$scope.user.userId = sessionStorage.userId;
		}).error(function(){
			alert("请求未得到服务器响应，请稍后重试...");
	});
	// 自动调用获取第一页订单信息
	$scope.getPageMyBill(1,sessionStorage.userId);
});
