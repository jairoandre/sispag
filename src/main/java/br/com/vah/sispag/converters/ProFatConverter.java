package br.com.vah.sispag.converters;

import br.com.vah.sispag.entities.dbamv.ProFat;
import br.com.vah.sispag.service.AbstractSrv;
import br.com.vah.sispag.service.ProFatSrv;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ProFatConverter extends GenericConverter<ProFat> {

  private
  @Inject
  ProFatSrv service;

  @Override
  public AbstractSrv<ProFat> getService() {
    return service;
  }

  @Override
  public Object parseId(String value) {
    return value;
  }
}