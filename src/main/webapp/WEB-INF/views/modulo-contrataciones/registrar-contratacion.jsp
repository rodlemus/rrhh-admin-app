<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RR-HH Admin App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css" type="text/css">
</head>
<body class="d-block">
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
<div class="container-fluid  d-flex flex-column justify-content-center align-items-center">
    <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Crear Contratación</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Profile</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Contact</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="disabled-tab" data-bs-toggle="tab" data-bs-target="#disabled-tab-pane" type="button" role="tab" aria-controls="disabled-tab-pane" aria-selected="false" disabled>Disabled</button>
        </li>
    </ul>
    <div class="tab-content h-100 w-100" id="myTabContent">
        <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
            <div class="bg-white p-4 rounded shadow-lg w-100 w-md-50">
                <h2 class="h4 text-dark mb-4">Buscar Empleado</h2>

                <label for="buscador" class="form-label">DUI:</label>
                <input type="text" id="buscador"
                       class="form-control mb-2"
                       placeholder="Ingrese el DUI">

                <div class="mt-2">
                    <ul id="resultados" class="list-group bg-white border rounded shadow p-2 d-none">

                    </ul>
                </div>

                <div id="usuario-info" class="card p-3">
                    <h5 id="usuario-nombre" class="fw-bold"></h5>
                    <p><strong>DUI:</strong> <span id="usuario-dui"></span></p>
                    <p><strong>ID Empleado: </strong><span id="usuario-id"></span></p>
                    <p><strong>Teléfono:</strong> <span id="usuario-telefono"></span></p>
                </div>



                <label for="tipoCargo" class="form-label mt-4">Tipo de Cargo:</label>
                <select id="tipoCargo" class="form-select">
                    <c:forEach items="${cargos}" var="cargo">
                        <option value="${cargo.getIdCargo()}">${cargo.getCargo()} - ${cargo.getDescripcionCargo()}</option>
                    </c:forEach>
                </select>

                <label for="departamento" class="form-label mt-4">Departamento:</label>
                <select id="departamento" class="form-select">
                    <c:forEach items="${departamentos}" var="departamento">
                        <option value="${departamento.getIdDepartamento()}">${departamento.getDepartamento()} - ${departamento.getDescripcion()}</option>
                    </c:forEach>
                </select>

                <label for="tipoContratacion" class="form-label mt-4">Tipo de Contratación:</label>
                <select id="tipoContratacion" class="form-select">
                    <c:forEach items="${tiposContrataciones}" var="tipoContratacion">
                        <option value="${tipoContratacion.getIdTipoC()}">${tipoContratacion.getTipoC()}</option>
                    </c:forEach>
                </select>
                <!-- Input para la Fecha de Contratación -->
                <label for="fechaContratacion" class="form-label mt-4">Fecha de Contratación:</label>
                <input type="date" id="fechaContratacion" class="form-control mb-2" placeholder="Seleccione la fecha de contratación">

                <!-- Input para el Salario -->
                <label for="salario" class="form-label mt-4">Salario:</label>
                <input type="number" id="salario" class="form-control mb-2" placeholder="Ingrese el salario" step="0.01">
                <div>
                    <button type="button" class="btn btn-primary mt-4" id="btnGuardarContratacion">Guardar Contratación</button>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
            <h1>Listar Contratacio</h1>
            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                <tr>
                    <th>ID Empleado</th>
                    <th>Departamento</th>
                    <th>Tipo de Contratación</th>
                    <th>Cargo</th>
                    <th>Salario</th>
                    <th>Estado</th>
                </tr>
                </thead>
                <tbody id="contrataciones-body">
                <!-- Aquí se insertarán las filas dinámicamente -->
                </tbody>
                <tfoot>
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                    </ul>
                </nav>
                </tfoot>
            </table>

        </div>
        <div class="tab-pane fade" id="contact-tab-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="0">...</div>
        <div class="tab-pane fade" id="disabled-tab-pane" role="tabpanel" aria-labelledby="disabled-tab" tabindex="0">...</div>
    </div>

    <div class="bg-dark text-light text-center py-3 mt-auto w-100">
        <p class="mb-0">© 2025 Desarrollo de Aplicaciones con Web Frameworks.</p>
    </div>
