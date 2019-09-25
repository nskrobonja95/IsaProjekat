'use strict';

angular.module('flightApp').controller('AddHotelController',
    ['$scope', '$rootScope', '$state', 'HotelService', 'initialDestinationsList','initialHotelAdminList',
        function ($scope, $rootScope, $state, HotelService, initialDestinationsList, initialHotelAdminList) {
            var self = this;
            console.log("HELLO");
            console.log(initialDestinationsList);
            self.addHotel = addHotel;
            self.destList = initialDestinationsList.destList;
            self.hotelAdmins = initialHotelAdminList.hotelAdmins;
            self.radioAdmin = 'existing';

            function addHotel() {
                if(self.radioAdmin == 'existing'){
                    console.log("Usao je ovde!");
                    self.obj.existingAdminId = self.existingAdminId;
                }
                self.obj.destination = self.selectedDest.name;
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