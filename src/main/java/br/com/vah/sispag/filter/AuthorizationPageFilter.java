package br.com.vah.sispag.filter;


import br.com.vah.sispag.constants.RoleEnum;
import br.com.vah.sispag.controllers.SessionCtrl;
import br.com.vah.sispag.constants.RestrictedViews;
import br.com.vah.sispag.entities.dbamv.Setor;
import br.com.vah.sispag.entities.usrdbvah.User;


import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * To prevent user from going back to Login page if the user already logged in
 *
 * @author Emre Simtay <emre@simtay.com>
 */
public class AuthorizationPageFilter implements Filter {

  private
  @Inject
  SessionCtrl sessionCtrl;

  public static final String ERROR_ACCESS_DENIED = "/error-access-denied.xhtml";
  public static final String LOGIN = "/login.xhtml";
  public static final String INDEX = "/index.html";
  public static final String SELECIONAR_SETOR = "/selecionarSetor.xhtml";


  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    if (request.getUserPrincipal() != null) {

      User user = sessionCtrl.getUser();

      String[] splitPath = request.getRequestURI().split(request.getContextPath());

      String view = splitPath[0];

      if (splitPath.length > 1) {
        view = splitPath[1];
      }

      /**
       * Verifica se a view requisitada se encontra na enumeração de views restritas
       */
      RestrictedViews restrictView = RestrictedViews.getByView(view);

      if (restrictView == null || restrictView.checkRoles(user.getRole())) {
        filterChain.doFilter(servletRequest, servletResponse);
      } else {
        response.sendRedirect(request.getContextPath() + ERROR_ACCESS_DENIED);
      }

    } else {
      // Usuário não logado
      response.sendRedirect(request.getContextPath() + LOGIN);
    }


  }

  @Override
  public void destroy() {
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }
}