package br.com.vah.sispag.controllers;

import br.com.vah.sispag.constants.RoleEnum;
import br.com.vah.sispag.entities.dbamv.Setor;
import br.com.vah.sispag.entities.usrdbvah.User;
import br.com.vah.sispag.service.UserSrv;
import br.com.vah.sispag.util.DateUtility;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Login Controller class allows only authenticated users to log in to the web
 * application.
 *
 * @author Emre Simtay <emre@simtay.com>
 */
@SuppressWarnings("serial")
@Named
@SessionScoped
public class SessionCtrl implements Serializable {

  private
  transient Logger logger = Logger.getLogger("LoginController");

  private
  @Inject
  UserSrv userSrv;


  private String username;
  private String password;
  private User user;
  private Setor setor;


  /**
   * Creates a new instance of LoginController
   */
  public SessionCtrl() {
  }

  // Getters and Setters

  /**
   * @return username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  public User getUser() {
    return user;
  }

  /**
   * Listen for button clicks on the #{loginController.login} action,
   * validates the username and password entered by the user and navigates to
   * the appropriate page.
   *
   * @param actionEvent
   */
  public void login(ActionEvent actionEvent) {

    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    try {

      String navigateString = "/pages/index.xhtml";
      /**
       * Realiza autenticação
       */
      String usernameLC = this.username.toLowerCase();

      request.login(usernameLC, password);

      /**
       * Verifica se o usuário já está configurado no sistema
       */

      user = userSrv.findByLogin(usernameLC);

      /**
       * Primeiro acesso
       */
      if (user == null) {
        user = new User();
        user.setLogin(usernameLC);
        user.setEmail(usernameLC + "@vah.com.br");
        user = userSrv.create(user);
      }

      // gets the user principle and navigates to the appropriate page

      Principal principal = request.getUserPrincipal();

      try {
        logger.log(Level.INFO, "User ({0}) loging in #" + DateUtility.getCurrentDateTime(),
            request.getUserPrincipal().getName());
        context.getExternalContext().redirect(request.getContextPath() + navigateString);
      } catch (IOException ex) {
        logger.log(Level.SEVERE, "IOException, Login Controller" + "Username : " + principal.getName(), ex);
        context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
      }
    } catch (ServletException e) {
      e.printStackTrace();
      logger.log(Level.SEVERE, e.toString());
      context.addMessage(null, new FacesMessage("Erro!", "Usuário ou senha inválida."));
    }
  }

  /**
   * Listen for logout button clicks on the #{loginController.logout} action
   * and navigates to login screen.
   */
  public void logout() {

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
        .getRequest();
    logger.log(Level.INFO, "User ({0}) loging out #" + DateUtility.getCurrentDateTime(),
        request.getUserPrincipal().getName());
    if (session != null) {
      session.invalidate();
    }
    FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
        .handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml?faces-redirect=true");
  }

  public boolean isUserInRoles(String roles) {
    boolean atLeastOneRole = false;
    for (String roleStr : roles.trim().split(",")) {
      RoleEnum role = RoleEnum.valueOf(roleStr);
      RoleEnum userRole = user.getRole();
      if (userRole.equals(role) || userRole.hasSubRole(role)) {
        atLeastOneRole = true;
        break;
      }
    }
    return atLeastOneRole;
  }

  public String prosseguir() {
    return "/pages/sispag/list.xhtml?faces-redirect=true";
  }

  public Setor getSetor() {
    return setor;
  }

  public void setSetor(Setor setor) {
    this.setor = setor;
  }
}