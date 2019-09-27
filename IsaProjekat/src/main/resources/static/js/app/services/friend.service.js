(function(){
    'use strict';     
    angular.module('flightApp').factory('FriendService', FriendService);
    FriendService.$inject=['$http', '$q', 'urls'];
    function FriendService($http, $q, urls){
        var service = {};         
        service.loadAllFriends = loadAllFriends;  
        service.addFriend = addFriend;
        service.refuseFriendship = refuseFriendship;
        service.acceptFriendship = acceptFriendship;
        return service; 

        function loadAllFriends(){
            var friendsList = $http.get(urls.FRIENDS_SERVICE_API+"friendsList")
                    .then(function(response) {
                        console.log("Friends response", response.data);
                        return response.data;
                    }, function(error) {
                        console.log("Error occured while initializing list of all friends!");
                    });
    
                return $q.all([friendsList])
                    .then(function(results) {
                        return {   friendsList: results[0] };        
                    });
        }
        function addFriend (friendsId) {
            console.log('Adding friend with id '+friendsId);
            var deferred = $q.defer();
            $http.post(urls.FRIENDS_SERVICE_API + "addFriend/" + friendsId)
                .then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error(errResponse.data.errorMessage);
                        deferred.reject(errResponse);
                    }
                );
            return deferred.promise;
        }
        function refuseFriendship( friendsId) {
            console.log('refusing friend with id '+friendsId);
            var deferred = $q.defer();
            $http.delete(urls.FRIENDS_SERVICE_API+ "refuse/" + friendsId)
                .then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error(errResponse.data.errorMessage);
                        deferred.reject(errResponse);
                    }
                );
            return deferred.promise;
        }
        function acceptFriendship(friendsId) {
            console.log('accepting friend with id '+friendsId);
            var deferred = $q.defer();
            $http.put(urls.FRIENDS_SERVICE_API+ "accept/" + friendsId)
                .then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error(errResponse.data.errorMessage);
                        deferred.reject(errResponse);
                    }
                );
            return deferred.promise;
        }
        function loadYourself(){
            var you = $http.get(urls.USERS_SERVICE_API+"get")
                    .then(function(response) {
                        console.log("User service reponded for geting your information", response.data);
                        return response.data;
                    }, function(error) {
                        console.log("Error occured while getting your informations!");
                    });
    
                return $q.all([you])
                    .then(function(results) {
                        return {   you: results[0] };        
                    });
        }
        function addUser(newUser){
            var response = $http.post(urls.USERS_SERVICE_API, newUser)
                    .then(function(response){
                        return response.data;
                    }, function(error){
                        console.log("Error occured while adding a new user!");
                    });
                    return $q.all([response])
                    .then(function(results) {
                        return {   response: results[0] };        
                    });
        }
        function deleteUser(id){
            var response = $http.delete(urls.USERS_SERVICE_API+id)
                                .then(function(){
                                    return response.data;
                                }, function(){
                                    console.log("Error occured while deleting user");
                                });
                    return $q.all([response])
                    .then(function(results) {
                        return {   response: results[0] };        
                    });
        }

        function editUser(user){
            var response = $http.put(urls.USERS_SERVICE_API, user)
                    .then(function(response){
                        return response.data;
                    }, function(error){
                        console.log("Error occured while editing an user!");
                    });
                    return $q.all([response])
                    .then(function(results) {
                        return {   response: results[0] };        
                    });
        }
    }
})();