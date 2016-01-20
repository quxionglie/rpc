package com.quxionglie.rpc.protobuf3;

import com.google.protobuf.InvalidProtocolBufferException;
import com.quxionglie.rpc.protobuf3.proto.UserProto;

/**
 * @author: xionglie.qu
 * Date:   2015-09-20
 */
public class Proto3Main {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        UserProto.User user1 = UserProto.User.newBuilder()
                .setId(88)
                .setName("oo")
                .setImg("/cc.png")
                .setMobile("13800138000")
                //.setCommunityId(0)
                .setCommunityName("做东")
                .setCreateAt(System.currentTimeMillis())
                .build();


        String key = "u3_" + user1.getId();
//        JedisClient.setData(key, user1.toByteArray());
//        System.out.println("user1=" + user1);

        byte[] bytes = user1.toByteArray();
        //byte[] bytes = JedisClient.getData(key);
        UserProto.User user2 = UserProto.User.parseFrom(bytes);
        System.out.println("user2=" + user2);
        System.out.println("user2 getCommunityId=" + user2.getCommunityId());
        System.out.println("getCommunityName=" + user2.getCommunityName());

//        ➜  rpc git:(master) ✗ redis-cli
//        127.0.0.1:6379> get u3_88
//        "\bX\x12\x02oo\x1a\a/cc.png\"\x0b13800138000(\x012\x06\xe5\x81\x9a\xe4\xb8\x9c8\xba\xed\xf7\xcd\xfe)"
    }
}