</div>




<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalnesert2@11"></script>

<script>
    const contextPath = "<%= request.getContextPath() %>";

    const buscadorInput = document.getElementById("buscador");
    const containerResultados = document.getElementById("resultados");
    const btnGuardarContratacion = document.getElementById("btnGuardarContratacion");

    const buscarUsuarioPorDui = async (palabraBuscar) => {
        const url = `${contextPath}/buscar-dui?query=`+palabraBuscar;
        console.log("target ", url)
       const result = await fetch(url);
       if (!result.ok){
           return []
       }
       return await result.json()
    }

    const buscadorMostrarResultados = (empleados, container) => {


        const onUserClick = (id, dui, nombre, telefono) =>{
            const usuarioNombre = document.getElementById("usuario-nombre");
            const usuarioDui = document.getElementById("usuario-dui");
            const usuarioTelefono = document.getElementById("usuario-telefono");
            const usuarioId = document.getElementById('usuario-id');

            usuarioNombre.textContent = nombre;
            usuarioDui.textContent = dui;
            usuarioTelefono.textContent = telefono;
            usuarioId.textContent = id;
        }


        for (const empleado of empleados) {
            const userListItem = document.createElement("li");
            userListItem.className = "list-group-item p-2 hover:bg-light cursor-pointer"
            userListItem.setAttribute("user-id",empleado.idEmpleado);
            userListItem.textContent = empleado.nombre;
            console.log(userListItem)
            userListItem.addEventListener("click", () =>{
                console.log("click")
                onUserClick(empleado.idEmpleado, empleado.numeroDui, empleado.nombre, empleado.numeroTelefono)
            })

            container.appendChild(userListItem)
        }
    }

    buscadorInput.addEventListener("input",async (e)=>{
        const palabraBuscar = e.target.value;

        const empleados = await buscarUsuarioPorDui(palabraBuscar);
        for (const childResult of containerResultados.children) {
            childResult.remove()
        }
        if(empleados.length === 0) return;
        if(palabraBuscar === '' || palabraBuscar === null || palabraBuscar === undefined) return;
        containerResultados.classList.remove("d-none")
        buscadorMostrarResultados(empleados, containerResultados)

    })

    buscadorInput.addEventListener("focusout", () => {
        setTimeout(() => {
            containerResultados.classList.add("d-none");
        }, 200); // Espera 200ms para que el `click` se capture primero
    });

    btnGuardarContratacion.addEventListener('click', async () => {
        // Obtener el DUI del usuario
        const dui = document.getElementById('usuario-dui').textContent.trim();

        // Validar si el DUI está vacío
        if (!dui) {
            alert('El DUI del usuario no está disponible. No se puede realizar la contratación.');
            return;  // Si está vacío, no hacer la petición
        }

        // Obtener los valores de los select
        const idEmpleado = document.getElementById('usuario-id').textContent;
        const idCargo = document.getElementById('tipoCargo').value;
        const idDepartamento = document.getElementById('departamento').value;
        const idTipoContratacion = document.getElementById('tipoContratacion').value;
        const fechaContratacion = document.getElementById("fechaContratacion").value;
        const salario = document.getElementById("salario").value;


        // Crear el objeto con los datos a enviar
        const data = {
            idEmpleado: parseInt(idEmpleado),
            idCargo: parseInt(idCargo),
            idDepartamento: parseInt(idDepartamento),
            idTipoContratacion: parseInt(idTipoContratacion),
            fechaContratacion,
            salario: parseFloat(salario)
        };



        try{
            const result = await fetch(`${contextPath}/contrataciones`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
            if (!result.ok){
                Swal.fire({
                    title: "Error al crear Contratación",
                    icon: "warn"
                });
                return;
            }
            Swal.fire({
                title: "Contratación creada con exito",
                icon: "success"
            });
        }catch (e){
            Swal.fire({
                title: "Error al crear Contratación",
                icon: "warn"
            });
        }
    });


    //listar contrataciones
    const cargarContrataciones = async (page = 1, limit = 5) => {
        const result = await fetch(`${contextPath}/listar-contrataciones?page=`+page+'&limit='+limit);
        const response = await result.json();

        console.log(response)
    }

    cargarContrataciones();
</script>

</body>

</html>