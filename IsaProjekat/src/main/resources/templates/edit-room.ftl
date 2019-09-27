<link rel="stylesheet" href="css/avio-company-profile.css" />
<br><br>
	<!-- Page Content -->
	<div class="container center-body">
		<div class="container-page">  
            <form>
                <div class="row">
                    <div class="col-sm-6">
                        <h3>Basic</h3>
                        <div class="form-group col-lg-6">
                            <label>Description:</label> 
                            <textarea ng-model="editRoomsCtrl.room.description" class="form-control" id="description" rows="3"></textarea>
                        </div>
                        <div class="form-group col-lg-6">
                            <label>Number of beds:</label> <input type="number" ng-model="editRoomsCtrl.room.numOfBeds" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="chiller_cb col-lg-6">
                            <input id="balcony" type="checkbox" ng-model="editRoomsCtrl.room.balcony" />
                            <label for="balcony">Balcony</label>
                            <span></span>
                        </div>
                        <br><br>
                        <h3>Services</h3>
                        <div class="chiller_cb col-lg-6" ng-repeat="service in editRoomsCtrl.services">
                            <input id="{{service.name}}" type="checkbox" ng-model="editRoomsCtrl.checkedServices[service.name]">
                            <label for="{{service.name}}">{{service.name}}({{service.rate}}$ {{service.charge}})</label>
                            <span></span>
                        </div>
                    </div>
                    <div class="col-sm-6"> 
                        <h3>Prices for months($)</h3>
                        <div class="form-group col-lg-6" ng-repeat="price in editRoomsCtrl.room.prices">
                            <label>from {{price.activeFrom}} to {{price.activeTo}}:</label> <input type="number" ng-model="price.price" class="form-control"
                                    maxlength="150">
                        </div>
                    </div>
                </div>
                <button class="btn btn-danger" ng-click="editRoomsCtrl.editBack()">Cancel</button>
                <button class="btn btn-info" ng-click="editRoomsCtrl.editRoom()">Save</button>
            </form>
        </div>
    </div>