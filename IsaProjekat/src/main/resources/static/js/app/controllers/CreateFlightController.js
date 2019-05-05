'use strict';

angular.module('flightApp').controller('CreateFlightController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'initialDestinations',
        function ($scope, $rootScope, $state, AvioService, initialDestinations) {
            var self = this;
            console.log(initialDestinations);
            self.destinations = initialDestinations.dests;
            self.setSelectedConfig = setSelectedConfig;
            self.checkFlightClass = checkFlightClass;
            self.evaluateChange = evaluateChange;
            self.saveFlight = saveFlight

            self.seatRowNum = 10;
            self.numOfBussinessSeats = Math.floor(self.seatRowNum/3);
            console.log(self.numOfBussinessSeats);
            self.numOfEconomicSeats = self.seatRowNum - self.numOfBussinessSeats;
            self.seatColNum = 6;

            function setSelectedConfig() {
                console.log(self.selectedType);
                if(self.selectedType == "smalljet"){
                    self.seatColNum = 6;
                    self.numOfBussinessSeats = Math.floor(self.seatRowNum/3);
                    self.numOfEconomicSeats = self.seatRowNum - self.numOfBussinessSeats;
                } else if(self.selectedType == "mediumjet") {
                    self.seatColNum = 7;
                    self.numOfBussinessSeats = Math.floor(self.seatRowNum/3);
                    self.numOfEconomicSeats = self.seatRowNum - self.numOfBussinessSeats;
                }  else if(self.selectedType == "airbus") {
                    self.seatColNum = 9;
                    self.numOfBussinessSeats = Math.floor(self.seatRowNum/3);
                    self.numOfEconomicSeats = self.seatRowNum - self.numOfBussinessSeats;
                }  else if(self.selectedType == "jumbojet") {
                    self.seatColNum = 10;
                    self.numOfBussinessSeats = Math.floor(self.seatRowNum/3);
                    self.numOfEconomicSeats = self.seatRowNum - self.numOfBussinessSeats;
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
            }

            function saveFlight() {
                var obj = {};
                obj.from = self.from.name;
                obj.to = self.to.name;
                obj.depart = self.depart;
                obj.land = self.landing;
                obj.priceForBaggageOver7kg = self.bggOver7;
                obj.priceForBaggageOver14kg = self.bggOver14;
                obj.economicPrice = self.economicPrice;
                obj.businessPrice = self.businessPrice;
                obj.numOfRows = self.seatRowNum;
                obj.configType = self.selectedType
                console.log(obj);
                AvioService.saveFlight(obj)
                    .then(function(response) {
                        $state.go("home-abstract.admin-flights");
                    }, function(errResponse){
                        console.log(errResponse);
                    });
            }

        }
    ]);