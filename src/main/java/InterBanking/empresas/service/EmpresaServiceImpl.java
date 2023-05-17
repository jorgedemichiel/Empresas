package InterBanking.empresas.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import InterBanking.empresas.model.Empresa;
import InterBanking.empresas.model.Transferencia;
import InterBanking.empresas.repository.EmpresaRepository;
import InterBanking.empresas.repository.EmpresaService;
import InterBanking.empresas.repository.TransferenciaRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService {

   private final EmpresaRepository empresaRepository;
   private final TransferenciaRepository transferenciaRepository;

   public EmpresaServiceImpl(EmpresaRepository empresaRepository, TransferenciaRepository transferenciaRepository) {
      this.empresaRepository = empresaRepository;
      this.transferenciaRepository = transferenciaRepository;
   }

   @Override
   public List<Empresa> getCompaniesWithRecentTransfers() {
      LocalDate fechaFin = LocalDate.now();
      LocalDate fechaInicio = fechaFin.minusMonths(1);
      List<Transferencia> transferencias = transferenciaRepository.findByFechaBetween(fechaInicio, fechaFin);
      return transferencias.stream().map(Transferencia::getEmpresa).distinct().collect(Collectors.toList());
   }

   @Override
   public List<Empresa> getCompaniesWithRecentAdherence() {
      LocalDate fechaFin = LocalDate.now();
      LocalDate fechaInicio = fechaFin.minusMonths(1);
      return empresaRepository.findByFechaAdhesionBetween(fechaInicio, fechaFin);
   }

   @Override
   public void performCompanyAdherence(Empresa empresa) {
      empresaRepository.save(empresa);
   }
}

