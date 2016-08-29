package br.com.vah.sispag.converters;

import br.com.vah.sispag.entities.dbamv.Setor;
import br.com.vah.sispag.service.AbstractSrv;
import br.com.vah.sispag.service.SetorSrv;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SetorConverter extends GenericConverter<Setor> {

  private
  @Inject
  SetorSrv service;

  @Override
  public AbstractSrv<Setor> getService() {
    return service;
  }
}