/**
 *  Copyright 2005-2018 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package com.hdfc.authentication;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath:spring/camel-context.xml" })
public class Application {

	// must have a main method spring-boot can run
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	ConfigurationBuilder clientBuilder;

	@Bean(initMethod = "start", destroyMethod = "stop")
    public RemoteCacheManager newRemoteCacheManager() {
        clientBuilder = new ConfigurationBuilder();

        clientBuilder.addServer()
                .host("localhost")
                .port(11222)
                .security()
                .authentication()
                .username("admin")
                .password("admin")
                .serverName("infinispan")
                .realm("default")
                .saslMechanism("DIGEST-MD5");

   

        return  new RemoteCacheManager(clientBuilder.build());
    }


}
