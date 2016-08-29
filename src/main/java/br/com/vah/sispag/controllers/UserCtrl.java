package br.com.vah.sispag.controllers;

import br.com.vah.sispag.constants.RoleEnum;
import br.com.vah.sispag.entities.usrdbvah.User;
import br.com.vah.sispag.service.AbstractSrv;
import br.com.vah.sispag.service.UserSrv;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Jairoportela on 06/04/2016.
 */
@Named
@ViewScoped
public class UserCtrl extends AbstractCtrl<User> {

  private
  @Inject
  transient Logger logger;

  private
  @Inject
  UserSrv service;

  private List<SelectItem> roles;

  @PostConstruct
  public void init() {
    logger.info(this.getClass().getSimpleName() + " created");
    setItem(createNewItem());
    initLazyModel(service);
    roles = RoleEnum.getSelectItems();
  }


  @Override
  public AbstractSrv<User> getService() {
    return service;
  }

  @Override
  public Logger getLogger() {
    return logger;
  }

  @Override
  public User createNewItem() {
    return new User();
  }

  @Override
  public String path() {
    return "user";
  }

  @Override
  public String getEntityName() {
    return "User";
  }

  public List<SelectItem> getRoles() {
    return roles;
  }

  @Override
  public void prepareSearch() {
    super.prepareSearch();
    setSearchParam("login", getSearchTerm());
    setSearchParam("email", getSearchTerm());
  }

}
