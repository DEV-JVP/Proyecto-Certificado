package tutorial.Customer.Service;


import tutorial.Customer.Entity.Certificado;

import java.util.List;
import java.util.Optional;


public interface CertificadoService {


    public List<Certificado> listarTodos();
    public void guardar(Certificado certificado);
    public Certificado buscarPorId(Integer id);
    public void eliminar(Integer id);
    public Certificado getCertificadoById(Integer id);


    Optional<Certificado> buscarPorDni(String dni);
}
