var app = angular.module('flightApp', ['ui.router', 'ngMaterial', 'ngMessages', 'ngCookies']);

app.run(function($rootScope, $http, $cookies) {

    $http.defaults.headers.get = { 'Content-type': 'application/json' };

    $rootScope.globals = $cookies.getObject('globals') || {};
    console.log($rootScope);
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    }

   
});

app.constant('urls', {
    USERS_SERVICE_API: 'http://localhost:8080/user/',
    REGISTER_SERVICE_API : 'http://localhost:8080/auth/register',
    LOGIN_SERVICE_API : 'http://localhost:8080/auth/login',
    FRIENDS_SERVICE_API: 'http://localhost:8080/friend/',
    CINEMAS_SERVICE_API : 'http://localhost:8080/SpringBootCRUDApp/cinemasApi/cinemas',
    AUTHENTICATION_SERVICE_API : 'http://localhost:8080/auth/',
    THEATRES_SERVICE_API : 'http://localhost:8080/SpringBootCRUDApp/theatresApi/theatres',
    SEARCH_FRIENDS_SERVICE_API : 'http://localhost:8080/SpringBootCRUDApp/api/searchFriends/',
    ADDING_FRIEND_API: 'http://localhost:8080/SpringBootCRUDApp/api/addFriend/',
    REFUSING_FRIEND_API: 'http://localhost:8080/SpringBootCRUDApp/api/refuse/',
    ACCEPTING_FRIEND_API: 'http://localhost:8080/SpringBootCRUDApp/api/accept/',
    OBJECT_SERVICE_API : 'http://localhost:8080/SpringBootCRUDApp/theatresApi/registerObject'
});

app.config(['$stateProvider', '$urlRouterProvider', '$mdThemingProvider',
    function ($stateProvider, $urlRouterProvider, $mdThemingProvider) {

        $stateProvider
            .state('home-abstract', {
                abstract: true,
                views:{
                    '@':{
                        templateUrl: "partials/home-abstract",
                        controller: "HomeController",
                        controllerAs: "homeCtrl"
                    }
                }
                

            })
            .state('home-abstract.flights', {
                url:'/flights',
                views: {
                    'flight-search': {
                        templateUrl: "partials/flight-search"
                    }

                }


            })
            .state('home-abstract.confirmReg', {
                url:'/confirmReg',
                views: {
                    'confirm': {
                        template: "Verification Email sent. Confirm your account."
                    }
                }
            })
            .state('home-abstract.profile-abstract', {
                url: '/:username',
                abstract: true,
                views: {
                    'profile-abstract': {
                      templateUrl: 'partials/profile-abstract'
                     
                  }
                }   
            })
            .state('home-abstract.profile-abstract.profile-overview', {
                url: '/overview',
                resolve: {
                    yourInitialData: [ 'UserService',function(UserService){
                          return UserService.loadYourself();   
                      }]
                },
                views: {
                    'profile-overview': {
                      templateUrl: 'partials/profile-overview',
                      controller: 'ProfileOverviewController',
                      controllerAs: 'pOCtrl'
                  }
                }  
            })
            .state('home-abstract.profile-abstract.friends-abstract', {
                url: '/friends',
                abstract: true,
                views: {
                    'friends-abstract': {
                      templateUrl: 'partials/friends-abstract',
                      controller: 'FriendController',
                      controllerAs: 'friendCtrl'
                     
                  }
                }   
            })
            .state('home-abstract.profile-abstract.friends-abstract.friends-list', {
                url: '/friendsList',
                resolve: {
                    initialFriendsData: ['FriendService',function(FriendService){
                        return FriendService.loadAllFriends();   
                      }]
                },
                views: {
                    'friends-list': {
                      templateUrl: 'partials/friends-list',
                      controller: 'FriendsListController',
                      controllerAs: 'fLCtrl'
                  }
                }  
            })
            .state('home-abstract.profile-abstract.friends-abstract.friends-search', {
                url: '/friendsSearch/:searchValue',
                resolve: {
                    initialData: ['$stateParams', 'UserService',function($stateParams, UserService){
                       
                        return UserService.searchUsers($stateParams.searchValue);   
                      }],
                      usersInitialData:  ['UserService',function(UserService){
                       
                        return UserService.loadYourself();   
                      }]
                },
                views: {
                    'friends-search': {
                      templateUrl: 'partials/friends-search',
                      controller: 'FriendsSearchController',
                      controllerAs: 'fsCtrl'
                  }
                }  
            })
            .state('home-abstract.settings-abstract', {
                url: '/settings',
                abstract: true,
                views: {
                    'settings-abstract': {
                      templateUrl: 'partials/settings-abstract'
                     
                  }
                }   
            })
            .state('home-abstract.settings-abstract.general-settings', {
                url: '/generalSettings',
                resolve: {
                    initialData: ['UserService',function(UserService){
                          return UserService.loadYourself();   
                      }]
                },
                views: {
                    'general-settings': {
                      templateUrl: 'partials/general-settings',
                      controller: 'GeneralSettingsController',
                      controllerAs: 'gsCtrl'
                  }
                }
                 
            })
            .state('home-abstract.settings-abstract.security-settings', {
                url: '/security',
                resolve: {
                    initialData: ['UserService',function(UserService){
                        return UserService.loadYourself();   
                    }]
                },
                views: {
                    'security-settings': {
                      templateUrl: 'partials/security-settings',
                      controller: 'SecuritySettingsController',
                      controllerAs: 'securityCtrl'
                  }
                }  
            })
            $urlRouterProvider.otherwise('/flights');

    }
]);