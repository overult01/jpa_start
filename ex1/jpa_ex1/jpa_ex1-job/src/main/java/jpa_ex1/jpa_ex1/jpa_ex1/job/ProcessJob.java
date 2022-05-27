package jpa_ex1.jpa_ex1.jpa_ex1.job;

import br.com.dsfnet.extarch.job.BaseJob;
import br.com.jarch.util.LogUtils;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Singleton
@Startup
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ProcessJob extends BaseJob {

    @Override
    public String getAno() {
        return "*";
    }

    @Override
    public String getMes() {
        return "*";
    }

    @Override
    public String getDiaMes() {
        return "*";
    }

    @Override
    public String getDiaSemana() {
        return "*";
    }

    @Override
    public String getHora() {
        return "*";
    }

    @Override
    public String getMinuto() {
        return "*/5";
    }

    @Override
    public String getSegundo() {
        return "0";
    }

    @Override
    public void processamentoJob(Long idMultTenant) {
    	LogUtils.warning("INICIO - Processando o Tenant " + idMultTenant);
    	// execute your action
        LogUtils.warning("FIM - Processando o Tenant "+idMultTenant);
    }

}
