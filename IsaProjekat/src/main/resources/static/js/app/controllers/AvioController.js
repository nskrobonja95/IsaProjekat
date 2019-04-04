'use strict';

angular.module('flightApp').controller('AvioController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'SearchService', 'initialDestinationsList', 'initialCompaniesList',
        function ($scope, $rootScope, $state, AvioService, SearchService, initialDestinationsList, initialCompaniesList) {

            var self = this;
            self.companiesList = initialCompaniesList.avioList;
            console.log(initialDestinationsList);
            self.destList = initialDestinationsList.destList;
            self.roundTripSearch = roundTripSearch;
            self.oneWaySearch = oneWaySearch;
            self.multiCitySearch = multiCitySearch;
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
                self.roundTrip.departDate = SearchService.formatDateString(self.roundTripDepartDate);
                self.roundTrip.returnDate = SearchService.formatDateString(self.roundTripReturnDate);
                self.roundTrip.from = self.roundTrip.from.name;
                self.roundTrip.to = self.roundTrip.to.name;
                console.log(self.roundTrip);
                AvioService.roundTripSearch(self.roundTrip)
                    .then(function (response) {
                        console.log("Response of the search:", response);
                        
                    }, function (errResponse) {
                        console.log("Error response of the search:", errResponse);
                    });
            }

            function oneWaySearch() {
                console.log(self.oneWay);
                self.oneWay.departDate = SearchService.formatDateString(self.oneWayDepartDate);
                self.oneWay.from = self.oneWay.from.name;
                self.oneWay.to = self.oneWay.to.name;
                console.log(self.oneWay);
                AvioService.oneWaySearch(self.oneWay)
                    .then(function (response) {
                        console.log("Response of the search:", response);
                        $state.go('home-abstract.flight-search-results', {flightSearchData:JSON.stringify(response)});
                    }, function (errResponse) {
                        console.log("Error response of the search:", errResponse);
                    });
            }

            function multiCitySearch() {
                console.log(self.multiCity);
                self.multiCity.departDate1 = SearchService.formatDateString(self.multiCityDepartDate1);
                self.multiCity.departDate2 = SearchService.formatDateString(self.multiCityDepartDate2);
                self.multiCity.from = self.multiCity.from.name;
                self.multiCity.midDest = self.multiCity.midDest.name;
                self.multiCity.to = self.multiCity.to.name;
                console.log(self.multiCity);
                AvioService.multiCitySearch(self.multiCity)
                    .then(function (response) {
                        console.log("Response of the search:", response);
                    }, function (errResponse) {
                        console.log("Error response of the search:", errResponse);
                    });
            }

            function incNumOfPpl() {
                self.roundTrip.numOfPpl += 1;
            }

            function decNumOfPpl() {
                if (self.roundTrip.numOfPpl > 1) self.roundTrip.numOfPpl -= 1;
            }

            function incNumOfPplOneWay() {
                self.oneWay.numOfPpl += 1;
            }

            function decNumOfPplOneWay() {
                if (self.oneWay.numOfPpl > 1) self.oneWay.numOfPpl -= 1;
            }

            function incNumOfPplMultiCity() {
                self.multiCity.numOfPpl += 1;
            }

            function decNumOfPplMultiCity() {
                if (self.multiCity.numOfPpl > 1) self.multiCity.numOfPpl -= 1;
            }

        }
    ]);