package br.com.vah.sispag.constants;

import javax.faces.model.SelectItem;
import java.util.*;

/**
 * Created by jairoportela on 16/08/2016.
 */
public enum OperacaoDataEnum {
  EQ("="),
  GT(">="),
  LT("<="),
  BW(">= & <=");

  private String label;

  OperacaoDataEnum(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public static List<SelectItem> getSelectItens() {
    List<SelectItem> list = new ArrayList<>();
    List<OperacaoDataEnum> asList = Arrays.asList(OperacaoDataEnum.values());
    for (OperacaoDataEnum tipo : asList) {
      list.add(new SelectItem(tipo, tipo.getLabel()));
    }
    return list;
  }
}
