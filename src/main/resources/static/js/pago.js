// pago.js

// Función para formatear la fecha en formato 'yyyy-MM-dd'
function formatDate(dateString) {
  const date = new Date(dateString);
  const day = String(date.getDate()).padStart(2, '0');
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const year = date.getFullYear();
  return `${year}-${month}-${day}`;
}

// Función para obtener y mostrar los detalles del pago por su ID
function Pago(pagoId) {
    fetch(`http://localhost:8080/api/pagos/${pagoId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('La solicitud ha fallado.');
            }
            return response.json();
        })
        .then(pago => {
            if (localStorage.getItem('esLectura') === 'true') {
                // Los detalles son de solo lectura, bloquear los campos
                document.getElementById('id').readOnly = true;
                document.getElementById('clienteId').readOnly = true;
                document.getElementById('monto').readOnly = true;
                document.getElementById('fechaPago').readOnly = true;

                // Ocultar el botón de guardar y mostrar el botón de modificar
                document.getElementById('guardarBtn').classList.add('d-none');
                document.getElementById('modificarBtn').classList.add('d-none');
            } else {
                // Los detalles son editables, no bloquear los campos
                document.getElementById('id').readOnly = false;
                document.getElementById('clienteId').readOnly = false;
                document.getElementById('monto').readOnly = false;
                document.getElementById('fechaPago').readOnly = false;
                // Ocultar el botón de modificar y mostrar el botón de guardar

                document.getElementById('guardarBtn').classList.add('d-none');
                document.getElementById('modificarBtn').classList.remove('d-none');
            }

            // Mostrar los detalles del pago en el formulario
            document.getElementById('id').value = pago.id;
            document.getElementById('clienteId').value = pago.clienteId;
            document.getElementById('monto').value = pago.monto;
            document.getElementById('fechaPago').value = formatDate(pago.fechaPago);

        })
        .catch(error => {
            console.error('Ha ocurrido un error:', error);
        });
}

// Función para crear un nuevo pago
function CrearPago() {
    document.getElementById('formularioPago').addEventListener('submit', function(event) {
        event.preventDefault(); // Evita el envío normal del formulario

        // Obtener los datos del formulario
        const data = {
            clienteId: document.getElementById('clienteId').value,
            monto: document.getElementById('monto').value,
            fechaPago: document.getElementById('fechaPago').value,
        };

        // Realizar la solicitud Fetch para crear un nuevo pago
        fetch('http://localhost:8080/api/pagos/create', {
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
            .then(pagoDTO => {
                // Manejo de la respuesta
                console.log('PagoDTO creado:', pagoDTO);
                // Redirigir a la página de pagoList después de guardar correctamente
                window.location.href = '/pagoList';
            })
            .catch(error => {
                // Manejo de errores
                console.error('Error en la solicitud:', error);
            });
    });
}

// Función para actualizar un pago existente
function ActualizarPago(data) {
    fetch('http://localhost:8080/api/pagos/update', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al actualizar los datos del pago.');
            }
            return response.json();
        })
        .then(pagoDTO => {
            // Manejo de la respuesta
            console.log('PagoDTO actualizado:', pagoDTO);
            // Redirigir a la página de pagoList después de actualizar correctamente
            window.location.href = '/pagoList';
            // Aquí puedes realizar acciones adicionales con la respuesta del servidor si es necesario
        })
        .catch(error => {
            console.error('Error en la solicitud de actualización:', error);
        });
}