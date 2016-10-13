package com.woorea.openstack.keystone.v3.api;

import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.keystone.v3.model.Authentication;
import com.woorea.openstack.keystone.v3.model.Token;

public class TokensResource {
	
	private final OpenStackClient CLIENT;
	
	public TokensResource(OpenStackClient client) {
		CLIENT = client;
	}

	public class Delete extends OpenStackRequest<Void> {
		public Delete(String id) {
	         super(CLIENT, HttpMethod.DELETE, "/auth/tokens", null, Void.class);
	     }
	}
	
	public Authenticate authenticate(Authentication authentication) {
		return new Authenticate(authentication);
	}
	
	public Delete revoke(String id) {
		return new Delete(id);
	}

	public OpenStackRequest<Token> show() {
		return CLIENT.get("/auth/tokens", Token.class);
	}

	public OpenStackRequest<String> getRevoked() {
		return CLIENT.get("/auth/tokens/OS-PKI/revoked", String.class);
	}
	
	public class Authenticate extends OpenStackRequest<Token> {
		
		private Authentication authentication;
		
		public Authenticate() {
			
		}
		
		public Authenticate(Authentication authentication) {
			super(CLIENT, HttpMethod.POST, "/auth/tokens", Entity.json(authentication), Token.class);
			this.authentication = authentication;
		}

	}
	
}
