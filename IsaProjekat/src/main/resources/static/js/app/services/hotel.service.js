(function () {
    'use strict';
    angular.module('flightApp').factory('HotelService', HotelService);
    HotelService.$inject = ['$http', '$q', 'urls'];

    function HotelService($http, $q, urls) {
        var service = {};
        service.loadAllHotels = loadAllHotels;
        service.loadHotelById = loadHotelById;
        service.search = search;
        service.getAvailableRoomsForHotel = getAvailableRoomsForHotel;
        service.loadHotelServicesById = loadHotelServicesById;
        service.bookRoom = bookRoom;
        service.loadHotelForAdmin = loadHotelForAdmin;
        service.updateBasicHotelInfo = updateBasicHotelInfo;
        service.loadHotelServicesForAdmin = loadHotelServicesForAdmin;
        service.saveService = saveService;
        service.removeService = removeService;
        service.loadRoomsForAdmin = loadRoomsForAdmin;
        service.saveRoom = saveRoom;
        service.addHotel = addHotel;
        service.getAllUserHotelReservations = getAllUserHotelReservations;
        service.getRoomById = getRoomById;
        service.loadHotelServicesByRoomId = loadHotelServicesByRoomId;
        service.editRoom = editRoom;
        service.deleteRoom = deleteRoom;
        service.cancelReservation = cancelReservation;
        service.rateReservation = rateReservation;
        service.loadHotelListForAdmin = loadHotelListForAdmin;
        service.createFastReservation = createFastReservation;
        service.loadFastReservationsForHotel = loadFastReservationsForHotel;
        service.fastHotelReserve = fastHotelReserve;
        service.loadHotelStatistics = loadHotelStatistics;

        return service;

        function loadHotelStatistics(hotelId){
            var stats = $http.get(urls.HOTEL_ADMIN_API+'hotelStatistics/'+hotelId)
            .then(function (response) {
                console.log("Hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing list of all hotel stats!", error);
                return error;
                
            });

        return $q.all([stats])
            .then(function (results) {
                return {
                    stats: results[0]
                };
            });
        }

        function loadHotelListForAdmin() {
            var list = $http.get(urls.HOTEL_ADMIN_API+'getHotels/')
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

        function loadFastReservationsForHotel(id) {
            var list = $http.get(urls.USERS_SERVICE_API+'getFastReservations/' + id)
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

        function fastHotelReserve(id) {
            var response = $http.put(urls.USERS_SERVICE_API+'fastHotelReserve/' + id)
            .then(function (response) {
                console.log("Hotel service response:", response);
                return response;
            }, function (error) {
                console.log("Error occured while initializing  hotels list!", error);
                return error;
            });

            return $q.all([response])
                .then(function (results) {
                    return {
                        response: results[0]
                    };
                });
        }

        function cancelReservation(reservationId){
            var canceled = $http.put(urls.USERS_SERVICE_API+'cancelAccomodation/'+reservationId)
            .then(function (response) {
                console.log("Hotel reservation canceled:", response);
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
        function rateReservation(resId, rate){
            var rating = $http.put(urls.USERS_SERVICE_API+'rateAccomodation/'+resId+"/"+rate)
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
        function getAllUserHotelReservations(){
            var hotelReservationsList = $http.get(urls.USERS_SERVICE_API+'retrieveUserAccReservations')
            .then(function (response) {
                console.log("Hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing list of all user hotel reservations!", error);
            });

        return $q.all([hotelReservationsList])
            .then(function (results) {
                return {
                    hotelReservationsList: results[0]
                };
            });
        }
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
            var hotel = $http.get(urls.UNREGISTERED_USERS_SERVICE_API+'hotels/'+id)
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

        function getAvailableRoomsForHotel(hotelId, checkIn, checkOut) {
            var searchObj = {};
            searchObj.hotel= hotelId;
            searchObj.checkIn = checkIn;
            searchObj.checkOut = checkOut;
            var availableRooms = $http.post(urls.UNREGISTERED_USERS_SERVICE_API+'searchAvailableRoomsForHotel/', searchObj)
            .then(function (response) {
                console.log("Search hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  Hotel!", error);
            });

            return $q.all([availableRooms])
                .then(function (results) {
                    return {
                        availableRooms: results[0]
                    };
                });
        }

        function loadHotelServicesById(hotelId) {
            var hotelServices = $http.get(urls.UNREGISTERED_USERS_SERVICE_API+'loadHotelServices/' + hotelId)
            .then(function (response) {
                console.log("Search hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  Hotel!", error);
            });

            return $q.all([hotelServices])
                .then(function (results) {
                    return {
                        hotelServices: results[0]
                    };
                });
        }

        function loadHotelServicesByRoomId(roomId) {
            var hotelServices = $http.get(urls.HOTEL_ADMIN_API+'loadHotelServicesByRoomId/' + roomId)
            .then(function (response) {
                console.log("Search hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  Hotel!", error);
            });

            return $q.all([hotelServices])
                .then(function (results) {
                    return {
                        hotelServices: results[0]
                    };
                });
        }

        function bookRoom(obj) {
            var bookingRoomResponse = $http.post(urls.USERS_SERVICE_API+'reserveRoom', obj)
            .then(function (response) {
                console.log("Book room response:", response.data);
                return response;
            }, function (error) {
                console.log("Error occured while bookung room!", error);
                return error;
            });

            return $q.all([bookingRoomResponse])
                .then(function (results) {
                    return {
                        bookingRoomResponse: results[0]
                    };
                });
        }

        function loadHotelForAdmin(id) {
            var hotel = $http.get(urls.HOTEL_ADMIN_API+'getHotel/'+id)
            .then(function (response) {
                console.log("Hotel service response:", response);
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

        function updateBasicHotelInfo(obj) {
            var updatedHotel = $http.put(urls.HOTEL_ADMIN_API+'updateBasicHotelInfo', obj)
            .then(function (response) {
                console.log("Book room response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while bookung room!", error);
            });

            return $q.all([updatedHotel])
                .then(function (results) {
                    return {
                        updatedHotel: results[0]
                    };
                });
        }

        function loadHotelServicesForAdmin(id) {
            var hotelServices = $http.get(urls.HOTEL_ADMIN_API+'getServices/'+id)
            .then(function (response) {
                console.log("Search hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  Hotel!", error);
            });

            return $q.all([hotelServices])
                .then(function (results) {
                    return {
                        hotelServices: results[0]
                    };
                });
        }

        function saveService(obj, hotelId) {
            var service = $http.post(urls.HOTEL_ADMIN_API+'saveService/'+hotelId, obj)
            .then(function (response) {
                console.log("Add service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while adding hotel service!", error);
            });

            return $q.all([service])
                .then(function (results) {
                    return {
                        service: results[0]
                    };
                });
        }

        function removeService(serviceId, hotelId) {
            var services = $http.delete(urls.HOTEL_ADMIN_API+'removeService/' + serviceId + "/" + hotelId)
            .then(function (response) {
                console.log("Add service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while removing hotel service!", error);
            });

            return $q.all([services])
                .then(function (results) {
                    return {
                        services: results[0]
                    };
                });
        }

        function loadRoomsForAdmin(id) {
            var hotelRooms = $http.get(urls.HOTEL_ADMIN_API+'getRooms/'+id)
            .then(function (response) {
                console.log("Load rooms for admin response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  admin room!", error);
            });

            return $q.all([hotelRooms])
                .then(function (results) {
                    return {
                        hotelRooms: results[0]
                    };
                });
        }

        function saveRoom(obj, id) {
            var response = $http.post(urls.HOTEL_ADMIN_API+'saveRoom/'+id, obj)
            .then(function (response) {
                console.log("Search hotel service response:", response);
                return response;
            }, function (error) {
                console.log("Error occured while initializing  Hotel!", error);
            });

            return $q.all([response])
                .then(function (results) {
                    return {
                        response: results[0]
                    };
                });
        }

        function editRoom(obj) {
            var response = $http.put(urls.HOTEL_ADMIN_API+'editRoom', obj)
            .then(function (response) {
                console.log("Search hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  Hotel!", error);
                return error;
            });

            return $q.all([response])
                .then(function (results) {
                    return {
                        response: results[0]
                    };
                });
        }

        function deleteRoom(id) {
            var response = $http.delete(urls.HOTEL_ADMIN_API+'deleteRoom/' + id)
            .then(function (response) {
                console.log("Search hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  Hotel!", error);
                return error;
            });

            return $q.all([response])
                .then(function (results) {
                    return {
                        response: results[0]
                    };
                });
        }

        function addHotel(obj) {
            var response = $http.post(urls.SYS_ADMIN_API+'saveHotel', obj)
            .then(function (response) {
                console.log("Search hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while adding  Hotel!", error);
                return error;
            });

            return $q.all([response])
                .then(function (results) {
                    return {
                        response: results[0]
                    };
                });
        }

        function getRoomById(id) {
            var room = $http.get(urls.HOTEL_ADMIN_API+'getRoom/' + id)
            .then(function (response) {
                console.log("Search hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  Hotel!", error);
            });

            return $q.all([room])
                .then(function (results) {
                    return {
                        room: results[0]
                    };
                });
        }

        function createFastReservation(obj, id) {
            var result = $http.post(urls.HOTEL_ADMIN_API+'createFastReservation/' + id, obj)
            .then(function (response) {
                console.log("Search hotel service response:", response);
                return response;
            }, function (error) {
                console.log("Error occured while initializing  Hotel!", error);
                return error;
            });

            return $q.all([result])
                .then(function (results) {
                    return {
                        result: results[0]
                    };
                });
        }
    }

})();