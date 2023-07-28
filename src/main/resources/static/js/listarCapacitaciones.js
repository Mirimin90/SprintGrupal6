// Utilidades para formateo de fecha y hora
function formatDate(dateString) {
  const date = new Date(dateString);
  const day = String(date.getDate()).padStart(2, '0');
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const year = date.getFullYear();
  return `${year}-${month}-${day}`;
}

function formatTime(timeString) {
  const [hours, minutes] = timeString.split(':');
  return `${hours}:${minutes}`;
}

// Función para crear una fila de la tabla
function createTableRow(capacitacion) {
  const newRow = document.createElement('tr');
  newRow.innerHTML = `
    <td>${capacitacion.id}</td>
    <td>${capacitacion.nombre}</td>
    <td>${capacitacion.detalle}</td>
    <td>${formatDate(capacitacion.fecha)}</td>
    <td>${formatTime(capacitacion.hora)}</td>
    <td>${capacitacion.lugar}</td>
    <td>${capacitacion.duracion}</td>
    <td>${capacitacion.cantidad}</td>
    <td>
      <a href="#" data-capacitacion-id="${capacitacion.id}" data-lectura="true" class="fas fa-eye btn-ver-detalles"></a>
      <a href="#" data-capacitacion-id="${capacitacion.id}" data-lectura="false" class="fas fa-pen-to-square btn-editar"></a>
      <a href="#" data-capacitacion-id="${capacitacion.id}" class="fas fa-trash btn-eliminar"></a>
    </td>`;
  return newRow;
}

// Función para cargar los datos de capacitaciones en la tabla
function cargarCapacitaciones() {
  const tableBody = document.querySelector('#capacitacionesTable tbody');
  fetch('http://localhost:8080/api/listadoCapacitaciones')
    .then(response => {
      if (!response.ok) {
        throw new Error('La solicitud ha fallado.');
      }
      return response.json();
    })
    .then(data => {
      data.forEach(capacitacion => {
        const newRow = createTableRow(capacitacion);
        tableBody.appendChild(newRow);
      });
    })
    .catch(error => {
      console.error('Ha ocurrido un error:', error);
    });
}

// Cargar los datos al cargar la página
document.addEventListener("DOMContentLoaded", cargarCapacitaciones);

// Evento de clic para ver los detalles de la capacitación
document.addEventListener("click", function(event) {
  if (event.target.classList.contains("btn-ver-detalles")) {
    event.preventDefault();
    const capacitacionId = event.target.getAttribute("data-capacitacion-id");
    leerCapacitacion(capacitacionId);

  } else if (event.target.classList.contains("btn-editar")) {
    event.preventDefault();
    const capacitacionId = event.target.getAttribute("data-capacitacion-id");
    editarCapacitacion(capacitacionId);

  } else if (event.target.classList.contains("btn-eliminar")) {
    event.preventDefault();
    const capacitacionId = event.target.getAttribute("data-capacitacion-id");
    eliminarCapacitacion(capacitacionId);
  }
});

// Función para redirigir a la página de lectura de la capacitación usando el ID
function leerCapacitacion(id) {
    localStorage.setItem('esLectura', true);
  window.location.href = `/capacitacion/${id}`;
}

// Función para redirigir a la página de edición de la capacitación usando el ID
function editarCapacitacion(id) {
    localStorage.setItem('esLectura', false);
  window.location.href = `/capacitacion/${id}`;
}

// Función para eliminar la capacitación con el ID proporcionado
function eliminarCapacitacion(id) {
  fetch(`http://localhost:8080/api/listadoCapacitaciones/delete/${id}`, {
    method: 'DELETE'
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Error al eliminar la capacitación.');
    }
    // Actualizar la tabla después de eliminar la capacitación
    limpiarTabla();
    cargarCapacitaciones();
  })
  .catch(error => {
    console.error('Error al eliminar la capacitación:', error);
  });
  function limpiarTabla() {
    const tableBody = document.querySelector('#capacitacionesTable tbody');
    tableBody.innerHTML = ''; // Vaciar el contenido del cuerpo de la tabla
  }
}
