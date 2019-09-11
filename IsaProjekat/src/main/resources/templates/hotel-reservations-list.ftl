
	<div class="reservation-list-box-div">
		<div class="">
			<section class="head">

				<#--  <div class="top-margin-div "></div>  -->
				<h2 class="text-center " style="margin:25px">Hotel reservations</h2>

			</section>
			
			<div class="clearfix"></div>
			<section class="search-box list-box-div">
				<div class="container-fluid " style="border:none !important; border-style:none;">
					<div class="row" style="border:none !important; border-style:none;">
						<div class="col-md-12 listing-block" style="float: right; border:none !important; border-style:none;">
							<div class="media" ng-repeat="reservation in hotelResListCtrl.hotelReservationsList"
								style="border:none !important; border-style:none;">
								
								<div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
								</div>
								
								<div class="media-body pl-3" style="text-align: left; padding:10px;">
								
								<div class="name_lastname">Reservation status: {{reservation.status}}</div>
									<div class="price" >
										<a class="price" ui-sref="home-abstract.avio-company({companyId:avioCompany.id})">
											{{reservation.room.hotel.name}}
										</a>
										<small>
											From: {{reservation.arrivalDate}} 
											<i class="fas fa-arrow-right"></i> 
											To: {{reservation.departingDate}} 
										</small>
									
									<div class="address">Number of beds: {{reservation.room.numOfBeds}}</div>
									
                                    </div>
                                    
									
								</div>
                                <div style="margin: auto; height:100%" >
                                    <a ng-show="hotelResListCtrl.cancelationEnabled(reservation.id)" ui-sref="" ng-click="hotelResListCtrl.cancelReservation(reservation.id)" class="btn btn-outlined btn-theme btn-lg d-flex align-self-start"  data-wow-delay="0.7s">Cancel<br>Reservation</a>
                                </div>
                        
                                <#--  <img class="d-flex align-self-start" style="border-radius:25px 0px 0px 25px;"
									src="images/airplane-company.jpg" alt="Generic placeholder image">  -->
							</div>

						</div>
					</div>
			</section>
			<#--  <div class="top-margin-div "></div>  -->
		</div>

	