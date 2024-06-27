package com.auth.jwtserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.jwtserver.document.RefreshToken;
import com.auth.jwtserver.document.User;
import com.auth.jwtserver.dto.SignupDto;
import com.auth.jwtserver.dto.TokenDto;
import com.auth.jwtserver.exception.UserNotFoundException;
import com.auth.jwtserver.repository.RefreshTokenRepository;
import com.auth.jwtserver.repository.UserRepository;
import com.auth.jwtserver.utility.JwtHelper;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
    RefreshTokenRepository refreshTokenRepository;

	@Autowired
    JwtHelper jwtHelper;

	@Autowired
    PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException());
	}
	
	public User findById(String id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException());
	}
	
    public List<User> findAll() {
        return userRepository.findAll();
    }

	public void deleteUserById(String id) {
		userRepository.deleteById(id);
	}

	public TokenDto signup(SignupDto dto) {
        User user = new User(dto.getUsername(), dto.getEmail(), passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setOwner(user);
        refreshTokenRepository.save(refreshToken);

        String accessToken = jwtHelper.generateAccessToken(user);
        String refreshTokenString = jwtHelper.generateRefreshToken(user, refreshToken);

        TokenDto responseData = new TokenDto(user.getId(), accessToken, refreshTokenString);
        return responseData;
    }
	
	public TokenDto editUser(SignupDto dto, String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setOwner(user);
        refreshTokenRepository.save(refreshToken);

        String accessToken = jwtHelper.generateAccessToken(user);
        String refreshTokenString = jwtHelper.generateRefreshToken(user, refreshToken);

        return new TokenDto(user.getId(), accessToken, refreshTokenString);
    }
}
