package br.com.vah.sispag.reports;

import java.io.Serializable;

/**
 * Created by Jairoportela on 03/01/2017.
 */
public class ColaboradoraDTO implements Serializable {

  private String colaboradora;
  private Integer criadas;
  private Integer autorizadas;
  private Integer media;

  public ColaboradoraDTO(String colaboradora, Integer criadas, Integer autorizadas, Integer media) {
    this.colaboradora = colaboradora;
    this.criadas = criadas;
    this.autorizadas = autorizadas;
    this.media = media;
  }

  public String getColaboradora() {
    return colaboradora;
  }

  public void setColaboradora(String colaboradora) {
    this.colaboradora = colaboradora;
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
