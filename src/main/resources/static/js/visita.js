// visita.js
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

// Función para obtener y mostrar los detalles de visita por su ID
function Visita(visitaId) {
    fetch(`http://localhost:8080/api/visitas/${visitaId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('La solicitud ha fallado.');
            }
            return response.json();
        })
        .then(visita => {
            if (localStorage.getItem('esLectura') === 'true') {
                // Los detalles son de solo lectura, bloquear los campos

                document.getElementById("id").readOnly = true;
                document.getElementById("clienteId").readOnly = true;
                document.getElementById("fecha").readOnly = true;
                document.getElementById("hora").readOnly = true;
                document.getElementById("lugar").readOnly = true;
                document.getElementById("realizado").readOnly = true;
                document.getElementById("detalle").readOnly = true;

                // Ocultar el botón de guardar y mostrar el botón de modificar
                document.getElementById('guardarBtn').classList.add('d-none');
                document.getElementById('modificarBtn').classList.add('d-none');
            } else {
                // Los detalles son editables, no bloquear los campos
                document.getElementById("id").readOnly = false;
                document.getElementById("clienteId").readOnly = false;
                document.getElementById("fecha").readOnly = false;
                document.getElementById("hora").readOnly = false;
                document.getElementById("lugar").readOnly = false;
                document.getElementById("realizado").readOnly = false;
                document.getElementById("detalle").readOnly = false;
                // Ocultar el botón de modificar y mostrar el botón de guardar

                document.getElementById('guardarBtn').classList.add('d-none');
                document.getElementById('modificarBtn').classList.remove('d-none');
            }

            // Mostrar los detalles de la visita en el formulario
            document.getElementById("id").value = visita.id;
            document.getElementById("clienteId").value = visita.clienteId;
            document.getElementById("fecha").value = visita.fecha;
            document.getElementById("hora").value = visita.hora;
            document.getElementById("lugar").value = visita.lugar;
            document.getElementById("realizado").value = visita.realizado;
            document.getElementById("detalle").value = visita.detalle;
        })
        .catch(error => {
            console.error('Ha ocurrido un error:', error);
        });
}

// Función para crear una nueva visita
function CrearVisita() {
    document.getElementById('formularioVisita').addEventListener('submit', function(event) {
        event.preventDefault(); // Evita el envío normal del formulario

        // Obtener los datos del formulario
        const data = {
            clienteId : document.getElementById("clienteId").value,
            fecha: document.getElementById("fecha").value,
            hora: document.getElementById("hora").value,
            lugar: document.getElementById("lugar").value,
            realizado: document.getElementById("realizado").value,
            detalle: document.getElementById("detalle").value,
        };

        // Realizar la solicitud Fetch para crear una nueva visita
        fetch('http://localhost:8080/api/visitas/create', {
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
            .then(visitaDTO => {
                // Manejo de la respuesta
                console.log('VisitaDTO creada:', visitaDTO);
                // Redirigir a la página de visitaList después de guardar correctamente
                window.location.href = '/visitaList';
            })
            .catch(error => {
                // Manejo de errores
                console.error('Error en la solicitud:', error);
            });
    });
}

// Función para actualizar una cliente existente
function ActualizarVisita(data) {
    fetch('http://localhost:8080/api/visitas/update', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al actualizar los datos de la visita.');
            }
            return response.json();
        })
        .then(visitaDTO => {
            // Manejo de la respuesta
            console.log('VisitaDTO actualizado:', visitaDTO);
            // Redirigir a la página de visitaList después de actualizar correctamente
            window.location.href = '/visitaList';
            // Aquí puedes realizar acciones adicionales con la respuesta del servidor si es necesario
        })
        .catch(error => {
            console.error('Error en la solicitud de actualización:', error);
        });
}