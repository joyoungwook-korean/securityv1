package com.rbwsn.oauth.provider;

import java.util.Map;

public class KakaoOAuth2UserInfo implements OAuth2UserInfo {
    private Map<String, Object> attibutes;
    private String providerId;
    private Map<String, Object> nickName;

    public KakaoOAuth2UserInfo(Map<String, Object> attibutes, String providerId, Map<String, Object> nickName) {
        this.attibutes = attibutes;
        this.providerId = providerId;
        this.nickName = nickName;
    }

    @Override
    public String getProviderId() {
        return providerId;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return (String) attibutes.get("email");
    }

    @Override
    public String getName() {
        return (String) nickName.get("nickname");
    }
}
