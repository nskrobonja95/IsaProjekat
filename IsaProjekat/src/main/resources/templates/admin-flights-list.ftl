<div class="company-list-box-div">
		<div class="">
			<section class="head">

				<#--  <div class="top-margin-div "></div>  -->
				<h1 class="text-center " style="margin:25px">Flights</h1>

			</section>
			<div class="clearfix"></div>
			<section class="search-box list-box-div">
				<div class="container-fluid " style="border:none !important; border-style:none;">
					<div class="row" style="border:none !important; border-style:none;">
						<div class="col-md-12 listing-block" style="float: right; border:none !important; border-style:none;">
							<div class="media" ng-repeat="flight in adminFlightsCtrl.flights"
								style="border:none !important; border-style:none;">
								
								<div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
								</div>
								
								<div class="media-body pl-3" style="text-align: left; padding:10px;">
									<div class="price" ><a class="price"
											ui-sref="home-abstract.avio-company({companyId:avioCompany.id})">{{flight.avioCompany.name}}</a><small>{{flight.from.name}} {{flight.takeoff}} <i class="fas fa-arrow-right"></i> {{flight.toDest.name}} {{flight.landing}}</small>
									<hr>
                                    </div>
                                    
									<#--  <div class="address">Price: {{flights.price}}</div>  -->
								</div>
                                <div style="margin: auto; height:100%" >
                                    <a href="#"  class="btn btn-outlined btn-theme btn-lg d-flex align-self-start"  data-wow-delay="0.7s">Select</a>
                                    <a href="#"  class="btn btn-outlined btn-theme btn-lg d-flex align-self-start"  data-wow-delay="0.7s">Book hotel</a>
                                </div>
                        
                                <#--  <img class="d-flex align-self-start" style="border-radius:25px 0px 0px 25px;"
									src="images/airplane-company.jpg" alt="Generic placeholder image">  -->
							</div>

						</div>
					</div>
			</section>
			<div class="top-margin-div "></div>
		</div>