<div class="form-group row">
    <h3 class="text-muted prj-name col-5">
        <span class="fa fa-user-plus fa-2x principal-title"></span>
        Search friends
    </h3>
</div>

<div class="jumbotron list-content" ng-if="fsCtrl.searchResults.length>0">
    <ul class="list-group">
      <li href="#" class="list-group-item title">
        Search results:
      </li>
      <li href="#" class="list-group-item text-left" ng-if="friend.username!=globals.currentUser.username && friend.role=='User' " ng-repeat="friend in fsCtrl.searchResults">
        <img class="img-thumbnail" ng-src="images/user-pic.png" width='100' height='100'>
        <label class="name">
            {{friend.name}} {{friend.lastname}} ({{friend.username}})<br>
        </label>
        <label class="pull-right">
            <button ng-show="fsCtrl.showAdd(friend.id)==0" class="btn btn-success btn-xs" ng-click="fsCtrl.addFriend(friend.id)" title="View"><i class="fas fa-user-plus"></i></button>
            <button ng-show="fsCtrl.showAdd(friend.id)==1" class="btn btn-default btn-xs"  title="View">Requested</button>
            <button ng-show="fsCtrl.showAdd(friend.id)==2" class="btn btn-success btn-xs" ng-click="fsCtrl.accept(friend.id)" title="View"><i class="fas fa-check"></i></button>
            <button ng-show="fsCtrl.showAdd(friend.id)==2" class="btn btn-danger btn-xs" ng-click="fsCtrl.refuse(friend.id)" title="View"><i class="fas fa-window-close"></i></button>
        </label>
        <div class="break"></div>
      </li>
      
    </ul>
  </div>
  <div class="alert alert-warning alert-dismissible fade show" ng-if="!(fsCtrl.searchResults.length>0)" role="alert">
  <strong>No users found!</strong> You should try to find users by using their name or lastname .
</div>

<div ui-view="searchResults"></div>