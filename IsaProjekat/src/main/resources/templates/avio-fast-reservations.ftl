<div>FAST RESERVATIONS</div>
<section class="search-box list-box-div">
    <div class="container-fluid " style="border:none !important; border-style:none;">
        <div class="row" style="border:none !important; border-style:none;">
            <div class="col-md-12 listing-block" style="float: right; border:none !important; border-style:none;">
                <div class="media" ng-repeat="ticket in afrCtrl.fastReservationSeats"
                    style="border:none !important; border-style:none;">
                    
                    <div class="fav-box"><i class="fa fa-heart-o" aria-hidden="true"></i>
                    </div>
                    
                    <div class="media-body pl-3" style="text-align: left; padding:10px;">
                            <a class="price" ui-sref="home-abstract.avio-company({companyId:avioCompany.id})">
                                {{ticket.flight.avioCompany.name}}
                            </a>
                            <small>
                                {{ticket.flight.from.name}} {{ticket.flight.takeoff}} 
                                <i class="fas fa-arrow-right"></i> 
                                {{ticket.flight.toDest.name}} {{ticket.flight.landing}}
                            </small>
                        <hr>
                        <div ng-show="ticket.flightClass == 'Economic'" class="address">Original price: {{ticket.flight.economicClassPrice}}$</div>
                        <div ng-show="ticket.flightClass == 'Business'" class="address">Original price: {{ticket.flight.bussinessClassPrice}}$</div>
                        <div class="address">Discount: {{ticket.flight.discount}}%</div>
                    </div>
                    <div style="margin: auto; height:100%" >
                        <button ng-show="!ticket.reserved" ng-click="afrCtrl.fastReserve(ticket)">Reserve</button>
                        <label ng-show="ticket.reserved">Reserved</label>
                    </div>
            
                    <#--  <img class="d-flex align-self-start" style="border-radius:25px 0px 0px 25px;"
                        src="images/airplane-company.jpg" alt="Generic placeholder image">  -->
                </div>

            </div>
        </div>
</section>