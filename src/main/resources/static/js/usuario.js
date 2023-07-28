// usuario.js

// Función para obtener y mostrar los detalles del usuario por su ID
function Usuario(usuarioId) {
  fetch(`http://localhost:8080/api/usuarios/${usuarioId}`)
    .then(response => {
      if (!response.ok) {
        throw new Error('La solicitud ha fallado.');
      }
      return response.json();
    })
    .then(usuario => {
      if (localStorage.getItem('esLectura') === 'true') {
        // Los detalles son de solo lectura, bloquear los campos
        document.getElementById('id').readOnly = true;
        document.getElementById('nombre').readOnly = true;
        document.getElementById('username').readOnly = true;
        document.getElementById('password').readOnly = true;

        // Ocultar el botón de guardar y mostrar el botón de modificar
        document.getElementById('guardarBtn').classList.add('d-none');
        document.getElementById('modificarBtn').classList.add('d-none');
      } else {
        // Los detalles son editables, no bloquear los campos
        document.getElementById('id').readOnly = false;
        document.getElementById('nombre').readOnly = false;
        document.getElementById('username').readOnly = false;
        document.getElementById('password').readOnly = false;
        // Ocultar el botón de modificar y mostrar el botón de guardar

        document.getElementById('guardarBtn').classList.add('d-none');
        document.getElementById('modificarBtn').classList.remove('d-none');
      }

      // Mostrar los detalles de la capacitación en el formulario
      document.getElementById('id').value = usuario.id;
      document.getElementById('nombre').value = usuario.nombre;
      document.getElementById('username').value = usuario.username;
      document.getElementById('password').value = usuario.password;
    })
    .catch(error => {
      console.error('Ha ocurrido un error:', error);
    });
}

// Función para crear una nueva capacitación
function CrearUsuario() {
  document.getElementById('formularioUsuario').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío normal del formulario

    // Obtener los datos del formulario
    const data = {
      nombre: document.getElementById('nombre').value,
      username: document.getElementById('username').value,
      password: document.getElementById('password').value,
    };

    // Realizar la solicitud Fetch para crear una nueva capacitación
    fetch('http://localhost:8080/api/usuarios/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Error al enviar los datos.');
        }
        return response.json();
      })
      .then(usuarioDTO => {
        // Manejo de la respuesta
        console.log('UsuarioDTO creada:', usuarioDTO);
        // Redirigir a la página de capacitaciónList después de guardar correctamente
        window.location.href = '/usuarioList';
      })
      .catch(error => {
        // Manejo de errores
        console.error('Error en la solicitud:', error);
      });
  });
}

// Función para actualizar una usuario existente
function ActualizarUsuario(data) {
  fetch('http://localhost:8080/api/usuarios/update', {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Error al actualizar los datos de la capacitación.');
      }
      return response.json();
    })
    .then(usuarioDTO => {
      // Manejo de la respuesta
      console.log('UsuarioDTO actualizado:', usuarioDTO);
      // Redirigir a la página de capacitaciónList después de actualizar correctamente
      window.location.href = '/usuarioList';
      // Aquí puedes realizar acciones adicionales con la respuesta del servidor si es necesario
    })
    .catch(error => {
      console.error('Error en la solicitud de actualización:', error);
    });
}
