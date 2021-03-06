/**
 * Autogenerated by Thrift Compiler (0.14.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package serialization;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.1)", date = "2021-06-07")
public class NestedStruct implements org.apache.thrift.TBase<NestedStruct, NestedStruct._Fields>, java.io.Serializable, Cloneable, Comparable<NestedStruct> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("NestedStruct");

  private static final org.apache.thrift.protocol.TField TEXT_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("textList", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField INTEGER_TO_DOUBLE_FIELD_DESC = new org.apache.thrift.protocol.TField("integerToDouble", org.apache.thrift.protocol.TType.MAP, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new NestedStructStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new NestedStructTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.util.List<java.lang.String> textList; // required
  public @org.apache.thrift.annotation.Nullable java.util.Map<java.lang.Integer,java.lang.Double> integerToDouble; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TEXT_LIST((short)1, "textList"),
    INTEGER_TO_DOUBLE((short)2, "integerToDouble");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // TEXT_LIST
          return TEXT_LIST;
        case 2: // INTEGER_TO_DOUBLE
          return INTEGER_TO_DOUBLE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TEXT_LIST, new org.apache.thrift.meta_data.FieldMetaData("textList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.INTEGER_TO_DOUBLE, new org.apache.thrift.meta_data.FieldMetaData("integerToDouble", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(NestedStruct.class, metaDataMap);
  }

  public NestedStruct() {
  }

  public NestedStruct(
    java.util.List<java.lang.String> textList,
    java.util.Map<java.lang.Integer,java.lang.Double> integerToDouble)
  {
    this();
    this.textList = textList;
    this.integerToDouble = integerToDouble;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public NestedStruct(NestedStruct other) {
    if (other.isSetTextList()) {
      java.util.List<java.lang.String> __this__textList = new java.util.ArrayList<java.lang.String>(other.textList);
      this.textList = __this__textList;
    }
    if (other.isSetIntegerToDouble()) {
      java.util.Map<java.lang.Integer,java.lang.Double> __this__integerToDouble = new java.util.HashMap<java.lang.Integer,java.lang.Double>(other.integerToDouble);
      this.integerToDouble = __this__integerToDouble;
    }
  }

  public NestedStruct deepCopy() {
    return new NestedStruct(this);
  }

  @Override
  public void clear() {
    this.textList = null;
    this.integerToDouble = null;
  }

  public int getTextListSize() {
    return (this.textList == null) ? 0 : this.textList.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.lang.String> getTextListIterator() {
    return (this.textList == null) ? null : this.textList.iterator();
  }

  public void addToTextList(java.lang.String elem) {
    if (this.textList == null) {
      this.textList = new java.util.ArrayList<java.lang.String>();
    }
    this.textList.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.lang.String> getTextList() {
    return this.textList;
  }

  public NestedStruct setTextList(@org.apache.thrift.annotation.Nullable java.util.List<java.lang.String> textList) {
    this.textList = textList;
    return this;
  }

  public void unsetTextList() {
    this.textList = null;
  }

  /** Returns true if field textList is set (has been assigned a value) and false otherwise */
  public boolean isSetTextList() {
    return this.textList != null;
  }

  public void setTextListIsSet(boolean value) {
    if (!value) {
      this.textList = null;
    }
  }

  public int getIntegerToDoubleSize() {
    return (this.integerToDouble == null) ? 0 : this.integerToDouble.size();
  }

  public void putToIntegerToDouble(int key, double val) {
    if (this.integerToDouble == null) {
      this.integerToDouble = new java.util.HashMap<java.lang.Integer,java.lang.Double>();
    }
    this.integerToDouble.put(key, val);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Map<java.lang.Integer,java.lang.Double> getIntegerToDouble() {
    return this.integerToDouble;
  }

  public NestedStruct setIntegerToDouble(@org.apache.thrift.annotation.Nullable java.util.Map<java.lang.Integer,java.lang.Double> integerToDouble) {
    this.integerToDouble = integerToDouble;
    return this;
  }

  public void unsetIntegerToDouble() {
    this.integerToDouble = null;
  }

  /** Returns true if field integerToDouble is set (has been assigned a value) and false otherwise */
  public boolean isSetIntegerToDouble() {
    return this.integerToDouble != null;
  }

  public void setIntegerToDoubleIsSet(boolean value) {
    if (!value) {
      this.integerToDouble = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case TEXT_LIST:
      if (value == null) {
        unsetTextList();
      } else {
        setTextList((java.util.List<java.lang.String>)value);
      }
      break;

    case INTEGER_TO_DOUBLE:
      if (value == null) {
        unsetIntegerToDouble();
      } else {
        setIntegerToDouble((java.util.Map<java.lang.Integer,java.lang.Double>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case TEXT_LIST:
      return getTextList();

    case INTEGER_TO_DOUBLE:
      return getIntegerToDouble();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case TEXT_LIST:
      return isSetTextList();
    case INTEGER_TO_DOUBLE:
      return isSetIntegerToDouble();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof NestedStruct)
      return this.equals((NestedStruct)that);
    return false;
  }

  public boolean equals(NestedStruct that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_textList = true && this.isSetTextList();
    boolean that_present_textList = true && that.isSetTextList();
    if (this_present_textList || that_present_textList) {
      if (!(this_present_textList && that_present_textList))
        return false;
      if (!this.textList.equals(that.textList))
        return false;
    }

    boolean this_present_integerToDouble = true && this.isSetIntegerToDouble();
    boolean that_present_integerToDouble = true && that.isSetIntegerToDouble();
    if (this_present_integerToDouble || that_present_integerToDouble) {
      if (!(this_present_integerToDouble && that_present_integerToDouble))
        return false;
      if (!this.integerToDouble.equals(that.integerToDouble))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetTextList()) ? 131071 : 524287);
    if (isSetTextList())
      hashCode = hashCode * 8191 + textList.hashCode();

    hashCode = hashCode * 8191 + ((isSetIntegerToDouble()) ? 131071 : 524287);
    if (isSetIntegerToDouble())
      hashCode = hashCode * 8191 + integerToDouble.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(NestedStruct other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetTextList(), other.isSetTextList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTextList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.textList, other.textList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetIntegerToDouble(), other.isSetIntegerToDouble());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIntegerToDouble()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.integerToDouble, other.integerToDouble);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("NestedStruct(");
    boolean first = true;

    sb.append("textList:");
    if (this.textList == null) {
      sb.append("null");
    } else {
      sb.append(this.textList);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("integerToDouble:");
    if (this.integerToDouble == null) {
      sb.append("null");
    } else {
      sb.append(this.integerToDouble);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class NestedStructStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public NestedStructStandardScheme getScheme() {
      return new NestedStructStandardScheme();
    }
  }

  private static class NestedStructStandardScheme extends org.apache.thrift.scheme.StandardScheme<NestedStruct> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, NestedStruct struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TEXT_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list16 = iprot.readListBegin();
                struct.textList = new java.util.ArrayList<java.lang.String>(_list16.size);
                @org.apache.thrift.annotation.Nullable java.lang.String _elem17;
                for (int _i18 = 0; _i18 < _list16.size; ++_i18)
                {
                  _elem17 = iprot.readString();
                  struct.textList.add(_elem17);
                }
                iprot.readListEnd();
              }
              struct.setTextListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // INTEGER_TO_DOUBLE
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map19 = iprot.readMapBegin();
                struct.integerToDouble = new java.util.HashMap<java.lang.Integer,java.lang.Double>(2*_map19.size);
                int _key20;
                double _val21;
                for (int _i22 = 0; _i22 < _map19.size; ++_i22)
                {
                  _key20 = iprot.readI32();
                  _val21 = iprot.readDouble();
                  struct.integerToDouble.put(_key20, _val21);
                }
                iprot.readMapEnd();
              }
              struct.setIntegerToDoubleIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, NestedStruct struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.textList != null) {
        oprot.writeFieldBegin(TEXT_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.textList.size()));
          for (java.lang.String _iter23 : struct.textList)
          {
            oprot.writeString(_iter23);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.integerToDouble != null) {
        oprot.writeFieldBegin(INTEGER_TO_DOUBLE_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.DOUBLE, struct.integerToDouble.size()));
          for (java.util.Map.Entry<java.lang.Integer, java.lang.Double> _iter24 : struct.integerToDouble.entrySet())
          {
            oprot.writeI32(_iter24.getKey());
            oprot.writeDouble(_iter24.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class NestedStructTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public NestedStructTupleScheme getScheme() {
      return new NestedStructTupleScheme();
    }
  }

  private static class NestedStructTupleScheme extends org.apache.thrift.scheme.TupleScheme<NestedStruct> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, NestedStruct struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetTextList()) {
        optionals.set(0);
      }
      if (struct.isSetIntegerToDouble()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetTextList()) {
        {
          oprot.writeI32(struct.textList.size());
          for (java.lang.String _iter25 : struct.textList)
          {
            oprot.writeString(_iter25);
          }
        }
      }
      if (struct.isSetIntegerToDouble()) {
        {
          oprot.writeI32(struct.integerToDouble.size());
          for (java.util.Map.Entry<java.lang.Integer, java.lang.Double> _iter26 : struct.integerToDouble.entrySet())
          {
            oprot.writeI32(_iter26.getKey());
            oprot.writeDouble(_iter26.getValue());
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, NestedStruct struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list27 = iprot.readListBegin();
          struct.textList = new java.util.ArrayList<java.lang.String>(_list27.size);
          @org.apache.thrift.annotation.Nullable java.lang.String _elem28;
          for (int _i29 = 0; _i29 < _list27.size; ++_i29)
          {
            _elem28 = iprot.readString();
            struct.textList.add(_elem28);
          }
        }
        struct.setTextListIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TMap _map30 = iprot.readMapBegin();
          struct.integerToDouble = new java.util.HashMap<java.lang.Integer,java.lang.Double>(2*_map30.size);
          int _key31;
          double _val32;
          for (int _i33 = 0; _i33 < _map30.size; ++_i33)
          {
            _key31 = iprot.readI32();
            _val32 = iprot.readDouble();
            struct.integerToDouble.put(_key31, _val32);
          }
        }
        struct.setIntegerToDoubleIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

