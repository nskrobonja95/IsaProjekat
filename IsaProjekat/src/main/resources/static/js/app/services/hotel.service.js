(function () {
    'use strict';
    angular.module('flightApp').factory('HotelService', HotelService);
    HotelService.$inject = ['$http', '$q', 'urls'];

    function HotelService($http, $q, urls) {
        var service = {};
        service.loadAllHotels = loadAllHotels;
        service.loadHotelById = loadHotelById;
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
        function loadHotelById(id){
            var hotel = $http.get(urls.UNREGISTERED_USERS_SERVICE_API+'hotels/'+id)
            .then(function (response) {
                console.log("Hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing hotel!", error);
            });

        return $q.all([hotel])
            .then(function (results) {
                return {
                    hotel: results[0]
                };
            });
        }
    }

})();