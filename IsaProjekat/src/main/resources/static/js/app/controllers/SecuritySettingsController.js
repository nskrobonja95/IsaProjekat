'use strict';

angular.module('flightApp').controller('SecuritySettingsController',
    ['UserService','initialData','$state', 'LoginService',   function( UserService, initialData, $state, LoginService) {
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
        self.submit = submit;
        self.securitySubmit = securitySubmit;
        self.correctPassword = true;
        
        function securitySubmit(){
            console.log(self.userData.password);
            console.log(self.currentPassword);
            console.log(self.newPassword);
            console.log(self.repeatPassword);
            if(self.userData.password !== self.currentPassword){
                self.correctPassword = false;
                return;
            }
            self.correctPassword = true;
            if(self.newPassword === self.repeatPassword){
                console.log("Sifre se slazu");
                self.passwordMatch = true;
                self.passwordDoNotMatch = false;
            }else{
                console.log("Sifre se ne slazu");
                self.passwordMatch = false;
                self.passwordDoNotMatch = true;
                return;
            }
            self.userData.password = self.newPassword;
            updateSecuritySettings(self.userData, self.userData.id);
        }
        function updateSecuritySettings(user, id){
            console.log('About to update user\'s password');
            self.dataLoading = true;
            UserService.updateUser(user, id)
                .then(
                    function (response){
                        console.log('User\'s password updated successfully');
                        console.log(response.username);
                        self.successMessage='User\'s password updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.dataLoading = false;
                        LoginService.ClearCredentials();
                        $state.go('home-abstract.flights');
                    },
                    function(errResponse){
                        console.error('Error while updating User\'s password');
                        self.errorMessage=errResponse.data.errorMessage;
                        self.dataLoading = false;
                        self.successMessage='';
                    }
                );
        }
        
     } ]);