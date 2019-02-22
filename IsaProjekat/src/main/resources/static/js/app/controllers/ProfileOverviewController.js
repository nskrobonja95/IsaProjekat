angular.module('flightApp').controller('ProfileOverviewController',
    ['$scope','$state', 'yourInitialData',   function($scope, $state, yourInitialData) {
        var self = this;
        console.log(yourInitialData.you);
        self.userData = yourInitialData.you;
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
        self.securitySubmit = securitySubmit;
        self.correctPassword = true;
        
        function submit(){
            updateUsersGeneralSettings(self.userData, self.userData.id);
        }
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
                        //AuthenticationService.SetCredentials(response.username, response.password, response.type);
                        AuthenticationService.ClearCredentials();
                        $state.go('login');
                    },
                    function(errResponse){
                        console.error('Error while updating User\'s password');
                        self.errorMessage=errResponse.data.errorMessage;
                        self.dataLoading = false;
                        self.successMessage='';
                    }
                );
        }
        function updateUsersGeneralSettings(user, id){
            console.log('About to update user');
            self.dataLoading = true;
            UserService.updateUser(user, id)
                .then(
                    function (response){
                        console.log('User updated successfully');
                        console.log(response.username);
                        self.successMessage='User updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.dataLoading = false;
                        AuthenticationService.SetCredentials(response.username, response.password, response.type);
                        $state.go('guest-abstract.settings-abstract.general', {username: response.username});
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