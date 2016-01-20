package com.quxionglie.rpc.grpc.server;

import com.quxionglie.rpc.grpc.proto.SmsProto;
import com.quxionglie.rpc.grpc.proto.SmsServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;


/**
 * Server that manages startup/shutdown of a  server.
 */
public class SmsServer {
    private static final Logger LOG = LoggerFactory.getLogger(SmsServer.class);

    private Server server;

    private void start() throws Exception {
        server = ServerBuilder.forPort(Config.GRPC_PORT)
                .addService(SmsServiceGrpc.bindService(new SmsServiceImpl()))
                .build()
                .start();
        LOG.info("Server started, listening on " + Config.GRPC_PORT);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                SmsServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws Exception {
        final SmsServer server = new SmsServer();
        server.start();
        server.blockUntilShutdown();
    }

    private class SmsServiceImpl implements SmsServiceGrpc.SmsService {
        @Override
        public void sendSms(SmsProto.SendSmsRequest request, StreamObserver<SmsProto.SendSmsResp> responseObserver) {
            LOG.info("#请求#mobile={},content={}", request.getMobile(), request.getContent());
            SmsProto.SendSmsResp reply = SmsProto.SendSmsResp.newBuilder()
                    .setCode(0)
                    .setMsg("发送成功" + new Random().nextInt(1000))
                    .build();
            LOG.info("#响应#mobile={},code={},msg={}", request.getMobile(), reply.getCode(), reply.getMsg());
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
