var servicesContext = '/portal/service';

function GetTags($scope, $http){
$http({method: 'GET', url: servicesContext + '/tag'}).
	  success(function(data, status, headers, config) {
		$scope.listOfTags = data;
	  }); 
	};
	
	function AddTag(name, description, type, $scope, $http){
	$http.post(servicesContext + '/tag', {name: name, description: description, type: {id:type}}).
	  success(function(data, status, headers, config) {
		$scope.getListOfTags();
		alert("Dodano tag");
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	}); 
	};
	
	function EditTag(id, name, description, type, $scope, $http){
$http.put(servicesContext + '/tag/' + id, {name: name, description: description, type: {id:type}}).
	  success(function(data, status, headers, config) {
		$scope.getListOfTags();
		alert("Edytowano tag");
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	}); 
	};
	
	function DeleteTag(id, showAlert, $scope, $http){
$http.delete(servicesContext + '/tag/' + id).
	  success(function(data, status, headers, config) {
		$scope.deleteTagSuccess += 1;
		
		if (showAlert)
		{
			$scope.getListOfTags();
			if ($scope.deleteTagSuccess == 1)
			{
				alert("Usunięto tag");
			}
			else
			{
				alert("Usunięto tagi");
			}
				
			$scope.deleteTagSuccess = 0;
		}
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	}); 
	};
	
function GetTagTypes($scope, $http){
$http({method: 'GET', url: servicesContext + '/type'}).
	  success(function(data, status, headers, config) {
		$scope.listOfTagsTypes = data;
	  }); 
	};
	
function AddTagType(name, description, $scope, $http){
	$http.post(servicesContext + '/type', {name: name, description: description}).
	  success(function(data, status, headers, config) {
		$scope.getListOfTagsTypes();
		alert("Dodano typ tagu");
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	}); 
};
	
function EditTagType(id, name, description, $scope, $http){
$http.put(servicesContext + '/type/' + id, {name: name, description: description}).
	  success(function(data, status, headers, config) {
		$scope.getListOfTagsTypes();
		alert("Edytowano typ tagu");
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	}); 
};

function DeleteTagType(id, showAlert, $scope, $http){
$http.delete(servicesContext + '/type/' + id).
	  success(function(data, status, headers, config) {
		$scope.deleteTagTypeSuccess += 1;
		
		if (showAlert)
		{
			$scope.getListOfTagsTypes();
			if ($scope.deleteTagTypeSuccess == 1)
			{
				alert("Usunięto tag");
			}
			else
			{
				alert("Usunięto tagi");
			}
				
			$scope.deleteTagTypeSuccess = 0;
		}
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	}); 
};

function GetArticleRank($scope, $http){
$http({method: 'GET', url: servicesContext + '/articleRank'}).
	  success(function(data, status, headers, config) {
		$scope.listOfArticleRanks = data;
	  }); 
	};
	
function AddArticleRank(name, description, $scope, $http){
	$http.post(servicesContext + '/articleRank', {name: name, description: description}).
	  success(function(data, status, headers, config) {
		$scope.getlistOfArticleRanks();
		alert("Dodano rangę artykułu");
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	}); 
};
	
function EditArticleRank(id, name, description, $scope, $http){
$http.put(servicesContext + '/articleRank/' + id, {name: name, description: description}).
	  success(function(data, status, headers, config) {
		$scope.getlistOfArticleRanks();
		alert("Edytowano rangę artykułu");
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	}); 
};

function DeleteArticleRank(id, showAlert, $scope, $http){
$http.delete(servicesContext + '/articleRank/' + id).
	  success(function(data, status, headers, config) {
		$scope.deleteArticleRankSuccess += 1;
		
		if (showAlert)
		{
			$scope.getlistOfArticleRanks();
			if ($scope.deleteArticleRankSuccess == 1)
			{
				alert("Usunięto rangę artykułu");
			}
			else
			{
				alert("Usunięto rangi artykułu");
			}
				
			$scope.deleteArticleRankSuccess = 0;
		}
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	}); 
};
	
