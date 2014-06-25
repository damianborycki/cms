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
		$scope.mainCategories.data = data[0].children;
	  }).
	  error(function(data, status, headers, config) {
	}); 
};

function GetUser(name, $scope, $http){
	$http.get('/portal/user/' + name).
	  success(function(data, status, headers, config) {
		$scope.searchedUser = data;
	  }).
	  error(function(data, status, headers, config) {

	}); 
};

function GetUserComments(login, page, limit, $scope, $http){
	$http.get('/portal/userComments/' + login + '?limit=' + limit + '&pageNo=' + page + '&sortOrder=DESC').
	  success(function(data, status, headers, config) {
		$scope.userComments = data;
	  }).
	  error(function(data, status, headers, config) {

	}); 
};

function GetArticleComments(articleId, page, limit, $scope, $http){
	$http.get('/portal/articleComments/' + articleId + '?limit=' + limit + '&pageNo=' + page + '&sortOrder=DESC').
	  success(function(data, status, headers, config) {
		$scope.articleComments = data;
	  }).
	  error(function(data, status, headers, config) {

	}); 
};