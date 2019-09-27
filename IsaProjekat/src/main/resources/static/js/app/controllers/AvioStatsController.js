'use strict';

angular.module('flightApp').controller('AvioStatsController', [
    'avioStatisticData', '$rootScope', '$state','$stateParams','AvioService','SearchService',
    function (avioStatisticData, $rootScope, $state, $stateParams, AvioService, SearchService) {

        var self = this;
        self.stats = avioStatisticData.stats;
        self.getGrosForInterval = getGrosForInterval;
        self.grossCalculated = false;
        self.grossObj = {};
        function getGrosForInterval(){
            if(self.grossObjTemp == undefined){
                alert("Fill both interval values");
                return;
            }else if(self.grossObjTemp.toGrossInterval == undefined || self.grossObjTemp.fromGrossInterval == undefined){
                alert("Fill both interval values");
                return;
            }else if(new Date(self.grossObjTemp.toGrossInterval) <= new Date(self.grossObjTemp.fromGrossInterval)){
                alert("TO date should be higher then FROM! Try again. ");
                return;
            }
            self.grossObj.toGrossInterval = SearchService.formatDateString(self.grossObjTemp.toGrossInterval);
            self.grossObj.fromGrossInterval = SearchService.formatDateString(self.grossObjTemp.fromGrossInterval);
            AvioService.getGrosForInterval($stateParams.avioId, self.grossObj)
                .then(function(response){
                    console.log("Status:", response.grossResponse.status);
                    if(response.grossResponse.status == 200){
                        self.grossResult = response.grossResponse.data;
                        self.grossCalculated = true;
                    }else if(response.grossResponse.status == 404){
                        console.log(response.grossResponse);
                        alert(response.grossResponse.data.errorMessage);
                        self.grossCalculated = 0;
                    }
                }, function(error){

                });

       }

        

    }
        
]);