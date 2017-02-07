var hjcr = angular.module('hjcr', ['ui.router']);
var auth = function(response) {
	if(response.messageCode == 401) {
		top.location.href = '/noPrivilege';
	} else {
		return true;
	}
	return false;
}
var alertMes = function(data,dataType,iconType){
	if(data!=null){
		Notify(data, 'top-right', '5000', dataType, iconType, true);
	}
}
// // 重写http的post和get方法，做到拦截无权限操作
// hjcr.run(function($rootScope,$state,$stateParams,$location,$http){
// 	$rootScope.$state = $state;
// 	$rootScope.$stateParams = $stateParams;
// });

hjcr.config(function($stateProvider,$urlRouterProvider) {

	$urlRouterProvider.otherwise('/welcome');
	//二维码管理模块路由配置
	$stateProvider.state("checkQrcode",{
		url:"/checkQrcode",
		templateUrl: "html/checkQrcode.html"   //查看模板
	}).state("createQrcode",{
		url:"/createQrcode",
		templateUrl: "html/createQrcode.html",  //生成永久有效二维码
	}).state("newQrcode",{
		url:"/newQrcode",
		templateUrl: "html/newQrcode.html",    //新建模板
	}).state("updateQrcode",{
		url:"/updateQrcode",
		templateUrl: "html/updateQrcode.html",   //修改模板
	//分润记账管理
	}).state("billManage",{
		url:"/billManage",
		templateUrl: "html/billManage.html",
	}).state("myBill",{
		url:"/myBill",
		templateUrl: "html/myBill.html",
	//提现管理
	}).state("withdrawalFinish",{
		url:"/withdrawalFinish",
		templateUrl: "html/withdrawalFinish.html",
	}).state("withdrawalWait",{
		url:"/withdrawalWait",
		templateUrl: "html/withdrawalWait.html",
	}).state("withdrawalReject",{
		url:"/withdrawalReject",
		templateUrl: "html/withdrawalReject.html",
	}).state("myWithdrawal",{
		url:"/myWithdrawal",
		templateUrl: "html/myWithdrawal.html",
 //分润管理
	}).state("profitManage",{
		url:"/profitManage",
		templateUrl: "html/profitManage.html",
 //权限管理
	}).state("roleManage",{
		url:"/roleManage",
		templateUrl: "html/roleManage.html",
	}).state("userManage",{
		url:"/userManage",
		templateUrl: "html/userManage.html",
	}).state("rightsManage",{
		url:"/rightsManage",
		templateUrl: "html/rightsManage.html",
 //数据统计
	}).state("dataStatistic",{
		url:"/dataStatistic",
		templateUrl: "html/dataStatistic.html"
 // 系统管理模块路由配置
	}).state("dataBackup",{
		url:"/dataBackup",
		templateUrl: "html/dataBackup.html"
	}).state("systemLog",{
		url:"/systemLog",
		templateUrl:"html/systemLog.html"
	// 修改登录密码
	}).state("updatePwd",{
		url:"/updatePwd",
		templateUrl:"html/updatePwd.html"
	// 欢迎页面
	}).state("welcome",{
		url:"/welcome",
		templateUrl:"html/welcome.html"
	});
});
hjcr.filter("showStatus",function(){
	return function(inputArray){
		var status = "";
		if(inputArray === 1){
			status ="待审核";
		}else if(inputArray === 2){
			status = "已完成";
		}else if(inputArray === 3){
			status = "已驳回";
		}
		return status;
	}
});
hjcr.filter("showStatusClass",function(){
	return function(inputArray){
		var status = "";
		if(inputArray === 1){
			status ="warning";
		}else if(inputArray === 2){
			status = "success";
		}else if(inputArray === 3){
			status = "danger";
		}
		return status;
	}
});
