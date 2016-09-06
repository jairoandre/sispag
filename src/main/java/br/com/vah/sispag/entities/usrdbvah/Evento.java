package br.com.vah.sispag.entities.usrdbvah;

import br.com.vah.sispag.constants.TipoEventoEnum;
import br.com.vah.sispag.constants.TipoGuiaEnum;
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

  @Column(name = "DT_EVENTO")
  private Date dataEvento;

  @Column(name = "NM_MENSAGEM")
  private String mensagem;

  public Evento(){}

  public Evento(TipoEventoEnum tipo, Guia guia, User usuario) {
    this.tipo = tipo;
    this.guia = guia;
    this.usuario = usuario;
    this.dataEvento = new Date();
  }

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

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  @Override
  public Object getIdentity() {
    return id;
  }
}
