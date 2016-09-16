package br.com.vah.sispag.controllers;

import br.com.vah.sispag.constants.*;
import br.com.vah.sispag.entities.dbamv.Convenio;
import br.com.vah.sispag.entities.dbamv.Prestador;
import br.com.vah.sispag.entities.dbamv.ProFat;
import br.com.vah.sispag.entities.usrdbvah.Anexo;
import br.com.vah.sispag.entities.usrdbvah.Evento;
import br.com.vah.sispag.entities.usrdbvah.Guia;
import br.com.vah.sispag.entities.usrdbvah.ItemGuia;
import br.com.vah.sispag.service.AbstractSrv;
import br.com.vah.sispag.service.GuiaSrv;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by jairoportela on 02/09/2016.
 */
@Named
@ViewScoped
public class GuiaCtrl extends AbstractCtrl<Guia> {

  private
  @Inject
  transient Logger logger;

  private
  @Inject
  GuiaSrv service;

  private
  @Inject
  SessionCtrl sessionCtrl;

  private final List<SelectItem> tipos = TipoGuiaEnum.getSelectItems();

  private final List<SelectItem> estadosForCombo = EstadoGuiaEnum.getSelectItems();

  private final List<SelectItem> operacoesForCombo = OperacaoDataEnum.getSelectItens();

  private ProFat proFatToAdd;

  private String motivo;

  private String nomePaciente;

  private Prestador prestador;

  private Convenio convenio;

  private Date[] emissao = new Date[2];

  private OperacaoDataEnum emissaoOp = OperacaoDataEnum.EQ;

  private Date[] resposta = new Date[2];

  private OperacaoDataEnum respostaOp = OperacaoDataEnum.EQ;

  private Date[] entrega = new Date[2];

  private OperacaoDataEnum entregaOp = OperacaoDataEnum.EQ;

  private EstadoGuiaEnum estado;

  private TipoGuiaEnum tipo;

  private List<String> filtros;

  private Map<String, GuiaFieldsEnum> mapFiltros;

  @PostConstruct
  public void init() {
    logger.info(this.getClass().getSimpleName() + " created");
    setItem(createNewItem());
    initLazyModel(service, "itens", "eventos");
    prepareSearch();
  }

  public void preOpenModalToVisualize(Guia guia) {
    preOpenModal(guia);
    setEditing(false);
  }

  public void preOpenModal() {
    setEditing(true);
    setItem(createNewItem());
    motivo = "";
  }

  public void preOpenModal(Guia guia) {
    setEditing(true);
    motivo = "";
    setItem(guia);
  }

  @Override
  public AbstractSrv<Guia> getService() {
    return service;
  }

  @Override
  public Logger getLogger() {
    return logger;
  }

  @Override
  public Guia createNewItem() {
    return new Guia();
  }

  @Override
  public String path() {
    return "guias";
  }

  @Override
  public String getEntityName() {
    return "Guia";
  }

  public List<SelectItem> getTipos() {
    return tipos;
  }

  public ProFat getProFatToAdd() {
    return proFatToAdd;
  }

  public void setProFatToAdd(ProFat proFatToAdd) {
    this.proFatToAdd = proFatToAdd;
  }

  public void onProFatSelect(SelectEvent evt) {
    ProFat proFat = (ProFat) evt.getObject();
    if (proFat != null) {
      ItemGuia itemGuia = new ItemGuia();
      itemGuia.setGuia(getItem());
      itemGuia.setNome(proFat.getCbhpm() + " - " + proFat.getNome());
      getItem().getItens().add(itemGuia);
    }
    proFatToAdd = null;
  }

  public void onChangeTipo(SelectEvent evt) {
    getItem().getItens().clear();
  }

  public void removeItemGuia(ItemGuia itemGuia) {
    getItem().getItens().remove(itemGuia);
  }

  private String filterDateLabel(OperacaoDataEnum op, String label, Date[] range) {
    switch (op) {
      case BW:
        return String.format("<b>%s</b> entre <b>%s</b> e <b>%s</b>", label, formatDate(range[0]), formatDate(range[1]));
      default:
        return String.format("<b>%s</b> %s <b>%s</b>", label, op.getLabel(), formatDate(range[0]));
    }
  }

  private String formatDate(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    if (date == null) {
      return "?";
    } else {
      return sdf.format(date);
    }
  }

