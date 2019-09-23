
<nav class="navbar navbar-expand-lg navbar-custom">
  <a class="navbar-brand" href="#"><img alt="Brand" ng-src="images/airplane-logo.png" id="brandImg" width="75" height="75"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" ng-if="globals.currentUser.username==null || globals.currentUser.userType=='User'">
      <li class="nav-item active">
        <a class="nav-link" ui-sref="home-abstract.avio-companies-list()" style="color:white"><i class="fas fa-plane"></i> Flights <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" ui-sref="home-abstract.hotels-list()" style="color:white" ><i class="fas fa-hotel"></i> Hotels <span class="sr-only"></span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" ui-sref="home-abstract.car-hire-companies-list()" style="color:white" ><i class="fas fa-car"></i> Car Hire</a>
      </li>
    </ul>

    <ul class="navbar-nav mr-auto" ng-if="globals.currentUser.userType=='AvioAdmin'">
      <li class="nav-item active">
        <a class="nav-link" ui-sref="home-abstract.avio-admin()" style="color:white" ><i class="fas fa-hotel"></i> Avio-Company <span class="sr-only"></span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" ui-sref="home-abstract.admin-flights()" style="color:white" ><i class="fas fa-hotel"></i> Flights <span class="sr-only"></span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" ui-sref="home-abstract.hotels-list()" style="color:white" ><i class="fas fa-hotel"></i> Discounts <span class="sr-only"></span></a>
      </li>
    </ul>

    <ul class="navbar-nav mr-auto" ng-if="globals.currentUser.userType=='HotelAdmin'">
      <li class="nav-item active">
        <a class="nav-link" ui-sref="home-abstract.hotel-admin()" style="color:white" ><i class="fas fa-hotel"></i> Hotel <span class="sr-only"></span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" ui-sref="home-abstract.admin-rooms-list()" style="color:white" ><i class="fas fa-hotel"></i> Rooms <span class="sr-only"></span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" ui-sref="home-abstract.hotels-list()" style="color:white" ><i class="fas fa-hotel"></i> Discounts <span class="sr-only"></span></a>
      </li>
    </ul>

    <ul class="navbar-nav mr-auto" ng-if="globals.currentUser.userType=='SysAdmin'">
      <li class="nav-item active">
        <a class="nav-link" ui-sref="home-abstract.system-admin-airlines()" style="color:white" ><i class="fas fa-hotel"></i> Airlines <span class="sr-only"></span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" ui-sref="home-abstract.system-admin-hotels()" style="color:white" ><i class="fas fa-hotel"></i> Hotels <span class="sr-only"></span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" ui-sref="home-abstract.hotels-list()" style="color:white" ><i class="fas fa-hotel"></i> Discounts <span class="sr-only"></span></a>
      </li>
    </ul>
    
    <ul class="navbar-nav ml-auto" ng-if="globals.currentUser.username==null">
      <li class="nav-item">
        <a  class="nav-link" href  style="color:white" data-toggle="modal" data-target="#loginModal"><i class="fas fa-sign-in-alt"></i> Login</a>
      </li>
      <li class="nav-item" >
        <a  class="nav-link" href data-toggle="modal" style="color:white"  data-target="#signupModal"><i class="fas fa-user-plus"></i> Sign up</a>
      </li>
      <li class="nav-item" ng-if="globals.currentUser.username!=null">
        <a  class="nav-link" href="#" style="color:white" ng-click="homeCtrl.logout()"><i class="fas fa-sign-out-alt"></i> Log Out</a>
      </li>
    </ul>

    <ul class="navbar-nav ml-auto" ng-if="globals.currentUser.username!=null">
        <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href style="color:white" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-user-circle" ></i> {{globals.currentUser.username}}
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href ui-sref="home-abstract.profile-abstract.profile-overview({username:globals.currentUser.username})">Your profile</a>
          <a class="dropdown-item" href ui-sref="home-abstract.settings-abstract.general-settings()">Settings</a>
          <a  class="dropdown-item" href ng-click="homeCtrl.logout()"><i class="fas fa-sign-out-alt"></i> Log Out</a>
        </div>
      </li>
    </ul>
    <#--  <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>  -->
  </div>
