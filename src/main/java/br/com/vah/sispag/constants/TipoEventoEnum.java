package br.com.vah.sispag.constants;

/**
 * Created by jairoportela on 02/09/2016.
 */
public enum TipoEventoEnum {
  CRIACAO("Criação"),
  COMENTARIO("Comentário"),
  AUTORIZADO("Autorizado"),
  AUTORIZADO_PARCIAL("Autor. Parcial"),
  NEGADO("Negado"),
  PENDENTE("Pendente"),
  EM_ANALISE("Em análise"),
  ALTERACAO("Alteração"),
  LIBERACAO("Liberação"),
  CANCELADO("Cancelado");

  private String label;

  TipoEventoEnum(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
