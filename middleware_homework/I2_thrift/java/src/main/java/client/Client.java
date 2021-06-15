package client;

import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import serialization.*;

import java.util.*;

public class Client {
    public static void main(String [] args) {

        try {
            TTransport transport;

            transport = new TSocket("127.0.0.1", 40004);
            transport.open();
            
            TProtocol protocol = getProtocol(1, transport);
            SendingService.Client client = new SendingService.Client(protocol);
            
            //sendSimpleStruct(client);
            //sendCollectionStruct(client);
            sendComplexStruct(client);

            transport.close();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    private static void sendSimpleStruct(SendingService.Client client) throws TException
    {
        long start;
        long stop;

        SimpleStruct struct = new SimpleStruct(300, 150.5, "text");
        
        TSerializer serializer = new TSerializer(Objects.requireNonNull(getProtocolFactory(1)));
        byte[] r;
        start = System.nanoTime();
        for(int i = 0; i < 1000; i++)
            r = serializer.serialize(struct);
        stop = System.nanoTime();
        
        System.out.println((stop-start)/1000000.0);
        client.sendSimpleStruct(struct);
    }

    private static void sendCollectionStruct(SendingService.Client client) throws TException
    {

        long start;
        long stop;

        int numOfElem = 10;
        List<Long> numbers = new ArrayList<>(numOfElem);
        Set<Double> decimals = new HashSet<>(numOfElem);
        for(int i = 0; i < numOfElem; i++)
        {
            numbers.add((long) i);
            decimals.add((i + 0.1));
        }
        CollectionStruct struct = new CollectionStruct(numbers, decimals);

        TSerializer serializer = new TSerializer(Objects.requireNonNull(getProtocolFactory(1)));
        byte[] r;
        start = System.nanoTime();
        for(int i = 0; i < 1000; i++)
            r = serializer.serialize(struct);
        stop = System.nanoTime();

        System.out.println((stop - start)/1000000.0);
        client.sendCollectionStruct(struct);
    }

    private static void sendComplexStruct(SendingService.Client client) throws TException
    {

        long start;
        long stop;

        int numOfElem = 10;
        List<NestedStruct> nested = new ArrayList<>(5);
        Map<Integer, NestedStruct> integerToNested = new HashMap<>(5);
        for(int i = 0; i < 5; i++)
        {
            List<String> words = new ArrayList<>(numOfElem);
            Map<Integer, Double> integerToDecimal = new HashMap<>(numOfElem);
            for(int j = 0; j < numOfElem; j++)
            {
                words.add(String.valueOf(j));
                integerToDecimal.put(j, (double) j);
            }
            nested.add(new NestedStruct(words, integerToDecimal));
            integerToNested.put(i, new NestedStruct(words, integerToDecimal));
        }
        ComplexStruct struct = new ComplexStruct(nested, integerToNested);

        TSerializer serializer = new TSerializer(Objects.requireNonNull(getProtocolFactory(1)));
        byte[] r;
        start = System.nanoTime();
        for(int i = 0; i < 1000; i++)
            r = serializer.serialize(struct);
        stop = System.nanoTime();

        System.out.println((stop - start)/1000000.0);
        client.sendComplexStruct(struct);
    }
    private static TProtocol getProtocol(int number, TTransport transport) {
        switch (number) {
            case 1:
                return new TBinaryProtocol(transport);
            case 2:
                return new TJSONProtocol(transport);
            case 3:
                return new TCompactProtocol(transport);
            default: return null;
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
