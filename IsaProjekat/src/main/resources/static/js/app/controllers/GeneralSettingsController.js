'use strict';

angular.module('flightApp').controller('GeneralSettingsController',
    ['UserService','initialData', '$rootScope','$state', 'LoginService',   function( UserService, initialData,$rootScope, $state, LoginService) {
        var self = this;
        self.userData = initialData.you;
        self.firstNamePattern=/^[A-Z][a-z]*\S$/;
        self.lastNamePattern=/^[A-Z][a-z]*\S$/;
        self.userNamePattern= /^\S*$/;
        self.passwordPattern = /^\S*$/;
        self.phonePattern = /^[0-9]+\S$/;
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
        self.dataLoading = false;
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
        self.updateUsersGeneralSettings = updateUsersGeneralSettings;
        self.submit = submit;
      
        self.correctPassword = true;
        
        function submit(){
            updateUsersGeneralSettings($rootScope.globals.currentUser.username, self.userData);
        }
        
        function updateUsersGeneralSettings(username, user){
            console.log('About to update user');
            self.dataLoading = true;
            UserService.editUser(username, user)
                .then(
                    function (response){
                        console.log('User updated successfully');
                        console.log("User: ", response);
                        console.log(response.response.username);
                        self.successMessage='User updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.dataLoading = false;
                        debugger;
                        LoginService.setCredentialsUsername(response.response.username);
                        //LoginService.ClearCredentials();
                        $state.go('home-abstract.settings-abstract.general-settings');
                    },
                    function(errResponse){
                        console.error('Error while updating User');
                        self.errorMessage=errResponse.data.errorMessage;
                        self.dataLoading = false;
                        self.successMessage='';
                    }
                );
        }
     } ]);