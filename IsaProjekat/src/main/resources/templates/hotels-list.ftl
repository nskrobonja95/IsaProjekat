<div>
  <div class="search-box-div">

      <div class="top-margin-div"></div>
      <div  class="">
        <h1 >Accomodation Booking</h1>
      </div>
      <div class="main-agileinfo">
        <div class="sap_tabs">
          <div id="horizontalTab">
            <ul class="resp-tabs-list">
              <li class="resp-tab-item"><span>Search</span></li>
            </ul>
            <div class="clearfix"></div>
            <div class="resp-tabs-container form-class">
              <div class="tab-1 resp-tab-content roundtrip">
                <form ng-submit="hotelCtrl.search()">
                  <div class="from">
                    <h3>Destination</h3>
                    <#-- <input type="text" name="city" ng-model="avioCtrl.multiCity.from"
										class="city1 input-class" placeholder="Type Departure City" required=""> -->
										<ui-select class="city1 input-class" ng-model="hotelCtrl.searchObj.dest"
											theme="selectize" ng-disabled="ctrl.disabled" style="width: 300px;"
											title="From">
											<ui-select-match placeholder="Select or search a location in the list...">
												{{$select.selected.name}}</ui-select-match>
											<ui-select-choices
												repeat="location in hotelCtrl.destList | filter: $select.search">
												<span ng-bind-html="location.name | highlight: $select.search"></span>
											</ui-select-choices>
										</ui-select>
                  </div>
                  <div class="clear"></div>
                  <div class="date">
                    <div class="depart">
                      <h3>Check-in</h3>
                      <input id="datepicker" ng-model="hotelCtrl.checkInDate" class="input-class" name="Text" type="text"
                        value="yyyy-MM-dd" onfocus="this.value = '';"
                        onblur="if (this.value == '') {this.value = 'yyyy-MM-dd';}" required="">
                      <span class="checkbox1">
                        <label class="checkbox"><input type="checkbox" name="" checked=""><i>
                          </i>Flexible with date</label>
                      </span>
                    </div>
                    <div class="return">
                      <h3>Check-out</h3>
                      <input id="datepicker1" ng-model="hotelCtrl.checkOutDate" class="input-class" name="Text" type="text"
                        value="yyyy-MM-dd" onfocus="this.value = '';"
                        onblur="if (this.value == '') {this.value = 'yyyy-MM-dd';}" required="">
                      <span class="checkbox1">
                        <label class="checkbox"><input type="checkbox" name="" checked=""><i>
                          </i>Flexible with date</label>
                      </span>
                    </div>
                  </div>
                  <div class="clear"></div>
                  <input type="submit" value="Search Hotels">
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="top-margin-div"></div>
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
<div class="search-box-div">
  <section class="head">
    <div class="container">
      <h2 class="text-center"><span style="color:white; font-family: Verdana, Geneva, sans-serif;">Hotels</span></h2>
    </div>
  </section>
  <div class="clearfix"></div>
  <section class="search-box">
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-12 listing-block">
        <div class="media" ng-repeat = "hotel in hotelCtrl.hotelsList" >
          <div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i></div>
          <img class="d-flex align-self-start" src="images/hotels.jpg" alt="Generic placeholder image">
          <div class="media-body pl-3">
            <div class="price"><a class="price" ui-sref="home-abstract.user-hotel({hotelId:hotel.id})">{{hotel.name}}</a><small>{{hotel.address}}</small>
            </div>
            <div class="address">{{hotel.promo}}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </section>
</div>
            <#--
        <div class="media">
            <div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
            </div> 
              <img class="d-flex align-self-start" src="https://images.pexels.com/photos/106399/pexels-photo-106399.jpeg?h=350&auto=compress&cs=tinysrgb" alt="Generic placeholder image">
              <div class="media-body pl-3">
                <div class="price">$506,400<small>New York</small></div>
                <div class="stats">
                    <span><i class="fa fa-arrows-alt"></i>1678 Sq ft</span>
                    <span><i class="fa fa-bath"></i>2 Beadrooms</span>
                </div>
                <div class="address">4062 Walnut Hill Drive
            Cincinnati</div>
              </div>
            </div>
        <div class="media">
            <div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
</div>
              <img class="d-flex align-self-start" src="https://images.pexels.com/photos/358636/pexels-photo-358636.jpeg?h=350&auto=compress&cs=tinysrgb" alt="Generic placeholder image">
              <div class="media-body pl-3">
                <div class="price">$506,400<small>New York</small></div>
                <div class="stats">
                    <span><i class="fa fa-arrows-alt"></i>1678 Sq ft</span>
                    <span><i class="fa fa-bath"></i>2 Beadrooms</span>
                </div>
                <div class="address">4062 Walnut Hill Drive
            Cincinnati</div>
              </div>
            </div>
        <div class="media">
            <div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
            </div>
            <img class="d-flex align-self-start" src="images/hotels.jpg" alt="Generic placeholder image">
            <div class="media-body pl-3">
              <div class="price" ui-sref="home-abstract.hotel({hotelId:hotel.id})">
                {{hotel.name}}<small>{{hotel.address}}</small></div>

              <div class="address">{{hotel.promo}}</div>
            </div>
          </div>
          

    </div>
    </div>
  </section>
</div>