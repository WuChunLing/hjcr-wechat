hjcr.directive('pagination',function(){
  return{
    restrict:'E',
    scope:{
      startDate:'@',
      endDate:'@',
      currentPage:'@',
      totalPage:'@',
      status:'@',
      pages:'=',
      getPage:'&'
    },
    link:function($scope,element,attrs){},
    template:
      '<ul  class="pagination" style="margin-top:20px;">'+
        '<li ng-click="getPage(startDate,endDate,currentPage[status-1]-1,status)"  ng-class="currentPage[status-1]===1?\'disabled\':\'\'>《</li>'+
        '<li ng-repeat="page in pages[status-1]" ng-click="getPage(startDate,endDate,page,status)" ng-class="currentPage[status-1]===page?\'active\':\'\'>{{page}}</li>'+
        '<li ng-click="getPage(startDate,endDate,currentPage[status-1]+1,status)"  ng-class="currentPage[status-1]===totalPage[status-1]?\'disabled\':\'\'>》</li>'+
      '</ul>'
  }
});
