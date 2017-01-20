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
var getAllocationURL = preURL_get + 'getAllocation.json';   //获取    一级二级代理的分润比例
var getAllVoucherURL = preURL_get + 'getAllVoucher.json';   //获取    和优惠券面额
var getOrderMoneyURL = preURL_get + 'getOrderMoney.json';   //获取    商品比例
// 商品比例的 修改和删除
var updataOrderMoneyURL = preURL_post + 'updataOrderMoney';
var deteleOrderMoneyURL = preURL_post + 'deteleOrderMoney';
// 更新一级代理比例
var updatafirstAllocationURL = preURL_post +'updatafirstAllocation';
// 更新二级代理比例
var updatasecondAllocationURL = preURL_post +'updatasecondAllocation';
// 更新一级优惠券
var updateFirstVoucherURL = preURL_post + 'updateFirstVoucher';
// 更新二级优惠券
var updateSecondVoucherURL = preURL_post + 'updateSecondVoucher';


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
// 修改个人登录密码的接口
var updatePwdURL = preURL_post + 'updatePwd';
// 退出登录
var loginOutURL = preURL_get + 'loginOut.json';


// 账单管理的接口
var getBillURL = preURL_post + 'getBill';   	//获取   第n页的订单记录
var getBillMoneyURL = preURL_get + 'getBillMoney.json'; //获取总订单 的金额信息

var getBillByIdURL = preURL_post + 'getBillById';   //通过订单号查询 订单
var getBillMoneyByIdURL = preURL_post + 'getBillMoneyById';  // 通过订单号查询的 订单的  总金额信息

var getBillByDateURL = preURL_post + 'getBillByDate';   //通过时间段查询 第n页的  订单
var getBillMoneyByDateURL = preURL_post + 'getBillMoneyByDate';  //通过时间段查询的订单  的总金额信息

var getMyBillURL = preURL_post + 'getMyBill';   //获取 用户为xx的   第n页的订单记录
var getBillUserURL = preURL_post + 'getBillUser';  //  	获取用户为xx的用户信息

// 提现管理的接口
var getWithdrawalURL = preURL_post + 'getWithdrawal'; //获取 状态为xx 的 第n页 提现记录
var getWithdrawalMoneyURL = preURL_post + 'getWithdrawalMoney'; //获取 状态为xx 总金额信息

var getWithdrawalByDateURL = preURL_post + 'getWithdrawalByDate'; //按时间段查询 状态为 xx 的第n页 提现记录
var getMoneyByDateURL = preURL_post + 'getMoneyByDate'; //按时间段查询 状态为xx 的总金额信息

