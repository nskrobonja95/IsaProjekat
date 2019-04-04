'use strict';

angular.module('flightApp').controller('FlightSearchResultsController', [
    '$scope', '$rootScope', '$state','$stateParams',
    function ($scope, $rootScope, $state, $stateParams) {
        var self = this;
        self.searchRes = JSON.parse($stateParams.flightSearchData);

    }
]);