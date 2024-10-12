const deleteUser = (url) => {
    Swal.fire({
        title: '¿Estás seguro?',
        text: 'Esta acción no se puede deshacer',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Borrar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(url, {
                method: 'POST'
            })
                .then(response => {
                    if (response.ok) {
                        Swal.fire({
                            title: 'Eliminado',
                            text: 'La compañía ha sido eliminada',
                            icon: 'success'
                        }).then(() => {
                            window.location.reload();
                        });
                    } else {
                        Swal.fire('Error', 'No se pudo eliminar la compañía', 'error');
                    }
                })
                .catch(error => {
                    Swal.fire('Error', 'Ocurrió un problema al eliminar la compañía', 'error');
                    console.error('Error:', error);
                });
        }
    });
}
