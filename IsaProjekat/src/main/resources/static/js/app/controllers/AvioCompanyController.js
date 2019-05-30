'use strict';

angular.module('flightApp').controller('AvioCompanyController',
    ['$scope', '$rootScope', '$state', 'initialCompanyData', 'initialDestinationsData',
        function ($scope, $rootScope, $state, initialCompanyData, initialDestinationsData) {

            var self = this;
            console.log(initialCompanyData);
            self.avioCompany = initialCompanyData.avio;
            self.destinations = initialDestinationsData.dest;

            $scope.rating = 0;
            $scope.rating = {
                current: 3.7,
                max: 5,
                editable: false
            }
            

            $scope.getSelectedRating = function (rating) {
                console.log(rating);
            }
            
            $scope.setMinrate= function(){
               $scope.ratings = [{
                current: 1,
                max: 10
            }, {
                current: 1,
                max: 5
            }];
            }
            
            $scope.setMaxrate= function(){
               $scope.ratings = [{
                current: 10,
                max: 10
            }, {
                current: 5,
                max: 5
            }];
          }
          
          $scope.sendRate = function(){
            alert("Thanks for your rates!\n\nFirst Rate: " + $scope.ratings[0].current+"/"+$scope.ratings[0].max
            +"\n"+"Second rate: "+ $scope.ratings[1].current+"/"+$scope.ratings[0].max)
          }
        }
    ]);