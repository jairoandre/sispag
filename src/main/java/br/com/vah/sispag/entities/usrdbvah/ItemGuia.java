package br.com.vah.sispag.entities.usrdbvah;

import br.com.vah.sispag.entities.BaseEntity;
import br.com.vah.sispag.entities.dbamv.ProFat;

import javax.persistence.*;

/**
 * Created by jairoportela on 01/09/2016.
 */
@Entity
@Table(name = "TB_SISPAG_IT_GUIA", schema = "USRDBVAH")
public class ItemGuia extends BaseEntity {

  @Id
  @SequenceGenerator(name = "seqItemGuia", sequenceName = "SEQ_SISPAG_IT_GUIA", schema = "USRDBVAH", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqItemGuia")
  @Column(name = "ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "ID_GUIA")
  private Guia guia;

  @Column(name = "NM_NOME")
  private String nome;

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

  @Override
  public Object getIdentity() {
    return id;
  }
}
