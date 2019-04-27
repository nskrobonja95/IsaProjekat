'use strict';

angular.module('flightApp').controller('AvioAdminController',
    ['$scope', '$rootScope', '$state', 'companyData',
        function ($scope, $rootScope, companyData) {
            var self = this;
            debugger;
            self.company = companyData.airline;
            console.log("HELLO");
            console.log(self.company);
        }
    ]);