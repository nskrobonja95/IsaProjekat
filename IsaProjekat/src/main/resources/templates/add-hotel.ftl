<link rel="stylesheet" href="css/avio-company-profile.css" />
<br><br>
	<!-- Page Content -->
	<div class="container center-body">
		<div class="container-page">  
            <form>
                <div class="row">
                    <div class="col-sm-6">
                        <h3>Basic about hotel</h3>
                        <div class="form-group col-lg-6">
                            <label>Name:</label> 
                            <input type="text" ng-model="ahCtrl.obj.name" class="form-control" id="name" />
                        </div>
                        <div class="form-group col-lg-6">
                            <label>Promo:</label> <input type="text" ng-model="ahCtrl.obj.promo" class="form-control"
                                    maxlength="150">
                        </div>
                        <div class="col-lg-6">
                            <label>Address:</label>
                            <input id="balcony" type="text" class="form-control" ng-model="ahCtrl.obj.address" />
                        </div>
                        <div class="col-lg-6">
                            <label>Destination:</label>
                            <ui-select class="city1 input-class" ng-model="ahCtrl.selectedDest"
                                theme="selectize" ng-disabled="ctrl.disabled" style="width: 300px;"
                                title="From">
                                <ui-select-match placeholder="Select or search a location in the list...">
                                    {{$select.selected.name}}</ui-select-match>
                                <ui-select-choices
                                    repeat="location in ahCtrl.destList | filter: $select.search">
                                    <span ng-bind-html="location.name | highlight: $select.search"></span>
                                </ui-select-choices>
                            </ui-select>
                        </div>
                    </div>
                    <div class="col-sm-6"> 
                        <h3>Define admin for airline</h3>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" ng-model="ahCtrl.radioAdmin" value="existing" checked>
                            <label class="form-check-label" for="inlineRadio1">Select existing admin</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" ng-model="ahCtrl.radioAdmin" value="new">
                            <label class="form-check-label" for="inlineRadio2">Create new admin</label>
                        </div>
                        <div ng-show="ahCtrl.radioAdmin == 'new' ">
                            <div class="form-group col-lg-6">
                                <label>Email:</label> <input type="email" ng-model="ahCtrl.obj.email" class="form-control"
                                        maxlength="150">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>Username:</label> <input type="text" ng-model="ahCtrl.obj.username" class="form-control"
                                        minlength="1">
                            </div>
                            <div class="form-group col-lg-6">
                                <label>Password:</label> <input type="password" ng-model="ahCtrl.obj.password" class="form-control"
                                        minlength="8">
                            </div>
                        </div>
                        <div ng-show="ahCtrl.radioAdmin == 'existing' ">
                            <div class="form-group">
                                <label for="inputState">Select admin:</label>
                                <select id="inputState" ng-model="ahCtrl.existingAdminId" class="form-control">
                                    <option value="{{admin.id}}" ng-repeat="admin in ahCtrl.hotelAdmins" >{{admin.username}}</option>
                                </select>
                            </div>
                            
                        </div>
                    </div>
                </div>
                <button class="btn btn-info" ng-click="ahCtrl.addHotel()">Save</button>
                <button class="btn btn-danger" ng-click="ahCtrl.goBack()">Cancel</button>
            </form>
        </div>
    </div>