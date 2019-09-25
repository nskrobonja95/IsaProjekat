<link rel="stylesheet" href="css/avio-company-profile.css" />
<div class="demo-title">
  <form>
    <label class="pull-left">Name: </label><input class="form-control" ng-model="hotelAdminCtrl.hotelName" ng-disabled="!hotelAdminCtrl.editState"/>
    <br>  
    <label class="pull-left">Address: </label><input class="form-control" ng-model="hotelAdminCtrl.hotelAddress" ng-disabled="!hotelAdminCtrl.editState" />
    <br>
    <label class="pull-left">Promo: </label><input class="form-control" ng-model="hotelAdminCtrl.hotelPromo" ng-disabled="!hotelAdminCtrl.editState" />
  </form>
  <br>
  <button class="btn btn-info" id="editHotelBtn" ng-click="hotelAdminCtrl.switchToEditState()">Edit</button>
  <button class="btn btn-info" id="cancelEditHotelBtn" ng-click="hotelAdminCtrl.cancelEditing()" ng-show="hotelAdminCtrl.editState">Cancel</button>
 <br>
 
  <div class="accordion accordion-div" id="accordionExample">
    <div class="card">
      <div class="card-header " id="headingOne">
        <h5 class="mb-0">
          <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseOne"
            aria-expanded="false" aria-controls="collapseOne">
            <p class="airline-title">Services</p>
          </button>
        </h5>
      </div>

      <div id="collapseOne" class="collapse " aria-labelledby="headingOne" data-parent="#accordionExample">
        <div class="card-body" align="left">
          <div ng-repeat="service in hotelAdminCtrl.hotelServices">
            <span><i style="color:#3485ef"></i> {{service.name}} </span>
            <span><i style="color:#3485ef"></i> ({{service.rate}}$ {{service.charge}}) </span>
            <button class="btn btn-warning pull-right" ng-click="hotelAdminCtrl.removeService(service.id)">-</button>
          </div>
          <button class="btn btn-info" ng-click="hotelAdminCtrl.addService()">Add</button>
        </div>
      </div>
    </div>
  </div>
  <br>
  <div style="display:inline-block;"><p>Rating:<div star-rating rating-value="rating.current" max="rating.max" editable="rating.editable">  </div></p></div>
</div>