  @Override
  public void prepareSearch() {
    super.prepareSearch();
    filtros = new ArrayList<>();
    mapFiltros = new HashMap<>();
    if (nomePaciente != null && !nomePaciente.isEmpty()) {
      String pacienteKey = "<b>Paciente: </b>" + nomePaciente;
      filtros.add(pacienteKey);
      mapFiltros.put(pacienteKey, GuiaFieldsEnum.PACIENTE);
      setSearchParam("paciente", nomePaciente);
    }
    if (prestador != null) {
      String prestadorKey = "<b>Prestador: </b>" + prestador.getNome();
      filtros.add(prestadorKey);
      mapFiltros.put(prestadorKey, GuiaFieldsEnum.PRESTADOR);
      setSearchParam("prestador", prestador);
    }
    if (convenio != null) {
      String convenioKey = "<b>Convênio: </b>" + convenio.getNome();
      filtros.add(convenioKey);
      mapFiltros.put(convenioKey, GuiaFieldsEnum.CONVENIO);
      setSearchParam("convenio", convenio);
    }
    if (emissao[0] != null) {
      String emissaoKey = filterDateLabel(emissaoOp, "Emissão", emissao);
      filtros.add(emissaoKey);
      mapFiltros.put(emissaoKey, GuiaFieldsEnum.EMISSAO);
      setSearchParam("emissao", emissao);
    }
    if (entrega[0] != null) {
      String entregaKey = filterDateLabel(entregaOp, "Entrega", entrega);
      filtros.add(entregaKey);
      mapFiltros.put(entregaKey, GuiaFieldsEnum.ENTREGA);
      setSearchParam("entrega", emissao);
    }
    if (resposta[0] != null) {
      String respostaKey = filterDateLabel(respostaOp, "Resposta", resposta);
      filtros.add(respostaKey);
      mapFiltros.put(respostaKey, GuiaFieldsEnum.RESPOSTA);
      setSearchParam("resposta", resposta);
    }
    if (estado != null) {
      String estadoKey = "<b>Estado: </b>" + estado.getLabel();
      filtros.add(estadoKey);
      mapFiltros.put(estadoKey, GuiaFieldsEnum.ESTADO);
      setSearchParam("estado", estado);
    }
    if (tipo != null) {
      String tipoKey = "<b>Tipo: </b>" + tipo.getLabel();
      filtros.add(tipoKey);
      mapFiltros.put(tipoKey, GuiaFieldsEnum.TIPO);
      setSearchParam("tipo", tipo);
    } else {
      if (sessionCtrl.getUser().getRole().equals(RoleEnum.CIRURGICO)) {
        setSearchParam("tipo", TipoGuiaEnum.OPME);
      }
    }
  }

  public void removeFilterItem(String key) {
    GuiaFieldsEnum field = mapFiltros.get(key);
    switch (field) {
      case PACIENTE:
        nomePaciente = null;
        break;
      case EMISSAO:
        emissao = new Date[2];
        break;
      case RESPOSTA:
        resposta = new Date[2];
        break;
      case ENTREGA:
        entrega = new Date[2];
        break;
      case ESTADO:
        estado = null;
        break;
      case PRESTADOR:
        prestador = null;
        break;
      case TIPO:
        tipo = null;
        break;
      case CONVENIO:
        convenio = null;
        break;
      default:
        break;
    }
    prepareSearch();
  }

  @Override
  public String doSave() {
    if (getItem().getId() == null) {
      setItem(service.criar(getItem(), sessionCtrl.getUser()));
    } else {
      setItem(service.atualizar(getItem(), sessionCtrl.getUser()));
    }
    return super.doSave();
  }

