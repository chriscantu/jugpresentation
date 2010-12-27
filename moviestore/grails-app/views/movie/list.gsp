
<%@ page import="com.rackspace.Movie" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'movie.label', default: 'Movie')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'movie.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="title" title="${message(code: 'movie.title.label', default: 'Title')}" />
                        
                            <g:sortableColumn property="rating" title="${message(code: 'movie.rating.label', default: 'Rating')}" />
                        
                            <g:sortableColumn property="year" title="${message(code: 'movie.year.label', default: 'Year')}" />
                        
                            <th><g:message code="movie.genre.label" default="Genre" /></th>
                        
                            <th><g:message code="movie.media.label" default="Media" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${movieInstanceList}" status="i" var="movieInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${movieInstance.id}">${movieInstance.id}</g:link></td>
                        
                            <td>${movieInstance.title}</td>
                        
                            <td>${movieInstance.rating}</td>
                        
                            <td>${movieInstance.year}</td>
                        
                            <td>${movieInstance.genre.type}</td>
                        
                            <td>${movieInstance.media.type}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${movieInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
