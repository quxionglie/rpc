package com.quxionglie.rpc.protobuf2;

import com.google.protobuf.InvalidProtocolBufferException;
import com.quxionglie.rpc.protobuf2.proto.UserProto;

/**
 * @author: xionglie.qu
 * Date:   2015-09-20
 */
public class Proto2Main {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        UserProto.User user1 = UserProto.User.newBuilder()
                .setId(88)
                .setName("oo")
                .setImg("/cc.png")
                .setMobile("13800138000")
                .setCommunityId(1)
                .setCommunityName("大草原")
                .setCreateAt(System.currentTimeMillis())
                .build();


        String key = "u_" + user1.getId();
//        JedisClient.setData(key, user1.toByteArray());
//        System.out.println("user1=" + user1);

        byte[] bytes = JedisClient.getData(key);
        UserProto.User user2 = UserProto.User.parseFrom(bytes);
        System.out.println("user2=" + user2);
        System.out.println("getCommunityName=" + user2.getCommunityName());
    }
}
