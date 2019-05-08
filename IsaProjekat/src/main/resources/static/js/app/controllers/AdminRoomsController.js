'use strict';

angular.module('flightApp').controller('AdminRoomsController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'initialRooms',
        function ($scope, $rootScope, $state, AvioService, initialRooms) {
            var self = this;
            console.log(initialRooms);
            self.rooms = initialRooms.hotelRooms;
            self.activePriceCheck = activePriceCheck;
            self.todayDate = new Date();

            function activePriceCheck(price) {
                var fromDate = new Date(price.activeFrom);
                var toDate = new Date(price.activeTo);
                return fromDate <= self.todayDate & toDate >= self.todayDate;
            }
        }
    ]);