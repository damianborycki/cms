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
	
function AddTagType(name, description, $scope, $http){
	$http.post('/portal/type', {name: name, description: description}).
	  success(function(data, status, headers, config) {
		$scope.getListOfTagsTypes();
	  }).
	  error(function(data, status, headers, config) {
	}); 
	};
	
function EditTagType(id, name, description, $scope, $http){
$http.put('/portal/type/' + id, {name: name, description: description}).
	  success(function(data, status, headers, config) {
		$scope.getListOfTagsTypes();
	  }).
	  error(function(data, status, headers, config) {
	}); 
};

function DeleteTagType(id, $scope, $http){
$http.delete('/portal/type/' + id).
	  success(function(data, status, headers, config) {
		$scope.getListOfTagsTypes();
	  }).
	  error(function(data, status, headers, config) {
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
		$scope.listOfCommentsWithStatus = data.comments;
	  }); 
	};

function SetCommentState($scope, $http, commentList){
	$http({method: 'PATCH', url: '/portal/setCommentStatus', 
			data: commentList}).
	  success(function(data, status, headers, config) {

		for (var i = 0; i < commentList.length; i++) {
			var index = $scope.getIndexOfCommentById(commentList[i].id);

			if (index > -1) {
				$scope.listOfCommentsWithStatus.splice(index, 1);
			}
		}
	  }); 
}

function GetGroups($scope, $http) {
	$http({method: 'GET', url: '/portal/group'}).
	  success(function(data, status, headers, config) {
	  	$scope.groups = data;
	  }); 
}

function AddNewGroup($scope, $http) {
	$http({method: 'POST', url: '/portal/group',
			data: {'name' : $scope.groupForModal.name, 'description' : $scope.groupForModal.description} }).
	  success(function(data, status, headers, config) {
	  	$scope.groups.push(data);
	  }); 
}

function UpdateGroup($scope, $http, id) {
		$http({method: 'PUT', url: '/portal/group/' + id, 
				data: {'name' : $scope.groupForModal.name, 'description' : $scope.groupForModal.description} }).
	  success(function(data, status, headers, config) {
	  	var ix = $scope.getIndexOfGroupById(id);
	  	$scope.groups(ix).id = data[id];
	  	$scope.groups(ix).name = data[name];
	  	$scope.groups(ix).description = data[description];
	  });
}