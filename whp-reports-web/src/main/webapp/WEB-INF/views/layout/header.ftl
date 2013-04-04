<#macro header title="WHP-Header" path="">
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<div id="links" class="navbar navbar-fixed-top">
    <div id="navbarInner" class="navbar-inner">
        <a href="/whp" class="brand pull-left">MoTeCH-WHP</a>

        <#if path != "">
            <#include path>
        </#if>

        <#if Session.loggedInUser?exists>
            <ul class="nav pull-right">
                <li class="dropdown pull-right">
                        <a>Welcome ${Session.loggedInUser.userName}</a>
                </li>
                <li><a id="logout" href="<@spring.url '/security/j_spring_security_logout' />"><i
                        class="icon-off icon-white"></i> Logout</a></li>
            </ul>

        </#if>

    </div>
    <#if Session.loggedInUser?exists>

    </#if>
</div>
</#macro>

