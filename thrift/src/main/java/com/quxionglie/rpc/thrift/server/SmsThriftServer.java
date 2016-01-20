package com.quxionglie.rpc.thrift.server;

import com.quxionglie.rpc.thrift.WrhSmsService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class SmsThriftServer {
    private static final Logger LOG = LoggerFactory.getLogger(SmsThriftServer.class);

    public void start() {
        try {
            InetSocketAddress bindAddr = new InetSocketAddress(Config.SERVER_HOST, Config.SERVER_PORT);
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(bindAddr);
            TBinaryProtocol.Factory profactory = new TBinaryProtocol.Factory();

            TProcessor tprocessor = new WrhSmsService.Processor<WrhSmsService.Iface>(new WrhSmsServiceImpl());
            TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(profactory);

            TServer server = new TThreadedSelectorServer(tArgs);
            LOG.info("Starting SMS server,host={},port={}", Config.SERVER_HOST, Config.SERVER_PORT);
            server.serve();
        } catch (Exception e) {
            LOG.error("Server start error!!!", e);
        }
    }

    public static void main(String[] args) {
        new SmsThriftServer().start();
    }

}
