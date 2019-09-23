'use strict';

angular.module('flightApp').controller('ChangePasswordController',
    ['$scope', '$rootScope', '$state', 'LoginService',
        function ($scope, $rootScope, $state, LoginService) {
            var self = this;
            self.changePassword = changePassword;
            console.log("HELLO");

            function changePassword() {
                LoginService.changePassword(self.password)
                    .then(function() {
                        LoginService.SetCredentials( $rootScope.globals.currentUser.username, 
                                self.password, $rootScope.globals.currentUser.userType);
                        if($rootScope.globals.currentUser.userType == 'AvioAdmin') {
                            $state.go('home-abstract.avio-admin');
                        } else {
                            $state.go('home-abstract.hotel-admin');
                        }
                    }, function(errResponse) {
                        console.log(errResponse);
                    });
            }

        }
    ]);