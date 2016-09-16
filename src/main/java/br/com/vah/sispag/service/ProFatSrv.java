package br.com.vah.sispag.service;

import br.com.vah.sispag.entities.dbamv.ProFat;
import br.com.vah.sispag.util.PaginatedSearchParam;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;

import javax.ejb.Stateless;
import java.util.Map;

/**
 * Created by jairoportela on 05/09/2016.
 */
@Stateless
public class ProFatSrv extends AbstractSrv<ProFat> {

  public ProFatSrv() {
    super(ProFat.class);
  }

  @Override
  public Criteria createCriteria(PaginatedSearchParam params) {
    Map<String, Object> paramsMap = params.getParams();
    String id = (String) paramsMap.get("id");
    String nome = (String) paramsMap.get("nome");
    String codigoSus = (String) paramsMap.get("codigoSus");
    String tipo = (String) paramsMap.get("tipo");
    Criteria crit = createCriteria();
    Disjunction disjunction = Restrictions.disjunction();
    disjunction.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
    disjunction.add(Restrictions.ilike("codigoSus", codigoSus, MatchMode.ANYWHERE));


    if (tipo == null) {
      crit.createAlias("gruPro", "g").add(Restrictions.ne("g.tipo", "OP"));
      disjunction.add(Restrictions.ilike("h.id.cbhpm", id, MatchMode.ANYWHERE));
      crit.createAlias("hierarquia", "h").add(disjunction);
    } else {
      crit.createAlias("gruPro", "g").add(Restrictions.eq("g.tipo", "OP"));
      crit.add(disjunction);
    }
    return crit;
  }
}
