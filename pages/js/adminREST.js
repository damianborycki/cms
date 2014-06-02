function GetTags($http, $scope){
$http({method: 'GET', url: '/portal/tag'}).
	  success(function(data, status, headers, config) {
		$scope.listOfTags = data;
	  }); 
	};