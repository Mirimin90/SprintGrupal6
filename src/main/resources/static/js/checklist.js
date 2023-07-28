// checklist.js

// Función para obtener y mostrar los detalles del checklist por su ID
function Checklist(checklistId) {
    document.getElementById('visita_id').value = checklistId;

    // Realizar la solicitud Fetch para obtener la lista de checklists
    fetch('http://localhost:8080/api/checklist')
        .then(response => {
            if (!response.ok) {
                throw new Error('La solicitud ha fallado. Código de estado: ' + response.status);
            }
            return response.json();
        })
        .then(checklists => {
            // Filtrar el checklist que tenga el mismo visitaID que el proporcionado
            const checklist = checklists.find(checklist => checklist.visitaId === parseInt(checklistId));

            if (!checklist) {
                // Si no se encuentra un checklist con el visitaId proporcionado, manejar el caso aquí
                console.log('No se encontró un checklist con el visitaId proporcionado.');
                // Dejar el botón "Modificar" oculto
                document.getElementById('modificarBtn').classList.add('d-none');
                return;
            }

            // Mostrar los detalles del checklist en el formulario
            document.getElementById('id').value = checklist.id;
            document.getElementById('visita_id').value = checklist.visitaId;
            document.getElementById('detalle').value = checklist.detalle;
            document.getElementById('estado').value = checklist.estado;

            // Ocultar el botón "Guardar" y mostrar el botón "Modificar"
            document.getElementById('guardarBtn').classList.add('d-none');
            document.getElementById('modificarBtn').classList.remove('d-none');
        })
        .catch(error => {
            console.error('Ha ocurrido un error:', error);
        });
}

// Función para crear un nuevo checklist
function CrearChecklist() {
    document.getElementById('formularioChecklist').addEventListener('submit', function(event) {
        event.preventDefault(); // Evita el envío normal del formulario

        // Obtener los datos del formulario
        const data = {
            visitaId : document.getElementById('visita_id').value,
            detalle : document.getElementById('detalle').value,
            estado : document.getElementById('estado').value,
        };

        // Realizar la solicitud Fetch para crear un nuevo pago
        fetch('http://localhost:8080/api/checklist/create', {
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
            .then(checklistDTO => {
                // Manejo de la respuesta
                console.log('ChecklistDTO creado:', checklistDTO);
                // Redirigir a la página de pagoList después de guardar correctamente
                window.location.href = '/visitaList';
            })
            .catch(error => {
                // Manejo de errores
                console.error('Error en la solicitud:', error);
            });
    });
}

// Función para actualizar un pago existente
function ActualizarChecklist(data) {
    fetch('http://localhost:8080/api/checklist/update', {
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
        .then(checklistDTO => {
            // Manejo de la respuesta
            console.log('ChecklistDTO actualizado:', checklistDTO);
            // Redirigir a la página de pagoList después de actualizar correctamente
            window.location.href = '/visitaList';
            // Aquí puedes realizar acciones adicionales con la respuesta del servidor si es necesario
        })
        .catch(error => {
            console.error('Error en la solicitud de actualización:', error);
        });
}