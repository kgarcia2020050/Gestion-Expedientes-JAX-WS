<%@ include file="/layout/topbar/topbar.jsp" %>
<%@ include file="/layout/sidebar/sidebar.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%
    boolean isDelete = false;
    boolean isEdit = request.getSession().getAttribute("company") != null;
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/components/private/admin/companies/companiesAdd.css">
    <%@ include file="../../../../layout/header.jsp" %>
    <title>
        <% if (request.getParameter("action") != null && request.getParameter("action").equals("drop")) {

            isDelete = true;
        %>
        Eliminar Compañía
        <%} else {%>
        <% if (isEdit) { %>
        Actualizar Compañía
        <% } else { %>
        Crear Compañía
        <% }
        }%>
    </title>
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
            color: white;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

    </style>
</head>
<bodY>
<h1>
    <% if (isDelete) {%>
    Eliminar Compañía
    <%} else {%>
    <% if (isEdit) { %>
    Actualizar Compañía
    <% } else { %>
    Crear Compañía
    <% }
    }%>
</h1>

<form id="addCompany" action="${pageContext.request.contextPath}/companies" method="post">
    <% if (isDelete) { %>
    <input type="hidden" name="action" value="delete">
    <% } else { %>
    <input type="hidden" name="action" value="post">
    <% } %>

    <input type="hidden" name="companyId" value="${company.getID()}"
           <%if(isDelete){%>disabled<%}%>>
    <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required value="${company.getNAME()}"
               <%if(isDelete){%>disabled<%}%>>
    </div>
    <div class="form-group">
        <label for="direccion">Dirección:</label>
        <input type="text" id="direccion" name="direccion" required value="${company.getADDRESS()}"
               <%if(isDelete){%>disabled<%}%>>
    </div>
    <div class="form-group">
        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono" required value="${company.getPHONE()}"
               <%if(isDelete){%>disabled<%}%>>
    </div>
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required value="${company.getEMAIL()}"
               <%if(isDelete){%>disabled<%}%>>
    </div>
    <div class="form-group">
        <label for="actividadEconomica">Actividad Económica:</label>
        <input type="text" id="actividadEconomica" name="actividadEconomica" required
               value="${company.getECONOMICACTIVITY()}" <%if(isDelete){%>disabled<%}%>>
    </div>
    <div class="form-group">
        <label for="idIdentification">Identificacion:</label>
        <input type="text" id="idIdentification" name="idIdentification" required
               value="${company.getIDIDENTIFICATION()}" <%if(isDelete){%>disabled<%}%>>
    </div>
    <% if (isDelete) {%>
    <button type="submit" class="btn btn-danger">
        Eliminar Compañía
    </button>
    <%} else {%>
    <% if (isEdit) { %>
    <button type="submit" class="btn btn-success">
        Actualizar Compañía
    </button>
    <% } else { %>
    <button type="submit" class="btn btn-primary">
        Crear Compañía
    </button>
    <% }
    }%>
</form>
</bodY>
</html>
