package br.com.vah.sispag.entities.dbamv;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Jairoportela on 14/09/2016.
 */
@Embeddable
public class ProFatHierarquizadoPK implements Serializable {

  @Column(name = "CD_PRO_FAT")
  private String proFat;

  @Column(name = "CD_PRO_FAT_HIERARQUIZADO")
  private String cbhpm;

  public ProFatHierarquizadoPK(){}

  public void setProFat(String proFat) {
    this.proFat = proFat;
  }

  public String getCbhpm() {
    return cbhpm;
  }

  public void setCbhpm(String cbhpm) {
    this.cbhpm = cbhpm;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ProFatHierarquizadoPK) {
      ProFatHierarquizadoPK pk = (ProFatHierarquizadoPK) obj;
      return proFat.equals(pk.proFat) && cbhpm.equals(pk.cbhpm);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 11 * hash + this.proFat.hashCode();
    hash = 11 * hash + this.cbhpm.hashCode();
    return hash;
  }
}
