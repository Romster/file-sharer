<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" media="all" href="css/main.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="/js/main.js"></script>
</head>
<body>
<form id="upload" action="/share" method="POST" enctype="multipart/form-data">

    <fieldset>
        <legend>HTML File Upload</legend>

        <input type="hidden" id="MAX_FILE_SIZE" name="MAX_FILE_SIZE" value="300000"/>

        <div>
            <label for="fileselect">Files to upload:</label>
            <input type="file" id="fileselect" name="files" multiple="multiple"/>
            <input type="text" name="session_id" value="${RequestParameters.id}" style="display: none"/>

            <div id="filedrag">or drop files here</div>
        </div>

        <div id="submitbutton">
            <button type="submit">Upload Files</button>
        </div>

    </fieldset>

</form>


<div id="messages">
    <p>Status Messages</p>
</div>

<div id="files">
    <p>Files:</p>
    <ul>
    <#list sharedFiles as file>
        <li>
            <a href="${file.downloadUrl}" download="${file.fileName}">${file.fileName}</a>
        </li>
    </#list>
    </ul>
</div>

<script>
    // call initialization file
    if (window.File && window.FileList && window.FileReader) {
        Init();
    }
</script>
</body>
</html>