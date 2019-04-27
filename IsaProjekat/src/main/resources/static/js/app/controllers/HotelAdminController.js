'use strict';

angular.module('flightApp').controller('HotelAdminController',
    ['$scope', '$rootScope', '$state', 'hotelData',
        function ($scope, $rootScope, hotelData) {
            var self = this;
            debugger;
            self.hotel = hotelData.hotel;
            console.log("HELLO HOTEL ADMIN");
            console.log(self.hotel);
        }
    ]);