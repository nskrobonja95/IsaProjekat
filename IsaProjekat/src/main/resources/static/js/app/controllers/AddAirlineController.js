'use strict';

angular.module('flightApp').controller('AddAirlineController',
    ['$scope', '$rootScope', '$state', 'AvioService',
        function ($scope, $rootScope, $state, AvioService) {
            var self = this;
            self.addAirline = addAirline;

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