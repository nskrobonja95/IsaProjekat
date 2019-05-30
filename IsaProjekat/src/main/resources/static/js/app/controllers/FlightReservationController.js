'use strict';

angular.module('flightApp').controller('FlightReservationController', [
    '$scope', '$rootScope', '$state','$stateParams',
    function ($scope, $rootScope, $state, $stateParams) {
        var self = this;
        // we will store all of our form data in this object
    $scope.formData = {};
    
    // function to process the form
    $scope.processForm = function() {
        alert('awesome!');  
    };
    }
]);