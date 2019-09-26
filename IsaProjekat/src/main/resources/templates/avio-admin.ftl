<link rel="stylesheet" href="css/avio-company-profile.css" />
<div class="demo-title">
  <form>
    <label class="pull-left">Name: </label><input class="form-control" ng-model="avioAdminCtrl.companyName" ng-disabled="!avioAdminCtrl.editState"/>
    <br>  
    <label class="pull-left">Address: </label><input class="form-control" ng-model="avioAdminCtrl.companyAddress" ng-disabled="!avioAdminCtrl.editState" />
    <br>
    <label class="pull-left">Promo: </label><input class="form-control" ng-model="avioAdminCtrl.companyPromo" ng-disabled="!avioAdminCtrl.editState" />
  </form>
  <br>
  <button class="btn btn-info" id="editButton" ng-click="avioAdminCtrl.switchToEditState()">Edit</button>
  <button class="btn btn-info" id="cancelButton" ng-click="avioAdminCtrl.cancelEditing()" ng-show="avioAdminCtrl.editState">Cancel</button>
  <div class="accordion accordion-div" id="accordionExample">
    <div class="card">
      <div class="card-header " id="headingOne">
        <h5 class="mb-0">
          <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseOne"
            aria-expanded="false" aria-controls="collapseOne">
            <p class="airline-title">See our destinations</p>
          </button>
        </h5>
      </div>

      <div id="collapseOne" class="collapse " aria-labelledby="headingOne" data-parent="#accordionExample">
        <div class="card-body" align="left">
          <div ng-repeat="dest in avioAdminCtrl.destinations">
            <span><i class="fas fa-city" style="color:#3485ef"></i> {{dest.name}} </span>
            <button class="btn btn-warning pull-right" ng-click="avioAdminCtrl.removeDestination(dest.id)">-</button>
          </div>
          <#--  <button class="btn btn-info" ng-click="avioAdminCtrl.switchAddDestState()" ng-show="!avioAdminCtrl.addDestState">Add</button>  -->
          <form>
            <ui-select class="city1 input-class" ng-model="avioAdminCtrl.newDest"
              theme="selectize" ng-disabled="ctrl.disabled" style="width: 300px;"
              title="From">
              <ui-select-match placeholder="Select or search a location in the list...">
                {{$select.selected.name}}</ui-select-match>
              <ui-select-choices
                repeat="location in avioAdminCtrl.restOfDestinations | filter: $select.search">
                <span ng-bind-html="location.name | highlight: $select.search"></span>
              </ui-select-choices>
            </ui-select>
            <button class="btn btn-info" ng-click="avioAdminCtrl.saveNewDestination()">Save</button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <br>
  <div style="display:inline-block;"><p>Rating:<div star-rating rating-value="rating.current" max="rating.max" editable="rating.editable">  </div></p></div>
</div>