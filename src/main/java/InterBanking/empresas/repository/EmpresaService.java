package InterBanking.empresas.repository;

import java.util.List;
import InterBanking.empresas.model.Empresa;

public interface EmpresaService {
   List<Empresa> getCompaniesWithRecentTransfers();
   List<Empresa> getCompaniesWithRecentAdherence();
   void performCompanyAdherence(Empresa empresa);
}