function GetCategories($scope, $http){
$http({method: 'GET', url: servicesContext + '/category'}).
	  success(function(data, status, headers, config) {
		$scope.listOfCategories = data;

		for(var i = 0; i < $scope.listOfCategories.length; i++) {
			$scope.listOfCategories[i]["parent"] = null;

				for(var j = 0; j < $scope.listOfCategories[i].children.length; j++) {
					$scope.listOfCategories[i].children[j]["parent"] = $scope.listOfCategories[i].id;
				}
		}
	  }); 
	};
	
function AddCategory($scope, $http){

var name = $scope.categoryForModal.name;
var parent = $scope.categoryForModal.parent;
var descr = $scope.categoryForModal.description;

if (parent == "null") parent = null;

$http({method: 'POST', url: servicesContext + '/category', data: {name: name, description: descr, parentId: parent}}).
	  success(function(data, status, headers, config) {
		$scope.getlistOfCategories();
		alert("Dodano kategorię");
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
		});
	};
	
function EditCategory(id, name, description, parent, $scope, $http){

if (parent == "null") parent = null;

$http({method: 'PUT', url: servicesContext + '/category/' + id, data: {name: name, description: description, parentId: parent}}).
	  success(function(data, status, headers, config) {
		$scope.getlistOfCategories();
		alert("Edytowano kategorię");
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	});
	};
	
function DeleteCategory(id, $scope, $http){
$http({method: 'DELETE', url: servicesContext + '/category/' + id}).
	  success(function(data, status, headers, config) {

	  	var idx = $scope.GetIndexOfCategoryById(id);

		if (idx > -1) {
			$scope.listOfCategories.splice(idx,1);  
		}

		$scope.getlistOfCategories();
		alert("Usunięto kategorię");
	  }); 
	};

function GetCommentsWithStatus($scope, $http, page){
$http({method: 'GET', url: servicesContext + '/comment?status=1&limit=' + $scope.commentsLimit+ '&pageNo='+$scope.commentsPage+'&sortOrder=DESC'}).
	  success(function(data, status, headers, config) {
		$scope.listOfCommentsWithStatus = data.comments;
		$scope.total = data.size;
		console.log($scope.page + ' ' + $scope.commentsPage);

		$scope.commentsPage = page;
	  }); 
	};

function SetCommentState($scope, $http, commentList){
	
	var listWrapper = {comments: null};
	listWrapper.comments = commentList;
	
	$http({method: 'PATCH', url: servicesContext + '/setCommentStatus', 
			data: listWrapper}).
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
	$http.get(servicesContext + '/articlesAll').
	  success(function(data, status, headers, config) {
	  	$scope.listOfArticles = data;
	  }); 
}

function GetImages($scope, $http) {
	$http.get(servicesContext + '/images').
	  success(function(data, status, headers, config) {
		var k = 0;

		for (var i = 0; i < data.length; i = i + 5)
		{
			var tempList = [];
			for (var j = 0; j < 5; ++j)
				tempList[j] = data[i+j]
			
			$scope.listOfImages[k] = tempList;
			++k;
		}
				
	  }); 
}

function GetArticle(articleId, $scope, $http){
	$http.get(servicesContext + '/article/' + articleId).
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
	$http.post(servicesContext + '/article', {title: title, description: description, content: content, categoryId: category_id, userId: 1, expirationDate: expiration_date, publicationDate: publication_date, tags: tags, rankId: rank, imageId: image}).
	  success(function(data, status, headers, config) {
		alert("Zapisano artykuł");
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	}); 
};

function EditArticle(id, title, description, content, category_id, user, expiration_date, publication_date, tags, rank, image, galery, date, $scope, $http){
	$http.put(servicesContext + '/article/' + id, {title: title, description: description, content: content, categoryId: category_id, userId: 1, expirationDate: expiration_date, publicationDate: publication_date, tags: tags, rankId: rank, imageId: image}).
	  success(function(data, status, headers, config) {
		alert("Zapisano artykuł");
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	}); 
};


function GetGroups($scope, $http) {
	$http({method: 'GET', url: servicesContext + '/group'}).
	  success(function(data, status, headers, config) {
	  	$scope.groups = data;
	  }); 
}

function AddNewGroup($scope, $http) {
	$http({method: 'POST', url: servicesContext + '/group',
			data: {'name' : $scope.groupForModal.name, 'description' : $scope.groupForModal.description} }).
	  success(function(data, status, headers, config) {
	  	$scope.groups.push(data);
		alert("Dodano grupę");
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	});
}

