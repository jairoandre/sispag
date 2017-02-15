package br.com.vah.sispag.controllers;

import br.com.vah.sispag.service.RelatorioSrv;
import org.primefaces.model.StreamedContent;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jairoportela on 03/01/2017.
 */
@Named
@ViewScoped
public class ReportCtrl implements Serializable {

  private
  @Inject
  RelatorioSrv service;

  private Date begin;

  private Date end;

  public Date getBegin() {
    return begin;
  }

  public void setBegin(Date begin) {
    this.begin = begin;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }

  public StreamedContent getRelatorioGuias() {
    return service.relatorioBalanco(begin, end);
  }

  public StreamedContent getRelatorioProdutividade() {
    return service.relatorioProdutividade(begin, end);
  }
}
