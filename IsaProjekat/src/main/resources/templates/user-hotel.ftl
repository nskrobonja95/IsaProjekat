<link rel="stylesheet" href="css/avio-company-profile.css" />
<div class="demo-title">
    <label class="pull-left">Name: {{uhCtrl.hotel.name}}</label>
    <br>  
    <label class="pull-left">Name: {{uhCtrl.hotel.address}}</label>
    <br>
    <label class="pull-left">Name: {{uhCtrl.hotel.promo}}</label>
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
          <div ng-repeat="service in uhCtrl.hotelServices">
            <span><i style="color:#3485ef"></i> {{service.name}} </span>
            <span><i style="color:#3485ef"></i> ({{service.rate}}$ {{service.charge}}) </span>
          </div>
        </div>
      </div>
    </div>
  </div>
  <button ng-click="uhCtrl.goToFastReservationsPage()">Fast Reservations</button>
  <br>
  <div style="display:inline-block;"><p>Rating:<div star-rating rating-value="rating.current" max="rating.max" editable="rating.editable">  </div></p></div>
</div>