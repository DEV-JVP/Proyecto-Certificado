package tutorial.Customer.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutorial.Customer.Dao.CertificadoDao;
import tutorial.Customer.Entity.Certificado;
import tutorial.Customer.Service.CertificadoService;

import java.util.List;
import java.util.Optional;

@Service
public class CertificadoServiceImpl implements CertificadoService {


    @Autowired
    private CertificadoDao certificadoDao;


    @Override
    public List<Certificado> listarTodos() {
        return certificadoDao.findAll();
    }

    @Override
    public void guardar(Certificado certificado) {
        certificadoDao.save(certificado);

    }

    @Override
    public Certificado buscarPorId(Integer id) {
        return certificadoDao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        certificadoDao.deleteById(id);

    }
    @Override
    public Certificado getCertificadoById(Integer id) {
        return certificadoDao.findById(id).orElseThrow(() -> new RuntimeException("Certificado no encontrado"));
    }

    @Override
    public Optional<Certificado> buscarPorDni(String dni) {

        return certificadoDao.findByDni(dni);
    }
}
