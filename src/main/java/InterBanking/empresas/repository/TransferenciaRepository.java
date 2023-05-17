package InterBanking.empresas.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import InterBanking.empresas.model.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
   List<Transferencia> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
