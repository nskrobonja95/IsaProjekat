<link rel="stylesheet" href="css/avio-company-profile.css" />
<div class="demo-title">
  <h1 class="display-1 airline-title">{{acCtrl.avioCompany.name}}</h1>
  <p>Address: <a href="#">{{acCtrl.avioCompany.address}}</a></p>
  <p>Promo: {{acCtrl.avioCompany.promo}}</p>
  <br>
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
          <div ng-repeat="dest in acCtrl.destinations">
            <span><i class="fas fa-city" style="color:#3485ef"></i> {{dest.name}} </span>
          </div>

        </div>
      </div>
    </div>

  </div>
  <br>
  <div style="display:inline-block;"><p>Rating:<div star-rating rating-value="rating.current" max="rating.max" editable="rating.editable">  </div></p></div>
</div>
