'use strict';

angular.module('flightApp').controller('HotelAdminWelcomeController',
    ['$scope', '$rootScope', '$state', 'HotelService','initalHotelList',
        function ($scope, $rootScope, $state, HotelService, initalHotelList) {
            var self = this;
           
            self.hotelList = initalHotelList.list;

            
        }
    ]);