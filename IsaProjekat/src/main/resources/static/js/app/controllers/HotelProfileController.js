'use strict';

angular.module('flightApp').controller('HotelProfileController',
    ['$scope', '$rootScope', '$state', 'initialHotelData',
        function ($scope, $rootScope, $state, initialHotelData) {

            var self = this;
            self.avioCompany = initialHotelData.avio;
            
        }
    ]);