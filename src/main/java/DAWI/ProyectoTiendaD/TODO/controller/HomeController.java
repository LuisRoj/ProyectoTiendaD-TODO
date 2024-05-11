package DAWI.ProyectoTiendaD.TODO.controller;

import DAWI.ProyectoTiendaD.TODO.model.bd.DetallePedido;
import DAWI.ProyectoTiendaD.TODO.model.bd.Pedido;
import DAWI.ProyectoTiendaD.TODO.model.bd.Producto;
import DAWI.ProyectoTiendaD.TODO.model.bd.Usuario;
import DAWI.ProyectoTiendaD.TODO.service.IDetallePedidoService;
import DAWI.ProyectoTiendaD.TODO.service.IPedidoService;
import DAWI.ProyectoTiendaD.TODO.service.IProductoService;
import DAWI.ProyectoTiendaD.TODO.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoService productoService;
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IDetallePedidoService detallePedidoService;

    List<DetallePedido> detalles=new ArrayList<DetallePedido>();

    Pedido pedido = new Pedido();

    @GetMapping("/")
    public String home() {
        return "redirect:/producto/listado";
    }

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        log.info("Id producto enviado como parámetro {}", id);
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();

        model.addAttribute("producto", producto);

        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetallePedido detallePedido = new DetallePedido();
        Producto producto = new Producto();
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("Producto añadido: {}", optionalProducto.get());
        log.info("Cantidad: {}", cantidad);
        producto = optionalProducto.get();

        detallePedido.setCantidadpedido(cantidad);
        detallePedido.setPrecio_unitariopedido(producto.getPrecioproducto());
        detallePedido.setNombre(producto.getNomidproducto());
        detallePedido.setSubtotalpedido(producto.getPrecioproducto() * cantidad);
        detallePedido.setProducto(producto);

        //validar que le producto no se añada 2 veces
        Integer idProducto=producto.getIdproducto();
        boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getIdproducto()==idProducto);

        if (!ingresado) {
            detalles.add(detallePedido);
        }

        sumaTotal=detalles.stream().mapToDouble(dt->dt.getSubtotalpedido()).sum();
        pedido.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("pedido", pedido);



        return "usuario/carrito";
    }

    // quitar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Integer id, Model model) {

        // lista nueva de prodcutos
        List<DetallePedido> ordenesNueva = new ArrayList<DetallePedido>();

        for (DetallePedido detallePedido : detalles) {
            if (detallePedido.getProducto().getIdproducto()!= id) {
                ordenesNueva.add(detallePedido);
            }
        }

        // poner la nueva lista con los productos restantes
        detalles = ordenesNueva;

        double sumaTotal = 0;
        sumaTotal=detalles.stream().mapToDouble(dt->dt.getSubtotalpedido()).sum();
        pedido.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("pedido", pedido);

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {

        model.addAttribute("cart", detalles);
        model.addAttribute("pedido", pedido);

        //sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "/usuario/carrito";
    }

    @GetMapping("/pedido")
    public String order(Model model, HttpSession session) {

        Usuario usuario =usuarioService.findById(4).get();
        model.addAttribute("cart", detalles);
        model.addAttribute("pedido", pedido);
        model.addAttribute("usuario", usuario);
        return "usuario/resumenpedido";
    }

    @GetMapping("/savePedido")
    public String savePedido() {
        Date fechaCreacion = new Date();
        pedido.setFechacreacion(fechaCreacion);
        pedido.setNumero(pedidoService.generarNumeroPedido());

        //usuario
        Usuario usuario =usuarioService.findById(4).get();

        pedido.setUsuario(usuario);
        pedidoService.save(pedido);

        //guardar detalles
        for (DetallePedido dt:detalles) {
            dt.setPedido(pedido);
            detallePedidoService.save(dt);
        }

        ///limpiar lista y orden
        pedido = new Pedido();
        detalles.clear();

        return "redirect:/producto/listado";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model) {
        log.info("Nombre del producto: {}", nombre);
        List<Producto> productos= productoService.findAll().stream().filter( p -> p.getNomidproducto().contains(nombre)).collect(Collectors.toList());
        model.addAttribute("listarproductos", productos);
        return "usuario/formproductos";
    }
}
