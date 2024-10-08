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
          href="${pageContext.request.contextPath}/components/private/admin/companies/companiesAdd.css">
    <title>Agregar Compañía</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            margin-left: 22vw;
            margin-top: 10vh;

        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input, select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        button {
            padding: 10px 15px;
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

    </style>
</head>
<body>
<h1>
    <%boolean isEdit = request.getParameter("company") != null;%>
    <% if (isEdit) { %>
    Editar Compañía
    <% } else { %>
    Agregar Compañía
    <% } %>
</h1>

<form id="addCompany" action="${pageContext.request.contextPath}/companies" method="post">
    <input type="hidden" name="action" value="post">
    <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required value="${company.name}">
    </div>
    <div class="form-group">
        <label for="direccion">Dirección:</label>
        <input type="text" id="direccion" name="direccion" required value="${company.address}">
    </div>
    <div class="form-group">
        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono" required value="${company.phone}">
    </div>
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required value="${company.email}">
    </div>
    <div class="form-group">
        <label for="actividadEconomica">Actividad Económica:</label>
        <input type="text" id="actividadEconomica" name="actividadEconomica" required value="${company.economicactivity}">
    </div>
    <button type="submit">Agregar Compañía</button>
</form>
</body>
</html>
