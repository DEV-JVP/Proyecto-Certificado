package tutorial.Customer.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="certificado")
public class Certificado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCertificado;

    private String dni;
    private String participante;
    private String capacitacion;


    private String fechaInicio;
    private String fechaFin;

    private String codigoCertificado;


    private String fechaEmision;
}
