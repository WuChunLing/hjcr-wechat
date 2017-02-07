hjcr.directive('paginationDire',function(){
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
        '<li>88</li>'+
        '<li ng-click="getPage(startDate,endDate,currentPage-1,status)"  ng-class="currentPage===1?\'disabled\':\'\'>《</li>'+
        '<li ng-repeat="page in pages" ng-click="getPage(startDate,endDate,page,status)" ng-class="currentPage===page?\'active\':\'\'>{{page}}</li>'+
        '<li ng-click="getPage(startDate,endDate,currentPage+1,status)"  ng-class="currentPage===totalPage?\'disabled\':\'\'>》</li>'+
      '</ul>'
  }
});
