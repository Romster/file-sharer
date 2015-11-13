var stompClient = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connect() {
    var socket = new SockJS('/start');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/' + getURLParameter("id"), function (greeting) {
            alert("OLA!");
            showGreeting(JSON.parse(greeting.body).content);
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
    stompClient.send("/app/start/"+getURLParameter("id"), {}, JSON.stringify({'type': 'BEGIN'}));
}

function sendNewUser() {

}

function showGreeting(message) {
    stompClient.send("/app/start/"+getURLParameter("id"), {}, JSON.stringify({'type': 'NEW_USER'}));
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