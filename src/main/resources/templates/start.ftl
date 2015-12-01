<!DOCTYPE HTML>
<html>
<head>
    <title>Welcome!</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" media="all" href="css/main.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="/js/sockjs.min.js"></script>
    <script src="/js/stomp.min.js"></script>
    <script src="/js/start/start.js"></script>
</head>
<body>

<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div>
    <div>
        <p>Пользоватали в сессии: <span id="userCounter"></span></p>
        <button id="start" onclick="sendStart();">Start</button>
    </div>
</div>
</body>
<script>
    $(function () {
        connect();
    });
</script>
</html>