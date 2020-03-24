package com.norra.config;

import io.redisearch.client.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JRedisearchConfig {

	@Value("${redisearch.host-index:host}")
	private String hostIndexName;

	@Value("${redisearch.guest-index:guest}")
	private String guestIndexName;

	@Value("${redisearch.pool-size:10}")
	private int poolSize;

	@Value("${redisearch.host:127.0.0.1}")
	private String host;

	@Value("${redisearch.port:6379}")
	private int port;

	@Value("${redisearch.timeout:2147483647}")
	private int timeout;

	@Value("${redisearch.password:#{null}}")
	private String password;

	@Value("${redisearch.database:0}")
	private int database;

	private static JedisPoolConfig initPoolConfig(int poolSize) {
		JedisPoolConfig conf = new JedisPoolConfig();
		conf.setMaxTotal(poolSize);
		conf.setTestOnBorrow(false);
		conf.setTestOnReturn(false);
		conf.setTestOnCreate(false);
		conf.setTestWhileIdle(false);
		conf.setMinEvictableIdleTimeMillis(60000L);
		conf.setTimeBetweenEvictionRunsMillis(30000L);
		conf.setNumTestsPerEvictionRun(-1);
		conf.setFairness(true);
		return conf;
	}

	@Bean
	public Client redisearchClientHosts() {
		return new Client(hostIndexName, new JedisPool(initPoolConfig(poolSize),
				host, port, timeout, password, database));
	}

	@Bean
	public Client redisearchClientGuests() {
		return new Client(guestIndexName, new JedisPool(initPoolConfig(poolSize),
				host, port, timeout, password, database));
	}
}
