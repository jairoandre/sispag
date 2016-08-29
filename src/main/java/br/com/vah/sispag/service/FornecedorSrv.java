package br.com.vah.sispag.service;

import br.com.vah.sispag.entities.dbamv.Fornecedor;

import javax.ejb.Stateless;

/**
 * Created by jairoportela on 16/08/2016.
 */
@Stateless
public class FornecedorSrv extends AbstractSrv<Fornecedor> {

  public FornecedorSrv() {
    super(Fornecedor.class);
  }

}
