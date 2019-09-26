'use strict';

angular.module('flightApp').controller('HotelFastReservationsController',
    ['$state', 'HotelService', 'fastReservations',
        function($state, HotelService, fastReservations) {
            var self = this;
            console.log(fastReservations);
            self.fastReservations = fastReservations.list;
            self.fastReserve = fastReserve;

            function fastReserve(id) {
                HotelService.fastHotelReserve(id)
                    .then(
                        function(response) {
                            console.log(response);
                            if(response.response.status == 400) {
                                alert("Well, this is embarrasing. This function wont work now. Try again later");
                                state.reload();
                            } else {
                                alert("Succesfully reserved");
                                self.fastReservations = response.response.data;
                            }
                            
                        }
                    );
            }
        } 
    ]);