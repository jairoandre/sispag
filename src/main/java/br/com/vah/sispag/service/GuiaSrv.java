package br.com.vah.sispag.service;

import br.com.vah.sispag.constants.StatusEnum;
import br.com.vah.sispag.constants.RoleEnum;
import br.com.vah.sispag.constants.TipoEventoEnum;
import br.com.vah.sispag.constants.TipoGuiaEnum;
import br.com.vah.sispag.entities.dbamv.Convenio;
import br.com.vah.sispag.entities.dbamv.Prestador;
import br.com.vah.sispag.entities.usrdbvah.*;
import br.com.vah.sispag.exceptions.VahBusinessException;
import br.com.vah.sispag.util.PaginatedSearchParam;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;
import java.util.*;

/**
 * Created by jairoportela on 02/09/2016.
 */
@Stateless
public class GuiaSrv extends AbstractSrv<Guia> {

  public static final int GUIA = 0, OPME = 1, MAT = 2;

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
    guia.setStatus(StatusEnum.CRIADA);
    if (guia.getOpme()) {
      guia.setStatusOpme(StatusEnum.A_LIBERAR);
    }
    if (guia.getMaterial()) {
      guia.setStatusMat(StatusEnum.A_SOLICITAR);
    }
    guia.setDataAlteracao(new Date());
    return guia;
  }

  public Guia atualizar(Guia guia, User user) throws VahBusinessException {
    Guia att = find(guia.getId());

    if (!att.getDataAlteracao().equals(guia.getDataAlteracao())) {
      throw new VahBusinessException("Documento desatualizado, não foi possível persistir as mudanças.");
    }

    Map<String, Object> mapGuia = guia.toMap();
    Map<String, Object> mapAtt = att.toMap();
    List<String> list = new ArrayList<>();
    Object[] keys = mapGuia.keySet().toArray();
    for (int i = 0, len = keys.length; i < len; i++) {
      Object newValue = mapGuia.get(keys[i]);
      Object attValue = mapAtt.get(keys[i]);

      if (!(newValue instanceof Set)) {
        if (newValue != null && !newValue.equals(attValue)) {
          list.add((String) keys[i]);
        }
      }
    }

    String mensagem = "";
    for (int i = 0, len = list.size(); i < len; i++) {
      mensagem += list.get(i);
      if (i < (len - 1)) {
        mensagem += ", ";
      } else {
        mensagem += ".";
      }
    }

    guia.setDataAlteracao(new Date());

    att = getEm().merge(guia);
    Evento ev = adicionarEvento(TipoEventoEnum.ATUALIZACAO, att, user);
    if (!mensagem.isEmpty()) {
      ev.setMensagem(mensagem);
    }

    if (att.getOpme() && att.getStatusOpme() == null) {
      att.setStatusOpme(StatusEnum.A_LIBERAR);
    }
    if (att.getMaterial() && att.getStatusMat() == null) {
      guia.setStatusMat(StatusEnum.A_SOLICITAR);
    }
    return att;
  }

  public Guia liberar(Guia guia, User user) {
    return changeStatus(guia, user, StatusEnum.LIBERADA, null, OPME);

  }

  public Guia autorizar(Guia guia, User user) {
    return changeStatus(guia, user, StatusEnum.AUTORIZADA, null, GUIA);

  }

  public Guia autorizarOpme(Guia guia, User user) {
    return changeStatus(guia, user, StatusEnum.AUTORIZADA, null, OPME);

  }

  public Guia autorizarMat(Guia guia, User user) {
    return changeStatus(guia, user, StatusEnum.AUTORIZADA, null, MAT);

  }

  public Guia solicitar(Guia guia, User user) throws VahBusinessException {
    return changeStatus(guia, user, StatusEnum.ANALISE, null, GUIA);

  }

  public Guia solicitarOpme(Guia guia, User user) {
    return changeStatus(guia, user, StatusEnum.ANALISE, null, OPME);
  }

  public Guia solicitarMaterial(Guia guia, User user) {
    return changeStatus(guia, user, StatusEnum.ANALISE, null, MAT);
  }

  public Guia pendente(Guia guia, User user, String motivo) {
    return changeStatus(guia, user, StatusEnum.PENDENTE, motivo, GUIA);
  }

  public Guia pendenteOpme(Guia guia, User user, String motivo) {
    return changeStatus(guia, user, StatusEnum.PENDENTE, motivo, OPME);
  }

  public Guia negar(Guia guia, User user, String motivo) {
    return changeStatus(guia, user, StatusEnum.NEGADA, motivo, GUIA);
  }

  public Guia negarOpme(Guia guia, User user, String motivo) {
    return changeStatus(guia, user, StatusEnum.NEGADA, motivo, OPME);
  }

  public Guia negarMaterial(Guia guia, User user, String motivo) {
    return changeStatus(guia, user, StatusEnum.NEGADA, motivo, MAT);
  }

  public Guia parcial(Guia guia, User user, String motivo) {
    return changeStatus(guia, user, StatusEnum.PARCIAL, motivo, GUIA);
  }

  public Guia parcialOpme(Guia guia, User user, String motivo) {
    return changeStatus(guia, user, StatusEnum.PARCIAL, motivo, OPME);
  }

  public Guia cancelar(Guia guia, User user, String motivo) {
    return changeStatus(guia, user, StatusEnum.CANCELADA, motivo, GUIA);
  }

  public Guia reAnalise(Guia guia, User user) {
    return changeStatus(guia, user, StatusEnum.RE_ANALISE, null, GUIA);
  }

  public Guia reAnaliseOPME(Guia guia, User user) {
    return changeStatus(guia, user, StatusEnum.RE_ANALISE, null, OPME);
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
        if (evento.getMensagem() != null && !evento.getMensagem().isEmpty()) {
          eventos.add(evento);
        }
      }
    }
    Collections.sort(eventos, new Comparator<Evento>() {
      @Override
      public int compare(Evento o1, Evento o2) {
        return o1.getId().compareTo(o2.getId()) * -1;
      }
    });

    return eventos;
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

  public Guia initializeLists(Guia guia) {
    Guia att = find(guia.getId());
    guia.setItens(new LinkedHashSet<>(att.getItens()));
    guia.setOpmes(new LinkedHashSet<>(att.getOpmes()));
    guia.setMateriais(new LinkedHashSet<>(att.getMateriais()));
    guia.setEventos(new LinkedHashSet<>(att.getEventos()));
    guia.setAnexos(new LinkedHashSet<>(att.getAnexos()));
    return guia;
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
    StatusEnum status = (StatusEnum) map.get("status");
    StatusEnum statusOpme = (StatusEnum) map.get("statusOpme");
    StatusEnum statusMat = (StatusEnum) map.get("statusMat");
    TipoGuiaEnum tipo = (TipoGuiaEnum) map.get("tipo");
    Boolean opme = (Boolean) map.get("opme");
    Boolean material = (Boolean) map.get("material");

    addDateRestrictions(crit, emissao, "dataEmissao");
    addDateRestrictions(crit, entrega, "dataEntrega");
    addDateRestrictions(crit, resposta, "dataResposta");

    if (paciente != null) {
      crit.add(Restrictions.like("paciente", paciente, MatchMode.ANYWHERE));
    }

    if (status != null) {
      crit.add(Restrictions.eq("status", status));
    } else {
      crit.add(Restrictions.ne("status", StatusEnum.CANCELADA));
    }

    if (statusOpme != null) {
      crit.add(Restrictions.eq("statusOpme", statusOpme));
    }

    if (statusMat != null) {
      crit.add(Restrictions.eq("statusMat", statusMat));
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

    if (opme != null && opme) {
      crit.add(Restrictions.eq("opme", true));
    }

    if (material != null && material) {
      crit.add(Restrictions.eq("material", true));
    }

    crit.setFetchMode("itens", FetchMode.SELECT);
    crit.setFetchMode("opmes", FetchMode.SELECT);
    crit.setFetchMode("materiais", FetchMode.SELECT);
    crit.setFetchMode("eventos", FetchMode.SELECT);
    crit.setFetchMode("anexos", FetchMode.SELECT);

    return crit;
  }

  private Guia changeStatus(Guia guia, User user, StatusEnum to, String msg, int statusOper) {
    guia = find(guia.getId());
    Evento evt = adicionarEvento(TipoEventoEnum.STATUS, guia, user);

    StatusEnum from;
    String complemento;

    switch (statusOper) {
      case GUIA:
        from = guia.getStatus();
        guia.setStatus(to);
        complemento = "Guia";
        break;
      case OPME:
        from = guia.getStatusOpme();
        guia.setStatusOpme(to);
        complemento = "OPME";
        break;
      default:
        from = guia.getStatusMat();
        guia.setStatusMat(to);
        complemento = "Mat./Estq.";
        break;
    }

    evt.setFrom(from.getLabel());
    evt.setTo(to.getLabel());
    evt.setComplemento(complemento);
    evt.setMensagem(msg);
    return guia;
  }

}
