var app = angular.module("trafficbuz",[]);

app.controller('AppCtrl', function($scope) {
    console.log("inside AppCtrl");
    $scope.user = {};
    $scope.user.emailid = "nouser@nodomain.com";
});