<link rel="stylesheet" href="css/stats.css" />
<div class="container-fluid">
   <div class="">
   <div class="row">
      <div class="page-heading">
         <h1 style="color:black;">Dashboard</h1>
         <div class="options">
            <div class="btn-toolbar">
               <a href="#" class="btn btn-default"><i class="fa fa-fw fa-cog"></i></a>
            </div>
         </div>
      </div>
      </div>
      <div class="row">
      <div class="col-md-3">
         <a class="info-tiles tiles-inverse has-footer" href="#">
            <div class="tiles-heading">
               <div class="pull-left">Average avio company rate</div>
               <div class="pull-right">
                  <div id="tileorders" class="sparkline-block">
                     <canvas width="39" height="13" style="display: inline-block; width: 39px; height: 13px; vertical-align: top;"></canvas>
                  </div>
               </div>
            </div>
            <div class="tiles-body">
               <div class="text-center">{{avioStatsCtrl.stats.avgAvioRate}}</div>
            </div>
            <div class="tiles-footer">
               <div class="pull-left">manage orders</div>
               <div class="pull-right percent-change">+20.7%</div>
            </div>
         </a>
      </div>
      <div class="col-md-3">
         <a class="info-tiles tiles-green has-footer" href="#">
            <div class="tiles-heading">
               <div class="pull-left">Tickets sold today</div>
               <div class="pull-right">
                  <div id="tilerevenues" class="sparkline-block">
                     <canvas width="40" height="13" style="display: inline-block; width: 40px; height: 13px; vertical-align: top;"></canvas>
                  </div>
               </div>
            </div>
            <div class="tiles-body">
               <div class="text-center">{{avioStatsCtrl.stats.dailySoldTickets}}</div>
            </div>
            <div class="tiles-footer">
               <div class="pull-left">go to accounts</div>
               <div class="pull-right percent-change">+17.2%</div>
            </div>
         </a>
      </div>
      <div class="col-md-3">
         <a class="info-tiles tiles-blue has-footer" href="#">
            <div class="tiles-heading">
               <div class="pull-left">Tickets sold this week</div>
               <div class="pull-right">
                  <div id="tiletickets" class="sparkline-block">
                     <canvas width="13" height="13" style="display: inline-block; width: 13px; height: 13px; vertical-align: top;"></canvas>
                  </div>
               </div>
            </div>
            <div class="tiles-body">
               <div class="text-center">{{avioStatsCtrl.stats.weeklySoldTickets}}</div>
            </div>
            <div class="tiles-footer">
               <div class="pull-left">see all tickets</div>
               <div class="pull-right percent-change">+10.3%</div>
            </div>
         </a>
      </div>
      <div class="col-md-3">
         <a class="info-tiles tiles-midnightblue has-footer" href="#">
            <div class="tiles-heading">
               <div class="pull-left">Tickets sold this month</div>
               <div class="pull-right">
                  <div id="tilemembers" class="sparkline-block">
                     <canvas width="39" height="13" style="display: inline-block; width: 39px; height: 13px; vertical-align: top;"></canvas>
                  </div>
               </div>
            </div>
            <div class="tiles-body">
               <div class="text-center">{{avioStatsCtrl.stats.monthlySoldTickets}}</div>
            </div>
            <div class="tiles-footer">
               <div class="pull-left">manage members</div>
               <div class="pull-right percent-change">-11.1%</div>
            </div>
         </a>
      </div>
      </div>
      <div class="row">
      <section class="features_table">
        <div class="container ">
        	<div class="col-sm-8 col-8 col-xs-12 no-padding">
            	<div class="features-table">
                	<ul>
                    	<h1>Flights average rates</h1>
                    	<li ng-repeat="flight in avioStatsCtrl.stats.flights"><p>Flight from {{flight.from}} to {{flight.to}} departing {{flight.depart}}:<hr><br>Average rate for this flight:<span style="color:red; font-size: 30px;"> {{flight.avgRate}}</span></p></li>
                        
                      
                        
                        
                    </ul>
                </div>
            </div>
            <div class="top-margin-div "></div>
        </div>
    
    </section>
      </div>
   </div>
</div>

   
