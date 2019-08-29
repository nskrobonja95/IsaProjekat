'use strict';

angular.module('flightApp').controller('FlightSearchResultsController', [
    '$scope', '$rootScope', '$state','$stateParams',
    function ($scope, $rootScope, $state, $stateParams) {
        var self = this;
        self.searchRes = JSON.parse($stateParams.flightSearchData);
        self.filterFlights = filterFlights;
        self.airlineFilterCheck = airlineFilterCheck;
        self.durationFilterCheck = durationFilterCheck;
        self.priceFilterCheck = priceFilterCheck;
        self.flightsList = [];
        debugger;
        if(self.searchRes.dest.data.directFlights){
            console.log("Uslo u round", self.searchRes.dest.data);
            for(var i=0; i < self.searchRes.dest.data.directFlights.length; i++){
                var flightData = {
                    flight:{},
                    price:0

                };
                for(var j=0; j<self.searchRes.dest.data.returnFlights.length; j++){
                    console.log("Ovo je price na pocetku racunanja:", flightData.price);
                    flightData.flight=([self.searchRes.dest.data.directFlights[i],self.searchRes.dest.data.returnFlights[j]]);
                    flightData.price = self.searchRes.dest.data.directFlights[i].bussinessClassPrice + self.searchRes.dest.data.returnFlights[j].bussinessClassPrice;
                    console.log("Ovo su cene:",self.searchRes.dest.data.directFlights[i].bussinessClassPrice,self.searchRes.dest.data.returnFlights[j].bussinessClassPrice);
                    console.log("Ovo je ono sto saberemo:", flightData.price);
                    self.flightsList.push(flightData);
                    var flightData = {
                        flight:{},
                        price:0
                    };
                }
                console.log(self.flightsList);
                
            }
        }else{
            console.log("Uslo", self.searchRes.dest.data);
            for(var i=0; i < self.searchRes.dest.data.length; i++){
                var flightData = {
                    flight:{},
                    price:0

                };
                flightData.flight = [self.searchRes.dest.data[i]];
                flightData.price = self.searchRes.dest.data[i].bussinessClassPrice;
                self.flightsList.push(flightData);
                
            }
            console.log(self.flightsList);
            
           
            
        }

        function filterFlights(flightObj) {
            console.log("FILTERING...");
            debugger;
            console.log(self.airlineFilterCheck(flightObj.flight));
            console.log(self.priceFilterCheck(flightObj));
            return self.airlineFilterCheck(flightObj.flight) && self.priceFilterCheck(flightObj);
        }

        function airlineFilterCheck(flight) {
            if(self.airlineFilter == '' || self.airlineFilter == undefined)return true;
            for(var i=0; i<flight.length; ++i) {
                if(flight[i].avioCompany.name.toUpperCase().includes(self.airlineFilter.toUpperCase()))return true;
            }
            return false;
        }

        function durationFilterCheck(flight) {
            //to do
        }

        function priceFilterCheck(flight) {
            if(self.priceFilter == '' || self.priceFilter == undefined) return true;
            return flight.price <= Number(self.priceFilter);
        }
    }
]);