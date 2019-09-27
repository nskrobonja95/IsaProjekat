'use strict';

angular.module('flightApp').controller('CreateRoomController',
    ['$scope', '$rootScope', '$state', 'HotelService', 'initialHotelServices','$stateParams',
        function ($scope, $rootScope, $state, HotelService, initialHotelServices, $stateParams) {
            var self = this;
            self.services = initialHotelServices.hotelServices;
            self.addRoom = addRoom;
            self.addRoomBack = addRoomBack;
            self.checkedServices = [];

            function addRoomBack(){
                $state.go("home-abstract.hotel-admin-hotel-profile.admin-rooms-list",{hotelId: $stateParams.hotelId});
            }
            function addRoom() {
                var obj = {};
                obj.description = self.description;
                obj.numOfBeds = self.numOfBeds;
                obj.monthPrices = [];

                var temp = {};
                temp.price = self.prices.january;
                temp.from = '-01-01 00:00:00';
                temp.to = '-01-31 00:00:00';
                obj.monthPrices.push(temp);

                temp = {};
                temp.price = self.prices.february;
                temp.from = '-02-01 00:00:00';
                temp.to = '-02-28 00:00:00';
                obj.monthPrices.push(temp);

                temp = {};
                temp.price = self.prices.march;
                temp.from = '-03-01 00:00:00';
                temp.to = '-03-31 00:00:00';
                obj.monthPrices.push(temp);

                temp = {};
                temp.price = self.prices.april;
                temp.from = '-04-01 00:00:00';
                temp.to = '-04-30 00:00:00';
                obj.monthPrices.push(temp);

                temp = {};
                temp.price = self.prices.may;
                temp.from = '-05-01 00:00:00';
                temp.to = '-05-31 00:00:00';
                obj.monthPrices.push(temp);

                temp = {};
                temp.price = self.prices.june;
                temp.from = '-06-01 00:00:00';
                temp.to = '-06-30 00:00:00';
                obj.monthPrices.push(temp);

                temp = {};
                temp.price = self.prices.july;
                temp.from = '-07-01 00:00:00';
                temp.to = '-07-31 00:00:00';
                obj.monthPrices.push(temp);

                temp = {};
                temp.price = self.prices.august;
                temp.from = '-08-01 00:00:00';
                temp.to = '-08-30 00:00:00';
                obj.monthPrices.push(temp);

                temp = {};
                temp.price = self.prices.september;
                temp.from = '-09-01 00:00:00';
                temp.to = '-09-30 00:00:00';
                obj.monthPrices.push(temp);

                temp = {};
                temp.price = self.prices.october;
                temp.from = '-10-01 00:00:00';
                temp.to = '-10-30 00:00:00';
                obj.monthPrices.push(temp);

                temp = {};
                temp.price = self.prices.november;
                temp.from = '-11-01 00:00:00';
                temp.to = '-11-31 00:00:00';
                obj.monthPrices.push(temp);

                temp = {};
                temp.price = self.prices.december;
                temp.from = '-12-01 00:00:00';
                temp.to = '-12-30 00:00:00';
                obj.monthPrices.push(temp);

                obj.balcony = self.balcony;
                obj.services = [];
                for(var j=0; j<self.services.length; ++j) {
                    var roomService = self.services[j];
                    var serviceChecked = self.checkedServices[roomService.name];
                    if(serviceChecked){
                        obj.services.push(roomService.name);
                    }
                }
                console.log(obj);
                HotelService.saveRoom(obj, $stateParams.hotelId)
                    .then(function(response) {
                        if(response.response.status == 200)
                        $state.go("home-abstract.hotel-admin-hotel-profile.admin-rooms-list",{hotelId: $stateParams.hotelId});
                        else
                            alert("Stani malo covjece prokleti");
                    }, function(errResponse) {
                        console.log(errResponse);
                    });
            }

        }
    ]);