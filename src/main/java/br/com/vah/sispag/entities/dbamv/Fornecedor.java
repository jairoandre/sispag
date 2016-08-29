package br.com.vah.sispag.entities.dbamv;

import br.com.vah.sispag.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "FORNECEDOR", schema = "DBAMV")
@NamedQueries({@NamedQuery(name = Fornecedor.ALL, query = "SELECT c FROM Fornecedor c"),
    @NamedQuery(name = Fornecedor.COUNT, query = "SELECT COUNT(c) FROM Fornecedor c")})
public class Fornecedor extends BaseEntity {

  public final static String ALL = "Fornecedor.populatedItems";
  public final static String COUNT = "Fornecedor.countTotal";

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "CD_FORNECEDOR")
  private Long id;

  @Column(name = "NM_FORNECEDOR")
  private String title;

  @Column(name = "NM_FANTASIA")
  private String nomeFantasia;

  @Column(name = "TP_CLIENTE_FORN")
  private String type;

  @Column(name = "NR_CGC_CPF")
  private String cgcCpf;

  @Column(name = "DS_ENDERECO")
  private String endereco;

  @Column(name = "DS_BAIRRO")
  private String bairro;

  @Column(name = "NR_CEP")
  private String cep;

  public Fornecedor() {
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getNomeFantasia() {
    return nomeFantasia;
  }

  public void setNomeFantasia(String nomeFantasia) {
    this.nomeFantasia = nomeFantasia;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCgcCpf() {
    return cgcCpf;
  }

  public void setCgcCpf(String cgcCpf) {
    this.cgcCpf = cgcCpf;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  @Override
  public String getLabelForSelectItem() {
    if (id == null) {
      return null;
    } else {
      return String.format("%d - %s", getId(), getTitle());
    }
  }

}
