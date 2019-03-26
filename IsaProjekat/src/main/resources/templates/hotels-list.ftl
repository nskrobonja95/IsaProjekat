<section class="head">
    <div class="container">
        <h2 class="text-center" style="color:white; font-family: Arial, Helvetica, sans-serif;"><span>Hotels</span></h2>
    </div>
</section>
<div class="clearfix"></div>
<section class="search-box">
    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12 listing-block">
        <div class="media" ng-repeat = "hotel in hotelCtrl.hotelsList" >
              <div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
              </div>
              <img class="d-flex align-self-start" src="images/airplane-company.jpg" alt="Generic placeholder image">
              <div class="media-body pl-3">
                <div class="price">{{hotel.name}}<small>{{hotel.address}}</small></div>
               
                <div class="address">{{hotel.promo}}</div>
              </div>
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
              <img class="d-flex align-self-start" src="https://images.pexels.com/photos/186077/pexels-photo-186077.jpeg?h=350&auto=compress&cs=tinysrgb" alt="Generic placeholder image">
              <div class="media-body pl-3">
                <div class="price">$799,000<small>New York</small></div>
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
		</div> -->
		
	</div>
</div>
</section>
