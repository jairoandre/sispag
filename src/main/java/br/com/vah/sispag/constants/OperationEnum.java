package br.com.vah.sispag.constants;

/**
 * Created by Jairoportela on 04/10/2016.
 */
public enum OperationEnum {
  GUIA,
  OPME,
  MAT,
  BOTH;

  public Boolean contains(String values) {
    String names[] = values.replace(" ", "").split(",");
    for (String name : names) {
      if (this.equals(OperationEnum.valueOf(name))) {
        return true;
      }
    }
    return false;
  }
}
