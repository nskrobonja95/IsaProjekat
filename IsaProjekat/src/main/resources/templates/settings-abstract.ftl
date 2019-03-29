<div class="container">
    <div class="row">
        <div class="col-3 float-left " role="navigation">
            <nav class="nav flex-column">
                <a class="nav-link active" ui-sref="home-abstract.settings-abstract.general-settings()">
                <i class="fas fa-cogs"></i> General
                </a>
                <a class="nav-link" ui-sref="guest-abstract.settings-abstract.security-settings()">
                <i class="fas fa-lock"></i> Security and Login
                </a>
            </nav>
        </div>
        <div class="col-9 float-left">
            <div ui-view='general-settings'></div>
            <div ui-view='security-settings'></div>
            
        </div>
    </div>
</div>
