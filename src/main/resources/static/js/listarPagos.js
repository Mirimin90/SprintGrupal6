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
async function createTableRow(pago) {
  const nombreCliente = await obtenerNombreClientePorID(pago.clienteId);
  const newRow = document.createElement('tr');
  newRow.innerHTML = `
    <td>${pago.id}</td>
    <td>${nombreCliente}</td>
    <td>${pago.monto}</td>
    <td>${formatDate(pago.fechaPago)}</td>
    <td>
      <a href="#" data-pago-id="${pago.id}" data-lectura="true" class="fas fa-eye btn-ver-detalles"></a>
      <a href="#" data-pago-id="${pago.id}" data-lectura="false" class="fas fa-pen-to-square btn-editar"></a>
      <a href="#" data-pago-id="${pago.id}" class="fas fa-trash btn-eliminar"></a>
    </td>`;
  return newRow;
}

//Función para cargar los datos de pago en la tabla
function cargarPagos() {
    const tableBody = document.querySelector('#pagosTable tbody');
      fetch('http://localhost:8080/api/pagos')
        .then(response => {
          if (!response.ok) {
            throw new Error('La solicitud ha fallado.');
          }
          return response.json();
        })
        .then(async data => {
          for (const pago of data) {
           //const nombreCliente = await obtenerNombreClientePorID(pago.id);
            const newRow =  await createTableRow(pago);
            tableBody.appendChild(newRow);
          }
        })
        .catch(error => {
          console.error('Ha ocurrido un error:', error);
        });
}
// Función para obtener el nombre del cliente por su ID
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
document.addEventListener("DOMContentLoaded", cargarPagos);

// Evento de clic para ver los detalles del pago
document.addEventListener("click", function(event) {
    if (event.target.classList.contains("btn-ver-detalles")) {
        event.preventDefault();
        const pagoId = event.target.getAttribute("data-pago-id");
        leerPago(pagoId);

    } else if (event.target.classList.contains("btn-editar")) {
        event.preventDefault();
        const pagoId = event.target.getAttribute("data-pago-id");
        editarPago(pagoId);

    } else if (event.target.classList.contains("btn-eliminar")) {
        event.preventDefault();
        const pagoId = event.target.getAttribute("data-pago-id");
        eliminarPago(pagoId);
    }
});

// Función para redirigir a la página de lectura del pago usando el ID
function leerPago(id) {
    localStorage.setItem('esLectura', true);
    window.location.href = `/pago/${id}`;
}

// Función para redirigir a la página de edición del pago usando el ID
function editarPago(id) {
    localStorage.setItem('esLectura', false);
    window.location.href = `/pago/${id}`;
}

// Función para eliminar el pago con el ID proporcionado
function eliminarPago(id) {
    fetch(`http://localhost:8080/api/pagos/delete/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al eliminar el pago.');
            }
            // Actualizar la tabla después de eliminar el pago
            limpiarTabla();
            cargarPagos();
        })
        .catch(error => {
            console.error('Error al eliminar el pago', error);
        });
    function limpiarTabla() {
        const tableBody = document.querySelector('#pagosTable tbody');
        tableBody.innerHTML = ''; // Vaciar el contenido del cuerpo de la tabla
    }
}


