'use-strict';

angular.module('flightApp').controller('FriendsListController', 
    ['$scope', '$state', 'initialFriendsData','FriendService',
        function($scope, $state, initialFriendsData, FriendService) {
            var self = this;
            console.log(initialFriendsData.friendsList.length);
            self.friendsList = initialFriendsData.friendsList;
            self.deleteFriend = deleteFriend;

            function deleteFriend(friendsId){
                FriendService.refuseFriendship(friendsId)
                .then(
                    function(response){
                        FriendService.loadAllFriends()
                        .then(
                            function(response){
                                self.friendsList = response.friendsList;
                            },
                            function(errResponse){
        
                            }
                        );
                    },
                    function(errResponse){
        
                    }
                );
            }
           
            console.log("loaded friends:******");
            console.log("prvi clan");
            console.log(self.friendsList);
            console.log("**************");
            console.log("friendsOf");
         //   console.log(self.friendsList[0].friendsOf[0].friends);
        }]);