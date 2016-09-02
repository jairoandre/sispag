package br.com.vah.sispag.service;

import br.com.vah.sispag.entities.usrdbvah.Guia;

import javax.ejb.Stateless;

/**
 * Created by jairoportela on 02/09/2016.
 */
@Stateless
public class GuiaSrv extends AbstractSrv<Guia> {

  public GuiaSrv() {
    super(Guia.class);
  }
}
