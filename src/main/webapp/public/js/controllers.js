const preURL_project = "/hjcr-wechat/";

// 模板管理  接口
var deleteQrcodeURL = preURL_project + 'deteleTemplate';   										//  删除   模板
var getQrcodeURL = preURL_project+'getAllTemplate';   												//  获取   所有的模板信息
var sureupdateQrcodeURL =  preURL_project + 'getTemplate';  										//  修改   模板
var updateQrcodeURL=preURL_project+'updateTemplate';                                                  //获取模板
var createQrcodeURL = preURL_project + 'getlastqrcode';  										//  生成永久二维码
var newQrcodeURL = preURL_project + 'addTemplate';   													//  新建   模板


// 分润管理  接口
var getAllocationURL = preURL_project + 'getAllocation';   //获取    一级二级代理的分润比例
var getAllVoucherURL = preURL_project + 'getAllVoucher';   //获取    和优惠券面额
var getOrderMoneyURL = preURL_project + 'getOrderMoney';   //获取    商品比例
// 商品比例的 修改和删除
var updataOrderMoneyURL = preURL_project + 'updataOrderMoney';
var deteleOrderMoneyURL = preURL_project + 'deteleOrderMoney';
// 更新一级代理比例
var updatafirstAllocationURL = preURL_project +'updatafirstAllocation';
// 更新二级代理比例
var updatasecondAllocationURL = preURL_project +'updatasecondAllocation';
// 更新一级优惠券
var updateFirstVoucherURL = preURL_project + 'updateFirstVoucher';
// 更新二级优惠券
var updateSecondVoucherURL = preURL_project + 'updateSecondVoucher';


// 权限管理的接口
// 角色
var getPrivilegeURL = preURL_project + 'system/getAllPrivilege';    // 获取 权限表
var getRoleURL = preURL_project + 'system/getAllRole';    			// 获取 角色表
var addRoleURL = preURL_project + 'system/addRole';    			// 新增 角色
var updateRoleURL = preURL_project + 'system/updateRolePrivilege';    // 修改 角色权限
var deleteRoleURL = preURL_project + 'system/deleteRole';    // 删除 角色
// 用户
var getUserURL = preURL_project + 'system/getAllSystemUser';    			// 获取 用户表
var addUserURL = preURL_project + 'system/addSystemUser';    			// 新增  用户
var updateUserURL = preURL_project + 'system/updateUserRole';    // 修改 用户 角色
var deleteUserURL = preURL_project + 'system/deleteSystemUser';    // 删除 用户
//修改个人登录密码的接口
var updatePwdURL = preURL_project + 'system/updatePassword';
// 退出登录
var loginOutURL = preURL_project + 'loginOut';

// 分润记账管理的接口

var getBillURL = preURL_project + 'bill/getAllBill';   	// 按时间段或者不按时间段 查询  第n页订单记录
var getBillMoneyURL = preURL_project + 'bill/getSumConsume'; //按时间段或者不按时间段  获取总订单 的金额信息
var getBillByIdURL = preURL_project + 'bill/getBillbyId';   //通过订单号查询 订单
// 个人分润信息
var getMyBillURL = preURL_project + 'bill/getBillbyUser';   //获取 用户为xx的   第n页的订单记录
var getBillUserURL = preURL_project + 'user/getUser';  //  	获取用户为xx的用户信息

// 提现管理的接口   (3个接口)

	// 按或者不按时间段查询  状态为 xx  的第n页  提现记录
	// 按或者不按时间段查询  状态为 xx  的总金额信息
	var getWithdrawalURL = preURL_project + 'drawrecord/getByStatus';
	var getWithdrawalMoneyURL = preURL_project + 'drawrecord/getStatusTotal';
	// 对待审核的提现记录的操作
	var operationURL = preURL_project + 'drawrecord/update';

