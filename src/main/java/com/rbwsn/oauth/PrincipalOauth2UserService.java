package com.rbwsn.oauth;

import com.rbwsn.auth.SecurityDetails;
import com.rbwsn.constant.Role;

import com.rbwsn.entity.User;
import com.rbwsn.oauth.provider.*;
import com.rbwsn.repository.UserRepository;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


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


        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("Google Login");
            oAuth2UserInfo = new GoogleOAuth2UserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            System.out.println("Facebook Login");
            oAuth2UserInfo = new FacebookOAuth2UserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            System.out.println("Naver Login");
            oAuth2UserInfo = new NaverOAuth2UserProvider(oAuth2User.getAttribute("response"));
        } else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")){
            System.out.println("Kakao Login");
            Map<String,Object> nickname = oAuth2User.getAttribute("properties");


            oAuth2UserInfo = new KakaoOAuth2UserInfo(oAuth2User.getAttribute("kakao_account")
                    ,oAuth2User.getAttribute("id").toString(),
                    nickname);
        }
        else {
            System.out.println("null");
        }
        System.out.println(oAuth2UserInfo.getProviderId());
        System.out.println(oAuth2UserInfo.getProvider());


        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();
        String password = passwordEncoder.encode("oauth1234");


        User user = userRepository.findByEmailAndProvider(email,provider);


        if (user == null) {
            user = User.builder()
                    .username(name+providerId+provider)
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
