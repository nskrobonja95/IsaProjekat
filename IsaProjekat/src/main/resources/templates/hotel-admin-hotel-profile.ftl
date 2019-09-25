<br><br>
<div class="container ">
    <div class="row">
        <div class="col-3 float-left " role="navigation">
            <nav class="nav flex-column">
                <a class="nav-link active" ui-sref="home-abstract.hotel-admin-hotel-profile.hotel-admin()">
                <i class="fas fa-list-ul"></i> Overview
                </a>
                <a  class="nav-link" ui-sref="home-abstract.hotel-admin-hotel-profile.admin-rooms-list()">
                <i class="fas fa-bed"></i> Rooms
                </a>
                
                <a class="nav-link" ui-sref="home-abstract.hotel-admin-hotel-profile.hotel-admin-statistics()">
                <i class="fas fa-swatchbook"></i> Statistics
                </a>
            </nav>
        </div>
        <div class="col-9 float-left">
            <div ui-view='hotel-admin'></div>
            <div ui-view='admin-rooms-list'></div>  
            <div ui-view='hotel-admin-statistics'></div>
            
        </div>
    </div>
</div>