// 个人提现记录 的接口 (2个)
var getMyWithdrawalURL = preURL_project + 'drawrecord/getByUserId'; //获取 用户为xx 的 第n页 提现记录
var getMyInfoURL = preURL_project + 'drawrecord/getUserInfo'; //获取 用户为xx 的 用户信息





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
			$scope.tableTitle = "分润账单";
		}else if($location.path() === "/myBill"){
			$scope.tableTitle = "分润账单-用户个人账单";
		}else if($location.path() === "/withdrawalFinish"){
			$scope.tableTitle = "提现账单-已完成";
		}else if($location.path() === "/withdrawalWait"){
			$scope.tableTitle = "提现账单-待审核";
		}else if($location.path() === "/withdrawalReject"){
			$scope.tableTitle = "提现账单-已驳回";
		}else if($location.path() === "/myWithdrawal"){
			$scope.tableTitle = "提现账单-用户个人提现记录";
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
		}else if($location.path() === "/updatePwd"){
			$scope.tableTitle = "修改密码";
		}
	});
	$scope.loginOut = function(){
		$http.get(loginOutURL)
			.success(function(response){
				location.href = '/login';
			}).error(function(){
		});
	}
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
			// $scope.qrcodes.splice($scope.templateIndex,1);
			$http.get(getQrcodeURL)
				.success(function(response){
					auth(response);
					$scope.qrcodes=response.resultParm.allTemplate;
				}).error(function(response){
			});
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
  //确认上传模板
	$scope.submitTemplate = function(){
		if($("#qrcodeImg").width()!=null && $("#qrcodeImg").width()!=0 && $("#qrcodeImg").width()!=undefined){
			template.templateQrcodeSize = $("#qrcodeImg").width()/450;
		}
		var offset = $('.templateImg').offset();
		var offsetQ = $('#qrcodeImg').offset();
		var offsetT = $('#toux').offset();
		// 二维码的 top 和 left 偏移比例
		template.templateQrcodeHigh = (offsetQ.top-offset.top)/820;
		template.templateQrcodeWide = (offsetQ.left-offset.left)/450;
		// 头像的 top 和 left 偏移比例
		template.templateHeadImgHigh = (offsetT.top-offset.top)/820;
		template.templateHeadImgWide = (offsetT.left-offset.left)/450;

		template.templateConfirm = $scope.templateConfirm===true?1:0;
		var templateFormDate = new FormData(document.getElementById("myForm"));
		templateFormDate.append("templateQrcodeHigh",template.templateQrcodeHigh);
		templateFormDate.append("templateQrcodeWide",template.templateQrcodeWide);
		templateFormDate.append("templateHeadImgHigh",template.templateHeadImgHigh);
		templateFormDate.append("templateHeadImgWide",template.templateHeadImgWide);
		templateFormDate.append("templateQrcodeSize",template.templateQrcodeSize);
		templateFormDate.append("templateConfirm",template.templateConfirm==true?1:0);
		var xhr = new XMLHttpRequest();
    xhr.onload=function(event)
    {
      if  ( ( xhr.status >= 200 && xhr.status < 300) || xhr.status == 304)   //上传成功
      {
				alertMes("上传成功!",'success','fa-check');
      }
			else if(xhr.status == 401){
				alertMes("您没有权限，操作失败!",'warning','fa-warning');
			}
      else
      {
        alertMes("上传失败!",'danger','fa-bolt');
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
			$scope.select = $scope.template.templateConfirm==1?true:false;
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
	//确认修改模板
	$scope.submitTemplate = function(){
		$scope.template.templateName=$("#templateName").val();
		$scope.template.templateQrcodeSize = $("#qrcodeImg").width()/450;
		$scope.template.templateConfirm=($scope.select==true?1:0);
		var offset = $('.templateImg').offset();
		var offsetQ = $('#qrcodeImg').offset();
		var offsetT = $('#touxImg').offset();
		// 二维码的 top 和 left 偏移比例
		$scope.template.templateQrcodeHigh = (offsetQ.top-offset.top)/820;
		$scope.template.templateQrcodeWide = (offsetQ.left-offset.left)/450;
		// 头像的 top 和 left 偏移比例
		$scope.template.templateHeadImgHigh = (offsetT.top-offset.top)/820;
		$scope.template.templateHeadImgWide = (offsetT.left-offset.left)/450;
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
	// 获取优惠券
	$http.get(getAllVoucherURL)
		.success(function(response){
	  	auth(response);
			$scope.voucher=response.resultParm.allVoucher;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
	});
	// 获取一二级代理分润比例
	$http.get(getAllocationURL)
		.success(function(response){
	  	auth(response);
			$scope.location=response.resultParm.allocation;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
	});
	// 获取商品比例
	$http.get(getOrderMoneyURL)
		.success(function(response){
	  	auth(response);
			$scope.productions=response.resultParm.orderMoney;
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
		$http.post(updateFirstVoucherURL,{money:save_exFirst})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			$scope.ifEditExFirst = !$scope.ifEditExFirst;
			$scope.voucher[0].voucherMoney = save_exFirst;
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
		$http.post(updateSecondVoucherURL,{money:save_exSecond})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			$scope.ifEditExSecond = !$scope.ifEditExSecond;
			$scope.voucher[1].voucherMoney = save_exSecond;
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
		$http.post(updatafirstAllocationURL,{orderMoneyFirst:save_orderFirst})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			// 获取一二级代理分润比例
			$http.get(getAllocationURL)
				.success(function(response){
			  	auth(response);
					$scope.location=response.resultParm.allocation;
				}).error(function(){
					alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
			});
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
		$http.post(updatasecondAllocationURL,{orderMoneySecond:save_orderSecond})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			// 获取一二级代理分润比例
			$http.get(getAllocationURL)
				.success(function(response){
			  	auth(response);
					$scope.location=response.resultParm.allocation;
				}).error(function(){
					alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
			});
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
		$http.post(deteleOrderMoneyURL,{orderMoneyId:$scope.deleteId})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			$scope.showModal = false;
			// 获取商品比例
			$http.get(getOrderMoneyURL)
				.success(function(response){
			  	auth(response);
					$scope.productions=response.resultParm.orderMoney;
				}).error(function(){
					alertMes('删除失败！','danger','fa-bolt');
			});
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
		$http.post(updataOrderMoneyURL,{
			orderMoneyId:id,
			orderMoneyName:$scope.productions[index].newOrderMoneyName,
			orderMoneyDistribution:$scope.productions[index].newOrderMoneyDistribution/100
		})
		.success(function(response){
  		auth(response);
			alertMes(response.resultInfo,'success','fa-check');
			$http.get(getOrderMoneyURL)
				.success(function(response){
			  	auth(response);
					$scope.productions=response.resultParm.orderMoney;
				}).error(function(){
			});
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
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
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
	});
	$http.get(getPrivilegeURL)
		.success(function(response){
			auth(response);
			$scope.privileges=response.resultParm.privilegeList;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
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
  			auth(response);
				alertMes(response.resultInfo,'info','fa-check');
				$scope.showModal = !$scope.showModal;
				$http.get(getRoleURL)
					.success(function(response){
  					auth(response);
						$scope.roles=response.resultParm.roleList;
					}).error(function(){
				});
			}).error(function(){
				$scope.showModal = !$scope.showModal;
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
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
				alertMes('添加角色成功！','success','fa-check');
				$http.get(getRoleURL)
					.success(function(response){
  					auth(response);
						$scope.roles=response.resultParm.roleList;
					}).error(function(){
				});
      }
			else if(xhr.status == 401){
				alertMes('无权限操作！','danger','fa-bolt');
			}
      else
      {
        alertMes('添加角色失败！','danger','fa-bolt');
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
			});
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
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
						alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
				});
			}).error(function(){
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}
});

// 用户管理 controller
hjcr.controller('userCtrl',function($scope,$http){
	// 获取用户表
	$http.get(getUserURL)
		.success(function(response){
			auth(response);
			alertMes(response.resultInfo,'info','fa-check');
			$scope.users=response.resultParm.userList;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
	});
	// 获取角色表
	$http.get(getRoleURL)
		.success(function(response){
			auth(response);
			alertMes(response.resultInfo,'info','fa-check');
			$scope.roles=response.resultParm.roleList;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
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
					alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
			});
		}).error(function(){
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
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
						alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
				});
				$scope.showAddUser = !$scope.showAddUser;
			}).error(function(){
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
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
			alertMes(response.resultInfo,'info','fa-check');
			$scope.users[index].userRole = $scope.users[index].newuserRole.roleName;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
		$scope.users[index].editActive = !$scope.users[index].editActive;
	}
});


