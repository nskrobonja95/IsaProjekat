(function () {
    'use strict';
    angular.module('flightApp').factory('UserService', UserService);
    UserService.$inject = ['$http', '$q', 'urls'];

    function UserService($http, $q, urls) {
        var service = {};
        service.loadYourself = loadYourself;
        service.searchUsers = searchUsers;
        // service.addUser = addUser;
        // service.deleteUser = deleteUser;
        service.editUser = editUser;
        service.loadAllAirlineAdmins = loadAllAirlineAdmins;
        service.loadAllHotelAdmins = loadAllHotelAdmins;
        return service;

        function loadAllAirlineAdmins(){
            var avioAdmins = $http.get(urls.SYS_ADMIN_API + "getAvioAdminUsers/")
                .then(function(response){
                    console.log("Users response for avio admins search", response.data);
                    return response.data;
                }, function(error){
                    console.log("Error occured while initializing list of avio admin users!");
                    return error
                });
            return $q.all([avioAdmins])
                .then(function(results){
                    return {
                        avioAdmins: results[0]
                    };
                });
        }
        function loadAllHotelAdmins(){
            var hotelAdmins = $http.get(urls.SYS_ADMIN_API + "getHotelAdminUsers/")
                .then(function(response){
                    console.log("Users response for hotel admins search", response.data);
                    return response.data;
                }, function(error){
                    console.log("Error occured while initializing list of hotel admin users!");
                    return error
                });
            return $q.all([hotelAdmins])
                .then(function(results){
                    return {
                        hotelAdmins: results[0]
                    };
                });
        }
        function searchUsers(searchQuery) {
            var usersList = $http.get(urls.USERS_SERVICE_API + "searchFriends/" + searchQuery)
                .then(function (response) {
                    console.log("Users response for friends search", response.data);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while initializing list of searched users!");
                    return error;
                });
            return $q.all([usersList])
                .then(function (results) {
                    return {
                        usersList: results[0]
                    };
                });
        }

        function loadAllUsers() {
            var you = $http.get(urls.USERS_SERVICE_API)
                .then(function (response) {
                    console.log("Users response", response.data);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while initializing list of all users!");
                });

            return $q.all([you])
                .then(function (results) {
                    return {
                        you: results[0]
                    };
                });
        }

        function loadYourself() {
            var you = $http.get(urls.USERS_SERVICE_API + "get")
                .then(function (response) {
                    console.log("User service reponded for geting your information", response.data);
                    return response.data;
                }, function (error) {
                    console.log("Error occured while getting your informations!");
                });

            return $q.all([you])
                .then(function (results) {
                    return {
                        you: results[0]
                    };
                });
        }

        function addUser(newUser) {
            var response = $http.post(urls.USERS_SERVICE_API, newUser)
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    console.log("Error occured while adding a new user!");
                });
            return $q.all([response])
                .then(function (results) {
                    return {
                        response: results[0]
                    };
                });
        }

        function deleteUser(id) {
            var response = $http.delete(urls.USERS_SERVICE_API + id)
                .then(function () {
                    return response.data;
                }, function () {
                    console.log("Error occured while deleting user");
                });
            return $q.all([response])
                .then(function (results) {
                    return {
                        response: results[0]
                    };
                });
        }

        function editUser(username, user) {
            var response = $http.put(urls.USERS_SERVICE_API + "edit/" + username, user)
                .then(function (response) {
                    return response;
                }, function (error) {
                    console.log("Error occured while editing an user!");
                    return error;
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