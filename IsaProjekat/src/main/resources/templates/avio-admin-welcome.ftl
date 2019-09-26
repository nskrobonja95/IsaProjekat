<div class="top-margin-div" style="text-align:center;" >
	<h1 style="position: relative; top: 50%; transform: translateY(-50%); color:black;">Welcome Avio Admin {{globals.currentUser.username  }}</h1>
</div>
<div class="search-box-div">
  <section class="head">
    <div class="container">
      <h2 class="text-center"><span style="color:white; font-family: Verdana, Geneva, sans-serif;">Avio Companies list</span></h2>
    </div>
  </section>
  <div class="clearfix"></div>
  <section class="search-box">
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-12 listing-block">
        <div class="media" ng-repeat = "avio in aawCtrl.avioList" >
          <div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i></div>
          <img class="d-flex align-self-start" src="images/airplane-company.jpg" alt="Generic placeholder image">
          <div class="media-body pl-3">
            <div class="price"><a class="price" ui-sref="home-abstract.avio-admin-avio-profile.avio-admin({avioId:avio.id})">{{avio.name}}</a><small>{{avio.address}}</small>
            </div>
            <div class="address">{{avio.promo}}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </section>
</div>
