// Función para crear una fila de la tabla
function createTableRow(profesional) {
  const newRow = document.createElement('tr');
  newRow.innerHTML = `
    <td>${profesional.id}</td>
    <td>${profesional.run}</td>
    <td>${profesional.nombre}</td>
    <td>${profesional.apellido}</td>
    <td>${profesional.correo}</td>
    <td>${profesional.telefono}</td>
    <td>${profesional.cargo}</td>

    <td>
      <a href="#" data-profesional-id="${profesional.id}" data-lectura="true" class="fas fa-eye btn-ver-detalles"></a>
      <a href="#" data-profesional-id="${profesional.id}" data-lectura="false" class="fas fa-pen-to-square btn-editar"></a>
      <a href="#" data-profesional-id="${profesional.id}" class="fas fa-trash btn-eliminar"></a>
    </td>`;
  return newRow;
}

//Función para cargar los datos del profesional en la tabla
function cargarProfesional() {
  const tableBody = document.querySelector('#profesionalesTable tbody');
  fetch('http://localhost:8080/api/profesionales')
    .then(response => {
      if (!response.ok) {
        throw new Error('La solicitud ha fallado.');
      }
      return response.json();
    })
    .then(data => {
      data.forEach(profesional => {
        const newRow = createTableRow(profesional);
        tableBody.appendChild(newRow);
      });
    })
    .catch(error => {
      console.error('Ha ocurrido un error:', error);
    });
}

// Cargar los datos al cargar la página
document.addEventListener("DOMContentLoaded", cargarProfesional);

// Evento de clic para ver los detalles de la capacitación
document.addEventListener("click", function(event) {
  if (event.target.classList.contains("btn-ver-detalles")) {
    event.preventDefault();
    const profesionalId = event.target.getAttribute("data-profesional-id");
    leerProfesional(profesionalId);

  } else if (event.target.classList.contains("btn-editar")) {
    event.preventDefault();
    const profesionalId = event.target.getAttribute("data-profesional-id");
    editarProfesional(profesionalId);

  } else if (event.target.classList.contains("btn-eliminar")) {
    event.preventDefault();
    const profesionalId = event.target.getAttribute("data-profesional-id");
    eliminarProfesional(profesionalId);
  }
});

// Función para redirigir a la página de lectura de la capacitación usando el ID
function leerProfesional(id) {
    localStorage.setItem('esLectura', true);
  window.location.href = `/profesional/${id}`;
}

// Función para redirigir a la página de edición de la capacitación usando el ID
function editarProfesional(id) {
    localStorage.setItem('esLectura', false);
  window.location.href = `/profesional/${id}`;
}

// Función para eliminar la capacitación con el ID proporcionado
function eliminarProfesional(id) {
  fetch(`http://localhost:8080/api/profesionales/delete/${id}`, {
    method: 'DELETE'
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Error al eliminar el profesional.');
    }
    // Actualizar la tabla después de eliminar al profesional
    limpiarTabla();
    cargarProfesional();
  })
  .catch(error => {
    console.error('Error al eliminar el profesional', error);
  });
  function limpiarTabla() {
    const tableBody = document.querySelector('#profesionalesTable tbody');
    tableBody.innerHTML = ''; // Vaciar el contenido del cuerpo de la tabla
  }
}