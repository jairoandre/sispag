package br.com.vah.sispag.converters;

import br.com.vah.sispag.entities.dbamv.Convenio;
import br.com.vah.sispag.service.AbstractSrv;
import br.com.vah.sispag.service.ConvenioSrv;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ConvenioConverter extends GenericConverter<Convenio> {

  private
  @Inject
  ConvenioSrv service;

  @Override
  public AbstractSrv<Convenio> getService() {
    return service;
  }
}