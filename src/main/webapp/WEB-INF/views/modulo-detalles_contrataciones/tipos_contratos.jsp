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
        <div class="d-flex justify-content-between align-items-center">
            <button class="btn btn-success p-2 text-white text-center" type="button" data-bs-toggle="modal" data-bs-target="#modalAgregarTipoC">
                <strong class="ml-3">Agregar Nuevo Tipo de Contrato</strong>
                <svg xmlns="http://www.w3.org/2000/svg" height="30px" viewBox="0 -960 960 960" width="30px" fill="#fff">
                    <path d="M440-440H200v-80h240v-240h80v240h240v80H520v240h-80v-240Z"/>
                </svg>
            </button>
            <div class="buscador w-50">
                <input type="text" id="buscador" class="form-control p-2" placeholder="Buscar tipo de contrato...">
            </div>
        </div>
        <div class="w-full mt-2 bg-gray-200">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="cargos" data-bs-toggle="tab" data-bs-target="#cargos-tab-pane" type="button" role="tab" aria-controls="cargos-tab-pane" aria-selected="false" onclick="window.location.href='${contextPath}/router-app?modulo=propiedades'">Cargos Disponibles</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="Tcontrato" data-bs-toggle="tab" data-bs-target="#Tcontrato-tab-pane" type="button" role="tab" aria-controls="Tcontrato-tab-pane" aria-selected="true">Tipos de Contrato</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="departamentos-tab" data-bs-toggle="tab" data-bs-target="#departamentos-tab-pane" type="button" role="tab" aria-controls="departamentos-tab-pane" aria-selected="false" onclick="window.location.href='${contextPath}/router-app?modulo=departamentos'">Departamentos</button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade" id="cargos-tab-pane" role="tabpanel" aria-labelledby="cargos-tab" tabindex="0">

                </div>
                <div class="tab-pane fade show active" id="Tcontrato-tab-pane" role="tabpanel" aria-labelledby="Tcontrato-tab" tabindex="0">
                    <table class="table table-striped w-full">
                        <thead>
                        <tr class="text-center">
                            <th>ID</th>
                            <th>Tipo de Contrato</th>
                            <th style="width: 20%;">Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${tiposC}" var="tipoC">
                            <tr class="text-center">
                                <td>${tipoC.getIdTipoC()}</td>
                                <td>${tipoC.getTipoC()}</td>
                                <td class="d-flex justify-content-center align-items-center gap-2">
                                    <button class="btn btn-warning btn-editar" type="button" data-bs-toggle="modal" data-bs-target="#modalEditarTipoC" data-id="${tipoC.getIdTipoC()}" data-tipoc="${tipoC.getTipoC()}">
                                        <svg xmlns="http://www.w3.org/2000/svg" height="30px" viewBox="0 -960 960 960" width="30px" fill="#fff">
                                            <path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/>
                                        </svg>
                                    </button>
                                    <button class="btn btn-danger btn-eliminar" type="button" data-bs-toggle="modal" data-bs-target="#modalEliminarTipoC" data-id="${tipoC.getIdTipoC()}" data-tipoc="${tipoC.getTipoC()}">
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
                <div class="tab-pane fade" id="departamentos-tab-pane" role="tabpanel" aria-labelledby="departamentos-tab" tabindex="0">

                </div>
            </div>
        </div>
    </div>
</section>

<div class="mb-3">
    <nav aria-label="...">
        <ul class="pagination justify-content-center mt-3">
            <c:if test="${paginaActual > 1}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/tipos_contratos?pagina=${paginaActual - 1}">Anterior</a>
                </li>
            </c:if>

            <c:forEach var="i" begin="1" end="${totalPaginas}">
                <li class="page-item ${i == paginaActual ? 'active' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/tipos_contratos?pagina=${i}">${i}</a>
                </li>
            </c:forEach>

            <c:if test="${paginaActual < totalPaginas}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/tipos_contratos?pagina=${paginaActual + 1}">Siguiente</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

<!-- Modal de agregar registros -->
<div class="modal fade" id="modalAgregarTipoC" tabindex="-1" aria-labelledby="modalAgregarTipoC" aria-hidden="true" data-bs-backdrop="static">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Agregar Tipo de Contrato</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="registrar-tipoC" method="post">
                    <div class="mb-3">
                        <label for="tipoC" class="form-label">Tipo de Contrato:</label>
                        <input type="text" class="form-control" id="tipoC" aria-labelledby="tipoC" name="tipoC" autocomplete="off" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-primary">Registrar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modales de actualizar registros-->
