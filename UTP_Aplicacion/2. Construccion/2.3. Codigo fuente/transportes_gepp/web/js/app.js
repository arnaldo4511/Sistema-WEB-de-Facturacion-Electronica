var myApp=angular.module("myApp",[]);

myApp.controller("myController",function($scope){
	console.log("In myController ...");
	
	$scope.newUser={};
	$scope.clickedUser={};
	$scope.message="";
	
	$scope.users=[
	{username:"rimon",fullName:"Md. Mamunur Rashid Rimon", email:"rimonmat@gmail.com"},
	{username:"shamin",fullName:"Md. Mamunur Rashid Rimon", email:"rimonmat@gmail.com"},
	{username:"tamim",fullName:"Md. Mamunur Rashid Rimon", email:"rimonmat@gmail.com"}
	];
	$scope.saveUser = function(){
		//console.log($scope.newUser);
		$scope.users.push($scope.newUser);
		$scope.newUser={};
		$scope.message="New User Added Successfull!";
	};
	
	$scope.selectUser = function(user){
		//console.log(user);
		$scope.clickedUser=user;
	};
	
	$scope.updateUser = function(){
		//console.log(user);
		//$scope.clickedUser=user;
		$scope.message="User Update Successfull!";
	};
	
	$scope.deleteUser = function(){
		//console.log(user);
		//$scope.clickedUser=user;
		$scope.users.splice($scope.users.indexOf($scope.clickedUser),1);
		$scope.message="User Deleted Successfull!";
	};
	
	$scope.clearMessage = function(){
		//console.log(user);
		$scope.message="";
	};
	
	
	
});