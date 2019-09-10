'use strict';

angular.module('flightApp').controller('ReservationListController', [
    'initialFlightReservationsData','InitialHotelReservationsData', '$rootScope', '$state','$stateParams',
    function (initialFlightReservationsData,InitialHotelReservationsData, $rootScope, $state, $stateParams) {

        var self = this;
        self.flightReservationsList = initialFlightReservationsData.flightReservationsList;
        self.hotelReservationsList = InitialHotelReservationsData.hotelReservationsList
        self.cancelationEnabled = cancelationEnabled;
        self.cancelReservation = cancelReservation;
        
        function cancelationEnabled(resId){
            var nowDate = new Date();
            nowDate.setDate(nowDate.getDate()-2);
            for(var i = 0; i < self.flightReservationsList.length; i++){
                if(self.flightReservationsList[i].reservationId == resId){
                    var takeoffDate = new Date(self.flightReservationsList[i].seats[0].flight.takeoff);
                    
                    if(nowDate > takeoffDate || self.flightReservationsList[i].status == "CANCELED" || self.flightReservationsList[i].status == "PENDING"){
                        return false;
                    }

                }
                
            }

            return true;
        }

        function cancelReservation(reservationId){
            AvioService.cancelReservation(reservationId)
                    .then(function (response) {
                        console.log("Response of the cancelation:", response);
                        for(var i = 0; i < self.flightReservationsList.length; i++){
                            if(self.flightReservationsList[i].reservationId == reservationId){
                                self.flightReservationsList[i].status = "CANCELED";
                            }
                        }
                        
                    }, function (errResponse) {
                        console.log("Error response while canceling:", errResponse);
                    });
        }

    }
]);