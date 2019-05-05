'use strict';

angular.module('flightApp').controller('CreateFlightController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'initialDestinations',
        function ($scope, $rootScope, $state, AvioService, initialDestinations) {
            var self = this;
            console.log(initialDestinations);
            self.destinations = initialDestinations.dests;

            self.seatRowNum = 10;
            self.seatColNum = 6;
        }
    ]);