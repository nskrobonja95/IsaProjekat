<div class="container center-body">
    <div class="container-page">     
        <form name="changePasswordForm" role="form">
            <h4>Please change password before proceeding</h4>
            <div class="form-group col-lg-6">
                <label>New password:</label>
                <input type="password" ng-model="cpCtrl.password" class="form-control" 
                        name="newpassword" ng-minlength="8">
                <div ng-show="changePasswordForm.newpassword.$touched && changePasswordForm.newpassword.$error.required">
                    <small style="color:red;display:block;text-align:center;">Required!</small>
              </div>
              <div ng-show="changePasswordForm.newpassword.$touched && (changePasswordForm.newpassword.$error.minlength || registerForm.password.$error.maxlength)">
                <small style="color:red;display:block;text-align:center;">Password must have anywhere between 8 and 16
                  characters!</small>
              </div>
            </div>
            <div class="form-group col-lg-6">
                <label>Confirm password:</label> 
                <input type="password" ng-model="cpCtrl.obj.password" class="form-control" 
                        name="passwordRepeat" ng-minlength="8" ng-pattern="cpCtrl.password">
                <div ng-show="changePasswordForm.passwordRepeat.$error.pattern && changePasswordForm.passwordRepeat.$touched">
                    <small style="color:red;display:block;text-align:center;">Passwords have to match!</small>
                </div>
            </div>
            <button class="btn btn-info" ng-click="cpCtrl.changePassword()"
                    ng-disabled="changePasswordForm.passwordRepeat.$error.pattern">
                    Ok</button>
        </form>
    </div>
</div>