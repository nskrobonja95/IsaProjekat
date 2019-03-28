<section class="head">
    <div class="container">
        <h2 class="text-center" style="color:white; font-family: Arial, Helvetica, sans-serif;"><span>Airline companies</span></h2>
    </div>
</section>
<div class="clearfix"></div>
<section class="search-box">
    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12 listing-block">
        <div class="media" ng-repeat = "avioCompany in avioCtrl.companiesList" >
              <div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
              </div>
              <img class="d-flex align-self-start" src="images/airplane-company.jpg" alt="Generic placeholder image">
              <div class="media-body pl-3">
                <div class="price"><a class="price" ui-sref="home-abstract.avio-company({companyId:avioCompany.id})">{{avioCompany.name}}</a><small>{{avioCompany.address}}</small></div>
               
                <div class="address">{{avioCompany.promo}}</div>
              </div>
            </div>
		
	</div>
</div>
</section>
