function Login(login, pass, $scope, $http){
	$http.post('/portal/login', {login: login, password: pass}).
	  success(function(data, status, headers, config) {
		$scope.userLoggedIn = status == "201";
		if ($scope.userLoggedIn)
			$scope.GetCurrentUserName();
	  }).
	  error(function(data, status, headers, config) {
		$scope.userLoggedIn = false;
	}); 
};

function Logout($scope, $http){
	$http.delete('/portal/logout').
	  success(function(data, status, headers, config) {
		$scope.userLoggedIn = false;
		$scope.currentUserName.data = "";
	  }).
	  error(function(data, status, headers, config) {
		$scope.userLoggedIn = true;
	}); 
};

function Register(login, pass, email, firstname, lastname, city, gender, $scope, $http){
	$scope.waitingForResponse = true;
	$http.post('/portal/user', {login: login, password: pass, email: email, firstname: firstname, city: city, gender: gender}).
	  success(function(data, status, headers, config) {
		$scope.Registered = status == "201";
		$scope.waitingForResponse = false;
	  }).
	  error(function(data, status, headers, config) {
		$scope.waitingForResponse = false;
		$scope.Registered = false;
	});
	//alert("Rejestracja zakończona powodzeniem! Możesz teraz już przejść do logowania."); 
	window.location.href = '/pages';
};

function EditUser(login, info, $scope, $http){
	$http({method: 'PATCH', url: '/portal/user/' + login, data: {login: login, info: info}}).
	  success(function(data, status, headers, config) {
		$scope.getUser(login);
	  }).
	  error(function(data, status, headers, config) {
	});
};

function GetCurrentUserLogin($scope, $http){
	$http.get('/portal/getCurrentUserLogin').
	  success(function(data, status, headers, config) {
		$scope.userLoggedIn = true;
		$scope.currentUserName.data = data.login;
	  }).
	  error(function(data, status, headers, config) {
		$scope.userLoggedIn = false;
	}); 
};

function GetTags($scope, $http){
	$http.get('/portal/tag').
	  success(function(data, status, headers, config) {
		$scope.tags.data = data;
	  }).
	  error(function(data, status, headers, config) {
	}); 
};

function GetMainCategories($scope, $http){
	$http.get('/portal/category').
	  success(function(data, status, headers, config) {
		$scope.mainCategories.data = data;
		$('#topMenu').show();
	  }).
	  error(function(data, status, headers, config) {
	}); 
};

function GetUser(name, $scope, $http){
	$http.get('/portal/userProfile/' + name).
	  success(function(data, status, headers, config) {
		$scope.searchedUser = data;
	  }).
	  error(function(data, status, headers, config) {

	}); 
};

function GetUserComments(userId, page, limit, $scope, $http){
	$http.get('/portal/userComments/' + userId + '?limit=' + limit + '&pageNo=' + page + '&sortOrder=DESC').
	  success(function(data, status, headers, config) {
		$scope.userComments = data.comments;
		$scope.total = data.size;
		$scope.commentsPage = 0;
	  }).
	  error(function(data, status, headers, config) {

	}); 
};

function GetArticleComments(articleId, page, limit, $scope, $http){
	$http.get('/portal/articleComments/' + articleId + '?limit=' + limit + '&pageNo=' + page + '&sortOrder=DESC').
	  success(function(data, status, headers, config) {
		$scope.articleComments = data.comments;
		$scope.total = data.size;
		$scope.commentsPage = 0;
	  }).
	  error(function(data, status, headers, config) {

	}); 
};

function GetArticle(articleId, $scope, $http){
	$http.get('/portal/article/' + articleId).
	  success(function(data, status, headers, config) {
		$scope.article = data;
	  }).
	  error(function(data, status, headers, config) {

	}); 
};

function CommentArticle(login, articleId, content, parent, $scope, $http){
	$scope.waitingForResponse = true;
	$http.post('/portal/comment', {login: login, articleId: articleId, content: content, parent: parent}).
	  success(function(data, status, headers, config) {
		$scope.waitingForResponse = false;
	  }).
	  error(function(data, status, headers, config) {
		$scope.waitingForResponse = false;
	}); 
};

function GetArticlesByRankForSide(rankId, limit, pageNo, sortOrder, sortBy, $scope, $http){
	$http.get('/portal/articlesByRank/' + rankId + '?limit=' + limit + '&pageNo=' + pageNo + '&sortOrder=' + sortOrder + '&sortBy=' + sortBy).
	  success(function(data, status, headers, config) {
		$scope.articlesForRanks[rankId] = data;
	  }).
	  error(function(data, status, headers, config) {
	}); 
};

function GetArticlesByCategoryForMainPage(categoryId, limit, pageNo, sortOrder, sortBy, $scope, $http){
	$http.get('/portal/articlesByCategory/' + categoryId + '?limit=' + limit + '&pageNo=' + pageNo + '&sortOrder=' + sortOrder + '&sortBy=' + sortBy).
	  success(function(data, status, headers, config) {
		$scope.articlesForCategories[categoryId] = data;
	  }).
	  error(function(data, status, headers, config) {
	}); 
};

function GetArticlesByTags(tags, limit, pageNo, sortOrder, sortBy, $scope, $http){
	$http.get('/portal/articlesByTag/' + tags + '?limit=' + limit + '&pageNo=' + pageNo + '&sortOrder=' + sortOrder + '&sortBy=' + sortBy).
	  success(function(data, status, headers, config) {
		$scope.listOfArticles = data;
	  }).
	  error(function(data, status, headers, config) {
	}); 
};

function GetArticlesByCategory(categoryId, limit, pageNo, sortOrder, sortBy, $scope, $http){
	$http.get('/portal/articlesByCategory/' + categoryId + '?limit=' + limit + '&pageNo=' + pageNo + '&sortOrder=' + sortOrder + '&sortBy=' + sortBy).
	  success(function(data, status, headers, config) {
		$scope.listOfArticles = data;
	  }).
	  error(function(data, status, headers, config) {
	}); 
};