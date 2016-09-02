package br.com.vah.sispag.entities;

import java.io.Serializable;

/**
 *
 * @author jairoportela
 *
 */
public abstract class BaseEntity implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public abstract Object getIdentity();

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (getIdentity() != null ? getIdentity().hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == null) {
      return false;
    } else if (!(obj instanceof BaseEntity)) {
      return false;
    } else if (((BaseEntity) obj).getIdentity() != null && ((BaseEntity) obj).getIdentity().equals(this.getIdentity())) {
      return true;
    } else {
      return obj == this;
    }
  }

  @Override
  public String toString() {
    return "entity." + this.getClass() + "[ id=" + getIdentity() + " ] ";
  }

  public String getLabelForSelectItem() {
    return getIdentity().toString();
  }
}