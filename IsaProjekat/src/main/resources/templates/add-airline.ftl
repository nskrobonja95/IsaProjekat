<link rel="stylesheet" href="css/avio-company-profile.css" />
<br><br>
	<!-- Page Content -->
	<div class="container center-body">
		<div class="container-page">  
            <form>
                <div class="row">
                    <div class="col-sm-6">
                        <h3>Basic about airline</h3>
                        <div class="form-group col-lg-6">
                            <label>Name:</label> 
                            <input type="text" ng-model="aaCtrl.obj.name" class="form-control" id="name" />
                        </div>
                        <div class="form-group col-lg-6">
                            <label>Promo:</label> <input type="text" ng-model="aaCtrl.obj.promo" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="col-lg-6">
                            <label>Address:</label>
                            <input id="balcony" type="text" class="form-control" ng-model="aaCtrl.obj.address" />
                        </div>
                    </div>
                    <div class="col-sm-6"> 
                        <h3>Define admin for airline</h3>
                        <div class="form-group col-lg-6">
                            <label>Email:</label> <input type="email" ng-model="aaCtrl.obj.email" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>Username:</label> <input type="text" ng-model="aaCtrl.obj.username" class="form-control"
                                    minlength="1">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>Password:</label> <input type="text" ng-model="aaCtrl.obj.password" class="form-control"
                                    minlength="8">
                        </div>
                    </div>
                </div>
                <button class="btn btn-info" ng-click="aaCtrl.addAirline()">Save</button>
            </form>
        </div>
    </div>