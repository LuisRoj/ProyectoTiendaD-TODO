$(document).on("click", "#btnagregar", function(){
    $("#txtnombre").val("");
    $("#txtdesc").val("");
    $("#txtprecio").val("0");
    $("#txtstock").val("0");
    $("#txtmarca").val("");
    $("#txtimagen").val("");
    $("#hddproductoid").val("0");
    $("#modalproducto").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#txtnombre").val($(this).attr("data-produnom"));
    $("#txtdesc").val($(this).attr("data-produdesc"));
    $("#txtprecio").val($(this).attr("data-produprecio"));
    $("#txtstock").val($(this).attr("data-produstock"));
    $("#txtmarca").val($(this).attr("data-produmarca"));
    $("#txtimagen").val($(this).attr("data-produimg"));
    $("#modalproducto").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/producto/registrar",
        contentType: "application/json",
        data: JSON.stringify({
            idproducto: $("#hddproductoid").val(),
            nombre: $("#txtnombre").val(),
            descripcion: $("#txtdesc").val(),
            precio: $("#txtprecio").val(),
            stock: $("#txtstock").val(),
            marca: $("#txtmarca").val(),
            imagen: $("#txtimagen").val()
        }),
        success: function(resultado){
            alert(resultado.mensaje)
            location.reload();
        }
    });
    $("#modalproducto").modal("hide");
});

$(document).on("click", ".btneliminar", function() {
    var idproducto = $(this).data("produid");
    if (confirm("¿Estás seguro de que quieres eliminar este producto?")) {
        $.ajax({
            type: "DELETE",
            url: "/producto/eliminar/" + idproducto,
            success: function() {
                // Recargar la página o actualizar la tabla después de eliminar
                // Por ejemplo:
                location.reload();
            },
            error: function(xhr, status, error) {
                console.error("Error al eliminar el producto:", error);
            }
        });
    }
});