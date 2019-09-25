'use strict';

angular.module('flightApp').controller('AddAirlineController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'initialAvioAdminList',
        function ($scope, $rootScope, $state, AvioService, initialAvioAdminList) {
            var self = this;
            self.avioAdmins = initialAvioAdminList.avioAdmins;
            self.addAirline = addAirline;
            self.radioAdmin = 'existing';
            self.defaultvalue = self.avioAdmins[0].id;
            function addAirline() {
                console.log(self.obj);
                AvioService.addAirline(self.obj)
                    .then(function(response) {
                        $state.go("home-abstract.system-admin-airlines");
                    }, function(errResponse) {
                        console.log(errResponse);
                    })
            }
        }
    ]);