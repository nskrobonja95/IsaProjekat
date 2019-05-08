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
                            <textarea ng-model="createRoomCtrl.description" class="form-control" id="description" rows="3"></textarea>
                        </div>
                        <div class="form-group col-lg-6">
                            <label>Number of beds:</label> <input type="number" ng-model="createRoomCtrl.numOfBeds" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="chiller_cb col-lg-6">
                            <input id="balcony" type="checkbox" ng-model="createRoomCtrl.balcony" />
                            <label for="balcony">Balcony</label>
                            <span></span>
                        </div>
                        <br><br>
                        <h3>Services</h3>
                        <div class="chiller_cb col-lg-6" ng-repeat="service in createRoomCtrl.services">
                            <input id="{{service.name}}" type="checkbox" ng-model="createRoomCtrl.checkedServices[service.name]">
                            <label for="{{service.name}}">{{service.name}}({{service.rate}}$ {{service.charge}})</label>
                            <span></span>
                        </div>
                    </div>
                    <div class="col-sm-6"> 
                        <h3>Prices for months($)</h3>
                        <div class="form-group col-lg-6">
                            <label>January:</label> <input type="number" ng-model="createRoomCtrl.prices.january" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>February:</label> <input type="number" ng-model="createRoomCtrl.prices.february" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>March:</label> <input type="number" ng-model="createRoomCtrl.prices.march" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>April:</label> <input type="number" ng-model="createRoomCtrl.prices.april" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>May:</label> <input type="number" ng-model="createRoomCtrl.prices.may" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>Jun:</label> <input type="number" ng-model="createRoomCtrl.prices.june" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>July:</label> <input type="number" ng-model="createRoomCtrl.prices.july" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>August:</label> <input type="number" ng-model="createRoomCtrl.prices.august" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>September:</label> <input type="number" ng-model="createRoomCtrl.prices.september" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>October:</label> <input type="number" ng-model="createRoomCtrl.prices.october" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>November:</label> <input type="number" ng-model="createRoomCtrl.prices.november" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="form-group col-lg-6">
                            <label>December:</label> <input type="number" ng-model="createRoomCtrl.prices.december" class="form-control"
                                    maxlength="150">
                        </div>
                    </div>
                </div>
                <button class="btn btn-info" ng-click="createRoomCtrl.addRoom()">Save</button>
            </form>
        </div>
    </div>