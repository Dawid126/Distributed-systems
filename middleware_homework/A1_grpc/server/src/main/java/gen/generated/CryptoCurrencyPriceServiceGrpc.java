package gen.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.37.0)",
    comments = "Source: crypto_currency_price_info.proto")
public final class CryptoCurrencyPriceServiceGrpc {

  private CryptoCurrencyPriceServiceGrpc() {}

  public static final String SERVICE_NAME = "CryptoCurrencyPriceService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<CryptoCurrencyPriceRequest,
      CryptoCurrencyPriceInfo> getSubscribeOnCryptoCurrencyPriceInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubscribeOnCryptoCurrencyPriceInfo",
      requestType = CryptoCurrencyPriceRequest.class,
      responseType = CryptoCurrencyPriceInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<CryptoCurrencyPriceRequest,
      CryptoCurrencyPriceInfo> getSubscribeOnCryptoCurrencyPriceInfoMethod() {
    io.grpc.MethodDescriptor<CryptoCurrencyPriceRequest, CryptoCurrencyPriceInfo> getSubscribeOnCryptoCurrencyPriceInfoMethod;
    if ((getSubscribeOnCryptoCurrencyPriceInfoMethod = CryptoCurrencyPriceServiceGrpc.getSubscribeOnCryptoCurrencyPriceInfoMethod) == null) {
      synchronized (CryptoCurrencyPriceServiceGrpc.class) {
        if ((getSubscribeOnCryptoCurrencyPriceInfoMethod = CryptoCurrencyPriceServiceGrpc.getSubscribeOnCryptoCurrencyPriceInfoMethod) == null) {
          CryptoCurrencyPriceServiceGrpc.getSubscribeOnCryptoCurrencyPriceInfoMethod = getSubscribeOnCryptoCurrencyPriceInfoMethod =
              io.grpc.MethodDescriptor.<CryptoCurrencyPriceRequest, CryptoCurrencyPriceInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubscribeOnCryptoCurrencyPriceInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CryptoCurrencyPriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CryptoCurrencyPriceInfo.getDefaultInstance()))
              .setSchemaDescriptor(new CryptoCurrencyPriceServiceMethodDescriptorSupplier("SubscribeOnCryptoCurrencyPriceInfo"))
              .build();
        }
      }
    }
    return getSubscribeOnCryptoCurrencyPriceInfoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CryptoCurrencyPriceServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CryptoCurrencyPriceServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CryptoCurrencyPriceServiceStub>() {
        @java.lang.Override
        public CryptoCurrencyPriceServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CryptoCurrencyPriceServiceStub(channel, callOptions);
        }
      };
    return CryptoCurrencyPriceServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CryptoCurrencyPriceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CryptoCurrencyPriceServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CryptoCurrencyPriceServiceBlockingStub>() {
        @java.lang.Override
        public CryptoCurrencyPriceServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CryptoCurrencyPriceServiceBlockingStub(channel, callOptions);
        }
      };
    return CryptoCurrencyPriceServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CryptoCurrencyPriceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CryptoCurrencyPriceServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CryptoCurrencyPriceServiceFutureStub>() {
        @java.lang.Override
        public CryptoCurrencyPriceServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CryptoCurrencyPriceServiceFutureStub(channel, callOptions);
        }
      };
    return CryptoCurrencyPriceServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CryptoCurrencyPriceServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void subscribeOnCryptoCurrencyPriceInfo(CryptoCurrencyPriceRequest request,
        io.grpc.stub.StreamObserver<CryptoCurrencyPriceInfo> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeOnCryptoCurrencyPriceInfoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSubscribeOnCryptoCurrencyPriceInfoMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                CryptoCurrencyPriceRequest,
                CryptoCurrencyPriceInfo>(
                  this, METHODID_SUBSCRIBE_ON_CRYPTO_CURRENCY_PRICE_INFO)))
          .build();
    }
  }

  /**
   */
  public static final class CryptoCurrencyPriceServiceStub extends io.grpc.stub.AbstractAsyncStub<CryptoCurrencyPriceServiceStub> {
    private CryptoCurrencyPriceServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CryptoCurrencyPriceServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CryptoCurrencyPriceServiceStub(channel, callOptions);
    }

    /**
     */
    public void subscribeOnCryptoCurrencyPriceInfo(CryptoCurrencyPriceRequest request,
        io.grpc.stub.StreamObserver<CryptoCurrencyPriceInfo> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSubscribeOnCryptoCurrencyPriceInfoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CryptoCurrencyPriceServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CryptoCurrencyPriceServiceBlockingStub> {
    private CryptoCurrencyPriceServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CryptoCurrencyPriceServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CryptoCurrencyPriceServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<CryptoCurrencyPriceInfo> subscribeOnCryptoCurrencyPriceInfo(
        CryptoCurrencyPriceRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSubscribeOnCryptoCurrencyPriceInfoMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CryptoCurrencyPriceServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CryptoCurrencyPriceServiceFutureStub> {
    private CryptoCurrencyPriceServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CryptoCurrencyPriceServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CryptoCurrencyPriceServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SUBSCRIBE_ON_CRYPTO_CURRENCY_PRICE_INFO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CryptoCurrencyPriceServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CryptoCurrencyPriceServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBSCRIBE_ON_CRYPTO_CURRENCY_PRICE_INFO:
          serviceImpl.subscribeOnCryptoCurrencyPriceInfo((CryptoCurrencyPriceRequest) request,
              (io.grpc.stub.StreamObserver<CryptoCurrencyPriceInfo>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CryptoCurrencyPriceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CryptoCurrencyPriceServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return CryptoCurrencyPriceInfoOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CryptoCurrencyPriceService");
    }
  }

  private static final class CryptoCurrencyPriceServiceFileDescriptorSupplier
      extends CryptoCurrencyPriceServiceBaseDescriptorSupplier {
    CryptoCurrencyPriceServiceFileDescriptorSupplier() {}
  }

  private static final class CryptoCurrencyPriceServiceMethodDescriptorSupplier
      extends CryptoCurrencyPriceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CryptoCurrencyPriceServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CryptoCurrencyPriceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CryptoCurrencyPriceServiceFileDescriptorSupplier())
              .addMethod(getSubscribeOnCryptoCurrencyPriceInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
