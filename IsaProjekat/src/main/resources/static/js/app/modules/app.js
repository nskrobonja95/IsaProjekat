var app = angular.module('flightApp', ['ui.router', 'ngMaterial', 'ngMessages', 'ngCookies']);


app.constant('urls', {
    BASE: 'http://localhost:8080/SpringBootCRUDApp',
    REGISTER_SERVICE_API : 'http://localhost:8080/auth/register',
    FRIENDS_SERVICE_API: 'http://localhost:8080/SpringBootCRUDApp/api/friendsList/',
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
            .state('home-abstract.center', {
                url:'/home',
                views: {
                    'center': {
                        template: "center state"
                    }

                }


            });
            $urlRouterProvider.otherwise('/home');

    }
]);