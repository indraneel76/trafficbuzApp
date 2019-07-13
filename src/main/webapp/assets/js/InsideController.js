(function(){
    var app = angular.module("trafficbuz");
    
    var InsideController=function($scope,$location,AuthenticationService){
        console.log("Inside InsideController");
        /*
        $scope.userinfo = AuthenticationService.getCredentials();
        
        console.log("currentUser = "+ $scope.userinfo.username);
       */
    };
    
    app.controller("InsideController",InsideController);
    
    
    
}());