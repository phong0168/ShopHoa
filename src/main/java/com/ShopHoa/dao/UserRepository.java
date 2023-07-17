package com.ShopHoa.dao;

import com.ShopHoa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUserName(String userName);

}
