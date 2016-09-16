(function(){
  'use strict';

  angular
      .module('voting')
      .controller('FruitDetailController',FruitDetailController);

    FruitDetailController.$inject = ['$rootScope','$state','$stateParams','fruitService','$sessionStorage','Notification'];
    function FruitDetailController($rootScope,$state,$stateParams,fruitService,$sessionStorage,Notification){
        var fruitDetailVm = this;

        $rootScope.signOutFlag = true;

        fruitDetailVm.title = $stateParams.title;
        fruitDetailVm.user = $sessionStorage.user;
        fruitDetailVm.updateVote = updateVote;

        loginCheck();

        function loginCheck() {
            if(fruitDetailVm.user == null) {
                $state.go("signin");
            }
            else {
                init();
            }
        }

        function init() {
            fruitService
                .getFruit(fruitDetailVm.title)
                .then(function (data) {
                    fruitDetailVm.fruit = data;

                }, function (errorData) {
                    console.log(errorData);
                    $state.go("fruits");
                    Notification.error('Fruit Not Found');
                });
        }


        function updateVote(){

             fruitService
                .updateVote(fruitDetailVm.fruit.fruitID)
                .then(function (data) {
                        Notification.success('Vote Recorded Successfully..');
                        $state.go("fruits");
                },
                function (errorData) {
                    Notification.error('Error..!');
                    console.log(errorData);
                });



        }
  }
})();
