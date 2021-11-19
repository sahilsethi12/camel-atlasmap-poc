package com.hdfc.authentication;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

public class Demo {

	// must have a main method spring-boot can run
	
	
	

	static ConfigurationBuilder clientBuilder;

	public static void newRemoteCacheManager() throws Exception {
		clientBuilder = new ConfigurationBuilder();

		clientBuilder.addServer().host("localhost").port(11222).security().authentication().username("admin")
				.password("admin").serverName("infinispan").realm("default").saslMechanism("DIGEST-MD5");

		RemoteCacheManager rcm = new RemoteCacheManager(clientBuilder.build());

		RemoteCache<Object, Object> cache = rcm.getCache("customcache");

		Object name = cache.get("fileName");

		System.out.println("------------" + name);

	}

}
