'use strict';

angular.module('flightApp').controller('AvioFastReservationsController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'initialFastReservationSeats',
        function ($scope, $rootScope, $state, AvioService, initialFastReservationSeats) {
            var self = this;
            self.fastReservationSeats = initialFastReservationSeats.fastReservations;
            self.fastReserve = fastReserve;

            function fastReserve(ticket) {
                AvioService.makeFastReservation(ticket.id)
                    .then(
                        function(response) {
                            ticket.reserved = true;
                            console.log(response);
                        },
                        function(error) {
                            console.log("Error:". error);
                        }
                    ); 
            }
 
        }
    ]);