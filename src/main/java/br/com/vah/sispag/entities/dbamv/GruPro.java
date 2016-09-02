package br.com.vah.sispag.entities.dbamv;

import br.com.vah.sispag.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jairoportela on 01/09/2016.
 */
@Entity
@Table(name = "GRU_PRO", schema = "DBAMV")
public class GruPro extends BaseEntity {

  @Id
  @Column(name = "CD_GRU_PRO")
  private Long id;

  @Column(name = "TP_GRU_PRO")
  private String tipo;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  @Override
  public Object getIdentity() {
    return id;
  }
}
