'use strict';

angular.module('flightApp').controller('HotelAdminController',
    ['$scope', '$rootScope', '$state', 'hotelData',
        function ($scope, $rootScope, $state, hotelData) {
            var self = this;
            self.hotel = hotelData.hotel;
        }
    ]);