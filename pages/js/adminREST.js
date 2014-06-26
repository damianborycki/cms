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

function GetCommentsWithStatus($scope, $http){
$http({method: 'GET', url: '/portal/comment?status=1&limit=10&pageNo=1&sortOrder=DESC'}).
	  success(function(data, status, headers, config) {
		$scope.listOfCommentsWithStatus = data;
	  }); 
	};