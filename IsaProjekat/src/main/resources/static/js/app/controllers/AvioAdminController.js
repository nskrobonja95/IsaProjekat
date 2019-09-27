'use strict';

angular.module('flightApp').controller('AvioAdminController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'companyData', 'restOfDestinationsList','$stateParams',
        function ($scope, $rootScope, $state, AvioService, companyData, restOfDestinationsList, $stateParams) {
            var self = this;
            self.company = companyData.airline;
            self.companyName = self.company.name;
            self.companyAddress = self.company.address;
            self.companyPromo = self.company.promo;
            self.destinations = self.company.destinations;
            self.restOfDestinations = restOfDestinationsList.destList;
            self.editState = false;
            self.switchToEditState = switchToEditState;
            self.cancelEditing = cancelEditing;
            self.addDestState = false;
            self.switchAddDestState = switchAddDestState;
            self.saveNewDestination = saveNewDestination
            self.cancelAddingDestination = cancelAddingDestination;
            self.removeDestination = removeDestination;

            function switchToEditState() {
                var btn = document.getElementById("editButton");
                if(btn.innerHTML == "Edit") {
                    btn.innerHTML = "Save";
                    self.editState = true;
                } else if(btn.innerHTML == "Save") {
                    btn.innerHTML = "Edit";
                    var obj = {};
                    obj.name = self.companyName;
                    obj.address = self.companyAddress;
                    obj.promo = self.companyPromo;
                    AvioService.updateBasicAvioInfo(obj)
                        .then(function(response) {
                                console.log(response);
                                self.company = response.updated;
                                self.company.name = self.companyName;
                                self.company.address = self.companyAddress;
                                self.company.promo = self.companyPromo;
                            }, 
                            function(errResponse) {
                                console.log(errResponse);
                            });
                    self.editState = false;
                }
            }

            function cancelEditing() {
                var btn = document.getElementById("editButton");
                btn.innerHTML = "Edit";
                self.editState = false;
                self.companyName = self.company.name;
                self.companyAddress = self.company.address;
                self.companyPromo = self.company.promo;
            }

            function switchAddDestState() {
                self.addDestState = true;
            }

            function saveNewDestination() {
                var obj = {};
                obj.name = self.newDest.name;
                AvioService.addDestination(obj, $stateParams.avioId)
                    .then(function(response) {
                            console.log(response);
                            self.company = response.avio;
                            self.destinations = self.company.destinations;
                            AvioService.loadRestOfDestinations($stateParams.avioId)
                                .then(function(response) {
                                    self.restOfDestinations = response.destList;
                                }, function(errResponse) {      
                                    console.log(errResponse);
                                })
                        }, function(errResponse) {
                            console.log(errResponse);
                        });
                self.newDest = "";
            }

            function cancelAddingDestination() {
                self.addDestState = false;
            }

            function removeDestination(destId) {
                AvioService.removeDestinationForAdmin(destId, $stateParams.avioId)
                    .then(function(response) {
                        console.log(response);
                        
                        if(response.dests.status == 400){
                            alert("Cannot remove destination. There are active flights for this destination.")
                            return;
                        }
                        self.destinations = response.dests;
                        AvioService.loadRestOfDestinations($stateParams.avioId)
                                .then(function(response) {
                                    self.restOfDestinations = response.destList;
                                }, function(errResponse) {      
                                    console.log(errResponse);
                                })
                    }, function(errResponse) {
                        console.log(errResponse);
                    });
            }
        }
    ]);