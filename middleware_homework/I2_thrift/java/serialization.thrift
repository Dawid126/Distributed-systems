namespace java serialization
namespace py serialization
namespace rb serialization


struct SimpleStruct
{
    1:i32 integer,
    2:double decimal,
    3:string text
}

struct CollectionStruct
{
    1:list<i64> numbers,
    2:set<double> decimals
}

struct NestedStruct
{
    1:list<string> textList,
    2:map<i32, double> integerToDouble
}

struct ComplexStruct
{
    1:list<NestedStruct> nestedStructList,
    2:map<i32, NestedStruct> integerToNestedStruct
}

service SendingService
{
    bool sendSimpleStruct(1:SimpleStruct str),
    bool sendCollectionStruct(1:CollectionStruct str),
    bool sendComplexStruct(1:ComplexStruct str),
}

