package br.com.vah.sispag.service;

import br.com.vah.sispag.entities.dbamv.Convenio;

import javax.ejb.Stateless;

/**
 * Created by jairoportela on 16/08/2016.
 */
@Stateless
public class ConvenioSrv extends AbstractSrv<Convenio> {

  public ConvenioSrv() {
    super(Convenio.class);
  }

}
