<br><br>
{{}}
<div class="container bootstrap snippet">
<div class="panel-body inf-content">
    <div class="row">
        <div class="col-md-4">
            <img alt="" style="width:600px;" title="" class="img-circle img-thumbnail isTooltip" src="https://bootdey.com/img/Content/user-453533-fdadfd.png" data-original-title="Usuario"> 
            <ul title="Ratings" class="list-inline ratings text-center">
                <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
            </ul>
        </div>
        <div class="col-md-6">
            <strong class="labele">Information</strong><br>
            <div class="table-responsive">
            <table class="table table-condensed table-responsive table-user-information">
                <tbody>
                    <tr>    
                        <td>
                            <strong class="labele">
                                <i class="fas fa-user-circle" style="color:#009CDA"></i>
                                Name                                                
                            </strong>
                        </td>
                        <td class="labele">
                            {{pOCtrl.userData.name}}     
                        </td>
                    </tr>
                    <tr>        
                        <td>
                            <strong class="labele">
                                <i class="fas fa-user-circle" style="color:#009CDA"></i>
                                Lastname                                                
                            </strong>
                        </td>
                        <td class="labele">
                            {{pOCtrl.userData.lastname}}  
                        </td>
                    </tr>

                    <tr>        
                        <td>
                            <strong class="labele">
                                <i class="fas fa-bookmark" style="color:#009CDA"></i> 
                                Username                                                
                            </strong>
                        </td>
                        <td class="labele">
                            {{pOCtrl.userData.username}} 
                        </td>
                    </tr>


                    <tr>        
                        <td>
                            <strong class="labele">
                                <i class="fas fa-eye" style="color:#009CDA"></i>
                                Role                                                
                            </strong>
                        </td>
                        <td class="labele" ng-if="pOCtrl.userData.role== 'User' ">
                            Basic user
                        </td>
                        <td class="labele" ng-if="pOCtrl.userData.role== 'SysAdmin' ">
                            System administrator
                        </td>
                        <td class="labele" ng-if="pOCtrl.userData.role=='AvioAdmin' ">
                            AvioCompany administrator
                        </td>
                        <td class="labele" ng-if="pOCtrl.userData.role=='HotelAdmin' ">
                            Hotel administrator
                        </td>
                        <td class="labele" ng-if="pOCtrl.userData.role=='RentACarAdmin' ">
                           RentACarCompany administrator
                        </td>
                    </tr>
                    <tr>        
                        <td>
                            <strong class="labele">
                                <i class="fas fa-envelope" style="color:#009CDA"></i>
                                Email                                                
                            </strong>
                        </td>
                        <td class="labele">
                            {{pOCtrl.userData.email}}  
                        </td>
                    </tr>
                    <tr>        
                        <td>
                            <strong class="labele">
                                <i class="fas fa-map-marker" style="color:#009CDA"></i>
                                City                                                
                            </strong>
                        </td>
                        <td class="labele">
                            {{pOCtrl.userData.city}}
                        </td>
                    </tr>
                    <tr>        
                        <td>
                            <strong class="labele">
                                <i class="fas fa-phone" style="color:#009CDA"></i>
                                Phone                                                
                            </strong>
                        </td>
                        <td class="labele">
                             {{pOCtrl.userData.phoneNumber}}
                        </td>
                    </tr>                                    
                </tbody>
            </table>
            </div>
        </div>
    </div>
</div>