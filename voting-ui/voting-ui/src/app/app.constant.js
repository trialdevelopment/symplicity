(function() {
  'use strict';

  angular
    .module('voting')
    .constant('CONFIG', {
      API_END_POINT: 'http://localhost:8080/voting/api'
    });
})();