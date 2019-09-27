'use strict';

angular.module('flightApp').controller('CreateFlightController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'initialDestinations','$stateParams',
        function ($scope, $rootScope, $state, AvioService, initialDestinations, $stateParams) {
            var self = this;
            console.log(initialDestinations);
            self.destinations = initialDestinations.dests;
            self.setSelectedConfig = setSelectedConfig;
            self.checkFlightClass = checkFlightClass;
            self.evaluateChange = evaluateChange;
            self.saveFlight = saveFlight;
            self.setToFastRes = setToFastRes;
            self.createSeats= createSeats;

            self.seatConfigObj = [];

            self.seatRowNum = 10;
            self.numOfBussinessSeats = Math.floor(self.seatRowNum/3);
            self.numOfEconomicSeats = self.seatRowNum - self.numOfBussinessSeats;
            self.seatColNum = 6;
            self.createSeats(10, 6);
            console.log(self.seatConfigObj);

            function setSelectedConfig() {
                console.log(self.selectedType);
                if(self.selectedType == "smalljet"){
                    self.seatColNum = 6;
                    self.numOfBussinessSeats = Math.floor(self.seatRowNum/3);
                    self.numOfEconomicSeats = self.seatRowNum - self.numOfBussinessSeats;
                    self.createSeats(self.seatRowNum, self.seatColNum);
                } else if(self.selectedType == "mediumjet") {
                    self.seatColNum = 7;
                    self.numOfBussinessSeats = Math.floor(self.seatRowNum/3);
                    self.numOfEconomicSeats = self.seatRowNum - self.numOfBussinessSeats;
                    self.createSeats(self.seatRowNum, self.seatColNum);
                }  else if(self.selectedType == "airbus") {
                    self.seatColNum = 9;
                    self.numOfBussinessSeats = Math.floor(self.seatRowNum/3);
                    self.numOfEconomicSeats = self.seatRowNum - self.numOfBussinessSeats;
                    self.createSeats(self.seatRowNum, self.seatColNum);
                }  else if(self.selectedType == "jumbojet") {
                    self.seatColNum = 10;
                    self.numOfBussinessSeats = Math.floor(self.seatRowNum/3);
                    self.numOfEconomicSeats = self.seatRowNum - self.numOfBussinessSeats;
                    self.createSeats(self.seatRowNum, self.seatColNum);
                }
            }

            function checkFlightClass(x) {
                console.log(self.rowCounter);
                return true;
                //return self.seatRowNum/3
            }

            function evaluateChange() {
                self.numOfBussinessSeats = Math.floor(self.seatRowNum/3);
                self.numOfEconomicSeats = self.seatRowNum - self.numOfBussinessSeats;
                self.createSeats(self.seatRowNum, self.seatColNum);
            }

            function saveFlight() {
                var obj = {};
                obj.from = self.from.name;
                obj.to = self.to.name;
                obj.depart = self.depart;
                obj.land = self.landing;
                obj.priceForBaggageOver14kg = self.bggOver14;
                obj.economicPrice = self.economicPrice;
                obj.businessPrice = self.businessPrice;
                obj.numOfRows = self.seatRowNum;
                obj.configType = self.selectedType;
                obj.discount = self.discount;
                obj.seats = [];
                for(var i=0; i<self.seatConfigObj.length; ++i) {
                    console.log(self.seatConfigObj[i]);
                    for(var j=0; j<self.seatConfigObj[i].length; ++j) {
                        var seat = {};
                        seat.flightClass = self.seatConfigObj[i][j].flightClass;
                        seat.fastRes = self.seatConfigObj[i][j].fastRes;
                        seat.rowNum = self.seatConfigObj[i][j].rowNum;
                        seat.colNum = self.seatConfigObj[i][j].colNum;
                        obj.seats.push(seat);
                    }
                }
                console.log(obj);
                AvioService.saveFlight(obj, $stateParams.avioId)
                    .then(function(response) {
                        $state.go("home-abstract.avio-admin-avio-profile.admin-flights", {avioId: $stateParams.avioId});
                    }, function(errResponse){
                        console.log(errResponse);
                    });
            }

            function setToFastRes(seat) {
                if(seat.fastRes)seat.fastRes = false;
                else seat.fastRes = true;
            }

            function createSeats(numOfRows, numOfCols) {
                self.seatConfigObj = [];
                for(var i=0; i<numOfRows; ++i) {
                    var seatRow = [];
                    for(var j=0; j<numOfCols; ++j) {
                        var seat = {};
                        if(i < Math.floor(numOfRows/3)){
                            seat.flightClass = "business";
                        } else {
                            seat.flightClass = "economic";
                        }
                        seat.fastRes = false;
                        seat.colNum = j+1;
                        seat.rowNum = i+1;
                        seatRow.push(seat);
                    }
                    self.seatConfigObj.push(seatRow);
                }
            }

        }
    ]);