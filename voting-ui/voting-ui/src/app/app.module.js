(function(){
    'use strict';

    angular
        .module('voting',['ui.router','ui.bootstrap', 'ngStorage','ngMessages','ngAnimate','ui-notification'])
        .config(moduleConfig);

   moduleConfig.$inject = ['$stateProvider', '$urlRouterProvider', 'NotificationProvider'];
    function moduleConfig($stateProvider, $urlRouterProvider, NotificationProvider) {

        $urlRouterProvider.otherwise('');

        $stateProvider

            .state('signin', {
                url: '',
                templateUrl: 'app/views/signin.tmpl.html',
                controller: 'UserController',
                controllerAs: 'userVm'
            })

            .state('fruits', {
                url: '/fruits',
                templateUrl: 'app/views/fruits.tmpl.html',
                controller:'FruitController',
                controllerAs: 'fruitVm'
            })

            .state('fruit-details', {
                url: '/fruit-details/:title',
                templateUrl: 'app/views/fruit-details.tmpl.html',
                controller:'FruitDetailController',
                controllerAs: 'fruitDetailVm'
            });

        NotificationProvider.setOptions({
            delay: 1200,
            startTop: 20,
            startRight: 10,
            verticalSpacing: 20,
            horizontalSpacing: 20,
            positionX: 'center',
            positionY: 'top'
        });
    }
})();