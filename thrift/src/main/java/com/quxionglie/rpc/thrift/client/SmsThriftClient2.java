package com.quxionglie.rpc.thrift.client;

import com.quxionglie.rpc.thrift.SmsSendResp;
import com.quxionglie.rpc.thrift.WrhSmsService;
import com.quxionglie.rpc.thrift.server.Config;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsThriftClient2 {
    private static final Logger LOG = LoggerFactory.getLogger(SmsThriftClient2.class);

    public static final int TIMEOUT = 10000;
    public static WrhSmsService.Client client = null;

    static {
        try {
            initClient();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static SmsSendResp sendMsg(String mobile, String content) {
        return sendMsg(mobile, content, "");
    }

    public static SmsSendResp sendMsg(String mobile, String content, String sign) {
        //LOG.info("#Client发送短信#mobile={},content={},sign={}", mobile, content, sign);
        try {
            return client.sendSms(mobile, content, sign);
        } catch (Exception e) {
            LOG.error("#发送SMS异常#mobile={},content={},e={},{}", mobile, content, e, e.getStackTrace());
            return new SmsSendResp(-1, "异常" + e.getMessage());
        } finally {
        }
    }

    private static void initClient() throws TTransportException {
        TTransport transport = new TFramedTransport(new TSocket(Config.SERVER_HOST, Config.SERVER_PORT, TIMEOUT));
        TProtocol protocol = new TBinaryProtocol(transport);
        SmsThriftClient2.client = new WrhSmsService.Client(protocol);
        transport.open();
    }

    public static void main(String[] arjgs) {
        for (int i = 0; i < 1; i++) {
            new Thread(new SendSmsThread(i * 1000)).start();
        }

        try {
            new Thread().sleep(100 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class SendSmsThread implements Runnable {
        int start;

        public SendSmsThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                String content = "seq" + (start + i);
                SmsSendResp sendResp = SmsThriftClient2.sendMsg("13800138000", content);
                LOG.info("i={},sendMsg={},sendResp={}", i, content, sendResp.getMsg());
            }
        }
    }

}