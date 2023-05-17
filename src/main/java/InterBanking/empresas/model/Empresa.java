package InterBanking.empresas.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Empresas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "cuit")
   private String cuit;

   @Column(name = "razon_social")
   private String razonSocial;

   @Column(name = "fecha_adhesion")
   private LocalDate fechaAdhesion;

}