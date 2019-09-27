'use strict';

angular.module('flightApp').controller('AddServiceController',
    ['$scope', '$rootScope', '$state', 'HotelService','$stateParams',
        function ($scope, $rootScope, $state, HotelService, $stateParams) {
            var self = this;
            self.addService = addService;

            function addService() {
                var obj = {};
                obj.name = self.name;
                obj.charge = self.charge;
                obj.rate = self.rate;
                console.log(obj);
                HotelService.saveService(obj, $stateParams.hotelId)
                    .then(function(response) {
                        $state.go("home-abstract.hotel-admin-hotel-profile.hotel-admin",{hotelId:$stateParams.hotelId});
                    }, function(errResponse) {
                        console.log(errResponse);
                    });
            }
        }
    ]);