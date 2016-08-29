package br.com.vah.sispag.converters;

import br.com.vah.sispag.entities.dbamv.Fornecedor;
import br.com.vah.sispag.entities.dbamv.Setor;
import br.com.vah.sispag.service.AbstractSrv;
import br.com.vah.sispag.service.FornecedorSrv;
import br.com.vah.sispag.service.SetorSrv;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FornecedorConverter extends GenericConverter<Fornecedor> {

  private
  @Inject
  FornecedorSrv service;

  @Override
  public AbstractSrv<Fornecedor> getService() {
    return service;
  }
}