(function(){

    console.log("inside app.js");
    var app = angular.module('trafficbuz',[
        'ngRoute',
        'ngCookies'
    ]);

    app.config(function($routeProvider) {
        $routeProvider

         .when("/main",{
                        templateUrl:"main.html",
                        controller:"MainController"
                })
          .when("/login",{
                                 templateUrl:"login.html",
                                 controller:"LoginController"
                         })

        .when("/register",{
                                         templateUrl:"registration.html",
                                         controller:"RegistrationController"
                                 })
        .when("/deviceregistration",{
                templateUrl:"DeviceRegistration.html",
                controller:"DeviceRegistrationController"
        })
        .when("/settings",{
                templateUrl:"settings.html",
                controller:"SettingsController"
        })

        .when("/profile",{
                        templateUrl:"profile.html",
                        controller:"ProfileController"
        })
        .when("/contactus",{
                        templateUrl:"contactus.html",
                        controller:"ContactUsController"
        })

        .when("/inside",{
                        templateUrl:"deviceregistration",
                        controller:"DeviceRegistrationController"
        })

        .otherwise(
        {redirectTo:"/main"});
    });


    app.run(['$rootScope', '$location', '$http','$cookies',
    function ($rootScope, $location, $http,$cookies) {
     console.log("inside app.run");
        $rootScope.globals = $cookies.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            console.log("inside global.currentUser");
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
            }

           ]);



}());