<%@ include file="/layout/topbar/topbar.jsp" %>
<%@ include file="/layout/sidebar/sidebar.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="home.css">
</head>
<body>

<div class="main-content">
    <div class="content-container">
        <div class="welcome-section">
            <h1>Bienvenido Administrador</h1>
            <p>Este es tu sistema de gestión de expedientes</p>
        </div>

        <div class="main-content">
            <div class="card">
                <h3>Gestión de Expedientes</h3>
                <p>Administra todos los expedientes que necesitan pasar por el proceso de aprobación.</p>
            </div>

            <div class="card">
                <h3>Flujo de Aprobación</h3>
                <p>Monitorea el estado de los expedientes en cada etapa del flujo de aprobación.</p>
            </div>

            <div class="card">
                <h3>Finalización Exitosa</h3>
                <p>Verifica cuando un expediente ha sido completamente aprobado y finalizado con éxito.</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
