<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/layout/sidebar/sidebar.css">

<div class="sidebar">
    <ul>
        <li><a href="home.jsp" class="sidebar-link">Dashboard</a></li>
        <% if ("ADMIN".equals(session.getAttribute("role"))) { %>
        <li><a href="/components/private/admin/departments/department.jsp" class="sidebar-link">Departamentos</a></li>
        <% } %>
        <li><a href="/components/public/auth/login.jsp" class="sidebar-link">Cerrar sesión</a></li>
    </ul>
</div>