// 分润账单
// 总订单控制器
hjcr.controller('billManageCtrl',function($scope,$http){
	$scope.bills = null;// 总订单记录
	$scope.billMoney = 0;// 总金额信息
	$scope.totalPage = 1;//全部页数
	$scope.currentPage = 0;//当前页码
	$scope.startDate = null;
	$scope.endDate = null;
	$scope.pageArr;// 页码数组
	$scope.size = 2;
	// 生成页码数组
	$scope.getPage = function(num){
		$scope.pageArr = new Array();
		for(var i=0;i<num;i++){
			$scope.pageArr[i] = i+1;
		}
	}

	//获取   第n页的订单记录
	$scope.getBill = function(start,end,num){
		if(num!=$scope.currentPage && num>=1 && num<=$scope.totalPage){
				$http.get(getBillURL,
					{
						params:{
							startDate:start,
							endDate:end,
							currentPage:num-1,
							size:$scope.size
						}
					}
				)
				.success(function(response){
					auth(response);
					$scope.bills=response.resultParm.list;
					$scope.totalPage = response.resultParm.totalPages;
					$scope.currentPage = response.resultParm.currentPage+1;
					$scope.getPage($scope.totalPage);
				}).error(function(){
					alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
			});
		}
	}

	//获取总订单 的金额信息
	$scope.getBillMoney = function(start,end){
		$http.get(getBillMoneyURL,{
				params:{
					startDate:start,
					endDate:end
				}
			})
			.success(function(response){
				auth(response);
				$scope.billMoney=response.resultParm;
			}).error(function(){
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}

	$scope.getBill($scope.startDate,$scope.endDate,1);
	$scope.getBillMoney($scope.startDate,$scope.endDate);

	//通过订单号查询 订单
	$scope.checkBillById = function(id){
		if(id!=null && id!='' && id!=' '){
			$http.get(getBillByIdURL,
				{
					params:{billId:id}
				})
				.success(function(response){
					auth(response);
					$scope.bills=response.resultParm.list;
					$scope.totalPage = 1;
					$scope.currentPage = 0;
					$scope.getPage($scope.totalPage);
					$scope.billMoney.total=$scope.bills[0].billMoney;
					$scope.billMoney.SumFeeSplittingtotal=$scope.bills[0].billProfit;
					alertMes(response.resultInfo,'info','fa-info-circle');
				}).error(function(){
					alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
			});
		}
	}
	$scope.keyupId = function(){
		if(window.event.keyCode == 13){
			$scope.checkBillById($scope.checkbillId);
		}
	}

	//通过时间段查询 第n页的  订单
	$scope.checkByDate = function(){
		var date = $("#reservation").val();
		if(date!=''&&date!=null){
			var dateArr = date.split(" 至 ");
			$scope.currentPage = 0;
			$scope.startDate = dateArr[0];
			$scope.endDate = dateArr[1] + " 24:00:00";
			$scope.getBill($scope.startDate,$scope.endDate,1);
			$scope.getBillMoney($scope.startDate,$scope.endDate);
		}
	}
	$scope.keyupDate = function(){
		if(window.event.keyCode == 13){
			$scope.checkByDate();
		}
	}
	// 返回主页面
	$scope.backTo = function(){
		$scope.currentPage = 0;
		$scope.startDate = null;
		$scope.endDate = null;
		$scope.getBill(null,null,1);
		$scope.getBillMoney(null,null);
	}
	$scope.checkMyBill = function(id){
		sessionStorage.checkBillById = id;
	}
});
// 个人订单控制器
hjcr.controller('myBillCtrl',function($scope,$http){
	$scope.id = sessionStorage.checkBillById;
	$scope.bills = null;// 总订单记录
	$scope.totalPage = 1;//全部页数
	$scope.currentPage = 0;//当前页码
	$scope.pageArr;// 页码数组
	$scope.size = 2;
	// 生成页码数组
	$scope.getPage = function(num){
		$scope.pageArr = new Array();
		for(var i=0;i<num;i++){
			$scope.pageArr[i] = i+1;
		}
	}

	// 获得用户id为id 的 第n页的订单信息  的方法
	$scope.getPageMyBill = function(num,id){
		if(num!=$scope.currentPage && num>=1 && num<=$scope.totalPage){
				$http.get(getBillURL,
					{
						params:{
							userId:id,
							currentPage:num-1,
							size:$scope.size
						}
					}
				)
				.success(function(response){
					auth(response);
					$scope.bills=response.resultParm.list;
					$scope.totalPage = response.resultParm.totalPages;
					$scope.currentPage = response.resultParm.currentPage+1;
					$scope.getPage($scope.totalPage);
				}).error(function(){
					alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
			});
		}
	}
	// 获得个人信息
	$http.get(getBillUserURL,{
		params:{
				userId:$scope.id
			}
		})
		.success(function(response){
  		auth(response);
			$scope.user=response.resultParm.list;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
	});
	// 自动调用获取第一页订单信息
	$scope.getPageMyBill(1,$scope.id);
	// 返回主页面
	$scope.backTo = function(){
		location.href="/#/billManage";
	}
});
// 用户修改自己的登录密码
hjcr.controller('updatePwdCtrl',function($http,$scope){
	$scope.username = sessionStorage.username;
	$scope.updatePwd = function(){
		if($scope.newPwd === $scope.newPwd_repeat){
			console.log($scope.oldPwd);console.log($scope.newPwd);console.log($scope.newPwd_repeat);
			$http.post(updatePwdURL,{
				username:$scope.username,
				oldPassword:$scope.oldPwd,
				newPassword:$scope.newPwd
			})
			.success(function(response){
  			auth(response);
				alertMes(response.resultInfo,'success','fa-check');
			}).error(function(){
				alertMes("请求未得到响应，请稍后重试！",'warning','fa-warning');
			});
		}
	}
});

// 提现管理的controller
hjcr.controller('recordManageCtrl',function($scope,$http,$location){
	// 提现记录
	$scope.record = {
		"wait":null,
		"finish":null,
		"reject":null
	}
	// 提现金额
	$scope.money = {
		"wait":null,
		"finish":null
		// "reject":null
	}
	// 总页数
	$scope.totalPage = [1,1,1];
	// 页码数组
	$scope.pageArr = new Array(3);
	for (var i = 0; i < 3; i++) {
		$scope.pageArr[i] = new Array();
	}
	// 当前页数
	$scope.currentPage = [0,0,0];

  // 当前时间段，状态
	$scope.startDate = null;
	$scope.endDate = null;
	$scope.status = 1;

	$scope.showModal = false;

	// 获取总金额信息
	// 按时间段或者不按时间段 以及 按状态获取
	$scope.getMoney = function(start,end,status){
		$http.get(getWithdrawalMoneyURL,{
				params:{
					startDate:start,
					endDate:end,
					status:status
				}
			})
			.success(function(response){
				auth(response);
				switch (status) {
					case 1:$scope.money.wait=response.resultParm.total;break;
					case 2:$scope.money.finish=response.resultParm.total;break;
					case 3:$scope.money.reject=response.resultParm.total;break;
				}
			}).error(function(){
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}

	// 获取记录信息
	// 按时间段或者不按时间段  根据状态 获得第n页的提现记录
	$scope.getPage = function(start,end,num,status){
		if(num>=1 && num<=$scope.totalPage[status-1] ){
			$http.get(getWithdrawalURL, {
				params: {
					startDate:start,
					endDate:end,
					currentPage:num-1,
					status:status,
					size:15
				}
			})
				.success(function(response){
					auth(response);
					$scope.totalPage[status-1] = response.resultParm.totalPages;
					$scope.currentPage[status-1] = response.resultParm.currentPage+1;
					switch (status) {
						case 1:$scope.record.wait=response.resultParm.list;break;
						case 2:$scope.record.finish=response.resultParm.list;break;
						case 3:$scope.record.reject=response.resultParm.list;break;
						default:break;
					}
					for(var i=0;i<$scope.totalPage[status-1];i++){
						$scope.pageArr[status-1][i] = i+1;
					}
				}).error(function(){
					alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
			});
		}
	}

	// 按时间段查询订单
	$scope.checkByDate = function(status){
		var date = $("#reservation").val();
		if(date!='' && date!=null){
			var dateArr = date.split(" 至 ");
			$scope.startDate = dateArr[0];
			$scope.endDate = dateArr[1]+" 24:00:00";
			$scope.status = status;
			$scope.getMoney($scope.startDate,$scope.endDate,$scope.status);
			$scope.currentPage=0;
			$scope.getPage($scope.startDate,$scope.endDate,$scope.currentPage,$scope.status);
		}
	}
	$scope.keyupDate = function(status){
		if(window.event.keyCode == 13){
			$scope.checkByDate(status);
		}
	}

	// 查看某个用户的相关提现记录
	$scope.checkMyWithdrawal = function(id){
		sessionStorage.userIdWithdrawal = id;
	}

	// // 返回总订单表
	$scope.backTo = function(status){
		$scope.startDate = null;
		$scope.endDate = null;
		$scope.currentPage = 0;
		$scope.getMoney($scope.startDate,$scope.endDate,$scope.status);
		$scope.getPage($scope.startDate,$scope.endDate,1,$scope.status);
	}

	if($location.path() === "/withdrawalWait"){$scope.status=1;$scope.startDate=null;$scope.endDate=null;}
	if($location.path() === "/withdrawalFinish"){$scope.status=2;$scope.startDate=null;$scope.endDate=null;}
	if($location.path() === "/withdrawalReject"){$scope.status=3;$scope.startDate=null;$scope.endDate=null;}
	$scope.getMoney($scope.startDate,$scope.endDate,$scope.status);
	$scope.getPage($scope.startDate,$scope.endDate,1,$scope.status);

	$scope.closeModel = function(){
		$scope.showModal = !$scope.showModal;
	}

	// 通过提现申请
	$scope.sure = function(name,money,id){
		$scope.showModelTian = {
			"id":id,
			"name":name,
			"money":money,
			"status":true
		}
		$scope.showModal = !$scope.showModal;
	}
	// 拒绝提现申请
	$scope.reject = function(name,money,id){
		$scope.showModelTian = {
			"id":id,
			"name":name,
			"money":money,
			"status":false
		}
		$scope.showModal = !$scope.showModal;
	}
	//提现申请操作的  确认弹框
	$scope.tixianModel = function(){
		var status;
		if($scope.showModelTian.status===true){
			status = 2;
		}
		else {
			status = 3;
		}
		$http.post(operationURL,
			{
				id:$scope.showModelTian.id,
				status:status
			})
			.success(function(response){
				auth(response);
				alertMes(response.resultInfo,'info','fa-info-circle');
				$scope.getMoney($scope.startDate,$scope.endDate,$scope.status);
				$scope.getPage($scope.startDate,$scope.endDate,$scope.currentPage[$scope.status-1],$scope.status);
				$scope.showModal = !$scope.showModal;
			}).error(function(){
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}
});


// 个人提现的 controller
hjcr.controller('myRecordCtrl',function($scope,$http){
	$scope.records = {};
	$scope.userInfo = {};
	$scope.currentPage = 0;
	$scope.totalPage = 1;
	$scope.id = sessionStorage.userIdWithdrawal;
	// 获得用户id为id 的 第n页的订单信息  的方法
	$scope.getPage = function(num,id){
		if(num>=1 && num<=$scope.totalPage ){
			$http.get(getMyWithdrawalURL,{
					params:{
						currentPage:num-1,
						userId:id,
						size:15
					}
				}).success(function(response){
	  				auth(response);
						$scope.records = response.resultParm.list;
						$scope.currentPage = response.resultParm.currentPage+1;
						$scope.totalPage = response.resultParm.totalPages;
						$scope.pageArr = new Array();
						for(var i=0;i<response.resultParm.totalPages;i++){
							$scope.pageArr[i] = i+1;
						}
					}).error(function(){
						alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
			});
		}
	}
	$scope.getMoney = function(){
		// 获得个人信息
		$http.get(getMyInfoURL,{
			params:{
				userId:sessionStorage.userIdWithdrawal
			}
		})
			.success(function(response){
	  		auth(response);
				$scope.user=response.resultParm;
			}).error(function(){
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}

	// 自动调用获取第一页订单信息
	$scope.getPage(1,$scope.id);
	$scope.getMoney();
	$scope.showModal = false;
	$scope.closeModel = function(){
		$scope.showModal = !$scope.showModal;
	}

	// 通过提现申请
	$scope.sure = function(name,money,id){
		$scope.showModelTian = {
			"id":id,
			"name":name,
			"money":money,
			"status":true
		}
		$scope.showModal = !$scope.showModal;
	}
	// 拒绝提现申请
	$scope.reject = function(name,money,id){
		$scope.showModelTian = {
			"id":id,
			"name":name,
			"money":money,
			"status":false
		}
		$scope.showModal = !$scope.showModal;
	}
	//提现申请操作的  确认弹框
	$scope.tixianModel = function(){
		var status;
		if($scope.showModelTian.status===true){
			status = 2;
		}
		else {
			status = 3;
		}
		$http.post(operationURL,
			{
				id:$scope.showModelTian.id,
				status:status
			})
			.success(function(response){
				auth(response);
				alertMes(response.resultInfo,'info','fa-info-circle');
				$scope.getMoney();
				$scope.getPage($scope.currentPage,$scope.id);
				$scope.showModal = !$scope.showModal;
			}).error(function(){
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}
});
