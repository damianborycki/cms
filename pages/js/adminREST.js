function GetTags($scope, $http){
$http({method: 'GET', url: '/portal/tag'}).
	  success(function(data, status, headers, config) {
		$scope.listOfTags = data;
	  }); 
	};
	
	function AddTag(name, description, type, $scope, $http){
	$http.post('/portal/tag', {name: name, description: description, type: {id:type}}).
	  success(function(data, status, headers, config) {
		$scope.getListOfTags();
	  }).
	  error(function(data, status, headers, config) {
	}); 
	};
	
	function EditTag(id, name, description, type, $scope, $http){
$http.put('/portal/tag/' + id, {name: name, description: description, type: {id:type}}).
	  success(function(data, status, headers, config) {
		$scope.getListOfTags();
	  }).
	  error(function(data, status, headers, config) {
	}); 
	};
	
	function DeleteTag(id, $scope, $http){
$http.delete('/portal/tag/' + id).
	  success(function(data, status, headers, config) {
		$scope.getListOfTags();
	  }).
	  error(function(data, status, headers, config) {
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

function GetArticleRank($scope, $http){
$http({method: 'GET', url: '/portal/articleRank'}).
	  success(function(data, status, headers, config) {
		$scope.listOfArticleRanks = data;
	  }); 
	};
	
function GetCategories($scope, $http){
$http({method: 'GET', url: '/portal/category'}).
	  success(function(data, status, headers, config) {
		$scope.listOfCategories = data;

		for(var i = 0; i < $scope.listOfCategories.length; i++) {
			$scope.listOfCategories[i]["parent"] = null;

				for(var j = 0; j < $scope.listOfCategories[i].children.length; j++) {
					$scope.listOfCategories[i].children[j]["parent"] = $scope.listOfCategories[i].id;
				}
		}

		console.log($scope.listOfCategories);
	  }); 
	};
	
function AddCategory($scope, $http){

var name = $scope.categoryForModal.name;
var parent = $scope.categoryForModal.parent;
var descr = $scope.categoryForModal.description;

if (parent == "null") parent = null;

console.log(parent);

$http({method: 'POST', url: '/portal/category', data: {name: name, description: descr, parentId: parent}}).
	  success(function(data, status, headers, config) {
		$scope.getlistOfCategories();
	  }); 
	};
	
function EditCategory(id, name, description, parent, $scope, $http){

if (parent == "null") parent = null;

$http({method: 'PUT', url: '/portal/category/' + id, data: {name: name, description: description, parentId: parent}}).
	  success(function(data, status, headers, config) {
		$scope.getlistOfCategories();
	  }); 
	};
	
function DeleteCategory(id, $scope, $http){
$http({method: 'DELETE', url: '/portal/category/' + id}).
	  success(function(data, status, headers, config) {

	  	var idx = $scope.GetIndexOfCategoryById(id);

		if (idx > -1) {
			$scope.listOfCategories.splice(idx,1);  
		}

		$scope.getlistOfCategories();
	  }); 
	};

function GetCommentsWithStatus($scope, $http){
$http({method: 'GET', url: '/portal/comment?status=1&limit=' + $scope.commentsLimit+ '&pageNo='+$scope.commentsPage+'&sortOrder=DESC'}).
	  success(function(data, status, headers, config) {
		$scope.listOfCommentsWithStatus = data.comments;
		$scope.total = data.size;
		$scope.commentsPage = 0;
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

function GetArticles($scope, $http) {
	$http.get('/portal/articles').
	  success(function(data, status, headers, config) {
	  	$scope.listOfArticles = data;
	  }); 
}

function GetArticle(articleId, $scope, $http){
	$http.get('/portal/article/' + articleId).
	  success(function(data, status, headers, config) {
		$scope.newArticle = data;
		$scope.selectedTags = data.tag;
		$scope.newArticle.publication_date = parseDate($scope.newArticle.publication_date);
		$scope.newArticle.expiration_date = parseDate($scope.newArticle.expiration_date);
	  }).
	  error(function(data, status, headers, config) {

	}); 
};

function AddArticle(title, description, content, category_id, user, expiration_date, publication_date, tags, rank, image, galery, date, $scope, $http){
	$http.post('/portal/article', {title: title, description: description, content: content, categoryId: category_id, userId: 1, expirationDate: expiration_date, publicationDate: publication_date, tags: tags, rankId: rank, imageId: image}).
	  success(function(data, status, headers, config) {
	  }).
	  error(function(data, status, headers, config) {
	}); 
};

function EditArticle(id, title, description, content, category_id, user, expiration_date, publication_date, tags, rank, image, galery, date, $scope, $http){
	$http.put('/portal/article/' + id, {title: title, description: description, content: content, categoryId: category_id, userId: 1, expirationDate: expiration_date, publicationDate: publication_date, tags: tags, rankId: rank, imageId: image}).
	  success(function(data, status, headers, config) {
	  }).
	  error(function(data, status, headers, config) {
	}); 
};


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

function RemoveGroup($scope, $http, id) {
	$http({method: 'DELETE', url: '/portal/group/' + id}).
	  success(function(data, status, headers, config) {
				var index = $scope.GetIndexOfGroupById(id);
				var i = $scope.groups.indexOf(index);

				if (i > -1) {
					$scope.groups.splice(i, 1);
				}
	  }); 
}

function GetUsers($scope, $http, pageNO){
	$http.get('/portal/user?limit=50&pageNo=' + pageNO + '&sortBy=id&sortOrder=ASC').
		success(function(data, status, headers, config) {
			$scope.listOfUsers = data.users;						
		});

}

function GetUserByLogin($scope, $http, login){
	$http.get('/portal/user/' + login).
		success(function(data, status, headers, config) {
			$scope.user = data;			
		});
		
}

function DeleteUser($scope, $http, login){
	$http.delete('/portal/user/' + login).
		success(function(data, status, headers, config) {
			$scope.getlistOfUsers(1);
		});
}

function SetUserGroup($scope, $http, login, group){
	$http({method: 'PATCH', url: '/portal/setUserGroup/' + login, 
			data: group}).
	  success(function(data, status, headers, config) {		
	  }); 
}

function GetCurrentUserLogin($scope, $http){
	$http.get('/portal/getCurrentUser').
	  success(function(data, status, headers, config) {
		$scope.userLoggedIn = true;		
		$scope.currentUserLogin = data.login;
		if (data.group) {
			$scope.currentUserGroup = data.group.id;
		}
	  }).
	  error(function(data, status, headers, config) {
		$scope.userLoggedIn = false;
		window.location.href = '/pages';
	}); 
};
