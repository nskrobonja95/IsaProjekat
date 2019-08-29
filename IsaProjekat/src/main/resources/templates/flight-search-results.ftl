<#--  Ovo su letovi:{{fsCtrl.flights}}  -->

<div class="company-list-box-div">
		<div class="">
			<section class="head">

				<#--  <div class="top-margin-div "></div>  -->
				<h1 class="text-center " style="margin:25px">Search results</h1>

			</section>
			<div>
				<h3>Filters:</h3>
				<input class="input-class" type="text" placeholder="Airline" ng-model="fsCtrl.airlineFilter" />
				<input class="input-class" type="text" placeholder="Duration" ng-model="fsCtrl.durationFilter" />
				<input class="input-class" type="text" placeholder="Price" ng-model="fsCtrl.priceFilter" />
			</div>
			<div class="clearfix"></div>
			<section class="search-box list-box-div">
				<div class="container-fluid " style="border:none !important; border-style:none;">
					<div class="row" style="border:none !important; border-style:none;">
						<div class="col-md-12 listing-block" style="float: right; border:none !important; border-style:none;">
							<div class="media" ng-repeat="flights in fsCtrl.flightsList | filter: fsCtrl.filterFlights"
								style="border:none !important; border-style:none;">
								
								<div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
								</div>
								
								<div class="media-body pl-3" style="text-align: left; padding:10px;">
									<div class="price" ng-repeat="flight in flights.flight" >
										<a class="price" ui-sref="home-abstract.avio-company({companyId:avioCompany.id})">
											{{flight.avioCompany.name}}
										</a>
										<small>
											{{flight.from.name}} {{flight.takeoff}} 
											<i class="fas fa-arrow-right"></i> 
											{{flight.toDest.name}} {{flight.landing}}
										</small>
									<hr>
                                    </div>
                                    
									<div class="address">Price: {{flights.price}}</div>
								</div>
                                <div style="margin: auto; height:100%" >
                                    <a ui-sref="home-abstract.flight-reservation()"  class="btn btn-outlined btn-theme btn-lg d-flex align-self-start"  data-wow-delay="0.7s">Select</a>
                                </div>
                        
                                <#--  <img class="d-flex align-self-start" style="border-radius:25px 0px 0px 25px;"
									src="images/airplane-company.jpg" alt="Generic placeholder image">  -->
							</div>

						</div>
					</div>
			</section>
			<div class="top-margin-div "></div>
		</div>