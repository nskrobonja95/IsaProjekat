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
            console.log("Begin: " + datestring);
            var retVal;
            var splitted = datestring.split("/");
            retVal = splitted[2] + "-" + splitted[0] + "-" + splitted[1];
            console.log("End: " + retVal);
            return retVal;
        }

        function reverseFormatDateString(datestring) {
            console.log("Begin: " + datestring);
            var retVal;
            var splitted = datestring.split("-");
            retVal = splitted[2] + "/" + splitted[0] + "/" + splitted[1];
            console.log("End: " + retVal);
            return retVal;
        }

    }
})();