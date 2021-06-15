#
# Autogenerated by Thrift Compiler (0.14.1)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

from thrift.Thrift import TType, TMessageType, TFrozenDict, TException, TApplicationException
from thrift.protocol.TProtocol import TProtocolException
from thrift.TRecursive import fix_spec

import sys

from thrift.transport import TTransport
all_structs = []


class SimpleStruct(object):
    """
    Attributes:
     - integer
     - decimal
     - text

    """


    def __init__(self, integer=None, decimal=None, text=None,):
        self.integer = integer
        self.decimal = decimal
        self.text = text

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.I32:
                    self.integer = iprot.readI32()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.DOUBLE:
                    self.decimal = iprot.readDouble()
                else:
                    iprot.skip(ftype)
            elif fid == 3:
                if ftype == TType.STRING:
                    self.text = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('SimpleStruct')
        if self.integer is not None:
            oprot.writeFieldBegin('integer', TType.I32, 1)
            oprot.writeI32(self.integer)
            oprot.writeFieldEnd()
        if self.decimal is not None:
            oprot.writeFieldBegin('decimal', TType.DOUBLE, 2)
            oprot.writeDouble(self.decimal)
            oprot.writeFieldEnd()
        if self.text is not None:
            oprot.writeFieldBegin('text', TType.STRING, 3)
            oprot.writeString(self.text.encode('utf-8') if sys.version_info[0] == 2 else self.text)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class CollectionStruct(object):
    """
    Attributes:
     - numbers
     - decimals

    """


    def __init__(self, numbers=None, decimals=None,):
        self.numbers = numbers
        self.decimals = decimals

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.LIST:
                    self.numbers = []
                    (_etype3, _size0) = iprot.readListBegin()
                    for _i4 in range(_size0):
                        _elem5 = iprot.readI64()
                        self.numbers.append(_elem5)
                    iprot.readListEnd()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.SET:
                    self.decimals = set()
                    (_etype9, _size6) = iprot.readSetBegin()
                    for _i10 in range(_size6):
                        _elem11 = iprot.readDouble()
                        self.decimals.add(_elem11)
                    iprot.readSetEnd()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('CollectionStruct')
        if self.numbers is not None:
            oprot.writeFieldBegin('numbers', TType.LIST, 1)
            oprot.writeListBegin(TType.I64, len(self.numbers))
            for iter12 in self.numbers:
                oprot.writeI64(iter12)
            oprot.writeListEnd()
            oprot.writeFieldEnd()
        if self.decimals is not None:
            oprot.writeFieldBegin('decimals', TType.SET, 2)
            oprot.writeSetBegin(TType.DOUBLE, len(self.decimals))
            for iter13 in self.decimals:
                oprot.writeDouble(iter13)
            oprot.writeSetEnd()
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class NestedStruct(object):
    """
    Attributes:
     - textList
     - integerToDouble

    """


    def __init__(self, textList=None, integerToDouble=None,):
        self.textList = textList
        self.integerToDouble = integerToDouble

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.LIST:
                    self.textList = []
                    (_etype17, _size14) = iprot.readListBegin()
                    for _i18 in range(_size14):
                        _elem19 = iprot.readString().decode('utf-8', errors='replace') if sys.version_info[0] == 2 else iprot.readString()
                        self.textList.append(_elem19)
                    iprot.readListEnd()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.MAP:
                    self.integerToDouble = {}
                    (_ktype21, _vtype22, _size20) = iprot.readMapBegin()
                    for _i24 in range(_size20):
                        _key25 = iprot.readI32()
                        _val26 = iprot.readDouble()
                        self.integerToDouble[_key25] = _val26
                    iprot.readMapEnd()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('NestedStruct')
        if self.textList is not None:
            oprot.writeFieldBegin('textList', TType.LIST, 1)
            oprot.writeListBegin(TType.STRING, len(self.textList))
            for iter27 in self.textList:
                oprot.writeString(iter27.encode('utf-8') if sys.version_info[0] == 2 else iter27)
            oprot.writeListEnd()
            oprot.writeFieldEnd()
        if self.integerToDouble is not None:
            oprot.writeFieldBegin('integerToDouble', TType.MAP, 2)
            oprot.writeMapBegin(TType.I32, TType.DOUBLE, len(self.integerToDouble))
            for kiter28, viter29 in self.integerToDouble.items():
                oprot.writeI32(kiter28)
                oprot.writeDouble(viter29)
            oprot.writeMapEnd()
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class ComplexStruct(object):
    """
    Attributes:
     - nestedStructList
     - integerToNestedStruct

    """


    def __init__(self, nestedStructList=None, integerToNestedStruct=None,):
        self.nestedStructList = nestedStructList
        self.integerToNestedStruct = integerToNestedStruct

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.LIST:
                    self.nestedStructList = []
                    (_etype33, _size30) = iprot.readListBegin()
                    for _i34 in range(_size30):
                        _elem35 = NestedStruct()
                        _elem35.read(iprot)
                        self.nestedStructList.append(_elem35)
                    iprot.readListEnd()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.MAP:
                    self.integerToNestedStruct = {}
                    (_ktype37, _vtype38, _size36) = iprot.readMapBegin()
                    for _i40 in range(_size36):
                        _key41 = iprot.readI32()
                        _val42 = NestedStruct()
                        _val42.read(iprot)
                        self.integerToNestedStruct[_key41] = _val42
                    iprot.readMapEnd()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('ComplexStruct')
        if self.nestedStructList is not None:
            oprot.writeFieldBegin('nestedStructList', TType.LIST, 1)
            oprot.writeListBegin(TType.STRUCT, len(self.nestedStructList))
            for iter43 in self.nestedStructList:
                iter43.write(oprot)
            oprot.writeListEnd()
            oprot.writeFieldEnd()
        if self.integerToNestedStruct is not None:
            oprot.writeFieldBegin('integerToNestedStruct', TType.MAP, 2)
            oprot.writeMapBegin(TType.I32, TType.STRUCT, len(self.integerToNestedStruct))
            for kiter44, viter45 in self.integerToNestedStruct.items():
                oprot.writeI32(kiter44)
                viter45.write(oprot)
            oprot.writeMapEnd()
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)
all_structs.append(SimpleStruct)
SimpleStruct.thrift_spec = (
    None,  # 0
    (1, TType.I32, 'integer', None, None, ),  # 1
    (2, TType.DOUBLE, 'decimal', None, None, ),  # 2
    (3, TType.STRING, 'text', 'UTF8', None, ),  # 3
)
all_structs.append(CollectionStruct)
CollectionStruct.thrift_spec = (
    None,  # 0
    (1, TType.LIST, 'numbers', (TType.I64, None, False), None, ),  # 1
    (2, TType.SET, 'decimals', (TType.DOUBLE, None, False), None, ),  # 2
)
all_structs.append(NestedStruct)
NestedStruct.thrift_spec = (
    None,  # 0
    (1, TType.LIST, 'textList', (TType.STRING, 'UTF8', False), None, ),  # 1
    (2, TType.MAP, 'integerToDouble', (TType.I32, None, TType.DOUBLE, None, False), None, ),  # 2
)
all_structs.append(ComplexStruct)
ComplexStruct.thrift_spec = (
    None,  # 0
    (1, TType.LIST, 'nestedStructList', (TType.STRUCT, [NestedStruct, None], False), None, ),  # 1
    (2, TType.MAP, 'integerToNestedStruct', (TType.I32, None, TType.STRUCT, [NestedStruct, None], False), None, ),  # 2
)
fix_spec(all_structs)
del all_structs
