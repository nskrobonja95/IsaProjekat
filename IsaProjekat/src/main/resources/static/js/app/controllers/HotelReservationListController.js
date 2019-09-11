'use strict';

angular.module('flightApp').controller('HotelReservationListController', [
    'initialHotelReservationsData','HotelService', '$rootScope', '$state','$stateParams',
    function (initialHotelReservationsData, HotelService, $rootScope, $state, $stateParams) {

        var self = this;
        
        self.hotelReservationsList = initialHotelReservationsData.hotelReservationsList
        self.cancelationEnabled = cancelationEnabled;
        self.cancelReservation = cancelReservation;
        self.rateEnabled = rateEnabled;
        self.rateRes = rateRes;

        function rateRes(resId){
            HotelService.rateReservation(resId, self.rate)
            .then(function (response) {
                console.log("Response of the rating:", response);
                
                
            }, function (errResponse) {
                console.log("Error response while canceling:", errResponse);
            });

        }

        function rateEnabled(resId){
            console.log("asas");
            var nowDate = new Date();
            for(var i = 0; i < self.hotelReservationsList.length; i++){
                if(self.hotelReservationsList[i].id == resId){
                    if(self.hotelReservationsList[i].status=="CANCELED"){
                        return false;
                    }
                    if(nowDate  < new Date(self.hotelReservationsList[i].arrivalDate)){
                        return false;
                    }
                
                }
            }
            return true;
        }

        function cancelationEnabled(resId){
            
            var nowDate = new Date();
            nowDate.setDate(nowDate.getDate()-2);
            for(var i = 0; i < self.hotelReservationsList.length; i++){
                
                if(self.hotelReservationsList[i].id == resId){
                    var takeoffDate = new Date(self.hotelReservationsList[i].arrivalDate);
                    
                    if(nowDate > takeoffDate || self.hotelReservationsList[i].status == "CANCELED" || self.hotelReservationsList[i].status == "PENDING"){
                        return false;
                    }

                }
                
            }

            return true;
        }

        function cancelReservation(reservationId){
            HotelService.cancelReservation(reservationId)
                    .then(function (response) {
                        for(var i = 0; i < self.hotelReservationsList.length; i++){
                            if(self.hotelReservationsList[i].id == reservationId){
                                self.hotelReservationsList[i].status = "CANCELED";
                            }
                        }
                        
                    }, function (errResponse) {
                        console.log("Error response while canceling:", errResponse);
                    });
        }

    }
]);