package br.com.vah.sispag.reports;

import java.io.Serializable;

/**
 * Created by Jairoportela on 03/01/2017.
 */
public class BalancoDTO implements Serializable {

  private String convenio;
  private Integer criadas;
  private Integer autorizadas;
  private Integer media;

  public BalancoDTO(String convenio, Integer criadas, Integer autorizadas, Integer media) {
    this.convenio = convenio;
    this.criadas = criadas;
    this.autorizadas = autorizadas;
    this.media = media;
  }

  public String getConvenio() {
    return convenio;
  }

  public void setConvenio(String convenio) {
    this.convenio = convenio;
  }

  public Integer getCriadas() {
    return criadas;
  }

  public void setCriadas(Integer criadas) {
    this.criadas = criadas;
  }

  public Integer getAutorizadas() {
    return autorizadas;
  }

  public void setAutorizadas(Integer autorizadas) {
    this.autorizadas = autorizadas;
  }

  public Integer getMedia() {
    return media;
  }

  public void setMedia(Integer media) {
    this.media = media;
  }
}
