// cliente.js

// Función para obtener y mostrar los detalles del usuario por su ID
function Cliente(clienteId) {
  fetch(`http://localhost:8080/api/clientes/${clienteId}`)
    .then(response => {
      if (!response.ok) {
        throw new Error('La solicitud ha fallado.');
      }
      return response.json();
    })
    .then(cliente => {
      if (localStorage.getItem('esLectura') === 'true') {
        // Los detalles son de solo lectura, bloquear los campos

        document.getElementById("id").readOnly = true;
        document.getElementById("run").readOnly = true;
        document.getElementById("nombre").readOnly = true;
        document.getElementById("apellido").readOnly = true;
        document.getElementById("correo").readOnly = true;
        document.getElementById("telefono").readOnly = true;
        document.getElementById("afp").readOnly = true;
        document.getElementById("salud").readOnly = true;
        document.getElementById("direccion").readOnly = true;
        document.getElementById("comuna").readOnly = true;
        document.getElementById("edad").readOnly = true;


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
        document.getElementById("afp").readOnly = false;
        document.getElementById("salud").readOnly = false;
        document.getElementById("direccion").readOnly = false;
        document.getElementById("comuna").readOnly = false;
        document.getElementById("edad").readOnly = false;
        // Ocultar el botón de modificar y mostrar el botón de guardar

        document.getElementById('guardarBtn').classList.add('d-none');
        document.getElementById('modificarBtn').classList.remove('d-none');
      }

      // Mostrar los detalles del cliente en el formulario
      document.getElementById("id").value = cliente.id;
      document.getElementById("run").value = cliente.rut;
      document.getElementById("nombre").value = cliente.nombre;
      document.getElementById("apellido").value = cliente.apellido;
      document.getElementById("correo").value = cliente.correo;
      document.getElementById("telefono").value = cliente.telefono;
      document.getElementById("afp").value = cliente.afp;
      document.getElementById("salud").value = cliente.sistemaSalud;
      document.getElementById("direccion").value = cliente.direccion;
      document.getElementById("comuna").value = cliente.comuna;
      document.getElementById("edad").value = cliente.edad;
    })
    .catch(error => {
      console.error('Ha ocurrido un error:', error);
    });
}

// Función para crear un nuevo cliente
function CrearCliente() {
  document.getElementById('formularioCliente').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío normal del formulario

    // Obtener los datos del formulario
    const data = {
      rut : document.getElementById("run").value,
      nombre: document.getElementById("nombre").value,
      apellido: document.getElementById("apellido").value,
      correo: document.getElementById("correo").value,
      telefono: document.getElementById("telefono").value,
      afp: document.getElementById("afp").value,
      sistemaSalud: document.getElementById("salud").value,
      direccion: document.getElementById("direccion").value,
      comuna: document.getElementById("comuna").value,
      edad: document.getElementById("edad").value,
    };

    // Realizar la solicitud Fetch para crear un nuevo cliente
    fetch('http://localhost:8080/api/clientes/create', {
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
      .then(clienteDTO => {
        // Manejo de la respuesta
        console.log('ClienteDTO creada:', clienteDTO);
        // Redirigir a la página de clienteList después de guardar correctamente
        window.location.href = '/clienteList';
      })
      .catch(error => {
        // Manejo de errores
        console.error('Error en la solicitud:', error);
      });
  });
}

// Función para actualizar una cliente existente
function ActualizarCliente(data) {
  fetch('http://localhost:8080/api/clientes/update', {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Error al actualizar los datos del cliente.');
      }
      return response.json();
    })
    .then(clienteDTO => {
      // Manejo de la respuesta
      console.log('ClienteDTO actualizado:', clienteDTO);
      // Redirigir a la página de clienteList después de actualizar correctamente
      window.location.href = '/clienteList';
      // Aquí puedes realizar acciones adicionales con la respuesta del servidor si es necesario
    })
    .catch(error => {
      console.error('Error en la solicitud de actualización:', error);
    });
}
function llenarComboBox() {
  // Obtener una referencia al combobox
  const miComboBox = document.getElementById('miComboBox');

  // URL de la fuente de datos (por ejemplo, una API o un archivo JSON)
  const urlFuenteDatos = 'http://localhost:8080/api/clientes';

  // Realizar la solicitud fetch para obtener los datos
  fetch(urlFuenteDatos)
    .then(response => response.json())
    .then(data => {
      // Iterar sobre los datos obtenidos y agregar opciones al combobox
      data.forEach(item => {
        const option = document.createElement('option');
        option.value = item.apellido; // Asignar el valor del elemento
        option.textContent = item.apellido; // Asignar el texto visible del elemento
        miComboBox.appendChild(option); // Agregar la opción al combobox
      });
    })
    .catch(error => {
      console.error('Error al obtener los datos:', error);
    });
}