</nav>

   <div class="modal fade" id="signupModal" tabindex="-1" role="dialog" aria-labelledby="signupModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">SignUp</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="alert alert-success" role="alert" ng-if="homeCtrl.successMessage">{{homeCtrl.successMessage}}</div>
        <div class="alert alert-danger" role="alert" ng-if="homeCtrl.errorMessage">{{homeCtrl.errorMessage}}</div>
        <div class="col-md-6 col-md-offset-3">
          <form name="registerForm" ng-submit="homeCtrl.submitReg()" role="form">


            <div class="form-group">
                <input type="email" class="form-control" name="email" placeholder="Email" ng-pattern="homeCtrl.emailPattern" ng-model="homeCtrl.user.email" ng-minlength="3" ng-maxlength="128" ng-required="true"></input>
                <div ng-show="registerForm.email.$touched && (registerForm.email.$error.minlength || registerForm.email.$error.maxlength)">
                  <small style="color:red;display:block;text-align:center;">Email must have anywhere between 3 and 128 characters!</small>
                </div>
                <div ng-show="registerForm.email.$touched && registerForm.email.$error.required">
                  <small style="color:red;display:block;text-align:center;">Required!</small>
                </div>
                <div ng-show="registerForm.email.$touched && registerForm.email.$error.pattern">
                  <small style="color:red;display:block;text-align:center;">Invalid characters detected!</small>
                </div>
            </div>
            <div class="form-group">
              <input type="text" class="form-control" name="username" placeholder="Username" ng-pattern="homeCtrl.usernamePattern"
                ng-model="homeCtrl.user.username" ng-minlength="3" ng-maxlength="10" ng-required="true">
              <div ng-show="registerForm.username.$touched && (registerForm.username.$error.minlength || registerForm.username.$error.maxlength)">
                <small style="color:red;display:block;text-align:center;">Username must have anywhere between 3 and 10
                  characters!</small>
              </div>
              <div ng-show="registerForm.username.$touched && registerForm.username.$error.required">
                <small style="color:red;display:block;text-align:center;">Required!</small>
              </div>
              <div ng-show="registerForm.username.$touched && registerForm.username.$error.pattern">
                <small style="color:red;display:block;text-align:center;">Invalid characters detected!</small>
              </div>
            </div>
            <div class="form-group">
              <input type="password" class="form-control" name="password" placeholder="Password" ng-pattern="homeCtrl.passwordPattern"
                ng-model="homeCtrl.user.password" ng-minlength="8" ng-maxlength="16" ng-required="true"></input>
              <div ng-show="registerForm.password.$touched && (registerForm.password.$error.minlength || registerForm.password.$error.maxlength)">
                <small style="color:red;display:block;text-align:center;">Password must have anywhere between 8 and 16
                  characters!</small>
              </div>
              <div ng-show="registerForm.password.$touched && registerForm.password.$error.required">
                <small style="color:red;display:block;text-align:center;">Required!</small>
              </div>
              <div ng-show="registerForm.password.$touched && registerForm.password.$error.pattern">
                <small style="color:red;display:block;text-align:center;">Invalid characters detected!</small>
              </div>
            </div>

            <div class="form-group">
              <input type="password" class="form-control" name="passwordRepeat" placeholder="Confirm Password"
                ng-pattern="homeCtrl.user.password" ng-model="homeCtrl.passwordRepeat" ng-minlength="8" ng-maxlength="16"
                ng-required="true">
              <div ng-show="registerForm.passwordRepeat.$touched && (registerForm.passwordRepeat.$error.minlength || registerForm.passwordRepeat.$error.maxlength)">
                <small style="color:red;display:block;text-align:center;">Password must have anywhere between 8 and 16
                  characters!</small>
              </div>
              <div ng-show="registerForm.passwordRepeat.$touched && registerForm.passwordRepeat.$error.required">
                <small style="color:red;display:block;text-align:center;">Required!</small>
              </div>

              <div ng-show="registerForm.passwordRepeat.$error.pattern && registerForm.passwordRepeat.$touched">
                <small style="color:red;display:block;text-align:center;">Passwords have to match!</small>
              </div>
              <div ng-show="!registerForm.passwordRepeat.$error.pattern && registerForm.passwordRepeat.$touched && homeCtrl.passwordRepeat != '' ">
                <small style="color:green;display:block;text-align:center;">Passwords matching!</small>
              </div>
            </div>
            <div class="form-group">
              <input type="text" class="form-control" name="name" placeholder="First name" ng-pattern="homeCtrl.namePattern" ng-model="homeCtrl.user.name" ng-minlength="2" ng-maxlength="20" ng-required="true"></input>
              <div ng-show="registerForm.name.$touched && registerForm.name.$error.minlength">
                  <small style="color:red;display:block;text-align:center;">Name you have entered is too small!</small>
              </div>
              <div ng-show="registerForm.name.$touched && registerForm.name.$error.maxlength">
                  <small style="color:red;display:block;text-align:center;">Name you have entered is too large!</small>
              </div>
              <div ng-show="registerForm.name.$touched && registerForm.name.$error.required">
                  <small style="color:red;display:block;text-align:center;">Required!</small>
              </div>
              <div ng-show="registerForm.name.$touched && registerForm.name.$error.pattern">
                  <small style="color:red;display:block;text-align:center;">Invalid characters detected!</small>
              </div>
            </div>
            <div class="form-group">
              <input type="text" class="form-control" name="lastname" placeholder="Last name" ng-pattern="homeCtrl.lastnamePattern" ng-model="homeCtrl.user.lastname" ng-minlength="2" ng-maxlength="20" ng-required="true"></input>
              <div ng-show="registerForm.lastname.$touched && registerForm.lastname.$error.minlength">
                  <small style="color:red;display:block;text-align:center;">Last name you have entered is too small!</small>
              </div>
              <div ng-show="registerForm.lastname.$touched && registerForm.lastname.$error.maxlength">
                  <small style="color:red;display:block;text-align:center;">Last name you have entered is too large!</small>
              </div>
              <div ng-show="registerForm.lastname.$touched && registerForm.lastname.$error.required">
                  <small style="color:red;display:block;text-align:center;">Required!</small>
              </div>
              <div ng-show="registerForm.lastname.$touched && registerForm.lastname.$error.pattern">
                  <small style="color:red;display:block;text-align:center;">Invalid characters detected!</small>
              </div>
            </div>
            <div class="form-group">
              <input type="text" class="form-control" name="city" placeholder="City" ng-model="homeCtrl.user.city" ng-minlength="2" ng-maxlength="20" ng-required="true">
            </div>
            <div class="form-group">
              <input type="text" class="form-control" name="phoneNumber" placeholder="Phone number" ng-pattern="homeCtrl.phonePattern" ng-model="homeCtrl.user.phoneNumber" ng-minlength="10" ng-maxlength="10" ng-required="true"></input>
              <div ng-show="registerForm.phoneNumber.$touched && (registerForm.phoneNumber.$error.minlength || registerForm.phoneNumber.$error.maxlength) && !registerForm.phoneNumber.$error.pattern ">
                  <small style="color:red;display:block;text-align:center;">Enter a valid phone number!</small>
              </div>
              <div ng-show="registerForm.phoneNumber.$touched && (registerForm.phoneNumber.$error.pattern)">
                  <small style="color:red;display:block;text-align:center;">Invalid characters detected!</small>
              </div>
              <div ng-show="registerForm.phoneNumber.$touched && registerForm.phoneNumber.$error.required">
                  <small style="color:red;display:block;text-align:center;">Required!</small>
              </div>
            </div>
            <div class="form-actions">
            </div>
          </form>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" ng-click="homeCtrl.submitReg()" ng-disabled="homeCtrl.dataLoading || registerForm.passwordRepeat.$error.pattern"
          class="btn btn-primary">Register</button>
        <img ng-if="homeCtrl.dataLoading" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="loginModalLabel">Login</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form name="loginForm" ng-submit="homeCtrl.login()" role="form">
          <div class="form-group">
            <input type="text" class="form-control" name="username" placeholder="Username" ng-pattern="homeCtrl.usernamePattern"
              ng-model="homeCtrl.userLogin.username" ng-minlength="3" ng-maxlength="25" ng-required="true">
            <div ng-show="registerForm.username.$touched && (registerForm.username.$error.minlength || registerForm.username.$error.maxlength)">
              <small style="color:red;display:block;text-align:center;">Username must have anywhere between 3 and 10
                    characters!</small>
            </div>
            <div ng-show="registerForm.username.$touched && registerForm.username.$error.required">
              <small style="color:red;display:block;text-align:center;">Required!</small>
            </div>
            <div ng-show="registerForm.username.$touched && registerForm.username.$error.pattern">
              <small style="color:red;display:block;text-align:center;">Invalid characters detected!</small>
            </div>
          </div>
          <div class="form-group">
            <input type="password" class="form-control" name="password" placeholder="Password" ng-pattern="homeCtrl.passwordPattern"
                ng-model="homeCtrl.userLogin.password" ng-minlength="8" ng-maxlength="16" ng-required="true"></input>
              <div ng-show="registerForm.password.$touched && (registerForm.password.$error.minlength || registerForm.password.$error.maxlength)">
                <small style="color:red;display:block;text-align:center;">Password must have anywhere between 8 and 16
                  characters!</small>
              </div>
              <div ng-show="registerForm.password.$touched && registerForm.password.$error.required">
                <small style="color:red;display:block;text-align:center;">Required!</small>
              </div>
              <div ng-show="registerForm.password.$touched && registerForm.password.$error.pattern">
                <small style="color:red;display:block;text-align:center;">Invalid characters detected!</small>
              </div>
            </div>
            <div class="form-actions">
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          <button type="button" ng-click="homeCtrl.login()" data-dismiss="modal" ng-disabled="homeCtrl.dataLoading || registerForm.passwordRepeat.$error.pattern"
            class="btn btn-primary">Sign in</button>
          <img ng-if="homeCtrl.dataLoading" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
      </div>
    </div>
  </div>
