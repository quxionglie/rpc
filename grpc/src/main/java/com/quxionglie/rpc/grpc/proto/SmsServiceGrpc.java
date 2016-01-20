package com.quxionglie.rpc.grpc.proto;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class SmsServiceGrpc {

  private SmsServiceGrpc() {}

  public static final String SERVICE_NAME = "sms.SmsService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<SmsProto.SendSmsRequest,
      SmsProto.SendSmsResp> METHOD_SEND_SMS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "sms.SmsService", "sendSms"),
          io.grpc.protobuf.ProtoUtils.marshaller(SmsProto.SendSmsRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(SmsProto.SendSmsResp.getDefaultInstance()));

  public static SmsServiceStub newStub(io.grpc.Channel channel) {
    return new SmsServiceStub(channel);
  }

  public static SmsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SmsServiceBlockingStub(channel);
  }

  public static SmsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SmsServiceFutureStub(channel);
  }

  public static interface SmsService {

    public void sendSms(SmsProto.SendSmsRequest request,
                        io.grpc.stub.StreamObserver<SmsProto.SendSmsResp> responseObserver);
  }

  public static interface SmsServiceBlockingClient {

    public SmsProto.SendSmsResp sendSms(SmsProto.SendSmsRequest request);
  }

  public static interface SmsServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<SmsProto.SendSmsResp> sendSms(
            SmsProto.SendSmsRequest request);
  }

  public static class SmsServiceStub extends io.grpc.stub.AbstractStub<SmsServiceStub>
      implements SmsService {
    private SmsServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmsServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SmsServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmsServiceStub(channel, callOptions);
    }

    @Override
    public void sendSms(SmsProto.SendSmsRequest request,
        io.grpc.stub.StreamObserver<SmsProto.SendSmsResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SEND_SMS, getCallOptions()), request, responseObserver);
    }
  }

  public static class SmsServiceBlockingStub extends io.grpc.stub.AbstractStub<SmsServiceBlockingStub>
      implements SmsServiceBlockingClient {
    private SmsServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmsServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SmsServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmsServiceBlockingStub(channel, callOptions);
    }

    @Override
    public SmsProto.SendSmsResp sendSms(SmsProto.SendSmsRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_SEND_SMS, getCallOptions()), request);
    }
  }

  public static class SmsServiceFutureStub extends io.grpc.stub.AbstractStub<SmsServiceFutureStub>
      implements SmsServiceFutureClient {
    private SmsServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SmsServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SmsServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SmsServiceFutureStub(channel, callOptions);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<SmsProto.SendSmsResp> sendSms(
        SmsProto.SendSmsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SEND_SMS, getCallOptions()), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final SmsService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
      .addMethod(
        METHOD_SEND_SMS,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              SmsProto.SendSmsRequest,
              SmsProto.SendSmsResp>() {
            @Override
            public void invoke(
                SmsProto.SendSmsRequest request,
                io.grpc.stub.StreamObserver<SmsProto.SendSmsResp> responseObserver) {
              serviceImpl.sendSms(request, responseObserver);
            }
          })).build();
  }
}
