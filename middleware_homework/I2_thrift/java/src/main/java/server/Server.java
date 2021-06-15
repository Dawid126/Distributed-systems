package server;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import serialization.SendingService;

public class Server {
    public static SenderHandler handler;

    public static SendingService.Processor<SendingService.Iface> processor;

    public static void main(String [] args) {
        try {
            handler = new SenderHandler();
            processor = new SendingService.Processor<>(handler);

            Runnable simple = new Runnable() {
                public void run() {
                    simple(processor);
                }
            };

            new Thread(simple).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void simple(SendingService.Processor<SendingService.Iface> processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(40004);

            TProtocolFactory protocolFactory = getProtocolFactory(1);

            TServer server = new TSimpleServer(new TServer.Args(serverTransport).protocolFactory(protocolFactory).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static TProtocolFactory getProtocolFactory(int number) {
        switch (number) {
            case 1:
                return new TBinaryProtocol.Factory();
            case 2:
                return new TJSONProtocol.Factory();
            case 3:
                return new TCompactProtocol.Factory();
            default: return null;
        }
    }
}
