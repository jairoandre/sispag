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
@Table(name = "PRESTADOR", schema = "DBAMV")
public class Prestador extends BaseEntity {

  @Id
  @Column(name = "CD_PRESTADOR")
  private Long id;

  @Column(name = "NM_PRESTADOR")
  private String nome;

  @Column(name = "TP_SITUACAO")
  private String situacao;

  @Column(name = "NR_CPF_CGC")
  private String cpf;

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

  public String getSituacao() {
    return situacao;
  }

  public void setSituacao(String situacao) {
    this.situacao = situacao;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  @Override
  public Object getIdentity() {
    return id;
  }
}
