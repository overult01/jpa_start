package jpa_ex1.jpa_ex1.jpa_ex1.ws.jaxrs;


import br.com.dsfnet.extarch.acesso.AcessoProxy;
import br.com.jarch.model.MultiTenant;
import br.com.jarch.model.UserInformation;
import br.com.jarch.util.LogUtils;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ContainerRequestFilterProvider implements ContainerRequestFilter {

    @Inject
    private Instance<MultiTenant> multiTenant;

    @Inject
    private Instance<UserInformation> userInformation;

    @Inject
    private Instance<AcessoProxy> acessoProxy;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String multiTenantId = requestContext.getHeaderString("multiTenantId");

        if (multiTenantId == null || multiTenantId.isEmpty()) {
            LogUtils.warning("MULTITENANT_ID não informado no HEADER");
        } else {
            LogUtils.warning("CONFIGURANDO MULTITENANT_ID = " + multiTenantId);
            multiTenant.get().set(Long.valueOf(multiTenantId));
        }

        try {
            userInformation.get().set(acessoProxy.get().usuarioJob(Long.valueOf(multiTenant.get().get())));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}