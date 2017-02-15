package br.com.vah.sispag.service;

import br.com.vah.sispag.reports.BalancoDTO;
import br.com.vah.sispag.reports.ColaboradoraDTO;
import br.com.vah.sispag.reports.ReportLoader;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.primefaces.model.StreamedContent;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Jairoportela on 03/01/2017.
 */
@Stateless
public class RelatorioSrv implements Serializable {

  @PersistenceContext
  private EntityManager em;

  @Inject
  private ReportLoader reportLoader;

  public List<BalancoDTO> searchBalanco(Date begin, Date end) {

    String sql =
        "SELECT " +
            "  NM_CONVENIO, " +
            "  SUM(CRIADAS)                               TOTAL_CRIADAS, " +
            "  SUM(AUTORIZADAS)                           TOTAL_AUTORIZADAS, " +
            "  ROUND(AVG(DT_AUTORIZACAO - DT_CRIACAO)) AS MEDIA_DIAS_AUTORIZACAO " +
            "FROM " +
            "  ( " +
            "    SELECT " +
            "      G.ID, " +
            "      C.NM_CONVENIO, " +
            "      MAX(DECODE(E.CD_TIPO, 0, 1))   AS CRIADAS, " +
            "      MAX(DECODE(E.CD_TIPO, 0, DT_EVENTO)) AS DT_CRIACAO, " +
            "      MAX(DECODE(E.CD_TIPO, 3, 1))   AS AUTORIZADAS, " +
            "      MAX(DECODE(E.CD_TIPO, 3, DT_EVENTO)) AS DT_AUTORIZACAO " +
            "    FROM USRDBVAH.TB_SISPAG_GUIA G " +
            "      LEFT JOIN USRDBVAH.TB_SISPAG_EVENTO E ON E.ID_GUIA = G.ID " +
            "      LEFT JOIN DBAMV.CONVENIO C ON C.CD_CONVENIO = G.CD_CONVENIO " +
            "    WHERE " +
            "      E.DT_EVENTO BETWEEN :BEGIN AND :END " +
            "      AND (E.CD_TIPO = 0 OR (E.CD_TIPO = 3 AND E.NM_PARA = 'Autorizada' AND E.NM_COMPLEMENTO = 'Guia')) " +
            "    GROUP BY G.ID, C.NM_CONVENIO " +
            "  ) " +
            "GROUP BY NM_CONVENIO " +
            "ORDER BY TOTAL_CRIADAS DESC";

    Session session = getSession();


    SQLQuery query = session.createSQLQuery(sql);

    query.setParameter("BEGIN", begin);
    query.setParameter("END", end);

    List<Object[]> rows = (List<Object[]>) query.list();

    List<BalancoDTO> result = new ArrayList<>();

    for (Object[] row : rows) {
      String convenio = (String) row[0];
      Integer criadas = 0;
      if (row[1] != null) {
        criadas = ((BigDecimal) row[1]).intValue();
      }
      Integer autorizadas = 0;
      if (row[2] != null) {
        autorizadas = ((BigDecimal) row[2]).intValue();
      }
      Integer media = 0;
      if (row[3] != null) {
        media = ((BigDecimal) row[3]).intValue();
      }
      result.add(new BalancoDTO(convenio, criadas, autorizadas, media));
    }

    return result;
  }

  public List<ColaboradoraDTO> listaProdutividade(Date begin, Date end) {

    String sql =
        "SELECT " +
            "  DS_LOGIN, " +
            "  NM_TITULO, " +
            "  SUM(CRIADAS)                            TOTAL_CRIADAS, " +
            "  SUM(AUTORIZADAS)                        TOTAL_AUTORIZADAS, " +
            "  ROUND(AVG(DT_AUTORIZACAO - DT_CRIACAO)) MEDIA_AUTORIZACAO " +
            "FROM ( " +
            "  SELECT " +
            "    G.ID, " +
            "    U.DS_LOGIN, " +
            "    U.NM_TITULO, " +
            "    MAX(DECODE(E.CD_TIPO, 0, 1))         CRIADAS, " +
            "    MAX(DECODE(E.CD_TIPO, 0, DT_EVENTO)) DT_CRIACAO, " +
            "    MAX(DECODE(E.CD_TIPO, 3, 1))         AUTORIZADAS, " +
            "    MAX(DECODE(E.CD_TIPO, 3, DT_EVENTO)) DT_AUTORIZACAO " +
            "  FROM USRDBVAH.TB_SISPAG_GUIA G " +
            "    LEFT JOIN USRDBVAH.TB_SISPAG_EVENTO E ON E.ID_GUIA = G.ID " +
            "    LEFT JOIN USRDBVAH.TB_SISPAG_USUARIO U ON E.ID_USUARIO = U.ID " +
            "  WHERE " +
            "    E.DT_EVENTO BETWEEN :BEGIN AND :END " +
            "    AND (E.CD_TIPO = 0 OR (E.CD_TIPO = 3 AND E.NM_PARA = 'Autorizada' AND E.NM_COMPLEMENTO = 'Guia')) " +
            "    AND (G.CD_STATUS = 0 OR G.CD_STATUS = 3) " +
            "  GROUP BY G.ID, U.DS_LOGIN, U.NM_TITULO) " +
            "GROUP BY DS_LOGIN, NM_TITULO " +
            "ORDER BY MEDIA_AUTORIZACAO DESC";

    Session session = getSession();


    SQLQuery query = session.createSQLQuery(sql);

    query.setParameter("BEGIN", begin);
    query.setParameter("END", end);

    List<Object[]> rows = (List<Object[]>) query.list();

    List<ColaboradoraDTO> result = new ArrayList<>();

    for (Object[] row : rows) {
      String colaboradora = (String) row[0];
      String titulo = (String) row[1];
      if (titulo != null && titulo.length() > 0) {
        colaboradora = titulo;
      }
      Integer criadas = 0;
      if (row[2] != null) {
        criadas = ((BigDecimal) row[2]).intValue();
      }
      Integer autorizadas = 0;
      if (row[3] != null) {
        autorizadas = ((BigDecimal) row[3]).intValue();
      }
      Integer media = 0;
      if (row[4] != null) {
        media = ((BigDecimal) row[4]).intValue();
      }
      result.add(new ColaboradoraDTO(colaboradora, criadas, autorizadas, media));
    }

    return result;
  }

  public Session getSession() {
    return em.unwrap(Session.class);
  }

  private StreamedContent generalReport(Date begin, Date end, String fileName, List list) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    Map<String, Object> params = new HashMap();
    params.put("begin", sdf2.format(begin));
    params.put("end", sdf2.format(end));
    return reportLoader.printReport(fileName, String.format("%s-%s", fileName, sdf.format(new Date())), list, params);
  }

  public StreamedContent relatorioBalanco(Date begin, Date end) {
    return generalReport(begin, end, "balanco", searchBalanco(begin, end));
  }

  public StreamedContent relatorioProdutividade(Date begin, Date end) {
    return generalReport(begin, end, "produtividade", listaProdutividade(begin, end));
  }


}
