angular.module('flightApp').controller('FriendController',
    ['$state', function($state) {
        var self = this;
        
        self.goToSearch = goToSearch;

        function goToSearch(){
            if(self.searchValue)
            $state.go('home-abstract.profile-abstract.friends-abstract.friends-search',{searchValue: self.searchValue});
        }
     } ]);