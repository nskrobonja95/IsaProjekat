'use strict';

angular.module('flightApp').controller('HotelSearchResultsController', [
    '$scope', '$rootScope', '$state','$stateParams',
    function ($scope, $rootScope, $state, $stateParams) {
        var self = this;
        self.searchRes = JSON.parse($stateParams.hotelSearchData);

    }
]);