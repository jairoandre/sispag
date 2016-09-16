package br.com.vah.sispag.entities.dbamv;

import br.com.vah.sispag.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Jairoportela on 13/09/2016.
 */
@Entity
@Table(name = "ESPECIALID", schema = "DBAMV")
public class Especialidade extends BaseEntity {

  @Id
  @Column(name = "CD_ESPECIALID")
  private Long id;

  @Column(name = "DS_ESPECIALID")
  private String nome;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public Object getIdentity() {
    return id;
  }
}
