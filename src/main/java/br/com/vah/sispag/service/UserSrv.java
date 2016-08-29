package br.com.vah.sispag.service;


import br.com.vah.sispag.entities.usrdbvah.User;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author jairoportela
 *
 */
@Stateless
public class UserSrv extends AbstractSrv<User> {
	
	public UserSrv(){
		super(User.class);
	}
	
	public User findByLogin(String login){
		Map<String, Object> params = new HashMap<>();
		params.put("login", login);
		List<User> resultList = findWithNamedQuery(User.FIND_BY_LOGIN, params);
		return resultList.isEmpty() ? null : resultList.get(0);
	}

}
