'use strict';

angular.module('flightApp').controller('HomeController',
    ['SignupService', 'LoginService', '$scope', '$rootScope', '$state',
        function (SignupService, LoginService, $scope, $rootScope, $state) {

            var self = this;
            self.user = {};
            //for login
            self.userLogin = {};

            self.users = [];
            self.user.enabled = false;
            self.user.confirmationToken = '';
            self.user.type = '0';
            self.loggedUser = null;
            self.submitReg = submitReg;
            self.submitLogin = submitLogin;
            self.login = login;
            self.logout = logout;
            self.getAllUsers = getAllUsers;
            self.createUser = createUser;
            self.updateUser = updateUser;
            self.removeUser = removeUser;
            self.editUser = editUser;
            self.reset = reset;
            self.firstNamePattern = /^[A-Z][a-z]*\S$/;
            self.lastNamePattern = /^[A-Z][a-z]*\S$/;
            self.userNamePattern = /^\S*$/;
            self.passwordPattern = /^\S*$/;
            self.phonePattern = /^[0-9]+\S$/;
            self.successMessage = '';
            self.errorMessage = '';
            self.done = false;
            self.dataLoading = false;
            self.onlyIntegers = /^\d+$/;
            self.onlyNumbers = /^\d+([,.]\d+)?$/;




            function submitReg() {
                console.log('Submitting');
                self.dataLoading = true;
                if (self.user.id === undefined || self.user.id === null) {
                    console.log('Saving New User', self.user);
                    createUser(self.user);
                } else {
                    updateUser(self.user, self.user.id);
                    console.log('User updated with id ', self.user.id);
                }
            }
            function logout(){
                console.log("Radi logout")
                LoginService.ClearCredentials();
                $state.go('home-abstract.flights');
            }
            function login() {
                (function initController() {
                    // reset login status
                    LoginService.ClearCredentials();
                })();
                self.dataLoading = true;
                LoginService.Login(self.userLogin.username, self.userLogin.password, function (response) {
                    if (response.status == 200) {
                        console.log('trebalo bi ovde da udje', response.status);
                        console.log(response);
                        console.log("a ovo je data od response");
                        console.log(response.data);
                        LoginService.SetCredentials(self.userLogin.username, self.userLogin.password, response.data.role);

                        console.log($rootScope.globals);
                        self.dataLoading = false;
                        $("#loginModal").modal("hide");
                        self.userLogin.username = '';
                        self.userLogin.password = '';
                        $state.go('home-abstract.flights');
                    } else {
                        console.log('Ovo je poruka o gresci ' + response.data.errorMessage);
                        self.errorMessage = response.data.errorMessage;
                        self.dataLoading = false;

                    }
                });
            };


            function submitLogin() {
                console.log(self.userLogin);
                self.dataLoading = true;
                SignupService.userSignIn(self.userLogin)
                    .then(
                        function (response) {
                            self.dataLoading = false;
                            $("#loginModal").modal("hide");
                            self.userLogin.username = '';
                            self.userLogin.password = '';
                        },
                        function (errResponse) {
                            self.dataLoading = false;
                            alert("RESPONSE ERROR");
                        }
                    );
            }

            self.moje = function () {
                $state.go('login');
            }

            function createUser(user) {
                console.log('About to create user');
                SignupService.createUser(user)
                    .then(
                        function (response) {
                            console.log('User created successfully');
                            self.successMessage = 'User created successfully, activate your profile and go to Login page';
                            self.errorMessage = '';
                            self.done = true;
                            self.user = {};
                            $scope.registerForm.$setPristine();
                            $scope.registerForm.$setUntouched();
                            self.dataLoading = false;
                            $("signupModal").modal("hide");
                        },
                        function (errResponse) {
                            console.error('Error while creating User');
                            self.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                            self.successMessage = '';
                            self.dataLoading = false;
                        }
                    );
            }


            function updateUser(user, id) {
                console.log('About to update user');
                SignupService.updateUser(user, id)
                    .then(
                        function (response) {
                            console.log('User updated successfully');
                            self.successMessage = 'User updated successfully';
                            self.errorMessage = '';
                            self.done = true;
                            $scope.myForm.$setPristine();
                        },
                        function (errResponse) {
                            console.error('Error while updating User');
                            self.errorMessage = 'Error while updating User ' + errResponse.data;
                            self.successMessage = '';
                        }
                    );
            }


            function removeUser(id) {
                console.log('About to remove User with id ' + id);
                SignupService.removeUser(id)
                    .then(
                        function () {
                            console.log('User ' + id + ' removed successfully');
                        },
                        function (errResponse) {
                            console.error('Error while removing user ' + id + ', Error :' + errResponse.data);
                        }
                    );
            }


            function getAllUsers() {
                return SignupService.getAllUsers();
            }

            function editUser(id) {
                self.successMessage = '';
                self.errorMessage = '';
                SignupService.getUser(id).then(
                    function (user) {
                        self.user = user;
                    },
                    function (errResponse) {
                        console.error('Error while removing user ' + id + ', Error :' + errResponse.data);
                    }
                );
            }

            function reset() {
                self.successMessage = '';
                self.errorMessage = '';
                self.user = {};
                $scope.myForm.$setPristine(); //reset Form
            }
        }


    ]);