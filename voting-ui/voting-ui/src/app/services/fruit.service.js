(function(){
    'use strict';

    angular
        .module('voting')
        .service('fruitService',fruitService);

    fruitService.$inject = ['$http','$q','CONFIG'];
    function fruitService($http,$q,CONFIG){

        var self = this;

        self.getFruits = getFruits;
        self.deleteFruit = deleteFruit;
        self.updateFruit = updateFruit;
        self.addFruit = addFruit;
        self.getFruit = getFruit;
        self.updateVote = updateVote;

        function getFruits(){
            return $http.get(CONFIG.API_END_POINT + '/fruits')
                .then(successFn,errorFn);
        }

        function deleteFruit(fruitID){
            return $http.delete(CONFIG.API_END_POINT + '/fruits/'+fruitID)
                .then(successFn,errorFn);
        }

        function updateFruit(fruitID,fruit){
            return $http.put(CONFIG.API_END_POINT + '/fruits/'+fruitID, fruit)
                .then(successFn,errorFn);
        }

        function getFruit(title) {
            return $http.get(CONFIG.API_END_POINT + '/fruits/'+title)
                .then(successFn,errorFn);
        }

        function updateVote(id) {
            return $http.put(CONFIG.API_END_POINT + '/fruits/'+id)
                .then(successFn,errorFn);
        }

        function addFruit(fruit){
            return $http.post(CONFIG.API_END_POINT + '/fruits/', fruit)
                .then(successFn,errorFn);
        }

        function successFn(response){
            return response.data;
        }

        function errorFn(errResponse){
            return $q.reject(errResponse.status);
        }
    }
})();