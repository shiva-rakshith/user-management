package org.rakshith.useractions.redisuseractions;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import redis.clients.jedis.Jedis;

public class RedisDBConfiguration {
    //To get redis server hostname and port from application.conf file
    Config conf = ConfigFactory.load();
    String hostname = conf.getString("redis-hostname");
    int port = conf.getInt("redis-port");

    //Connecting to redis database.
    Jedis jedis = new Jedis(hostname, port);
}
