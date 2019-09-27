"use strict";

angular.module("flightApp").controller("SystemAdminAirlinesController", [
    "$scope",
    "$rootScope",
    "$state",
    "AvioService",
    "initialCompaniesList",
    "initialDestinationsList",
    function (
        $scope,
        $rootScope,
        $state,
        AvioService,
        initialCompaniesList,
        initialDestinationsList
    ) {
        var self = this;
        console.log("HELLO");
        console.log(initialCompaniesList);
        console.log(initialDestinationsList);
        self.companiesList = initialCompaniesList.avioList;
        self.destinationList = initialDestinationsList.destList;
        self.goToAddAirlinePage = goToAddAirlinePage;
        self.addDestFormFlag = false;
        self.showAddDestForm = showAddDestForm;
        self.addNewDestination = addNewDestination;
        self.deleteDest = deleteDest;
        self.activeId = 0;
        self.editDest = editDest;
        self.activeName = "";
        self.activeCountry = "";

        function goToAddAirlinePage() {
            $state.go("home-abstract.add-airline");
        }

        function showAddDestForm() {
            self.addDestFormFlag = true;
        }

        function addNewDestination() {
            var obj = {};
            obj.name = self.cityName + ", " + self.countryName;
            if (self.activeId == 0) {
                AvioService.saveDestination(obj).then(
                    function (response) {
                        console.log("Response of the search:", response);
                        self.destinationList.push(response.avio);
                        self.cityName = "";
                        self.countryName = "";
                        self.addDestFormFlag = false;
                    },
                    function (errResponse) {
                        console.log("Error response of the search:", errResponse);
                    }
                );
            } else {
                AvioService.editDestination(obj).then(
                    function (response) {
                        for (var i = 0; i < self.destinationList.length; ++i) {
                            if (
                                self.destinationList[i].name ==
                                self.activeName + self.activeCountry
                            ) {
                                self.destinationList[i];
                            }
                        }
                        self.destinationList.push(response.avio);
                        self.addDestFormFlag = false;
                        self.activeId = 0;
                        self.cityName = "";
                        self.countryName = "";
                        self.addDestFormFlag = false;
                    },
                    function (errResponse) {
                        console.log("Error response of the search:", errResponse);
                    }
                );
            }
        }

        function deleteDest(dest) {
            debugger;
            AvioService.deleteDestination(dest.id).then(function (response) {
                if (response.response.status == 200) {
                    alert("Succesful");
                    for (var i = 0; i < self.destinationList.length; ++i) {
                        if (self.destinationList[i] == dest) {
                            self.destinationList.splice(i, 1);
                        }
                    }
                } else {
                    alert("Unable to delete destination.");
                }
            });
        }

        function editDest(dest) {
            self.cityName = dest.name.split(",")[0];
            self.countryName = dest.name.split(" ")[1];
            self.activeName = dest.name.split(",")[0];
            self.activeCountry = dest.name.split(" ")[1];
            self.activeId = dest.id;
            self.addDestFormFlag = true;
        }
    }
]);
