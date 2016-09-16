package br.com.vah.sispag.entities.usrdbvah;

import br.com.vah.sispag.constants.EstadoGuiaEnum;
import br.com.vah.sispag.constants.TipoGuiaEnum;
import br.com.vah.sispag.entities.BaseEntity;
import br.com.vah.sispag.entities.dbamv.Convenio;
import br.com.vah.sispag.entities.dbamv.Prestador;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
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

  @ManyToOne
  @JoinColumn(name = "CD_CONVENIO")
  private Convenio convenio;

  @ManyToOne
  @JoinColumn(name = "CD_PRESTADOR")
  private Prestador prestador;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "CD_ESTADO")
  private EstadoGuiaEnum estado = EstadoGuiaEnum.PENDENTE;

  @Column(name = "CD_GUIA")
  private String numeroGuia;

  @Column(name = "CD_SOLICITACAO")
  private String numeroSolicitacao;

  @Column(name = "CD_SENHA")
  private String senha;

  @Column(name = "DT_EMISSAO")
  private Date dataEmissao;

  @Column(name = "DT_ENTREGA")
  private Date dataEntrega;

  @Column(name = "DT_RESPOSTA")
  private Date dataResposta;

  @Column(name = "NM_TELEFONE")
  private String numeroTelefone;

  @Column(name = "NM_PACIENTE")
  private String paciente;

  @Column(name = "CD_CARTAO")
  private String cartao;

  @OneToMany(mappedBy = "guia", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<ItemGuia> itens = new LinkedHashSet<>();

  @OneToMany(mappedBy = "guia", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @OrderBy("dataEvento DESC")
  private Set<Evento> eventos = new LinkedHashSet<>();

  @OneToMany(mappedBy = "guia", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<Anexo> anexos = new LinkedHashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TipoGuiaEnum getTipo() {
    return tipo;
  }

  public void setTipo(TipoGuiaEnum tipo) {
    this.tipo = tipo;
  }

  public Convenio getConvenio() {
    return convenio;
  }

  public void setConvenio(Convenio convenio) {
    this.convenio = convenio;
  }

  public Prestador getPrestador() {
    return prestador;
  }

  public void setPrestador(Prestador prestador) {
    this.prestador = prestador;
  }

  public EstadoGuiaEnum getEstado() {
    return estado;
  }

  public void setEstado(EstadoGuiaEnum estado) {
    this.estado = estado;
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

  public Date getDataEntrega() {
    return dataEntrega;
  }

  public void setDataEntrega(Date dataEntrega) {
    this.dataEntrega = dataEntrega;
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

  public String getNumeroTelefone() {
    return numeroTelefone;
  }

  public void setNumeroTelefone(String numeroTelefone) {
    this.numeroTelefone = numeroTelefone;
  }

  public String getPaciente() {
    return paciente;
  }

  public void setPaciente(String paciente) {
    this.paciente = paciente;
  }

  public String getCartao() {
    return cartao;
  }

  public void setCartao(String cartao) {
    this.cartao = cartao;
  }

  public Set<ItemGuia> getItens() {
    return itens;
  }

  public void setItens(Set<ItemGuia> itens) {
    this.itens = itens;
  }

  public Set<Evento> getEventos() {
    return eventos;
  }

  public void setEventos(Set<Evento> eventos) {
    this.eventos = eventos;
  }

  public Set<Anexo> getAnexos() {
    return anexos;
  }

  public void setAnexos(Set<Anexo> anexos) {
    this.anexos = anexos;
  }

  @Override
  public Object getIdentity() {
    return id;
  }
}
