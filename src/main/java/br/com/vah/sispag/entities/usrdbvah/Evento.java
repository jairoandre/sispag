package br.com.vah.sispag.entities.usrdbvah;

import br.com.vah.sispag.constants.TipoEventoEnum;
import br.com.vah.sispag.entities.BaseEntity;
import br.com.vah.sispag.entities.dbamv.Setor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by jairoportela on 01/09/2016.
 */
@Entity
@Table(name = "TB_SISPAG_EVENTO", schema = "USRDBVAH")
public class Evento extends BaseEntity {

  @Id
  @SequenceGenerator(name = "seqEvento", sequenceName = "SEQ_SISPAG_EVENTO", schema = "USRDBVAH", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEvento")
  @Column(name = "ID")
  private Long id;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "CD_TIPO")
  private TipoEventoEnum tipo;

  @ManyToOne
  @JoinColumn(name = "ID_GUIA")
  private Guia guia;

  @ManyToOne
  @JoinColumn(name = "ID_USUARIO")
  private User usuario;

  @ManyToOne
  @JoinColumn(name = "ID_SETOR_O")
  private Setor origem;

  @ManyToOne
  @JoinColumn(name = "ID_SETOR_D")
  private Setor destino;

  @Column(name = "DT_EVENTO")
  private Date dataEvento;

  @OneToMany(mappedBy = "evento")
  private Set<Mensagem> mensagens;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TipoEventoEnum getTipo() {
    return tipo;
  }

  public void setTipo(TipoEventoEnum tipo) {
    this.tipo = tipo;
  }

  public Guia getGuia() {
    return guia;
  }

  public void setGuia(Guia guia) {
    this.guia = guia;
  }

  public Setor getOrigem() {
    return origem;
  }

  public void setOrigem(Setor origem) {
    this.origem = origem;
  }

  public Setor getDestino() {
    return destino;
  }

  public void setDestino(Setor destino) {
    this.destino = destino;
  }

  public User getUsuario() {
    return usuario;
  }

  public void setUsuario(User usuario) {
    this.usuario = usuario;
  }

  public Date getDataEvento() {
    return dataEvento;
  }

  public void setDataEvento(Date dataEvento) {
    this.dataEvento = dataEvento;
  }

  public Set<Mensagem> getMensagens() {
    return mensagens;
  }

  public void setMensagens(Set<Mensagem> mensagens) {
    this.mensagens = mensagens;
  }

  @Override
  public Object getIdentity() {
    return id;
  }
}
