'use strict';

angular.module('flightApp').controller('FlightReservationController', [
    '$scope', '$rootScope', 'AvioService', 'HotelService', 'FriendService', '$state','$stateParams', 'direct_flight', 'return_flight', 'dir_seats', 'ret_seats',
    function ($scope, $rootScope, AvioService, HotelService, FriendService,$state, $stateParams, direct_flight, return_flight, dir_seats, ret_seats) {
        var self = this;
        self.dir_flight = direct_flight.flight;
        self.ret_flight = return_flight.flight;
        self.dir_seats = dir_seats;
        self.ret_seats = ret_seats;
        self.dirSeatSelected = false;
        // we will store all of our form data in this object
        $scope.formData = {};
        $scope.direct_flight = direct_flight.flight;
        $scope.return_flight = return_flight.flight;
        $scope.dir_seats = dir_seats.seats;
        $scope.ret_seats = ret_seats.seats;
        $scope.dirSeatSelected = false;
        $scope.retSeatSelected = false;
        $scope.roundTripObj = [];
        $scope.reservationObj = [];
        $scope.oneWayObj = [];
        $scope.searchValue = "";
        $scope.friendsList = [];
        $scope.recommendation = false;
        $scope.numOfReservationsMade = 0;
        // console.log(direct_flight);
        // console.log(return_flight);
        // console.log(dir_seats);
        // console.log(ret_seats);
        // console.log(direct_flight.flight == -1);
        // console.log(typeof(return_flight.flight));
        // console.log(return_flight.flight === -1);

        // function to process the form
        // $scope.processForm = function() {
        //     alert('awesome!');
        // };

        $scope.selectSeatForDirectFlight = function(seat) {
            if(!seat.available){
                alert("SEAT NOT AVAILABLE");
                return;
            }
            if(seat.reserved) {
                alert("SEAT ALREADY RESERVED");
                return;
            }
            //ako je u pitanju rezervacija za one-way
            if($scope.return_flight == -1){
                if($scope.roundTripObj.length == 4){
                    alert("Maximum 4 seats can be reserved.");
                    return;
                }
                seat.available = false;
                seat.reserved = true;
                seat.successfullyReserved = false;
                seat.inviteFriend = false;
                $scope.roundTripObj.push(seat);
                console.log($scope.roundTripObj);
                return;
            }
            if($scope.dirSeatSelected){
                alert("Please select seat for return flight.");
                return;
            }
            if($scope.roundTripObj.length == 4){
                alert("Maximum 4 seats can be reserved.");
                return;
            }
            seat.available = false;
            seat.reserved = true;
            // seat.successfullyReserved = false;
            $scope.dirSeatSelected = true;
            $scope.reservationObj.push(seat);
            $scope.reservationObj.successfullyReserved = false;
            $scope.reservationObj.inviteFriend = false;
            console.log($scope.roundTripObj);
        }

        $scope.selectSeatForReturnFlight = function(seat) {
            if(!$scope.dirSeatSelected) {
                alert("Please select for direct flight first");
                return;
            }
            $scope.dirSeatSelected = false;
            seat.available = false;
            seat.reserved = true;
            $scope.reservationObj.push(seat);
            $scope.roundTripObj.push($scope.reservationObj);
            $scope.reservationObj = [];
            console.log($scope.roundTripObj);
        }

        $scope.reserve = function(seat) {
            var obj = {};
            obj.name = seat.name;
            obj.lastname = seat.lastname;
            obj.passportNumber = seat.passnum;
            obj.seats = [];
            if(seat instanceof Array) {
                for(var i=0; i<seat.length; ++i) {
                    obj.seats.push(seat[i]);
                }
            } else {
                obj.seats.push(seat);
            }
            AvioService.makeReservation(obj)
                .then(
                    function (response) {
                    	if(response.response.status == 500 || response.response.status == 400) {
                    		alert("Seat has been taken! Please select other!");
                    		for(var i=0; i<$scope.roundTripObj.length; ++i) {
                    			if($scope.roundTripObj[i] == seat) {
                    				$scope.roundTripObj.splice(i, 1);
                    			}
                    		}
                    		return;
                    	}
                        seat.successfullyReserved = true;
                        $scope.recommendation = true;
                        ++$scope.numOfReservationsMade;
                    },
                    function (errResponse) {
                        alert("RESPONSE ERROR");
                    }
                );
        }

        $scope.search = function(seat) {
            var searchOutput = [];
            FriendService.loadAllFriends()
                .then(
                    function(response) {
                        console.log(response);
                        $scope.friendsList = response.friendsList;
                        angular.forEach($scope.friendsList, function(friend) {
                            if(friend.username.toLowerCase().indexOf(seat.searchValue.toLowerCase()) >= 0){
                                searchOutput.push(friend.username);
                            }
                        })
                        seat.filterFriends = searchOutput;
                    },
                    function(error) {
                        console.log(error);
                    }
                );
        }

        $scope.showSearchFriendForm = function(seat) {
            seat.inviteFriend = true;
            seat.friendInvited = false;
            seat.filterFriends = [];
        }

        $scope.hideSearchFriendForm = function(seat) {
            seat.inviteFriend = false;
            seat.filterFriends = [];
        }

        $scope.fillTextBox = function(username, obj) {
            obj.searchValue = username;
            obj.filterFriends = [];
        }

        $scope.invite = function(seat) {
            var obj = {};
            obj.name = seat.name;
            obj.lastname = seat.lastname;
            obj.passportNumber = seat.passnum;
            obj.username = seat.searchValue;
            obj.seats = [];
            if(seat instanceof Array) {
                for(var i=0; i<seat.length; ++i) {
                    obj.seats.push(seat[i]);
                }
            } else {
                obj.seats.push(seat);
            }
            AvioService.sendInvitation(obj)
                .then(
                    function (response) {
                        // seat.inviteFriend = false;
                        seat.friendInvited = true;
                    },
                    function (errResponse) {
                        alert("RESPONSE ERROR");
                    }
                );
        }

        $scope.findRecommendations = function() {
            var obj = {};
            console.log($scope.direct_flight);
            obj.checkIn = $scope.direct_flight.landing.split(" ")[0];
            if($scope.return_flight != -1){
                obj.checkOut = $scope.return_flight.landing.split(" ")[0];
            } else {
                obj.checkOut = null;
            }
            obj.dest = $scope.direct_flight.toDest.name;
            HotelService.search(obj)
                .then(function(response) {
                    console.log("Response of the search:", response);
                    $state.go('home-abstract.hotel-search-results', {hotelSearchData:JSON.stringify(response)});
                }, function (errResponse) {
                    console.log("Error response of the search:", errResponse);
                });
        }
    }
]);