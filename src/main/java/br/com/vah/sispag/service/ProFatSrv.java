package br.com.vah.sispag.service;

import br.com.vah.sispag.entities.dbamv.ProFat;

import javax.ejb.Stateless;

/**
 * Created by jairoportela on 05/09/2016.
 */
@Stateless
public class ProFatSrv extends AbstractSrv<ProFat> {

  public ProFatSrv() {
    super(ProFat.class);
  }

}