function UpdateGroup($scope, $http, id) {
		$http({method: 'PUT', url: servicesContext + '/group/' + id, 
				data: {'name' : $scope.groupForModal.name, 'description' : $scope.groupForModal.description} }).
	  success(function(data, status, headers, config) {
	  	var ix = $scope.GetIndexOfGroupById(id);
	  	$scope.groups(ix).id = data[id];
	  	$scope.groups(ix).name = data[name];
	  	$scope.groups(ix).description = data[description];
		alert("Edytowano grupę");
	  }).
	  error(function(data, status, headers, config) {
		alert("Błąd " + status);
	});
}

function RemoveGroup($scope, $http, id) {
	$http({method: 'DELETE', url: servicesContext + '/group/' + id}).
	  success(function(data, status, headers, config) {
				var index = $scope.GetIndexOfGroupById(id);
				var i = $scope.groups.indexOf(index);

				if (i > -1) {
					$scope.groups.splice(i, 1);
				}
				alert("usunięto grupę");
	  }); 
}

function GetUsers($scope, $http, limit, pageNO){
	$http.get(servicesContext + '/user?limit=' + limit + '&pageNo=' + pageNO + '&sortBy=id&sortOrder=ASC').
		success(function(data, status, headers, config) {
			$scope.listOfUsers = data.users;
			$scope.totalUsers = data.size;
			$scope.usersPage = pageNO;		

			for(var i = 0; i < $scope.listOfUsers.length; i++) {
				$scope.listOfUsers[i].group.selected = $scope.listOfUsers[i].group.id;
			}	

		});			
}

function GetUserByLogin($scope, $http, login){
	$http.get(servicesContext + '/user/' + login).
		success(function(data, status, headers, config) {
			$scope.user = data;			
		});
		
}

function DeleteUser($scope, $http, login){
	$http.delete(servicesContext + '/user/' + login).
		success(function(data, status, headers, config) {
			$scope.getlistOfUsers(1);
			alert("Usunięto użytkownika");
		});
}

function SetUserGroup($scope, $http, user, groupId){
	$http({method: 'PATCH', url: servicesContext + '/setUserGroup/' + user.login, 
			data: {id : groupId}}).
	  success(function(data, status, headers, config) {	
	  	$scope.listOfUsers[$scope.getUserPosById(user.id)].group.id = groupId;
	  	
	  	alert("Użytkownik został przeniesiony do grupy: "+$scope.groups[groupId-1].name);
	  }); 
}

function GetCurrentUserLogin($scope, $http){
	$http.get(servicesContext + '/getCurrentUser').
	  success(function(data, status, headers, config) {
		$scope.userLoggedIn = true;		
		$scope.currentUserLogin = data.login;
		if (data.group) {
			$scope.currentUserGroup = data.group.id;
		}
	  }).
	  error(function(data, status, headers, config) {
		$scope.userLoggedIn = false;
		window.location.href = '/portal';
	}); 
};
function LoginExists($scope, $http, login){
	
	$http.post(servicesContext + '/loginExists', {login: login}).
	  success(function(data, status, headers, config) {	  	
		$scope.loginE = data;		
		if(data == 'true'){
			$scope.uniqueL = false;
	  	} else {	  		
	  		$scope.uniqueL = true;			  		
	  	}
	  });
};

function EmailExists($scope, $http, email){
	$http.post(servicesContext + '/emailExists', {email: email}).
	  success(function(data, status, headers, config) {
		$scope.emailE = data;

		if(data == 'true'){
			$scope.uniqueE = false;		
	  	} else {	  		
	  		$scope.uniqueE = true;
	  	}		
	  });
};

function Register(login, pass, email, firstname, lastname, city, gender, $scope, $http){
	
	pass = Math.random().toString(36).slice(-8);
	$scope.waitingForResponse = true;
	$http.post(servicesContext + '/createUser', {login: login, password: pass, email: email, firstname: firstname, city: city, gender: gender}).
	success(function(data, status, headers, config) {
		$scope.Registered = status == "201";
		$scope.waitingForResponse = false;
		
		alert("Utworzono nowe konto użytkownika");		
  }).
  error(function(data, status, headers, config) {
	$scope.waitingForResponse = false;
	$scope.Registered = false;

	alert("Ups, coś poszło nie tak.");

  });
};
