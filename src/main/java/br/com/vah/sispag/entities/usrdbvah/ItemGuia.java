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

  @ManyToOne
  @JoinColumn(name = "CD_PRO_FAT")
  private ProFat proFat;

  @Column(name = "NM_OUTRO")
  private String outro;

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

  public ProFat getProFat() {
    return proFat;
  }

  public void setProFat(ProFat proFat) {
    this.proFat = proFat;
  }

  public String getOutro() {
    return outro;
  }

  public void setOutro(String outro) {
    this.outro = outro;
  }

  @Override
  public Object getIdentity() {
    return id;
  }
}
