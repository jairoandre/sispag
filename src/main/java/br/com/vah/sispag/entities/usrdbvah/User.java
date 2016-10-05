package br.com.vah.sispag.entities.usrdbvah;

import br.com.vah.sispag.constants.RoleEnum;
import br.com.vah.sispag.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "TB_SISPAG_USUARIO", schema = "USRDBVAH")
@NamedQueries({@NamedQuery(name = User.ALL, query = "SELECT u FROM User u"),
    @NamedQuery(name = User.COUNT, query = "SELECT COUNT(u) FROM User u"),
    @NamedQuery(name = User.FIND_BY_LOGIN, query = "SELECT u FROM User u where u.login = :login")})
public class User extends BaseEntity {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  public static final String ALL = "User.populatedItems";
  public static final String COUNT = "User.countTotal";
  public static final String FIND_BY_LOGIN = "User.findByLogin";

  @Id
  @SequenceGenerator(name = "seqUser", sequenceName = "SEQ_SISPAG_USUARIO", schema = "USRDBVAH", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
  @Column(name = "ID")
  private Long id;

  @Column(name = "DS_LOGIN", nullable = false, unique = true)
  private String login;

  @Column(name = "NM_TITULO", nullable = true, length = 75)
  private String title;

  @Column(name = "NM_EMAIL", length = 75)
  private String email;

  @Column(name = "CD_ROLE", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private RoleEnum role;

  public User() {
    role = RoleEnum.USUARIO;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;

  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  @Override
  public Object getIdentity() {
    return id;
  }

  @Override
  public String getLabelForSelectItem() {
    return login;
  }

}