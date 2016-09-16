package br.com.vah.sispag.constants;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

public enum RoleEnum {
  USUARIO("Usuário"),
  RECEPCAO("Recepção", USUARIO),
  CIRURGICO("Cirúrgico", USUARIO),
  ADMINISTRADOR("Administrador", CIRURGICO, RECEPCAO);

  private String label;
  private RoleEnum[] subroles;

  RoleEnum(String label, RoleEnum... subroles) {
    this.label = label;
    this.subroles = subroles;
  }

  public String getLabel() {
    return label;
  }

  public static List<SelectItem> getSelectItems() {
    List<SelectItem> items = new ArrayList<>();
    items.add(new SelectItem(null, "Selecione..."));
    for (RoleEnum role : RoleEnum.values()) {
      items.add(new SelectItem(role, role.getLabel()));
    }
    return items;
  }

  public boolean hasSubRole(RoleEnum role) {
    if (this.equals(role)) {
      return true;
    } else if (this.subroles != null) {
      for (RoleEnum subrole : this.subroles) {
        if (subrole.hasSubRole(role)) {
          return true;
        }
      }

    }
    return false;
  }
}
