'use strict';

angular.module('flightApp').controller('HotelController',
    ['$scope', '$rootScope', '$state', 'SearchService', 'HotelService', 'initialDestinationsList', 'initialHotelsList',
        function ($scope, $rootScope, $state, SearchService, HotelService, initialDestinationsList, initialHotelsList) {

            var self = this;
            this.hotelsList = initialHotelsList.hotelsList;
            this.destList = initialDestinationsList.destList;
            self.searchObj = {};
            self.search = search;
            
            console.log(this.hotelsList);

            function search() {
                self.searchObj.checkIn = SearchService.formatDateString(self.checkInDate);
                self.searchObj.checkOut = SearchService.formatDateString(self.checkOutDate);
                self.searchObj.dest = self.searchObj.dest.name;
                console.log(self.searchObj);
                HotelService.search(self.searchObj);
            }
        }
    ]);