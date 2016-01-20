package com.quxionglie.rpc.thrift.server;

import com.quxionglie.rpc.thrift.SmsSendResp;
import com.quxionglie.rpc.thrift.WrhSmsService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class WrhSmsServiceImpl implements WrhSmsService.Iface {
    private static final Logger LOG = LoggerFactory.getLogger(WrhSmsServiceImpl.class);

    @Override
    public SmsSendResp sendSms(String mobile, String content, String sign) throws TException {
        LOG.debug("#Server发送短信#mobile={},content={},sign={}", mobile, content, sign);
        // 发送短信...
        //ssswewe
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new SmsSendResp(0, "发送成功,"+content);
    }


}
