hjcr.directive('pagination',function(){
  return{
    restrict:'E',
    scope:{
      currentPage:'@',
      totalPage:'='
    },
    link:function($scope,element,attrs){
    },
    controller:function($scope, $element, $attrs){
      $scope.index = 0;
      $scope.pages =[];
      $scope.$watch('totalPage',function(){
        if($scope.totalPage<=5){
          for (var i = 0; i < $scope.totalPage; i++) {
            $scope.pages[i] = i+1;
          }
        }
        $scope.$watch('index',function(){
          if($scope.totalPage>5){
            for (var i = 0; i < 5; i++) {
              $scope.pages[i] = $scope.index+i+1;
            }
          }
        })
      });
      $scope.$watch('currentPage',function(newValue, oldValue){
        $scope.currentPage = newValue;
        if($scope.currentPage<$scope.pages[0]){

        }
        else if ($scope.currentPage>$scope.pages[4]) {

        }
      });
      $scope.prev = function(){
        if($scope.currentPage>1){
          $scope.currentPage--;
          if($scope.pages[0]!=1)
          {$scope.index--;}
        }
      }
      $scope.next = function(){
        if ($scope.currentPage<$scope.totalPage) {
          $scope.currentPage++;
          if($scope.pages[4] != $scope.totalPage)
          {$scope.index++;}
        }
      }
      $scope.toFirst = function(){$scope.currentPage = 1;$scope.index=0;}
      $scope.toEnd = function(){$scope.currentPage = $scope.totalPage;$scope.index=$scope.totalPage-5;}
      $scope.prevSize = function(){
        if($scope.pages[0]-4<0){
          $scope.index = 0;
        }
        $scope.index = $scope.pages[0]-4;
        $scope.currentPage = $scope.index+1;
      }
      $scope.nextSize = function(){
        $scope.index = $scope.pages[0]+4;
        $scope.currentPage = $scope.index+1;
      }
    },
    template:
      '<ul  class="pagination" style="margin-top:20px;">'+
        '<li ng-click="toFirst()"><a>首页</a></li>'+
        '<li ng-click="prevSize()"><a><i class="fa fa-backward"></i></a></li>'+
        '<li ng-click="prev()"><a><i class="fa fa-chevron-left"></i></a></li>'+
        '<li ng-class="currentPage==page?\'active\':\'\'" ng-repeat="page in pages"><a>{{page}}</a></li>'+
        '<li ng-click="next()"><a><i class="fa fa-chevron-right"></i></a></li>'+
        '<li ng-click="nextSize()"><a><i class="fa fa-forward"></i></a></li>'+
        '<li ng-click="toEnd()"><a>尾页</a></li>'+
      '</ul>'
  }
});
