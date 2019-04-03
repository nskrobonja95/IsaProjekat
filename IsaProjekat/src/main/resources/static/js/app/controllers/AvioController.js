'use strict';

angular.module('flightApp').controller('AvioController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'SearchService', 'initialCompaniesList',
        function ($scope, $rootScope, $state, AvioService, SearchService, initialCompaniesList) {

            var self = this;
            this.companiesList = initialCompaniesList.avioList;
            self.roundTripSearch = roundTripSearch;
            self.oneWaySearch = oneWaySearch;
            self.multiCitySearch = multiCitySearch;
            self.formatDateString = formatDateString;
            self.incNumOfPpl = incNumOfPpl;
            self.decNumOfPpl = decNumOfPpl;
            self.incNumOfPplOneWay = incNumOfPplOneWay;
            self.decNumOfPplOneWay = decNumOfPplOneWay;
            self.incNumOfPplMultiCity = incNumOfPplMultiCity;
            self.decNumOfPplMultiCity = decNumOfPplMultiCity;
            self.roundTrip = {};
            self.roundTrip.numOfPpl = 1;
            self.oneWay = {};
            self.oneWay.numOfPpl = 1;
            self.multiCity = {};
            self.multiCity.numOfPpl = 1;

            function roundTripSearch() {
                console.log(self.roundTrip);
                self.roundTrip.departDate = formatDateString(self.roundTripDepartDate);
                self.roundTrip.returnDate = formatDateString(self.roundTripReturnDate);
                console.log(self.roundTrip);
                AvioService.roundTripSearch(self.roundTrip);
            }

            function oneWaySearch() {
                console.log(self.oneWay);
                self.oneWay.departDate = formatDateString(self.oneWayDepartDate);
                console.log(self.oneWay);
                AvioService.oneWaySearch(self.oneWay);
            }

            function multiCitySearch() {
                console.log(self.multiCity);
                self.multiCity.departDate1 = SearchService.formatDateString(self.multiCityDepartDate1);
                self.multiCity.departDate2 = SearchService.formatDateString(self.multiCityDepartDate2);
                console.log(self.multiCity);
                AvioService.multiCitySearch(self.multiCity);
            }

            function incNumOfPpl() {
                self.roundTrip.numOfPpl += 1;
            }

            function decNumOfPpl() {
                if(self.roundTrip.numOfPpl > 1) self.roundTrip.numOfPpl-=1;
            }

            function incNumOfPplOneWay() {
                self.oneWay.numOfPpl += 1;
            }

            function decNumOfPplOneWay() {
                if(self.oneWay.numOfPpl > 1) self.oneWay.numOfPpl-=1;
            }

            function incNumOfPplMultiCity() {
                self.multiCity.numOfPpl += 1;
            }

            function decNumOfPplMultiCity() {
                if(self.multiCity.numOfPpl > 1) self.multiCity.numOfPpl-=1;
            }
            
        }
    ]);