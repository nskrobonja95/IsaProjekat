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
        service.updateBasicAvioInfo = updateBasicAvioInfo;
        service.addDestination = addDestination;
        service.removeDestinationForAdmin = removeDestinationForAdmin;
        service.loadAllFlightsForAdmin = loadAllFlightsForAdmin;
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
            
            function updateBasicAvioInfo(obj) {
                var updated = $http.put(urls.AVIO_ADMIN_API+'updateBasicCompanyInfo', obj)
                .then(function (response) {
                    console.log("Updated avio:", response);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while updating avio!", error);
                });
    
                return $q.all([updated])
                    .then(function (results) {
                        return {
                            updated: results[0]
                        };
                    });
            }

            function addDestination(obj) {
                var avio = $http.post(urls.AVIO_ADMIN_API+'addDestination', obj)
                .then(function (response) {
                    console.log("Updated avio:", response);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while updating avio!", error);
                });
    
                return $q.all([avio])
                    .then(function (results) {
                        return {
                            avio: results[0]
                        };
                    });
            }

            function removeDestinationForAdmin(id) {
                var dests = $http.delete(urls.AVIO_ADMIN_API+'removeDestination/' + id)
                .then(function (response) {
                    console.log("Response:", response);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while removing destinations!", error);
                });
    
                return $q.all([dests])
                    .then(function (results) {
                        return {
                            dests: results[0]
                        };
                    });
            }

            function loadAllFlightsForAdmin() {
                var flights = $http.get(urls.AVIO_ADMIN_API+'getFlights')
                .then(function (response) {
                    console.log("Response:", response);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while removing destinations!", error);
                });
    
                return $q.all([flights])
                    .then(function (results) {
                        return {
                            flights: results[0]
                        };
                    });
            }
        }

})();