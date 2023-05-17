package InterBanking.empresas.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import InterBanking.empresas.model.Empresa;
import InterBanking.empresas.model.Transferencia;
import InterBanking.empresas.repository.EmpresaRepository;
import InterBanking.empresas.repository.TransferenciaRepository;
import InterBanking.empresas.service.EmpresaServiceImpl;

public class EmpresaServiceTest {

   @Test
   public void getCompaniesWithRecentTransfers() {
      TransferenciaRepository transferenciaRepository = mock(TransferenciaRepository.class);
      EmpresaRepository empresaRepository = mock(EmpresaRepository.class);
      EmpresaServiceImpl empresaService = new EmpresaServiceImpl(empresaRepository, transferenciaRepository);
      Empresa empresa1 = new Empresa(1L, "123456789", "Empresa 1", LocalDate.now());
      Empresa empresa2 = new Empresa(2L, "987654321", "Empresa 2", LocalDate.now());
      Transferencia transferencia1 = new Transferencia(null, new BigDecimal("1000.00"), empresa1, "Cuenta Débito 1", "Cuenta Crédito 1", LocalDate.now());
      Transferencia transferencia2 = new Transferencia(null, new BigDecimal("2000.00"), empresa1, "Cuenta Débito 2", "Cuenta Crédito 2", LocalDate.now());
      Transferencia transferencia3 = new Transferencia(null, new BigDecimal("3000.00"), empresa2, "Cuenta Débito 3", "Cuenta Crédito 3", LocalDate.now().minusMonths(1));
      List<Transferencia> transferencias = Arrays.asList(transferencia1, transferencia2, transferencia3);
      when(transferenciaRepository.findByFechaBetween(any(LocalDate.class), any(LocalDate.class)))
            .thenReturn(transferencias);

      List<Empresa> empresasObtenidas = empresaService.getCompaniesWithRecentTransfers();

      List<Empresa> empresasEsperadas = transferencias.stream()
                                                      .map(Transferencia::getEmpresa)
                                                      .distinct()
                                                      .collect(Collectors.toList());
      assertEquals(empresasEsperadas, empresasObtenidas);
      verify(transferenciaRepository).findByFechaBetween(any(LocalDate.class), any(LocalDate.class));
   }

   @Test
   public void getCompaniesWithRecentAdherence() {
      LocalDate fechaActual = LocalDate.now();
      LocalDate fechaInicio = fechaActual.minusMonths(1);
      EmpresaRepository empresaRepository = mock(EmpresaRepository.class);
      TransferenciaRepository transferenciaRepository = mock(TransferenciaRepository.class);
      EmpresaServiceImpl empresaService = new EmpresaServiceImpl(empresaRepository, transferenciaRepository);
      Empresa empresa1 = new Empresa(1L, "123456789", "Empresa 1", fechaActual);
      Empresa empresa2 = new Empresa(2L, "987654321", "Empresa 2", fechaActual.minusDays(15));
      Empresa empresa3 = new Empresa(3L, "987654323", "Empresa 3", fechaActual.minusMonths(2));
      List<Empresa> empresasEsperadas = Arrays.asList(empresa1, empresa2);
      when(empresaRepository.findByFechaAdhesionBetween(fechaInicio, fechaActual))
            .thenReturn(empresasEsperadas);

      List<Empresa> empresasObtenidas = empresaService.getCompaniesWithRecentAdherence();

      assertEquals(empresasEsperadas, empresasObtenidas);
      verify(empresaRepository).findByFechaAdhesionBetween(fechaInicio, fechaActual);
   }
}
