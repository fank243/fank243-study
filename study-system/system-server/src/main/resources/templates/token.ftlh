<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Oauth2 Test</title>
</head>
<body>
<div>
    <h1>Oauth2 Token</h1>
    <div>
        <form method="POST" action="http://127.0.0.1:8901/oauth2/token">
            <p>grant_type
                <input type="text" name="grant_type" value="${grant_type}">
            </p>
            <p>code
                <input type="text" name="code" value="${code}">
            </p>
            <p>client_id
                <input type="text" name="client_id" value="${client_id}">
            </p>
            <p>client_secret
                <input type="text" name="client_secret" value="${client_secret}">
            </p>
            <p>redirect_uri
                <input type="text" name="redirect_uri" value="${redirect_uri}">
            </p>
            <p>
                <input type="submit" value="提交">
            </p>
        </form>
    </div>
</div>
</body>
</html>