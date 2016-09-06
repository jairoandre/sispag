package br.com.vah.sispag.controllers;

import br.com.vah.sispag.entities.dbamv.Fornecedor;
import br.com.vah.sispag.service.AbstractSrv;
import br.com.vah.sispag.service.FornecedorSrv;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

/**
 * Created by jairoportela on 05/09/2016.
 */
@Named
@ViewScoped
public class FornecedorCtrl extends AbstractCtrl<Fornecedor> {

  private
  @Inject
  transient Logger logger;

  private
  @Inject
  FornecedorSrv service;

  @Override
  public AbstractSrv<Fornecedor> getService() {
    return service;
  }

  @Override
  public Logger getLogger() {
    return logger;
  }

  @Override
  public Fornecedor createNewItem() {
    return new Fornecedor();
  }

  @Override
  public String path() {
    return "fornecedor";
  }

  @Override
  public String getEntityName() {
    return "Fornecedor";
  }
}
