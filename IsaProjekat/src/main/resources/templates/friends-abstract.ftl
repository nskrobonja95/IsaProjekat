<div>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
   <span class="navbar-brand mb-0 h1">Friends</span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link"ui-sref="home-abstract.profile-abstract.friends-abstract.friends-list({username:globals.currentUser.username})">
        Friends List <span class="sr-only">(current)</span>
        </a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" name="searchForm" ng-submit="friendCtrl.goToSearch()">
      <input class="form-control mr-sm-2" type="search" name="searchNewFriends" ng-model="friendCtrl.searchValue" placeholder="Search new friends" aria-label="Search new friends">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<br><br>
<div ui-view="friends-list"></div>
<div ui-view="friends-search"></div>
</div>