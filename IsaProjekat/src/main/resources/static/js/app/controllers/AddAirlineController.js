'use strict';

angular.module('flightApp').controller('AddAirlineController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'initialAvioAdminList',
        function ($scope, $rootScope, $state, AvioService, initialAvioAdminList) {
            var self = this;
            self.avioAdmins = initialAvioAdminList.avioAdmins;
            self.addAirline = addAirline;
            self.radioAdmin = 'existing';
            self.defaultvalue = self.avioAdmins[0].id;
            self.goBack = goBack;

            function goBack(){
                $state.go("home-abstract.system-admin-airlines");
            }
            function addAirline() {
                console.log(self.obj);
                if(self.radioAdmin == 'existing'){
                    console.log("Usao je ovde!");
                    self.obj.existingAdminId = self.existingAdminId;
                }
                AvioService.addAirline(self.obj)
                    .then(function(response) {
                        if(response.response.status==400){
                            alert(response.response.data.errorMessage);
                            return;
                        }
                        $state.go("home-abstract.system-admin-airlines");
                    }, function(errResponse) {
                        console.log(errResponse);
                    })
            }
        }
    ]);