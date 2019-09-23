'use strict';

angular.module('flightApp').controller('AdminRoomsController',
    ['$scope', '$rootScope', '$state', 'HotelService', 'initialRooms',
        function ($scope, $rootScope, $state, HotelService, initialRooms) {
            var self = this;
            console.log(initialRooms);
            self.rooms = initialRooms.hotelRooms;
            self.activePriceCheck = activePriceCheck;
            self.todayDate = new Date();
            self.goToEditPage = goToEditPage;
            self.removeRoom = removeRoom;

            function activePriceCheck(price) {
                var fromDate = new Date(price.activeFrom);
                var toDate = new Date(price.activeTo);
                return fromDate <= self.todayDate & toDate >= self.todayDate;
            }

            function goToEditPage(room) {
                debugger;
                $state.go("home-abstract.edit-room", {roomId: room.id});
            }

            function removeRoom(room) {
                HotelService.deleteRoom(room.id)
                    .then(function(response) {
                        console.log(response);
                        if(response.response.status == 304) {
                            alert("Reservations exist for this room!");
                            return;
                        }
                        for(var i=0; i<self.rooms.length; ++i){
                            if(self.rooms[i] == room){
                                self.rooms.splice(i, 1);
                            }
                        }
                    }, function(errResponse) {
                        console.log(errResponse);
                    });
            }
        }
    ]);