// 个人提现记录
var getMyWithdrawalURL = preURL_post + 'getMyWithdrawal'; //获取 用户为xx 的 第n页 提现记录
var getMyInfoURL = preURL_post + 'getMyWithdrawal'; //获取 用户为xx 的 用户信息



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
			$scope.tableTitle = "账单管理-用户个人账单";
		}else if($location.path() === "/withdrawalFinish"){
			$scope.tableTitle = "提现管理-已完成";
		}else if($location.path() === "/withdrawalWait"){
			$scope.tableTitle = "提现管理-未完成";
		}else if($location.path() === "/withdrawalReject"){
			$scope.tableTitle = "提现管理-已驳回";
		}else if($location.path() === "/myWithdrawal"){
			$scope.tableTitle = "提现管理-用户个人提现记录";
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
		var offsetT = $('#toux').offset();
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
			// $scope.productions[index].orderMoneyName = $scope.productions[index].newOrderMoneyName;
			// $scope.productions[index].orderMoneyDistribution = $scope.productions[index].newOrderMoneyDistribution/100;
			// $scope.productions[index].editActive = !$scope.productions[index].editActive;
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
	// 获取角色表
	$http.get(getRoleURL)
		.success(function(response){
  		auth(response);
			$scope.roles=response.resultParm.roleList;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
	});
	// 获取权限表
	$http.get(getPrivilegeURL)
		.success(function(response){
  		auth(response);
			$scope.privileges=response.resultParm.privilegeList;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
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
				});
			}).error(function(){
				$scope.showModal = !$scope.showModal;
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
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
			$scope.users=response.resultParm.userList;
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
	});
	// 获取角色表
	$http.get(getRoleURL)
		.success(function(response){
			auth(response);
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


// 账单管理
// 总订单控制器
hjcr.controller('billManageCtrl',function($scope,$http){
	$scope.bills = null;// 总订单记录
	$scope.totalPage = 1;//全部页数
	$scope.currentPage = 0;//当前页码
	$scope.billMoney = 0;// 总金额信息
	$scope.pageArr;// 页码数组
	$scope.id = null;
	$scope.date = null;
	// 生成页码数组
	$scope.getPage = function(num){
		$scope.pageArr = new Array();
		for(var i=0;i<num;i++){
			$scope.pageArr[i] = i+1;
		}
	}
	// post请求后常用套路
	$scope.postFuc = function(response){
		auth(response);
		alertMes(response.resultInfo,'info','fa-info-circle');
		$scope.bills=response;
		$scope.totalPage = response.totalPage;
		$scope.currentPage = response.currentPage;
		$scope.getPage($scope.totalPage);
	}

	//获取   第n页的订单记录
	$scope.getBill = function(num){
		$scope.id = null;
		$scope.date = null;
		$http.post(getBillURL,
			{
				page:num,
				pageNum:15
			})
			.success(function(response){
				$scope.postFuc(response);
			}).error(function(){
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}
	$scope.getPageBill = function(num){
		console.log("调用总订单信息"+num);
		if(num!=$scope.currentPage && num>=1 && num<=$scope.totalPage){
			$scope.getBill(num);
		}
	}
	//获取总订单 的金额信息
	$scope.getBillMoney = function(){
		$http.get(getBillMoneyURL)
			.success(function(response){
				$scope.billMoney=response;
			}).error(function(){
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}

	$scope.getPageBill(1);
	$scope.getBillMoney();

	//通过订单号查询 订单
	$scope.checkBillById = function(id){
		$scope.id = id;
		if(id!=null && id!='' && id!=' '){
			$http.post(getBillByIdURL,
				{
					billId:id
				})
				.success(function(response){
					$scope.postFuc(response);
					$scope.billMoney.billMoney=response.bill[0].billMoney;
					$scope.billMoney.billProfit=response.bill[0].billProfit;
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
	$scope.checkBillByDate = function(start,end,page){
			$http.post(getBillByDateURL,{
					startDate:start,
					endDate:end,
					page:page
				})
				.success(function(response){
					$scope.postFuc(response);
				}).error(function(){
					alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
			});
	}
	$scope.checkByDate = function(){
		var date = $("#reservation").val();
		if(date!=''&&date!=null){
			var dateArr = date.split(" 至 ");
			$scope.currentPage = 0;
			$scope.date = true;
			$scope.checkBillByDate(dateArr[0],dateArr[1],1);
			$scope.getMoneyByDate(dateArr[0],dateArr[1]);
		}
	}
	$scope.keykeyupDate = function(){
		if(window.event.keyCode == 13){
			$scope.checkByDate();
		}
	}
	$scope.checkDateByPage = function(num){
		console.log("按日期查询"+num);
		if(num!=$scope.currentPage && num>=1 && num<=$scope.totalPage){
			var date = $("#reservation").val();
			if(date!=''&&date!=null){
				var dateArr = date.split(" 至 ");
				$scope.checkBillByDate(dateArr[0],dateArr[1],num);
			}
		}
	}
	//通过时间段查询的订单  的总金额信息
	$scope.getMoneyByDate = function(start,end){
		$http.post(getBillMoneyByDateURL,{
				startDate:start,
				endDate:end
			})
			.success(function(response){
				$scope.billMoney=response;
			}).error(function(){
		});
	}

	// 返回总订单表
	$scope.backToAllBill = function(){
		$scope.currentPage = 0;
		$scope.getPageBill(1);
		$scope.getBillMoney();
		$scope.id = null;
		$scope.date = null;
	}

	// 页码标志 调用
	$scope.selectPost = function(num,id,date){
		if(id!=null){
			return  false;
		}
		else if(date!=null){
			$scope.checkDateByPage(num);
		}
		else {
			$scope.getPageBill(num);
		}
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
					alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
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
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
	});
	// 自动调用获取第一页订单信息
	$scope.getPageMyBill(1,sessionStorage.userId);
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
		else {
			alertMes('输入的两次密码不一致','warning','fa-warning');
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
		"finish":null,
		"reject":null
	}
	// 总页数
	$scope.totalPage = [1,1,1];
	$scope.pageArr = new Array(3);
	for (var i = 0; i < 3; i++) {
		$scope.pageArr[i] = new Array();
	}
	// 当前页数
	$scope.currentPage = [0,0,0];

	// 根据状态获取 提现的 总金额信息
	$scope.getMoney = function(status){
		$http.post(getWithdrawalMoneyURL,{status:status})
			.success(function(response){
				auth(response);
				switch (status) {
					case 1:$scope.money.wait=response.resultParm.recordMoney.money;break;
					case 2:$scope.money.finish=response.resultParm.recordMoney.money;break;
					case 3:$scope.money.reject=response.resultParm.recordMoney.money;break;
				}
			}).error(function(){
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}
	// 根据状态 获得第n页的提现记录  的方法
	$scope.getPage = function(num,status){
		if(num>=1 && num<=$scope.totalPage[status-1] && num!= $scope.currentPage[status-1]){
			$http.post(getWithdrawalURL,{
					page:num,
					status:status
				})
				.success(function(response){
					auth(response);
					$scope.totalPage[status-1] = response.totalPage;
					$scope.currentPage[status-1] = response.currentPage;
					switch (status) {
						case 1:$scope.record.wait=response.resultParm.recordList;break;
						case 2:$scope.record.finish=response.resultParm.recordList;break;
						case 3:$scope.record.reject=response.resultParm.recordList;break;
						default:break;
					}
					for(var i=0;i<response.totalPage;i++){
						$scope.pageArr[status-1][i] = i+1;
					}
				}).error(function(){
					alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
			});
		}
		console.log(num);
	}
	var data;
	if($location.path() === "/withdrawalWait"){data=1;}
	if($location.path() === "/withdrawalFinish"){data=2;}
	if($location.path() === "/withdrawalReject"){data=3;}
	$scope.getMoney(data);
	$scope.getPage(1,data);

	// 查看某个用户的相关提现记录
	$scope.checkMyWithdrawal = function(id){
		sessionStorage.userIdWithdrawal = id;
	}

	//按 时间段 页码 状态 查询
	$scope.searchDate = function(date,num,status){
		if(num>=1 && num<=$scope.totalPage[status-1] && num!= $scope.currentPage[status-1]){
			$http.post(getWithdrawalByDateURL,{
					date:date,
					page:num,
					status:status
				})
				.success(function(response){
					auth(response);
					$scope.totalPage[status-1] = response.totalPage;
					$scope.currentPage[status-1] = response.currentPage;
					switch (status) {
						case 1:$scope.record.wait=response.resultParm.recordList;break;
						case 2:$scope.record.finish=response.resultParm.recordList;break;
						case 3:$scope.record.reject=response.resultParm.recordList;break;
						default:break;
					}
					for(var i=0;i<response.totalPage;i++){
						$scope.pageArr[status-1][i] = i+1;
					}
				}).error(function(){
					alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
			});
		}
	}
	//按 时间段 状态 查询  提现金额
	$scope.searchMoneyByDate = function(date,status){
		$http.post(getMoneyByDateURL,{
				date:date,
				status:status
			})
			.success(function(response){
				auth(response);
				switch (status) {
					case 1:$scope.money.wait=response.resultParm.recordMoney.money;break;
					case 2:$scope.money.finish=response.resultParm.recordMoney.money;break;
					case 3:$scope.money.reject=response.resultParm.recordMoney.money;break;
				}
			}).error(function(){
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}
	// 按时间段查询订单
	$scope.checkByDate = function(status){
		var date = $("#reservation").val();
		$scope.searchDate(date,1,status);
		$scope.searchMoneyByDate(date,status);
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
				alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}

});
// 个人提现的 controller
hjcr.controller('myRecordCtrl',function($scope,$http){
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
					alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
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
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
	});
	// 自动调用获取第一页订单信息
	$scope.getPageMyBill(1,sessionStorage.userId);
});
