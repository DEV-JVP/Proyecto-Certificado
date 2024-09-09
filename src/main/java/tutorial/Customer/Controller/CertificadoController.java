package tutorial.Customer.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tutorial.Customer.Entity.Certificado;
import tutorial.Customer.Service.CertificadoService;
import tutorial.Customer.Util.CertificadoPdfGenerator;
import tutorial.Customer.Util.ListarCertificadoPdf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;


    @GetMapping("/")
    public String redirectToUser() {
        return "redirect:/usuario";
    }

    @GetMapping("/usuario")
    public String listarCertificadosUsuarios(Model model) {
        List<Certificado> certificados = certificadoService.listarTodos();
        model.addAttribute("certificados", certificados);
        return "usuario";
    }

      @PostMapping("/findUser")
    public String listarCertificados(Model model, @RequestParam String dni) {
        Optional<Certificado> certificado = certificadoService.buscarPorDni(dni);
        if (certificado.isPresent()) {
            model.addAttribute("certificado", certificado.get());
        } else {
            model.addAttribute("error", "No se encontró el certificado con el DNI proporcionado.");
        }
        return "usuario";  // Esto debería devolver la vista "usuario.html"
    }

    @GetMapping("/certificados/add")
    public String mostrarFormulario(Model model) {
        Certificado certificado = new Certificado();
        model.addAttribute("certificados", certificado);
        return "crear-certificados";
    }

        @PostMapping("/saveCertificado") //GUARDAR NUEVO
    public String guardar(@ModelAttribute Certificado certificado) {
        certificadoService.guardar(certificado);
        System.out.println("guardado");
        return "redirect:/index";
    }
    @GetMapping("/eliminar/{id}") //listado
    public String delete(@PathVariable("id") int idCertificado) {
        certificadoService.eliminar(idCertificado);
        return "redirect:/index";
    }
    @GetMapping("/editar/{id}")
    public String editarform(@PathVariable("id") int idCertificado, Model model) {
        Certificado certificado = certificadoService.buscarPorId(idCertificado);
        model.addAttribute("certificados", certificado); // Asegúrate de que el nombre sea 'certificados'
        return "crear-certificados";
    }

    @GetMapping("/tables") //listado correctamente
    public String tables(Model model) {
        List<Certificado> certificados = certificadoService.listarTodos();
        model.addAttribute("certificados", certificados);
        return "tables";
    }
    @GetMapping("/export/pdf")
    public ModelAndView exportarPdf() {
        List<Certificado> certificados = certificadoService.listarTodos();
        Map<String, Object> model = new HashMap<>();
        model.put("certificados", certificados);

        return new ModelAndView(new ListarCertificadoPdf(), model);
    }

    @GetMapping("/certificado/{id}/pdf")
    public void generateCertificadoPdf(@PathVariable Integer id, HttpServletResponse response) {
        try {
            Certificado certificado = certificadoService.getCertificadoById(id);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=certificado_" + id + ".pdf");

            CertificadoPdfGenerator pdfGenerator = new CertificadoPdfGenerator();
            pdfGenerator.generatePdf(certificado, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



      @GetMapping("/index")
    public String index(Model model) {
        List<Certificado> certificados = certificadoService.listarTodos();
        model.addAttribute("certificados", certificados);
        return "index";
    }




}




