package com.ShopHoa.service;


import com.ShopHoa.entity.User;
import com.ShopHoa.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User findByUserName(String userName);

	void save(WebUser webUser);

}
