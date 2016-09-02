package br.com.vah.sispag.constants;

/**
 * Created by jairoportela on 01/09/2016.
 */
public enum TipoGuiaEnum {
  INTERNACAO("Internação"),
  SADT("SADT"),
  OPME("OPME");

  private String label;

  TipoGuiaEnum(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
