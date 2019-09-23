'use strict';

angular.module('flightApp').controller('AvioStatsController', [
    'avioStatisticData', '$rootScope', '$state','$stateParams',
    function (avioStatisticData, $rootScope, $state, $stateParams) {

        var self = this;
        self.stats = avioStatisticData.stats;
       

        

    }
        
]);