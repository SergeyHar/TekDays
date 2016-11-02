<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tekUser.label', default: 'TekUser')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-tekUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>

                <li><g:link class="login" action="login"><g:message code="default.login.label" default="Login" /></g:link></li>
                <li><g:link class="logout" action="logout"><g:message code="default.logout.label" default="Logout" /></g:link></li>
            </ul>
        </div>
        <div id="list-tekUser" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${tekUserList}" />

            <div class="pagination">
                <g:paginate total="${tekUserCount ?: 0}" />
            </div>
        </div>
    </body>
</html>