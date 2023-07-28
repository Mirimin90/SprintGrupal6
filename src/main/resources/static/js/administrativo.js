// administrativo.js

// Función para obtener y mostrar los detalles del administrativo por su ID
function Administrativo(administrativoId) {
  if (!administrativoId){
  return;
  }
  fetch(`http://localhost:8080/api/administrativos/${administrativoId}`)
    .then(response => {
      if (!response.ok) {
        throw new Error('La solicitud ha fallado.');
      }
      return response.json();
    })
    .then(administrativo => {
      if (localStorage.getItem('esLectura') === 'true') {
        // Los detalles son de solo lectura, bloquear los campos

        document.getElementById("id").readOnly = true;
        document.getElementById("run").readOnly = true;
        document.getElementById("nombre").readOnly = true;
        document.getElementById("apellido").readOnly = true;
        document.getElementById("correo").readOnly = true;
        document.getElementById("area").readOnly = true;

        // Ocultar el botón de guardar y mostrar el botón de modificar
        document.getElementById('guardarBtn').classList.add('d-none');
        document.getElementById('modificarBtn').classList.add('d-none');
      } else {
        // Los detalles son editables, no bloquear los campos
        document.getElementById("id").readOnly = false;
        document.getElementById("run").readOnly = false;
        document.getElementById("nombre").readOnly = false;
        document.getElementById("apellido").readOnly = false;
        document.getElementById("correo").readOnly = false;
        document.getElementById("area").readOnly = false;

        // Ocultar el botón de modificar y mostrar el botón de guardar
        document.getElementById('guardarBtn').classList.add('d-none');
        document.getElementById('modificarBtn').classList.remove('d-none');
      }

      // Mostrar los detalles del cliente en el formulario
      document.getElementById("id").value = administrativo.id;
      document.getElementById("run").value = administrativo.run;
      document.getElementById("nombre").value = administrativo.nombre;
      document.getElementById("apellido").value = administrativo.apellido;
      document.getElementById("correo").value = administrativo.correo;
      document.getElementById("area").value = administrativo.area;
    })
    .catch(error => {
      console.error('Ha ocurrido un error:', error);
    });
}

// Función para crear un nuevo cliente
function CrearAdministrativo() {
  document.getElementById('formularioAdministrativo').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío normal del formulario

    // Obtener los datos del formulario
    const data = {
      run : document.getElementById("run").value,
      nombre: document.getElementById("nombre").value,
      apellido: document.getElementById("apellido").value,
      correo: document.getElementById("correo").value,
      area: document.getElementById("area").value,
     };
    console.log(JSON.stringify(data));
    // Realizar la solicitud Fetch para crear un nuevo administrativo
    fetch('http://localhost:8080/api/administrativos/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })
      .then(response => {

        console.log(response);
/*        if (!response.ok) {
          throw new Error('Error al enviar los datos.', response);
        }*/
        return response.json();
      })
      .then(administrativoDTO => {
        // Manejo de la respuesta
        console.log('AdministrativoDTO creada:', administrativoDTO);
        // Redirigir a la página de administrativoList después de guardar correctamente
        window.location.href = '/administrativoList';
      })
      .catch(error => {
        // Manejo de errores
        console.error('Error en la solicitud:', error);
      });
  });
}

// Función para actualizar un administrativo existente
function ActualizarAdministrativo(data) {
  fetch('http://localhost:8080/api/administrativos/update', {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Error al actualizar los datos del administrativo.');
      }
      return response.json();
    })
    .then(administrativoDTO => {
      // Manejo de la respuesta
      console.log('AdministrativoDTO actualizado:', administrativoDTO);
      // Redirigir a la página de administrativoList después de actualizar correctamente
      window.location.href = '/administrativoList';
      // Aquí puedes realizar acciones adicionales con la respuesta del servidor si es necesario
    })
    .catch(error => {
      console.error('Error en la solicitud de actualización:', error);
    });
}
