package br.com.vah.sispag.converters;

import br.com.vah.sispag.entities.dbamv.Prestador;
import br.com.vah.sispag.service.AbstractSrv;
import br.com.vah.sispag.service.PrestadorSrv;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PrestadorConverter extends GenericConverter<Prestador> {

  private
  @Inject
  PrestadorSrv service;

  @Override
  public AbstractSrv<Prestador> getService() {
    return service;
  }
}