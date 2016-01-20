
package com.quxionglie.rpc.grpc.client;

import com.quxionglie.rpc.grpc.proto.SmsProto;
import com.quxionglie.rpc.grpc.proto.SmsServiceGrpc;
import com.quxionglie.rpc.grpc.server.Config;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SmsClient {
    private static final Logger LOG = LoggerFactory.getLogger(SmsClient.class);

    private final ManagedChannel channel;
    private final SmsServiceGrpc.SmsServiceBlockingStub blockingStub;

    public SmsClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        blockingStub = SmsServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void sendSms(String mobile, String content) {
        try {
            LOG.info("#请求#mobile={},content={}", mobile, content);
            SmsProto.SendSmsRequest request = SmsProto.SendSmsRequest.newBuilder()
                    .setMobile(mobile)
                    .setContent(content)
                    .setSign("")
                    .build();
            SmsProto.SendSmsResp response = blockingStub.sendSms(request);
            LOG.info("#响应#code={},msg={}", response.getCode(), response.getMsg());
        } catch (RuntimeException e) {
            LOG.error("RPC failed", e);
            return;
        }
    }

    public static void main(String[] args) throws Exception {
        SmsClient client = new SmsClient("localhost", Config.GRPC_PORT);
        try {
            client.sendSms("13800138001", "date=" + new Date());
        } finally {
            client.shutdown();
        }
    }
}
