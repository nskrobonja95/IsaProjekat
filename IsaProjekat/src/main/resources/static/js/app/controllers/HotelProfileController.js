'use strict';

angular.module('flightApp').controller('HotelProfileController',
    ['$scope', '$rootScope', '$state', '$stateParams', 'SearchService', 'HotelService',
    'initialHotelServices', 'initialHotel', 'availableRooms',
        function ($scope, $rootScope, $state, $stateParams, SearchService, HotelService,
            initialHotelServices, initialHotel, availableRooms) {

            var self = this;
            this.hotel = initialHotel.hotel;
            this.availableRooms = availableRooms.availableRooms;
            this.hotelServices = initialHotelServices.hotelServices;
            console.log("SERVICES");
            console.log(initialHotelServices);
            self.search = search;
            self.checkInDate = SearchService.reverseFormatDateString($stateParams.checkIn);
            self.checkOutDate = SearchService.reverseFormatDateString($stateParams.checkOut);

            function search() {
                var checkIn = SearchService.formatDateString(self.checkInDate);
                var checkOut = SearchService.formatDateString(self.checkOutDate);
                HotelService.getAvailableRoomsForHotel(this.hotel.id, checkIn, checkOut)
                    .then(function(response){
                        debugger;
                        self.availableRooms = response.availableRooms;        
                    }, function(errResponse){
                        console.log(errResponse);
                    });
                // debugger;
                
            }

        }
    ]);