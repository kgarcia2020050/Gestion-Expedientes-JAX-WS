<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/layout/sidebar/sidebar.css">

<div class="sidebar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/components/public/home/home.jsp"
               class="sidebar-link">Dashboard</a></li>
        <% if ("ADMIN".equals(session.getAttribute("role"))) { %>
        <li>
            <form action="${pageContext.request.contextPath}/companies" method="post">
                <input type="hidden" name="action" value="get">
                <button type="submit" class="sidebar-button">Companias</button>
            </form>


        </li>
        <% } %>
        <li><a href="${pageContext.request.contextPath}/components/public/auth/login.jsp" class="sidebar-link">Cerrar
            sesión</a></li>
    </ul>
</div>
