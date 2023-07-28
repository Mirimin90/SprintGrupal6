// Función para crear una fila de la tabla
function createTableRow(cliente) {
  const newRow = document.createElement('tr');
  newRow.innerHTML = `
    <td>${cliente.id}</td>
    <td>${cliente.rut}</td>
    <td>${cliente.nombre}</td>
    <td>${cliente.apellido}</td>
    <td>${cliente.correo}</td>
    <td>${cliente.telefono}</td>
    <td>${cliente.afp}</td>
    <td>${cliente.sistemaSalud}</td>
    <td>${cliente.direccion}</td>
    <td>${cliente.comuna}</td>
    <td>${cliente.edad}</td>
    <td>
      <a href="#" data-cliente-id="${cliente.id}" data-lectura="true" class="fas fa-eye btn-ver-detalles"></a>
      <a href="#" data-cliente-id="${cliente.id}" data-lectura="false" class="fas fa-pen-to-square btn-editar"></a>
      <a href="#" data-cliente-id="${cliente.id}" class="fas fa-trash btn-eliminar"></a>
    </td>`;
  return newRow;
}

//Función para cargar los datos de usuario en la tabla
function cargarClientes() {
  const tableBody = document.querySelector('#clientesTable tbody');
  fetch('http://localhost:8080/api/clientes')
    .then(response => {
      if (!response.ok) {
        throw new Error('La solicitud ha fallado.');
      }
      return response.json();
    })
    .then(data => {
      data.forEach(cliente => {
        const newRow = createTableRow(cliente);
        tableBody.appendChild(newRow);
      });
    })
    .catch(error => {
      console.error('Ha ocurrido un error:', error);
    });
}

// Cargar los datos al cargar la página
document.addEventListener("DOMContentLoaded", cargarClientes);

// Evento de clic para ver los detalles de la capacitación
document.addEventListener("click", function(event) {
  if (event.target.classList.contains("btn-ver-detalles")) {
    event.preventDefault();
    const clienteId = event.target.getAttribute("data-cliente-id");
    leerCliente(clienteId);

  } else if (event.target.classList.contains("btn-editar")) {
    event.preventDefault();
    const clienteId = event.target.getAttribute("data-cliente-id");
    editarCliente(clienteId);

  } else if (event.target.classList.contains("btn-eliminar")) {
    event.preventDefault();
    const clienteId = event.target.getAttribute("data-cliente-id");
    eliminarCliente(clienteId);
  }
});

// Función para redirigir a la página de lectura de la capacitación usando el ID
function leerCliente(id) {
    localStorage.setItem('esLectura', true);
  window.location.href = `/cliente/${id}`;
}

// Función para redirigir a la página de edición de la capacitación usando el ID
function editarCliente(id) {
    localStorage.setItem('esLectura', false);
  window.location.href = `/cliente/${id}`;
}

// Función para eliminar la capacitación con el ID proporcionado
function eliminarCliente(id) {
  fetch(`http://localhost:8080/api/clientes/delete/${id}`, {
    method: 'DELETE'
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Error al eliminar el cliente.');
    }
    // Actualizar la tabla después de eliminar al usuario
    limpiarTabla();
    cargarClientes();
  })
  .catch(error => {
    console.error('Error al eliminar el cliente', error);
  });
  function limpiarTabla() {
    const tableBody = document.querySelector('#clientesTable tbody');
    tableBody.innerHTML = ''; // Vaciar el contenido del cuerpo de la tabla
  }
}