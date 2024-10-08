<%@ page import="java.util.List" %>
<%@ page import="com.frontend.frontend.wsdl.Company" %>
<%@ include file="/layout/topbar/topbar.jsp" %>
<%@ include file="/layout/sidebar/sidebar.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Companias</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/components/private/admin/companies/companiesList.css">
</head>
<body>

<div class="main-content">
    <div class="container">
        <h1>Lista de Compañías</h1>

        <div class="add-company-button">
            <a href="${pageContext.request.contextPath}/components/private/admin/companies/companiesAdd.jsp"
               class="btn btn-primary">Añadir Compañía</a>
        </div>

        <table class="companies-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Dirección</th>
                <th>Teléfono</th>
                <th>Email</th>
                <th>Actividad Económica</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Company> companies = (List<Company>) request.getAttribute("companies");
                if (companies != null && !companies.isEmpty()) {
                    for (Company company : companies) {
            %>
            <tr>
                <td><%= company.getID() %>
                </td>
                <td><%= company.getNAME() %>
                </td>
                <td><%= company.getADDRESS() %>
                </td>
                <td><%= company.getPHONE() %>
                </td>
                <td><%= company.getEMAIL() %>
                </td>
                <td><%= company.getECONOMICACTIVITY() %>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/companies?action=edit&id=<%= company.getID() %>"
                       class="btn btn-edit">Editar</a>

                    <form action="${pageContext.request.contextPath}/deleteCompany" method="post"
                          style="display:inline;">
                        <input type="hidden" name="id" value="<%= company.getID() %>"/>
                        <button type="submit" class="btn btn-delete"
                                onclick="return confirm('¿Estás seguro de que quieres eliminar esta compañía?');">
                            Eliminar
                        </button>
                    </form>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="7">No hay compañías registradas.</td>
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
