<!-- Include Bootstrap Datepicker -->
<link rel="stylesheet" href="css/datetimepicker.css" />
<script src="js\lib\bootstrap-datetimepicker.js"></script>

<div>
<form>
  <div class="form-row">
    <div class="form-group col-md-3">
      <h3>From</h3>
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
    <div class="form-group col-md-3">
      <h3>To</h3>
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
  </div>
  <div class="form-row">
    <div class="form-group col-md-3">
        <h3>Depart</h3>
        <input id="flightDepart" class="input-class" />
    </div>
    <div class="form-group col-md-3">
        <h3>Landing</h3>
        <input id="flightLanding" class="input-class" />
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-3">
      <label for="baggage_over7">Over 7kg(baggage):</label>
      <input type="text" class="form-control" id="baggage_over7">
    </div>
    <div class="form-group col-md-3">
      <label for="baggage_over14">Over 14kg(baggage):</label>
      <input type="text" class="form-control" id="baggage_over14">
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-3">
      <label for="economicPrice">Economy class price:</label>
      <input type="text" class="form-control" id="economicPrice">
    </div>
    <div class="form-group col-md-3">
      <label for="businessPrice">Business class price:</label>
      <input type="text" class="form-control" id="businessPrice">
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-3">
            <label for="inputState">Configuration type:</label>
            <select id="inputState" class="form-control">
                <option selected>Small Jet</option>
                <option>Medium Jet</option>
                <option>Airbus</option>
                <option>Jumbo Jet</option>
            </select>
    </div>
    <div class="form-group col-md-3">
        <label for="numOfRows">Number of rows:</label>
        <input type="text" class="form-control" id="numOfRows">
    </div>
  </div>
    <div ng-repeat="x in [].constructor(createFlightCtrl.seatRowNum) track by $index">
        <div class="row">
            <div ng-repeat="y in [].constructor(createFlightCtrl.seatColNum) track by $index">
                <div class="seatCircle rounded-circle col-sm-2"></div>
            </div>
        </div>
    </div>
  <button type="submit" class="btn btn-primary">Sign in</button>
</form>
</div>

<script>
    $(function () {
        $("#flightDepart, #flightLanding").datetimepicker({
            format: "yyyy-mm-dd hh:ii"
        });
    });
</script>