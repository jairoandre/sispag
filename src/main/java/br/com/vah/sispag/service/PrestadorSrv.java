package br.com.vah.sispag.service;

import br.com.vah.sispag.entities.dbamv.Prestador;

import javax.ejb.Stateless;

/**
 * Created by jairoportela on 05/09/2016.
 */
@Stateless
public class PrestadorSrv extends AbstractSrv<Prestador> {

  public PrestadorSrv() {
    super(Prestador.class);
  }

}
