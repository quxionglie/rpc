
namespace java com.quxionglie.rpc.thrift

struct SmsSendResp {
  1: i32 code,  //code为0代表发送成功
  2: string msg //msg为出错后的提示信息
}

service WrhSmsService {
    /**
    * 功能：发送验证码短信
    * @param mobile 手机号，多个手机号以,分开
    * @param content 短信内容
    * @param sign 短信签名
    * return 返回结果SmsResp
    */
    SmsSendResp sendSms(1:string mobile, 2:string content, 3:string sign)
}
 