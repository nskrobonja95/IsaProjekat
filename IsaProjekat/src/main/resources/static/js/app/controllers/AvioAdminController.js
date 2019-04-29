'use strict';

angular.module('flightApp').controller('AvioAdminController',
    ['$scope', '$rootScope', '$state', 'AvioService', 'companyData',
        function ($scope, $rootScope, $state, AvioService, companyData) {
            var self = this;
            debugger;
            self.company = companyData.airline;
            self.companyName = self.company.name;
            self.companyAddress = self.company.address;
            self.companyPromo = self.company.promo;
            self.destinations = self.company.destinations;
            self.editState = false;
            self.switchToEditState = switchToEditState;
            self.cancelEditing = cancelEditing;
            self.addDestState = false;
            self.switchAddDestState = switchAddDestState;
            self.saveNewDestination = saveNewDestination
            self.cancelAddingDestination = cancelAddingDestination;

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
                obj.name = self.newDest;
                AvioService.addDestination(obj)
                    .then(function(response) {
                            console.log(response);
                            self.company = response.avio;
                            self.destinations = self.company.destinations;
                        }, function(errResponse) {
                            console.log(errResponse);
                        });
                self.newDest = "";
            }

            function cancelAddingDestination() {
                self.addDestState = false;
            }
        }
    ]);