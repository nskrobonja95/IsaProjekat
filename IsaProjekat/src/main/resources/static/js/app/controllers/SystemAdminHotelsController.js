'use strict';

angular.module('flightApp').controller('SystemAdminHotelsController',
    ['$state', 'AvioService', 'initialHotelsList',
        function($state, AvioService, initialHotelsList) {
            var self = this;
            self.hotelsList = initialHotelsList.hotelsList;
            self.goToAddHotelPage = goToAddHotelPage;

            function goToAddHotelPage() {
                $state.go("home-abstract.add-hotel");
            }
        } 
    ]);