'use strict';

angular.module('flightApp').factory('SignupService',
    ['$http', '$q', 'urls',
        function ($http, $q, urls) {

            var factory = {
                getAllUsers: getAllUsers,
                getUser: getUser,
                createUser: createUser,
                updateUser: updateUser,
                removeUser: removeUser,
                getUserByUsername: getUserByUsername
            };

            return factory;

          

            function getAllUsers(){
                return $localStorage.users;
            }

         
            function getUser (id) {
                var userData = $http.get(urls.USER_SERVICE_API + id)
                    .then(function(response) {
                        return response.data;
                    }, function(error) {
                        console.log("Error occured while initializing user data!");
                    });
    
                return $q.all([userData])
                    .then(function(results) {
                        return {   userData: results[0] };        
                });
            }

            function getUserByUsername (username) {
                var userData = $http.get(urls.USER_SERVICE_API + username)
                    .then(function(response) {
                        return response.data;
                    }, function(error) {
                        console.log("Error occured while initializing user data!");
                    });

                return $q.all([userData])
                    .then(function(results) {
                        return {   userData: results[0] };        
                });
            }
            function createUser(user) {
                console.log('Creating User');
                console.log(user);
                var deferred = $q.defer();
                console.log(urls.REGISTER_SERVICE_API);
                $http.post(urls.REGISTER_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating User : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateUser(user, id) {
                console.log('Updating User with id '+id);
                var deferred = $q.defer();
                $http.put(urls.USER_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error(errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeUser(id) {
                console.log('Removing User with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing User with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);