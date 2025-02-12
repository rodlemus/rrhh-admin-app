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
<body class="d-flex flex-column justify-content-between">
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
                    <a class="nav-link" aria-current="page" href="${contextPath}/empleados">Registrar Empleado</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${contextPath}">Administrar Propiedas Del Empleado</a>
                </li>
            </ul>
        </div>
    </div>
    </nav>

    <section class="flex-grow-1">
        <div class="container-fluid w-full d-flex justify-content-center align-items-center mt-4 mb-4">
            <h1>Administracion de propiedades del empleado</h1>
        </div>

        <div class="container mt-2 mb-4">
            <button class="btn btn-success p-2 text-white text-center" id="agregar_cargo">
                <strong class="ml-3">Agregar nuevo cargo</strong>
                <svg xmlns="http://www.w3.org/2000/svg" height="30px" viewBox="0 -960 960 960" width="30px" fill="#fff">
                    <path d="M440-440H200v-80h240v-240h80v240h240v80H520v240h-80v-240Z"/>
                </svg>
            </button>
            <div class="w-full mt-2 bg-gray-200">
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="cargos" data-bs-toggle="tab" data-bs-target="#cargos-tab-pane" type="button" role="tab" aria-controls="cargos-tab-pane" aria-selected="true">Cargos Disponibles</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="Tcontrato-tab" data-bs-toggle="tab" data-bs-target="#Tcontrato-tab-pane" type="button" role="tab" aria-controls="Tcontrato-tab-pane" aria-selected="false">Tipos de Contrato</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="departamentos-tab" data-bs-toggle="tab" data-bs-target="#departamentos-tab-pane" type="button" role="tab" aria-controls="departamentos-tab-pane" aria-selected="false">Departamentos</button>
                    </li>
                </ul>
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="cargos-tab-pane" role="tabpanel" aria-labelledby="cargos-tab" tabindex="0">
                        <table class="table table-striped w-full">
                            <thead class="p-3">
                                <tr class="text-center">
                                    <th>ID</th>
                                    <th>Nombre del cargo</th>
                                    <th>Descripcion del cargo</th>
                                    <th>Jefatura</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${cargos}" var="cargo">
                                <tr class="text-center">
                                    <td>${cargo.getIdCargo()}</td>
                                    <td>${cargo.getCargo()}</td>
                                    <td>${cargo.getDescripcionCargo()}</td>
                                    <td>${cargo.isJefatura()}</td>
                                    <td class="d-flex justify-content-center align-items-center flex-gap-4">
                                        <button class="btn btn-warning">
                                            <svg xmlns="http://www.w3.org/2000/svg" height="30px" viewBox="0 -960 960 960" width="30px" fill="#fff">
                                                <path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/>
                                            </svg>
                                        </button>
                                        <button class="btn btn-danger">
                                            <svg xmlns="http://www.w3.org/2000/svg" height="30px" viewBox="0 -960 960 960" width="30px" fill="#fff">
                                                <path d="M280-120q-33 0-56.5-23.5T200-200v-520h-40v-80h200v-40h240v40h200v80h-40v520q0 33-23.5 56.5T680-120H280Zm400-600H280v520h400v-520ZM360-280h80v-360h-80v360Zm160 0h80v-360h-80v360ZM280-720v520-520Z"/>
                                            </svg>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane fade" id="Tcontrato-tab-pane" role="tabpanel" aria-labelledby="Tcontrato-tab" tabindex="0">

                    </div>
                    <div class="tab-pane fade" id="departamentos-tab-pane" role="tabpanel" aria-labelledby="departamentos-tab" tabindex="0">

                    </div>
                </div>
            </div>
        </div>
    </section>

    <footer class="bg-dark text-light text-center py-3">
        <p class="mb-0">© 2025 Desarrollo de Aplicaciones con Web Frameworks.</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>
