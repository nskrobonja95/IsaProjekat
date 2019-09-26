<!-- Include Bootstrap Datepicker -->
<link rel="stylesheet" href="css/datetimepicker.css" />
<script src="js\lib\bootstrap-datetimepicker.js"></script>

<div style="padding-left:50px">
<form name="createFlightForm">
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="from">From:</label>
                <ui-select type="text" class="form-control" ng-model="createFlightCtrl.from"
                    theme="selectize" style="width: 300px;"
                    title="From" id="from">
                    <ui-select-match placeholder="Select or search a location in the list...">
                        {{$select.selected.name}}</ui-select-match>
                    <ui-select-choices
                        repeat="location in createFlightCtrl.destinations | filter: $select.search">
                        <span ng-bind-html="location.name | highlight: $select.search"></span>
                    </ui-select-choices>
                </ui-select>
            </div>
            <div class="form-group">
                <label for="to">To:</label>
                <ui-select type="text" class="form-control" ng-model="createFlightCtrl.to"
                    theme="selectize" style="width: 300px;"
                    title="From" id="to">
                    <ui-select-match placeholder="Select or search a location in the list...">
                        {{$select.selected.name}}</ui-select-match>
                    <ui-select-choices
                        repeat="location in createFlightCtrl.destinations | filter: $select.search">
                        <span ng-bind-html="location.name | highlight: $select.search"></span>
                    </ui-select-choices>
                </ui-select>
            </div>
            <div class="form-group">
                <label for="flightDepart">Depart:</label>
                <input type="text" class="form-control" id="flightDepart" ng-model="createFlightCtrl.depart" />
            </div>
            <div class="form-group">
                <label for="flightLanding">Landing:</label>
                <input type="text" class="form-control" id="flightLanding" ng-model="createFlightCtrl.landing" />
            </div>
            <div class="form-group">
                <label for="inputState">Configuration type:</label>
                <select id="inputState" ng-model="createFlightCtrl.selectedType"
                    class="form-control" ng-change="createFlightCtrl.setSelectedConfig()">
                    <option value="smalljet" selected>Small Jet</option>
                    <option value="mediumjet">Medium Jet</option>
                    <option value="airbus">Airbus</option>
                    <option value="jumbojet">Jumbo Jet</option>
                </select>
            </div>
            <div class="form-group">
                <label for="numOfRows">Number of rows:</label>
                <input type="number" ng-model="createFlightCtrl.seatRowNum" 
                    ng-change="createFlightCtrl.evaluateChange()" min="0" max="15" 
                    class="form-control" id="numOfRows" name="numOfRows">
            </div>
            <div class="form-group">
                <label for="baggage_over7">Price over 7kg(baggage):</label>
                <input type="number" class="form-control" ng-model="createFlightCtrl.bggOver7" id="baggage_over7">
            </div>
            <div class="form-group">
                <label for="baggage_over14">Price over 14kg(baggage):</label>
                <input type="number" class="form-control" ng-model="createFlightCtrl.bggOver14" id="baggage_over14">
            </div>
            <div class="form-group">
                <label for="economicPrice">Economy class price($):</label>
                <input type="number" class="form-control" ng-model="createFlightCtrl.economicPrice" id="economicPrice">
            </div>
            <div class="form-group">
                <label for="businessPrice">Business class price($):</label>
                <input type="number" class="form-control" ng-model="createFlightCtrl.businessPrice" id="businessPrice">
            </div>
            <div class="form-group">
                <label for="discount">Discount(%):</label>
                <input type="number" class="form-control" ng-model="createFlightCtrl.discount" id="discount">
            </div>
        </div>
        <div class="col-sm-8">  
            <div ng-repeat="seatRow in createFlightCtrl.seatConfigObj" class="row">
                <div ng-repeat="seat in seatRow">
                    <svg width="70" height="70" ng-show="seat.flightClass == 'business' && !seat.fastRes && !(seat.flightClass == 'economic')">
                        <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                        stroke="orange" class="business"
                            stroke-width="2" fill="blue" ng-click="createFlightCtrl.setToFastRes(seat)" />
                    </svg>
                    <svg width="70" height="70" ng-show="seat.flightClass == 'economic' && !seat.fastRes && !(seat.flightClass == 'business')">
                        <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                        stroke="blue" class="economic"
                            stroke-width="2" fill="orange" ng-click="createFlightCtrl.setToFastRes(seat)" />
                    </svg>
                    <svg width="70" height="70" ng-show="seat.flightClass == 'economic' && seat.fastRes && !(seat.flightClass == 'business')">
                        <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                        stroke="red" class="business"
                            stroke-width="2" fill="black" ng-click="createFlightCtrl.setToFastRes(seat)" />
                    </svg>
                    <svg width="70" height="70" ng-show="!(seat.flightClass == 'economic') && seat.fastRes && seat.flightClass == 'business'">
                        <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                        stroke="black" class="economic"
                            stroke-width="2" fill="red" ng-click="createFlightCtrl.setToFastRes(seat)" />
                    </svg>
                </div>
            </div>
            <p>Click on seat to set it as fast reservation</p>
        </div>
    </div>
  <button ng-click="createFlightCtrl.saveFlight()" class="btn btn-primary">Save</button>
</form>
</div>

<script>
    $(function () {
        $("#flightDepart, #flightLanding").datetimepicker({
            format: "yyyy-mm-dd hh:ii"
        });
    });
</script>