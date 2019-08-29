'use strict';

angular.module('flightApp').controller('SystemAdminAirlinesController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'initialCompaniesList', 'initialDestinationsList',
        function($scope, $rootScope, $state, AvioService, initialCompaniesList, initialDestinationsList) {
            var self = this;
            console.log("HELLO");
            console.log(initialCompaniesList);
            console.log(initialDestinationsList);
            self.companiesList = initialCompaniesList.avioList;
            self.destinationList = initialDestinationsList.destList;
            self.goToAddAirlinePage = goToAddAirlinePage;
            self.addDestFormFlag = false;
            self.showAddDestForm = showAddDestForm;
            self.addNewDestination = addNewDestination;

            function goToAddAirlinePage() {
                $state.go("home-abstract.add-airline");
            }

            function showAddDestForm() {
                self.addDestFormFlag = true;
            }

            function addNewDestination() {
                var obj = {};
                obj.name = self.cityName + ', ' + self.countryName; 
                AvioService.saveDestination(obj) 
                    .then(function (response) {
                        console.log("Response of the search:", response);
                        self.destinationList.push(obj);
                        self.cityName = '';
                        self.countryName = '';
                        self.addDestFormFlag = false;
                    }, function (errResponse) {
                        console.log("Error response of the search:", errResponse);
                    });
            }
        }
    ]);