package br.com.vah.sispag.controllers;

import br.com.vah.sispag.constants.TipoEventoEnum;
import br.com.vah.sispag.constants.TipoGuiaEnum;
import br.com.vah.sispag.entities.dbamv.ProFat;
import br.com.vah.sispag.entities.usrdbvah.Evento;
import br.com.vah.sispag.entities.usrdbvah.Guia;
import br.com.vah.sispag.entities.usrdbvah.ItemGuia;
import br.com.vah.sispag.service.AbstractSrv;
import br.com.vah.sispag.service.GuiaSrv;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jairoportela on 02/09/2016.
 */
@Named
@ViewScoped
public class GuiaCtrl extends AbstractCtrl<Guia> {

  private
  @Inject
  transient Logger logger;

  private
  @Inject
  GuiaSrv service;

  private
  @Inject
  SessionCtrl sessionCtrl;

  private final List<SelectItem> tipos = TipoGuiaEnum.getSelectItems();

  private ProFat proFatToAdd;

  private String motivo;

  @PostConstruct
  public void init() {
    logger.info(this.getClass().getSimpleName() + " created");
    setItem(createNewItem());
    initLazyModel(service, "itens", "eventos");
  }

  public void preOpenModal() {
    setItem(createNewItem());
    motivo = "";
  }

  public void preOpenModal(Guia guia) {
    setItem(guia);
  }

  @Override
  public AbstractSrv<Guia> getService() {
    return service;
  }

  @Override
  public Logger getLogger() {
    return logger;
  }

  @Override
  public Guia createNewItem() {
    return new Guia();
  }

  @Override
  public String path() {
    return "guias";
  }

  @Override
  public String getEntityName() {
    return "Guia";
  }

  public List<SelectItem> getTipos() {
    return tipos;
  }

  public ProFat getProFatToAdd() {
    return proFatToAdd;
  }

  public void setProFatToAdd(ProFat proFatToAdd) {
    this.proFatToAdd = proFatToAdd;
  }

  public void onProFatSelect(SelectEvent evt) {
    ProFat proFat = (ProFat) evt.getObject();
    if (proFat != null) {
      ItemGuia itemGuia = new ItemGuia();
      itemGuia.setGuia(getItem());
      itemGuia.setNome(proFat.getNome());
      getItem().getItens().add(itemGuia);
    }
    proFatToAdd = null;
  }

  public void removeItemGuia(ItemGuia itemGuia) {
    getItem().getItens().remove(itemGuia);
  }

  @Override
  public String doSave() {
    setItem(service.criar(getItem(), sessionCtrl.getUser()));
    return super.doSave();
  }

  public String estadoCorrente(Guia guia) {
    Evento evento = service.eventoCorrente(guia);
    return evento.getTipo().name();
  }

  public void enviar(Guia guia) {
    try {
      service.movimentar(guia, sessionCtrl.getUser());
      service.update(guia);
      addInfoMsg("Guia enviada com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void autorizar(Guia guia) {
    try {
      guia = service.autorizar(guia, sessionCtrl.getUser());
      service.update(guia);
      addInfoMsg("Guia autorizada com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void negar() {
    try {
      Guia guia = service.negar(getItem(), sessionCtrl.getUser(), motivo);
      service.update(guia);
      addInfoMsg("Guia negada com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void parcial() {
    try {
      Guia guia = service.parcial(getItem(), sessionCtrl.getUser(), motivo);
      service.update(guia);
      addInfoMsg("Autorização parcial com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void cancelar() {
    try {
      Guia guia = service.cancelar(getItem(), sessionCtrl.getUser(), motivo);
      service.update(guia);
      addInfoMsg("Guia cancelada com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void receber(Guia guia) {
    try {
      service.receber(guia, sessionCtrl.getUser());
      service.update(guia);
      addInfoMsg("Guia recebida com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }


  public List<Evento> comentariosGuia() {
    return service.comentariosGuia(getItem());
  }


  public Boolean disableReceber(Guia guia) {
    return service.disableReceber(guia, sessionCtrl.getUser());
  }

  public Boolean disableAutorizar(Guia guia) {
    return service.disableAutorizar(guia, sessionCtrl.getUser());
  }

  public Boolean disableNegar(Guia guia) {
    return service.disableNegar(guia, sessionCtrl.getUser());
  }

  public Boolean disableParcial(Guia guia) {
    return service.disableParcial(guia, sessionCtrl.getUser());
  }

  public Boolean disableEnviar(Guia guia) {
    return service.disableEnviar(guia, sessionCtrl.getUser());
  }

  public Boolean disableCancelar(Guia guia) {
    return service.disableCancelar(guia, sessionCtrl.getUser());
  }

  public String getMotivo() {
    return motivo;
  }

  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }
}
