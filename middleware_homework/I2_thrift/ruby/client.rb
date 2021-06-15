$:.push('gen-rb')
$:.unshift '../../lib/rb/lib'

require 'thrift'

require_relative 'gen-rb/sending_service'
include Serialization
include SendingService
require_relative 'gen-rb/serialization_types'
include Serialization

# $serializer = Thrift::Serializer.new(Thrift::BinaryProtocolFactory.new)
# $serializer = Thrift::Serializer.new(Thrift::JsonProtocolFactory.new)
$serializer = Thrift::Serializer.new(Thrift::CompactProtocolFactory.new)


def send_simple_struct(client)
  struct = SimpleStruct.new
  struct.integer = 300
  struct.decimal = 150.5
  struct.text = 'text'

  t1 = Time.now
  makeSerialization(struct)
  t2 = Time.now
  puts (t2 - t1) * 1000

  client.sendSimpleStruct(struct)
end

def send_collection_struct(client)
  struct = CollectionStruct.new

  numbers = Array.new
  decimals = Set.new

  $i = 0
  $num = 10
  while $i < $num  do
    numbers.push($i)
    decimals.add($i + 0.1)
    $i +=1
  end

  struct.numbers = numbers
  struct.decimals = decimals

  t1 = Time.now
  makeSerialization(struct)
  t2 = Time.now
  puts (t2 - t1) * 1000

  client.sendCollectionStruct(struct)
end

def send_complex_struct(client)
  struct = ComplexStruct.new

  nestedList = Array.new
  integer_to_nested = {}

  $i = 0
  $num1 = 5
  while $i < $num1  do
    words = Array.new
    integer_to_decimal = {}

    $j = 0
    $num2 = 10
    while $j < $num2 do
      words.push("text")
      integer_to_decimal[$j] = ($j + 0.1)

      $j += 1
    end

    nestedStruct = NestedStruct.new
    nestedStruct.textList = words
    nestedStruct.integerToDouble = integer_to_decimal

    nestedList.push(nestedStruct)
    integer_to_nested[$i] = nestedStruct

    $i +=1
  end

  struct.nestedStructList = nestedList
  struct.integerToNestedStruct = integer_to_nested

  t1 = Time.now
  makeSerialization(struct)
  t2 = Time.now
  puts (t2 - t1) * 1000

  client.sendComplexStruct(struct)
end

def makeSerialization(struct)
  $i = 0
  $num = 1000
  while $i < $num  do
    r = $serializer.serialize(struct)
    $i +=1
  end
end

begin
  port =  40004

  transport = Thrift::BufferedTransport.new(Thrift::Socket.new('localhost', port))

  # protocol = Thrift::BinaryProtocol.new(transport)
  # protocol = Thrift::JsonProtocol.new(transport)
  protocol = Thrift::CompactProtocol.new(transport)

  client = SendingService::Client.new(protocol)

  transport.open
  # send_simple_struct(client)
  # send_collection_struct(client)
  send_complex_struct(client)

  transport.close

rescue Thrift::Exception => tx
  print 'Thrift::Exception: ', tx.message, "\n"
end