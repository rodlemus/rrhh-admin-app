<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>RR-HH Admin App</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css" type="text/css">
</head>
<body class="h-100">
<nav class="navbar navbar-expand-lg bg-body-tertiary " data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">RR-HH Admin App</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#">Inicio</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div class="container-fluid h-100 w-full d-flex justify-content-center align-items-center">
  <div class="row ">
    <div class="col">
      <div class="card h-100" style="width: 18rem;">
        <div class="card-body d-flex flex-column justify-content-between">
          <div class="pb-4">
            <h5 class="card-title">Módulo 1 - Registrar Empleado</h5>
            <p class="card-text">En este modulo se registran los empleados y sus datos personales es un paso previo al crear la contratación en el sistema.</p>
          </div>
          <a href="${contextPath}/agregar_form.jsp" class="btn btn-primary">Acceder</a>
        </div>

      </div>
    </div>
    <div class="col">
      <div class="card h-100" style="width: 18rem;">
        <div class="card-body d-flex flex-column justify-content-between">
          <div class="pb-4">
            <h5 class="card-title flex-fill">Módulo 2 - Administrar Propiedas Del Empleado</h5>
            <p class="card-text">Este modulo es encargado de ingresar - modificar - y listar, propiedades como el cargo, tipo de contrato y departamento</p>
          </div>
          <a href="${contextPath}/router-app?modulo=propiedades" class="btn btn-primary">Acceder</a>
        </div>

      </div>
    </div>
    <div class="col">
      <div class="card" style="width: 18rem;">
        <div class="card-body d-flex flex-column justify-content-between">
          <div class="pb-4">
            <h5 class="card-title">Módulo 3 - Registrar Contrataciones</h5>
            <p class="card-text">Este modulo es el encargado de finalizar el proceso de contratacion con el registro en el sistema del nuevo empleado.</p>
          </div>
          <a href="${contextPath}/router-app?modulo=contrataciones" class="btn btn-primary">Acceder</a>
        </div>
      </div>
    </div>
  </div>
</div>
<footer class="bg-dark text-light text-center py-3">
  <p class="mb-0">© 2025 Desarrollo de Aplicaciones con Web Frameworks.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>