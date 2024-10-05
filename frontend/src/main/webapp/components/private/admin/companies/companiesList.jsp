<%@ page import="com.frontend.frontend.wsdl.CompanyDto" %>
<%@ page import="com.frontend.frontend.models.Company" %>
<%@ page import="java.util.List" %>
<%@ include file="/layout/topbar/topbar.jsp" %>
<%@ include file="/layout/sidebar/sidebar.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Companias</title>
    <link rel="stylesheet" href="companiesList.css">
</head>
<body>

<div class="main-content">
    <div class="container">
        <h1>Lista de Compañías</h1>

        <table class="companies-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Dirección</th>
                <th>Teléfono</th>
                <th>Email</th>
                <th>Actividad Económica</th>
                <th>Creado Por</th>
                <th>Actualizado Por</th>
                <th>Fecha de Creación</th>
                <th>Fecha de Actualización</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Company> companies = (List<Company>) request.getAttribute("companies");
                if (companies != null && !companies.isEmpty()) {
                    for (Company company : companies) {
            %>
            <tr>
                <td><%= company.getId() %></td>
                <td><%= company.getName() %></td>
                <td><%= company.getAddress() %></td>
                <td><%= company.getPhone() %></td>
                <td><%= company.getEmail() %></td>
                <td><%= company.getEconomicActivity() %></td>
                <td><%= company.getCreatedBy() %></td>
                <td><%= company.getUpdatedBy() %></td>
                <td><%= company.getCreatedAt() %></td>
                <td><%= company.getUpdatedAt() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="10">No hay compañías registradas.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
