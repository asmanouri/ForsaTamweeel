package com.ii.app.services.interfaces;

import com.ii.app.dto.edit.UserEdit;
import com.ii.app.dto.in.UserIn;
import com.ii.app.dto.out.UserOut;
import com.ii.app.models.user.JwtToken;
import com.ii.app.models.user.User;
import com.ii.app.models.user.UserRole;


import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserOut create(UserIn userIn);

    UserOut findCurrentUser();

    List<UserOut> findAllByUserType(UserRole.UserType userType);


	 public User authenticate(String login, String password) ;

    UserOut createEmployee(UserIn userIn);


    UserOut findByIdentifier(String identifier);



}
