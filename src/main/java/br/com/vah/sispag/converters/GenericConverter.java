package br.com.vah.sispag.converters;

import br.com.vah.sispag.entities.BaseEntity;
import br.com.vah.sispag.service.AbstractSrv;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Created by jairoportela on 12/04/2016.
 */
public abstract class GenericConverter<T extends BaseEntity> implements Converter {

  public abstract AbstractSrv<T> getService();

  public Object parseId(String value) {
    try {
      return Long.parseLong(value);
    } catch (NumberFormatException e) {
      throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Objeto inválido."));
    }
  }

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    if (value != null && value.trim().length() > 0) {
      return getService().find(parseId(value));
    } else {
      return null;
    }
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value != null) {
      return String.valueOf(((T) value).getIdentity());
    } else {
      return null;
    }
  }
}
