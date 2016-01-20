package com.quxionglie.rpc.thrift.client;

import com.quxionglie.rpc.thrift.SmsSendResp;
import com.quxionglie.rpc.thrift.WrhSmsService;
import com.quxionglie.rpc.thrift.server.Config;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SmsThriftClient {
    private static final Logger LOG = LoggerFactory.getLogger(SmsThriftClient.class);

    public static final int TIMEOUT = 10000;

    public static SmsSendResp sendMsg(String mobile, String content) {
        return sendMsg(mobile, content, "");
    }

    public static SmsSendResp sendMsg(String mobile, String content, String sign) {
        TTransport transport = null;
        LOG.info("#Client发送短信#mobile={},content={},sign={}", mobile, content, sign);
        try {
            transport = new TFramedTransport(new TSocket(Config.SERVER_HOST, Config.SERVER_PORT, TIMEOUT));
//            transport = new TFramedTransport(new TSocket("10.162.16.9", Config.SERVER_PORT, TIMEOUT));
            TProtocol protocol = new TBinaryProtocol(transport);
            WrhSmsService.Client client = new WrhSmsService.Client(protocol);
            transport.open();
            return client.sendSms(mobile, content, sign);
        } catch (Exception e) {
            LOG.error("#发送SMS异常#mobile={},content={},e={},{}", mobile, content, e, e.getStackTrace());
            return new SmsSendResp(-1, "异常" + e.getMessage());
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("args=" + arg);
        }
        SmsSendResp smsSendResp= SmsThriftClient.sendMsg("13800138000", "time:" + new Date());
        LOG.info("返回结果={}", smsSendResp);
    }

}