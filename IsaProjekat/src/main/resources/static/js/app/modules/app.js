var app = angular.module('flightApp', ['ui.router', 'ngMaterial', 'ngMessages', 'ngCookies']);

app.config(['$stateProvider', '$urlRouterProvider', '$mdThemingProvider',
    function ($stateProvider, $urlRouterProvider, $mdThemingProvider) {

        $stateProvider
            .state('home-abstract', {
                abstract: true,
                views:{
                    '@':{
                        templateUrl: "partials/home-abstract"
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