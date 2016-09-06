package br.com.vah.sispag.entities.dbamv;

import br.com.vah.sispag.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jairoportela on 02/09/2016.
 */
@Entity
@Table(name = "CONVENIO", schema = "DBAMV")
public class Convenio extends BaseEntity {

  @Id
  @Column(name = "CD_CONVENIO")
  private Long id;

  @Column(name = "NM_CONVENIO")
  private String nome;

  @Column(name = "NR_REGISTRO_OPERADORA_ANS")
  private String codigoAns;

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

  public String getCodigoAns() {
    return codigoAns;
  }

  public void setCodigoAns(String codigoAns) {
    this.codigoAns = codigoAns;
  }

  @Override
  public Object getIdentity() {
    return id;
  }
}
