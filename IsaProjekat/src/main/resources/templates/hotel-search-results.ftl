<#--  {{hsCtrl.searchRes}}  -->
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
                            <p> 3 Reasons to Visit: gambling, entertainment & relaxation </p>
                        </div>
                    </div>
                    <div class="row mb-3" ng-repeat="room in hsCtrl.searchRes.hotels">
                        
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <img src="https://dynamic.realestateindia.com/proj_images/project14122/proj_img-14122_1-small.jpg">
                                        </div>
                                        <div class="col-md-6  card-body">
                                            <div class="list-title">
                                                <ul class="list-inline list-unstyled">
                                                    <li class="list-inline-item">
                                                        <a href="#" style="color:blue;">
                                                            <h4>{{room.hotel.name}}</h4>
                                                        </a>
                                                    </li>
                                                    <li class="list-inline-item text-warning">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-half"></i>
                                                    </li>
                                                    <li class="list-inline-item text-success">
                                                        <i class="fa fa-thumbs-up"></i>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="list-location">
                                                <a href="#" style="color:blue;">
                                                    <i class="fa fa-map-marker"></i>
                                                    <small> {{room.hotel.destination.name}}</small>
                                                </a>
                                            </div>
                                            <div class="list-descrip">
                                                <small>{{room.description}} </small>
                                            </div>
                                            <div class="list-descrip">
                                                <small>Number of beds: {{room.numOfBeds}} </small>
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
                                                    <button class="btn btn-primary">Book</button>
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