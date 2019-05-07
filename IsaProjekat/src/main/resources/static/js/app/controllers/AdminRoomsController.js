'use strict';

angular.module('flightApp').controller('AdminRoomsController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'initialRooms',
        function ($scope, $rootScope, $state, AvioService, initialRooms) {
            var self = this;
            console.log(initialRooms);
            self.rooms = initialRooms.hotelRooms;
        }
    ]);