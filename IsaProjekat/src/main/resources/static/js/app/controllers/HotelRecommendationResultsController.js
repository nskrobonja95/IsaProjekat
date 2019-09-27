'use strict';

angular.module('flightApp').controller('HotelRecommendationResultsController', [
    '$scope', '$rootScope', '$state','$stateParams',
    function ($scope, $rootScope, $state, $stateParams) {
        var self = this;
        self.searchRes = JSON.parse($stateParams.hotelSearchData);
        self.flightRes = JSON.parse($stateParams.flightReservations);
        self.flightResJson = $stateParams.flightReservations;
        console.log(self.searchRes);
        console.log(self.flightRes);

    }
]);