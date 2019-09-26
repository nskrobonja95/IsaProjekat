'use strict';

angular.module('flightApp').controller('AdminFlightsController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'initialFlights','$stateParams',
        function ($scope, $rootScope, $state, AvioService, initialFlights, $stateParams) {
            var self = this;
            console.log(initialFlights);
            self.flights = initialFlights.flights;
            self.avioId = $stateParams.avioId;
        }
    ]);