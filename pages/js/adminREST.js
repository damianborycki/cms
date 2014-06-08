function GetTags($scope, $http){
$http({method: 'GET', url: '/portal/tag'}).
	  success(function(data, status, headers, config) {
		$scope.listOfTags = data;
	  }); 
	};
	
function GetTagTypes($scope, $http){
$http({method: 'GET', url: '/portal/type'}).
	  success(function(data, status, headers, config) {
		$scope.listOfTagsTypes = data;
	  }); 
	};
	
function GetCategories($scope, $http){
$http({method: 'GET', url: '/portal/category'}).
	  success(function(data, status, headers, config) {
		$scope.listOfCategories = data;
	  }); 
	};