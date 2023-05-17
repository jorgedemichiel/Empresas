package InterBanking.empresas.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import InterBanking.empresas.controller.EmpresaController;
import InterBanking.empresas.model.Empresa;
import InterBanking.empresas.repository.EmpresaService;


public class EmpresaControllerTest {

   @Test
   public void getCompaniesWithRecentTransfersTest() {
      EmpresaService empresaService = mock(EmpresaService.class);
      EmpresaController empresaController = new EmpresaController(empresaService, null, null);
      Empresa empresa1 = new Empresa(1L, "123456789", "Empresa 1", LocalDate.now());
      Empresa empresa2 = new Empresa(2L, "987654321", "Empresa 2", LocalDate.now());
      List<Empresa> empresasEsperadas = Arrays.asList(empresa1, empresa2);
      when(empresaService.getCompaniesWithRecentTransfers()).thenReturn(empresasEsperadas);

      ResponseEntity<List<Empresa>> response = empresaController.obtenerEmpresasConTransferenciasUltimoMes();
      List<Empresa> empresasObtenidas = response.getBody();

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(empresasEsperadas, empresasObtenidas);
      verify(empresaService).getCompaniesWithRecentTransfers();
   }

   @Test
   public void performCompanyAdherenceOkTest() {
      EmpresaService empresaService = mock(EmpresaService.class);
      EmpresaController empresaController = new EmpresaController(empresaService, null, null);
      Empresa empresa = new Empresa(1L, "123456789", "Empresa 1", LocalDate.now());

      ResponseEntity<String> response = empresaController.realizarAdhesionEmpresa(empresa);

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("Adhesión realizada correctamente.", response.getBody());
      verify(empresaService).performCompanyAdherence(empresa);
   }
}
