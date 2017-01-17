var hjcr = angular.module('hjcr', ['ui.router']);
var auth = function(response) {
	if(response.messageCode == '401') {
		top.location.href = 'http://localhost:4000/noPrivilege';
	} else {
		return true;
	}
	return false;
}
var alertMes = function(data,dataType,iconType){
	Notify(data, 'top-right', '5000', dataType, iconType, true);
}
// 重写http的post和get方法，做到拦截无权限操作
hjcr.run(function($rootScope,$state,$stateParams,$location,$http){
	$rootScope.$state = $state;
	$rootScope.$stateParams = $stateParams;
});

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
	//账单管理
	}).state("billManage",{
		url:"/billManage",
		templateUrl: "html/billManage.html",
	}).state("myBill",{
		url:"/myBill",
		templateUrl: "html/myBill.html",
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
	}).state("welcome",{
		url:"/welcome",
		templateUrl:"html/welcome.html"
	});
});
