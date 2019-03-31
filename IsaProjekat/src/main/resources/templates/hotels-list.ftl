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
          <div class="media" ng-repeat="hotel in hotelCtrl.hotelsList">
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