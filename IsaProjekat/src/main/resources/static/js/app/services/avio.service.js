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
        service.loadDestinationsForAdmin = loadDestinationsForAdmin;
        service.saveFlight = saveFlight;
        service.addAirline = addAirline;
        service.loadRestOfDestinations = loadRestOfDestinations;
        service.saveDestination = saveDestination;
        service.loadFlight = loadFlight;
        service.loadSeatsForFlight = loadSeatsForFlight;
        service.makeReservation = makeReservation;
        service.sendInvitation = sendInvitation;

        service.getAllUserFlightReservations = getAllUserFlightReservations;
        service.cancelReservation = cancelReservation;

        service.retrieveFastReservationsForAvioCompany = retrieveFastReservationsForAvioCompany;
        service.makeFastReservation = makeFastReservation;
        service.rateReservation = rateReservation;
        service.loadAvioStatistics = loadAvioStatistics;
        service.loadAvioListForAdmin = loadAvioListForAdmin;
<<<<<<< HEAD
        service.deleteDestination = deleteDestination;
=======
        service.getGrosForInterval = getGrosForInterval;
>>>>>>> e48bfdad929e63e4ee1f3a8de00f29891978b2c4
        return service;
        
        function getGrosForInterval(avioId, grossObj){
            var grossResponse = $http.post(urls.AVIO_ADMIN_API+'getGrossForInterval/'+avioId, grossObj)
            .then(function (response) {
                console.log("Avio service response:", response);
                return response;
            }, function (error) {
                console.log("Error occured while calculating  gross for interval!", error);
                return error;
                
            });

            return $q.all([grossResponse])
                .then(function (results) {
                    return {
                        grossResponse: results[0]
                    };
                });
        }

        function loadAvioListForAdmin() {
            var list = $http.get(urls.AVIO_ADMIN_API+'getCompanies/')
            .then(function (response) {
                console.log("Hotel service response:", response);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  hotels list!", error);
            });

            return $q.all([list])
                .then(function (results) {
                    return {
                        list: results[0]
                    };
                });
        }

        function loadAvioStatistics(avioId){
            var stats = $http.get(urls.AVIO_ADMIN_API+'avioStatistics/'+avioId)
            .then(function (response) {
                console.log("Avio service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing list of all avio stats!", error);
            });

        return $q.all([stats])
            .then(function (results) {
                return {
                    stats: results[0]
                };
            });
        }

        function cancelReservation(reservationId){
            var canceled = $http.put(urls.USERS_SERVICE_API+'cancelFlight/'+reservationId)
            .then(function (response) {
                console.log("Flight canceled:", response);
                return response.data;
            }, function (error) {
                console.log("Error occured while canceling reservations!", error);
            });

            return $q.all([canceled])
                .then(function (results) {
                    return {
                        canceled: results[0]
                    };
                });
        }
        function rateReservation(ratingObj){
            var rating = $http.put(urls.USERS_SERVICE_API+'rateFlight', ratingObj)
            .then(function (response) {
                console.log("Avio service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while rating reservations!", error);
            });

        return $q.all([rating])
            .then(function (results) {
                return {
                    rating: results[0]
                };
            });
        }
        function getAllUserFlightReservations(){
            var flightReservationsList = $http.get(urls.USERS_SERVICE_API+'flightUserReservationsList')
            .then(function (response) {
                console.log("Avio service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing list of all user reservations!", error);
            });

        return $q.all([flightReservationsList])
            .then(function (results) {
                return {
                    flightReservationsList: results[0]
                };
            });
        }

        

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

        function loadRestOfDestinations(id){
            console.log(id);
            var destList = $http.get(urls.AVIO_ADMIN_API+'getRestOfDestinations/'+id)
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

            function loadAvioForAdmin(id){
                var airline = $http.get(urls.AVIO_ADMIN_API+'getCompany/'+id)
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

            function addDestination(obj, companyId) {
                var avio = $http.post(urls.AVIO_ADMIN_API+'addDestination/'+companyId, obj)
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

            function saveDestination(obj) {
                
                var avio = $http.post(urls.SYS_ADMIN_API+'saveDestination/', obj)
                .then(function (response) {
                    console.log("Updated avio:", response);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while updating avio!", error);
                    return error;
                });
    
                return $q.all([avio])
                    .then(function (results) {
                        return {
                            avio: results[0]
                        };
                    });
            }

            function saveDestination(obj) {
                debugger;
                var avio = $http.put(urls.SYS_ADMIN_API+'editDestination/', obj)
                .then(function (response) {
                    console.log("Updated avio:", response);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while updating avio!", error);
                    return error;
                });
    
                return $q.all([avio])
                    .then(function (results) {
                        return {
                            avio: results[0]
                        };
                    });
            }

            function removeDestinationForAdmin(destId, avioId) {
                var dests = $http.delete(urls.AVIO_ADMIN_API+'removeDestination/' + destId + "/" + avioId)
                .then(function (response) {
                    console.log("Response:", response);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while removing destinations!", error);
                    return error;
                });
    
                return $q.all([dests])
                    .then(function (results) {
                        return {
                            dests: results[0]
                        };
                    });
            }

            function loadAllFlightsForAdmin(avioId) {
                var flights = $http.get(urls.AVIO_ADMIN_API+'getFlights/'+avioId)
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
            function loadDestinationsForAdmin(avioId) {
                var dests = $http.get(urls.AVIO_ADMIN_API+'getDestinations/'+avioId)
                .then(function (response) {
                    console.log("Response:", response);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while loading destinations!", error);
                });
    
                return $q.all([dests])
                    .then(function (results) {
                        return {
                            dests: results[0]
                        };
                    });
            }

            function saveFlight(obj, avioId) {
                var flight = $http.post(urls.AVIO_ADMIN_API+'createFlight/'+avioId, obj)
                .then(function (response) {
                    console.log("Response:", response);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while creating flight!", error);
                });
    
                return $q.all([flight])
                    .then(function (results) {
                        return {
                            flight: results[0]
                        };
                    });
            }

            function addAirline(obj) {
                var response = $http.post(urls.SYS_ADMIN_API+'saveAirline', obj)
                .then(function (response) {
                    console.log("Response:", response);
                    return response.data;
                }, function (error) {
                    return error;
                    console.log("Error occured while removing destinations!", error);
                });
    
                return $q.all([response])
                    .then(function (results) {
                        return {
                            response: results[0]
                        };
                    });
            }

            function loadFlight(id) {
                var flight = $http.get(urls.USERS_SERVICE_API+'getFlight/'+id)
                .then(function (response) {
                    console.log("Avio service response(get flight):", response.data);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while initializing  avio company destinations!", error);
                });

                return $q.all([flight])
                    .then(function (results) {
                        return {
                            flight: results[0]
                        };
                    });
            }

            function loadSeatsForFlight(id) {
                var seats = $http.get(urls.USERS_SERVICE_API+'getSeats/'+id)
                .then(function (response) {
                    //console.log("Avio service response(get seats):", response.data);
                    return response.data;
                }, function (error) {
                    //console.log("Error occured while initializing  avio company destinations!", error);
                    return error.data;
                });

                return $q.all([seats])
                    .then(function (results) {
                        return {
                            seats: results[0]
                        };
                    });
            }

            function deleteDestination(id) {
                debugger;
                var response = $http.delete(urls.SYS_ADMIN_API+'deleteDestination/'+id)
                .then(function (response) {
                    console.log("Avio service response(get seats):", response.data);
                    return response;
                }, function (error) {
                    console.log("Error occured while initializing  avio company destinations!", error);
                    return error;
                });

                return $q.all([response])
                    .then(function (results) {
                        return {
                            response: results[0]
                        };
                    });
            }

            // function makeReservation(obj) {
            //     var response = $http.post(urls.USERS_SERVICE_API+'reserve', obj)
            //     .then(function (response) {
            //         console.log("Response:", response);
            //         return response;
            //     }, function (error) {
            //         console.log("Error occured while removing destinations!", error);
            //         return error;
            //     });
    
            //     return $q.all([response])
            //         .then(function (results) {
            //             return {
            //                 response: results[0]
            //             };
            //         });
            // }

            function makeReservation(obj) {
                var response = $http.post(urls.USERS_SERVICE_API+'reserve', obj)
                .then(function (response) {
                    console.log("Response:", response);
                    return response;
                }, function (error) {
                    console.log("Error occured while removing destinations!", error);
                    return error;
                });
    
                return $q.all([response])
                    .then(function (results) {
                        return {
                            response: results[0]
                        };
                    });
            }

            function sendInvitation(obj) {
                var response = $http.post(urls.USERS_SERVICE_API+'sendInvitation', obj)
                .then(function (response) {
                    console.log("Response:", response);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while removing destinations!", error);
                });
    
                return $q.all([response])
                    .then(function (results) {
                        return {
                            response: results[0]
                        };
                    });
            }

            function retrieveFastReservationsForAvioCompany(id) {
                var fastReservations = $http.get(urls.UNREGISTERED_USERS_SERVICE_API+'retrieveFastResAvioComp/'+id)
                .then(function (response) {
                    console.log("Avio service response:", response.data);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while initializing  avio company destinations!", error);
                });

                return $q.all([fastReservations])
                    .then(function (results) {
                        return {
                            fastReservations: results[0]
                        };
                    });
            }

            function makeFastReservation(id) {
                var fastReservation = $http.post(urls.USERS_SERVICE_API+'makeFastReservation/'+id)
                .then(function (response) {
                    console.log("Avio service response:", response.data);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while initializing  avio company destinations!", error);
                });

                return $q.all([fastReservation])
                    .then(function (results) {
                        return {
                            fastReservation: results[0]
                        };
                    });
            }

        }

})();