<div class="company-list-box-div">
		<div class="">
			<section class="head">

				<div class="top-margin-div "></div>
				<div class="row">
					<div class="col-sm-11">
						<h1 class="text-center " style="margin:25px">Airline companies</h1>
					</div>
					<div class="col-sm-1">
						<button class="btn btn-info" ng-click="sysAdminAirlinesCtrl.goToAddAirlinePage()">+</button>
					</div>
				</div>
			</section>
			<div class="clearfix"></div>
			<section class="search-box list-box-div">
				<div class="container-fluid " style="border:none !important; border-style:none;">
					<div class="row" style="border:none !important; border-style:none;">
						<div class="col-md-12 listing-block" style="border:none !important; border-style:none;">
							<div class="media" ng-repeat="avioCompany in sysAdminAirlinesCtrl.companiesList"
								style="border:none !important; border-style:none;">
								<div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
								</div>
								<img class="d-flex align-self-start" style="border-radius:25px 0px 0px 25px;"
									src="images/airplane-company.jpg" alt="Generic placeholder image">
								<div class="media-body pl-3">
									<div class="price"><h3 >{{avioCompany.name}}</h3><small>{{avioCompany.address}}</small>
									</div>

									<div class="address">{{avioCompany.promo}}</div>
								</div>
							</div>

						</div>
					</div>
			</section>
			<div class="top-margin-div "></div>
		</div>
	</div>