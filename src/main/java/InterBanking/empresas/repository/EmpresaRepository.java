package InterBanking.empresas.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import InterBanking.empresas.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
   List<Empresa> findByFechaAdhesionBetween(LocalDate fechaInicio, LocalDate fechaFin);
}