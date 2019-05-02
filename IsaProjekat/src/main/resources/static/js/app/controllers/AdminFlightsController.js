'use strict';

angular.module('flightApp').controller('AdminFlightsController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'initialFlights',
        function ($scope, $rootScope, $state, AvioService, initialFlights) {
            var self = this;
            console.log(initialFlights);
            self.flights = initialFlights.flights;
        }
    ]);