<link rel="stylesheet" href="css/avio-company-profile.css" />
<div class="demo-title">
  <h1 class="display-1 airline-title">{{hpCtrl.hotel.name}}</h1>
  <p>Address: <a href="#">{{hpCtrl.hotel.address}}</a></p>
  <p>Promo: {{hpCtrl.hotel.promo}}</p>
  <br>
  <div style="display:inline-block;"><p>Rating:<div star-rating rating-value="rating.current" max="rating.max" editable="rating.editable">  </div></p></div>
  </div>
</div>

<div class="date">
    <div class="depart">
        <h3>Check in</h3>
        <input id="check-in" ng-model="hpCtrl.checkInDate"
            class="input-class" name="Text" type="text" value="yyyy-MM-dd"
            onfocus="this.value = '';"
            onblur="if (this.value == '') {this.value = 'yyyy-MM-dd';}" required="">
        <span class="checkbox1">
            <label class="checkbox"><input type="checkbox" name="" checked=""><i>
                </i>Flexible with date</label>
        </span>
    </div>
    <div class="return">
        <h3>Check out</h3>
        <input id="check-out" ng-model="hpCtrl.checkOutDate"
            class="input-class" name="Text" type="text" value="yyyy-MM-dd"
            onfocus="this.value = '';"
            onblur="if (this.value == '') {this.value = 'yyyy-MM-dd';}" required="">
        <span class="checkbox1">
            <label class="checkbox"><input type="checkbox" name="" checked=""><i>
                </i>Flexible with date</label>
        </span>
    </div>
    <div class="clear"></div>
</div>
<br>
<button class="btn btn-primary" style="color:blue;" ng-click="hpCtrl.search()">Search</button>

<div id="searchId">

    <!-- <div class="mt-5"></div> -->

    <section class="listings">
        <div class="container">
            <div class="row">
                <div style="height:100px">

                </div>
            </div>

     <div class="col-md-9">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Available rooms</h2>
                        </div>
                    </div>
                    <div class="row mb-3" ng-repeat="room in hpCtrl.availableRooms">
                        
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <img src="https://dynamic.realestateindia.com/proj_images/project14122/proj_img-14122_1-small.jpg">
                                        </div>
                                        <div class="col-md-6  card-body">
                                            <div class="list-title">
                                                <ul class="list-unstyled">
                                                    <li class="list-inline-item">
                                                        <a style="color:blue;">
                                                            <h4>{{room.description}}</h4>
                                                        </a>
                                                    </li>
                                                    <li class="list-item text-warning">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-half"></i>
                                                    </li>
                                                    <#--  <li class="list-item text-success">
                                                        <i class="fa fa-thumbs-up"></i>
                                                    </li>  -->
                                                </ul>
                                            </div>
                                            <div class="list-descrip">
                                                <small>Number of beds: {{room.numOfBeds}}</small>
                                            </div>
                                        </div>
                                        <div class="col-md-3 border-left card-body">
                                            <ul class="list-unstyled">
                                                <li>
                                                    <h3>500 RSD</h3>
                                                </li>
                                                <li class="text-secondary">
                                                    <small>350</small>
                                                </li>
                                                <li>
                                                    <button class="btn btn-primary" style="color:blue;">Book</button>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </section>
    <section class="cta py-5 bg-primary text-white">
        <div class="container">
            <div class="row text-center">
                <div class="col-md-12">
                    <h2>Save time, save money!</h2>
                    <p> Sign up and we'll send the best deals to you </p>
                </div>
            </div>
        </div>
    </section>
    <section class="cta-btm py-2 bg-info">
        <div class="row text-center">
            <div class="col-md-12 ">
                <button type="button" class="btn btn-outline-light">Book Now</button>
                <button type="button" class="btn btn-outline-light">Sell Services</button>
            </div>
        </div>
    </section>
</div>

<script>
    $(function () {
        $("#check-in,#check-out").datepicker();
    });
</script>