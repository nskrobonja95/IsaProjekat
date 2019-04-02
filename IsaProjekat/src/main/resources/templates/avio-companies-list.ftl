<div >
	<div class="search-box-div">

		<div class="top-margin-div"></div>
		<div  class="">
		<h1 >Flight Ticket Booking</h1>
		</div>
		<div class="main-agileinfo">
			<div class="sap_tabs">
				<div id="horizontalTab">
					<ul class="resp-tabs-list">
						<li class="resp-tab-item"><span>Round Trip</span></li>
						<li class="resp-tab-item"><span>One way</span></li>
						<li class="resp-tab-item"><span>Multi city</span></li>
					</ul>
					<div class="clearfix"> </div>
					<div class="resp-tabs-container form-class">
						<div class="tab-1 resp-tab-content roundtrip">
							<form ng-submit="avioCtrl.roundTripSearch()">
								<div class="from">
									<h3>From</h3>
									<input type="text" name="city" ng-model="avioCtrl.roundTrip.from" class="city1 input-class"
										placeholder="Type Departure City" required="">
								</div>
								<div class="to">
									<h3>To</h3>
									<input type="text" name="city" ng-model="avioCtrl.roundTrip.to" class="city2 input-class"
										placeholder="Type Destination City" required="">
								</div>
								<div class="clear"></div>
								<div class="date">
									<div class="depart">
										<h3>Depart</h3>
										<input id="datepicker" ng-model="avioCtrl.roundTripDepartDate" class="input-class" name="Text" type="text"
											value="yyyy-MM-dd" onfocus="this.value = '';"
											onblur="if (this.value == '') {this.value = 'yyyy-MM-dd';}" required="">
										<span class="checkbox1">
											<label class="checkbox"><input type="checkbox" name="" checked=""><i>
												</i>Flexible with date</label>
										</span>
									</div>
									<div class="return">
										<h3>Return</h3>
										<input id="datepicker1" ng-model="avioCtrl.roundTripReturnDate" class="input-class" name="Text" type="text"
											value="yyyy-MM-dd" onfocus="this.value = '';"
											onblur="if (this.value == '') {this.value = 'yyyy-MM-dd';}" required="">
										<span class="checkbox1">
											<label class="checkbox"><input type="checkbox" name="" checked=""><i>
												</i>Flexible with date</label>
										</span>
									</div>
									<div class="clear"></div>
								</div>
								<div class="class">
									<h3>Class</h3>
									<select id="w3_country1" ng-model="avioCtrl.roundTrip.flightClass"
										class="frm-field required">
										<option value="Economic">Economy</option>
										<option value="Bussiness">Business</option>
									</select>

								</div>
								<div class="clear"></div>
								<div class="numofppl">
									<div class="adults">
										<h3>Adult:(12+ yrs)</h3>
										<div class="quantity">
											<div class="quantity-select">
												<div class="entry value-minus" ng-click="avioCtrl.decNumOfPpl()">&nbsp;</div>
												<div class="entry value"><span ng-bind="avioCtrl.roundTrip.numOfPpl"></span></div>
												<div class="entry value-plus active" ng-click="avioCtrl.incNumOfPpl()">&nbsp;</div>
											</div>
										</div>
									</div>
									<div class="child">
										<h3>Child:(2-11 yrs)</h3>
										<div class="quantity">
											<div class="quantity-select">
												<div class="entry value-minus">&nbsp;</div>
												<div class="entry value"><span>1</span></div>
												<div class="entry value-plus active">&nbsp;</div>
											</div>
										</div>
									</div>
									<div class="clear"></div>
								</div>
								<div class="clear"></div>
								<input type="submit" value="Search Flights">
							</form>
						</div>
						<div class="tab-1 resp-tab-content oneway">
							<form ng-submit="avioCtrl.oneWaySearch()">
								<div class="from">
									<h3>From</h3>
									<input type="text" name="city" ng-model="avioCtrl.oneWay.from" class="city1 input-class"
										placeholder="Type Departure City" required="">
								</div>
								<div class="to">
									<h3>To</h3>
									<input type="text" name="city" ng-model="avioCtrl.oneWay.to" class="city2 input-class"
										placeholder="Type Destination City" required="">
								</div>
								<div class="clear"></div>
								<div class="date">
									<div class="depart">
										<h3>Depart</h3>
										<input class="date input-class" ng-model="avioCtrl.oneWayDepartDate" id="datepicker2" name="Text" type="text"
											value="mm/dd/yyyy" onfocus="this.value = '';"
											onblur="if (this.value == '') {this.value = 'mm/dd/yyyy';}" required="">
										<span class="checkbox1">
											<label class="checkbox"><input type="checkbox" name="" checked=""><i>
												</i>Flexible with date</label>
										</span>
									</div>
								</div>
								<div class="class">
									<h3>Class</h3>
									<select id="w3_country2" ng-model="avioCtrl.oneWay.flightClass"
										class="frm-field required">
										<option value="Economic">Economy</option>
										<option value="Bussiness">Business</option>
									</select>

								</div>
								<div class="clear"></div>
								<div class="numofppl">
									<div class="adults">
										<h3>Adult:(12+ yrs)</h3>
										<div class="quantity">
											<div class="quantity-select">
												<div class="entry value-minus" ng-click="avioCtrl.decNumOfPplOneWay()">&nbsp;</div>
												<div class="entry value"><span ng-bind="avioCtrl.oneWay.numOfPpl"></span></div>
												<div class="entry value-plus active" ng-click="avioCtrl.incNumOfPplOneWay()">&nbsp;</div>
											</div>
										</div>
									</div>
									<div class="child">
										<h3>Child:(2-11 yrs)</h3>
										<div class="quantity">
											<div class="quantity-select">
												<div class="entry value-minus">&nbsp;</div>
												<div class="entry value"><span></span></div>
												<div class="entry value-plus active">&nbsp;</div>
											</div>
										</div>
									</div>
									<div class="clear"></div>
								</div>
								<div class="clear"></div>
								<input type="submit" value="Search Flights">
							</form>

						</div>
						<div class="tab-1 resp-tab-content multicity">

							<form>
								<div class="from">
									<h3>From</h3>
									<input type="text" name="city" ng-model="avioCtrl.multiCity.from" class="city1 input-class"
										placeholder="Type Departure City" required="">
								</div>
								<div class="to">
									<h3>To</h3>
									<input type="text" name="city" ng-model="avioCtrl.multiCity.midDest" class="city2 input-class"
										placeholder="Type Destination City" required="">
								</div>
								<div class="clear"></div>
								<div class="date">
									<div class="depart">
										<h3>Depart</h3>
										<input class="date input-class" ng-model="avioCtrl.multiCityDepartDate1" id="datepicker3" name="Text" type="text"
											value="mm/dd/yyyy" onfocus="this.value = '';"
											onblur="if (this.value == '') {this.value = 'mm/dd/yyyy';}" required="">
										<span class="checkbox1">
											<label class="checkbox"><input type="checkbox" name="" checked=""><i>
												</i>Flexible with date</label>
										</span>
									</div>
								</div>
								<div class="class">
									<h3>Class</h3>
									<select id="w3_country3"
										class="frm-field required">
										<option value="Economic">Economy</option>
										<option value="Bussiness">Business</option>
									</select>
								</div>
								<div class="clear"></div>
								<div id="loadMore">Add City+</div>
								<div id="showLess">Remove</div>
							</form>
							<div class="load_more">
								<ul id="myList">
									<li>

										<div class="l_g spcl">
											<form class="blackbg">
												<div class="from">
													<h3>From</h3>
													<input type="text" name="city" ng-model="avioCtrl.multiCity.midDest" class="city1 input-class"
														placeholder="Type Departure City" required="">
												</div>
												<div class="to">
													<h3>To</h3>
													<input type="text" name="city" ng-model="avioCtrl.multiCity.to" class="city2 input-class"
														placeholder="Type Destination City" required="">
												</div>
												<div class="clear"></div>
												<div class="date">
													<div class="depart">
														<h3>Depart</h3>
														<input class="date input-class" ng-model="avioCtrl.multiCityDepartDate2" id="datepicker4" name="Text"
															type="text" value="mm/dd/yyyy" onfocus="this.value = '';"
															onblur="if (this.value == '') {this.value = 'mm/dd/yyyy';}"
															required="">
														<span class="checkbox1">
															<label class="checkbox"><input type="checkbox" name=""
																	checked=""><i> </i>Flexible with
																date</label>
														</span>
													</div>
												</div>
												<div class="clear"></div>
											</form>

										</div>

									</li>
									<form ng-submit="avioCtrl.multiCitySearch()">
										<div class="numofppl">
											<div class="adults">
												<h3>Adult:(12+ yrs)</h3>
												<div class="quantity">
													<div class="quantity-select">
														<div class="entry value-minus" ng-click="avioCtrl.decNumOfPplMultiCity()">&nbsp;</div>
														<div class="entry value"><span ng-bind="avioCtrl.multiCity.numOfPpl"></span></div>
														<div class="entry value-plus active" ng-click="avioCtrl.incNumOfPplMultiCity()">&nbsp;</div>
													</div>
												</div>
											</div>
											<div class="child">
												<h3>Child:(2-11 yrs)</h3>
												<div class="quantity">
													<div class="quantity-select">
														<div class="entry value-minus">&nbsp;</div>
														<div class="entry value"><span>1</span></div>
														<div class="entry value-plus active">&nbsp;</div>
													</div>
												</div>
											</div>
											<div class="clear"></div>
										</div>
										<div class="clear"></div>
										<input type="submit" value="Search Flights" />
									</form>
								</ul>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="top-margin-div"></div>
	</div>
	<!--script for portfolio-->

	<script type="text/javascript">
		$(document).ready(function () {
			$('#horizontalTab').easyResponsiveTabs({
				type: 'default', //Types: default, vertical, accordion           
				width: 'auto', //auto or any width like 600px
				fit: true // 100% fit in a container
			});
		});
	</script>
	<!--//script for portfolio-->
	<!-- Calendar -->
	<script>
		$(function () {
			$("#datepicker,#datepicker1,#datepicker2,#datepicker3, #datepicker4").datepicker();
		});
	</script>
	<!-- //Calendar -->
	<!--quantity-->
	<script>
		//$('.value-plus').on('click', function () {
		//	var divUpd = $(this).parent().find('.value'),
		//		newVal = parseInt(divUpd.text(), 10) + 1;
		//	divUpd.text(newVal);
		//});

		//$('.value-minus').on('click', function () {
		//	var divUpd = $(this).parent().find('.value'),
		//		newVal = parseInt(divUpd.text(), 10) - 1;
		//	if (newVal >= 1) divUpd.text(newVal);
		//});
	</script>
	<!--//quantity-->
	<!--load more-->
	<script>
		$(document).ready(function () {
			size_li = $("#myList li").size();
			x = 1;
			$('#myList li:lt(' + x + ')').show();
			$('#loadMore').click(function () {
				x = (x + 1 <= size_li) ? x + 1 : size_li;
				$('#myList li:lt(' + x + ')').show();
			});
			$('#showLess').click(function () {
				x = (x - 1 < 0) ? 1 : x - 1;
				$('#myList li').not(':lt(' + x + ')').hide();
			});


		});
	</script>
	<!-- //load-more -->



	<div class="top-margin-div" style="text-align:center;" >
		<h2 style="position: relative; top: 50%; transform: translateY(-50%); color:black;">Search Flights, Hotels & Car Hire to our most popular destinations....</h2>
	</div>
	<div class="company-list-box-div">
		<div class="">
		<section class="head">
			
			<div class="top-margin-div "></div>
			<h1 class="text-center " style="margin:25px">Airline companies</h1>
			
		</section>
		<div class="clearfix"></div>
		<section class="search-box list-box-div">
			<div class="container-fluid " style="border:none !important; border-style:none;" >
				<div class="row" style="border:none !important; border-style:none;">
					<div class="col-md-12 listing-block" style="border:none !important; border-style:none;">
						<div class="media" ng-repeat="avioCompany in avioCtrl.companiesList" style="border:none !important; border-style:none;">
							<div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
							</div>
							<img class="d-flex align-self-start" style="border-radius:25px 0px 0px 25px;" src="images/airplane-company.jpg"
								alt="Generic placeholder image">
							<div class="media-body pl-3">
								<div class="price"><a class="price"
										ui-sref="home-abstract.avio-company({companyId:avioCompany.id})">{{avioCompany.name}}</a><small>{{avioCompany.address}}</small>
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
</div>
