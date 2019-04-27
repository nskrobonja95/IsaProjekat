(function () {
    'use strict';
    angular.module('flightApp').factory('AvioService', AvioService);
    AvioService.$inject = ['$http', '$q', 'urls'];

    function AvioService($http, $q, urls) {
        var service = {};
        service.loadAllAvio = loadAllAvio;
        service.loadAvioById = loadAvioById;
        service.loadDestinationsById = loadDestinationsById;
        service.roundTripSearch = roundTripSearch;
        service.oneWaySearch = oneWaySearch;
        service.multiCitySearch = multiCitySearch;
        service.loadAllDestiantions = loadAllDestiantions;
        service.loadAvioForAdmin = loadAvioForAdmin;
        return service;
        
        function loadAllDestiantions(){
            var destList = $http.get(urls.UNREGISTERED_USERS_SERVICE_API+'getAllDestinations')
            .then(function (response) {
                console.log("Avio service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing list of all destinations!", error);
            });

        return $q.all([destList])
            .then(function (results) {
                return {
                    destList: results[0]
                };
            });
        }
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
        function loadAvioById(id){
            var avio = $http.get(urls.UNREGISTERED_USERS_SERVICE_API+'airlines/'+id)
            .then(function (response) {
                console.log("Avio service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  avio company!", error);
            });

            return $q.all([avio])
            .then(function (results) {
                return {
                    avio: results[0]
                };
            });
        }
        function loadDestinationsById(id){
            var dest = $http.get(urls.UNREGISTERED_USERS_SERVICE_API+'getAllDestinationsById/'+id)
            .then(function (response) {
                console.log("Avio service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  avio company destinations!", error);
            });

            return $q.all([dest])
                .then(function (results) {
                    return {
                        dest: results[0]
                    };
                });
            }

            function roundTripSearch(searchObj){
                var dest = $http.post(urls.UNREGISTERED_USERS_SERVICE_API+'roundTripSearch', searchObj)
                .then(function (response) {
                    console.log("Search response:", response);
                    return response;
                }, function (error) {
                    console.log("Error occured while flight search!", error);
                });
    
                return $q.all([dest])
                    .then(function (results) {
                        return {
                            dest: results[0]
                        };
                    });
            }

            function oneWaySearch(searchObj){
                var dest = $http.post(urls.UNREGISTERED_USERS_SERVICE_API+'oneWaySearch', searchObj)
                .then(function (response) {
                    console.log("Search response:", response);
                    return response;
                }, function (error) {
                    console.log("Error occured while flight search!", error);
                });
    
                return $q.all([dest])
                    .then(function (results) {
                        return {
                            dest: results[0]
                        };
                    });
            }

            function multiCitySearch(searchObj){
                var dest = $http.post(urls.UNREGISTERED_USERS_SERVICE_API+'multiCitySearch', searchObj)
                .then(function (response) {
                    console.log("Search response:", response);
                    return response;
                }, function (error) {
                    console.log("Error occured while flight search!", error);
                });
    
                return $q.all([dest])
                    .then(function (results) {
                        return {
                            dest: results[0]
                        };
                    });
            }

            function loadAvioForAdmin(){
                debugger;
                var airline = $http.get(urls.AVIO_ADMIN_API+'getCompany')
                .then(function (response) {
                    console.log("Avio response for admin:", response);
                    return response.data;
                }, function (error) {
                    console.log("Error occured when trying to retrieve airline for admin!", error);
                });
    
                return $q.all([airline])
                    .then(function (results) {
                        return {
                            airline: results[0]
                        };
                    });
            }
        }

})();