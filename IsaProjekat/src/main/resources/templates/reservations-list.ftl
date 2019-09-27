<div class="reservation-list-box-div">
		<div class="">
			<section class="head">

				<#--  <div class="top-margin-div "></div>  -->
				<h2 class="text-center " style="margin:25px">Flight reservations</h2>

			</section>
			
			<div class="clearfix"></div>
			<section class="search-box list-box-div">
				<div class="container-fluid " style="border:none !important; border-style:none;">
					<div class="row" style="border:none !important; border-style:none;">
						<div class="col-md-12 listing-block" style="float: right; border:none !important; border-style:none;">
							<div class="media" ng-repeat="reservation in resListCtrl.flightReservationsList"
								style="border:none !important; border-style:none;">
								
								<div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
								</div>
								
								<div class="media-body pl-3" style="text-align: left; padding:10px;">
								<div class="name_lastname">First name: {{reservation.name}}</div>
								<div class="name_lastname">Last name: {{reservation.lastname}}</div>
								<div class="name_lastname">Reservation status: {{reservation.status}}</div>
									<div class="price" ng-repeat="seat in reservation.seats" >
										<a class="price" ui-sref="home-abstract.avio-company({companyId:avioCompany.id})">
											{{seat.flight.avioCompany.name}}
										</a>
										<small>
											{{seat.flight.from.name}} {{seat.flight.takeoff}} 
											<i class="fas fa-arrow-right"></i> 
											{{seat.flight.toDest.name}} {{seat.flight.landing}}
										</small>
									<hr>
									<div class="address">Flight class: {{seat.flightClass}}</div>
									<div class="address">Row number: {{seat.rowNum}}</div>
									<div class="address">Seat number: {{seat.seatNumber}}</div>
									
									<div class="btn-group" ng-show="resListCtrl.rateEnabled(reservation.reservationId, seat.id)">
										<button type="button" ng-click="resListCtrl.rateRes(reservation.reservationId, seat.flight.id)" class="btn btn-primary">Rate</button>
										
										<select class="form-control" id="exampleFormControlSelect1" ng-model="resListCtrl.rate">
											<option >1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
										</select>
									</div>
                                    </div>
                                    
									
								</div>
                                <div style="margin: auto; height:100%" >
                                    <a ng-show="resListCtrl.cancelationEnabled(reservation.reservationId)" ui-sref="" ng-click="resListCtrl.cancelReservation(reservation.reservationId)" class="btn btn-outlined btn-theme btn-lg d-flex align-self-start"  data-wow-delay="0.7s">Cancel<br>Reservation</a>
                                </div>
                        
                                <#--  <img class="d-flex align-self-start" style="border-radius:25px 0px 0px 25px;"
									src="images/airplane-company.jpg" alt="Generic placeholder image">  -->
							</div>

						</div>
					</div>
			</section>
			<#--  <div class="top-margin-div "></div>  -->
		</div>