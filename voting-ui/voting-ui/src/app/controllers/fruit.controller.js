(function(){
    'use strict';

    angular.module('voting')
        .controller('FruitController',FruitController);

    FruitController.$inject = ['$rootScope','fruitService','$state','$sessionStorage','$uibModal'];
    function FruitController($rootScope,fruitService,$state,$sessionStorage,$uibModal){

        var fruitVm = this;

        $rootScope.signOutFlag = true;

        fruitVm.sort = sort;
        fruitVm.sortOrderChange = sortOrderChange;

        fruitVm.user = $sessionStorage.user;
        fruitVm.loginCheck = loginCheck;
        fruitVm.itemsPerPage = 8;
        fruitVm.pageChanged = pageChanged;
        fruitVm.deleteFlag = false;
        fruitVm.updateFlag = false;
        fruitVm.addFlag = false;
        fruitVm.showModal = false;
        fruitVm.showAdminPriveleges = showAdminPriveleges;
        fruitVm.deleteFruit = deleteFruit;
        fruitVm.updateFruit = updateFruit;
        fruitVm.addFruit = addFruit;

        loginCheck();

        function loginCheck() {
            if(fruitVm.user == null) {
                $state.go("signin");
            }
            else {
                init();
            }
        }

        function init(){

            fruitService
                .getFruits()
                .then(function (data){
                        fruitVm.fruits = data;
                        fruitVm.length = data.length;
                        fruitVm.currentPage = 1;
                        showAdminPriveleges();
                },function(errorData) {
                   console.log(errorData);
                });
        }

        function showAdminPriveleges(){
            if(fruitVm.user.role === "ADMIN"){
                fruitVm.deleteFlag = true;
                fruitVm.addFlag = true;
                fruitVm.updateFlag = true;
                fruitVm.itemsPerPage = 7;
            }
        }

        function pageChanged() {
            if(fruitVm.currentPage == 1){
                if(fruitVm.user.role === "ADMIN"){
                    fruitVm.addFlag = true;
                    fruitVm.itemsPerPage = 7;
                }
            }
            else {
                fruitVm.itemsPerPage = 8;
                fruitVm.addFlag = false;
            }
        }

        function sort(keyname){
            fruitVm.sortKey=keyname;
            fruitVm.reverse = false;
        }

        function sortOrderChange(){
            fruitVm.reverse=!fruitVm.reverse;
        }

        function deleteFruit(fruit){

            var modalInstance = $uibModal.open({
                templateUrl: 'app/views/delete-fruit.tmpl.html',
                controller: 'ModalController as modalVm',
                resolve: {
                    fruit: function(){
                        return fruit;
                    },
                    title: function(){
                        return "Delete Fruit";
                    },
                    btnTxt: function(){
                        return "Delete";
                    }
                }
            });

            modalInstance.result.then(function(){
                init();
            });
        }

        function updateFruit(fruit){

            var modalInstance = $uibModal.open({
                templateUrl: 'app/views/update-fruit.tmpl.html',
                controller: 'ModalController as modalVm',
                resolve: {
                    fruit: function(){
                        return fruit;
                    },
                    title: function(){
                        return "Update Fruit";
                    },
                    btnTxt: function(){
                        return "Update";
                    }
                }
            });

            modalInstance.result.then(function(){
                init();
            });
        }

        function addFruit(fruit){

            var modalInstance = $uibModal.open({
                templateUrl: 'app/views/update-fruit.tmpl.html',
                controller: 'ModalController as modalVm',
                resolve: {
                    fruit: function(){
                        return fruit;
                    },
                    title: function(){
                        return "Add a Fruit";
                    },
                    btnTxt: function(){
                        return "Add";
                    }
                }
            });

            modalInstance.result.then(function(){
                init();
            });
        }
    }

})();