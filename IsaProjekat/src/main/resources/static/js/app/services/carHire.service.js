(function () {
    'use strict';
    angular.module('flightApp').factory('CarHireService', CarHireService);
    CarHireService.$inject = ['$http', '$q', 'urls'];

    function CarHireService($http, $q, urls) {
        var service = {};
        service.loadAllCarHireCompanies = loadAllCarHireCompanies;

        return service;

        function loadAllCarHireCompanies() {
            var carHireList = $http.get(urls.UNREGISTERED_USERS_SERVICE_API+'carHire')
                .then(function (response) {
                    console.log("Car hire service response:", response.data);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while initializing list of all car hire companies!", error);
                });

            return $q.all([carHireList])
                .then(function (results) {
                    return {
                        carHireList: results[0]
                    };
                });
        }
    }

})();