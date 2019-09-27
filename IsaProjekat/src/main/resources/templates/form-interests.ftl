<label>Select seats for direct flight</label>
<div class="form-group">
    <div ng-repeat="seatRow in dir_seats" class="row">
        <div ng-repeat="seat in seatRow.seats">
            <svg width="70" height="70" ng-show="seat.flightClass == 'Business' && seat.available">
                <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                stroke="orange" class="business"
                    stroke-width="2" fill="blue" ng-click="selectSeatForDirectFlight(seat)" />
            </svg>
            <svg width="70" height="70" ng-show="seat.flightClass == 'Economic' && seat.available">
                <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                stroke="blue" class="economic"
                    stroke-width="2" fill="orange" ng-click="selectSeatForDirectFlight(seat)" />
            </svg>
            <svg width="70" height="70" ng-show="!seat.available && !seat.reserved">
                <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                stroke="black" class="economic"
                    stroke-width="2" fill="black" ng-click="selectSeatForDirectFlight(seat)" />
            </svg>
            <svg width="70" height="70" ng-show="seat.reserved">
                <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                stroke="black" class="economic"
                    stroke-width="2" fill="black" ng-click="selectSeatForDirectFlight(seat)" />
            </svg>
        </div>
    </div>

    <br><br>
    <label>Select seats for return flight</label>
    <div ng-repeat="seatRow in ret_seats" class="row">
        <div ng-repeat="seat in seatRow.seats">
            <svg width="70" height="70" ng-show="seat.flightClass == 'Business' && seat.available">
                <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                stroke="orange" class="business"
                    stroke-width="2" fill="blue" ng-click="selectSeatForReturnFlight(seat)" />
            </svg>
            <svg width="70" height="70" ng-show="seat.flightClass == 'Economic' && seat.available">
                <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                stroke="blue" class="economic"
                    stroke-width="2" fill="orange" ng-click="selectSeatForReturnFlight(seat)" />
            </svg>
            <svg width="70" height="70" ng-show="!seat.available && !seat.reserved">
                <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                stroke="black" class="economic"
                    stroke-width="2" fill="black" ng-click="selectSeatForReturnFlight(seat)" />
            </svg>
            <svg width="70" height="70" ng-show="seat.reserved">
                <circle ng-attr-cx="50" ng-attr-cy="50" ng-attr-r="20" 
                stroke="green" class="economic"
                    stroke-width="2" fill="green" ng-click="selectSeatForReturnFlight(seat)" />
            </svg>
        </div>
    </div>
</div>

<div class="form-group row">
<div class="col-xs-12" class="btnbox-center">
    <a ui-sref="home-abstract.flight-reservation.profile" class="btn btn-right btn-info">
    Next <span class="glyphicon glyphicon-circle-arrow-right"></span>
    </a>
    <#--  <button class="btn btn-right btn-info" ng-click="">
        Next
    </button>  -->
</div>
</div>