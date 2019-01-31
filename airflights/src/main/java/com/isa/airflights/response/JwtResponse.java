package com.isa.airflights.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
	private Collection<? extends GrantedAuthority> authorities;
	private int idCompany;

	public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities, int id) {
		this.token = accessToken;
		this.username = username;
		this.authorities = authorities;
		this.idCompany = id;
	}
	
	

    public int getIdCompany() {
		return idCompany;
	}



	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}



	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}



	public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}