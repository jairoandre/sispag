package br.com.vah.sispag.entities.usrdbvah;

import br.com.vah.sispag.entities.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jairoportela on 02/09/2016.
 */
@Entity
@Table(name = "TB_SISPAG_MSG", schema = "USRDBVAH")
public class Mensagem extends BaseEntity {

  @Id
  @SequenceGenerator(name = "seqMensagem", sequenceName = "TB_SISPAG_MSG", schema = "USRDBVAH", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMensagem")
  @Column(name = "ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "ID_EVENTO")
  private Evento evento;

  @Column(name = "DT_MENSAGEM")
  private Date data;

  @Column(name = "NM_DESCRICAO")
  private String descricao;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Evento getEvento() {
    return evento;
  }

  public void setEvento(Evento evento) {
    this.evento = evento;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  @Override
  public Object getIdentity() {
    return id;
  }
}
