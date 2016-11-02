<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'message.label', default: 'Message')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    %{--<g:javascript library="jquery" />--}%
    %{--<g:javascript library="prototype"/>--}%

</head>

<body>
<a href="#list-message" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-message" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
%{--<f:table collection="${messageList}" />--}%

    <div class="body">
        <h1>${event?.name} - Forum Messages</h1>

        <div id="messageList">
            %{--<f:table collection="messageInstanceList" properties="['subject']"/>--}%
            %{--<f:table collection="messageInstanceList" properties="['subject']"/>--}%
            %{--<g:messageThread messages="${messageInstanceList}" />--}%
            <g:each in="${messageInstanceList}" var="messageInstance">

%{--//tag [remoteLink] does not exist. No tag library found for namespace: g--}%
            %{--<g:remoteLink action="showDetail" id="${messageInstance?.id}" > ${messageInstance?.subject}</g:remoteLink>--}%
                <g:link controller="message" action="showDetail"
                        id="${messageInstance?.id}">${messageInstance?.subject}</g:link><br>
            %{--<g:remoteLink action="index" id="${messageInstance?.id}"--}%
            %{--update="details" >--}%
            %{--${messageInstance?.subject}--}%
            %{--</g:remoteLink>--}%
            %{----}%
            %{--<g:link action="showDetail" controller="message" >--}%
            %{--${messageInstance?.subject}--}%
            %{--</g:link>--}%

            %{--<h1> ${messageInstance?.id}</h1>--}%

            %{--<h1> ${messageInstance?.author.fullName}</h1>--}%
            </g:each>
        </div>

        <h3>Message Details</h3>

        <div id="details">
        </div>
    </div>

    <div class="pagination">
        <g:paginate total="${messageCount ?: 0}"/>
    </div>
</div>
</body>
</html>