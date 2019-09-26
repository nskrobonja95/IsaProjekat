'use strict';

angular.module('flightApp').controller('AvioAdminWelcomeController',
    ['$scope', '$rootScope', '$state', 'AvioService','initalAvioList',
        function ($scope, $rootScope, $state, HotelService, initalAvioList) {
            var self = this;
           
            self.avioList = initalAvioList.list;

            
        }
    ]);