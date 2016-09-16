(function() {
    'use strict';

    angular.module('voting')
        .controller('ModalController', ModalController);

    ModalController.$inject=['$uibModalInstance','fruit','title','btnTxt','fruitService','Notification'];
    function ModalController($uibModalInstance,fruit,title,btnTxt,fruitService,Notification){

        var modalVm = this;

        modalVm.fruit = fruit;
        modalVm.title = title;
        modalVm.btnTxt = btnTxt;
        modalVm.close = close;
        modalVm.fruitAlreadyExists = false;
        modalVm.fruitNotFound = false;

        modalVm.deleteFruit = deleteFruit;
        modalVm.updateFruit = updateFruit;
        modalVm.addFruit = addFruit;

        modalVm.open = open;
        modalVm.popup = {
            opened: false
        };

        function open() {
            modalVm.popup.opened = true;
        };

        function deleteFruit() {
            fruitService
                .deleteFruit(modalVm.fruit.fruitID)
                .then(function (data){
                    Notification.success('Fruit Deleted..');
                    close();
                },function(errorData) {
                    modalVm.fruitNotFound = true;
                    Notification.error('Not Found!');
                });
        }

        function updateFruit(isValid) {

            if(isValid) {

                if(modalVm.btnTxt=="Update"){

                    fruitService
                        .updateFruit(modalVm.fruit.fruitID, modalVm.fruit)
                        .then(function (data) {
                            Notification.success('Fruit Updated..');
                            close();
                        }, function (errorData) {
                            console.log(errorData);
                            if(errorData == 404) {
                                modalVm.fruitNotFound = true;
                                Notification.error('Not Found!');
                            }
                            else {
                                modalVm.fruitAlreadyExists = true;
                                Notification.error('Already Exists!');
                            }
                        });
                }
                else{
                    fruitService
                        .addFruit(modalVm.fruit)
                        .then(function (data) {
                            Notification.success('Fruit Updated..');
                            close();
                        }, function (errorData) {
                            Notification.error('Already Exists!');
                            modalVm.fruitAlreadyExists = true;
                        });
                }
            }
        }

        function addFruit(isValid) {

            if(isValid) {

                fruitService
                    .addFruit(modalVm.fruit)
                    .then(function (data) {
                        Notification.success('Fruit Added Successfully..');
                        close();
                    }, function (errorData) {
                        modalVm.fruitAlreadyExists = true;
                        Notification.error('Already Exists');
                    });
            }
        }

        function close(){
            $uibModalInstance.close();
        }
    }
})();