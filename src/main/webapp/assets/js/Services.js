'use strict';
angular.module('trafficbuz',['ngCookies'])
.factory('AuthenticationService',
        ['$cookies','$rootScope',
        function($cookies,$rootScope){
            
            console.log("Inside Authentication Service");
            var service={};
            var currentUser ={};
            service.setCredentials = function(username,token){
              console.log("Insde Set Credentails");
                currentUser={currentUser:

                    {username:username,
                    token:token}
                };
                
                $rootScope.globals=currentUser;
                $cookies.put('globals',$rootScope.globals);
                
            };
            
            service.clearCredentials=function()
            {
                console.log("Insde Clear Credentails");
                $rootScope.globals.currentUser={};
                $cookies.remove('globals');
            }
            
            service.getCredentials=function()
            {
                console.log("Insde get Credentails");
                return {username:'NoUser'};
               
            }
            
            return service;
            
        }]);