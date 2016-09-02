package br.com.vah.sispag.entities.usrdbvah;

import br.com.vah.sispag.constants.TipoGuiaEnum;
import br.com.vah.sispag.entities.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by jairoportela on 01/09/2016.
 */
@Entity
@Table(name = "TB_SISPAG_GUIA", schema = "USRDBVAH")
public class Guia extends BaseEntity {

  @Id
  @SequenceGenerator(name = "seqGuia", sequenceName = "SEQ_SISPAG_GUIA", schema = "USRDBVAH", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGuia")
  @Column(name = "ID")
  private Long id;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "CD_TIPO")
  private TipoGuiaEnum tipo;

  @Column(name = "CD_REG_ANS")
  private String registroANS;

  @Column(name = "CD_GUIA")
  private String numeroGuia;

  @Column(name = "CD_SOLICT")
  private String numeroSolicitacao;

  @Column(name = "CD_SENHA")
  private String senha;

  @Column(name = "DT_EMISSAO")
  private Date dataEmissao;

  @Column(name = "DT_RESPOSTA")
  private Date dataResposta;

  @Column(name = "NM_PACIENTE")
  private String nome;

  @Column(name = "CD_CNES")
  private String cnes;

  @Column(name = "CD_CARTAO")
  private String cartao;

  @Column(name = "ID_USUARIO")
  private User solicitante;

  @OneToMany(mappedBy = "guia", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<ItemGuia> itens;


  public Object getId() {
    return id;
  }

  public void setId(Object id) {
    this.id = (Long) id;
  }

  @Override
  public String getLabelForSelectItem() {
    return null;
  }

  public TipoGuiaEnum getTipo() {
    return tipo;
  }

  public void setTipo(TipoGuiaEnum tipo) {
    this.tipo = tipo;
  }

  public String getRegistroANS() {
    return registroANS;
  }

  public void setRegistroANS(String registroANS) {
    this.registroANS = registroANS;
  }

  public String getNumeroGuia() {
    return numeroGuia;
  }

  public void setNumeroGuia(String numeroGuia) {
    this.numeroGuia = numeroGuia;
  }

  public String getNumeroSolicitacao() {
    return numeroSolicitacao;
  }

  public void setNumeroSolicitacao(String numeroSolicitacao) {
    this.numeroSolicitacao = numeroSolicitacao;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Date getDataEmissao() {
    return dataEmissao;
  }

  public void setDataEmissao(Date dataEmissao) {
    this.dataEmissao = dataEmissao;
  }

  public Date getDataResposta() {
    return dataResposta;
  }

  public void setDataResposta(Date dataResposta) {
    this.dataResposta = dataResposta;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCnes() {
    return cnes;
  }

  public void setCnes(String cnes) {
    this.cnes = cnes;
  }

  public String getCartao() {
    return cartao;
  }

  public void setCartao(String cartao) {
    this.cartao = cartao;
  }

  public User getSolicitante() {
    return solicitante;
  }

  public void setSolicitante(User solicitante) {
    this.solicitante = solicitante;
  }

  public Set<ItemGuia> getItens() {
    return itens;
  }

  public void setItens(Set<ItemGuia> itens) {
    this.itens = itens;
  }

  @Override
  public Object getIdentity() {
    return id;
  }
}
