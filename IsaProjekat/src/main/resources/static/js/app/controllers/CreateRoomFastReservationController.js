'use strict';

angular.module('flightApp').controller('CreateRoomFastReservationController',
    ['$scope', '$rootScope', '$state', 'HotelService', 'SearchService', '$stateParams',
        function ($scope, $rootScope, $state, HotelService, SearchService, $stateParams) {
            var self = this;
            self.roomId = $stateParams.roomId;
            self.saveFastReservation = saveFastReservation;

            function saveFastReservation() {
                var obj = {};
                obj.departingDate = SearchService.formatDateString(self.checkOutDate);
                obj.arrivalDate = SearchService.formatDateString(self.checkInDate);
                obj.discount = self.discount;
                debugger;
                HotelService.createFastReservation(obj, self.roomId)
                    .then(
                        function(response) {
                            debugger;
                            if(response.result.status == 400) {
                                alert("Reservations for this room exist in this period.");
                            } else {
                                alert("Succesfully created!");
                                $state.go("home-abstract.hotel-admin-hotel-profile.hotel-admin",{hotelId:response.result.data});
                            }
                        }
                    );
            }

        }
    ]);