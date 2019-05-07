'use strict';

angular.module('flightApp').controller('AddServiceController',
    ['$scope', '$rootScope', '$state', 'HotelService',
        function ($scope, $rootScope, $state, HotelService) {
            var self = this;
            self.addService = addService;

            function addService() {
                var obj = {};
                obj.name = self.name;
                obj.charge = self.charge;
                obj.rate = self.rate;
                console.log(obj);
                HotelService.saveService(obj)
                    .then(function(response) {
                        $state.go("home-abstract.hotel-admin");
                    }, function(errResponse) {
                        console.log(errResponse);
                    });
            }
        }
    ]);