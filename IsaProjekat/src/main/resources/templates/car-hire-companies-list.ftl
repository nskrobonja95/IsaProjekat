<section class="head">
    <div class="container">
        <h2 class="text-center" style="color:white; font-family: Arial, Helvetica, sans-serif;"><span>Rent-a-car companies</span></h2>
    </div>
</section>
<div class="clearfix"></div>
<section class="search-box">
    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12 listing-block">
        <div class="media" ng-repeat = "chCompany in chCtrl.carHireCompaniesList" >
              <div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
              </div>
              <img class="d-flex align-self-start" src="images/car-hire-company.jpg" alt="Generic placeholder image">
              <div class="media-body pl-3">
                <div class="price">{{chCompany.name}}<small>{{chCompany.address}}</small></div>
               
                <div class="address">{{chCompany.promo}}</div>
              </div>
            </div>
           
		
	</div>
</div>
</section>
