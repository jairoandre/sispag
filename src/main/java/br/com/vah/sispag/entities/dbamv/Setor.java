package br.com.vah.sispag.entities.dbamv;

import br.com.vah.sispag.entities.BaseEntity;

import javax.persistence.*;

/**
 * Created by Jairoportela on 05/04/2016.
 */
@Entity
@Table(name = "SETOR", schema = "DBAMV")
@NamedQueries({@NamedQuery(name = Setor.ALL, query = "SELECT s FROM Setor s"),
    @NamedQuery(name = Setor.COUNT, query = "SELECT COUNT(s) FROM Setor s")})
public class Setor extends BaseEntity {

  public final static String ALL = "Setor.all";
  public final static String COUNT = "Setor.count";

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "CD_SETOR")
  private Long id;

  @Column(name = "NM_SETOR")
  private String title;

  @Column(name = "CD_MULTI_EMPRESA")
  private Integer multiEmpresa;

  @Column(name = "CD_GRUPO_DE_CUSTO")
  private Long grupoCusto;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getMultiEmpresa() {
    return multiEmpresa;
  }

  public void setMultiEmpresa(Integer multiEmpresa) {
    this.multiEmpresa = multiEmpresa;
  }

  public Long getGrupoCusto() {
    return grupoCusto;
  }

  public void setGrupoCusto(Long grupoCusto) {
    this.grupoCusto = grupoCusto;
  }

  @Override
  public Object getIdentity() {
    return id;
  }

  @Override
  public String getLabelForSelectItem() {
    return String.format("%d - %s", id, title);
  }


}
