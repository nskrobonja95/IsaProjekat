<br><br>
<div class="container form-class">
    <div class="row">
        <div class="col-3 float-left " role="navigation">
            <nav class="nav flex-column">
                <a class="nav-link active" ui-sref="home-abstract.profile-abstract.profile-overview({username:globals.currentUser.username})">
                <i class="fas fa-list-ul"></i> Overview
                </a>
                <a ng-show="globals.currentUser.userType == 'User' " class="nav-link" ui-sref="home-abstract.profile-abstract.friends-abstract.friends-list({username:globals.currentUser.username})">
                <i class="fas fa-users"></i> Friends
                </a>
                <a ng-show="globals.currentUser.userType == 'User' " class="nav-link" ui-sref="home-abstract.profile-abstract.reservations-list({username:globals.currentUser.username})">
                <i class="fab fa-rendact"></i> Flight Reservations
                </a>
                <a ng-show="globals.currentUser.userType == 'User' " class="nav-link" ui-sref="home-abstract.profile-abstract.hotel-reservations-list({username:globals.currentUser.username})">
                <i class="fab fa-rendact"></i> Hotel Reservations
                </a>
                <a ng-show="globals.currentUser.userType == 1" class="nav-link" ui-sref="guest-abstract.profile-abstract.profile-usersList">
                <i class="fas fa-users"></i> Users
                </a>
                
                <a ng-show="globals.currentUser.userType == 1" class="nav-link" ui-sref="guest-abstract.profile-abstract.profile-usersList">
                <i class="fas fa-users"></i> Statistics
                </a>
            </nav>
        </div>
        <div class="col-9 float-left">
            <div ui-view='profile-overview'></div>
            <div ui-view='friends-abstract'></div>  
            <div ui-view='reservations-list'></div> 
            <div ui-view='hotel-reservations-list'></div>
            
        </div>
    </div>
</div>