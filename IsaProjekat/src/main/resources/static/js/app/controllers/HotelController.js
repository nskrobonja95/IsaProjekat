'use strict';

angular.module('flightApp').controller('HotelController',
    ['$scope', '$rootScope', '$state', 'SearchService', 'initialHotelsList',
        function ($scope, $rootScope, $state, SearchService, initialHotelsList) {

            var self = this;
            this.hotelsList = initialHotelsList.hotelsList;
            self.searchObj = {};
            self.search = search;
            
            console.log(this.hotelsList);

            function search() {
                searchObj.checkIn = SearchService.formatDateString(self.checkInDate);
                searchObj.checkOut = SearchService.formatDateString(self.checkOutDate);
                console.log(searchObj);
            }
        }
    ]);