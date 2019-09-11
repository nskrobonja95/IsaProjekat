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
        service.cancelReservation = cancelReservation;
        return service;

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
            // debugger;
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

        function bookRoom(obj) {
            var bookingRoomResponse = $http.post(urls.RESERVATION_SERVICE_API+'room', obj)
            .then(function (response) {
                console.log("Book room response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while bookung room!", error);
            });

            return $q.all([bookingRoomResponse])
                .then(function (results) {
                    return {
                        bookingRoomResponse: results[0]
                    };
                });
        }

        function loadHotelForAdmin() {
            var hotel = $http.get(urls.HOTEL_ADMIN_API+'getHotel')
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

        function loadHotelServicesForAdmin() {
            var hotelServices = $http.get(urls.HOTEL_ADMIN_API+'getServices/')
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

        function saveService(obj) {
            var service = $http.post(urls.HOTEL_ADMIN_API+'saveService', obj)
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

        function removeService(id) {
            var services = $http.delete(urls.HOTEL_ADMIN_API+'removeService/' + id)
            .then(function (response) {
                console.log("Add service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while adding hotel service!", error);
            });

            return $q.all([services])
                .then(function (results) {
                    return {
                        services: results[0]
                    };
                });
        }

        function loadRoomsForAdmin() {
            var hotelRooms = $http.get(urls.HOTEL_ADMIN_API+'getRooms/')
            .then(function (response) {
                console.log("Search hotel service response:", response.data);
                return response.data;
            }, function (error) {
                console.log("Error occured while initializing  Hotel!", error);
            });

            return $q.all([hotelRooms])
                .then(function (results) {
                    return {
                        hotelRooms: results[0]
                    };
                });
        }

        function saveRoom(obj) {
            var response = $http.post(urls.HOTEL_ADMIN_API+'saveRoom', obj)
            .then(function (response) {
                console.log("Search hotel service response:", response.data);
                return response.data;
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

        function addHotel(obj) {
            var response = $http.post(urls.SYS_ADMIN_API+'saveHotel', obj)
            .then(function (response) {
                console.log("Search hotel service response:", response.data);
                return response.data;
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
    }

})();