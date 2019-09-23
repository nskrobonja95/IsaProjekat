<div class="company-list-box-div">
		<div class="">
			<section class="head">

				<#--  <div class="top-margin-div "></div>  -->
				<h1 class="text-center " style="margin:25px">Rooms</h1>

			</section>
			<div class="clearfix"></div>
			<section class="search-box list-box-div">
				<div class="container-fluid " style="border:none !important; border-style:none;">
					<div class="row" style="border:none !important; border-style:none;">
						<div class="col-md-12 listing-block" style="float: right; border:none !important; border-style:none;">
							<div class="row mb-3" ng-repeat="room in adminRoomsCtrl.rooms">
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
                                                            <div ng-repeat="price in room.prices">
                                                                <h3 ng-if="adminRoomsCtrl.activePriceCheck(price)">{{price.price}}$</h3>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                    <button ng-click="adminRoomsCtrl.goToEditPage(room)">Edit</button>
                                                    <button ng-click="adminRoomsCtrl.removeRoom(room)">Delete</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
						</div>
					</div>
					<a ui-sref="home-abstract.create-room()">Create room</a>
			</section>
			<div class="top-margin-div "></div>
		</div>