package br.com.vah.sispag.entities.usrdbvah;

import br.com.vah.sispag.constants.StatusEnum;
import br.com.vah.sispag.constants.TipoGuiaEnum;
import br.com.vah.sispag.entities.BaseEntity;
import br.com.vah.sispag.entities.dbamv.Convenio;
import br.com.vah.sispag.entities.dbamv.Prestador;

import javax.persistence.*;
import java.util.*;

/**
 * Created by jairoportela on 01/09/2016.
 */
@Entity
@Table(name = "TB_SISPAG_GUIA", schema = "USRDBVAH")
public class Guia extends BaseEntity {

  @Id
  @SequenceGenerator(name = "seqGuia", sequenceName = "SEQ_SISPAG_GUIA", schema = "USRDBVAH", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGuia")
  @Column(name = "ID", nullable = false)
  private Long id;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "CD_TIPO", nullable = false)
  private TipoGuiaEnum tipo;

  @ManyToOne
  @JoinColumn(name = "CD_CONVENIO", nullable = false)
  private Convenio convenio;

  @ManyToOne
  @JoinColumn(name = "CD_PRESTADOR", nullable = false)
  private Prestador prestador;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "CD_STATUS", nullable = false)
  private StatusEnum status = StatusEnum.PENDENTE;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "CD_STATUS_OPME")
  private StatusEnum statusOpme;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "CD_STATUS_MAT")
  private StatusEnum statusMat;

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

  @Column(name = "DT_ALTERACAO")
  private Date dataAlteracao;

  @Column(name = "NM_TELEFONE")
  private String numeroTelefone;

  @Column(name = "NM_PACIENTE")
  private String paciente;

  @Column(name = "CD_CARTAO")
  private String cartao;

  @Column(name = "CD_CPF")
  private String cpf;

  @Column(name = "SN_OPME")
  private Boolean opme = false;

  @Column(name = "SN_MATERIAL")
  private Boolean material = false;

  @OneToMany(mappedBy = "guia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @OrderBy("id DESC")
  private Set<ItemGuia> itens = new LinkedHashSet<>();

  @OneToMany(mappedBy = "guia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @OrderBy("id DESC")
  private Set<ItemOpme> opmes = new LinkedHashSet<>();

  @OneToMany(mappedBy = "guia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @OrderBy("id DESC")
  private Set<ItemMat> materiais = new LinkedHashSet<>();

  @OneToMany(mappedBy = "guia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @OrderBy("id DESC")
  private Set<Evento> eventos = new LinkedHashSet<>();

  @OneToMany(mappedBy = "guia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @OrderBy("id DESC")
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

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public StatusEnum getStatusOpme() {
    return statusOpme;
  }

  public void setStatusOpme(StatusEnum statusOpme) {
    this.statusOpme = statusOpme;
  }

  public StatusEnum getStatusMat() {
    return statusMat;
  }

  public void setStatusMat(StatusEnum statusMat) {
    this.statusMat = statusMat;
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

  public Date getDataAlteracao() {
    return dataAlteracao;
  }

  public void setDataAlteracao(Date dataAlteracao) {
    this.dataAlteracao = dataAlteracao;
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

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Boolean getOpme() {
    return opme;
  }

  public void setOpme(Boolean opme) {
    this.opme = opme;
  }

  public Boolean getMaterial() {
    return material;
  }

  public void setMaterial(Boolean material) {
    this.material = material;
  }

  public Set<ItemGuia> getItens() {
    return itens;
  }

  public void setItens(Set<ItemGuia> itens) {
    this.itens = itens;
  }

  public Set<ItemOpme> getOpmes() {
    return opmes;
  }

  public void setOpmes(Set<ItemOpme> opmes) {
    this.opmes = opmes;
  }

  public Set<ItemMat> getMateriais() {
    return materiais;
  }

  public void setMateriais(Set<ItemMat> materiais) {
    this.materiais = materiais;
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

  public Map<String, Object> toMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("Convênio", convenio == null ? null : convenio.getId());
    map.put("Prestador", prestador == null ? null : prestador.getId());
    map.put("Status Guia", status);
    map.put("Status OPME", statusOpme);
    map.put("Status Material", statusMat);
    map.put("Nº. Guia", numeroGuia);
    map.put("Nº. Solicitação", numeroSolicitacao);
    map.put("Senha", senha);
    map.put("Dt. Entrega", dataEntrega);
    map.put("Dt. Solicitação", dataEmissao);
    map.put("Dt. Resposta", dataResposta);
    map.put("Telefone", numeroTelefone);
    map.put("Paciente", paciente);
    map.put("Cartão", cartao);
    map.put("Cpf", cpf);
    map.put("Itens", itens);
    map.put("Opmes", opmes);
    map.put("Materiais", materiais);
    map.put("Anexos", anexos);
    return map;
  }
}
