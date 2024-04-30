package DAWI.ProyectoTiendaD.TODO.controller;

import DAWI.ProyectoTiendaD.TODO.model.bd.Producto;
import DAWI.ProyectoTiendaD.TODO.service.IProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/producto")
public class ProductoController {
    private IProductoService IProductoService;

    @GetMapping("")
    public String listarProductos(Model model){
        model.addAttribute("listarproductos" ,
                IProductoService.listarProductos());
        return "formproductos";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Producto> listPublicaciones(){
        return IProductoService.listarProductos();
    }

}
