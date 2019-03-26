(function () {
    'use strict';
    angular.module('flightApp').factory('AvioService', AvioService);
    AvioService.$inject = ['$http', '$q', 'urls'];

    function AvioService($http, $q, urls) {
        var service = {};
        service.loadAllAvio = loadAllAvio;

        return service;

        function loadAllAvio() {
            var avioList = $http.get(urls.UNREGISTERED_USERS_SERVICE_API+'airlines')
                .then(function (response) {
                    console.log("Avio service response:", response.data);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while initializing list of all avio companies!", error);
                });

            return $q.all([avioList])
                .then(function (results) {
                    return {
                        avioList: results[0]
                    };
                });
        }
    }

})();