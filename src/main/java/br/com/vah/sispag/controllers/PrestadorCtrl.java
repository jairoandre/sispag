package br.com.vah.sispag.controllers;

import br.com.vah.sispag.entities.dbamv.Prestador;
import br.com.vah.sispag.service.AbstractSrv;
import br.com.vah.sispag.service.PrestadorSrv;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

/**
 * Created by jairoportela on 05/09/2016.
 */
@Named
@ViewScoped
public class PrestadorCtrl extends AbstractCtrl<Prestador> {

  private
  @Inject
  transient Logger logger;

  private
  @Inject
  PrestadorSrv service;

  @Override
  public AbstractSrv<Prestador> getService() {
    return service;
  }

  @PostConstruct
  public void init() {
    logger.info(this.getClass().getSimpleName() + " created");
    setItem(createNewItem());
    initLazyModel(service);
  }

  @Override
  public Logger getLogger() {
    return logger;
  }

  @Override
  public Prestador createNewItem() {
    return new Prestador();
  }

  @Override
  public String path() {
    return "prestador";
  }

  @Override
  public String getEntityName() {
    return "Prestador";
  }

  @Override
  public void prepareSearch() {
    super.prepareSearch();
    setSearchParam("nome", getSearchTerm());
  }
}
