'use strict';

angular.module('flightApp').controller('AvioCompanyController',
    ['$scope', '$rootScope', '$state', 'initialCompanyData',
        function ($scope, $rootScope, $state, initialCompanyData) {

            var self = this;
            this.avioCompany = initialCompanyData.avio;
        }
    ]);