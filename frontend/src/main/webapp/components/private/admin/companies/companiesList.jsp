<%@ page import="java.util.List" %>
<%@ page import="com.frontend.frontend.wsdl.Company" %>
<%@ include file="/layout/topbar/topbar.jsp" %>
<%@ include file="/layout/sidebar/sidebar.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String errorMessage = "";
    String successMessage = "";
    if (session.getAttribute("error") != null) {
        errorMessage = (String) session.getAttribute("error");
        session.removeAttribute("error");
    }

    if (session.getAttribute("success") != null) {
        successMessage = (String) session.getAttribute("success");
        session.removeAttribute("success");
    }

%>

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
                List<Company> companies = (List<Company>) session.getAttribute("companies");
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

                    <a href="${pageContext.request.contextPath}/companies?action=drop&id=<%= company.getID() %>"
                       class="btn btn-delete" style="display:inline;"
                    >
                        Eliminar
                    </a>
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

<%@ include file="../../../../layout/footer.jsp" %>
<script src="${pageContext.request.contextPath}/components/private/admin/companies/companiesList.js"></script>
<script>
    const errorMessage = "<%= errorMessage %>";
    const successMessage = "<%= successMessage %>";
    if (errorMessage.length > 0) {
        console.log("ENTRA")
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: errorMessage
        });
    }

    if (successMessage.length > 0) {
        Swal.fire({
            icon: 'success',
            title: '¡Éxito!',
            text: successMessage
        });
    }

</script>
</body>
</html>
