package br.com.vah.sispag.entities.usrdbvah;

import br.com.vah.sispag.entities.BaseEntity;

import javax.persistence.*;

/**
 * Created by jairoportela on 01/09/2016.
 */
@Entity
@Table(name = "TB_SISPAG_IT_OPME", schema = "USRDBVAH")
public class ItemOpme extends BaseEntity {

  @Id
  @SequenceGenerator(name = "seqItemOpme", sequenceName = "SEQ_SISPAG_IT_OPME", schema = "USRDBVAH", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqItemOpme")
  @Column(name = "ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "ID_GUIA")
  private Guia guia;

  @Column(name = "NM_NOME")
  private String nome;

  @Column(name = "VL_QUANTIDADE")
  private Integer quantidade = 1;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Guia getGuia() {
    return guia;
  }

  public void setGuia(Guia guia) {
    this.guia = guia;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  @Override
  public Object getIdentity() {
    return id;
  }
}
