'use strict';

angular.module('flightApp').controller('CarHireController',
    ['$scope', '$rootScope', '$state','initialCarHireCompaniesList',
        function ($scope, $rootScope, $state, initialCarHireCompaniesList) {

            var self = this;
            this.carHireCompaniesList = initialCarHireCompaniesList.carHireList;
        }
    ]);