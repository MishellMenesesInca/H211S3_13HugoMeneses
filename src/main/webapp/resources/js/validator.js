
function validarDNII() {
    var dni = document.getElementById("DNI").value;
    if (dni.length === 8) {
        PF('btnregistrarvalidator').enable();
    } else {
        PF('btnregistrarvalidator').disable();
    }
}

function validarCELULAR() {
    var celular = document.getElementById("CEL").value;
    if (celular.length === 9) {
        PF('btnregistrarvalidator').enable();
    } else {
        PF('btnregistrarvalidator').disable();
    }
}

