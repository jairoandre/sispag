package br.com.vah.sispag.entities.dbamv;

import br.com.vah.sispag.entities.BaseEntity;

import javax.persistence.*;

/**
 * Created by Jairoportela on 14/09/2016.
 */
@Entity
@Table(name = "PRO_FAT_HIERARQUIZADO", schema = "DBAMV")
public class ProFatHierarquizado extends BaseEntity {

  @EmbeddedId
  private ProFatHierarquizadoPK id;

  @Column(name = "DS_PRO_FAT_HIERARQUIZADO")
  private String descricao;

  public ProFatHierarquizadoPK getId() {
    return id;
  }

  public void setId(ProFatHierarquizadoPK id) {
    this.id = id;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  @Override
  public Object getIdentity() {
    return this.id;
  }
}
