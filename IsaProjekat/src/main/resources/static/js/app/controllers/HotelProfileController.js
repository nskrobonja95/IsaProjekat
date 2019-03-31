'use strict';

angular.module('flightApp').controller('HotelProfileController',
    ['$scope', '$rootScope', '$state','initialHotel',
        function ($scope, $rootScope, $state, initialHotel) {

            var self = this;
            self.hotel = initialHotel;

            console.log("HOTEL");
            console.log(self.hotel);
        }
    ]);