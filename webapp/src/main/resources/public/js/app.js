function justLogIn() {
    var url = '/login';
    if ($('#username').val() != '' || $('#password').val() != '') {
        url = url + '/' + $('#username').val();
        url = url + '/' + $('#password').val();
    }

    $.get(url,
        function (isRight) {
            if (!isRight) {
                $("#usernameIco").css('color', 'darkred');
                $("#passwordIco").css('color', 'darkred');
            }
            else
                $('#loginForm').submit();
        })
}


function geocodeAddress(geocoder, resultsMap) {
    var address = document.getElementById('address').innerHTML;
    geocoder.geocode({'address': address}, function (results, status) {
        if (status === google.maps.GeocoderStatus.OK) {
            resultsMap.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: resultsMap,
                position: results[0].geometry.location
            });
        }
    });
}

var initialized = false;

function displayMap() {
    if (!initialized)
        initialize();
    initialized = true;
}

function initialize() {
    var myOptions = {
        zoom: 14,
        center: new google.maps.LatLng(0.0, 0.0),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("map"),
        myOptions);
    var geocoder = new google.maps.Geocoder();
    geocodeAddress(geocoder, map);
}
  
