'use strict';

angular.module('flightApp').controller('AvioController',
    ['$scope', '$rootScope', '$state','initialCompaniesList',
        function ($scope, $rootScope, $state, initialCompaniesList) {

            var self = this;
            this.companiesList = initialCompaniesList.avioList;
        }
    ]);