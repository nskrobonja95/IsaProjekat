'use strict';

angular.module('flightApp').controller('AddHotelController',
    ['$scope', '$rootScope', '$state', 'HotelService', 'initialDestinationsList',
        function ($scope, $rootScope, $state, HotelService, initialDestinationsList) {
            var self = this;
            console.log("HELLO");
            console.log(initialDestinationsList);
            self.addHotel = addHotel;
            self.destList = initialDestinationsList.destList;

            function addHotel() {
                self.obj.destination = self.selectedDest.name;
                debugger;
                console.log(self.obj);
                HotelService.addHotel(self.obj)
                    .then(function(response) {
                        $state.go("home-abstract.system-admin-hotels");
                    }, function(errResponse) {
                        console.log(errResponse);
                    });
            }
        }
    ]);