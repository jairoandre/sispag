package br.com.vah.sispag.entities.dbamv;

import br.com.vah.sispag.entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by jairoportela on 01/09/2016.
 */
@Entity
@Table(name = "PRO_FAT", schema = "DBAMV")
public class ProFat extends BaseEntity {

  @Id
  @Column(name = "CD_PRO_FAT")
  private String id;

  @Column(name = "DS_PRO_FAT")
  private String nome;

  @Column(name = "CD_SUS")
  private String codigoSus;

  @ManyToOne
  @JoinColumn(name = "CD_GRU_PRO")
  private GruPro gruPro;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "CD_PRO_FAT", insertable = false, updatable = false)
  private Set<ProFatHierarquizado> hierarquia;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCodigoSus() {
    return codigoSus;
  }

  public void setCodigoSus(String codigoSus) {
    this.codigoSus = codigoSus;
  }

  public GruPro getGruPro() {
    return gruPro;
  }

  public void setGruPro(GruPro gruPro) {
    this.gruPro = gruPro;
  }

  public Set<ProFatHierarquizado> getHierarquia() {
    return hierarquia;
  }

  public void setHierarquia(Set<ProFatHierarquizado> hierarquia) {
    this.hierarquia = hierarquia;
  }

  public String getCbhpm() {
    if (hierarquia != null && !hierarquia.isEmpty()) {
      return hierarquia.iterator().next().getId().getCbhpm();
    }
    return id;
  }

  @Override
  public Object getIdentity() {
    return id;
  }
}
