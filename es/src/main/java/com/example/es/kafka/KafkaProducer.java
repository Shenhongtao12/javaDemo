package com.example.es.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Aaron
 * @date 2020/10/26 20:04
 */
@Component
public class KafkaProducer {

    private KafkaTemplate<String, Object> kafkaTemplate;
}
