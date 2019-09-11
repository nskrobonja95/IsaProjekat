'use strict';

angular.module('flightApp').controller('AvioCompanyController',
    ['$scope', '$rootScope', 'AvioService', '$state', 'initialCompanyData', 'initialDestinationsData', 
        function ($scope, $rootScope, AvioService, $state, initialCompanyData, initialDestinationsData) {

            var self = this;
            console.log(initialCompanyData);
            self.avioCompany = initialCompanyData.avio;
            self.destinations = initialDestinationsData.dest;
            self.showFlightsWithDiscount = showFlightsWithDiscount;

            $scope.rating = 0;
            $scope.rating = {
                current: self.avioCompany.averageRate,
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

          function showFlightsWithDiscount() {
              $state.go("home-abstract.avio-fast-reservations", {companyId: self.avioCompany.id});
          }
        }
    ]);