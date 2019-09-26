<link rel="stylesheet" href="css/avio-company-profile.css" />
<br><br>
<div class="container ">
    <div class="row">
        <div class="col-3 float-left " role="navigation">
            <nav class="nav flex-column">
                <a class="nav-link active" ui-sref="home-abstract.avio-admin-avio-profile.avio-admin()">
                <i class="fas fa-list-ul"></i> Overview
                </a>
                <a  class="nav-link" ui-sref="home-abstract.avio-admin-avio-profile.admin-flights()">
                <i class="fas fa-route"></i> Flights  
                </a>
                
                <a class="nav-link" ui-sref="home-abstract.avio-admin-avio-profile.avio-statistics()">
                <i class="fas fa-swatchbook"></i> Statistics
                </a>
            </nav>
        </div>
        <div class="col-9 float-left">
            <div ui-view='avio-admin'></div>
            <div ui-view='admin-flights'></div>  
            <div ui-view='avio-statistics'></div>
            
        </div>
    </div>
</div>