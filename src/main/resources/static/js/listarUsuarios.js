// Función para crear una fila de la tabla
function createTableRow(usuario) {
  const newRow = document.createElement('tr');
  newRow.innerHTML = `
    <td>${usuario.id}</td>
    <td>${usuario.nombre}</td>
    <td>${usuario.username}</td>
    <td>
      <a href="#" data-usuario-id="${usuario.id}" data-lectura="true" class="fas fa-eye btn-ver-detalles"></a>
      <a href="#" data-usuario-id="${usuario.id}" data-lectura="false" class="fas fa-pen-to-square btn-editar"></a>
      <a href="#" data-usuario-id="${usuario.id}" class="fas fa-trash btn-eliminar"></a>
    </td>`;
  return newRow;
}

//Función para cargar los datos de usuario en la tabla
function cargarUsuarios() {
  const tableBody = document.querySelector('#usuariosTable tbody');
  fetch('http://localhost:8080/api/usuarios')
    .then(response => {
      if (!response.ok) {
        throw new Error('La solicitud ha fallado.');
      }
      return response.json();
    })
    .then(data => {
      data.forEach(usuario => {
        const newRow = createTableRow(usuario);
        tableBody.appendChild(newRow);
      });
    })
    .catch(error => {
      console.error('Ha ocurrido un error:', error);
    });
}

// Cargar los datos al cargar la página
document.addEventListener("DOMContentLoaded", cargarUsuarios);

// Evento de clic para ver los detalles de la capacitación
document.addEventListener("click", function(event) {
  if (event.target.classList.contains("btn-ver-detalles")) {
    event.preventDefault();
    const usuarioId = event.target.getAttribute("data-usuario-id");
    leerUsuario(usuarioId);

  } else if (event.target.classList.contains("btn-editar")) {
    event.preventDefault();
    const usuarioId = event.target.getAttribute("data-usuario-id");
    editarUsuario(usuarioId);

  } else if (event.target.classList.contains("btn-eliminar")) {
    event.preventDefault();
    const usuarioId = event.target.getAttribute("data-usuario-id");
    eliminarUsuario(usuarioId);
  }
});

// Función para redirigir a la página de lectura de la capacitación usando el ID
function leerUsuario(id) {
    localStorage.setItem('esLectura', true);
  window.location.href = `/usuario/${id}`;
}

// Función para redirigir a la página de edición de la capacitación usando el ID
function editarUsuario(id) {
    localStorage.setItem('esLectura', false);
  window.location.href = `/usuario/${id}`;
}

// Función para eliminar la capacitación con el ID proporcionado
function eliminarUsuario(id) {
  fetch(`http://localhost:8080/api/usuarios/delete/${id}`, {
    method: 'DELETE'
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Error al eliminar al usuario.');
    }
    // Actualizar la tabla después de eliminar al usuario
    limpiarTabla();
    cargarUsuarios();
  })
  .catch(error => {
    console.error('Error al eliminar el usuario', error);
  });
  function limpiarTabla() {
    const tableBody = document.querySelector('#usuariosTable tbody');
    tableBody.innerHTML = ''; // Vaciar el contenido del cuerpo de la tabla
  }
}
