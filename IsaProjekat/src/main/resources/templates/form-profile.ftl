<label>{{direct_flight.avioCompany.name}}: from {{direct_flight.from.name}} to {{direct_flight.toDest.name}}</label>
<br>
<label ng-show="return_flight != -1">{{return_flight.avioCompany.name}}: from {{return_flight.from.name}} to {{return_flight.toDest.name}}</label>
<div ng-repeat="resSeats in roundTripObj">
    <div ng-show="return_flight != -1" ng-repeat="seat in resSeats">
        <label>Seat number: {{seat.seatNumber}}</label>
    </div>
    <label ng-show="return_flight == -1">Seat number: {{resSeats.seatNumber}}</label>
    <div class="form-group">
        <label for="fullname">Name</label>
        <input type="text" class="form-control" name="fullname" ng-model="resSeats.name"
        placeholder="Name" ng-minlength=3 ng-required required>
        <label for="lastname">Last name</label>
        <input type="text" class="form-control" name="lastname" ng-model="resSeats.lastname"
        placeholder="Last name" ng-minlength=3 ng-required required>
        <label for="passnum">Passport number</label>
        <input type="text" class="form-control" name="passnum" ng-model="resSeats.passnum"
        placeholder="Passport" ng-minlength="6" ng-maxlength="6" ng-required required>
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

        <input type="email" class="form-control" name="email" id="email" ng-model="resSeats.email" 
        placeholder="Email address"
        ng-minlength=3 ng-maxlength=50 required />

        <button ng-show="!resSeats.successfullyReserved && !resSeats.inviteFriend" ng-click="reserve(resSeats)">Reserve</button>
        <button ng-show="!resSeats.successfullyReserved && !resSeats.inviteFriend" ng-click="showSearchFriendForm(resSeats)">Invite</button>
        <label ng-show="resSeats.inviteFriend && !resSeats.friendInvited" for="searchForFriends">Search </label>
        <input ng-show="resSeats.inviteFriend && !resSeats.friendInvited" class="form-control" type="search" id="searchForFriends" name="searchForFriends" ng-model="resSeats.searchValue" ng-keyup="search(resSeats)" placeholder="Search new friends" aria-label="Search new friends">
        <ul class="list-group" ng-show="resSeats.inviteFriend">
            <li class="list-group-item" ng-repeat="username in resSeats.filterFriends" ng-click="fillTextBox(username, resSeats)">
                {{username}}
            </li>
        </ul>
        <button ng-show="resSeats.inviteFriend && !resSeats.friendInvited" ng-click="invite(resSeats)">Send</button>
        <button ng-show="resSeats.inviteFriend && !resSeats.friendInvited" class="btn btn-outline-success my-2 my-sm-0" ng-click="hideSearchFriendForm(resSeats)">Cancel</button>
        <label ng-show="!resSeats.successfullyReserved && resSeats.friendInvited">Successfully sent invitation</label>
        <label ng-show="resSeats.successfullyReserved && !resSeats.inviteFriend">Reserved successfully</label>
        <img ng-show="resSeats.successfullyReserved || resSeats.friendInvited" class="img-thumbnail" ng-src="images/reservation-success.png" width='25' height='25'>
    </div>
</div>
<#--  <tt>signupform.$valid = {{signupform.$valid}}</tt><br/>  -->
<div class="form-group row">
<div class="col-xs-12" class="btnbox-center">
    <#--  <a ui-sref="form.interests" class="btn btn-info">
     <span class="glyphicon glyphicon-circle-arrow-left"></span> Back
    </a>
    <a ui-sref="form.payment" class="btn btn-right btn-info" ng-disabled=signupform.$invalid>
    Next <span class="fas fa-arrow-right"></span>
    </a>  -->
    
</div>
</div>