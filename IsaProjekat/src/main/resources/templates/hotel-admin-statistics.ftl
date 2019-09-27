<link rel="stylesheet" href="css/stats.css" />
<div class="container-fluid">
   <div class="">
   <div class="row">
      <div class="page-heading">
         <h1 style="color:black;">Dashboard</h1>
         <div class="options">
            <#--  <div class="btn-toolbar">
               <a href="#" class="btn btn-default"><i class="fa fa-fw fa-cog"></i></a>
            </div>  -->
         </div>
      </div>
      </div>
      <div class="row">
      <div class="col-md-3">
         <a class="info-tiles tiles-inverse has-footer">
            <div class="tiles-heading">
               <div class="pull-left">Average hotel rate</div>
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
               <div class="pull-left">Average rate</div>
               <div class="pull-right percent-change">Of hotel</div>
            </div>
         </a>
      </div>
      <div class="col-md-3">
         <a class="info-tiles tiles-blue has-footer">
            <div class="tiles-heading">
               <div class="pull-left">Accommodation attendance today</div>
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
               <div class="pull-left">Number of visitors</div>
               <div class="pull-right percent-change">today</div>
            </div>
         </a>
      </div>
      <div class="col-md-3">
         <a class="info-tiles tiles-blue has-footer">
            <div class="tiles-heading">
               <div class="pull-left">Accommodation attendance this week</div>
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
               <div class="pull-left">Number of visitors</div>
               <div class="pull-right percent-change">this week</div>
            </div>
         </a>
      </div>
      <div class="col-md-3">
         <a class="info-tiles tiles-blue has-footer">
            <div class="tiles-heading">
               <div class="pull-left">Accommodation attendance this month</div>
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
               <div class="pull-left">Number of visitors</div>
               <div class="pull-right percent-change">this month</div>
            </div>
         </a>
      </div>
      </div>
      <div class="row date" style="border-style=solid; border-width:5px; border-color: red;">
         <div class="container">
         <div class="depart">
            <h3>From</h3>
            <input id="datepicker" ng-model="avioStatsCtrl.grossObjTemp.fromGrossInterval" class="input-class" name="Text" type="text"
                        value="yyyy-MM-dd" onfocus="this.value = '';"
                        onblur="if (this.value == '') {this.value = 'yyyy-MM-dd';}" required="">
         </div>
         <div class="return">
            <h3>To</h3>
            <input id="datepicker1" ng-model="avioStatsCtrl.grossObjTemp.toGrossInterval" class="input-class" name="Text" type="text"
                        value="yyyy-MM-dd" onfocus="this.value = '';"
                        onblur="if (this.value == '') {this.value = 'yyyy-MM-dd';}" required="">
         </div>
         <button class="btn btn-danger" ng-click="avioStatsCtrl.getGrosForInterval()">Get gross for interval</button>
         <div class="">
         <a class="info-tiles tiles-green " ng-show = "avioStatsCtrl.grossCalculated">
            <div class="tiles-heading">
               <div class="pull-left">GROSS</div>
               <div class="pull-right">
                  <div id="tilemembers" class="sparkline-block">
                     <canvas width="39" height="13" style="display: inline-block; width: 39px; height: 13px; vertical-align: top;"></canvas>
                  </div>
               </div>
            </div>
            <div class="tiles-body">
               <div class="text-center">{{avioStatsCtrl.grossResult}} $</div>
            </div>
            <div class="tiles-footer">
               <div class="pull-left">Gross results </div>
               <div class="pull-right percent-change">for given interval</div>
            </div>
         </a>
      </div>
      </div>
      </div>
      <div class="row">
      <section class="features_table">
        <div class="container ">
        	<div class="col-sm-8 col-8 col-xs-12 no-padding">
            	<div class="features-table">
                	<ul>
                    	<h1>Room average rates</h1>
                    	<li ng-repeat="flight in avioStatsCtrl.stats.flights"><p>Room: <hr><br>Average rate for this flight:<span style="color:red; font-size: 30px;"> {{flight.avgRate}}</span></p></li>
                        
                      
                        
                        
                    </ul>
                </div>
            </div>
            <div class="top-margin-div "></div>
        </div>
    
    </section>
      </div>
   </div>
</div>

   
	<script>
		$(function () {
			$("#datepicker,#datepicker1").datepicker();
		});
	</script>