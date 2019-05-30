<div class="form-group">
    <label for="fullname">Name</label>
    <input type="text" class="form-control" name="fullname" ng-model="formData.fullname"
    placeholder="Full name" ng-minlength=3 ng-required required>
    <label for="email">Email </label> 
       <span class="error-container" 
         ng-show="signupform.email.$dirty && signupform.email.$invalid">
      <small class="error" 
             ng-show="signupform.email.$error.required">
             Your email is required.
      </small>
      <small class="error" 
             ng-show="signupform.email.$error.minlength">
              Your email is required to be at least 3 characters
      </small>
      <small class="error" 
             ng-show="signupform.email.$error.email">
             That is not a valid email. Please input a valid email.
      </small>
      <small class="error" 
             ng-show="signupform.email.$error.maxlength">
              Your email cannot be longer than 50 characters
      </small>
    </span>

    <input type="email" class="form-control" name="email" id="email" ng-model="formData.email" 
    placeholder="Email address"
    ng-minlength=3 ng-maxlength=50 required />

</div>
<tt>signupform.$valid = {{signupform.$valid}}</tt><br/>
<div class="form-group row">
<div class="col-xs-12" class="btnbox-center">
    <a ui-sref="home-abstract.flight-reservation.interests" class="btn btn-right btn-info" ng-disabled=signupform.$invalid>
    Next <span class="fas fa-arrow-right"></span>
    </a>
</div>
</div>