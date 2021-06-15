package server;

import org.apache.thrift.TException;
import serialization.CollectionStruct;
import serialization.ComplexStruct;
import serialization.SendingService;
import serialization.SimpleStruct;

public class SenderHandler implements SendingService.Iface {

    @Override
    public boolean sendSimpleStruct(SimpleStruct str) throws TException
    {
        return str.integer > 300;
    }

    @Override
    public boolean sendCollectionStruct(CollectionStruct str) throws TException
    {
        return str.numbers.size() > 1;
    }

    @Override
    public boolean sendComplexStruct(ComplexStruct str) throws TException
    {
        return str.nestedStructList.size() > 1;
    }
}
