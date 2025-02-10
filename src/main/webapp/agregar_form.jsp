<%--
  Created by IntelliJ IDEA.
  User: camil
  Date: 9/2/2025
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar empleado</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RR-HH Admin App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/agregar_empleado.css" type="text/css">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary " data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${contextPath}/index.jsp">RR-HH Admin App</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${contextPath}/index.jsp">Inicio</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<h2 class="text-center">Agregar Empleado</h2>

<form action="registrar-empleado" method="post">
    <label for="dui">DUI:</label>
    <input type="text" id="dui" name="dui" required pattern="^\d{9}$" title="Por favor ingrese un número de DUI válido">

    <label for="name">Nombre:</label>
    <input type="text" id="name" name="nombre" required maxlength="50" title="Por favor ingrese el nombre(max 50 caracteres)">

    <label for="user">Usuario:</label>
    <input type="text" id="user" name="usuario" required maxlength="50" title="Por favor ingrese un nombre de usuario (max 50 caracteres)">

    <label for="phone">Telefono:</label>
    <input type="text" id="phone" name="telefono" required pattern="^\d{4}-\d{4}$" title="Por favor ingrese un numero de telefono valido en el formato: ####-####">

    <label for="email">Correo Electronico:</label>
    <input type="email" id="email" name="correo" required maxlength="50" title="Por favor ingrese un correo electronico válido">

    <label for="dob">Fecha de Nacimiento:</label>
    <input type="date" id="dob" name="fechaNacimiento" required>

    <div class="text-center">
    <input type="submit" value="Agregar">
    </div>
</form>
<footer class="bg-dark text-light text-center py-3">
    <p class="mb-0">© 2025 Desarrollo de Aplicaciones con Web Frameworks.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>
