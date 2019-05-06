<!-- Include Bootstrap Datepicker -->
<link rel="stylesheet" href="css/datetimepicker.css" />
<script src="js\lib\bootstrap-datetimepicker.js"></script>

<div style="padding-left:50px">
<form name="createFlightForm">
  <div class="form-row">
    <div class="form-group col-md-3">
      <h3>From</h3>
      <ui-select class="city1 input-class" ng-model="createFlightCtrl.from"
            theme="selectize" style="width: 300px;"
            title="From">
            <ui-select-match placeholder="Select or search a location in the list...">
                {{$select.selected.name}}</ui-select-match>
            <ui-select-choices
                repeat="location in createFlightCtrl.destinations | filter: $select.search">
                <span ng-bind-html="location.name | highlight: $select.search"></span>
            </ui-select-choices>
        </ui-select>
    </div>
    <div class="form-group col-md-3">
      <h3>To</h3>
      <ui-select class="city1 input-class" ng-model="createFlightCtrl.to"
            theme="selectize" style="width: 300px;"
            title="From">
            <ui-select-match placeholder="Select or search a location in the list...">
                {{$select.selected.name}}</ui-select-match>
            <ui-select-choices
                repeat="location in createFlightCtrl.destinations | filter: $select.search">
                <span ng-bind-html="location.name | highlight: $select.search"></span>
            </ui-select-choices>
        </ui-select>
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-3">
        <h3>Depart</h3>
        <input id="flightDepart" ng-model="createFlightCtrl.depart" />
    </div>
    <div class="form-group col-md-3">
        <h3>Landing</h3>
        <input id="flightLanding" ng-model="createFlightCtrl.landing" />
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-3">
      <label for="baggage_over7">Price over 7kg(baggage):</label>
      <input type="text" class="form-control" ng-model="createFlightCtrl.bggOver7" id="baggage_over7">
    </div>
    <div class="form-group col-md-3">
      <label for="baggage_over14">Price over 14kg(baggage):</label>
      <input type="text" class="form-control" ng-model="createFlightCtrl.bggOver14" id="baggage_over14">
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-3">
      <label for="economicPrice">Economy class price:</label>
      <input type="text" class="form-control" ng-model="createFlightCtrl.economicPrice" id="economicPrice">
    </div>
    <div class="form-group col-md-3">
      <label for="businessPrice">Business class price:</label>
      <input type="text" class="form-control" ng-model="createFlightCtrl.businessPrice" id="businessPrice">
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-3">
        <label for="inputState">Configuration type:</label>
        <select id="inputState" ng-model="createFlightCtrl.selectedType"
            class="form-control" ng-change="createFlightCtrl.setSelectedConfig()">
            <option value="smalljet" selected>Small Jet</option>
            <option value="mediumjet">Medium Jet</option>
            <option value="airbus">Airbus</option>
            <option value="jumbojet">Jumbo Jet</option>
        </select>
    </div>
    <div class="form-group col-md-3">
        <label for="numOfRows">Number of rows:</label>
        <input type="number" ng-model="createFlightCtrl.seatRowNum" 
            ng-change="createFlightCtrl.evaluateChange()" min="0" max="15" 
            class="form-control" id="numOfRows" name="numOfRows">
        <#--  <div ng-show="createFlightForm.email.$touched && (createFlightForm.email.$error.minlength || registerForm.email.$error.maxlength)">
            <small style="color:red;display:block;text-align:center;">Email must have anywhere between 3 and 128 characters!</small>
        </div>
        <div ng-show="createFlightForm.email.$touched && createFlightForm.email.$error.required">
            <small style="color:red;display:block;text-align:center;">Required!</small>
        </div>
        <div ng-show="createFlightForm.email.$touched && createFlightForm.email.$error.pattern">
            <small style="color:red;display:block;text-align:center;">Invalid characters detected!</small>
        </div>  -->
    </div>
  </div>
    <#--  <div ng-repeat="x in [].constructor(createFlightCtrl.numOfBussinessSeats) track by $index">
         <div class="row">
            <div ng-repeat="y in [].constructor(createFlightCtrl.seatColNum) track by $index">
                <svg width="70" height="70">
                    <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                    stroke="orange" class="business"
                     stroke-width="2" fill="blue" ng-click="createFlightCtrl.setToFastRes(this)" />
                </svg>
            </div>
        </div>
    </div>
    <div ng-repeat="x in [].constructor(createFlightCtrl.numOfEconomicSeats) track by $index">
         <div class="row">
            <div ng-repeat="y in [].constructor(createFlightCtrl.seatColNum) track by $index">
                <svg width="70" height="70">
                    <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                    stroke="blue" class="economic"
                     stroke-width="2" fill="orange" ng-click="createFlightCtrl.setToFastRes(this)" />
                </svg>
            </div>
        </div>
    </div>  -->
    <p>Click on seat to set it as fast reservation</p>
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