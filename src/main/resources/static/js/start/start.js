var stompClient = null;

function setConnected(connected) {

}

function connect() {
    var socket = new SockJS('/start');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        var topic = '/start/' + getURLParameter("id");
        stompClient.subscribe(topic, function (message) {
            var resp = JSON.parse(message.body);
            var userCount = resp.userCount;
            var redirectUrl = resp.redirectUrl;
            if (redirectUrl != null) {
                window.window.location.replace(redirectUrl);
            } else {
                var counter = $('#userCounter');
                counter.empty();
                counter.append(userCount);
            }
        });
    });

}

function disconnect() {
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var name = document.getElementById('name').value;
    stompClient.send("/app/start/" + getURLParameter("id"), {}, JSON.stringify({'type': 'BEGIN'}));
}


function sendStart() {
    stompClient.send("/app/start/" + getURLParameter("id"), {}, JSON.stringify({'type': 'START'}));
}

function sendNewUser() {

}

function showGreeting(message) {
    stompClient.send("/app/start/" + getURLParameter("id"), {}, JSON.stringify({'type': 'NEW_USER'}));
}


function getURLParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}