package br.com.vah.sispag.constants;

/**
 * Created by Jairoportela on 11/08/2016.
 */
public enum RestrictedViews {

  USER_LIST("/admin/user/edit.xhtml", RoleEnum.ADMINISTRADOR),
  USER_EDIT("/admin/user/list.xhtml", RoleEnum.ADMINISTRADOR);

  private String view;

  private RoleEnum[] roles;

  RestrictedViews(String view, RoleEnum... roles) {
    this.view = view;
    this.roles = roles;
  }

  public String getView() {
    return view;
  }

  public static RestrictedViews getByView(String view) {
    for (RestrictedViews viewEnum : RestrictedViews.values()) {
      if (viewEnum.getView().equals(view)) {
        return viewEnum;
      }
    }
    return null;
  }

  public boolean checkRoles(RoleEnum... roles) {
    boolean atLeastOne = false;
    for (RoleEnum thisRole : this.roles) {
      for (RoleEnum theirRole : roles) {
        if (theirRole.equals(thisRole)) {
          atLeastOne = true;
          break;
        }
      }
      if (atLeastOne) {
        break;
      }
    }
    return atLeastOne;
  }
}
