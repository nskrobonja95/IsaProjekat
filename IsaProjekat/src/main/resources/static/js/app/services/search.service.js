(function () {
    'use strict';
    angular.module('flightApp').factory('SearchService', SearchService);
    SearchService.$inject = ['$http', '$q', 'urls'];

    function SearchService($http, $q, urls) {
        var service = {};
        service.formatDateString = formatDateString;
        service.reverseFormatDateString = reverseFormatDateString;
        return service;

        function formatDateString(datestring) {
            var retVal;
            var splitted = datestring.split("/");
            retVal = splitted[2] + "-" + splitted[0] + "-" + splitted[1];
            return retVal;
        }

        function reverseFormatDateString(datestring) {
            var retVal;
            var splitted = datestring.split("-");
            retVal = splitted[1] + "/" + splitted[2] + "/" + splitted[0];
            return retVal;
        }

    }
})();