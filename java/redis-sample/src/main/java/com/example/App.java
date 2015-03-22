package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;

@SpringBootApplication
public class App {
    @Bean
    public RedisConnectionFactory getConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        return factory;
    }

    @Bean
    public RedisTemplate<String, Record> getRedisTemplate() {
        RedisTemplate<String, Record> template = new RedisTemplate<>();

        template.setConnectionFactory(getConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer(Record.class));

        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}

@Controller
class Hello implements CommandLineRunner {
    @Autowired
    RedisTemplate<String, Record> template;

    @Override
    public void run(String[] args) throws Exception {
        template.opsForValue().set("foo",
                new Record() {{
                    setName("foo");
                    setValue("bar");
                }});

        Record foo = template.opsForValue().get("foo");
        System.out.println(foo.getValue());
    }
}

class Record {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
