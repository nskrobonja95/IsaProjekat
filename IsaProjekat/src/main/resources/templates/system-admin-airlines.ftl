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
			<section class="search-box list-box-div">
				<div class="accordion accordion-div" id="seeOurDest">
					<div class="card">
					<div class="card-header " id="headingOne">
						<h5 class="mb-0">
						<button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseOne"
							aria-expanded="false" aria-controls="collapseOne">
							<p class="airline-title">Destinations</p>
						</button>
						</h5>
					</div>

					<div id="collapseOne" class="collapse " aria-labelledby="headingOne" data-parent="#seeOurDest">
						<div class="card-body" align="left">
						<div ng-repeat="dest in sysAdminAirlinesCtrl.destinationList">
							<span><i class="fas fa-city" style="color:#3485ef"></i> {{dest.name}} </span>
							<button ng-click="sysAdminAirlinesCtrl.editDest(dest)">Edit</button>
							<button ng-click="sysAdminAirlinesCtrl.deleteDest(dest)">Delete</button>
						</div>
						</div>
					</div>
					</div>

				</div>
				<button class="btn btn-info" ng-click="sysAdminAirlinesCtrl.showAddDestForm()">+</button>
				<form ng-show="sysAdminAirlinesCtrl.addDestFormFlag">
					<label class="pull-left">City: </label><input class="form-control" ng-model="sysAdminAirlinesCtrl.cityName" />
					<br>  
					<label class="pull-left">Country: </label><input class="form-control" ng-model="sysAdminAirlinesCtrl.countryName" />
					<button class="btn btn-info" ng-click="sysAdminAirlinesCtrl.addNewDestination()">Save</button>
				</form>
			</section>
			<div class="top-margin-div "></div>
		</div>
	</div>