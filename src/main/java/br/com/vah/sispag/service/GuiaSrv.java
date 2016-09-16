package br.com.vah.sispag.service;

import br.com.vah.sispag.constants.EstadoGuiaEnum;
import br.com.vah.sispag.constants.RoleEnum;
import br.com.vah.sispag.constants.TipoEventoEnum;
import br.com.vah.sispag.constants.TipoGuiaEnum;
import br.com.vah.sispag.entities.dbamv.Convenio;
import br.com.vah.sispag.entities.dbamv.Prestador;
import br.com.vah.sispag.entities.dbamv.Setor;
import br.com.vah.sispag.entities.usrdbvah.Evento;
import br.com.vah.sispag.entities.usrdbvah.Guia;
import br.com.vah.sispag.entities.usrdbvah.User;
import br.com.vah.sispag.util.PaginatedSearchParam;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    if (guia.getTipo().equals(TipoGuiaEnum.OPME)) {
      guia.setEstado(EstadoGuiaEnum.A_LIBERAR);
    } else {
      guia.setEstado(EstadoGuiaEnum.ANALISE);
    }
    return guia;
  }

  public Guia atualizar(Guia guia, User user) {
    adicionarEvento(TipoEventoEnum.ALTERACAO, guia, user);
    return guia;
  }

  public Guia liberar(Guia guia, User user) {
    adicionarEvento(TipoEventoEnum.LIBERACAO, guia, user);
    guia.setEstado(EstadoGuiaEnum.ANALISE);
    return guia;
  }

  public Guia autorizar(Guia guia, User user) {
    adicionarEvento(TipoEventoEnum.AUTORIZADO, guia, user);
    guia.setEstado(EstadoGuiaEnum.AUTORIZADA);
    return guia;
  }

  public Guia emAnalise(Guia guia, User user) {
    adicionarEvento(TipoEventoEnum.EM_ANALISE, guia, user);
    guia.setEstado(EstadoGuiaEnum.ANALISE);
    return guia;
  }

  public Guia pendente(Guia guia, User user, String motivo) {
    Evento evt = adicionarEvento(TipoEventoEnum.PENDENTE, guia, user);
    evt.setMensagem(motivo);
    guia.setEstado(EstadoGuiaEnum.PENDENTE);
    return guia;
  }

  public Guia negar(Guia guia, User user, String motivo) {
    Evento evt = adicionarEvento(TipoEventoEnum.NEGADO, guia, user);
    evt.setMensagem(motivo);
    guia.setEstado(EstadoGuiaEnum.NEGADA);
    return guia;
  }

  public Guia parcial(Guia guia, User user, String motivo) {
    Evento evt = adicionarEvento(TipoEventoEnum.AUTORIZADO_PARCIAL, guia, user);
    evt.setMensagem(motivo);
    guia.setEstado(EstadoGuiaEnum.PARCIAL);
    return guia;
  }

  public Guia cancelar(Guia guia, User user, String motivo) {
    Evento evt = adicionarEvento(TipoEventoEnum.CANCELADO, guia, user);
    evt.setMensagem(motivo);
    guia.setEstado(EstadoGuiaEnum.CANCELADA);
    return guia;
  }

  public Guia comentar(Guia guia, User user, String comentario) {
    Evento evt = adicionarEvento(TipoEventoEnum.COMENTARIO, guia, user);
    evt.setMensagem(comentario);
    return guia;
  }

  public List<Evento> comentariosGuia(Guia guia) {
    List<Evento> eventos = new ArrayList<>();
    if (guia != null) {
      for (Evento evento : guia.getEventos()) {
        TipoEventoEnum tipo = evento.getTipo();
        if (tipo.equals(TipoEventoEnum.COMENTARIO) ||
            tipo.equals(TipoEventoEnum.AUTORIZADO_PARCIAL) ||
            tipo.equals(TipoEventoEnum.NEGADO) ||
            tipo.equals(TipoEventoEnum.CANCELADO)) {
          eventos.add(evento);
        }
      }
    }
    return eventos;
  }

  public Boolean checkPerfilEdicao(TipoGuiaEnum tipo, RoleEnum role) {
    return tipo.equals(TipoGuiaEnum.OPME) ? role.hasSubRole(RoleEnum.CIRURGICO) : role.hasSubRole(RoleEnum.RECEPCAO);
  }

  public Boolean renderLiberar(Guia guia, User user) {
    return guia.getTipo().equals(TipoGuiaEnum.OPME) && user.getRole().hasSubRole(RoleEnum.RECEPCAO) && guia.getEstado().equals(EstadoGuiaEnum.A_LIBERAR);
  }

  public Boolean renderResposta(Guia guia, User user) {
    return checkPerfilEdicao(guia.getTipo(), user.getRole()) && guia.getEstado().equals(EstadoGuiaEnum.ANALISE);
  }

  public Boolean renderAnalise(Guia guia, User user) {
    return checkPerfilEdicao(guia.getTipo(), user.getRole()) && guia.getEstado().equals(EstadoGuiaEnum.PENDENTE);
  }

  public Boolean renderPendente(Guia guia, User user) {
    EstadoGuiaEnum estado = guia.getEstado();
    return checkPerfilEdicao(guia.getTipo(), user.getRole()) && (estado.equals(EstadoGuiaEnum.ANALISE) || estado.equals(EstadoGuiaEnum.PARCIAL));
  }

  public Boolean renderCancelar(Guia guia, User user) {
    RoleEnum role = user.getRole();
    Boolean checkDefault = (role.hasSubRole(RoleEnum.CIRURGICO) || role.hasSubRole(RoleEnum.RECEPCAO)) && !guia.getEstado().equals(EstadoGuiaEnum.CANCELADA);
    return role.hasSubRole(RoleEnum.ADMINISTRADOR) || checkDefault;
  }

  public Boolean renderEditar(Guia guia, User user) {
    RoleEnum role = user.getRole();
    Boolean checkDefault = (role.hasSubRole(RoleEnum.CIRURGICO) || role.hasSubRole(RoleEnum.RECEPCAO)) && guia.getEstado().equals(EstadoGuiaEnum.ANALISE);
    return role.hasSubRole(RoleEnum.ADMINISTRADOR) || checkDefault;
  }

  public void addDateRestrictions(Criteria crit, Date[] range, String field) {
    if (range != null) {
      if (range[0] != null) {
        if (range[1] != null) {
          crit.add(Restrictions.between(field, range[0], range[1]));
        } else {
          crit.add(Restrictions.eq(field, range[0]));
        }
      }
    }
  }

  @Override
  public Criteria createCriteria(PaginatedSearchParam params) {
    Criteria crit = super.createCriteria();
    Map<String, Object> map = params.getParams();
    String paciente = (String) map.get("paciente");
    Prestador prestador = (Prestador) map.get("prestador");
    Convenio convenio = (Convenio) map.get("convenio");

    Date[] emissao = (Date[]) map.get("emissao");
    Date[] entrega = (Date[]) map.get("entrega");
    Date[] resposta = (Date[]) map.get("resposta");
    EstadoGuiaEnum estado = (EstadoGuiaEnum) map.get("estado");
    TipoGuiaEnum tipo = (TipoGuiaEnum) map.get("tipo");

    addDateRestrictions(crit, emissao, "emissao");
    addDateRestrictions(crit, entrega, "entrega");
    addDateRestrictions(crit, resposta, "resposta");

    if (paciente != null) {
      crit.add(Restrictions.like("paciente", paciente, MatchMode.ANYWHERE));
    }

    if (estado != null) {
      crit.add(Restrictions.eq("estado", estado));
    }

    if (prestador != null) {
      crit.add(Restrictions.eq("prestador", prestador));
    }

    if (convenio != null) {
      crit.add(Restrictions.eq("convenio", convenio));
    }

    if (tipo != null) {
      crit.add(Restrictions.eq("tipo", tipo));
    }

    return crit;
  }
}
