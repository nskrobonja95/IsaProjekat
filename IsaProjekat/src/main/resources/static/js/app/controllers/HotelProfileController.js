'use strict';

angular.module('flightApp').controller('HotelProfileController',
    ['$scope', '$rootScope', '$state', '$stateParams', 'SearchService', 'HotelService',
    'initialHotelServices', 'initialHotel', 'availableRooms',
        function ($scope, $rootScope, $state, $stateParams, SearchService, HotelService,
            initialHotelServices, initialHotel, availableRooms) {

            var self = this;
            self.user = $rootScope.globals.currentUser;
            console.log("Current user:");
            console.log(self.user);
            self.flightReservations = JSON.parse($stateParams.flightReservations);
            self.hotel = initialHotel.hotel;
            self.availableRooms = availableRooms.availableRooms;
            for(var i=0; i<availableRooms.lenght; ++i) {
                self.availableRooms.dataLoading = false;
            }
            self.hotelServices = initialHotelServices.hotelServices;
            self.filterServices = filterServices;
            self.search = search;
            self.check = check;
            self.bookRoom = bookRoom;
            self.dataLoading = false;

            function check() {
                console.log(self.filterItems);
                console.log(typeof(self.filterItems));
            }
            self.filterItems = {};
            self.checkInDate = SearchService.reverseFormatDateString($stateParams.checkIn);
            self.checkOutDate = SearchService.reverseFormatDateString($stateParams.checkOut);
            self.todayDate = new Date();
            // console.log("Today: ", self.todayDate);
            self.activePriceCheck = activePriceCheck;
            // self.filterServices = filterServices;

            function search() {
                var checkIn = SearchService.formatDateString(self.checkInDate);
                var checkOut = SearchService.formatDateString(self.checkOutDate);
                HotelService.getAvailableRoomsForHotel(this.hotel.id, checkIn, checkOut)
                    .then(function(response){
                        self.availableRooms = response.availableRooms;        
                    }, function(errResponse){
                        console.log(errResponse);
                    });
                
            }

            function activePriceCheck(price) {
                var fromDate = new Date(price.activeFrom);
                var toDate = new Date(price.activeTo);
                return fromDate <= self.todayDate & toDate >= self.todayDate;
            }

            function filterServices(room) {
                var flag = false;
                for(var i=0; i<self.hotelServices.length; ++i) {
                    flag = false;
                    var hotelService = self.hotelServices[i];
                    var serviceChecked = self.filterItems[hotelService.name];
                    if(serviceChecked){
                        for(var j=0; j<room.hotelServices.length; ++j) {
                            var roomService = room.hotelServices[j];
                            if(roomService.name == hotelService.name){
                                flag = true;
                                break;
                            }
                        }
                        if(!flag) return false;
                    }
                }
                return true;
            }

            function bookRoom(room) {
                room.dataLoading = true;
                var obj = {};
                obj.roomId = room.id;
                obj.hotelServices = [];
                for(var j=0; j<room.hotelServices.length; ++j) {
                    var roomService = room.hotelServices[j];
                    var serviceChecked = self.filterItems[roomService.name];
                    if(serviceChecked){
                        obj.hotelServices.push(roomService.name);
                    }
                }
                obj.arrivalDate = SearchService.formatDateString(self.checkInDate);
                obj.departingDate = SearchService.formatDateString(self.checkOutDate);
                console.log(obj);
                debugger;
                if(self.flightReservations != null) {
                    obj.flightReservationIds = self.flightReservations.ids;
                }
                else{
                    obj.flightReservationIds = null;
                }
                HotelService.bookRoom(obj)
                    .then(
                        function (response) {
                            debugger;
                            if(response.bookingRoomResponse.status == 200){
                                if(self.flightReservations == null) {
                                    var btn = document.getElementById(room.id);
                                    btn.disabled = true;
                                    btn.style.backgroundColor="green";
                                    btn.innerHTML = 'RESERVED';
                                    // btn.style.visibility = 'hidden';
                                    // var lbl = btn.nextElementSibling;
                                    // lbl.style.visibility = 'visible';
                                    // lbl.textContent = 'BOOKED';
                                    room.dataLoading = false;
                                } else {
                                    room.dataLoading = false;
                                    $state.go("home-abstract.succesful-reservation");
                                }
                            } else if(response.bookingRoomResponse.status == 409) {
                                alert("Room taken");
                            } else {
                                alert("Something went wrong with reservation process");
                            }
                        },
                        function (errResponse) {
                            room.dataLoading = false;
                            console.log(errResponse);
                        }
                    )
            }

        }
    ]);