  public void autorizar(Guia guia) {
    try {
      guia = service.autorizar(guia, sessionCtrl.getUser());
      service.update(guia);
      addInfoMsg("Guia autorizada com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void emAnalise(Guia guia) {
    try {
      guia = service.emAnalise(guia, sessionCtrl.getUser());
      service.update(guia);
      addInfoMsg("Estado de guia modificado com sucesso (Em análise).");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void liberar(Guia guia) {
    try {
      guia = service.liberar(guia, sessionCtrl.getUser());
      service.update(guia);
      addInfoMsg("Guia liberada com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void negar() {
    try {
      Guia guia = service.negar(getItem(), sessionCtrl.getUser(), motivo);
      service.update(guia);
      addInfoMsg("Guia negada com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void parcial() {
    try {
      Guia guia = service.parcial(getItem(), sessionCtrl.getUser(), motivo);
      service.update(guia);
      addInfoMsg("Autorização parcial com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void cancelar() {
    try {
      Guia guia = service.cancelar(getItem(), sessionCtrl.getUser(), motivo);
      service.update(guia);
      addInfoMsg("Guia cancelada com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void pendente() {
    try {
      Guia guia = service.pendente(getItem(), sessionCtrl.getUser(), motivo);
      service.update(guia);
      addInfoMsg("Guia marcada como pendente com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void comentar() {
    try {
      Guia guia = service.comentar(getItem(), sessionCtrl.getUser(), motivo);
      service.update(guia);
      addInfoMsg("Comentário registrado com sucesso");
    } catch (Exception e) {
      addErrorMsg(e);
    }
  }

  public void uploadAnexo(FileUploadEvent evt) {
    UploadedFile file = evt.getFile();
    Anexo anexo = new Anexo();
    anexo.setGuia(getItem());
    anexo.setNome(file.getFileName());
    anexo.setArquivo(file.getContents());
    getItem().getAnexos().add(anexo);
  }

  public void removerAnexo(Anexo anexo) {
    getItem().getAnexos().remove(anexo);
  }

  public StreamedContent download(Anexo anexo) {
    InputStream report = new ByteArrayInputStream(anexo.getArquivo());
    DefaultStreamedContent dsc = new DefaultStreamedContent(report);
    dsc.setContentType("application/pdf");
    dsc.setName(anexo.getNome());
    return dsc;
  }

  public List<Evento> comentariosGuia() {
    return service.comentariosGuia(getItem());
  }

  public Boolean renderLiberar(Guia guia) {
    return service.renderLiberar(guia, sessionCtrl.getUser());
  }

  public Boolean renderResposta(Guia guia) {
    return service.renderResposta(guia, sessionCtrl.getUser());
  }

  public Boolean renderPendente(Guia guia) {
    return service.renderPendente(guia, sessionCtrl.getUser());
  }

  public Boolean renderCancelar(Guia guia) {
    return service.renderCancelar(guia, sessionCtrl.getUser());
  }

  public Boolean renderAnalise(Guia guia) {
    return service.renderAnalise(guia, sessionCtrl.getUser());
  }

  public Boolean renderEditar(Guia guia) {
    return service.renderEditar(guia, sessionCtrl.getUser());
  }

  public String getMotivo() {
    return motivo;
  }

  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }

  public String getNomePaciente() {
    return nomePaciente;
  }

  public void setNomePaciente(String nomePaciente) {
    this.nomePaciente = nomePaciente;
  }

  public Convenio getConvenio() {
    return convenio;
  }

  public void setConvenio(Convenio convenio) {
    this.convenio = convenio;
  }

  public Date[] getEmissao() {
    return emissao;
  }

  public void setEmissao(Date[] emissao) {
    this.emissao = emissao;
  }

  public OperacaoDataEnum getEmissaoOp() {
    return emissaoOp;
  }

  public void setEmissaoOp(OperacaoDataEnum emissaoOp) {
    this.emissaoOp = emissaoOp;
  }

  public Date[] getResposta() {
    return resposta;
  }

  public void setResposta(Date[] resposta) {
    this.resposta = resposta;
  }

  public Prestador getPrestador() {
    return prestador;
  }

  public void setPrestador(Prestador prestador) {
    this.prestador = prestador;
  }

  public OperacaoDataEnum getRespostaOp() {
    return respostaOp;
  }

  public void setRespostaOp(OperacaoDataEnum respostaOp) {
    this.respostaOp = respostaOp;
  }

  public Date[] getEntrega() {
    return entrega;
  }

  public void setEntrega(Date[] entrega) {
    this.entrega = entrega;
  }

  public OperacaoDataEnum getEntregaOp() {
    return entregaOp;
  }

  public void setEntregaOp(OperacaoDataEnum entregaOp) {
    this.entregaOp = entregaOp;
  }

  public EstadoGuiaEnum getEstado() {
    return estado;
  }

  public void setEstado(EstadoGuiaEnum estado) {
    this.estado = estado;
  }

  public TipoGuiaEnum getTipo() {
    return tipo;
  }

  public void setTipo(TipoGuiaEnum tipo) {
    this.tipo = tipo;
  }

  public List<String> getFiltros() {
    return filtros;
  }

  public Map<String, GuiaFieldsEnum> getMapFiltros() {
    return mapFiltros;
  }

  public List<SelectItem> getEstadosForCombo() {
    return estadosForCombo;
  }

  public List<SelectItem> getOperacoesForCombo() {
    return operacoesForCombo;
  }
}
