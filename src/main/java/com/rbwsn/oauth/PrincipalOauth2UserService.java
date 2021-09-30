package com.rbwsn.oauth;

import com.rbwsn.auth.SecurityDetails;
import com.rbwsn.constant.Role;
import com.rbwsn.controller.IndexController;
import com.rbwsn.entity.User;
import com.rbwsn.repository.UserRepository;
import com.rbwsn.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.RequestDispatcher;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

@Service
@Transactional
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @SneakyThrows
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {


        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getClientId();
        String providerId = oAuth2User.getAttribute("sub");
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name") + providerId;
        String password = passwordEncoder.encode("google1234");

        User user = userRepository.findByEmail(email);

        if (user == null) {
            user = User.builder()
                    .username(name)
                    .provider(provider)
                    .password(password)
                    .email(email)
                    .role(Role.ROLE_USER)
                    .provider_id(providerId)
                    .build();
            userRepository.save(user);
        }



        return new SecurityDetails(user, oAuth2User.getAttributes());
    }
}