<div class="modal fade" id="modalEditarTipoC" tabindex="-1" aria-labelledby="modalEditarTipoCLabel" aria-hidden="true" data-bs-backdrop="static">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Actualizar Tipo de Contrato</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="actualizar-tipoC" method="post">
                    <input type="hidden" id="edit-idTipoC" name="idTipoC">

                    <div class="mb-3">
                        <label for="tipoC" class="form-label">Tipo de Contrato:</label>
                        <input type="text" id="edit-tipoC" class="form-control" aria-labelledby="tipoC" name="tipoC" autocomplete="off" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal para eliminar registros -->
<div class="modal fade" id="modalEliminarTipoC" tabindex="-1" aria-labelledby="modalEliminarTipoCLabel" aria-hidden="true" data-bs-backdrop="static">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4>Confirmación de eliminación de registro</h4>
            </div>
            <div class="modal-body">
                <!--Campo oculto para el ID del cargo-->
                <input type="hidden" id="del-idTipoC" name="idTipoC">
                <p>
                    ¿Deseas eliminar el tipo de contrato <strong><span id="del-tipoC"></span></strong>?
                </p>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-danger" onclick="eliminarTipoContrato()">Confirmar</button>
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

<script>

    // Funcion para cargar los datos de cargo en el modal de actualizar
    const mostrarTiposContratosActualizar = () => {
        document.addEventListener("DOMContentLoaded", function() {
            const editarButton = document.querySelectorAll(".btn-editar");

            editarButton.forEach(button => {
                button.addEventListener('click', function() {
                    // Obtenemos los datos del atributo data- de los botones
                    const idTipoC = button.getAttribute("data-id");
                    const tipoC = button.getAttribute("data-tipoc");

                    // Rellenamos los campos del modal con los datos obtenidos
                    document.getElementById("edit-idTipoC").value = idTipoC;
                    document.getElementById("edit-tipoC").value = tipoC;
                });
            });
        });
    };

    const mostrarDatosEliminar = () => {
        document.addEventListener('DOMContentLoaded', function() {
            const btnEliminar = document.querySelectorAll('.btn-eliminar');

            btnEliminar.forEach(button => {
                button.addEventListener('click', function() {
                    const idCargo = button.getAttribute("data-id");
                    const cargo = button.getAttribute("data-tipoc");

                    document.getElementById("del-idTipoC").value = idCargo;
                    document.getElementById("del-tipoC").textContent = cargo;

                    console.log(idCargo)
                });
            });
        });
    };

    const contextPath = "<%= request.getContextPath() %>";
    const eliminarTipoContrato = async () => {
        const idTipoC = parseInt(document.getElementById('del-idTipoC').value);  // Obtenemos el ID del campo oculto
        try {
            const response = await fetch(contextPath + '/eliminar-tipoC?idTipoC=' + idTipoC, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            if (response.ok) {
                console.log("Tipo de Contrato eliminado con éxito.");
                window.location.reload(); // recargamos la pagina para recargar los datos de la tabla
            } else {
                console.log("Error al eliminar el tipo de contrato.");
            }
        } catch (error) {
            console.log("Error de red o de servidor:", error);
        }
    };

    const buscarTiposContratos = () => {
        document.addEventListener("DOMContentLoaded", function() {
            const inputBuscador = document.getElementById("buscador");
            const filas = document.querySelectorAll("tbody tr");

            inputBuscador.addEventListener("input", function() {
                const filtro = inputBuscador.value.trim().toLowerCase();

                filas.forEach(fila => {
                    const tipoC = fila.querySelector("td:nth-child(2)").textContent.toLowerCase();

                    // Si el nombre del tipo de contrato contiene el texto del input, mostramos la fila, de lo contrario, la ocultamos, usando propiedades css
                    if (tipoC.includes(filtro)) {
                        fila.style.display = "";
                    } else {
                        fila.style.display = "none";
                    }
                });
            });
        });
    }

    mostrarTiposContratosActualizar();
    mostrarDatosEliminar();
    buscarTiposContratos();

</script>
</body>
</html>
