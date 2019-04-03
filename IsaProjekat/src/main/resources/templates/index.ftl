<!DOCTYPE html>

<html lang="en" ng-app="flightApp">

<head>
    <meta http-equiv="expires" content="0">
    <title>${title}</title>
    <link rel="shortcut icon" href="images/favicon.png" />
    <link href="css/app.css" rel="stylesheet" />
    <link href="css/toolbar.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
        integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.8/angular-material.min.css">
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css" />

    <link rel="stylesheet" href="css/jquery-ui.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/simplebar/3.1.3/simplebar.css">
    <link
        href='//fonts.googleapis.com/css?family=Open+Sans:400,300italic,300,400italic,600,600italic,700,700italic,800,800italic'
        rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-select/0.20.0/select.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.8.5/css/selectize.default.css">

    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
</head>

<body>

    <div ui-view></div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.0.1/jquery-migrate.min.js"> </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/simplebar/3.1.3/simplebar.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.6/angular.min.js"></script>
    <script src="//unpkg.com/@uirouter/angularjs/release/angular-ui-router.min.js"></script>
    <script src="https://code.angularjs.org/1.6.9/angular-cookies.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
        integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-animate.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-aria.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-messages.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.8/angular-material.min.js"></script>
    <script src="js/app/modules/app.js"></script>
    <script src="js\lib\jquery-ui.js"></script>
    <script src="js\lib\easyResponsiveTabs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
    <script src="https://cdn.rawgit.com/BioPhoton/angular1-star-rating/v1.2.4/dist/index.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-sanitize/1.7.8/angular-sanitize.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-select/0.20.0/select.js"> </script>

    <!-- Directives -->
    <script src="js/app/directives/starRating.directive.js"> </script>

    <!-- Services -->
    <script src="js/app/services/signup.service.js"> </script>
    <script src="js/app/services/login.service.js"> </script>
    <script src="js/app/services/user.service.js"> </script>
    <script src="js/app/services/friend.service.js"> </script>
    <script src="js/app/services/avio.service.js"> </script>
    <script src="js/app/services/hotel.service.js"> </script>
    <script src="js/app/services/carHire.service.js"> </script>
    <script src="js/app/services/search.service.js"> </script>
    
    <!-- Controllers -->
    <script src="js/app/controllers/HomeController.js"></script>
    <script src="js/app/controllers/ProfileOverviewController.js"></script>
    <script src="js/app/controllers/FriendController.js"></script>
    <script src="js/app/controllers/FriendsListController.js"></script>
    <script src="js/app/controllers/FriendsSearchController.js"></script>
    <script src="js/app/controllers/GeneralSettingsController.js"></script>
    <script src="js/app/controllers/AvioController.js"></script>
    <script src="js/app/controllers/HotelController.js"></script>
    <script src="js/app/controllers/HotelProfileController.js"></script>
    <script src="js/app/controllers/AvioCompanyController.js"></script>
    <script src="js/app/controllers/CarHireController.js"></script>
    <script src="js/app/controllers/HotelProfileController.js"></script>


    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
        integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous">
    </script>
</body>

</html>