'use strict';

angular.module('flightApp').controller('AvioCompanyController',
    ['$scope', '$rootScope', '$state', 'initialCompanyData','initialDestinationsData',
        function ($scope, $rootScope, $state, initialCompanyData, initialDestinationsData) {

            var self = this;
            self.avioCompany = initialCompanyData.avio;
            self.destinations=initialDestinationsData.dest;
            
        }
    ]);