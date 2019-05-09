'use strict';

angular.module('flightApp').controller('SystemAdminAirlinesController',
    ['$scope', '$rootScope', '$state', 'initialCompaniesList',
        function($scope, $rootScope, $state, initialCompaniesList) {
            var self = this;
            console.log("HELLO");
            console.log(initialCompaniesList);
            self.companiesList = initialCompaniesList.avioList;
            self.goToAddAirlinePage = goToAddAirlinePage;

            function goToAddAirlinePage() {
                $state.go("home-abstract.add-airline");
            }
        }
    ]);