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
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" ng-model="aaCtrl.radioAdmin" value="existing" checked>
                            <label class="form-check-label" for="inlineRadio1">Select existing admin</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" ng-model="aaCtrl.radioAdmin" value="new">
                            <label class="form-check-label" for="inlineRadio2">Create new admin</label>
                        </div>
                        <div ng-show="aaCtrl.radioAdmin == 'new' ">
                            <div class="form-group col-lg-6">

                                <label>Email:</label> <input type="email" ng-model="aaCtrl.obj.email" class="form-control"
                                        maxlength="150">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>Username:</label> <input type="text" ng-model="aaCtrl.obj.username" class="form-control"
                                        minlength="1">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>Password:</label> <input type="password" ng-model="aaCtrl.obj.password" class="form-control"
                                        minlength="8">
                            </div>
                        </div>
                        <div ng-show="aaCtrl.radioAdmin == 'existing' ">
                            <div class="form-group">
                                <label for="inputState">Select admin:</label>
                                <select id="inputState" ng-model="aaCtrl.existingAdminId" class="form-control">
                                    <option value="{{admin.id}}" ng-repeat="admin in aaCtrl.avioAdmins" >{{admin.username}}</option>
                                </select>
                            </div>
                            
                        </div>
                    </div>
                </div>
                <button class="btn btn-info" ng-click="aaCtrl.addAirline()">Save</button>
                <button class="btn btn-info" ng-click="aaCtrl.goBack()">Cancel</button>
            </form>
        </div>
    </div>