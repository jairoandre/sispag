package br.com.vah.sispag.service;

import br.com.vah.sispag.constants.RoleEnum;
import br.com.vah.sispag.constants.TipoEventoEnum;
import br.com.vah.sispag.constants.TipoGuiaEnum;
import br.com.vah.sispag.entities.dbamv.Setor;
import br.com.vah.sispag.entities.usrdbvah.Evento;
import br.com.vah.sispag.entities.usrdbvah.Guia;
import br.com.vah.sispag.entities.usrdbvah.User;

import javax.ejb.Stateless;
import java.util.Date;

/**
 * Created by jairoportela on 02/09/2016.
 */
@Stateless
public class GuiaSrv extends AbstractSrv<Guia> {

  public GuiaSrv() {
    super(Guia.class);
  }

  private Evento adicionarEvento(TipoEventoEnum tipo, Guia guia, User user) {
    Evento evento = new Evento(tipo, guia, user);
    guia.getEventos().add(evento);
    return evento;
  }

  public Guia criar(Guia guia, User user) {
    adicionarEvento(TipoEventoEnum.CRIACAO, guia, user);
    return guia;
  }

  public Guia movimentar(Guia guia, User user) {
    adicionarEvento(TipoEventoEnum.MOVIMENTACAO, guia, user);
    return guia;
  }

  public Boolean disableEnviar(Guia guia, User user) {
    Evento evento = eventoCorrente(guia);
    if (evento.getTipo().equals(TipoEventoEnum.CRIACAO)) {
      if (TipoGuiaEnum.INTERNACAO.equals(guia.getTipo()) || TipoGuiaEnum.SADT.equals(guia.getTipo())) {
        if (RoleEnum.RECEPCAO.equals(user.getRole()) || RoleEnum.ADMINISTRADOR.equals(user.getRole())) {
          return false;
        }
      }
    }
    return true;
  }

  public Guia receber(Guia guia, User user) {
    adicionarEvento(TipoEventoEnum.RECEBIMENTO, guia, user);
    return guia;
  }

  public Boolean disableReceber(Guia guia, User user) {
    Evento evento = eventoCorrente(guia);
    if (evento.getTipo().equals(TipoEventoEnum.MOVIMENTACAO)) {
      if (TipoGuiaEnum.INTERNACAO.equals(guia.getTipo()) || TipoGuiaEnum.SADT.equals(guia.getTipo())) {
        if (RoleEnum.RECEPCAO.equals(user.getRole()) || RoleEnum.ADMINISTRADOR.equals(user.getRole())) {
          return false;
        }
      }
    }
    return true;
  }

  public Boolean disableNegar(Guia guia, User user) {
    Evento evento = eventoCorrente(guia);
    if (!evento.getTipo().equals(TipoEventoEnum.CANCELADO)) {
      return false;
    }
    return true;
  }

  public Guia inserirMensagem(Guia guia, User user, String mensagem) {
    Evento evento = adicionarEvento(TipoEventoEnum.MOVIMENTACAO, guia, user);
    evento.setMensagem(mensagem);
    return guia;
  }

  public Guia autorizar(Guia guia, User user) {
    adicionarEvento(TipoEventoEnum.AUTORIZADO, guia, user);
    return guia;
  }

  public Guia autorizarParcialmente(Guia guia, User user, String motivo) {
    Evento evt = adicionarEvento(TipoEventoEnum.AUTORIZADO_PARCIAL, guia, user);
    evt.setMensagem(motivo);
    return guia;
  }

  public Guia negar(Guia guia, User user, String motivo) {
    Evento evt = adicionarEvento(TipoEventoEnum.NEGADO, guia, user);
    evt.setMensagem(motivo);
    return guia;
  }

  public Guia cancelar(Guia guia, User user, String motivo) {
    Evento evt = adicionarEvento(TipoEventoEnum.CANCELADO, guia, user);
    evt.setMensagem(motivo);
    return guia;
  }

  public Guia comentar(Guia guia, User user, String comentario) {
    Evento evt = adicionarEvento(TipoEventoEnum.COMENTARIO, guia, user);
    evt.setMensagem(comentario);
    return guia;
  }

  public Evento eventoCorrente(Guia guia) {
    return guia.getEventos().iterator().next();
  }


}
