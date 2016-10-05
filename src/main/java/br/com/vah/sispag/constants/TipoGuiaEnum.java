package br.com.vah.sispag.constants;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jairoportela on 01/09/2016.
 */
public enum TipoGuiaEnum {
  INTERNACAO("Internação"),
  SADT("SADT");

  private String label;

  TipoGuiaEnum(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public static List<SelectItem> getSelectItems() {
    List<SelectItem> selectItems = new ArrayList<>();
    selectItems.add(new SelectItem(null, "Escolha..."));
    for (TipoGuiaEnum tipo : TipoGuiaEnum.values()) {
      selectItems.add(new SelectItem(tipo, tipo.getLabel()));
    }
    return selectItems;
  }
}
