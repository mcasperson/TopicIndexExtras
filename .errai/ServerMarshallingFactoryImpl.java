package org.jboss.errai.marshalling.server.impl;

import com.redhat.topicindex.rest.collections.RESTBugzillaBugCollectionV1;
import com.redhat.topicindex.rest.collections.RESTCategoryCollectionV1;
import com.redhat.topicindex.rest.collections.RESTImageCollectionV1;
import com.redhat.topicindex.rest.collections.RESTLanguageImageCollectionV1;
import com.redhat.topicindex.rest.collections.RESTProjectCollectionV1;
import com.redhat.topicindex.rest.collections.RESTPropertyTagCollectionV1;
import com.redhat.topicindex.rest.collections.RESTRoleCollectionV1;
import com.redhat.topicindex.rest.collections.RESTStringConstantCollectionV1;
import com.redhat.topicindex.rest.collections.RESTTagCollectionV1;
import com.redhat.topicindex.rest.collections.RESTTopicCollectionV1;
import com.redhat.topicindex.rest.collections.RESTTopicSourceUrlCollectionV1;
import com.redhat.topicindex.rest.collections.RESTTranslatedTopicCollectionV1;
import com.redhat.topicindex.rest.collections.RESTTranslatedTopicStringCollectionV1;
import com.redhat.topicindex.rest.collections.RESTUserCollectionV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTBugzillaBugV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTCategoryV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTImageV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTLanguageImageV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTProjectV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTPropertyTagV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTagV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTopicSourceUrlV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTopicV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicStringV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicV1;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.enterprise.context.Dependent;
import org.jboss.errai.bus.client.api.base.MessageDeliveryFailure;
import org.jboss.errai.bus.client.api.base.TransportIOException;
import org.jboss.errai.enterprise.client.cdi.events.BusReadyEvent;
import org.jboss.errai.marshalling.client.api.Marshaller;
import org.jboss.errai.marshalling.client.api.MarshallerFactory;
import org.jboss.errai.marshalling.client.api.MarshallingSession;
import org.jboss.errai.marshalling.client.api.json.EJArray;
import org.jboss.errai.marshalling.client.api.json.EJObject;
import org.jboss.errai.marshalling.client.api.json.EJValue;
import org.jboss.errai.marshalling.client.marshallers.BigDecimalMarshaller;
import org.jboss.errai.marshalling.client.marshallers.BigIntegerMarshaller;
import org.jboss.errai.marshalling.client.marshallers.BooleanMarshaller;
import org.jboss.errai.marshalling.client.marshallers.ByteMarshaller;
import org.jboss.errai.marshalling.client.marshallers.CharacterMarshaller;
import org.jboss.errai.marshalling.client.marshallers.DateMarshaller;
import org.jboss.errai.marshalling.client.marshallers.DoubleMarshaller;
import org.jboss.errai.marshalling.client.marshallers.FloatMarshaller;
import org.jboss.errai.marshalling.client.marshallers.IntegerMarshaller;
import org.jboss.errai.marshalling.client.marshallers.LinkedListMarshaller;
import org.jboss.errai.marshalling.client.marshallers.LinkedMapMarshaller;
import org.jboss.errai.marshalling.client.marshallers.ListMarshaller;
import org.jboss.errai.marshalling.client.marshallers.LongMarshaller;
import org.jboss.errai.marshalling.client.marshallers.MapMarshaller;
import org.jboss.errai.marshalling.client.marshallers.ObjectMarshaller;
import org.jboss.errai.marshalling.client.marshallers.PriorityQueueMarshaller;
import org.jboss.errai.marshalling.client.marshallers.QualifyingMarshallerWrapper;
import org.jboss.errai.marshalling.client.marshallers.QueueMarshaller;
import org.jboss.errai.marshalling.client.marshallers.SQLDateMarshaller;
import org.jboss.errai.marshalling.client.marshallers.SetMarshaller;
import org.jboss.errai.marshalling.client.marshallers.ShortMarshaller;
import org.jboss.errai.marshalling.client.marshallers.SortedMapMarshaller;
import org.jboss.errai.marshalling.client.marshallers.SortedSetMarshaller;
import org.jboss.errai.marshalling.client.marshallers.StringBufferMarshaller;
import org.jboss.errai.marshalling.client.marshallers.StringBuilderMarshaller;
import org.jboss.errai.marshalling.client.marshallers.StringMarshaller;
import org.jboss.errai.marshalling.client.marshallers.TimeMarshaller;
import org.jboss.errai.marshalling.client.marshallers.TimestampMarshaller;
@Dependent public class ServerMarshallingFactoryImpl implements MarshallerFactory {
  private Map<String, Marshaller> marshallers = new HashMap<String, Marshaller>();
  private LongMarshaller java_lang_Long;
  private IntegerMarshaller java_lang_Integer;
  private ShortMarshaller java_lang_Short;
  private QualifyingMarshallerWrapper<LinkedHashMap> java_util_LinkedHashMap;
  private StringBufferMarshaller java_lang_StringBuffer;
  private SortedSetMarshaller java_util_SortedSet;
  private DoubleMarshaller java_lang_Double;
  private TimeMarshaller java_sql_Time;
  private SetMarshaller java_util_Set;
  private SortedSetMarshaller java_util_TreeSet;
  private ListMarshaller java_util_Stack;
  private SetMarshaller java_util_AbstractSet;
  private ListMarshaller java_util_ArrayList;
  private QualifyingMarshallerWrapper<AbstractMap> java_util_AbstractMap;
  private BigDecimalMarshaller java_math_BigDecimal;
  private DateMarshaller java_util_Date;
  private ByteMarshaller java_lang_Byte;
  private ListMarshaller java_util_AbstractList;
  private BooleanMarshaller java_lang_Boolean;
  private StringMarshaller java_lang_String;
  private QualifyingMarshallerWrapper<HashMap> java_util_HashMap;
  private QualifyingMarshallerWrapper<Map> java_util_Map;
  private QueueMarshaller java_util_AbstractQueue;
  private ObjectMarshaller java_lang_Object;
  private ListMarshaller java_util_List;
  private CharacterMarshaller java_lang_Character;
  private TimestampMarshaller java_sql_Timestamp;
  private QueueMarshaller java_util_Queue;
  private QualifyingMarshallerWrapper<SortedMap> java_util_SortedMap;
  private SetMarshaller java_util_HashSet;
  private PriorityQueueMarshaller java_util_PriorityQueue;
  private StringBuilderMarshaller java_lang_StringBuilder;
  private FloatMarshaller java_lang_Float;
  private LinkedListMarshaller java_util_LinkedList;
  private SetMarshaller java_util_LinkedHashSet;
  private QualifyingMarshallerWrapper<TreeMap> java_util_TreeMap;
  private SQLDateMarshaller java_sql_Date;
  private ListMarshaller java_util_Vector;
  private BigIntegerMarshaller java_math_BigInteger;
  private Marshaller<RESTImageV1> com_redhat_topicindex_rest_entities_interfaces_RESTImageV1;
  private QualifyingMarshallerWrapper<StackTraceElement[]> arrayOf_java_lang_StackTraceElement_D1;
  private Marshaller<Throwable> java_lang_Throwable;
  private Marshaller<RESTTopicCollectionV1> com_redhat_topicindex_rest_collections_RESTTopicCollectionV1;
  private Marshaller<StackTraceElement> java_lang_StackTraceElement;
  private Marshaller<StringIndexOutOfBoundsException> java_lang_StringIndexOutOfBoundsException;
  private Marshaller<UnsupportedOperationException> java_lang_UnsupportedOperationException;
  private Marshaller<RESTTagCollectionV1> com_redhat_topicindex_rest_collections_RESTTagCollectionV1;
  private Marshaller<BusReadyEvent> org_jboss_errai_enterprise_client_cdi_events_BusReadyEvent;
  private Marshaller<IllegalArgumentException> java_lang_IllegalArgumentException;
  private Marshaller<MessageDeliveryFailure> org_jboss_errai_bus_client_api_base_MessageDeliveryFailure;
  private Marshaller<ArrayStoreException> java_lang_ArrayStoreException;
  private Marshaller<RESTCategoryCollectionV1> com_redhat_topicindex_rest_collections_RESTCategoryCollectionV1;
  private Marshaller<TransportIOException> org_jboss_errai_bus_client_api_base_TransportIOException;
  private Marshaller<AssertionError> java_lang_AssertionError;
  private Marshaller<RESTLanguageImageCollectionV1> com_redhat_topicindex_rest_collections_RESTLanguageImageCollectionV1;
  private Marshaller<RuntimeException> java_lang_RuntimeException;
  private static Field com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicV1_translatedTopicStrings_fld = _getAccessibleField(RESTTranslatedTopicV1.class, "translatedTopicStrings");
  private Marshaller<RESTTranslatedTopicV1> com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicV1;
  private Marshaller<ConcurrentModificationException> java_util_ConcurrentModificationException;
  private Marshaller<RESTCategoryV1> com_redhat_topicindex_rest_entities_interfaces_RESTCategoryV1;
  private Marshaller<RESTTopicSourceUrlCollectionV1> com_redhat_topicindex_rest_collections_RESTTopicSourceUrlCollectionV1;
  private Marshaller<RESTBugzillaBugCollectionV1> com_redhat_topicindex_rest_collections_RESTBugzillaBugCollectionV1;
  private Marshaller<RESTRoleCollectionV1> com_redhat_topicindex_rest_collections_RESTRoleCollectionV1;
  private Marshaller<NullPointerException> java_lang_NullPointerException;
  private Marshaller<RESTTopicSourceUrlV1> com_redhat_topicindex_rest_entities_interfaces_RESTTopicSourceUrlV1;
  private QualifyingMarshallerWrapper<byte[]> arrayOf_byte_D1;
  private Marshaller<RESTLanguageImageV1> com_redhat_topicindex_rest_entities_interfaces_RESTLanguageImageV1;
  private Marshaller<RESTTranslatedTopicStringV1> com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicStringV1;
  private Marshaller<ArithmeticException> java_lang_ArithmeticException;
  private Marshaller<EmptyStackException> java_util_EmptyStackException;
  private Marshaller<RESTStringConstantCollectionV1> com_redhat_topicindex_rest_collections_RESTStringConstantCollectionV1;
  private Marshaller<RESTTranslatedTopicCollectionV1> com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1;
  private Marshaller<IOException> java_io_IOException;
  private Marshaller<RESTProjectCollectionV1> com_redhat_topicindex_rest_collections_RESTProjectCollectionV1;
  private Marshaller<RESTPropertyTagV1> com_redhat_topicindex_rest_entities_interfaces_RESTPropertyTagV1;
  private Marshaller<RESTTagV1> com_redhat_topicindex_rest_entities_interfaces_RESTTagV1;
  private Marshaller<ClassCastException> java_lang_ClassCastException;
  private Marshaller<NegativeArraySizeException> java_lang_NegativeArraySizeException;
  private Marshaller<RESTTopicV1> com_redhat_topicindex_rest_entities_interfaces_RESTTopicV1;
  private Marshaller<RESTBugzillaBugV1> com_redhat_topicindex_rest_entities_interfaces_RESTBugzillaBugV1;
  private Marshaller<RESTProjectV1> com_redhat_topicindex_rest_entities_interfaces_RESTProjectV1;
  private Marshaller<IndexOutOfBoundsException> java_lang_IndexOutOfBoundsException;
  private Marshaller<RESTPropertyTagCollectionV1> com_redhat_topicindex_rest_collections_RESTPropertyTagCollectionV1;
  private Marshaller<RESTTranslatedTopicStringCollectionV1> com_redhat_topicindex_rest_collections_RESTTranslatedTopicStringCollectionV1;
  private Marshaller<RESTImageCollectionV1> com_redhat_topicindex_rest_collections_RESTImageCollectionV1;
  private Marshaller<RESTUserCollectionV1> com_redhat_topicindex_rest_collections_RESTUserCollectionV1;
  private QualifyingMarshallerWrapper<Object[]> arrayOf_java_lang_Object_D1;
  private QualifyingMarshallerWrapper<String[]> arrayOf_java_lang_String_D1;
  private QualifyingMarshallerWrapper<int[]> arrayOf_int_D1;
  private QualifyingMarshallerWrapper<long[]> arrayOf_long_D1;
  private QualifyingMarshallerWrapper<double[]> arrayOf_double_D1;
  private QualifyingMarshallerWrapper<float[]> arrayOf_float_D1;
  private QualifyingMarshallerWrapper<short[]> arrayOf_short_D1;
  private QualifyingMarshallerWrapper<boolean[]> arrayOf_boolean_D1;
  private QualifyingMarshallerWrapper<char[]> arrayOf_char_D1;
  private QualifyingMarshallerWrapper<Integer[]> arrayOf_java_lang_Integer_D1;
  private QualifyingMarshallerWrapper<Long[]> arrayOf_java_lang_Long_D1;
  private QualifyingMarshallerWrapper<Double[]> arrayOf_java_lang_Double_D1;
  private QualifyingMarshallerWrapper<Float[]> arrayOf_java_lang_Float_D1;
  private QualifyingMarshallerWrapper<Short[]> arrayOf_java_lang_Short_D1;
  private QualifyingMarshallerWrapper<Boolean[]> arrayOf_java_lang_Boolean_D1;
  private QualifyingMarshallerWrapper<Byte[]> arrayOf_java_lang_Byte_D1;
  private QualifyingMarshallerWrapper<Character[]> arrayOf_java_lang_Character_D1;
  public ServerMarshallingFactoryImpl() {
    java_lang_Long = new LongMarshaller();
    marshallers.put("java.lang.Long", java_lang_Long);
    java_lang_Integer = new IntegerMarshaller();
    marshallers.put("java.lang.Integer", java_lang_Integer);
    java_lang_Short = new ShortMarshaller();
    marshallers.put("java.lang.Short", java_lang_Short);
    java_util_LinkedHashMap = new QualifyingMarshallerWrapper(new LinkedMapMarshaller());
    marshallers.put("java.util.LinkedHashMap", java_util_LinkedHashMap);
    java_lang_StringBuffer = new StringBufferMarshaller();
    marshallers.put("java.lang.StringBuffer", java_lang_StringBuffer);
    java_util_SortedSet = new SortedSetMarshaller();
    marshallers.put("java.util.SortedSet", java_util_SortedSet);
    marshallers.put("java.util.Collections$UnmodifiableSortedSet", java_util_SortedSet);
    marshallers.put("java.util.TreeSet", java_util_SortedSet);
    marshallers.put("java.util.Collections$SynchronizedSortedSet", java_util_SortedSet);
    java_lang_Double = new DoubleMarshaller();
    marshallers.put("java.lang.Double", java_lang_Double);
    java_sql_Time = new TimeMarshaller();
    marshallers.put("java.sql.Time", java_sql_Time);
    java_util_Set = new SetMarshaller();
    marshallers.put("java.util.Set", java_util_Set);
    marshallers.put("java.util.Collections$SynchronizedSet", java_util_Set);
    marshallers.put("java.util.Collections$UnmodifiableSet", java_util_Set);
    marshallers.put("java.util.Collections$EmptySet", java_util_Set);
    marshallers.put("java.util.Collections$SingletonSet", java_util_Set);
    marshallers.put("java.util.AbstractSet", java_util_Set);
    marshallers.put("java.util.HashSet", java_util_Set);
    marshallers.put("java.util.LinkedHashSet", java_util_Set);
    java_util_TreeSet = new SortedSetMarshaller();
    marshallers.put("java.util.TreeSet", java_util_TreeSet);
    java_util_Stack = new ListMarshaller();
    marshallers.put("java.util.Stack", java_util_Stack);
    java_util_AbstractSet = new SetMarshaller();
    marshallers.put("java.util.AbstractSet", java_util_AbstractSet);
    java_util_ArrayList = new ListMarshaller();
    marshallers.put("java.util.ArrayList", java_util_ArrayList);
    java_util_AbstractMap = new QualifyingMarshallerWrapper(new MapMarshaller());
    marshallers.put("java.util.AbstractMap", java_util_AbstractMap);
    java_math_BigDecimal = new BigDecimalMarshaller();
    marshallers.put("java.math.BigDecimal", java_math_BigDecimal);
    java_util_Date = new DateMarshaller();
    marshallers.put("java.util.Date", java_util_Date);
    java_lang_Byte = new ByteMarshaller();
    marshallers.put("java.lang.Byte", java_lang_Byte);
    java_util_AbstractList = new ListMarshaller();
    marshallers.put("java.util.AbstractList", java_util_AbstractList);
    java_lang_Boolean = new BooleanMarshaller();
    marshallers.put("java.lang.Boolean", java_lang_Boolean);
    java_lang_String = new StringMarshaller();
    marshallers.put("java.lang.String", java_lang_String);
    java_util_HashMap = new QualifyingMarshallerWrapper(new MapMarshaller());
    marshallers.put("java.util.HashMap", java_util_HashMap);
    java_util_Map = new QualifyingMarshallerWrapper(new MapMarshaller());
    marshallers.put("java.util.Map", java_util_Map);
    marshallers.put("java.util.Collections$SingletonMap", java_util_Map);
    marshallers.put("java.util.AbstractMap", java_util_Map);
    marshallers.put("java.util.Collections$SynchronizedMap", java_util_Map);
    marshallers.put("java.util.HashMap", java_util_Map);
    marshallers.put("java.util.Collections$UnmodifiableMap", java_util_Map);
    marshallers.put("java.util.Collections$EmptyMap", java_util_Map);
    java_util_AbstractQueue = new QueueMarshaller();
    marshallers.put("java.util.AbstractQueue", java_util_AbstractQueue);
    java_lang_Object = new ObjectMarshaller();
    marshallers.put("java.lang.Object", java_lang_Object);
    java_util_List = new ListMarshaller();
    marshallers.put("java.util.List", java_util_List);
    marshallers.put("java.util.Collections$SynchronizedRandomAccessList", java_util_List);
    marshallers.put("java.util.Collections$UnmodifiableRandomAccessList", java_util_List);
    marshallers.put("java.util.Stack", java_util_List);
    marshallers.put("java.util.Vector", java_util_List);
    marshallers.put("java.util.ArrayList", java_util_List);
    marshallers.put("java.util.Collections$SingletonList", java_util_List);
    marshallers.put("java.util.Collections$SynchronizedList", java_util_List);
    marshallers.put("java.util.Collections$UnmodifiableList", java_util_List);
    marshallers.put("java.util.Collections$EmptyList", java_util_List);
    marshallers.put("java.util.Arrays$ArrayList", java_util_List);
    marshallers.put("java.util.AbstractList", java_util_List);
    java_lang_Character = new CharacterMarshaller();
    marshallers.put("java.lang.Character", java_lang_Character);
    java_sql_Timestamp = new TimestampMarshaller();
    marshallers.put("java.sql.Timestamp", java_sql_Timestamp);
    java_util_Queue = new QueueMarshaller();
    marshallers.put("java.util.Queue", java_util_Queue);
    marshallers.put("java.util.AbstractQueue", java_util_Queue);
    java_util_SortedMap = new QualifyingMarshallerWrapper(new SortedMapMarshaller());
    marshallers.put("java.util.SortedMap", java_util_SortedMap);
    marshallers.put("java.util.Collections$SynchronizedSortedMap", java_util_SortedMap);
    marshallers.put("java.util.Collections$UnmodifiableSortedMap", java_util_SortedMap);
    marshallers.put("java.util.TreeMap", java_util_SortedMap);
    java_util_HashSet = new SetMarshaller();
    marshallers.put("java.util.HashSet", java_util_HashSet);
    java_util_PriorityQueue = new PriorityQueueMarshaller();
    marshallers.put("java.util.PriorityQueue", java_util_PriorityQueue);
    java_lang_StringBuilder = new StringBuilderMarshaller();
    marshallers.put("java.lang.StringBuilder", java_lang_StringBuilder);
    java_lang_Float = new FloatMarshaller();
    marshallers.put("java.lang.Float", java_lang_Float);
    java_util_LinkedList = new LinkedListMarshaller();
    marshallers.put("java.util.LinkedList", java_util_LinkedList);
    java_util_LinkedHashSet = new SetMarshaller();
    marshallers.put("java.util.LinkedHashSet", java_util_LinkedHashSet);
    java_util_TreeMap = new QualifyingMarshallerWrapper(new SortedMapMarshaller());
    marshallers.put("java.util.TreeMap", java_util_TreeMap);
    java_sql_Date = new SQLDateMarshaller();
    marshallers.put("java.sql.Date", java_sql_Date);
    java_util_Vector = new ListMarshaller();
    marshallers.put("java.util.Vector", java_util_Vector);
    java_math_BigInteger = new BigIntegerMarshaller();
    marshallers.put("java.math.BigInteger", java_math_BigInteger);
    com_redhat_topicindex_rest_entities_interfaces_RESTImageV1 = new Marshaller<RESTImageV1>() {
      public Class getTypeHandled() {
        return RESTImageV1.class;
      }
      public RESTImageV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTImageV1.class, objId);
          }
          RESTImageV1 entity = new RESTImageV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("languageImages_OTM")) && (!obj.get("languageImages_OTM").isNull())) {
            entity.setLanguageImages_OTM(com_redhat_topicindex_rest_collections_RESTLanguageImageCollectionV1.demarshall(obj.get("languageImages_OTM"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(com_redhat_topicindex_rest_collections_RESTImageCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("configuredParameters")) && (!obj.get("configuredParameters").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setConfiguredParameters(java_util_List.demarshall(obj.get("configuredParameters"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("selfLink")) && (!obj.get("selfLink").isNull())) {
            entity.setSelfLink(java_lang_String.demarshall(obj.get("selfLink"), a1));
          }
          if ((obj.containsKey("editLink")) && (!obj.get("editLink").isNull())) {
            entity.setEditLink(java_lang_String.demarshall(obj.get("editLink"), a1));
          }
          if ((obj.containsKey("deleteLink")) && (!obj.get("deleteLink").isNull())) {
            entity.setDeleteLink(java_lang_String.demarshall(obj.get("deleteLink"), a1));
          }
          if ((obj.containsKey("addLink")) && (!obj.get("addLink").isNull())) {
            entity.setAddLink(java_lang_String.demarshall(obj.get("addLink"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("addItem")) && (!obj.get("addItem").isNull())) {
            entity.setAddItem((boolean) java_lang_Boolean.demarshall(obj.get("addItem"), a1));
          }
          if ((obj.containsKey("removeItem")) && (!obj.get("removeItem").isNull())) {
            entity.setRemoveItem((boolean) java_lang_Boolean.demarshall(obj.get("removeItem"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.entities.interfaces.RESTImageV1", t);
        }
      }
      public String marshall(RESTImageV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTImageV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3072).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTImageV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"languageImages_OTM\" : ").append(com_redhat_topicindex_rest_collections_RESTLanguageImageCollectionV1.marshall(a0.getLanguageImages_OTM(), a1)).append(",").append("\"revisions\" : ").append(com_redhat_topicindex_rest_collections_RESTImageCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"addItem\" : ").append(java_lang_Boolean.marshall(a0.getAddItem(), a1)).append(",").append("\"removeItem\" : ").append(java_lang_Boolean.marshall(a0.getRemoveItem(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.entities.interfaces.RESTImageV1", com_redhat_topicindex_rest_entities_interfaces_RESTImageV1);
    arrayOf_java_lang_StackTraceElement_D1 = new QualifyingMarshallerWrapper(new Marshaller<StackTraceElement[]>() {
      private StackTraceElement[] _demarshall1(EJArray a0, MarshallingSession a1) {
        StackTraceElement[] newArray = new StackTraceElement[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_StackTraceElement.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(StackTraceElement[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_StackTraceElement.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return StackTraceElement.class;
      }
      public StackTraceElement[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(StackTraceElement[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.StackTraceElement;", arrayOf_java_lang_StackTraceElement_D1);
    java_lang_Throwable = new Marshaller<Throwable>() {
      public Class getTypeHandled() {
        return Throwable.class;
      }
      public Throwable demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(Throwable.class, objId);
          }
          Throwable entity = new Throwable(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.Throwable", t);
        }
      }
      public String marshall(Throwable a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.Throwable\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.Throwable\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.Throwable", java_lang_Throwable);
    com_redhat_topicindex_rest_collections_RESTTopicCollectionV1 = new Marshaller<RESTTopicCollectionV1>() {
      public Class getTypeHandled() {
        return RESTTopicCollectionV1.class;
      }
      public RESTTopicCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTopicCollectionV1.class, objId);
          }
          RESTTopicCollectionV1 entity = new RESTTopicCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTTopicV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTTopicCollectionV1", t);
        }
      }
      public String marshall(RESTTopicCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTTopicCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTTopicCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTTopicCollectionV1", com_redhat_topicindex_rest_collections_RESTTopicCollectionV1);
    java_lang_StackTraceElement = new Marshaller<StackTraceElement>() {
      public Class getTypeHandled() {
        return StackTraceElement.class;
      }
      public StackTraceElement demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(StackTraceElement.class, objId);
          }
          StackTraceElement entity = new StackTraceElement(java_lang_String.demarshall(obj.get("declaringClass"), a1), java_lang_String.demarshall(obj.get("methodName"), a1), java_lang_String.demarshall(obj.get("fileName"), a1), java_lang_Integer.demarshall(obj.get("lineNumber"), a1));
          a1.recordObject(objId, entity);
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.StackTraceElement", t);
        }
      }
      public String marshall(StackTraceElement a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.StackTraceElement\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(640).append("{\"^EncodedType\":\"java.lang.StackTraceElement\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"fileName\" : ").append(java_lang_String.marshall(a0.getFileName(), a1)).append(",").append("\"methodName\" : ").append(java_lang_String.marshall(a0.getMethodName(), a1)).append(",").append("\"lineNumber\" : ").append(java_lang_Integer.marshall(a0.getLineNumber(), a1)).append(",").append("\"declaringClass\" : ").append(java_lang_String.marshall(a0.getClassName(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.StackTraceElement", java_lang_StackTraceElement);
    java_lang_StringIndexOutOfBoundsException = new Marshaller<StringIndexOutOfBoundsException>() {
      public Class getTypeHandled() {
        return StringIndexOutOfBoundsException.class;
      }
      public StringIndexOutOfBoundsException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(StringIndexOutOfBoundsException.class, objId);
          }
          StringIndexOutOfBoundsException entity = new StringIndexOutOfBoundsException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.StringIndexOutOfBoundsException", t);
        }
      }
      public String marshall(StringIndexOutOfBoundsException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.StringIndexOutOfBoundsException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.StringIndexOutOfBoundsException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.StringIndexOutOfBoundsException", java_lang_StringIndexOutOfBoundsException);
    java_lang_UnsupportedOperationException = new Marshaller<UnsupportedOperationException>() {
      public Class getTypeHandled() {
        return UnsupportedOperationException.class;
      }
      public UnsupportedOperationException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(UnsupportedOperationException.class, objId);
          }
          UnsupportedOperationException entity = new UnsupportedOperationException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.UnsupportedOperationException", t);
        }
      }
      public String marshall(UnsupportedOperationException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.UnsupportedOperationException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.UnsupportedOperationException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.UnsupportedOperationException", java_lang_UnsupportedOperationException);
    com_redhat_topicindex_rest_collections_RESTTagCollectionV1 = new Marshaller<RESTTagCollectionV1>() {
      public Class getTypeHandled() {
        return RESTTagCollectionV1.class;
      }
      public RESTTagCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTagCollectionV1.class, objId);
          }
          RESTTagCollectionV1 entity = new RESTTagCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTTagV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTTagCollectionV1", t);
        }
      }
      public String marshall(RESTTagCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTTagCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTTagCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTTagCollectionV1", com_redhat_topicindex_rest_collections_RESTTagCollectionV1);
    org_jboss_errai_enterprise_client_cdi_events_BusReadyEvent = new Marshaller<BusReadyEvent>() {
      public Class getTypeHandled() {
        return BusReadyEvent.class;
      }
      public BusReadyEvent demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(BusReadyEvent.class, objId);
          }
          BusReadyEvent entity = new BusReadyEvent();
          a1.recordObject(objId, entity);
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.errai.enterprise.client.cdi.events.BusReadyEvent", t);
        }
      }
      public String marshall(BusReadyEvent a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.errai.enterprise.client.cdi.events.BusReadyEvent\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.errai.enterprise.client.cdi.events.BusReadyEvent\",\"^ObjectID\":\"").append(objId).append("\"").append(",\"^InstantiateOnly\":true").append("}").toString();
      }
    };
    marshallers.put("org.jboss.errai.enterprise.client.cdi.events.BusReadyEvent", org_jboss_errai_enterprise_client_cdi_events_BusReadyEvent);
    java_lang_IllegalArgumentException = new Marshaller<IllegalArgumentException>() {
      public Class getTypeHandled() {
        return IllegalArgumentException.class;
      }
      public IllegalArgumentException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(IllegalArgumentException.class, objId);
          }
          IllegalArgumentException entity = new IllegalArgumentException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.IllegalArgumentException", t);
        }
      }
      public String marshall(IllegalArgumentException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.IllegalArgumentException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.IllegalArgumentException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.IllegalArgumentException", java_lang_IllegalArgumentException);
    org_jboss_errai_bus_client_api_base_MessageDeliveryFailure = new Marshaller<MessageDeliveryFailure>() {
      public Class getTypeHandled() {
        return MessageDeliveryFailure.class;
      }
      public MessageDeliveryFailure demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(MessageDeliveryFailure.class, objId);
          }
          MessageDeliveryFailure entity = new MessageDeliveryFailure(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.errai.bus.client.api.base.MessageDeliveryFailure", t);
        }
      }
      public String marshall(MessageDeliveryFailure a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.errai.bus.client.api.base.MessageDeliveryFailure\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"org.jboss.errai.bus.client.api.base.MessageDeliveryFailure\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.errai.bus.client.api.base.MessageDeliveryFailure", org_jboss_errai_bus_client_api_base_MessageDeliveryFailure);
    java_lang_ArrayStoreException = new Marshaller<ArrayStoreException>() {
      public Class getTypeHandled() {
        return ArrayStoreException.class;
      }
      public ArrayStoreException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(ArrayStoreException.class, objId);
          }
          ArrayStoreException entity = new ArrayStoreException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.ArrayStoreException", t);
        }
      }
      public String marshall(ArrayStoreException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.ArrayStoreException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.ArrayStoreException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.ArrayStoreException", java_lang_ArrayStoreException);
    com_redhat_topicindex_rest_collections_RESTCategoryCollectionV1 = new Marshaller<RESTCategoryCollectionV1>() {
      public Class getTypeHandled() {
        return RESTCategoryCollectionV1.class;
      }
      public RESTCategoryCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTCategoryCollectionV1.class, objId);
          }
          RESTCategoryCollectionV1 entity = new RESTCategoryCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTCategoryV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTCategoryCollectionV1", t);
        }
      }
      public String marshall(RESTCategoryCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTCategoryCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTCategoryCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTCategoryCollectionV1", com_redhat_topicindex_rest_collections_RESTCategoryCollectionV1);
    org_jboss_errai_bus_client_api_base_TransportIOException = new Marshaller<TransportIOException>() {
      public Class getTypeHandled() {
        return TransportIOException.class;
      }
      public TransportIOException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(TransportIOException.class, objId);
          }
          TransportIOException entity = new TransportIOException(java_lang_String.demarshall(obj.get("message"), a1), java_lang_Integer.demarshall(obj.get("errorCode"), a1), java_lang_String.demarshall(obj.get("errorMessage"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.errai.bus.client.api.base.TransportIOException", t);
        }
      }
      public String marshall(TransportIOException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.errai.bus.client.api.base.TransportIOException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(4224).append("{\"^EncodedType\":\"org.jboss.errai.bus.client.api.base.TransportIOException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"errorCode\" : ").append(java_lang_Integer.marshall(a0.errorCode(), a1)).append(",").append("\"errorMessage\" : ").append(java_lang_String.marshall(a0.getErrorMessage(), a1)).append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.errai.bus.client.api.base.TransportIOException", org_jboss_errai_bus_client_api_base_TransportIOException);
    java_lang_AssertionError = new Marshaller<AssertionError>() {
      public Class getTypeHandled() {
        return AssertionError.class;
      }
      public AssertionError demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(AssertionError.class, objId);
          }
          AssertionError entity = new AssertionError(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.AssertionError", t);
        }
      }
      public String marshall(AssertionError a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.AssertionError\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.AssertionError\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.AssertionError", java_lang_AssertionError);
    com_redhat_topicindex_rest_collections_RESTLanguageImageCollectionV1 = new Marshaller<RESTLanguageImageCollectionV1>() {
      public Class getTypeHandled() {
        return RESTLanguageImageCollectionV1.class;
      }
      public RESTLanguageImageCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTLanguageImageCollectionV1.class, objId);
          }
          RESTLanguageImageCollectionV1 entity = new RESTLanguageImageCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTLanguageImageV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTLanguageImageCollectionV1", t);
        }
      }
      public String marshall(RESTLanguageImageCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTLanguageImageCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTLanguageImageCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTLanguageImageCollectionV1", com_redhat_topicindex_rest_collections_RESTLanguageImageCollectionV1);
    java_lang_RuntimeException = new Marshaller<RuntimeException>() {
      public Class getTypeHandled() {
        return RuntimeException.class;
      }
      public RuntimeException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RuntimeException.class, objId);
          }
          RuntimeException entity = new RuntimeException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.RuntimeException", t);
        }
      }
      public String marshall(RuntimeException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.RuntimeException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.RuntimeException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.RuntimeException", java_lang_RuntimeException);
    com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicV1 = new Marshaller<RESTTranslatedTopicV1>() {
      public Class getTypeHandled() {
        return RESTTranslatedTopicV1.class;
      }
      public RESTTranslatedTopicV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTranslatedTopicV1.class, objId);
          }
          RESTTranslatedTopicV1 entity = new RESTTranslatedTopicV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("topic")) && (!obj.get("topic").isNull())) {
            entity.setTopic(com_redhat_topicindex_rest_entities_interfaces_RESTTopicV1.demarshall(obj.get("topic"), a1));
          }
          if ((obj.containsKey("translatedTopicId")) && (!obj.get("translatedTopicId").isNull())) {
            entity.setTranslatedTopicId(java_lang_Integer.demarshall(obj.get("translatedTopicId"), a1));
          }
          if ((obj.containsKey("topicId")) && (!obj.get("topicId").isNull())) {
            entity.setTopicId(java_lang_Integer.demarshall(obj.get("topicId"), a1));
          }
          if ((obj.containsKey("topicRevision")) && (!obj.get("topicRevision").isNull())) {
            entity.setTopicRevision(java_lang_Integer.demarshall(obj.get("topicRevision"), a1));
          }
          if ((obj.containsKey("translationPercentage")) && (!obj.get("translationPercentage").isNull())) {
            entity.setTranslationPercentage(java_lang_Integer.demarshall(obj.get("translationPercentage"), a1));
          }
          if ((obj.containsKey("htmlUpdated")) && (!obj.get("htmlUpdated").isNull())) {
            entity.setHtmlUpdated((Date) java_lang_Object.demarshall(obj.get("htmlUpdated"), a1));
          }
          if ((obj.containsKey("translatedTopicStrings")) && (!obj.get("translatedTopicStrings").isNull())) {
            com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicV1_translatedTopicStrings(entity, com_redhat_topicindex_rest_collections_RESTTranslatedTopicStringCollectionV1.demarshall(obj.get("translatedTopicStrings"), a1));
          }
          if ((obj.containsKey("outgoingTranslatedRelationships")) && (!obj.get("outgoingTranslatedRelationships").isNull())) {
            entity.setOutgoingTranslatedRelationships(com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1.demarshall(obj.get("outgoingTranslatedRelationships"), a1));
          }
          if ((obj.containsKey("incomingTranslatedRelationships")) && (!obj.get("incomingTranslatedRelationships").isNull())) {
            entity.setIncomingTranslatedRelationships(com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1.demarshall(obj.get("incomingTranslatedRelationships"), a1));
          }
          if ((obj.containsKey("outgoingRelationships")) && (!obj.get("outgoingRelationships").isNull())) {
            entity.setOutgoingRelationships(com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1.demarshall(obj.get("outgoingRelationships"), a1));
          }
          if ((obj.containsKey("incomingRelationships")) && (!obj.get("incomingRelationships").isNull())) {
            entity.setIncomingRelationships(com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1.demarshall(obj.get("incomingRelationships"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("title")) && (!obj.get("title").isNull())) {
            entity.setTitle(java_lang_String.demarshall(obj.get("title"), a1));
          }
          if ((obj.containsKey("xml")) && (!obj.get("xml").isNull())) {
            entity.setXml(java_lang_String.demarshall(obj.get("xml"), a1));
          }
          if ((obj.containsKey("xmlErrors")) && (!obj.get("xmlErrors").isNull())) {
            entity.setXmlErrors(java_lang_String.demarshall(obj.get("xmlErrors"), a1));
          }
          if ((obj.containsKey("html")) && (!obj.get("html").isNull())) {
            entity.setHtml(java_lang_String.demarshall(obj.get("html"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("locale")) && (!obj.get("locale").isNull())) {
            entity.setLocale(java_lang_String.demarshall(obj.get("locale"), a1));
          }
          if ((obj.containsKey("tags")) && (!obj.get("tags").isNull())) {
            entity.setTags(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.demarshall(obj.get("tags"), a1));
          }
          if ((obj.containsKey("sourceUrls_OTM")) && (!obj.get("sourceUrls_OTM").isNull())) {
            entity.setSourceUrls_OTM(com_redhat_topicindex_rest_collections_RESTTopicSourceUrlCollectionV1.demarshall(obj.get("sourceUrls_OTM"), a1));
          }
          if ((obj.containsKey("properties")) && (!obj.get("properties").isNull())) {
            entity.setProperties(com_redhat_topicindex_rest_collections_RESTPropertyTagCollectionV1.demarshall(obj.get("properties"), a1));
          }
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("configuredParameters")) && (!obj.get("configuredParameters").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setConfiguredParameters(java_util_List.demarshall(obj.get("configuredParameters"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("selfLink")) && (!obj.get("selfLink").isNull())) {
            entity.setSelfLink(java_lang_String.demarshall(obj.get("selfLink"), a1));
          }
          if ((obj.containsKey("editLink")) && (!obj.get("editLink").isNull())) {
            entity.setEditLink(java_lang_String.demarshall(obj.get("editLink"), a1));
          }
          if ((obj.containsKey("deleteLink")) && (!obj.get("deleteLink").isNull())) {
            entity.setDeleteLink(java_lang_String.demarshall(obj.get("deleteLink"), a1));
          }
          if ((obj.containsKey("addLink")) && (!obj.get("addLink").isNull())) {
            entity.setAddLink(java_lang_String.demarshall(obj.get("addLink"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("addItem")) && (!obj.get("addItem").isNull())) {
            entity.setAddItem((boolean) java_lang_Boolean.demarshall(obj.get("addItem"), a1));
          }
          if ((obj.containsKey("removeItem")) && (!obj.get("removeItem").isNull())) {
            entity.setRemoveItem((boolean) java_lang_Boolean.demarshall(obj.get("removeItem"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicV1", t);
        }
      }
      public String marshall(RESTTranslatedTopicV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(12032).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"topic\" : ").append(com_redhat_topicindex_rest_entities_interfaces_RESTTopicV1.marshall(a0.getTopic(), a1)).append(",").append("\"translatedTopicId\" : ").append(java_lang_Integer.marshall(a0.getTranslatedTopicId(), a1)).append(",").append("\"topicId\" : ").append(java_lang_Integer.marshall(a0.getTopicId(), a1)).append(",").append("\"topicRevision\" : ").append(java_lang_Integer.marshall(a0.getTopicRevision(), a1)).append(",").append("\"translationPercentage\" : ").append(java_lang_Integer.marshall(a0.getTranslationPercentage(), a1)).append(",").append("\"htmlUpdated\" : ").append(java_lang_Object.marshall(a0.getHtmlUpdated(), a1)).append(",").append("\"translatedTopicStrings\" : ").append(com_redhat_topicindex_rest_collections_RESTTranslatedTopicStringCollectionV1.marshall(com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicV1_translatedTopicStrings(a0), a1)).append(",").append("\"outgoingTranslatedRelationships\" : ").append(com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1.marshall(a0.getOutgoingTranslatedRelationships(), a1)).append(",").append("\"incomingTranslatedRelationships\" : ").append(com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1.marshall(a0.getIncomingTranslatedRelationships(), a1)).append(",").append("\"outgoingRelationships\" : ").append(com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1.marshall(a0.getOutgoingRelationships(), a1)).append(",").append("\"incomingRelationships\" : ").append(com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1.marshall(a0.getIncomingRelationships(), a1)).append(",").append("\"revisions\" : ").append(com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"title\" : ").append(java_lang_String.marshall(a0.getTitle(), a1)).append(",").append("\"xml\" : ").append(java_lang_String.marshall(a0.getXml(), a1)).append(",").append("\"xmlErrors\" : ").append(java_lang_String.marshall(a0.getXmlErrors(), a1)).append(",").append("\"html\" : ").append(java_lang_String.marshall(a0.getHtml(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"locale\" : ").append(java_lang_String.marshall(a0.getLocale(), a1)).append(",").append("\"tags\" : ").append(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.marshall(a0.getTags(), a1)).append(",").append("\"sourceUrls_OTM\" : ").append(com_redhat_topicindex_rest_collections_RESTTopicSourceUrlCollectionV1.marshall(a0.getSourceUrls_OTM(), a1)).append(",").append("\"properties\" : ").append(com_redhat_topicindex_rest_collections_RESTPropertyTagCollectionV1.marshall(a0.getProperties(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"addItem\" : ").append(java_lang_Boolean.marshall(a0.getAddItem(), a1)).append(",").append("\"removeItem\" : ").append(java_lang_Boolean.marshall(a0.getRemoveItem(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicV1", com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicV1);
    java_util_ConcurrentModificationException = new Marshaller<ConcurrentModificationException>() {
      public Class getTypeHandled() {
        return ConcurrentModificationException.class;
      }
      public ConcurrentModificationException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(ConcurrentModificationException.class, objId);
          }
          ConcurrentModificationException entity = new ConcurrentModificationException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.util.ConcurrentModificationException", t);
        }
      }
      public String marshall(ConcurrentModificationException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.util.ConcurrentModificationException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.util.ConcurrentModificationException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.util.ConcurrentModificationException", java_util_ConcurrentModificationException);
    com_redhat_topicindex_rest_entities_interfaces_RESTCategoryV1 = new Marshaller<RESTCategoryV1>() {
      public Class getTypeHandled() {
        return RESTCategoryV1.class;
      }
      public RESTCategoryV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTCategoryV1.class, objId);
          }
          RESTCategoryV1 entity = new RESTCategoryV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("mutuallyExclusive")) && (!obj.get("mutuallyExclusive").isNull())) {
            entity.setMutuallyExclusive((boolean) java_lang_Boolean.demarshall(obj.get("mutuallyExclusive"), a1));
          }
          if ((obj.containsKey("sort")) && (!obj.get("sort").isNull())) {
            entity.setSort(java_lang_Integer.demarshall(obj.get("sort"), a1));
          }
          if ((obj.containsKey("tags")) && (!obj.get("tags").isNull())) {
            entity.setTags(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.demarshall(obj.get("tags"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(com_redhat_topicindex_rest_collections_RESTCategoryCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("configuredParameters")) && (!obj.get("configuredParameters").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setConfiguredParameters(java_util_List.demarshall(obj.get("configuredParameters"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("selfLink")) && (!obj.get("selfLink").isNull())) {
            entity.setSelfLink(java_lang_String.demarshall(obj.get("selfLink"), a1));
          }
          if ((obj.containsKey("editLink")) && (!obj.get("editLink").isNull())) {
            entity.setEditLink(java_lang_String.demarshall(obj.get("editLink"), a1));
          }
          if ((obj.containsKey("deleteLink")) && (!obj.get("deleteLink").isNull())) {
            entity.setDeleteLink(java_lang_String.demarshall(obj.get("deleteLink"), a1));
          }
          if ((obj.containsKey("addLink")) && (!obj.get("addLink").isNull())) {
            entity.setAddLink(java_lang_String.demarshall(obj.get("addLink"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("addItem")) && (!obj.get("addItem").isNull())) {
            entity.setAddItem((boolean) java_lang_Boolean.demarshall(obj.get("addItem"), a1));
          }
          if ((obj.containsKey("removeItem")) && (!obj.get("removeItem").isNull())) {
            entity.setRemoveItem((boolean) java_lang_Boolean.demarshall(obj.get("removeItem"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.entities.interfaces.RESTCategoryV1", t);
        }
      }
      public String marshall(RESTCategoryV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTCategoryV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3456).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTCategoryV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"mutuallyExclusive\" : ").append(java_lang_Boolean.marshall(a0.getMutuallyExclusive(), a1)).append(",").append("\"sort\" : ").append(java_lang_Integer.marshall(a0.getSort(), a1)).append(",").append("\"tags\" : ").append(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.marshall(a0.getTags(), a1)).append(",").append("\"revisions\" : ").append(com_redhat_topicindex_rest_collections_RESTCategoryCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"addItem\" : ").append(java_lang_Boolean.marshall(a0.getAddItem(), a1)).append(",").append("\"removeItem\" : ").append(java_lang_Boolean.marshall(a0.getRemoveItem(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.entities.interfaces.RESTCategoryV1", com_redhat_topicindex_rest_entities_interfaces_RESTCategoryV1);
    com_redhat_topicindex_rest_collections_RESTTopicSourceUrlCollectionV1 = new Marshaller<RESTTopicSourceUrlCollectionV1>() {
      public Class getTypeHandled() {
        return RESTTopicSourceUrlCollectionV1.class;
      }
      public RESTTopicSourceUrlCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTopicSourceUrlCollectionV1.class, objId);
          }
          RESTTopicSourceUrlCollectionV1 entity = new RESTTopicSourceUrlCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTTopicSourceUrlV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTTopicSourceUrlCollectionV1", t);
        }
      }
      public String marshall(RESTTopicSourceUrlCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTTopicSourceUrlCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTTopicSourceUrlCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTTopicSourceUrlCollectionV1", com_redhat_topicindex_rest_collections_RESTTopicSourceUrlCollectionV1);
    com_redhat_topicindex_rest_collections_RESTBugzillaBugCollectionV1 = new Marshaller<RESTBugzillaBugCollectionV1>() {
      public Class getTypeHandled() {
        return RESTBugzillaBugCollectionV1.class;
      }
      public RESTBugzillaBugCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTBugzillaBugCollectionV1.class, objId);
          }
          RESTBugzillaBugCollectionV1 entity = new RESTBugzillaBugCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTBugzillaBugV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTBugzillaBugCollectionV1", t);
        }
      }
      public String marshall(RESTBugzillaBugCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTBugzillaBugCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTBugzillaBugCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTBugzillaBugCollectionV1", com_redhat_topicindex_rest_collections_RESTBugzillaBugCollectionV1);
    com_redhat_topicindex_rest_collections_RESTRoleCollectionV1 = new Marshaller<RESTRoleCollectionV1>() {
      public Class getTypeHandled() {
        return RESTRoleCollectionV1.class;
      }
      public RESTRoleCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTRoleCollectionV1.class, objId);
          }
          RESTRoleCollectionV1 entity = new RESTRoleCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTRoleV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTRoleCollectionV1", t);
        }
      }
      public String marshall(RESTRoleCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTRoleCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTRoleCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTRoleCollectionV1", com_redhat_topicindex_rest_collections_RESTRoleCollectionV1);
    java_lang_NullPointerException = new Marshaller<NullPointerException>() {
      public Class getTypeHandled() {
        return NullPointerException.class;
      }
      public NullPointerException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(NullPointerException.class, objId);
          }
          NullPointerException entity = new NullPointerException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.NullPointerException", t);
        }
      }
      public String marshall(NullPointerException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.NullPointerException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.NullPointerException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.NullPointerException", java_lang_NullPointerException);
    com_redhat_topicindex_rest_entities_interfaces_RESTTopicSourceUrlV1 = new Marshaller<RESTTopicSourceUrlV1>() {
      public Class getTypeHandled() {
        return RESTTopicSourceUrlV1.class;
      }
      public RESTTopicSourceUrlV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTopicSourceUrlV1.class, objId);
          }
          RESTTopicSourceUrlV1 entity = new RESTTopicSourceUrlV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("url")) && (!obj.get("url").isNull())) {
            entity.setUrl(java_lang_String.demarshall(obj.get("url"), a1));
          }
          if ((obj.containsKey("title")) && (!obj.get("title").isNull())) {
            entity.setTitle(java_lang_String.demarshall(obj.get("title"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(com_redhat_topicindex_rest_collections_RESTTopicSourceUrlCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("configuredParameters")) && (!obj.get("configuredParameters").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setConfiguredParameters(java_util_List.demarshall(obj.get("configuredParameters"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("selfLink")) && (!obj.get("selfLink").isNull())) {
            entity.setSelfLink(java_lang_String.demarshall(obj.get("selfLink"), a1));
          }
          if ((obj.containsKey("editLink")) && (!obj.get("editLink").isNull())) {
            entity.setEditLink(java_lang_String.demarshall(obj.get("editLink"), a1));
          }
          if ((obj.containsKey("deleteLink")) && (!obj.get("deleteLink").isNull())) {
            entity.setDeleteLink(java_lang_String.demarshall(obj.get("deleteLink"), a1));
          }
          if ((obj.containsKey("addLink")) && (!obj.get("addLink").isNull())) {
            entity.setAddLink(java_lang_String.demarshall(obj.get("addLink"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("addItem")) && (!obj.get("addItem").isNull())) {
            entity.setAddItem((boolean) java_lang_Boolean.demarshall(obj.get("addItem"), a1));
          }
          if ((obj.containsKey("removeItem")) && (!obj.get("removeItem").isNull())) {
            entity.setRemoveItem((boolean) java_lang_Boolean.demarshall(obj.get("removeItem"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.entities.interfaces.RESTTopicSourceUrlV1", t);
        }
      }
      public String marshall(RESTTopicSourceUrlV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTTopicSourceUrlV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(2560).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTTopicSourceUrlV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"url\" : ").append(java_lang_String.marshall(a0.getUrl(), a1)).append(",").append("\"title\" : ").append(java_lang_String.marshall(a0.getTitle(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"revisions\" : ").append(com_redhat_topicindex_rest_collections_RESTTopicSourceUrlCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"addItem\" : ").append(java_lang_Boolean.marshall(a0.getAddItem(), a1)).append(",").append("\"removeItem\" : ").append(java_lang_Boolean.marshall(a0.getRemoveItem(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.entities.interfaces.RESTTopicSourceUrlV1", com_redhat_topicindex_rest_entities_interfaces_RESTTopicSourceUrlV1);
    arrayOf_byte_D1 = new QualifyingMarshallerWrapper(new Marshaller<byte[]>() {
      private byte[] _demarshall1(EJArray a0, MarshallingSession a1) {
        byte[] newArray = new byte[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Byte.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(byte[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Byte.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return byte.class;
      }
      public byte[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(byte[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[B", arrayOf_byte_D1);
    com_redhat_topicindex_rest_entities_interfaces_RESTLanguageImageV1 = new Marshaller<RESTLanguageImageV1>() {
      public Class getTypeHandled() {
        return RESTLanguageImageV1.class;
      }
      public RESTLanguageImageV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTLanguageImageV1.class, objId);
          }
          RESTLanguageImageV1 entity = new RESTLanguageImageV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("image")) && (!obj.get("image").isNull())) {
            entity.setImage(com_redhat_topicindex_rest_entities_interfaces_RESTImageV1.demarshall(obj.get("image"), a1));
          }
          if ((obj.containsKey("imageData")) && (!obj.get("imageData").isNull())) {
            entity.setImageData((byte[]) arrayOf_byte_D1.demarshall(obj.get("imageData"), a1));
          }
          if ((obj.containsKey("thumbnail")) && (!obj.get("thumbnail").isNull())) {
            entity.setThumbnail((byte[]) arrayOf_byte_D1.demarshall(obj.get("thumbnail"), a1));
          }
          if ((obj.containsKey("imageDataBase64")) && (!obj.get("imageDataBase64").isNull())) {
            entity.setImageDataBase64((byte[]) arrayOf_byte_D1.demarshall(obj.get("imageDataBase64"), a1));
          }
          if ((obj.containsKey("locale")) && (!obj.get("locale").isNull())) {
            entity.setLocale(java_lang_String.demarshall(obj.get("locale"), a1));
          }
          if ((obj.containsKey("filename")) && (!obj.get("filename").isNull())) {
            entity.setFilename(java_lang_String.demarshall(obj.get("filename"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(com_redhat_topicindex_rest_collections_RESTLanguageImageCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("configuredParameters")) && (!obj.get("configuredParameters").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setConfiguredParameters(java_util_List.demarshall(obj.get("configuredParameters"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("selfLink")) && (!obj.get("selfLink").isNull())) {
            entity.setSelfLink(java_lang_String.demarshall(obj.get("selfLink"), a1));
          }
          if ((obj.containsKey("editLink")) && (!obj.get("editLink").isNull())) {
            entity.setEditLink(java_lang_String.demarshall(obj.get("editLink"), a1));
          }
          if ((obj.containsKey("deleteLink")) && (!obj.get("deleteLink").isNull())) {
            entity.setDeleteLink(java_lang_String.demarshall(obj.get("deleteLink"), a1));
          }
          if ((obj.containsKey("addLink")) && (!obj.get("addLink").isNull())) {
            entity.setAddLink(java_lang_String.demarshall(obj.get("addLink"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("addItem")) && (!obj.get("addItem").isNull())) {
            entity.setAddItem((boolean) java_lang_Boolean.demarshall(obj.get("addItem"), a1));
          }
          if ((obj.containsKey("removeItem")) && (!obj.get("removeItem").isNull())) {
            entity.setRemoveItem((boolean) java_lang_Boolean.demarshall(obj.get("removeItem"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.entities.interfaces.RESTLanguageImageV1", t);
        }
      }
      public String marshall(RESTLanguageImageV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTLanguageImageV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(6528).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTLanguageImageV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"image\" : ").append(com_redhat_topicindex_rest_entities_interfaces_RESTImageV1.marshall(a0.getImage(), a1)).append(",").append("\"imageData\" : ").append(arrayOf_byte_D1.marshall(a0.getImageData(), a1)).append(",").append("\"thumbnail\" : ").append(arrayOf_byte_D1.marshall(a0.getThumbnail(), a1)).append(",").append("\"imageDataBase64\" : ").append(arrayOf_byte_D1.marshall(a0.getImageDataBase64(), a1)).append(",").append("\"locale\" : ").append(java_lang_String.marshall(a0.getLocale(), a1)).append(",").append("\"filename\" : ").append(java_lang_String.marshall(a0.getFilename(), a1)).append(",").append("\"revisions\" : ").append(com_redhat_topicindex_rest_collections_RESTLanguageImageCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"addItem\" : ").append(java_lang_Boolean.marshall(a0.getAddItem(), a1)).append(",").append("\"removeItem\" : ").append(java_lang_Boolean.marshall(a0.getRemoveItem(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.entities.interfaces.RESTLanguageImageV1", com_redhat_topicindex_rest_entities_interfaces_RESTLanguageImageV1);
    com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicStringV1 = new Marshaller<RESTTranslatedTopicStringV1>() {
      public Class getTypeHandled() {
        return RESTTranslatedTopicStringV1.class;
      }
      public RESTTranslatedTopicStringV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTranslatedTopicStringV1.class, objId);
          }
          RESTTranslatedTopicStringV1 entity = new RESTTranslatedTopicStringV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("translatedTopic")) && (!obj.get("translatedTopic").isNull())) {
            entity.setTranslatedTopic(com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicV1.demarshall(obj.get("translatedTopic"), a1));
          }
          if ((obj.containsKey("originalString")) && (!obj.get("originalString").isNull())) {
            entity.setOriginalString(java_lang_String.demarshall(obj.get("originalString"), a1));
          }
          if ((obj.containsKey("translatedString")) && (!obj.get("translatedString").isNull())) {
            entity.setTranslatedString(java_lang_String.demarshall(obj.get("translatedString"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(com_redhat_topicindex_rest_collections_RESTTranslatedTopicStringCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("configuredParameters")) && (!obj.get("configuredParameters").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setConfiguredParameters(java_util_List.demarshall(obj.get("configuredParameters"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("selfLink")) && (!obj.get("selfLink").isNull())) {
            entity.setSelfLink(java_lang_String.demarshall(obj.get("selfLink"), a1));
          }
          if ((obj.containsKey("editLink")) && (!obj.get("editLink").isNull())) {
            entity.setEditLink(java_lang_String.demarshall(obj.get("editLink"), a1));
          }
          if ((obj.containsKey("deleteLink")) && (!obj.get("deleteLink").isNull())) {
            entity.setDeleteLink(java_lang_String.demarshall(obj.get("deleteLink"), a1));
          }
          if ((obj.containsKey("addLink")) && (!obj.get("addLink").isNull())) {
            entity.setAddLink(java_lang_String.demarshall(obj.get("addLink"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("addItem")) && (!obj.get("addItem").isNull())) {
            entity.setAddItem((boolean) java_lang_Boolean.demarshall(obj.get("addItem"), a1));
          }
          if ((obj.containsKey("removeItem")) && (!obj.get("removeItem").isNull())) {
            entity.setRemoveItem((boolean) java_lang_Boolean.demarshall(obj.get("removeItem"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicStringV1", t);
        }
      }
      public String marshall(RESTTranslatedTopicStringV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicStringV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(13824).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicStringV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"translatedTopic\" : ").append(com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicV1.marshall(a0.getTranslatedTopic(), a1)).append(",").append("\"originalString\" : ").append(java_lang_String.marshall(a0.getOriginalString(), a1)).append(",").append("\"translatedString\" : ").append(java_lang_String.marshall(a0.getTranslatedString(), a1)).append(",").append("\"revisions\" : ").append(com_redhat_topicindex_rest_collections_RESTTranslatedTopicStringCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"addItem\" : ").append(java_lang_Boolean.marshall(a0.getAddItem(), a1)).append(",").append("\"removeItem\" : ").append(java_lang_Boolean.marshall(a0.getRemoveItem(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicStringV1", com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicStringV1);
    java_lang_ArithmeticException = new Marshaller<ArithmeticException>() {
      public Class getTypeHandled() {
        return ArithmeticException.class;
      }
      public ArithmeticException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(ArithmeticException.class, objId);
          }
          ArithmeticException entity = new ArithmeticException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.ArithmeticException", t);
        }
      }
      public String marshall(ArithmeticException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.ArithmeticException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.ArithmeticException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.ArithmeticException", java_lang_ArithmeticException);
    java_util_EmptyStackException = new Marshaller<EmptyStackException>() {
      public Class getTypeHandled() {
        return EmptyStackException.class;
      }
      public EmptyStackException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(EmptyStackException.class, objId);
          }
          EmptyStackException entity = new EmptyStackException();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.util.EmptyStackException", t);
        }
      }
      public String marshall(EmptyStackException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.util.EmptyStackException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.util.EmptyStackException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.util.EmptyStackException", java_util_EmptyStackException);
    com_redhat_topicindex_rest_collections_RESTStringConstantCollectionV1 = new Marshaller<RESTStringConstantCollectionV1>() {
      public Class getTypeHandled() {
        return RESTStringConstantCollectionV1.class;
      }
      public RESTStringConstantCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTStringConstantCollectionV1.class, objId);
          }
          RESTStringConstantCollectionV1 entity = new RESTStringConstantCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTStringConstantV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTStringConstantCollectionV1", t);
        }
      }
      public String marshall(RESTStringConstantCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTStringConstantCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTStringConstantCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTStringConstantCollectionV1", com_redhat_topicindex_rest_collections_RESTStringConstantCollectionV1);
    com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1 = new Marshaller<RESTTranslatedTopicCollectionV1>() {
      public Class getTypeHandled() {
        return RESTTranslatedTopicCollectionV1.class;
      }
      public RESTTranslatedTopicCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTranslatedTopicCollectionV1.class, objId);
          }
          RESTTranslatedTopicCollectionV1 entity = new RESTTranslatedTopicCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTTranslatedTopicCollectionV1", t);
        }
      }
      public String marshall(RESTTranslatedTopicCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTTranslatedTopicCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTTranslatedTopicCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTTranslatedTopicCollectionV1", com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1);
    java_io_IOException = new Marshaller<IOException>() {
      public Class getTypeHandled() {
        return IOException.class;
      }
      public IOException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(IOException.class, objId);
          }
          IOException entity = new IOException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.io.IOException", t);
        }
      }
      public String marshall(IOException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.io.IOException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.io.IOException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.io.IOException", java_io_IOException);
    com_redhat_topicindex_rest_collections_RESTProjectCollectionV1 = new Marshaller<RESTProjectCollectionV1>() {
      public Class getTypeHandled() {
        return RESTProjectCollectionV1.class;
      }
      public RESTProjectCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTProjectCollectionV1.class, objId);
          }
          RESTProjectCollectionV1 entity = new RESTProjectCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTProjectV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTProjectCollectionV1", t);
        }
      }
      public String marshall(RESTProjectCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTProjectCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTProjectCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTProjectCollectionV1", com_redhat_topicindex_rest_collections_RESTProjectCollectionV1);
    com_redhat_topicindex_rest_entities_interfaces_RESTPropertyTagV1 = new Marshaller<RESTPropertyTagV1>() {
      public Class getTypeHandled() {
        return RESTPropertyTagV1.class;
      }
      public RESTPropertyTagV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTPropertyTagV1.class, objId);
          }
          RESTPropertyTagV1 entity = new RESTPropertyTagV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("value")) && (!obj.get("value").isNull())) {
            entity.setValue(java_lang_String.demarshall(obj.get("value"), a1));
          }
          if ((obj.containsKey("valid")) && (!obj.get("valid").isNull())) {
            entity.setValid((boolean) java_lang_Boolean.demarshall(obj.get("valid"), a1));
          }
          if ((obj.containsKey("regex")) && (!obj.get("regex").isNull())) {
            entity.setRegex(java_lang_String.demarshall(obj.get("regex"), a1));
          }
          if ((obj.containsKey("canBeNull")) && (!obj.get("canBeNull").isNull())) {
            entity.setCanBeNull((boolean) java_lang_Boolean.demarshall(obj.get("canBeNull"), a1));
          }
          if ((obj.containsKey("isUnique")) && (!obj.get("isUnique").isNull())) {
            entity.setIsUnique((boolean) java_lang_Boolean.demarshall(obj.get("isUnique"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(com_redhat_topicindex_rest_collections_RESTPropertyTagCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("configuredParameters")) && (!obj.get("configuredParameters").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setConfiguredParameters(java_util_List.demarshall(obj.get("configuredParameters"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("selfLink")) && (!obj.get("selfLink").isNull())) {
            entity.setSelfLink(java_lang_String.demarshall(obj.get("selfLink"), a1));
          }
          if ((obj.containsKey("editLink")) && (!obj.get("editLink").isNull())) {
            entity.setEditLink(java_lang_String.demarshall(obj.get("editLink"), a1));
          }
          if ((obj.containsKey("deleteLink")) && (!obj.get("deleteLink").isNull())) {
            entity.setDeleteLink(java_lang_String.demarshall(obj.get("deleteLink"), a1));
          }
          if ((obj.containsKey("addLink")) && (!obj.get("addLink").isNull())) {
            entity.setAddLink(java_lang_String.demarshall(obj.get("addLink"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("addItem")) && (!obj.get("addItem").isNull())) {
            entity.setAddItem((boolean) java_lang_Boolean.demarshall(obj.get("addItem"), a1));
          }
          if ((obj.containsKey("removeItem")) && (!obj.get("removeItem").isNull())) {
            entity.setRemoveItem((boolean) java_lang_Boolean.demarshall(obj.get("removeItem"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.entities.interfaces.RESTPropertyTagV1", t);
        }
      }
      public String marshall(RESTPropertyTagV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTPropertyTagV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3072).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTPropertyTagV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"value\" : ").append(java_lang_String.marshall(a0.getValue(), a1)).append(",").append("\"valid\" : ").append(java_lang_Boolean.marshall(a0.getValid(), a1)).append(",").append("\"regex\" : ").append(java_lang_String.marshall(a0.getRegex(), a1)).append(",").append("\"canBeNull\" : ").append(java_lang_Boolean.marshall(a0.getCanBeNull(), a1)).append(",").append("\"isUnique\" : ").append(java_lang_Boolean.marshall(a0.getIsUnique(), a1)).append(",").append("\"revisions\" : ").append(com_redhat_topicindex_rest_collections_RESTPropertyTagCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"addItem\" : ").append(java_lang_Boolean.marshall(a0.getAddItem(), a1)).append(",").append("\"removeItem\" : ").append(java_lang_Boolean.marshall(a0.getRemoveItem(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.entities.interfaces.RESTPropertyTagV1", com_redhat_topicindex_rest_entities_interfaces_RESTPropertyTagV1);
    com_redhat_topicindex_rest_entities_interfaces_RESTTagV1 = new Marshaller<RESTTagV1>() {
      public Class getTypeHandled() {
        return RESTTagV1.class;
      }
      public RESTTagV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTagV1.class, objId);
          }
          RESTTagV1 entity = new RESTTagV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("categories")) && (!obj.get("categories").isNull())) {
            entity.setCategories(com_redhat_topicindex_rest_collections_RESTCategoryCollectionV1.demarshall(obj.get("categories"), a1));
          }
          if ((obj.containsKey("parentTags")) && (!obj.get("parentTags").isNull())) {
            entity.setParentTags(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.demarshall(obj.get("parentTags"), a1));
          }
          if ((obj.containsKey("childTags")) && (!obj.get("childTags").isNull())) {
            entity.setChildTags(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.demarshall(obj.get("childTags"), a1));
          }
          if ((obj.containsKey("projects")) && (!obj.get("projects").isNull())) {
            entity.setProjects(com_redhat_topicindex_rest_collections_RESTProjectCollectionV1.demarshall(obj.get("projects"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("properties")) && (!obj.get("properties").isNull())) {
            entity.setProperties(com_redhat_topicindex_rest_collections_RESTPropertyTagCollectionV1.demarshall(obj.get("properties"), a1));
          }
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("configuredParameters")) && (!obj.get("configuredParameters").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setConfiguredParameters(java_util_List.demarshall(obj.get("configuredParameters"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("selfLink")) && (!obj.get("selfLink").isNull())) {
            entity.setSelfLink(java_lang_String.demarshall(obj.get("selfLink"), a1));
          }
          if ((obj.containsKey("editLink")) && (!obj.get("editLink").isNull())) {
            entity.setEditLink(java_lang_String.demarshall(obj.get("editLink"), a1));
          }
          if ((obj.containsKey("deleteLink")) && (!obj.get("deleteLink").isNull())) {
            entity.setDeleteLink(java_lang_String.demarshall(obj.get("deleteLink"), a1));
          }
          if ((obj.containsKey("addLink")) && (!obj.get("addLink").isNull())) {
            entity.setAddLink(java_lang_String.demarshall(obj.get("addLink"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("addItem")) && (!obj.get("addItem").isNull())) {
            entity.setAddItem((boolean) java_lang_Boolean.demarshall(obj.get("addItem"), a1));
          }
          if ((obj.containsKey("removeItem")) && (!obj.get("removeItem").isNull())) {
            entity.setRemoveItem((boolean) java_lang_Boolean.demarshall(obj.get("removeItem"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.entities.interfaces.RESTTagV1", t);
        }
      }
      public String marshall(RESTTagV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTTagV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(4992).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTTagV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"categories\" : ").append(com_redhat_topicindex_rest_collections_RESTCategoryCollectionV1.marshall(a0.getCategories(), a1)).append(",").append("\"parentTags\" : ").append(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.marshall(a0.getParentTags(), a1)).append(",").append("\"childTags\" : ").append(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.marshall(a0.getChildTags(), a1)).append(",").append("\"projects\" : ").append(com_redhat_topicindex_rest_collections_RESTProjectCollectionV1.marshall(a0.getProjects(), a1)).append(",").append("\"revisions\" : ").append(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"properties\" : ").append(com_redhat_topicindex_rest_collections_RESTPropertyTagCollectionV1.marshall(a0.getProperties(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"addItem\" : ").append(java_lang_Boolean.marshall(a0.getAddItem(), a1)).append(",").append("\"removeItem\" : ").append(java_lang_Boolean.marshall(a0.getRemoveItem(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.entities.interfaces.RESTTagV1", com_redhat_topicindex_rest_entities_interfaces_RESTTagV1);
    java_lang_ClassCastException = new Marshaller<ClassCastException>() {
      public Class getTypeHandled() {
        return ClassCastException.class;
      }
      public ClassCastException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(ClassCastException.class, objId);
          }
          ClassCastException entity = new ClassCastException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.ClassCastException", t);
        }
      }
      public String marshall(ClassCastException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.ClassCastException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.ClassCastException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.ClassCastException", java_lang_ClassCastException);
    java_lang_NegativeArraySizeException = new Marshaller<NegativeArraySizeException>() {
      public Class getTypeHandled() {
        return NegativeArraySizeException.class;
      }
      public NegativeArraySizeException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(NegativeArraySizeException.class, objId);
          }
          NegativeArraySizeException entity = new NegativeArraySizeException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.NegativeArraySizeException", t);
        }
      }
      public String marshall(NegativeArraySizeException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.NegativeArraySizeException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.NegativeArraySizeException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.NegativeArraySizeException", java_lang_NegativeArraySizeException);
    com_redhat_topicindex_rest_entities_interfaces_RESTTopicV1 = new Marshaller<RESTTopicV1>() {
      public Class getTypeHandled() {
        return RESTTopicV1.class;
      }
      public RESTTopicV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTopicV1.class, objId);
          }
          RESTTopicV1 entity = new RESTTopicV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("created")) && (!obj.get("created").isNull())) {
            entity.setCreated((Date) java_lang_Object.demarshall(obj.get("created"), a1));
          }
          if ((obj.containsKey("lastModified")) && (!obj.get("lastModified").isNull())) {
            entity.setLastModified((Date) java_lang_Object.demarshall(obj.get("lastModified"), a1));
          }
          if ((obj.containsKey("bugzillaBugs_OTM")) && (!obj.get("bugzillaBugs_OTM").isNull())) {
            entity.setBugzillaBugs_OTM(com_redhat_topicindex_rest_collections_RESTBugzillaBugCollectionV1.demarshall(obj.get("bugzillaBugs_OTM"), a1));
          }
          if ((obj.containsKey("translatedTopics_OTM")) && (!obj.get("translatedTopics_OTM").isNull())) {
            entity.setTranslatedTopics_OTM(com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1.demarshall(obj.get("translatedTopics_OTM"), a1));
          }
          if ((obj.containsKey("outgoingRelationships")) && (!obj.get("outgoingRelationships").isNull())) {
            entity.setOutgoingRelationships(com_redhat_topicindex_rest_collections_RESTTopicCollectionV1.demarshall(obj.get("outgoingRelationships"), a1));
          }
          if ((obj.containsKey("incomingRelationships")) && (!obj.get("incomingRelationships").isNull())) {
            entity.setIncomingRelationships(com_redhat_topicindex_rest_collections_RESTTopicCollectionV1.demarshall(obj.get("incomingRelationships"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(com_redhat_topicindex_rest_collections_RESTTopicCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("title")) && (!obj.get("title").isNull())) {
            entity.setTitle(java_lang_String.demarshall(obj.get("title"), a1));
          }
          if ((obj.containsKey("xml")) && (!obj.get("xml").isNull())) {
            entity.setXml(java_lang_String.demarshall(obj.get("xml"), a1));
          }
          if ((obj.containsKey("xmlErrors")) && (!obj.get("xmlErrors").isNull())) {
            entity.setXmlErrors(java_lang_String.demarshall(obj.get("xmlErrors"), a1));
          }
          if ((obj.containsKey("html")) && (!obj.get("html").isNull())) {
            entity.setHtml(java_lang_String.demarshall(obj.get("html"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("locale")) && (!obj.get("locale").isNull())) {
            entity.setLocale(java_lang_String.demarshall(obj.get("locale"), a1));
          }
          if ((obj.containsKey("tags")) && (!obj.get("tags").isNull())) {
            entity.setTags(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.demarshall(obj.get("tags"), a1));
          }
          if ((obj.containsKey("sourceUrls_OTM")) && (!obj.get("sourceUrls_OTM").isNull())) {
            entity.setSourceUrls_OTM(com_redhat_topicindex_rest_collections_RESTTopicSourceUrlCollectionV1.demarshall(obj.get("sourceUrls_OTM"), a1));
          }
          if ((obj.containsKey("properties")) && (!obj.get("properties").isNull())) {
            entity.setProperties(com_redhat_topicindex_rest_collections_RESTPropertyTagCollectionV1.demarshall(obj.get("properties"), a1));
          }
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("configuredParameters")) && (!obj.get("configuredParameters").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setConfiguredParameters(java_util_List.demarshall(obj.get("configuredParameters"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("selfLink")) && (!obj.get("selfLink").isNull())) {
            entity.setSelfLink(java_lang_String.demarshall(obj.get("selfLink"), a1));
          }
          if ((obj.containsKey("editLink")) && (!obj.get("editLink").isNull())) {
            entity.setEditLink(java_lang_String.demarshall(obj.get("editLink"), a1));
          }
          if ((obj.containsKey("deleteLink")) && (!obj.get("deleteLink").isNull())) {
            entity.setDeleteLink(java_lang_String.demarshall(obj.get("deleteLink"), a1));
          }
          if ((obj.containsKey("addLink")) && (!obj.get("addLink").isNull())) {
            entity.setAddLink(java_lang_String.demarshall(obj.get("addLink"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("addItem")) && (!obj.get("addItem").isNull())) {
            entity.setAddItem((boolean) java_lang_Boolean.demarshall(obj.get("addItem"), a1));
          }
          if ((obj.containsKey("removeItem")) && (!obj.get("removeItem").isNull())) {
            entity.setRemoveItem((boolean) java_lang_Boolean.demarshall(obj.get("removeItem"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.entities.interfaces.RESTTopicV1", t);
        }
      }
      public String marshall(RESTTopicV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTTopicV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(7424).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTTopicV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"created\" : ").append(java_lang_Object.marshall(a0.getCreated(), a1)).append(",").append("\"lastModified\" : ").append(java_lang_Object.marshall(a0.getLastModified(), a1)).append(",").append("\"bugzillaBugs_OTM\" : ").append(com_redhat_topicindex_rest_collections_RESTBugzillaBugCollectionV1.marshall(a0.getBugzillaBugs_OTM(), a1)).append(",").append("\"translatedTopics_OTM\" : ").append(com_redhat_topicindex_rest_collections_RESTTranslatedTopicCollectionV1.marshall(a0.getTranslatedTopics_OTM(), a1)).append(",").append("\"outgoingRelationships\" : ").append(com_redhat_topicindex_rest_collections_RESTTopicCollectionV1.marshall(a0.getOutgoingRelationships(), a1)).append(",").append("\"incomingRelationships\" : ").append(com_redhat_topicindex_rest_collections_RESTTopicCollectionV1.marshall(a0.getIncomingRelationships(), a1)).append(",").append("\"revisions\" : ").append(com_redhat_topicindex_rest_collections_RESTTopicCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"title\" : ").append(java_lang_String.marshall(a0.getTitle(), a1)).append(",").append("\"xml\" : ").append(java_lang_String.marshall(a0.getXml(), a1)).append(",").append("\"xmlErrors\" : ").append(java_lang_String.marshall(a0.getXmlErrors(), a1)).append(",").append("\"html\" : ").append(java_lang_String.marshall(a0.getHtml(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"locale\" : ").append(java_lang_String.marshall(a0.getLocale(), a1)).append(",").append("\"tags\" : ").append(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.marshall(a0.getTags(), a1)).append(",").append("\"sourceUrls_OTM\" : ").append(com_redhat_topicindex_rest_collections_RESTTopicSourceUrlCollectionV1.marshall(a0.getSourceUrls_OTM(), a1)).append(",").append("\"properties\" : ").append(com_redhat_topicindex_rest_collections_RESTPropertyTagCollectionV1.marshall(a0.getProperties(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"addItem\" : ").append(java_lang_Boolean.marshall(a0.getAddItem(), a1)).append(",").append("\"removeItem\" : ").append(java_lang_Boolean.marshall(a0.getRemoveItem(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.entities.interfaces.RESTTopicV1", com_redhat_topicindex_rest_entities_interfaces_RESTTopicV1);
    com_redhat_topicindex_rest_entities_interfaces_RESTBugzillaBugV1 = new Marshaller<RESTBugzillaBugV1>() {
      public Class getTypeHandled() {
        return RESTBugzillaBugV1.class;
      }
      public RESTBugzillaBugV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTBugzillaBugV1.class, objId);
          }
          RESTBugzillaBugV1 entity = new RESTBugzillaBugV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("bugId")) && (!obj.get("bugId").isNull())) {
            entity.setBugId(java_lang_Integer.demarshall(obj.get("bugId"), a1));
          }
          if ((obj.containsKey("isOpen")) && (!obj.get("isOpen").isNull())) {
            entity.setIsOpen(java_lang_Boolean.demarshall(obj.get("isOpen"), a1));
          }
          if ((obj.containsKey("summary")) && (!obj.get("summary").isNull())) {
            entity.setSummary(java_lang_String.demarshall(obj.get("summary"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(com_redhat_topicindex_rest_collections_RESTBugzillaBugCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("configuredParameters")) && (!obj.get("configuredParameters").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setConfiguredParameters(java_util_List.demarshall(obj.get("configuredParameters"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("selfLink")) && (!obj.get("selfLink").isNull())) {
            entity.setSelfLink(java_lang_String.demarshall(obj.get("selfLink"), a1));
          }
          if ((obj.containsKey("editLink")) && (!obj.get("editLink").isNull())) {
            entity.setEditLink(java_lang_String.demarshall(obj.get("editLink"), a1));
          }
          if ((obj.containsKey("deleteLink")) && (!obj.get("deleteLink").isNull())) {
            entity.setDeleteLink(java_lang_String.demarshall(obj.get("deleteLink"), a1));
          }
          if ((obj.containsKey("addLink")) && (!obj.get("addLink").isNull())) {
            entity.setAddLink(java_lang_String.demarshall(obj.get("addLink"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("addItem")) && (!obj.get("addItem").isNull())) {
            entity.setAddItem((boolean) java_lang_Boolean.demarshall(obj.get("addItem"), a1));
          }
          if ((obj.containsKey("removeItem")) && (!obj.get("removeItem").isNull())) {
            entity.setRemoveItem((boolean) java_lang_Boolean.demarshall(obj.get("removeItem"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.entities.interfaces.RESTBugzillaBugV1", t);
        }
      }
      public String marshall(RESTBugzillaBugV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTBugzillaBugV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(2560).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTBugzillaBugV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"bugId\" : ").append(java_lang_Integer.marshall(a0.getBugId(), a1)).append(",").append("\"isOpen\" : ").append(java_lang_Boolean.marshall(a0.getIsOpen(), a1)).append(",").append("\"summary\" : ").append(java_lang_String.marshall(a0.getSummary(), a1)).append(",").append("\"revisions\" : ").append(com_redhat_topicindex_rest_collections_RESTBugzillaBugCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"addItem\" : ").append(java_lang_Boolean.marshall(a0.getAddItem(), a1)).append(",").append("\"removeItem\" : ").append(java_lang_Boolean.marshall(a0.getRemoveItem(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.entities.interfaces.RESTBugzillaBugV1", com_redhat_topicindex_rest_entities_interfaces_RESTBugzillaBugV1);
    com_redhat_topicindex_rest_entities_interfaces_RESTProjectV1 = new Marshaller<RESTProjectV1>() {
      public Class getTypeHandled() {
        return RESTProjectV1.class;
      }
      public RESTProjectV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTProjectV1.class, objId);
          }
          RESTProjectV1 entity = new RESTProjectV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("tags")) && (!obj.get("tags").isNull())) {
            entity.setTags(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.demarshall(obj.get("tags"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(com_redhat_topicindex_rest_collections_RESTProjectCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
            entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
          }
          if ((obj.containsKey("revision")) && (!obj.get("revision").isNull())) {
            entity.setRevision(java_lang_Integer.demarshall(obj.get("revision"), a1));
          }
          if ((obj.containsKey("configuredParameters")) && (!obj.get("configuredParameters").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setConfiguredParameters(java_util_List.demarshall(obj.get("configuredParameters"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("selfLink")) && (!obj.get("selfLink").isNull())) {
            entity.setSelfLink(java_lang_String.demarshall(obj.get("selfLink"), a1));
          }
          if ((obj.containsKey("editLink")) && (!obj.get("editLink").isNull())) {
            entity.setEditLink(java_lang_String.demarshall(obj.get("editLink"), a1));
          }
          if ((obj.containsKey("deleteLink")) && (!obj.get("deleteLink").isNull())) {
            entity.setDeleteLink(java_lang_String.demarshall(obj.get("deleteLink"), a1));
          }
          if ((obj.containsKey("addLink")) && (!obj.get("addLink").isNull())) {
            entity.setAddLink(java_lang_String.demarshall(obj.get("addLink"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("addItem")) && (!obj.get("addItem").isNull())) {
            entity.setAddItem((boolean) java_lang_Boolean.demarshall(obj.get("addItem"), a1));
          }
          if ((obj.containsKey("removeItem")) && (!obj.get("removeItem").isNull())) {
            entity.setRemoveItem((boolean) java_lang_Boolean.demarshall(obj.get("removeItem"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.entities.interfaces.RESTProjectV1", t);
        }
      }
      public String marshall(RESTProjectV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTProjectV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3200).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.entities.interfaces.RESTProjectV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"tags\" : ").append(com_redhat_topicindex_rest_collections_RESTTagCollectionV1.marshall(a0.getTags(), a1)).append(",").append("\"revisions\" : ").append(com_redhat_topicindex_rest_collections_RESTProjectCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"addItem\" : ").append(java_lang_Boolean.marshall(a0.getAddItem(), a1)).append(",").append("\"removeItem\" : ").append(java_lang_Boolean.marshall(a0.getRemoveItem(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.entities.interfaces.RESTProjectV1", com_redhat_topicindex_rest_entities_interfaces_RESTProjectV1);
    java_lang_IndexOutOfBoundsException = new Marshaller<IndexOutOfBoundsException>() {
      public Class getTypeHandled() {
        return IndexOutOfBoundsException.class;
      }
      public IndexOutOfBoundsException demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(IndexOutOfBoundsException.class, objId);
          }
          IndexOutOfBoundsException entity = new IndexOutOfBoundsException(java_lang_String.demarshall(obj.get("message"), a1));
          a1.recordObject(objId, entity);
          if ((obj.containsKey("cause")) && (!obj.get("cause").isNull())) {
            entity.initCause(java_lang_Throwable.demarshall(obj.get("cause"), a1));
          }
          if ((obj.containsKey("stackTrace")) && (!obj.get("stackTrace").isNull())) {
            entity.setStackTrace((StackTraceElement[]) arrayOf_java_lang_StackTraceElement_D1.demarshall(obj.get("stackTrace"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: java.lang.IndexOutOfBoundsException", t);
        }
      }
      public String marshall(IndexOutOfBoundsException a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"java.lang.IndexOutOfBoundsException\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"java.lang.IndexOutOfBoundsException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Throwable.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.IndexOutOfBoundsException", java_lang_IndexOutOfBoundsException);
    com_redhat_topicindex_rest_collections_RESTPropertyTagCollectionV1 = new Marshaller<RESTPropertyTagCollectionV1>() {
      public Class getTypeHandled() {
        return RESTPropertyTagCollectionV1.class;
      }
      public RESTPropertyTagCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTPropertyTagCollectionV1.class, objId);
          }
          RESTPropertyTagCollectionV1 entity = new RESTPropertyTagCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTPropertyTagV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTPropertyTagCollectionV1", t);
        }
      }
      public String marshall(RESTPropertyTagCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTPropertyTagCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTPropertyTagCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTPropertyTagCollectionV1", com_redhat_topicindex_rest_collections_RESTPropertyTagCollectionV1);
    com_redhat_topicindex_rest_collections_RESTTranslatedTopicStringCollectionV1 = new Marshaller<RESTTranslatedTopicStringCollectionV1>() {
      public Class getTypeHandled() {
        return RESTTranslatedTopicStringCollectionV1.class;
      }
      public RESTTranslatedTopicStringCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTranslatedTopicStringCollectionV1.class, objId);
          }
          RESTTranslatedTopicStringCollectionV1 entity = new RESTTranslatedTopicStringCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicStringV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTTranslatedTopicStringCollectionV1", t);
        }
      }
      public String marshall(RESTTranslatedTopicStringCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTTranslatedTopicStringCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTTranslatedTopicStringCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTTranslatedTopicStringCollectionV1", com_redhat_topicindex_rest_collections_RESTTranslatedTopicStringCollectionV1);
    com_redhat_topicindex_rest_collections_RESTImageCollectionV1 = new Marshaller<RESTImageCollectionV1>() {
      public Class getTypeHandled() {
        return RESTImageCollectionV1.class;
      }
      public RESTImageCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTImageCollectionV1.class, objId);
          }
          RESTImageCollectionV1 entity = new RESTImageCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTImageV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTImageCollectionV1", t);
        }
      }
      public String marshall(RESTImageCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTImageCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTImageCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTImageCollectionV1", com_redhat_topicindex_rest_collections_RESTImageCollectionV1);
    com_redhat_topicindex_rest_collections_RESTUserCollectionV1 = new Marshaller<RESTUserCollectionV1>() {
      public Class getTypeHandled() {
        return RESTUserCollectionV1.class;
      }
      public RESTUserCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTUserCollectionV1.class, objId);
          }
          RESTUserCollectionV1 entity = new RESTUserCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("com.redhat.topicindex.rest.entities.interfaces.RESTUserV1");
            entity.setItems(java_util_List.demarshall(obj.get("items"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("size")) && (!obj.get("size").isNull())) {
            entity.setSize(java_lang_Integer.demarshall(obj.get("size"), a1));
          }
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            entity.setExpand(java_lang_String.demarshall(obj.get("expand"), a1));
          }
          if ((obj.containsKey("startExpandIndex")) && (!obj.get("startExpandIndex").isNull())) {
            entity.setStartExpandIndex(java_lang_Integer.demarshall(obj.get("startExpandIndex"), a1));
          }
          if ((obj.containsKey("endExpandIndex")) && (!obj.get("endExpandIndex").isNull())) {
            entity.setEndExpandIndex(java_lang_Integer.demarshall(obj.get("endExpandIndex"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: com.redhat.topicindex.rest.collections.RESTUserCollectionV1", t);
        }
      }
      public String marshall(RESTUserCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTUserCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"com.redhat.topicindex.rest.collections.RESTUserCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("com.redhat.topicindex.rest.collections.RESTUserCollectionV1", com_redhat_topicindex_rest_collections_RESTUserCollectionV1);
    arrayOf_java_lang_Object_D1 = new QualifyingMarshallerWrapper(new Marshaller<Object[]>() {
      private Object[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Object[] newArray = new Object[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Object.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Object[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Object.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Object.class;
      }
      public Object[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(Object[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Object;", arrayOf_java_lang_Object_D1);
    arrayOf_java_lang_String_D1 = new QualifyingMarshallerWrapper(new Marshaller<String[]>() {
      private String[] _demarshall1(EJArray a0, MarshallingSession a1) {
        String[] newArray = new String[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_String.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(String[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_String.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return String.class;
      }
      public String[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(String[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.String;", arrayOf_java_lang_String_D1);
    arrayOf_int_D1 = new QualifyingMarshallerWrapper(new Marshaller<int[]>() {
      private int[] _demarshall1(EJArray a0, MarshallingSession a1) {
        int[] newArray = new int[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Integer.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(int[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Integer.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return int.class;
      }
      public int[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(int[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[I", arrayOf_int_D1);
    arrayOf_long_D1 = new QualifyingMarshallerWrapper(new Marshaller<long[]>() {
      private long[] _demarshall1(EJArray a0, MarshallingSession a1) {
        long[] newArray = new long[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Long.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(long[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Long.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return long.class;
      }
      public long[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(long[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[J", arrayOf_long_D1);
    arrayOf_double_D1 = new QualifyingMarshallerWrapper(new Marshaller<double[]>() {
      private double[] _demarshall1(EJArray a0, MarshallingSession a1) {
        double[] newArray = new double[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Double.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(double[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Double.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return double.class;
      }
      public double[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(double[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[D", arrayOf_double_D1);
    arrayOf_float_D1 = new QualifyingMarshallerWrapper(new Marshaller<float[]>() {
      private float[] _demarshall1(EJArray a0, MarshallingSession a1) {
        float[] newArray = new float[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Float.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(float[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Float.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return float.class;
      }
      public float[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(float[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[F", arrayOf_float_D1);
    arrayOf_short_D1 = new QualifyingMarshallerWrapper(new Marshaller<short[]>() {
      private short[] _demarshall1(EJArray a0, MarshallingSession a1) {
        short[] newArray = new short[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Short.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(short[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Short.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return short.class;
      }
      public short[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(short[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[S", arrayOf_short_D1);
    arrayOf_boolean_D1 = new QualifyingMarshallerWrapper(new Marshaller<boolean[]>() {
      private boolean[] _demarshall1(EJArray a0, MarshallingSession a1) {
        boolean[] newArray = new boolean[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Boolean.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(boolean[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Boolean.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return boolean.class;
      }
      public boolean[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(boolean[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Z", arrayOf_boolean_D1);
    arrayOf_char_D1 = new QualifyingMarshallerWrapper(new Marshaller<char[]>() {
      private char[] _demarshall1(EJArray a0, MarshallingSession a1) {
        char[] newArray = new char[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Character.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(char[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Character.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return char.class;
      }
      public char[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(char[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[C", arrayOf_char_D1);
    arrayOf_java_lang_Integer_D1 = new QualifyingMarshallerWrapper(new Marshaller<Integer[]>() {
      private Integer[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Integer[] newArray = new Integer[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Integer.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Integer[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Integer.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Integer.class;
      }
      public Integer[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(Integer[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Integer;", arrayOf_java_lang_Integer_D1);
    arrayOf_java_lang_Long_D1 = new QualifyingMarshallerWrapper(new Marshaller<Long[]>() {
      private Long[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Long[] newArray = new Long[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Long.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Long[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Long.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Long.class;
      }
      public Long[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(Long[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Long;", arrayOf_java_lang_Long_D1);
    arrayOf_java_lang_Double_D1 = new QualifyingMarshallerWrapper(new Marshaller<Double[]>() {
      private Double[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Double[] newArray = new Double[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Double.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Double[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Double.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Double.class;
      }
      public Double[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(Double[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Double;", arrayOf_java_lang_Double_D1);
    arrayOf_java_lang_Float_D1 = new QualifyingMarshallerWrapper(new Marshaller<Float[]>() {
      private Float[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Float[] newArray = new Float[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Float.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Float[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Float.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Float.class;
      }
      public Float[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(Float[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Float;", arrayOf_java_lang_Float_D1);
    arrayOf_java_lang_Short_D1 = new QualifyingMarshallerWrapper(new Marshaller<Short[]>() {
      private Short[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Short[] newArray = new Short[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Short.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Short[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Short.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Short.class;
      }
      public Short[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(Short[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Short;", arrayOf_java_lang_Short_D1);
    arrayOf_java_lang_Boolean_D1 = new QualifyingMarshallerWrapper(new Marshaller<Boolean[]>() {
      private Boolean[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Boolean[] newArray = new Boolean[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Boolean.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Boolean[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Boolean.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Boolean.class;
      }
      public Boolean[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(Boolean[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Boolean;", arrayOf_java_lang_Boolean_D1);
    arrayOf_java_lang_Byte_D1 = new QualifyingMarshallerWrapper(new Marshaller<Byte[]>() {
      private Byte[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Byte[] newArray = new Byte[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Byte.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Byte[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Byte.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Byte.class;
      }
      public Byte[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(Byte[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Byte;", arrayOf_java_lang_Byte_D1);
    arrayOf_java_lang_Character_D1 = new QualifyingMarshallerWrapper(new Marshaller<Character[]>() {
      private Character[] _demarshall1(EJArray a0, MarshallingSession a1) {
        Character[] newArray = new Character[a0.size()];
        for (int i = 0; i < newArray.length; i++) {
          newArray[i] = java_lang_Character.demarshall(a0.get(i), a1);
        }
        return newArray;
      }

      private String _marshall1(Character[] a0, MarshallingSession a1) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a0.length; i++) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(java_lang_Character.marshall(a0[i], a1));
        }
        return sb.append("]").toString();
      }
      public Class getTypeHandled() {
        return Character.class;
      }
      public Character[] demarshall(EJValue a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          EJArray arr = a0.isArray();
          return this._demarshall1(arr, a1);
        }
      }
      public String marshall(Character[] a0, MarshallingSession a1) {
        if (a0 == null) {
          return null;
        } else {
          return this._marshall1(a0, a1);
        }
      }
    });
    marshallers.put("[Ljava.lang.Character;", arrayOf_java_lang_Character_D1);
  }

  private static Field _getAccessibleField(Class cls, String name) {
    try {
      Field fld = cls.getDeclaredField(name);
      fld.setAccessible(true);
      return fld;
    } catch (Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private static void com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicV1_translatedTopicStrings(RESTTranslatedTopicV1 instance, RESTTranslatedTopicStringCollectionV1 value) {
    try {
      com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicV1_translatedTopicStrings_fld.set(instance, value);
    } catch (Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private static RESTTranslatedTopicStringCollectionV1 com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicV1_translatedTopicStrings(RESTTranslatedTopicV1 instance) {
    try {
      return (RESTTranslatedTopicStringCollectionV1) com_redhat_topicindex_rest_entities_interfaces_RESTTranslatedTopicV1_translatedTopicStrings_fld.get(instance);
    } catch (Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public Marshaller getMarshaller(String a0, String a1) {
    return marshallers.get(a1);
  }
}