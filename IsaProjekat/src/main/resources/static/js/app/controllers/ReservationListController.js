'use strict';

angular.module('flightApp').controller('ReservationListController', [
    'initialFlightReservationsData','AvioService', '$rootScope', '$state','$stateParams',
    function (initialFlightReservationsData, AvioService, $rootScope, $state, $stateParams) {

        var self = this;
        self.flightReservationsList = initialFlightReservationsData.flightReservationsList;
        self.ratesList = [{
            value: "1"
        },
        {
            value: "2"
        }
    ]
        self.cancelationEnabled = cancelationEnabled;
        self.cancelReservation = cancelReservation;
        self.rateEnabled = rateEnabled;
        self.rateRes = rateRes;
        function cancelationEnabled(resId){
            var nowDate = new Date();
            nowDate.setDate(nowDate.getDate()-2);
            for(var i = 0; i < self.flightReservationsList.length; i++){
                console.log(self.flightReservationsList[i]);
                if(self.flightReservationsList[i].reservationId == resId){
                    var takeoffDate = new Date(self.flightReservationsList[i].seats[0].flight.takeoff);
                    
                    if(nowDate > takeoffDate || self.flightReservationsList[i].status == "CANCELED" || self.flightReservationsList[i].status == "PENDING"){
                        return false;
                    }

                }
                
            }

            return true;
        }
        function rateEnabled(resId, seatId){
            var nowDate = new Date();
            for(var i = 0; i < self.flightReservationsList.length; i++){
                if(self.flightReservationsList[i].reservationId == resId){
                    if(self.flightReservationsList[i].status=="CANCELED"){
                        return false;
                    }
                    if(self.flightReservationsList[i].flightRate != 0){
                        return false;
                    }
                    for(var j = 0; j < self.flightReservationsList[i].seats.length; j++){
                        if(self.flightReservationsList[i].seats[j].id == seatId){
                            
                            if(nowDate < new Date(self.flightReservationsList[i].seats[j].flight.landing)){
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        function rateRes(resId, flightId){
            if(self.rate == undefined || self.rate == null){
                alert("Please select rate number!");
                return;
            }
            AvioService.rateReservation({
                flightReservationId: resId,
                flightId: flightId,
                rate: self.rate
            })
            .then(function (response) {
                console.log("Response of the rating:", response);
                
                
            }, function (errResponse) {
                console.log("Error response while canceling:", errResponse);
            });

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