'use strict';

angular.module('flightApp').controller('EditRoomController',
    ['$scope', '$rootScope', '$state', 'HotelService', 'initialRoom', 'initialHotelServices',
        function ($scope, $rootScope, $state, HotelService, initialRoom, initialHotelServices) {
            var self = this;
            self.room = initialRoom.room;
            self.services = initialHotelServices.hotelServices;
            self.checkedServices = {};
            self.editRoom = editRoom;
            self.editBack = editBack;
            console.log(self.services);
            for(var j=0; j<self.services.length; ++j) {
                var roomService = self.services[j];
                var flag = false;
                for(var i=0; i<self.room.hotelServices.length; ++i) {
                    if(self.room.hotelServices[i].id == self.services[j].id){
                        flag = true
                        break;
                    }
                }
                if(flag)
                    self.checkedServices[roomService.name] = true;
                else
                    self.checkedServices[roomService.name] = false;
                // if(serviceChecked){
                //     obj.services.push(roomService.name);
                // }
            }
            console.log(self.checkedServices);
            function editBack(){
                $state.go("home-abstract.hotel-admin-hotel-profile.admin-rooms-list",{hotelId: self.room.hotel.id});
            }
            function editRoom() {
                console.log(self.room);
                var obj = {};
                obj.id = self.room.id;
                obj.balcony = self.room.balcony;
                obj.description = self.room.description;
                obj.numOfBeds = self.room.numOfBeds;
                obj.prices = [];
                var months = [];
                
                var temp = {};
                temp.id = self.room.prices[0].id;
                temp.price = self.room.prices[0].price;
                temp.from = self.room.prices[0].activeFrom;
                temp.to = self.room.prices[0].activeTo;
                obj.prices.push(temp);

                var temp = {};
                temp.id = self.room.prices[1].id;
                temp.price = self.room.prices[1].price;
                temp.from = self.room.prices[1].activeFrom;
                temp.to = self.room.prices[1].activeTo;
                obj.prices.push(temp);

                var temp = {};
                temp.id = self.room.prices[2].id;
                temp.price = self.room.prices[2].price;
                temp.from = self.room.prices[2].activeFrom;
                temp.to = self.room.prices[2].activeTo;
                obj.prices.push(temp);

                var temp = {};
                temp.id = self.room.prices[3].id;
                temp.price = self.room.prices[3].price;
                temp.from = self.room.prices[3].activeFrom;
                temp.to = self.room.prices[3].activeTo;
                obj.prices.push(temp);

                var temp = {};
                temp.id = self.room.prices[4].id;
                temp.price = self.room.prices[4].price;
                temp.from = self.room.prices[4].activeFrom;
                temp.to = self.room.prices[4].activeTo;
                obj.prices.push(temp);

                var temp = {};
                temp.id = self.room.prices[5].id;
                temp.price = self.room.prices[5].price;
                temp.from = self.room.prices[5].activeFrom;
                temp.to = self.room.prices[5].activeTo;
                obj.prices.push(temp);

                var temp = {};
                temp.id = self.room.prices[6].id;
                temp.price = self.room.prices[6].price;
                temp.from = self.room.prices[6].activeFrom;
                temp.to = self.room.prices[6].activeTo;
                obj.prices.push(temp);

                var temp = {};
                temp.id = self.room.prices[7].id;
                temp.price = self.room.prices[7].price;
                temp.from = self.room.prices[7].activeFrom;
                temp.to = self.room.prices[7].activeTo;
                obj.prices.push(temp);

                var temp = {};
                temp.id = self.room.prices[8].id;
                temp.price = self.room.prices[8].price;
                temp.from = self.room.prices[8].activeFrom;
                temp.to = self.room.prices[8].activeTo;
                obj.prices.push(temp);

                var temp = {};
                temp.id = self.room.prices[9].id;
                temp.price = self.room.prices[9].price;
                temp.from = self.room.prices[9].activeFrom;
                temp.to = self.room.prices[9].activeTo;
                obj.prices.push(temp);

                var temp = {};
                temp.id = self.room.prices[10].id;
                temp.price = self.room.prices[10].price;
                temp.from = self.room.prices[10].activeFrom;
                temp.to = self.room.prices[10].activeTo;
                obj.prices.push(temp);

                var temp = {};
                temp.id = self.room.prices[11].id;
                temp.price = self.room.prices[11].price;
                temp.from = self.room.prices[11].activeFrom;
                temp.to = self.room.prices[11].activeTo;
                obj.prices.push(temp);

                obj.hotelServices = [];
                for(var j=0; j<self.services.length; ++j) {
                    var roomService = self.services[j];
                    var serviceChecked = self.checkedServices[roomService.name];
                    if(serviceChecked){
                        var localService = {};
                        localService.id = roomService.id;
                        localService.name = roomService.name;
                        localService.rate = roomService.rate;
                        localService.charge = roomService.charge;
                        obj.hotelServices.push(localService);
                    }
                }
                console.log(obj);
                HotelService.editRoom(obj)
                    .then(function(response) {
                        if(response.response.status == 400){
                            alert("Reservations exist for this room");
                        }
                        $state.go("home-abstract.hotel-admin-hotel-profile.admin-rooms-list",{hotelId: self.room.hotel.id});
                    }, function(errResponse) {
                        console.log(errResponse);
                    });
            }
        }
    ]);