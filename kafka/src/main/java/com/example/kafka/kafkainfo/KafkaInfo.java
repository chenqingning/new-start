package com.example.kafka.kafkainfo;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class KafkaInfo {
    public static void main(String[] args) throws ExecutionException {
        // 设置Kafka集群的地址
        String bootstrapServers = "localhost:9092";

        // 创建AdminClient
        Map servers = Maps.newHashMap();
        servers.put("bootstrap.servers", bootstrapServers);
        try (AdminClient adminClient = AdminClient.create(servers)) {
            // 配置ListTopicsOptions以获取详细信息
            ListTopicsOptions options = new ListTopicsOptions();
            options.listInternal(true);

            // 获取主题列表
            ListTopicsResult topicsResult = adminClient.listTopics(options);
            Set<String> topicNames = topicsResult.names().get();

            // 遍历每个主题并获取分区信息
            for (String topicName : topicNames) {
                Set topics = new HashSet<>();
                topics.add(topicName);
                int numPartitions = adminClient.describeTopics(topicNames)
                        .topicNameValues().get(topicName).get().partitions().size();
                System.out.println("Topic: " + topicName + ", Partitions: " + numPartitions);
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
