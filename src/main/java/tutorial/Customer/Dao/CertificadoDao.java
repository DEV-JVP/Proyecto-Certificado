package tutorial.Customer.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tutorial.Customer.Entity.Certificado;

import java.util.Optional;

public interface CertificadoDao extends JpaRepository<Certificado, Integer> {
    Optional<Certificado> findByDni(String dni);
}
