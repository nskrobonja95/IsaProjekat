'use strict';

angular.module('flightApp').controller('AvioController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'initialCompaniesList',
        function ($scope, $rootScope, $state, AvioService, initialCompaniesList) {

            var self = this;
            this.companiesList = initialCompaniesList.avioList;
            self.roundTripSearch = roundTripSearch;
            self.formatDateString = formatDateString;
            self.incNumOfPpl = incNumOfPpl;
            self.decNumOfPpl = decNumOfPpl;
            self.roundTrip = {};
            self.roundTrip.numOfPpl = 1;
            // self.roundTrip.depart = new Date();
            // self.roundTrip.return = new Date();

            function roundTripSearch() {
                console.log(self.roundTrip);
                self.roundTrip.departDate = formatDateString(self.roundTrip.departDate);
                self.roundTrip.returnDate = formatDateString(self.roundTrip.returnDate);
                console.log(self.roundTrip);
                AvioService.roundTripSearch(self.roundTrip);
            }

            function incNumOfPpl() {
                self.roundTrip.numOfPpl += 1;
            }

            function decNumOfPpl() {
                if(self.roundTrip.numOfPpl > 1) self.roundTrip.numOfPpl-=1;
            }

            function formatDateString(datestring) {
                console.log("Begin: " + datestring);
                var retVal;
                var splitted = datestring.split("/");
                retVal = splitted[2] + "-" + splitted[0] + "-" + splitted[1];
                console.log("End: " + retVal);
                return retVal;
            }
        }
    ]);