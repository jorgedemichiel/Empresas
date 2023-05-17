package InterBanking.empresas.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import InterBanking.empresas.model.Empresa;
import InterBanking.empresas.model.Transferencia;
import InterBanking.empresas.repository.EmpresaRepository;
import InterBanking.empresas.repository.EmpresaService;
import InterBanking.empresas.repository.TransferenciaRepository;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

   private final EmpresaService empresaService;
   private final EmpresaRepository empresaRepository;
   private final TransferenciaRepository transferenciaRepository;

   public EmpresaController(EmpresaService empresaService, EmpresaRepository empresaRepository, TransferenciaRepository transferenciaRepository) {
      this.empresaService = empresaService;
      this.empresaRepository = empresaRepository;
      this.transferenciaRepository = transferenciaRepository;
   }

   @GetMapping("/transferencias-ultimo-mes")
   public ResponseEntity<List<Empresa>> obtenerEmpresasConTransferenciasUltimoMes() {
      List<Empresa> empresas = empresaService.getCompaniesWithRecentTransfers();
      return ResponseEntity.ok(empresas);
   }

   @GetMapping("/adhesion-ultimo-mes")
   public ResponseEntity<List<Empresa>> obtenerEmpresasAdheridasUltimoMes() {
      List<Empresa> empresas = empresaService.getCompaniesWithRecentAdherence();
      return ResponseEntity.ok(empresas);
   }

   @PostMapping("/adhesion")
   public ResponseEntity<String> realizarAdhesionEmpresa(@RequestBody Empresa empresa) {
      empresaService.performCompanyAdherence(empresa);
      return ResponseEntity.ok("Adhesión realizada correctamente.");
   }

   @PostMapping("/cargar")
   public ResponseEntity<String> cargarEmpresas() {
      Empresa empresa1 = new Empresa(null, "123456789", "Empresa 1", LocalDate.now());
      Empresa empresa2 = new Empresa(null, "987654321", "Empresa 2", LocalDate.now());
      Empresa empresa3 = new Empresa(null, "987654323", "Empresa 3", LocalDate.now().minusMonths(6));
      Empresa empresa4 = new Empresa(null, "545454545", "Empresa 4", LocalDate.now().minusMonths(10));
      empresaRepository.saveAll(Arrays.asList(empresa1, empresa2, empresa3, empresa4));

      Transferencia transferencia1 = new Transferencia(null, new BigDecimal("1000.00"), empresa1, "Cuenta Débito 1", "Cuenta Crédito 1", LocalDate.now());
      Transferencia transferencia2 = new Transferencia(null, new BigDecimal("2000.00"), empresa1, "Cuenta Débito 2", "Cuenta Crédito 2", LocalDate.now());
      Transferencia transferencia3 = new Transferencia(null, new BigDecimal("3000.00"), empresa2, "Cuenta Débito 3", "Cuenta Crédito 3", LocalDate.now().minusMonths(1));
      Transferencia transferencia4 = new Transferencia(null, new BigDecimal("10000.00"), empresa3, "Cuenta Débito 4", "Cuenta Crédito 4", LocalDate.now().minusMonths(3));
      Transferencia transferencia5 = new Transferencia(null, new BigDecimal("10000.00"), empresa4, "Cuenta Débito 4", "Cuenta Crédito 4", LocalDate.now().minusMonths(3));
      transferenciaRepository.saveAll(Arrays.asList(transferencia1, transferencia2, transferencia3, transferencia4, transferencia5));

      return ResponseEntity.ok("Empresas y transferencias cargadas exitosamente en la base de datos.");
   }
}
