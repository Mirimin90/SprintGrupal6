// capacitaciones.js

// Función para formatear la fecha en formato 'yyyy-MM-dd'
function formatDate(dateString) {
  const date = new Date(dateString);
  const day = String(date.getDate()).padStart(2, '0');
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const year = date.getFullYear();
  return `${year}-${month}-${day}`;
}
// Función para formatear la hora en formato 'HH:mm'
function formatTime(timeString) {
  const [hours, minutes] = timeString.split(':');
  return `${hours}:${minutes}`;
}

// Función para obtener y mostrar los detalles de la capacitación por su ID
function Capacitacion(capacitacionId) {
  fetch(`http://localhost:8080/api/listadoCapacitaciones/${capacitacionId}`)
    .then(response => {
      if (!response.ok) {
        throw new Error('La solicitud ha fallado.');
      }
      return response.json();
    })
    .then(capacitacion => {
      if (localStorage.getItem('esLectura') === 'true') {
        // Los detalles son de solo lectura, bloquear los campos
        document.getElementById('id').readOnly = true;
        document.getElementById('nombre').readOnly = true;
        document.getElementById('detalle').readOnly = true;
        document.getElementById('fecha').readOnly = true;
        document.getElementById('hora').readOnly = true;
        document.getElementById('lugar').readOnly = true;
        document.getElementById('duracion').readOnly = true;
        document.getElementById('cantidad').readOnly = true;

        // Ocultar el botón de guardar y mostrar el botón de modificar
        document.getElementById('guardarBtn').classList.add('d-none');
        document.getElementById('modificarBtn').classList.add('d-none');
      } else {
        // Los detalles son editables, no bloquear los campos
        document.getElementById('id').readOnly = false;
        document.getElementById('nombre').readOnly = false;
        document.getElementById('detalle').readOnly = false;
        document.getElementById('fecha').readOnly = false;
        document.getElementById('hora').readOnly = false;
        document.getElementById('lugar').readOnly = false;
        document.getElementById('duracion').readOnly = false;
        document.getElementById('cantidad').readOnly = false;

        // Ocultar el botón de modificar y mostrar el botón de guardar

        document.getElementById('guardarBtn').classList.add('d-none');
        document.getElementById('modificarBtn').classList.remove('d-none');
      }

      // Mostrar los detalles de la capacitación en el formulario
      document.getElementById('id').value = capacitacion.id;
      document.getElementById('nombre').value = capacitacion.nombre;
      document.getElementById('detalle').value = capacitacion.detalle;
      document.getElementById('fecha').value = formatDate(capacitacion.fecha);
      document.getElementById('hora').value = capacitacion.hora;
      document.getElementById('lugar').value = capacitacion.lugar;
      document.getElementById('duracion').value = capacitacion.duracion;
      document.getElementById('cantidad').value = capacitacion.cantidad;
    })
    .catch(error => {
      console.error('Ha ocurrido un error:', error);
    });
}

// Función para crear una nueva capacitación
function CrearCapacitacion() {
  document.getElementById('formularioCapacitacion').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío normal del formulario

    // Obtener los datos del formulario
    const data = {
      nombre: document.getElementById('nombre').value,
      detalle: document.getElementById('detalle').value,
      fecha: document.getElementById('fecha').value,
      hora: document.getElementById('hora').value,
      lugar: document.getElementById('lugar').value,
      duracion: document.getElementById('duracion').value,
      cantidad: document.getElementById('cantidad').value,
    };

    // Realizar la solicitud Fetch para crear una nueva capacitación
    fetch('http://localhost:8080/api/listadoCapacitaciones/create', {
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
      .then(capacitacionDTO => {
        // Manejo de la respuesta
        console.log('CapacitacionDTO creada:', capacitacionDTO);
        // Redirigir a la página de capacitaciónList después de guardar correctamente
        window.location.href = '/capacitacionList';
      })
      .catch(error => {
        // Manejo de errores
        console.error('Error en la solicitud:', error);
      });
  });
}

// Función para actualizar una capacitación existente
function ActualizarCapacitacion(data) {
  fetch('http://localhost:8080/api/listadoCapacitaciones/update', {
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
    .then(capacitacionDTO => {
      // Manejo de la respuesta
      console.log('CapacitacionDTO actualizada:', capacitacionDTO);
      // Redirigir a la página de capacitaciónList después de actualizar correctamente
      window.location.href = '/capacitacionList';
      // Aquí puedes realizar acciones adicionales con la respuesta del servidor si es necesario
    })
    .catch(error => {
      console.error('Error en la solicitud de actualización:', error);
    });
}
