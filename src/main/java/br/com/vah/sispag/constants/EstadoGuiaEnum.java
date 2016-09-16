package br.com.vah.sispag.constants;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jairoportela on 09/09/2016.
 */
public enum EstadoGuiaEnum {
  ANALISE("Em análise"),
  PENDENTE("Pendente"),
  AUTORIZADA("Autorizada"),
  PARCIAL("Autor. Parcial"),
  NEGADA("Negada"),
  A_LIBERAR("A liberar"),
  CANCELADA("Cancelada");

  private String label;

  EstadoGuiaEnum(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }

  public static List<SelectItem> getSelectItems() {
    List<SelectItem> selectItems = new ArrayList<>();
    selectItems.add(new SelectItem(null, "Escolha..."));
    for (EstadoGuiaEnum tipo : EstadoGuiaEnum.values()) {
      selectItems.add(new SelectItem(tipo, tipo.getLabel()));
    }
    return selectItems;
  }
}
