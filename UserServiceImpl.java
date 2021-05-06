package com.ii.app.services;

import com.ii.app.config.security.JwtTokenGenerator;
import com.ii.app.dto.edit.UserEdit;
import com.ii.app.dto.in.UserIn;
import com.ii.app.dto.out.UserOut;
import com.ii.app.mappers.UserMapper;
import com.ii.app.models.user.JwtToken;
import com.ii.app.models.user.User;
import com.ii.app.models.user.UserRole;
import com.ii.app.models.user.UserRole.UserType;
import com.ii.app.repositories.UserRepository;
import com.ii.app.repositories.UserRoleRepository;
import com.ii.app.services.interfaces.EmailService;
import com.ii.app.services.interfaces.UserService;
import com.ii.app.utils.Constants;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;


	private final UserMapper userMapper;

	private final BCryptPasswordEncoder passwordEncoder;

	private final Constants CONSTANTS;


	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, UserMapper userMapper,
			BCryptPasswordEncoder passwordEncoder, Constants CONSTANTS, EmailService emailService) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.CONSTANTS = CONSTANTS;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User authenticate(String login, String password) {

		return userRepository.getEmployeByEmailAndPassword(login, password);

	}

	/*
	 * @Override public UserOut create(UserIn userIn) { User mapped =
	 * userMapper.userInToUser(userIn); mapped.setLocked(false);
	 * mapped.setCredentials(false); mapped.setEnabled(false);
	 * 
	 * String identifier = generateIdentifier(); mapped.setIdentifier(identifier);
	 * mapped.setUserRoles(Collections.singleton(
	 * userRoleRepository.findByUserType(UserRole.UserType.ROLE_USER)) );
	 * mapped.setPassword(passwordEncoder.encode(userIn.getPassword()));
	 * 
	 * emailService.sendAccountMail(mapped.getEmail(), identifier);
	 * 
	 * return us erMapper.userToUserOut(userRepository.save(mapped));
	 * 
	 * }
	 */
	private String generateIdentifier() {
		String identifier;
		do {
			identifier = RandomStringUtils.randomNumeric(CONSTANTS.USER_IDENTIFIER_LENGTH);
		} while (identifier.charAt(0) == '0' || userRepository.existsByIdentifier(identifier));

		return identifier;
	}

	@Override
	public UserOut findCurrentUser() {
		return userRepository.findByIdentifier(SecurityContextHolder.getContext().getAuthentication().getName())
				.map(userMapper::userToUserOut).orElseThrow(() -> new RuntimeException("User not found"));
	}

	/*
	 * @Override public UserOut createEmployee(UserIn userIn) { User mapped =
	 * userMapper.userInToUser(userIn); mapped.setLocked(false);
	 * mapped.setCredentials(false); mapped.setEnabled(true); String identifier =
	 * generateIdentifier(); mapped.setIdentifier(identifier);
	 * mapped.setUserRoles(Collections.singleton(userRoleRepository.findByUserType(
	 * UserRole.UserType.ROLE_EMPLOYEE)));
	 * mapped.setPassword(passwordEncoder.encode(userIn.getPassword()));
	 * 
	 * emailService.sendAccountMail(mapped.getEmail(), identifier);
	 * 
	 * return userMapper.userToUserOut(userRepository.save(mapped)); }
	 */
	@Override
	public UserOut findByIdentifier(String identifier) {
		return userMapper.userToUserOut(
				userRepository.findByIdentifier(identifier).orElseThrow(() -> new RuntimeException("User not found")));
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return userRepository.findByIdentifier(s).orElseThrow(() -> new UsernameNotFoundException("Not found"));
	}

	@Override
	public List<UserOut> findAllByUserType(UserType userType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserOut create(UserIn userIn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserOut createEmployee(UserIn userIn) {
		// TODO Auto-generated method stub
		return null;
	}
}
