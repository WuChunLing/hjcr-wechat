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
	$scope.template = {
		"templateName":null,
		"templateQrcodeHigh":520/820,
		"templateQrcodeWide":150/450,
		"templateHeadImgHigh":250/820,
		"templateHeadImgWide":180/450,
		"templateQrcodeSize":150/450,
		"templateConfirm":false
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
			auth(response);
			alertMes(response.resultInfo,'info','fa-check');
		}).error(function(){
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
		});
	}
});

//修改二维码 的controller
hjcr.controller('updateQCtrl',function($scope,$http){
	$scope.template = null;
	//显示要修改的模板的原始信息
	$http.post(updateQrcodeURL,{templateId:sessionStorage.templateId})
		.success(function(response){
			auth(response);
			$scope.template = response;
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
			alertMes('请求得不到响应，请稍后刷新重试！','warning','fa-warning');
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
		$http.post(updateQrcodeURL,{template:$scope.template})
		.success(function(response){
  		auth(response);
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
  auth(response);
				$scope.createQImg=response.templatePath;
				$scope.createQ = true;
			}).error(function(){
				alert("请求未得到响应，请稍后重试！");
				$scope.createQ = false;
			});
		}
	 }
});
