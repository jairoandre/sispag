package br.com.vah.sispag.reports;

/**
 * Created by jairoportela on 28/04/2016.
 */
public class EventosGuiaDTO {

  private Long id;
  private String evento;
  private String usuario;
  private String data;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEvento() {
    return evento;
  }

  public void setEvento(String evento) {
    this.evento = evento;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
