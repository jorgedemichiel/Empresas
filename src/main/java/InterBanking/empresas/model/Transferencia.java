package InterBanking.empresas.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private BigDecimal importe;

   @ManyToOne
   @JoinColumn(name = "empresa_id")
   private Empresa empresa;

   private String cuentaDebito;
   private String cuentaCredito;

   private LocalDate fecha;

}
