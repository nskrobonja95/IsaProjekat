'use strict';

angular.module('flightApp').controller('HotelAdminController',
    ['$scope', '$rootScope', '$state', 'HotelService', 'hotelData', 'initialHotelServices',
        function ($scope, $rootScope, $state, HotelService, hotelData, initialHotelServices) {
            var self = this;
            console.log(hotelData);
            console.log(hotelData.hotel);
            console.log(initialHotelServices);
            self.hotel = hotelData.hotel;
            self.hotelServices = initialHotelServices.hotelServices;
            self.hotelName = self.hotel.name;
            self.hotelAddress = self.hotel.address;
            self.hotelPromo = self.hotel.promo;
            self.editState = false;
            self.switchToEditState = switchToEditState;
            self.cancelEditing = cancelEditing;
            self.addService = addService;
            self.removeService = removeService;

            function switchToEditState() {
                var btn = document.getElementById("editHotelBtn");
                if(btn.innerHTML == "Edit") {
                    btn.innerHTML = "Save";
                    self.editState = true;
                } else if(btn.innerHTML == "Save") {
                    btn.innerHTML = "Edit";
                    var obj = {};
                    obj.name = self.hotelName;
                    obj.address = self.hotelAddress;
                    obj.promo = self.hotelPromo;
                    HotelService.updateBasicHotelInfo(obj)
                        .then(function(response) {
                                console.log(response);
                                self.hotel = response.updatedHotel;
                                self.hotel.name = self.hotelName;
                                self.hotel.address = self.hotelAddress;
                                self.hotel.promo = self.hotelPromo;
                            }, 
                            function(errResponse) {
                                console.log(errResponse);
                            });
                    self.editState = false;
                }
            }

            function cancelEditing() {
                var btn = document.getElementById("editHotelBtn");
                btn.innerHTML = "Edit";
                self.editState = false;
                self.companyName = self.company.name;
                self.companyAddress = self.company.address;
                self.companyPromo = self.company.promo;
            }

            function addService() {
                $state.go("home-abstract.add-service");
            }

            function removeService(id) {
                HotelService.removeService(id)
                    .then(function(response) {
                        self.hotelServices = response.services;
                    }, function(errResponse) {
                        console.log(errResponse);
                    });
            }

        }
    ]);