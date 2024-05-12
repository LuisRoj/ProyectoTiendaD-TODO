package DAWI.ProyectoTiendaD.TODO.controller;

import DAWI.ProyectoTiendaD.TODO.model.bd.Rol;
import DAWI.ProyectoTiendaD.TODO.model.bd.Usuario;
import DAWI.ProyectoTiendaD.TODO.model.security.UsuarioSecurity;
import DAWI.ProyectoTiendaD.TODO.service.IProductoService;
import DAWI.ProyectoTiendaD.TODO.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginController {
    private final Logger logger= LoggerFactory.getLogger(LoginController.class);
    private UsuarioService usuarioService;
    private IProductoService IProductoService;

    @GetMapping("/login")
    public String login(HttpServletRequest request){
        return "auth/frmLogin";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "auth/frmRegistroUsuario";
    }

    @GetMapping("/login-success")
    public String loginSucces(){
        return "redirect:/auth/dashboard";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.saveUser(usuario);
        return "auth/frmLogin";
    }



    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Usuario usuario = usuarioService.findUserByUserName(username);
        logger.info("Accesos : {}", usuario);
        String email = usuario.getEmail();
        // Aquí puedes obtener todos los demás campos de Usuario
        session.setAttribute("usuario", email);
        session.setAttribute("idusuario", usuario.getIdusuario());
        model.addAttribute("listarproductos", IProductoService.listarProductos());

        // Obteniendo el idrol del usuario
        Rol rol = usuario.getRoles().iterator().next();
        int idrol = rol.getIdrol();

        // Redirigiendo basado en el idrol
        if (idrol == 1) {
            return "redirect:/administrador";
        } else if (idrol == 2) {
            return "redirect:/";
        } else {
            return "usuario/formproductos";
        }
    }

}
