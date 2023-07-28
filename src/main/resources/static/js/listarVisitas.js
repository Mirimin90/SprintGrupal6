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
async function createTableRow(visita) {
    const nombreCliente = await obtenerNombreClientePorID(visita.clienteId);
    const newRow = document.createElement('tr');
    newRow.innerHTML = `
    <td>${visita.id}</td>
    <td>${nombreCliente}</td>
    <td>${formatDate(visita.fecha)}</td>
    <td>${formatTime(visita.hora)}</td>
    <td>${visita.lugar}</td>
    <td>${visita.realizado}</td>
    <td>${visita.detalle}</td>
    <td>
      <a href="#" data-visita-id="${visita.id}" data-lectura="true" class="fas fa-eye btn-ver-detalles"></a>
      <a href="#" data-visita-id="${visita.id}" data-lectura="false" class="fas fa-pen-to-square btn-editar"></a>
      <a href="#" data-visita-id="${visita.id}" class="fas fa-trash btn-eliminar"></a>
      <a href="#" data-visita-id="${visita.id}" data-lectura="false" class="fa-solid fa-square-check btn-check-list"></a>
    </td>`;
    return newRow;
}

//Función para cargar los datos de visita en la tabla
function cargarVisitas() {
    const tableBody = document.querySelector('#visitasTable tbody');
      fetch('http://localhost:8080/api/visitas')
        .then(response => {
          if (!response.ok) {
            throw new Error('La solicitud ha fallado.');
          }
          return response.json();
        })
        .then(async data => {
          for (const visita of data) {
           //const nombreCliente = await obtenerNombreClientePorID(pago.id);
            const newRow =  await createTableRow(visita);
            tableBody.appendChild(newRow);
          }
        })
        .catch(error => {
          console.error('Ha ocurrido un error:', error);
        });
}

async function obtenerNombreClientePorID(id) {
  try {
    const response = await fetch('http://localhost:8080/api/clientes');
    if (!response.ok) {
      throw new Error('La solicitud ha fallado.');
    }
    const clientes = await response.json();
    const clienteEncontrado = clientes.find(cliente => cliente.id === id);
    return clienteEncontrado ? clienteEncontrado.nombre : 'Cliente no encontrado';
  } catch (error) {
    console.error('Error al obtener los datos de clientes:', error);
    return null;
  }
}

// Cargar los datos al cargar la página
document.addEventListener("DOMContentLoaded", cargarVisitas);

// Evento de clic para ver los detalles de la visita
document.addEventListener("click", function(event) {
    if (event.target.classList.contains("btn-ver-detalles")) {
        event.preventDefault();
        const visitaId = event.target.getAttribute("data-visita-id");
        leerVisita(visitaId);

    } else if (event.target.classList.contains("btn-editar")) {
        event.preventDefault();
        const visitaId = event.target.getAttribute("data-visita-id");
        editarVisita(visitaId);

    } else if (event.target.classList.contains("btn-eliminar")) {
        event.preventDefault();
        const visitaId = event.target.getAttribute("data-visita-id");
        eliminarVisita(visitaId);
    } else if (event.target.classList.contains("btn-check-list")) {
        event.preventDefault();
        const visitaId = event.target.getAttribute("data-visita-id");
        verChecklist(visitaId);
    }
});
// Función para redirigir a la página de lectura de checklist usando el ID
function verChecklist(id) {
    localStorage.setItem('esLectura', false);
    window.location.href = `/checklist/${id}`;
}

// Función para redirigir a la página de lectura de la visita usando el ID
function leerVisita(id) {
    localStorage.setItem('esLectura', true);
    window.location.href = `/visita/${id}`;
}

// Función para redirigir a la página de edición de la visita usando el ID
function editarVisita(id) {
    localStorage.setItem('esLectura', false);
    window.location.href = `/visita/${id}`;
}

// Función para eliminar la visita con el ID proporcionado
function eliminarVisita(id) {
    fetch(`http://localhost:8080/api/visitas/delete/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al eliminar el cliente.');
            }
            // Actualizar la tabla después de eliminar la visita
            limpiarTabla();
            cargarVisitas();
        })
        .catch(error => {
            console.error('Error al eliminar la visita', error);
        });
    function limpiarTabla() {
        const tableBody = document.querySelector('#visitasTable tbody');
        tableBody.innerHTML = ''; // Vaciar el contenido del cuerpo de la tabla
    }
}