'use strict';

angular.module('flightApp').controller('UserHotelController',
    ['$state', 'HotelService', 'hotelData', 'initialHotelServices',
        function($state, HotelService, hotelData, initialHotelServices) {
            var self = this;
            self.hotel = hotelData.hotel;
            self.hotelServices = initialHotelServices.hotelServices;
            self.goToFastReservationsPage = goToFastReservationsPage;

            function goToFastReservationsPage() {
                debugger;
                $state.go("home-abstract.hotel-fast-reservations", {hotelId: self.hotel.id});
            }
        } 
    ]);