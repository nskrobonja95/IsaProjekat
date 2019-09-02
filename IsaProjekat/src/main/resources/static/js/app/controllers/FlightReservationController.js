'use strict';

angular.module('flightApp').controller('FlightReservationController', [
    '$scope', '$rootScope', '$state','$stateParams', 'direct_flight', 'return_flight', 'dir_seats', 'ret_seats',
    function ($scope, $rootScope, $state, $stateParams, direct_flight, return_flight, dir_seats, ret_seats) {
        var self = this;
        self.dir_flight = direct_flight.flight;
        self.ret_flight = return_flight.flight;
        self.dir_seats = dir_seats;
        self.ret_seats = ret_seats;
        // we will store all of our form data in this object
        $scope.formData = {};
        $scope.direct_flight = direct_flight.flight;
        $scope.return_flight = return_flight.flight;
        $scope.dir_seats = dir_seats.seats;
        $scope.ret_seats = ret_seats.seats;
        console.log($scope.dir_seats);
        // console.log(direct_flight);
        // console.log(return_flight);
        // console.log(dir_seats);
        // console.log(ret_seats);
        // console.log(direct_flight.flight == -1);
        // console.log(typeof(return_flight.flight));
        // console.log(return_flight.flight === -1);

        // function to process the form
        $scope.processForm = function() {
            alert('awesome!');
        };
    }
]);