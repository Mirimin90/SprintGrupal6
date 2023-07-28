// Función para crear una fila de la tabla
function createTableRow(administrativo) {
  const newRow = document.createElement('tr');
  newRow.innerHTML = `
    <td>${administrativo.id}</td>
    <td>${administrativo.run}</td>
    <td>${administrativo.nombre}</td>
    <td>${administrativo.apellido}</td>
    <td>${administrativo.correo}</td>
    <td>${administrativo.area}</td>
    <td>
      <a href="#" data-administrativo-id="${administrativo.id}" data-lectura="true" class="fas fa-eye btn-ver-detalles"></a>
      <a href="#" data-administrativo-id="${administrativo.id}" data-lectura="false" class="fas fa-pen-to-square btn-editar"></a>
      <a href="#" data-administrativo-id="${administrativo.id}" class="fas fa-trash btn-eliminar"></a>
    </td>`;
  return newRow;
}

//Función para cargar los datos del administrativo en la tabla
function cargarAdministrativo() {
  const tableBody = document.querySelector('#administrativosTable tbody');
  fetch('http://localhost:8080/api/administrativos')
    .then(response => {
      if (!response.ok) {
        throw new Error('La solicitud ha fallado.');
      }
      return response.json();
    })
    .then(data => {
      data.forEach(administrativo => {
        const newRow = createTableRow(administrativo);
        tableBody.appendChild(newRow);
      });
    })
    .catch(error => {
      console.error('Ha ocurrido un error:', error);
    });
}

// Cargar los datos al cargar la página
document.addEventListener("DOMContentLoaded", cargarAdministrativo);

// Evento de clic para ver los detalles del administrativo
document.addEventListener("click", function(event) {
  if (event.target.classList.contains("btn-ver-detalles")) {
    event.preventDefault();
    const administrativoId = event.target.getAttribute("data-administrativo-id");
    leerAdministrativo(administrativoId);

  } else if (event.target.classList.contains("btn-editar")) {
    event.preventDefault();
    const administrativoId = event.target.getAttribute("data-administrativo-id");
    editarAdministrativo(administrativoId);

  } else if (event.target.classList.contains("btn-eliminar")) {
    event.preventDefault();
    const administrativoId = event.target.getAttribute("data-administrativo-id");
    eliminarAdministrativo(administrativoId);
  }
});

// Función para redirigir a la página de lectura del administrativo usando el ID
function leerAdministrativo(id) {
    localStorage.setItem('esLectura', true);
  window.location.href = `/administrativo/${id}`;
}

// Función para redirigir a la página de edición del administrativo usando el ID
function editarAdministrativo(id) {
    localStorage.setItem('esLectura', false);
  window.location.href = `/administrativo/${id}`;
}

// Función para eliminar el administrativo con el ID proporcionado
function eliminarAdministrativo(id) {
  fetch(`http://localhost:8080/api/administrativos/delete/${id}`, {
    method: 'DELETE'
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Error al eliminar el administrativo.');
    }
    // Actualizar la tabla después de eliminar al administrativo
    limpiarTabla();
    cargarAdministrativo();
  })
  .catch(error => {
    console.error('Error al eliminar el administrativo', error);
  });
  function limpiarTabla() {
    const tableBody = document.querySelector('#administrativosTable tbody');
    tableBody.innerHTML = ''; // Vaciar el contenido del cuerpo de la tabla
  }
}