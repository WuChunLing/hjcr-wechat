hjcr.directive('pagination',function(){
  return{
    restrict:'E',
    scope:{
      startDate:'=',
      endDate:'=',
      currentPage:'=',
      totalPage:'=',
      status:'@',
      pages:'=',
      getPage:'&'
    },
    link:function($scope,element,attrs){},
    template:
      '<ul  class="pagination" style="margin-top:20px;">'+
        '<li ng-click="getPage(startDate,endDate,currentPage-1,status)"  ng-class="currentPage===1?\'disabled\':\'\'><a>《</a></li>'+
        '<li ng-repeat="page in pages" ng-click="getPage(startDate,endDate,page,status)" ng-class="currentPage===page?\'active\':\'\'><a>{{page}}</a></li>'+
        '<li ng-click="getPage(startDate,endDate,currentPage+1,status)"  ng-class="currentPage===totalPage?\'disabled\':\'\'><a>》</a></li>'+
      '</ul>'
  }
});
