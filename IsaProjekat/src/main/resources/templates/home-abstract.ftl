
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#"><img alt="Brand" ng-src="images/airplane-logo" id="brandImg" width="75" height="75"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href><i class="fas fa-plane"></i> Flights <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href ><i class="fas fa-hotel"></i> Hotels <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href ><i class="fas fa-car"></i> Car Hire</a>
      </li>
      
    </ul>
    
    <ul class="navbar-nav ml-auto" ng-if="globals.currentUser.username==null">
      <li class="nav-item">
        <a  class="nav-link" href ui-sref="login"><i class="fas fa-sign-in-alt"></i> Login</a>
      </li>
      <li class="nav-item" >
        <a  class="nav-link" href ui-sref="registration"><i class="fas fa-user-plus"></i> Sign up</a>
      </li>
      <li class="nav-item" ng-if="globals.currentUser.username!=null">
        <a  class="nav-link" href="#" ng-click="baseCtrl.logout()"><i class="fas fa-sign-out-alt"></i> Log Out</a>
      </li>
    </ul>

    <ul class="navbar-nav ml-auto" ng-if="globals.currentUser.username!=null">
        <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle"  id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-user-circle"></i> {{globals.currentUser.username}}
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" ui-sref="guest-abstract.profile-abstract.profile-overview({username:globals.currentUser.username})">Your profile</a>
          <a class="dropdown-item" ui-sref="guest-abstract.settings-abstract.general({username: globals.currentUser.username})">Settings</a>
          <a  class="dropdown-item" href="#" ng-click="baseCtrl.logout()"><i class="fas fa-sign-out-alt"></i> Log Out</a>
        </div>
      </li>
    </ul>
    <#--  <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>  -->
  </div>
</nav>

   <#--  <div ui-view='choosing'></div>
   <div ui-view='cinemas'></div>
   <div ui-view='theatres'></div>
   <div ui-view='profilePage'></div>
   <div ui-view='settingsPage'></div>
   <div ui-view='reserve'></div>
   <div ui-view='registerObject'></div>  -->
   <div ui-view="center"></div>
