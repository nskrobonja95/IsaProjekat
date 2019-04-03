(function () {
    'use strict';
    angular.module('flightApp').factory('HotelService', HotelService);
    HotelService.$inject = ['$http', '$q', 'urls'];

    function HotelService($http, $q, urls) {
        var service = {};
        service.loadAllHotels = loadAllHotels;
        service.loadHotelById = loadHotelById;
        service.search = search;
        
        return service;

        function loadAllHotels() {
            var hotelsList = $http.get(urls.UNREGISTERED_USERS_SERVICE_API+'hotels')
                .then(function (response) {
                    console.log("Hotel service response:", response.data);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while initializing list of all hotels!", error);
                });

            return $q.all([hotelsList])
                .then(function (results) {
                    return {
                        hotelsList: results[0]
                    };
                });
        }

        function loadHotelById(id) {
            var hotel = $http.get(urls.UNREGISTERED_USERS_SERVICE_API+'hotel/'+id)
            .then(function (response) {
                console.log("Hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  Hotel!", error);
            });

            return $q.all([hotel])
                .then(function (results) {
                    return {
                        hotel: results[0]
                    };
                });
        }

        function search(searchObj) {
            var hotels = $http.post(urls.UNREGISTERED_USERS_SERVICE_API+'searchHotels/', searchObj)
            .then(function (response) {
                console.log("Search hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  Hotel!", error);
            });

            return $q.all([hotels])
                .then(function (results) {
                    return {
                        hotels: results[0]
                    };
                });
        }
    }

})();