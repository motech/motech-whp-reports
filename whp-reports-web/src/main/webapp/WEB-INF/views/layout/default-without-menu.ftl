<#import "/spring.ftl" as spring />
<#import "header.ftl" as header/>
<#macro defaultLayout title="WHP">
<!DOCTYPE html>
<html>
<head>
    <title> ${title} </title>
    <#include "scripts.ftl"/>
</head>
<body class="login-page">

    <div id="bodyContent" class="container">
        <div class="row-fluid" id="mainContent">
            <noscript>
                <div class="row alert alert-error  javascript-warning">Please enable Java Script in your browser for the application to work properly. Please contact WHP administrator if you need assistance</div>
            </noscript>
            <#nested/>
        </div>
    </div>

    <div class="row-fluid" id="footerContent">
        <#include "footer.ftl"/>
    </div>

</body>
</html>
</#macro>