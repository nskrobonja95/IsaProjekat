angular.module('flightApp').controller('FriendsSearchController',['$state', '$rootScope', 'initialData','usersInitialData','FriendService',
 'UserService',function($state, $rootScope, initialData,usersInitialData, FriendService,UserService){
    var self = this;

    self.searchResults = initialData.usersList;
    self.userData = usersInitialData.you;
    self.addFriend = addFriend;
    self.refuse = refuse;
    self.accept = accept;
    self.showAdd = showAdd;
    self.searchValue='';
    console.log(self.searchResults);
    console.log("**************");
    console.log(self.userData);
    function addFriend(friendsId){
        FriendService.addFriend(friendsId)
        .then(
            function(response){
                UserService.loadYourself()
                .then(
                    function(response){
                        self.userData = response.you;
                    },
                    function(errResponse){

                    }
                );
            },
            function(errResponse){

            }
        );
    
    }
    function refuse(friendsId){
        FriendService.refuseFriendship( friendsId)
        .then(
            function(response){
                UserService.loadYourself()
                .then(
                    function(response){
                        self.userData = response.you;
                    },
                    function(errResponse){

                    }
                );
            },
            function(errResponse){

            }
        );
    }
    function accept(friendsId){
        FriendService.acceptFriendship(friendsId)
        .then(
            function(response){
                UserService.loadYourself()
                .then(
                    function(response){
                        self.userData = response.you;
                    },
                    function(errResponse){

                    }
                );
            },
            function(errResponse){

            }
        );
    }
    function showAdd(friendsId){
        for(var i =0; i<self.userData.friends.length; i++){
            if((self.userData.friends[i].friendsKey == friendsId) && self.userData.friends[i].sent === "requested"){
                return 1;
            }else if((self.userData.friends[i].friendsKey == friendsId) && self.userData.friends[i].sent === "received"){
                return 2;
            }else if((self.userData.friends[i].friendsKey == friendsId) && self.userData.friends[i].sent === "accepted"){
                return 3;
            }
        }
        
        return 0;
    }
}]);