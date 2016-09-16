package br.com.vah.sispag.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 * Created by Jairoportela on 14/09/2016.
 */
@Named
public class CpfStringCvt implements Converter {
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    if (value != null) {
      return value.replace(".","").replace("-", "");
    } else {
      return null;
    }
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value instanceof String) {
      String str = (String) value;
      if (str.length() == 11) {
        return String.format("%s.%s.%s-%s", str.substring(0,3), str.substring(3, 6), str.substring(6, 9), str.substring(9));
      }
    }
    return "";

  }
}