</div>



<div ui-view="flight-search"></div>
<div ui-view="avio-companies-list" style="padding: 0; margin:0"></div>
<div ui-view="avio-company"></div>
<div ui-view="hotel-profile"></div>
<div ui-view="car-hire-companies-list"></div>
<div ui-view="hotels-list"></div>
<div ui-view="profile-abstract"></div>
<div ui-view="settings-abstract"></div>
<div ui-view="confirm"></div>
<div ui-view="flight-search-results"></div>
<div ui-view="hotel-search-results"></div>
<div ui-view="avio-admin"></div>
<div ui-view="hotel-admin"></div>
<div ui-view="admin-flights"></div>
<div ui-view="create-flight"></div>
<div ui-view="flight-reservation"></div>
<div ui-view="add-service"></div>
<div ui-view="admin-rooms-list"></div>
<div ui-view="create-room"></div>
<div ui-view="system-admin-airlines"></div>
<div ui-view="system-admin-hotels"></div>
<div ui-view="add-hotel"></div>
<div ui-view="add-airline"></div>
<div ui-view="change-password"></div>
<div ui-view="avio-fast-reservations"></div>
<div ui-view="edit-room"></div>

<script>
  $('#loginModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var recipient = button.data('whatever') // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var modal = $(this)

  })
  $('#signupModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var recipient = button.data('whatever') // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var modal = $(this)

  })
</script>
