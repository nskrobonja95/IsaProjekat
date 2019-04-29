var app = angular.module('flightApp', ['ui.router', 'ngMaterial', 'ngMessages', 'ngCookies', 'star-rating', 'ui.select', 'ngSanitize']);

app.run(function($rootScope, $http, $cookies) {

    $http.defaults.headers.get = { 'Content-type': 'application/json' };

    $rootScope.globals = $cookies.getObject('globals') || {};
    //console.log($rootScope);
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    }

   
});

app.constant('urls', {
    USERS_SERVICE_API: 'http://localhost:8080/user/',
    UNREGISTERED_USERS_SERVICE_API: 'http://localhost:8080/app/',
    AVIO_ADMIN_API: 'http://localhost:8080/avioadmin/',
    HOTEL_ADMIN_API: 'http://localhost:8080/hoteladmin/',
    RESERVATION_SERVICE_API: 'http://localhost:8080/reserve/',
    AVIO_SERVICE_API: 'http://localhost:8080/avio/',
    REGISTER_SERVICE_API : 'http://localhost:8080/auth/register',
    LOGIN_SERVICE_API : 'http://localhost:8080/auth/login',
    FRIENDS_SERVICE_API: 'http://localhost:8080/friend/',
    AUTHENTICATION_SERVICE_API : 'http://localhost:8080/auth/',
    SEARCH_FRIENDS_SERVICE_API : 'http://localhost:8080/SpringBootCRUDApp/api/searchFriends/',
    ADDING_FRIEND_API: 'http://localhost:8080/SpringBootCRUDApp/api/addFriend/',
    REFUSING_FRIEND_API: 'http://localhost:8080/SpringBootCRUDApp/api/refuse/',
    ACCEPTING_FRIEND_API: 'http://localhost:8080/SpringBootCRUDApp/api/accept/'
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
            .state('home-abstract.avio-companies-list', {
                url:'/avio-companies',
                views: {
                    'avio-companies-list': {
                        templateUrl: "partials/avio-companies-list",
                        controller: "AvioController",
                        controllerAs: "avioCtrl"
                    }

                },
                resolve: {
                    initialCompaniesList: ['AvioService',function(AvioService){
                       
                        return AvioService.loadAllAvio();   
                      }],
                      initialDestinationsList: ['AvioService', function(AvioService){
                        return AvioService.loadAllDestiantions();
                      }]
                }
            })
            .state('home-abstract.flight-search-results', {
                url: '/flight-search-results',
                views: {
                    'flight-search-results': {
                        templateUrl: "partials/flight-search-results",
                        controller: "FlightSearchResultsController",
                        controllerAs: "fsCtrl"
                    }
                },
                params: {
                    flightSearchData: null 
                }
            })
            .state('home-abstract.hotel-search-results', {
                url: '/hotel-search-results',
                views: {
                    'hotel-search-results': {
                        templateUrl: "partials/hotel-search-results",
                        controller: "HotelSearchResultsController",
                        controllerAs: "hsCtrl"
                    }
                },
                params: {
                    hotelSearchData: null
                }
            })
            .state('home-abstract.hotel-profile', {
                url: '/hotel-profile/:hotelId/:checkIn/:checkOut',
                views: {
                    'hotel-profile': {
                        templateUrl: "partials/hotel-profile",
                        controller: "HotelProfileController",
                        controllerAs: "hpCtrl"
                    }
                },
                resolve: {
                    availableRooms: ['$stateParams', 'HotelService',function($stateParams, HotelService){
                        return HotelService.getAvailableRoomsForHotel($stateParams.hotelId, $stateParams.checkIn, $stateParams.checkOut);
                    }],
                    initialHotel: ['$stateParams', 'HotelService',function($stateParams, HotelService){
                        return HotelService.loadHotelById($stateParams.hotelId);
                    }],
                    initialHotelServices: ['$stateParams', 'HotelService',function($stateParams, HotelService){
                        return HotelService.loadHotelServicesById($stateParams.hotelId);
                    }]
                }
            })
            .state('home-abstract.avio-company',{
                url:'/avio-company/:companyId',
                views: {
                    'avio-company': {
                        templateUrl: "partials/avio-company",
                        controller: "AvioCompanyController",
                        controllerAs: "acCtrl"
                    }
                },
                resolve: {
                    initialCompanyData: ['$stateParams', 'AvioService',function($stateParams, AvioService){
                        return AvioService.loadAvioById($stateParams.companyId);   
                      }],
                      initialDestinationsData: ['$stateParams', 'AvioService',function($stateParams, AvioService){
                        return AvioService.loadDestinationsById($stateParams.companyId);   
                      }]
                }
            })
            .state('home-abstract.avio-admin',{
                url:'/avio-admin',
                views: {
                    'avio-admin': {
                        templateUrl: "partials/avio-admin",
                        controller: "AvioAdminController",
                        controllerAs: "avioAdminCtrl"
                    }
                },
                resolve: {
                    companyData: ['AvioService', function(AvioService){
                        return AvioService.loadAvioForAdmin();
                    }]
                }
            })
            .state('home-abstract.hotel-admin',{
                url:'/hotel-admin',
                views: {
                    'hotel-admin': {
                        templateUrl: "partials/hotel-admin",
                        controller: "HotelAdminController",
                        controllerAs: "hotelAdminCtrl"
                    }
                },
                resolve: {
                    hotelData: ['HotelService', function(HotelService){
                        return HotelService.loadHotelForAdmin();
                    }]
                }
            })
            .state('home-abstract.car-hire-companies-list', {
                url:'/car-hire-companies',
                views: {
                    'car-hire-companies-list': {
                        templateUrl: "partials/car-hire-companies-list",
                        controller: "CarHireController",
                        controllerAs: "chCtrl"
                    }

                },
                resolve: {
                    initialCarHireCompaniesList: ['$stateParams', 'CarHireService',function($stateParams, CarHireService){
                       
                        return CarHireService.loadAllCarHireCompanies();   
                      }]
                }
            })
            .state('home-abstract.hotels-list', {
                url:'/hotels',
                views: {
                    'hotels-list': {
                        templateUrl: "partials/hotels-list",
                        controller: "HotelController",
                        controllerAs: "hotelCtrl"
                    }

                },
                resolve: {
                    initialHotelsList: ['$stateParams', 'HotelService',function($stateParams, HotelService){
                       
                        return HotelService.loadAllHotels();   
                      }],
                      initialDestinationsList: ['AvioService', function(AvioService){
                        return AvioService.loadAllDestiantions();
                      }]
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
            $urlRouterProvider.otherwise('/avio-companies');

    }
]);