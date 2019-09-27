var app = angular.module('flightApp', ['ngAnimate','ui.router', 'ngMaterial', 'ngMessages', 'ngCookies', 'star-rating', 'ui.select', 'ngSanitize']);

app.run(function($rootScope, $http, $cookies) {

    $http.defaults.headers.get = { 'Content-type': 'application/json' };

    $rootScope.globals = $cookies.getObject('globals') || {};
    //console.log($rootScope);
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    }

   
});

app.constant('urls', {
    USERS_SERVICE_API: 'http://192.168.1.13:8080/user/',
    UNREGISTERED_USERS_SERVICE_API: 'http://192.168.1.13:8080/app/',
    AVIO_ADMIN_API: 'http://192.168.1.13:8080/avioadmin/',
    HOTEL_ADMIN_API: 'http://192.168.1.13:8080/hoteladmin/',
    ENTITY_ADMIN_API: 'http://192.168.1.13:8080/entityadmin/',
    SYS_ADMIN_API: 'http://192.168.1.13:8080/admin/',
    RESERVATION_SERVICE_API: 'http://192.168.1.13:8080/reserve/',
    AVIO_SERVICE_API: 'http://192.168.1.13:8080/avio/',
    REGISTER_SERVICE_API : 'http://192.168.1.13:8080/auth/register',
    LOGIN_SERVICE_API : 'http://192.168.1.13:8080/auth/login',
    FRIENDS_SERVICE_API: 'http://192.168.1.13:8080/friend/',
    AUTHENTICATION_SERVICE_API : 'http://192.168.1.13:8080/auth/',
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
            .state('home-abstract.flight-reservation',{
                url: '/reserve-flight/:direct_id/:return_id',
                views: {
                    'flight-reservation': {
                        templateUrl: "partials/flight-reservation",
                        controller: "FlightReservationController",
                        controllerAs: "frCtrl"
                    }
                },
                resolve: {
                    direct_flight: ['$stateParams', 'AvioService',function($stateParams,AvioService){
                        return AvioService.loadFlight($stateParams.direct_id);
                    }],
                    return_flight: ['$stateParams', 'AvioService', function($stateParams,AvioService){
                        return AvioService.loadFlight($stateParams.return_id);
                    }],
                    dir_seats: ['$stateParams', 'AvioService',function($stateParams,AvioService){
                        return AvioService.loadSeatsForFlight($stateParams.direct_id);
                    }],
                    ret_seats:['$stateParams', 'AvioService',function($stateParams,AvioService){
                        return AvioService.loadSeatsForFlight($stateParams.return_id);
                    }]
                }
            })
            .state('form', {
                url: '/form',
                templateUrl: 'form.html',
                controller: 'formController'
            })

            // url will be /form/interests
            .state('home-abstract.flight-reservation.interests', {
                url: '/interests',
                templateUrl: 'partials/form-interests'
            })
            
            // nested states 
            // each of these sections will have their own view
            // url will be nested (/form/profile)
            .state('home-abstract.flight-reservation.profile', {
                url: '/profile',
                templateUrl: 'partials/form-profile'
            })
            
            // url will be /form/payment
            .state('home-abstract.flight-reservation.payment', {
                url: '/payment',
                templateUrl: 'partials/form-payment'
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
            .state('home-abstract.hotel-recommendation-results', {
                url: '/hotel-recommendation-results',
                views: {
                    'hotel-recommendation-results': {
                        templateUrl: "partials/hotel-recommendation-results",
                        controller: "HotelRecommendationResultsController",
                        controllerAs: "hrsCtrl"
                    }
                },
                params: {
                    hotelSearchData: null,
                    flightReservations: null
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
                params: {
                    flightReservations: null
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
            .state('home-abstract.user-hotel',{
                url:'/user-hotel/:hotelId',
                views: {
                    'user-hotel': {
                        templateUrl: "partials/user-hotel",
                        controller: "UserHotelController",
                        controllerAs: "uhCtrl"
                    }
                },
                resolve: {
                      hotelData: ['$stateParams', 'HotelService',function($stateParams, HotelService){
                        return HotelService.loadHotelById($stateParams.hotelId);   
                      }],
                      initialHotelServices: ['HotelService', '$stateParams',function(HotelService, $stateParams){
                          return HotelService.loadHotelServicesForAdmin($stateParams.hotelId);
                      }]
                }
            })
            .state('home-abstract.hotel-fast-reservations',{
                url:'/hotel-fast-reservations/:hotelId',
                views: {
                    'hotel-fast-reservations': {
                        templateUrl: "partials/hotel-fast-reservations",
                        controller: "HotelFastReservationsController",
                        controllerAs: "hotelFastCtrl"
                    }
                },
                resolve: {
                      fastReservations: ['$stateParams', 'HotelService',function($stateParams, HotelService){
                        return HotelService.loadFastReservationsForHotel($stateParams.hotelId);   
                      }]
                }
            })
            .state('home-abstract.avio-fast-reservations',{
                url:'/avio-fast-reservations/:companyId',
                views: {
                    'avio-fast-reservations': {
                        templateUrl: "partials/avio-fast-reservations",
                        controller: "AvioFastReservationsController",
                        controllerAs: "afrCtrl"
                    }
                },
                resolve: {
                    initialFastReservationSeats: ['$stateParams', 'AvioService',function($stateParams, AvioService){
                        return AvioService.retrieveFastReservationsForAvioCompany($stateParams.companyId);   
                      }]
                }
            })
            .state('home-abstract.avio-admin-avio-profile.avio-statistics',{
                url:'/avio-statistics',
                views: {
                    'avio-statistics': {
                        templateUrl: "partials/avio-statistics",
                        controller: "AvioStatsController",
                        controllerAs: "avioStatsCtrl"
                    }
                },
                resolve: {
                    avioStatisticData: ['AvioService','$stateParams', function(AvioService, $stateParams){
                        return AvioService.loadAvioStatistics($stateParams.avioId);
                    }]
                }
            })
            .state('home-abstract.avio-admin-avio-profile.avio-admin',{
                url:'/avio-admin',
                views: {
                    'avio-admin': {
                        templateUrl: "partials/avio-admin",
                        controller: "AvioAdminController",
                        controllerAs: "avioAdminCtrl"
                    }
                },
                resolve: {
                    companyData: ['AvioService', '$stateParams', function(AvioService, $stateParams){
                        return AvioService.loadAvioForAdmin($stateParams.avioId);
                    }],
                    restOfDestinationsList: ['AvioService','$stateParams', function(AvioService, $stateParams){
                        return AvioService.loadRestOfDestinations($stateParams.avioId);
                    }]
                }
            })
            .state('home-abstract.avio-admin-welcome',{
                url:'/avio-admin-welcome',
                views: {
                    'avio-admin-welcome': {
                        templateUrl: "partials/avio-admin-welcome",
                        controller: "AvioAdminWelcomeController",
                        controllerAs: "aawCtrl"
                    }
                },
                resolve: {
                    initalAvioList: ['AvioService','$rootScope', function(AvioService, $rootScope){
                        return AvioService.loadAvioListForAdmin();
                    }]
                }
            })
            .state('home-abstract.avio-admin-avio-profile',{
                url:'/avio-admin-avio-profile/:avioId',
                abstract:true,
                views: {
                    'avio-admin-avio-profile': {
                        templateUrl: "partials/avio-admin-avio-profile"
                    }
                }
            })
            .state('home-abstract.hotel-admin-welcome',{
                url:'/hotel-admin-welcome',
                views: {
                    'hotel-admin-welcome': {
                        templateUrl: "partials/hotel-admin-welcome",
                        controller: "HotelAdminWelcomeController",
                        controllerAs: "hawCtrl"
                    }
                },
                resolve: {
                    initalHotelList: ['HotelService', function(HotelService){
                        return HotelService.loadHotelListForAdmin();
                    }]
                }
            })
            .state('home-abstract.hotel-admin-hotel-profile',{
                url:'/hotel-admin-hotel-profile/:hotelId',
                abstract:true,
                views: {
                    'hotel-admin-hotel-profile': {
                        templateUrl: "partials/hotel-admin-hotel-profile",
                        controller: "HotelAdminHotelProfileController",
                        controllerAs: "hahpCtrl"
                    }
                }
            })
            .state('home-abstract.hotel-admin-hotel-profile.hotel-admin',{
                url:'/hotel-admin',
                views: {
                    'hotel-admin': {
                        templateUrl: "partials/hotel-admin",
                        controller: "HotelAdminController",
                        controllerAs: "hotelAdminCtrl"
                    }
                },
                resolve: {
                    hotelData: ['HotelService','$stateParams', function(HotelService, $stateParams){
                        return HotelService.loadHotelForAdmin($stateParams.hotelId);
                    }],
                    initialHotelServices: ['HotelService', '$stateParams',function(HotelService, $stateParams){
                        return HotelService.loadHotelServicesForAdmin($stateParams.hotelId);
                    }]
                }
            })
            .state('home-abstract.hotel-admin-hotel-profile.hotel-admin-statistics',{
                url:'/hotel-admin-statistics',
                views: {
                    'hotel-admin-statistics': {
                        template: "partials/hotel-admin"
                    }
                }
            })
            .state('home-abstract.add-service',{
                url:'/add-service/:hotelId',
                views: {
                    'add-service': {
                        templateUrl: "partials/add-service",
                        controller: "AddServiceController",
                        controllerAs: "addServiceCtrl"
                    }
                }
            })
            .state('home-abstract.create-room',{
                url:'/create-room/:hotelId',
                views: {
                    'create-room': {
                        templateUrl: "partials/create-room",
                        controller: "CreateRoomController",
                        controllerAs: "createRoomCtrl"
                    }
                },
                resolve: {
                    initialHotelServices: ['HotelService','$stateParams',function(HotelService, $stateParams){
                        return HotelService.loadHotelServicesForAdmin($stateParams.hotelId);
                    }]
                }
            })
            .state('home-abstract.create-room-fast-reservation',{
                url:'/create-room-fast-reservation/:roomId',
                views: {
                    'create-room-fast-reservation': {
                        templateUrl: "partials/create-room-fast-reservation",
                        controller: "CreateRoomFastReservationController",
                        controllerAs: "crfCtrl"
                    }
                }
            })
            .state('home-abstract.hotel-admin-hotel-profile.admin-rooms-list',{
                url:'/admin-rooms-list',
                views: {
                    'admin-rooms-list': {
                        templateUrl: "partials/admin-rooms-list",
                        controller: "AdminRoomsController",
                        controllerAs: "adminRoomsCtrl"
                    }
                },
                resolve: {
                    initialRooms: ['HotelService', '$stateParams', function(HotelService, $stateParams){
                        return HotelService.loadRoomsForAdmin($stateParams.hotelId);
                    }]
                }
            })
            .state('home-abstract.edit-room',{
                url:'/edit-room/:roomId',
                views: {
                    'edit-room': {
                        templateUrl: "partials/edit-room",
                        controller: "EditRoomController",
                        controllerAs: "editRoomsCtrl"
                    }
                },
                resolve: {
                    initialRoom: ['$stateParams', 'HotelService',function($stateParams, HotelService){
                        return HotelService.getRoomById($stateParams.roomId);
                    }],
                    initialHotelServices: ['$stateParams', 'HotelService',function($stateParams, HotelService){
                        return HotelService.loadHotelServicesByRoomId($stateParams.roomId);
                    }]
                }
            })
            .state('home-abstract.system-admin-airlines',{
                url:'/system-admin-airlines',
                views: {
                    'system-admin-airlines': {
                        templateUrl: "partials/system-admin-airlines",
                        controller: "SystemAdminAirlinesController",
                        controllerAs: "sysAdminAirlinesCtrl"
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
            .state('home-abstract.system-admin-hotels',{
                url:'/system-admin-hotels',
                views: {
                    'system-admin-hotels': {
                        templateUrl: "partials/system-admin-hotels",
                        controller: "SystemAdminHotelsController",
                        controllerAs: "sysAdminHotelsCtrl"
                    }
                },
                resolve: {
                    initialHotelsList: ['$stateParams', 'HotelService',function($stateParams, HotelService){                     
                        return HotelService.loadAllHotels();   
                    }]
                }
            })
            .state('home-abstract.add-hotel',{
                url:'/add-hotel',
                views: {
                    'add-hotel': {
                        templateUrl: "partials/add-hotel",
                        controller: "AddHotelController",
                        controllerAs: "ahCtrl"
                    }
                },
                resolve: {
                    initialDestinationsList: ['AvioService', function(AvioService){
                        return AvioService.loadAllDestiantions();
                      }],
                    initialHotelAdminList: ['UserService', function(UserService){
                        return UserService.loadAllHotelAdmins();
                      }]
                }
            })
            .state('home-abstract.add-airline',{
                url:'/add-airline',
                views: {
                    'add-airline': {
                        templateUrl: "partials/add-airline",
                        controller: "AddAirlineController",
                        controllerAs: "aaCtrl"
                    }
                },
                resolve: {
                    initialAvioAdminList: ['UserService', function(UserService){
                        return UserService.loadAllAirlineAdmins();
                      }]
                }
            })
            .state('home-abstract.change-password',{
                url:'/change-password',
                views: {
                    'change-password': {
                        templateUrl: "partials/change-password",
                        controller: "ChangePasswordController",
                        controllerAs: "cpCtrl"
                    }
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
            .state('home-abstract.profile-abstract.reservations-list', {
                url: '/reservationsList',                
                views: {
                    'reservations-list': {
                      templateUrl: 'partials/reservations-list',
                      controller: 'ReservationListController',
                      controllerAs: 'resListCtrl'
                     
                  }
                },
                resolve: {
                    initialFlightReservationsData: ['$stateParams', 'AvioService',function($stateParams, AvioService){
                       
                        return AvioService.getAllUserFlightReservations();   
                      }]
                    
                }
            })
            .state('home-abstract.profile-abstract.hotel-reservations-list', {
                url: '/hotelReservationsList',                
                views: {
                    'hotel-reservations-list': {
                      templateUrl: 'partials/hotel-reservations-list',
                      controller: 'HotelReservationListController',
                      controllerAs: 'hotelResListCtrl'
                     
                  }
                },
                resolve: {
                    initialHotelReservationsData: ['$stateParams', 'HotelService',function($stateParams, HotelService){
                       
                        return HotelService.getAllUserHotelReservations();   
                      }]
                    
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
            .state('home-abstract.avio-admin-avio-profile.admin-flights', {
                url: '/admin-flights',
                resolve: {
                    initialFlights: ['AvioService', '$stateParams',function(AvioService, $stateParams){
                        return AvioService.loadAllFlightsForAdmin($stateParams.avioId);
                    }]
                },
                views: {
                    'admin-flights': {
                      templateUrl: 'partials/admin-flights-list',
                      controller: 'AdminFlightsController',
                      controllerAs: 'adminFlightsCtrl'
                  }
                }  
            })
            .state('home-abstract.create-flight', {
                url: '/createflight/:avioId',
                resolve: {
                    initialDestinations: ['AvioService',function(AvioService){
                        return AvioService.loadDestinationsForAdmin();
                    }]
                },
                views: {
                    'create-flight': {
                      templateUrl: 'partials/create-flight',
                      controller: 'CreateFlightController',
                      controllerAs: 'createFlightCtrl'
                  }
                }  
            })
            .state('home-abstract.succesful-reservation', {
                url: '/succesfulReservation',
                views: {
                    'succesful-reservation': {
                      templateUrl: 'partials/succesful-reservation'
                  }
                }  
            })
            $urlRouterProvider.otherwise('/avio-companies');

    }
]);