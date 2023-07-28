// profesional.js

// Función para obtener y mostrar los detalles del profesional por su ID
function Profesional(profesionalId) {
  fetch(`http://localhost:8080/api/profesionales/${profesionalId}`)
    .then(response => {
      if (!response.ok) {
        throw new Error('La solicitud ha fallado.');
      }
      return response.json();
    })
    .then(profesional => {
      if (localStorage.getItem('esLectura') === 'true') {
        // Los detalles son de solo lectura, bloquear los campos

        document.getElementById("id").readOnly = true;
        document.getElementById("run").readOnly = true;
        document.getElementById("nombre").readOnly = true;
        document.getElementById("apellido").readOnly = true;
        document.getElementById("correo").readOnly = true;
        document.getElementById("telefono").readOnly = true;
        document.getElementById("cargo").readOnly = true;

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
                document.getElementById("telefono").readOnly = false;
                document.getElementById("cargo").readOnly = false;

        // Ocultar el botón de modificar y mostrar el botón de guardar
        document.getElementById('guardarBtn').classList.add('d-none');
        document.getElementById('modificarBtn').classList.remove('d-none');
      }

      // Mostrar los detalles del cliente en el formulario
      document.getElementById("id").value = profesional.id;
      document.getElementById("run").value = profesional.run;
      document.getElementById("nombre").value = profesional.nombre;
      document.getElementById("apellido").value = profesional.apellido;
      document.getElementById("correo").value = profesional.correo;
      document.getElementById("telefono").value = profesional.telefono;
      document.getElementById("cargo").value = profesional.cargo;
    })
    .catch(error => {
      console.error('Ha ocurrido un error:', error);
    });
}

// Función para crear un nuevo cliente
function CrearProfesional() {
  document.getElementById('formularioProfesional').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío normal del formulario

    // Obtener los datos del formulario
    const data = {
      run : document.getElementById("run").value,
      nombre: document.getElementById("nombre").value,
      apellido: document.getElementById("apellido").value,
      correo: document.getElementById("correo").value,
      telefono: document.getElementById("telefono").value,
      cargo: document.getElementById("cargo").value,
     };

    // Realizar la solicitud Fetch para crear un nuevo profesional
    fetch('http://localhost:8080/api/profesionales/create', {
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
      .then(profesionalDTO => {
        // Manejo de la respuesta
        console.log('ProfesionalDTO creada:', profesionalDTO);
        // Redirigir a la página de profesionalList después de guardar correctamente
        window.location.href = '/profesionalList';
      })
      .catch(error => {
        // Manejo de errores
        console.error('Error en la solicitud:', error);
      });
  });
}

// Función para actualizar una profesional existente
function ActualizarProfesional(data) {
  fetch('http://localhost:8080/api/profesionales/update', {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Error al actualizar los datos del profesional.');
      }
      return response.json();
    })
    .then(profesionalDTO => {
      // Manejo de la respuesta
      console.log('ProfesionalDTO actualizado:', profesionalDTO);
      // Redirigir a la página de profesionalList después de actualizar correctamente
      window.location.href = '/profesionalList';
      // Aquí puedes realizar acciones adicionales con la respuesta del servidor si es necesario
    })
    .catch(error => {
      console.error('Error en la solicitud de actualización:', error);
    });
}
