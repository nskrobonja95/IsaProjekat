'use strict';

angular.module('flightApp').controller('HotelController',
    ['$scope', '$rootScope', '$state','initialHotelsList',
        function ($scope, $rootScope, $state, initialHotelsList) {

            var self = this;
            this.hotelsList = initialHotelsList.hotelsList;
        }
    ]);