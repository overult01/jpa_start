package jpa_ex1.jpa_ex1.jpa_ex1.web.login;

import br.com.dsfnet.extarch.acesso.ValidacaoAcessoUsuarioBase;
import br.com.dsfnet.extarch.type.SistemaDsfType;

public class ValidacaoAcessoUsuario extends ValidacaoAcessoUsuarioBase {

	@Override
	public String identificadorSistema() {
		//FIXME: Adicionar o enumerado correto da aplicação
		return SistemaDsfType.DSF_ADMFIS.name();
	}
}
