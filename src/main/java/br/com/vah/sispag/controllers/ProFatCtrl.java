package br.com.vah.sispag.controllers;

import br.com.vah.sispag.entities.dbamv.ProFat;
import br.com.vah.sispag.service.AbstractSrv;
import br.com.vah.sispag.service.ProFatSrv;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jairoportela on 05/09/2016.
 */
@Named
@ViewScoped
public class ProFatCtrl extends AbstractCtrl<ProFat> {

  private
  @Inject
  transient Logger logger;

  private
  @Inject
  ProFatSrv service;

  @Override
  public AbstractSrv<ProFat> getService() {
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
  public ProFat createNewItem() {
    return new ProFat();
  }

  @Override
  public String path() {
    return "proFat";
  }

  @Override
  public String getEntityName() {
    return "Proc./OPME";
  }

  @Override
  public void prepareSearch() {
    resetSearchParams();
    setSearchParam("id", getSearchTerm());
    setSearchParam("nome", getSearchTerm());
    setSearchParam("codigoSus", getSearchTerm());
  }

  @Override
  public List<ProFat> completeMethod(String query) {
    setItem(createNewItem());
    setSearchTerm(query);
    prepareSearch();
    return getLazyModel().load(6);
  }

  public List<ProFat> completeMethodMat(String query) {
    setItem(createNewItem());
    setSearchTerm(query);
    prepareSearch();
    setSearchParam("tipo", "MAT");
    return getLazyModel().load(6);
  }
}
