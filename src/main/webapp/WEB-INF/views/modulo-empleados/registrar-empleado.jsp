<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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
          <a class="nav-link" aria-current="page" href="${contextPath}">Inicio</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="${contextPath}">Registrar Empleado</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="${contextPath}">Administrar Propiedas Del Empleado</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div class="container-fluid h-100 w-full d-flex flex-column justify-content-center align-items-center mt-3">
  <h1>Registrar Empleado</h1>
  <div class="w-75">
    <!-- Nav Tabs -->
    <ul class="nav nav-tabs" id="empleadoTab" role="tablist">
      <li class="nav-item" role="presentation">
        <button class="nav-link active" id="registrar-tab" data-bs-toggle="tab" data-bs-target="#registrar" type="button" role="tab">Registrar Empleado</button>
      </li>
      <li class="nav-item"  role="presentation">
        <button class="nav-link" id="listar-tab" data-bs-toggle="tab" data-bs-target="#listar" type="button" role="tab">Listar Empleados</button>
      </li>
    </ul>

    <!-- Tab Content -->
    <div class="tab-content p-3 border border-top-0">
      <!-- Registrar Empleado -->
      <div class="tab-pane fade show active" id="registrar" role="tabpanel">
        <form action="registrar-empleado" method="post">
          <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
          </div>
          <div class="mb-3">
            <label for="dui" class="form-label">Número de DUI: </label>
            <input type="text" class="form-control" id="dui" name="dui" maxlength="9" minlength="9" required>
          </div>
          <div class="mb-3">
            <label for="usuario" class="form-label">Usuario: </label>
            <input type="text" class="form-control" id="usuario" name="usuario" minlength="4" maxlength="50" required>
          </div>
          <div class="mb-3">
            <label for="correo" class="form-label">Correo Electrónico</label>
            <input type="email" class="form-control" id="correo" name="correo" required>
          </div>
          <div class="mb-3">
            <label for="telefono" class="form-label">Telefono: </label>
            <input type="text" class="form-control" id="telefono" name="telefono" minlength="8" maxlength="8" required>
          </div>
          <div class="mb-3">
            <label for="fechaNacimiento" class="form-label">Fecha de nacimiento</label>

            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" max="2007-12-31"  required/>

          </div>
          <button type="submit" class="btn btn-primary">Registrar</button>
        </form>
      </div>

      <!-- Listar Empleados -->
      <div class="tab-pane fade " id="listar" role="tabpanel">
        <table class="table table-striped">
          <thead>
          <tr>
            <th>Nombre</th>
            <th>Editar</th>
            <th>Eliminar</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${empleados}" var="empleado">
            <tr>
              <td>${empleado.getNombre()}</td>
              <td>
                <button class="btn btn-warning">Editar</button>
              </td>
              <td>
                <button class="btn btn-danger" onclick="eliminarEmpleado(e,${empleado.getIdEmpleado()})">Eliminar</button>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<footer class="bg-dark text-light text-center py-3">
  <p class="mb-0">© 2025 Desarrollo de Aplicaciones con Web Frameworks.</p>
</footer>
<script>
  const contextPath = "<%= request.getContextPath() %>";
  // logica pra el funcionamiento de los tabs de bootrap
  const buttonTabs = document.querySelectorAll(".nav-tabs button")
  console.log(buttonTabs)
  for (const tab of buttonTabs) {
    tab.addEventListener("click", (e) =>{
      const currentActiveTab = document.querySelector(".nav-tabs .active");
      const currentActiveTabContent = document.querySelector(".tab-pane.active");

      currentActiveTab.classList.remove("active");
      e.target.classList.add("active")
      currentActiveTabContent.classList.remove("show", "active");

      const targetContentId = e.target.getAttribute("data-bs-target");
      const desiredTabContent = document.querySelector(targetContentId);

      e.target.classList.add("active")
      desiredTabContent.classList.add("show","active")
    })
  }


  // Función para eliminar empleado mediante un HTTP DELETE
  const eliminarEmpleado = async (event, empleadoId) => {
    try {
      // Hacer la solicitud DELETE al endpoint
      const response = await fetch(contextPath+'/eliminar-empleado?id='+empleadoId, {
        method: 'DELETE',  // Especificamos que es un método DELETE
        headers: {
          'Content-Type': 'application/json'  // Establecemos el tipo de contenido
        }
      });
      console.log(response)
      // Verificar si la respuesta fue exitosa
      if (response.status) {
        console.log(event.target.parentNode)
        const result = await response.json(); // Parseamos la respuesta JSON
        Swal.fire({
          title: result.message,
          icon: "success"
        });
      } else {
        Swal.fire({
          title: result.message,
          icon: "error"
        });
      }
    } catch (error) {
      console.log(error)
      Swal.fire({
        title: "Error, del sistema, contactar con un tecnico.",
        icon: "error"
      });
    }
  };

</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>