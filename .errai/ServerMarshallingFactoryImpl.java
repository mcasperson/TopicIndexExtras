package org.jboss.errai.marshalling.server.impl;

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
import org.jboss.errai.marshalling.client.marshallers.LinkedHashSetMarshaller;
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
import org.jboss.pressgang.ccms.rest.v1.collections.RESTBlobConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTBugzillaBugCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterFieldCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterLocaleCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTIntegerConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTRoleCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTStringConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicStringCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTUserCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBlobConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterLocaleCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTIntegerConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTRoleCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTStringConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicSourceUrlCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicStringCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTUserCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyCategoryInPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyTagInPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTCategoryInTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyCategoryInPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyTagInPropertyCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTBugzillaBugV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterLocaleV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTIntegerConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTRoleV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicSourceUrlV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicStringV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTUserV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTLogDetailsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTCategoryInTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyCategoryInPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyTagInPropertyCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
@Dependent public class ServerMarshallingFactoryImpl implements MarshallerFactory {
  private Map<String, Marshaller> marshallers = new HashMap<String, Marshaller>();
  private FloatMarshaller java_lang_Float;
  private LinkedListMarshaller java_util_LinkedList;
  private SetMarshaller java_util_HashSet;
  private QueueMarshaller java_util_Queue;
  private LongMarshaller java_lang_Long;
  private ShortMarshaller java_lang_Short;
  private QualifyingMarshallerWrapper<LinkedHashMap> java_util_LinkedHashMap;
  private QualifyingMarshallerWrapper<Map> java_util_Map;
  private IntegerMarshaller java_lang_Integer;
  private ObjectMarshaller java_lang_Object;
  private TimestampMarshaller java_sql_Timestamp;
  private QualifyingMarshallerWrapper<SortedMap> java_util_SortedMap;
  private SQLDateMarshaller java_sql_Date;
  private SetMarshaller java_util_AbstractSet;
  private ListMarshaller java_util_Stack;
  private DateMarshaller java_util_Date;
  private TimeMarshaller java_sql_Time;
  private BigIntegerMarshaller java_math_BigInteger;
  private LinkedHashSetMarshaller java_util_LinkedHashSet;
  private CharacterMarshaller java_lang_Character;
  private ListMarshaller java_util_Vector;
  private PriorityQueueMarshaller java_util_PriorityQueue;
  private ListMarshaller java_util_List;
  private SortedSetMarshaller java_util_SortedSet;
  private QualifyingMarshallerWrapper<AbstractMap> java_util_AbstractMap;
  private QueueMarshaller java_util_AbstractQueue;
  private StringBuilderMarshaller java_lang_StringBuilder;
  private DoubleMarshaller java_lang_Double;
  private BooleanMarshaller java_lang_Boolean;
  private SortedSetMarshaller java_util_TreeSet;
  private BigDecimalMarshaller java_math_BigDecimal;
  private StringMarshaller java_lang_String;
  private QualifyingMarshallerWrapper<HashMap> java_util_HashMap;
  private StringBufferMarshaller java_lang_StringBuffer;
  private QualifyingMarshallerWrapper<TreeMap> java_util_TreeMap;
  private ListMarshaller java_util_AbstractList;
  private SetMarshaller java_util_Set;
  private ListMarshaller java_util_ArrayList;
  private ByteMarshaller java_lang_Byte;
  private Marshaller<RESTTagCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1;
  private Marshaller<RESTCategoryInTagCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_join_RESTCategoryInTagCollectionV1;
  private Marshaller<RESTProjectCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTProjectCollectionV1;
  private Marshaller<RESTAssignedPropertyTagCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1;
  private Marshaller<RESTRoleCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTRoleCollectionV1;
  private Marshaller<RESTUserCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTUserCollectionV1;
  private Marshaller<RESTUserV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTUserV1;
  private Marshaller<RESTLogDetailsV1> org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1;
  private Marshaller<RESTTagV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTTagV1;
  private Marshaller<RESTFilterTagCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterTagCollectionV1;
  private Marshaller<RESTFilterLocaleCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterLocaleCollectionV1;
  private Marshaller<RESTFilterCategoryCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterCategoryCollectionV1;
  private Marshaller<RESTFilterFieldCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterFieldCollectionV1;
  private Marshaller<RESTFilterCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterCollectionV1;
  private Marshaller<RESTFilterV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1;
  private Marshaller<RESTFilterTagV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterTagV1;
  private QualifyingMarshallerWrapper<StackTraceElement[]> arrayOf_java_lang_StackTraceElement_D1;
  private Marshaller<Throwable> java_lang_Throwable;
  private Marshaller<IllegalArgumentException> java_lang_IllegalArgumentException;
  private Marshaller<UnsupportedOperationException> java_lang_UnsupportedOperationException;
  private Marshaller<RESTRoleV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTRoleV1;
  private Marshaller<RESTPropertyCategoryInPropertyTagCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyCategoryInPropertyTagCollectionV1;
  private Marshaller<RESTPropertyTagInPropertyCategoryCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyTagInPropertyCategoryCollectionV1;
  private Marshaller<RESTPropertyCategoryInPropertyTagV1> org_jboss_pressgang_ccms_rest_v1_entities_join_RESTPropertyCategoryInPropertyTagV1;
  private Marshaller<AssertionError> java_lang_AssertionError;
  private Marshaller<ArrayStoreException> java_lang_ArrayStoreException;
  private Marshaller<RESTStringConstantCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTStringConstantCollectionV1;
  private Marshaller<RESTStringConstantV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTStringConstantV1;
  private Marshaller<RESTStringConstantCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTStringConstantCollectionItemV1;
  private Marshaller<RESTLanguageImageCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTLanguageImageCollectionV1;
  private Marshaller<RESTProjectV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTProjectV1;
  private Marshaller<RESTFilterTagCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterTagCollectionItemV1;
  private Marshaller<IOException> java_io_IOException;
  private Marshaller<RESTAssignedPropertyTagV1> org_jboss_pressgang_ccms_rest_v1_entities_join_RESTAssignedPropertyTagV1;
  private Marshaller<RESTPropertyTagCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTPropertyTagCollectionV1;
  private Marshaller<RESTPropertyTagV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTPropertyTagV1;
  private Marshaller<RESTPropertyTagCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTPropertyTagCollectionItemV1;
  private Marshaller<RESTBugzillaBugCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTBugzillaBugCollectionV1;
  private Marshaller<RESTBugzillaBugV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTBugzillaBugV1;
  private Marshaller<RESTBugzillaBugCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTBugzillaBugCollectionItemV1;
  private Marshaller<RESTTranslatedTopicCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1;
  private Marshaller<RESTTagInCategoryCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_join_RESTTagInCategoryCollectionV1;
  private Marshaller<RESTCategoryInTagV1> org_jboss_pressgang_ccms_rest_v1_entities_join_RESTCategoryInTagV1;
  private Marshaller<RESTCategoryInTagCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTCategoryInTagCollectionItemV1;
  private Marshaller<RESTIntegerConstantCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTIntegerConstantCollectionV1;
  private Marshaller<RESTTopicCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicCollectionV1;
  private Marshaller<StackTraceElement> java_lang_StackTraceElement;
  private Marshaller<RESTProjectCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTProjectCollectionItemV1;
  private Marshaller<StringIndexOutOfBoundsException> java_lang_StringIndexOutOfBoundsException;
  private Marshaller<EmptyStackException> java_util_EmptyStackException;
  private Marshaller<RESTAssignedPropertyTagCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTAssignedPropertyTagCollectionItemV1;
  private Marshaller<RuntimeException> java_lang_RuntimeException;
  private Marshaller<RESTFilterFieldV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterFieldV1;
  private Marshaller<RESTImageCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTImageCollectionV1;
  private Marshaller<NullPointerException> java_lang_NullPointerException;
  private Marshaller<NegativeArraySizeException> java_lang_NegativeArraySizeException;
  private Marshaller<TransportIOException> org_jboss_errai_bus_client_api_base_TransportIOException;
  private Marshaller<RESTPropertyCategoryInPropertyTagCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTPropertyCategoryInPropertyTagCollectionItemV1;
  private Marshaller<RESTTopicSourceUrlCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicSourceUrlCollectionV1;
  private Marshaller<RESTTopicV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTTopicV1;
  private static Field _$227131749_containsFuzzyTranslation_fld = _getAccessibleField(RESTTranslatedTopicV1.class, "containsFuzzyTranslation");
  private Marshaller<RESTTranslatedTopicStringCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicStringCollectionV1;
  private static Field _$227131749_translatedTopicStrings_fld = _getAccessibleField(RESTTranslatedTopicV1.class, "translatedTopicStrings");
  private Marshaller<RESTTranslatedTopicV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTTranslatedTopicV1;
  private Marshaller<RESTTranslatedTopicCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTranslatedTopicCollectionItemV1;
  private Marshaller<RESTFilterLocaleV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterLocaleV1;
  private Marshaller<RESTPropertyTagInPropertyCategoryV1> org_jboss_pressgang_ccms_rest_v1_entities_join_RESTPropertyTagInPropertyCategoryV1;
  private Marshaller<RESTTopicCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTopicCollectionItemV1;
  private Marshaller<RESTRoleCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTRoleCollectionItemV1;
  private Marshaller<RESTTopicSourceUrlV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTTopicSourceUrlV1;
  private Marshaller<RESTTopicSourceUrlCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTopicSourceUrlCollectionItemV1;
  private Marshaller<RESTFilterLocaleCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterLocaleCollectionItemV1;
  private Marshaller<RESTCategoryCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTCategoryCollectionV1;
  private Marshaller<RESTCategoryV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTCategoryV1;
  private Marshaller<RESTFilterCategoryV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterCategoryV1;
  private Marshaller<RESTFilterCategoryCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterCategoryCollectionItemV1;
  private Marshaller<MessageDeliveryFailure> org_jboss_errai_bus_client_api_base_MessageDeliveryFailure;
  private Marshaller<RESTTagInCategoryV1> org_jboss_pressgang_ccms_rest_v1_entities_join_RESTTagInCategoryV1;
  private Marshaller<RESTTagInCategoryCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTTagInCategoryCollectionItemV1;
  private Marshaller<RESTImageV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTImageV1;
  private QualifyingMarshallerWrapper<byte[]> arrayOf_byte_D1;
  private Marshaller<RESTLanguageImageV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTLanguageImageV1;
  private Marshaller<RESTLanguageImageCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTLanguageImageCollectionItemV1;
  private Marshaller<RESTImageCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTImageCollectionItemV1;
  private Marshaller<ClassCastException> java_lang_ClassCastException;
  private Marshaller<RESTCategoryCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTCategoryCollectionItemV1;
  private Marshaller<IndexOutOfBoundsException> java_lang_IndexOutOfBoundsException;
  private Marshaller<RESTIntegerConstantV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTIntegerConstantV1;
  private Marshaller<RESTIntegerConstantCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTIntegerConstantCollectionItemV1;
  private Marshaller<RESTPropertyTagInPropertyCategoryCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTPropertyTagInPropertyCategoryCollectionItemV1;
  private Marshaller<RESTBlobConstantCollectionV1> org_jboss_pressgang_ccms_rest_v1_collections_RESTBlobConstantCollectionV1;
  private Marshaller<RESTBlobConstantV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTBlobConstantV1;
  private Marshaller<BusReadyEvent> org_jboss_errai_enterprise_client_cdi_events_BusReadyEvent;
  private Marshaller<ConcurrentModificationException> java_util_ConcurrentModificationException;
  private Marshaller<RESTTranslatedTopicStringV1> org_jboss_pressgang_ccms_rest_v1_entities_RESTTranslatedTopicStringV1;
  private Marshaller<RESTFilterCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterCollectionItemV1;
  private Marshaller<RESTTagCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTagCollectionItemV1;
  private Marshaller<RESTFilterFieldCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterFieldCollectionItemV1;
  private Marshaller<RESTUserCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTUserCollectionItemV1;
  private Marshaller<RESTBlobConstantCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTBlobConstantCollectionItemV1;
  private Marshaller<RESTTranslatedTopicStringCollectionItemV1> org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTranslatedTopicStringCollectionItemV1;
  private Marshaller<ArithmeticException> java_lang_ArithmeticException;
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
    java_lang_Float = new FloatMarshaller();
    marshallers.put("java.lang.Float", java_lang_Float);
    java_util_LinkedList = new LinkedListMarshaller();
    marshallers.put("java.util.LinkedList", java_util_LinkedList);
    java_util_HashSet = new SetMarshaller();
    marshallers.put("java.util.HashSet", java_util_HashSet);
    java_util_Queue = new QueueMarshaller();
    marshallers.put("java.util.Queue", java_util_Queue);
    marshallers.put("java.util.AbstractQueue", java_util_Queue);
    java_lang_Long = new LongMarshaller();
    marshallers.put("java.lang.Long", java_lang_Long);
    java_lang_Short = new ShortMarshaller();
    marshallers.put("java.lang.Short", java_lang_Short);
    java_util_LinkedHashMap = new QualifyingMarshallerWrapper(new LinkedMapMarshaller());
    marshallers.put("java.util.LinkedHashMap", java_util_LinkedHashMap);
    java_util_Map = new QualifyingMarshallerWrapper(new MapMarshaller());
    marshallers.put("java.util.Map", java_util_Map);
    marshallers.put("java.util.Collections$SingletonMap", java_util_Map);
    marshallers.put("java.util.AbstractMap", java_util_Map);
    marshallers.put("java.util.Collections$SynchronizedMap", java_util_Map);
    marshallers.put("java.util.HashMap", java_util_Map);
    marshallers.put("java.util.Collections$UnmodifiableMap", java_util_Map);
    marshallers.put("java.util.Collections$EmptyMap", java_util_Map);
    java_lang_Integer = new IntegerMarshaller();
    marshallers.put("java.lang.Integer", java_lang_Integer);
    java_lang_Object = new ObjectMarshaller();
    marshallers.put("java.lang.Object", java_lang_Object);
    java_sql_Timestamp = new TimestampMarshaller();
    marshallers.put("java.sql.Timestamp", java_sql_Timestamp);
    java_util_SortedMap = new QualifyingMarshallerWrapper(new SortedMapMarshaller());
    marshallers.put("java.util.SortedMap", java_util_SortedMap);
    marshallers.put("java.util.Collections$SynchronizedSortedMap", java_util_SortedMap);
    marshallers.put("java.util.Collections$UnmodifiableSortedMap", java_util_SortedMap);
    marshallers.put("java.util.TreeMap", java_util_SortedMap);
    java_sql_Date = new SQLDateMarshaller();
    marshallers.put("java.sql.Date", java_sql_Date);
    java_util_AbstractSet = new SetMarshaller();
    marshallers.put("java.util.AbstractSet", java_util_AbstractSet);
    java_util_Stack = new ListMarshaller();
    marshallers.put("java.util.Stack", java_util_Stack);
    java_util_Date = new DateMarshaller();
    marshallers.put("java.util.Date", java_util_Date);
    java_sql_Time = new TimeMarshaller();
    marshallers.put("java.sql.Time", java_sql_Time);
    java_math_BigInteger = new BigIntegerMarshaller();
    marshallers.put("java.math.BigInteger", java_math_BigInteger);
    java_util_LinkedHashSet = new LinkedHashSetMarshaller();
    marshallers.put("java.util.LinkedHashSet", java_util_LinkedHashSet);
    java_lang_Character = new CharacterMarshaller();
    marshallers.put("java.lang.Character", java_lang_Character);
    java_util_Vector = new ListMarshaller();
    marshallers.put("java.util.Vector", java_util_Vector);
    java_util_PriorityQueue = new PriorityQueueMarshaller();
    marshallers.put("java.util.PriorityQueue", java_util_PriorityQueue);
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
    java_util_SortedSet = new SortedSetMarshaller();
    marshallers.put("java.util.SortedSet", java_util_SortedSet);
    marshallers.put("java.util.Collections$UnmodifiableSortedSet", java_util_SortedSet);
    marshallers.put("java.util.TreeSet", java_util_SortedSet);
    marshallers.put("java.util.Collections$SynchronizedSortedSet", java_util_SortedSet);
    java_util_AbstractMap = new QualifyingMarshallerWrapper(new MapMarshaller());
    marshallers.put("java.util.AbstractMap", java_util_AbstractMap);
    java_util_AbstractQueue = new QueueMarshaller();
    marshallers.put("java.util.AbstractQueue", java_util_AbstractQueue);
    java_lang_StringBuilder = new StringBuilderMarshaller();
    marshallers.put("java.lang.StringBuilder", java_lang_StringBuilder);
    java_lang_Double = new DoubleMarshaller();
    marshallers.put("java.lang.Double", java_lang_Double);
    java_lang_Boolean = new BooleanMarshaller();
    marshallers.put("java.lang.Boolean", java_lang_Boolean);
    java_util_TreeSet = new SortedSetMarshaller();
    marshallers.put("java.util.TreeSet", java_util_TreeSet);
    java_math_BigDecimal = new BigDecimalMarshaller();
    marshallers.put("java.math.BigDecimal", java_math_BigDecimal);
    java_lang_String = new StringMarshaller();
    marshallers.put("java.lang.String", java_lang_String);
    java_util_HashMap = new QualifyingMarshallerWrapper(new MapMarshaller());
    marshallers.put("java.util.HashMap", java_util_HashMap);
    java_lang_StringBuffer = new StringBufferMarshaller();
    marshallers.put("java.lang.StringBuffer", java_lang_StringBuffer);
    java_util_TreeMap = new QualifyingMarshallerWrapper(new SortedMapMarshaller());
    marshallers.put("java.util.TreeMap", java_util_TreeMap);
    java_util_AbstractList = new ListMarshaller();
    marshallers.put("java.util.AbstractList", java_util_AbstractList);
    java_util_Set = new SetMarshaller();
    marshallers.put("java.util.Set", java_util_Set);
    marshallers.put("java.util.Collections$SynchronizedSet", java_util_Set);
    marshallers.put("java.util.Collections$UnmodifiableSet", java_util_Set);
    marshallers.put("java.util.Collections$EmptySet", java_util_Set);
    marshallers.put("java.util.Collections$SingletonSet", java_util_Set);
    marshallers.put("java.util.AbstractSet", java_util_Set);
    marshallers.put("java.util.HashSet", java_util_Set);
    java_util_ArrayList = new ListMarshaller();
    marshallers.put("java.util.ArrayList", java_util_ArrayList);
    java_lang_Byte = new ByteMarshaller();
    marshallers.put("java.lang.Byte", java_lang_Byte);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1 = new Marshaller<RESTTagCollectionV1>() {
      private RESTTagCollectionV1[] EMPTY_ARRAY = new RESTTagCollectionV1[0];
      public RESTTagCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1", t);
        }
      }
      public String marshall(RESTTagCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_collections_join_RESTCategoryInTagCollectionV1 = new Marshaller<RESTCategoryInTagCollectionV1>() {
      private RESTCategoryInTagCollectionV1[] EMPTY_ARRAY = new RESTCategoryInTagCollectionV1[0];
      public RESTCategoryInTagCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTCategoryInTagCollectionV1.class;
      }
      public RESTCategoryInTagCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTCategoryInTagCollectionV1.class, objId);
          }
          RESTCategoryInTagCollectionV1 entity = new RESTCategoryInTagCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.join.RESTCategoryInTagCollectionV1", t);
        }
      }
      public String marshall(RESTCategoryInTagCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.join.RESTCategoryInTagCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.join.RESTCategoryInTagCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.join.RESTCategoryInTagCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_join_RESTCategoryInTagCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTProjectCollectionV1 = new Marshaller<RESTProjectCollectionV1>() {
      private RESTProjectCollectionV1[] EMPTY_ARRAY = new RESTProjectCollectionV1[0];
      public RESTProjectCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1", t);
        }
      }
      public String marshall(RESTProjectCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTProjectCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1 = new Marshaller<RESTAssignedPropertyTagCollectionV1>() {
      private RESTAssignedPropertyTagCollectionV1[] EMPTY_ARRAY = new RESTAssignedPropertyTagCollectionV1[0];
      public RESTAssignedPropertyTagCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTAssignedPropertyTagCollectionV1.class;
      }
      public RESTAssignedPropertyTagCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTAssignedPropertyTagCollectionV1.class, objId);
          }
          RESTAssignedPropertyTagCollectionV1 entity = new RESTAssignedPropertyTagCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1", t);
        }
      }
      public String marshall(RESTAssignedPropertyTagCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTRoleCollectionV1 = new Marshaller<RESTRoleCollectionV1>() {
      private RESTRoleCollectionV1[] EMPTY_ARRAY = new RESTRoleCollectionV1[0];
      public RESTRoleCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTRoleCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTRoleCollectionV1", t);
        }
      }
      public String marshall(RESTRoleCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTRoleCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTRoleCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTRoleCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTRoleCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTUserCollectionV1 = new Marshaller<RESTUserCollectionV1>() {
      private RESTUserCollectionV1[] EMPTY_ARRAY = new RESTUserCollectionV1[0];
      public RESTUserCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTUserCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTUserCollectionV1", t);
        }
      }
      public String marshall(RESTUserCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTUserCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTUserCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTUserCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTUserCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTUserV1 = new Marshaller<RESTUserV1>() {
      private RESTUserV1[] EMPTY_ARRAY = new RESTUserV1[0];
      public RESTUserV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTUserV1.class;
      }
      public RESTUserV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTUserV1.class, objId);
          }
          RESTUserV1 entity = new RESTUserV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("roles")) && (!obj.get("roles").isNull())) {
            entity.setRoles(org_jboss_pressgang_ccms_rest_v1_collections_RESTRoleCollectionV1.demarshall(obj.get("roles"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTUserCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTUserV1", t);
        }
      }
      public String marshall(RESTUserV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTUserV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3712).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTUserV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"roles\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTRoleCollectionV1.marshall(a0.getRoles(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTUserCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTUserV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTUserV1);
    org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1 = new Marshaller<RESTLogDetailsV1>() {
      private RESTLogDetailsV1[] EMPTY_ARRAY = new RESTLogDetailsV1[0];
      public RESTLogDetailsV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTLogDetailsV1.class;
      }
      public RESTLogDetailsV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTLogDetailsV1.class, objId);
          }
          RESTLogDetailsV1 entity = new RESTLogDetailsV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("message")) && (!obj.get("message").isNull())) {
            entity.setMessage(java_lang_String.demarshall(obj.get("message"), a1));
          }
          if ((obj.containsKey("flag")) && (!obj.get("flag").isNull())) {
            entity.setFlag(java_lang_Integer.demarshall(obj.get("flag"), a1));
          }
          if ((obj.containsKey("user")) && (!obj.get("user").isNull())) {
            entity.setUser(org_jboss_pressgang_ccms_rest_v1_entities_RESTUserV1.demarshall(obj.get("user"), a1));
          }
          if ((obj.containsKey("date")) && (!obj.get("date").isNull())) {
            entity.setDate((Date) java_lang_Object.demarshall(Date.class, obj.get("date"), a1));
          }
          if ((obj.containsKey("configuredParameters")) && (!obj.get("configuredParameters").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setConfiguredParameters(java_util_List.demarshall(obj.get("configuredParameters"), a1));
            a1.setAssumedElementType(null);
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.base.RESTLogDetailsV1", t);
        }
      }
      public String marshall(RESTLogDetailsV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.base.RESTLogDetailsV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3712).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.base.RESTLogDetailsV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"flag\" : ").append(java_lang_Integer.marshall(a0.getFlag(), a1)).append(",").append("\"user\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTUserV1.marshall(a0.getUser(), a1)).append(",").append("\"date\" : ").append(java_lang_Object.marshall(a0.getDate(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.base.RESTLogDetailsV1", org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTTagV1 = new Marshaller<RESTTagV1>() {
      private RESTTagV1[] EMPTY_ARRAY = new RESTTagV1[0];
      public RESTTagV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("categories")) && (!obj.get("categories").isNull())) {
            entity.setCategories(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTCategoryInTagCollectionV1.demarshall(obj.get("categories"), a1));
          }
          if ((obj.containsKey("parentTags")) && (!obj.get("parentTags").isNull())) {
            entity.setParentTags(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.demarshall(obj.get("parentTags"), a1));
          }
          if ((obj.containsKey("childTags")) && (!obj.get("childTags").isNull())) {
            entity.setChildTags(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.demarshall(obj.get("childTags"), a1));
          }
          if ((obj.containsKey("projects")) && (!obj.get("projects").isNull())) {
            entity.setProjects(org_jboss_pressgang_ccms_rest_v1_collections_RESTProjectCollectionV1.demarshall(obj.get("projects"), a1));
          }
          if ((obj.containsKey("properties")) && (!obj.get("properties").isNull())) {
            entity.setProperties(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1.demarshall(obj.get("properties"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1", t);
        }
      }
      public String marshall(RESTTagV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(8448).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"categories\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTCategoryInTagCollectionV1.marshall(a0.getCategories(), a1)).append(",").append("\"parentTags\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.marshall(a0.getParentTags(), a1)).append(",").append("\"childTags\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.marshall(a0.getChildTags(), a1)).append(",").append("\"projects\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTProjectCollectionV1.marshall(a0.getProjects(), a1)).append(",").append("\"properties\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1.marshall(a0.getProperties(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTTagV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterTagCollectionV1 = new Marshaller<RESTFilterTagCollectionV1>() {
      private RESTFilterTagCollectionV1[] EMPTY_ARRAY = new RESTFilterTagCollectionV1[0];
      public RESTFilterTagCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterTagCollectionV1.class;
      }
      public RESTFilterTagCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterTagCollectionV1.class, objId);
          }
          RESTFilterTagCollectionV1 entity = new RESTFilterTagCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterTagCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterTagCollectionV1", t);
        }
      }
      public String marshall(RESTFilterTagCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterTagCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterTagCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterTagCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterTagCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterLocaleCollectionV1 = new Marshaller<RESTFilterLocaleCollectionV1>() {
      private RESTFilterLocaleCollectionV1[] EMPTY_ARRAY = new RESTFilterLocaleCollectionV1[0];
      public RESTFilterLocaleCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterLocaleCollectionV1.class;
      }
      public RESTFilterLocaleCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterLocaleCollectionV1.class, objId);
          }
          RESTFilterLocaleCollectionV1 entity = new RESTFilterLocaleCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterLocaleCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterLocaleCollectionV1", t);
        }
      }
      public String marshall(RESTFilterLocaleCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterLocaleCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterLocaleCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterLocaleCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterLocaleCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterCategoryCollectionV1 = new Marshaller<RESTFilterCategoryCollectionV1>() {
      private RESTFilterCategoryCollectionV1[] EMPTY_ARRAY = new RESTFilterCategoryCollectionV1[0];
      public RESTFilterCategoryCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterCategoryCollectionV1.class;
      }
      public RESTFilterCategoryCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterCategoryCollectionV1.class, objId);
          }
          RESTFilterCategoryCollectionV1 entity = new RESTFilterCategoryCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCategoryCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCategoryCollectionV1", t);
        }
      }
      public String marshall(RESTFilterCategoryCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCategoryCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCategoryCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCategoryCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterCategoryCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterFieldCollectionV1 = new Marshaller<RESTFilterFieldCollectionV1>() {
      private RESTFilterFieldCollectionV1[] EMPTY_ARRAY = new RESTFilterFieldCollectionV1[0];
      public RESTFilterFieldCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterFieldCollectionV1.class;
      }
      public RESTFilterFieldCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterFieldCollectionV1.class, objId);
          }
          RESTFilterFieldCollectionV1 entity = new RESTFilterFieldCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterFieldCollectionV1", t);
        }
      }
      public String marshall(RESTFilterFieldCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterFieldCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterFieldCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterFieldCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterFieldCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterCollectionV1 = new Marshaller<RESTFilterCollectionV1>() {
      private RESTFilterCollectionV1[] EMPTY_ARRAY = new RESTFilterCollectionV1[0];
      public RESTFilterCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterCollectionV1.class;
      }
      public RESTFilterCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterCollectionV1.class, objId);
          }
          RESTFilterCollectionV1 entity = new RESTFilterCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1", t);
        }
      }
      public String marshall(RESTFilterCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1 = new Marshaller<RESTFilterV1>() {
      private RESTFilterV1[] EMPTY_ARRAY = new RESTFilterV1[0];
      public RESTFilterV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterV1.class;
      }
      public RESTFilterV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterV1.class, objId);
          }
          RESTFilterV1 entity = new RESTFilterV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("filterTags_OTM")) && (!obj.get("filterTags_OTM").isNull())) {
            entity.setFilterTags_OTM(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterTagCollectionV1.demarshall(obj.get("filterTags_OTM"), a1));
          }
          if ((obj.containsKey("filterLocales_OTM")) && (!obj.get("filterLocales_OTM").isNull())) {
            entity.setFilterLocales_OTM(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterLocaleCollectionV1.demarshall(obj.get("filterLocales_OTM"), a1));
          }
          if ((obj.containsKey("filterCategories_OTM")) && (!obj.get("filterCategories_OTM").isNull())) {
            entity.setFilterCategories_OTM(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterCategoryCollectionV1.demarshall(obj.get("filterCategories_OTM"), a1));
          }
          if ((obj.containsKey("filterFields_OTM")) && (!obj.get("filterFields_OTM").isNull())) {
            entity.setFilterFields_OTM(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterFieldCollectionV1.demarshall(obj.get("filterFields_OTM"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1", t);
        }
      }
      public String marshall(RESTFilterV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(8960).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"filterTags_OTM\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterTagCollectionV1.marshall(a0.getFilterTags_OTM(), a1)).append(",").append("\"filterLocales_OTM\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterLocaleCollectionV1.marshall(a0.getFilterLocales_OTM(), a1)).append(",").append("\"filterCategories_OTM\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterCategoryCollectionV1.marshall(a0.getFilterCategories_OTM(), a1)).append(",").append("\"filterFields_OTM\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterFieldCollectionV1.marshall(a0.getFilterFields_OTM(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterTagV1 = new Marshaller<RESTFilterTagV1>() {
      private RESTFilterTagV1[] EMPTY_ARRAY = new RESTFilterTagV1[0];
      public RESTFilterTagV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterTagV1.class;
      }
      public RESTFilterTagV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterTagV1.class, objId);
          }
          RESTFilterTagV1 entity = new RESTFilterTagV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("tag")) && (!obj.get("tag").isNull())) {
            entity.setTag(org_jboss_pressgang_ccms_rest_v1_entities_RESTTagV1.demarshall(obj.get("tag"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          if ((obj.containsKey("filter")) && (!obj.get("filter").isNull())) {
            entity.setFilter(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1.demarshall(obj.get("filter"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterTagCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterTagV1", t);
        }
      }
      public String marshall(RESTFilterTagV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterTagV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(14848).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterTagV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"tag\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTTagV1.marshall(a0.getTag(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append(",").append("\"filter\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1.marshall(a0.getFilter(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterTagCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterTagV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterTagV1);
    arrayOf_java_lang_StackTraceElement_D1 = new QualifyingMarshallerWrapper(new Marshaller<StackTraceElement[]>() {
      public java.lang.StackTraceElement[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      private Throwable[] EMPTY_ARRAY = new Throwable[0];
      public Throwable[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.Throwable\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.Throwable", java_lang_Throwable);
    java_lang_IllegalArgumentException = new Marshaller<IllegalArgumentException>() {
      private IllegalArgumentException[] EMPTY_ARRAY = new IllegalArgumentException[0];
      public IllegalArgumentException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.IllegalArgumentException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.IllegalArgumentException", java_lang_IllegalArgumentException);
    java_lang_UnsupportedOperationException = new Marshaller<UnsupportedOperationException>() {
      private UnsupportedOperationException[] EMPTY_ARRAY = new UnsupportedOperationException[0];
      public UnsupportedOperationException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.UnsupportedOperationException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.UnsupportedOperationException", java_lang_UnsupportedOperationException);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTRoleV1 = new Marshaller<RESTRoleV1>() {
      private RESTRoleV1[] EMPTY_ARRAY = new RESTRoleV1[0];
      public RESTRoleV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTRoleV1.class;
      }
      public RESTRoleV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTRoleV1.class, objId);
          }
          RESTRoleV1 entity = new RESTRoleV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("users")) && (!obj.get("users").isNull())) {
            entity.setUsers(org_jboss_pressgang_ccms_rest_v1_collections_RESTUserCollectionV1.demarshall(obj.get("users"), a1));
          }
          if ((obj.containsKey("childRoles")) && (!obj.get("childRoles").isNull())) {
            entity.setChildRoles(org_jboss_pressgang_ccms_rest_v1_collections_RESTRoleCollectionV1.demarshall(obj.get("childRoles"), a1));
          }
          if ((obj.containsKey("parentRoles")) && (!obj.get("parentRoles").isNull())) {
            entity.setParentRoles(org_jboss_pressgang_ccms_rest_v1_collections_RESTRoleCollectionV1.demarshall(obj.get("parentRoles"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTRoleCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTRoleV1", t);
        }
      }
      public String marshall(RESTRoleV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTRoleV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(5632).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTRoleV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"users\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTUserCollectionV1.marshall(a0.getUsers(), a1)).append(",").append("\"childRoles\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTRoleCollectionV1.marshall(a0.getChildRoles(), a1)).append(",").append("\"parentRoles\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTRoleCollectionV1.marshall(a0.getParentRoles(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTRoleCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTRoleV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTRoleV1);
    org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyCategoryInPropertyTagCollectionV1 = new Marshaller<RESTPropertyCategoryInPropertyTagCollectionV1>() {
      private RESTPropertyCategoryInPropertyTagCollectionV1[] EMPTY_ARRAY = new RESTPropertyCategoryInPropertyTagCollectionV1[0];
      public RESTPropertyCategoryInPropertyTagCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTPropertyCategoryInPropertyTagCollectionV1.class;
      }
      public RESTPropertyCategoryInPropertyTagCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTPropertyCategoryInPropertyTagCollectionV1.class, objId);
          }
          RESTPropertyCategoryInPropertyTagCollectionV1 entity = new RESTPropertyCategoryInPropertyTagCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyCategoryInPropertyTagCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyCategoryInPropertyTagCollectionV1", t);
        }
      }
      public String marshall(RESTPropertyCategoryInPropertyTagCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyCategoryInPropertyTagCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyCategoryInPropertyTagCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyCategoryInPropertyTagCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyCategoryInPropertyTagCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyTagInPropertyCategoryCollectionV1 = new Marshaller<RESTPropertyTagInPropertyCategoryCollectionV1>() {
      private RESTPropertyTagInPropertyCategoryCollectionV1[] EMPTY_ARRAY = new RESTPropertyTagInPropertyCategoryCollectionV1[0];
      public RESTPropertyTagInPropertyCategoryCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTPropertyTagInPropertyCategoryCollectionV1.class;
      }
      public RESTPropertyTagInPropertyCategoryCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTPropertyTagInPropertyCategoryCollectionV1.class, objId);
          }
          RESTPropertyTagInPropertyCategoryCollectionV1 entity = new RESTPropertyTagInPropertyCategoryCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyTagInPropertyCategoryCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyTagInPropertyCategoryCollectionV1", t);
        }
      }
      public String marshall(RESTPropertyTagInPropertyCategoryCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyTagInPropertyCategoryCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyTagInPropertyCategoryCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyTagInPropertyCategoryCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyTagInPropertyCategoryCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_entities_join_RESTPropertyCategoryInPropertyTagV1 = new Marshaller<RESTPropertyCategoryInPropertyTagV1>() {
      private RESTPropertyCategoryInPropertyTagV1[] EMPTY_ARRAY = new RESTPropertyCategoryInPropertyTagV1[0];
      public RESTPropertyCategoryInPropertyTagV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTPropertyCategoryInPropertyTagV1.class;
      }
      public RESTPropertyCategoryInPropertyTagV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTPropertyCategoryInPropertyTagV1.class, objId);
          }
          RESTPropertyCategoryInPropertyTagV1 entity = new RESTPropertyCategoryInPropertyTagV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("relationshipId")) && (!obj.get("relationshipId").isNull())) {
            entity.setRelationshipId(java_lang_Integer.demarshall(obj.get("relationshipId"), a1));
          }
          if ((obj.containsKey("relationshipSort")) && (!obj.get("relationshipSort").isNull())) {
            entity.setRelationshipSort(java_lang_Integer.demarshall(obj.get("relationshipSort"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyCategoryInPropertyTagCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("propertyTags")) && (!obj.get("propertyTags").isNull())) {
            entity.setPropertyTags(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyTagInPropertyCategoryCollectionV1.demarshall(obj.get("propertyTags"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyCategoryInPropertyTagV1", t);
        }
      }
      public String marshall(RESTPropertyCategoryInPropertyTagV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyCategoryInPropertyTagV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(6912).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyCategoryInPropertyTagV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"relationshipId\" : ").append(java_lang_Integer.marshall(a0.getRelationshipId(), a1)).append(",").append("\"relationshipSort\" : ").append(java_lang_Integer.marshall(a0.getRelationshipSort(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyCategoryInPropertyTagCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"propertyTags\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyTagInPropertyCategoryCollectionV1.marshall(a0.getPropertyTags(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyCategoryInPropertyTagV1", org_jboss_pressgang_ccms_rest_v1_entities_join_RESTPropertyCategoryInPropertyTagV1);
    java_lang_AssertionError = new Marshaller<AssertionError>() {
      private AssertionError[] EMPTY_ARRAY = new AssertionError[0];
      public AssertionError[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.AssertionError\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.AssertionError", java_lang_AssertionError);
    java_lang_ArrayStoreException = new Marshaller<ArrayStoreException>() {
      private ArrayStoreException[] EMPTY_ARRAY = new ArrayStoreException[0];
      public ArrayStoreException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.ArrayStoreException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.ArrayStoreException", java_lang_ArrayStoreException);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTStringConstantCollectionV1 = new Marshaller<RESTStringConstantCollectionV1>() {
      private RESTStringConstantCollectionV1[] EMPTY_ARRAY = new RESTStringConstantCollectionV1[0];
      public RESTStringConstantCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTStringConstantCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTStringConstantCollectionV1", t);
        }
      }
      public String marshall(RESTStringConstantCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTStringConstantCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTStringConstantCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTStringConstantCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTStringConstantCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTStringConstantV1 = new Marshaller<RESTStringConstantV1>() {
      private RESTStringConstantV1[] EMPTY_ARRAY = new RESTStringConstantV1[0];
      public RESTStringConstantV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTStringConstantV1.class;
      }
      public RESTStringConstantV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTStringConstantV1.class, objId);
          }
          RESTStringConstantV1 entity = new RESTStringConstantV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("value")) && (!obj.get("value").isNull())) {
            entity.setValue(java_lang_String.demarshall(obj.get("value"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTStringConstantCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1", t);
        }
      }
      public String marshall(RESTStringConstantV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(5888).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"value\" : ").append(java_lang_String.marshall(a0.getValue(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTStringConstantCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTStringConstantV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTStringConstantCollectionItemV1 = new Marshaller<RESTStringConstantCollectionItemV1>() {
      private RESTStringConstantCollectionItemV1[] EMPTY_ARRAY = new RESTStringConstantCollectionItemV1[0];
      public RESTStringConstantCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTStringConstantCollectionItemV1.class;
      }
      public RESTStringConstantCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTStringConstantCollectionItemV1.class, objId);
          }
          RESTStringConstantCollectionItemV1 entity = new RESTStringConstantCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTStringConstantV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTStringConstantCollectionItemV1", t);
        }
      }
      public String marshall(RESTStringConstantCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTStringConstantCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(6144).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTStringConstantCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTStringConstantV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTStringConstantCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTStringConstantCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTLanguageImageCollectionV1 = new Marshaller<RESTLanguageImageCollectionV1>() {
      private RESTLanguageImageCollectionV1[] EMPTY_ARRAY = new RESTLanguageImageCollectionV1[0];
      public RESTLanguageImageCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1", t);
        }
      }
      public String marshall(RESTLanguageImageCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTLanguageImageCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTProjectV1 = new Marshaller<RESTProjectV1>() {
      private RESTProjectV1[] EMPTY_ARRAY = new RESTProjectV1[0];
      public RESTProjectV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.setTags(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.demarshall(obj.get("tags"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTProjectCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1", t);
        }
      }
      public String marshall(RESTProjectV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(6656).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"tags\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.marshall(a0.getTags(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTProjectCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTProjectV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterTagCollectionItemV1 = new Marshaller<RESTFilterTagCollectionItemV1>() {
      private RESTFilterTagCollectionItemV1[] EMPTY_ARRAY = new RESTFilterTagCollectionItemV1[0];
      public RESTFilterTagCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterTagCollectionItemV1.class;
      }
      public RESTFilterTagCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterTagCollectionItemV1.class, objId);
          }
          RESTFilterTagCollectionItemV1 entity = new RESTFilterTagCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterTagV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterTagCollectionItemV1", t);
        }
      }
      public String marshall(RESTFilterTagCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterTagCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(15104).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterTagCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterTagV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterTagCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterTagCollectionItemV1);
    java_io_IOException = new Marshaller<IOException>() {
      private IOException[] EMPTY_ARRAY = new IOException[0];
      public IOException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.io.IOException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.io.IOException", java_io_IOException);
    org_jboss_pressgang_ccms_rest_v1_entities_join_RESTAssignedPropertyTagV1 = new Marshaller<RESTAssignedPropertyTagV1>() {
      private RESTAssignedPropertyTagV1[] EMPTY_ARRAY = new RESTAssignedPropertyTagV1[0];
      public RESTAssignedPropertyTagV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTAssignedPropertyTagV1.class;
      }
      public RESTAssignedPropertyTagV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTAssignedPropertyTagV1.class, objId);
          }
          RESTAssignedPropertyTagV1 entity = new RESTAssignedPropertyTagV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("relationshipId")) && (!obj.get("relationshipId").isNull())) {
            entity.setRelationshipId(java_lang_Integer.demarshall(obj.get("relationshipId"), a1));
          }
          if ((obj.containsKey("value")) && (!obj.get("value").isNull())) {
            entity.setValue(java_lang_String.demarshall(obj.get("value"), a1));
          }
          if ((obj.containsKey("valid")) && (!obj.get("valid").isNull())) {
            entity.setValid((boolean) java_lang_Boolean.demarshall(obj.get("valid"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
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
          if ((obj.containsKey("propertyCategories")) && (!obj.get("propertyCategories").isNull())) {
            entity.setPropertyCategories(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyCategoryInPropertyTagCollectionV1.demarshall(obj.get("propertyCategories"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1", t);
        }
      }
      public String marshall(RESTAssignedPropertyTagV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(7424).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"relationshipId\" : ").append(java_lang_Integer.marshall(a0.getRelationshipId(), a1)).append(",").append("\"value\" : ").append(java_lang_String.marshall(a0.getValue(), a1)).append(",").append("\"valid\" : ").append(java_lang_Boolean.marshall(a0.getValid(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"regex\" : ").append(java_lang_String.marshall(a0.getRegex(), a1)).append(",").append("\"canBeNull\" : ").append(java_lang_Boolean.marshall(a0.getCanBeNull(), a1)).append(",").append("\"isUnique\" : ").append(java_lang_Boolean.marshall(a0.getIsUnique(), a1)).append(",").append("\"propertyCategories\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyCategoryInPropertyTagCollectionV1.marshall(a0.getPropertyCategories(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1", org_jboss_pressgang_ccms_rest_v1_entities_join_RESTAssignedPropertyTagV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTPropertyTagCollectionV1 = new Marshaller<RESTPropertyTagCollectionV1>() {
      private RESTPropertyTagCollectionV1[] EMPTY_ARRAY = new RESTPropertyTagCollectionV1[0];
      public RESTPropertyTagCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyTagCollectionV1", t);
        }
      }
      public String marshall(RESTPropertyTagCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyTagCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyTagCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyTagCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTPropertyTagCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTPropertyTagV1 = new Marshaller<RESTPropertyTagV1>() {
      private RESTPropertyTagV1[] EMPTY_ARRAY = new RESTPropertyTagV1[0];
      public RESTPropertyTagV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTPropertyTagCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
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
          if ((obj.containsKey("propertyCategories")) && (!obj.get("propertyCategories").isNull())) {
            entity.setPropertyCategories(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyCategoryInPropertyTagCollectionV1.demarshall(obj.get("propertyCategories"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1", t);
        }
      }
      public String marshall(RESTPropertyTagV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(7040).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTPropertyTagCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"regex\" : ").append(java_lang_String.marshall(a0.getRegex(), a1)).append(",").append("\"canBeNull\" : ").append(java_lang_Boolean.marshall(a0.getCanBeNull(), a1)).append(",").append("\"isUnique\" : ").append(java_lang_Boolean.marshall(a0.getIsUnique(), a1)).append(",").append("\"propertyCategories\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyCategoryInPropertyTagCollectionV1.marshall(a0.getPropertyCategories(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTPropertyTagV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTPropertyTagCollectionItemV1 = new Marshaller<RESTPropertyTagCollectionItemV1>() {
      private RESTPropertyTagCollectionItemV1[] EMPTY_ARRAY = new RESTPropertyTagCollectionItemV1[0];
      public RESTPropertyTagCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTPropertyTagCollectionItemV1.class;
      }
      public RESTPropertyTagCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTPropertyTagCollectionItemV1.class, objId);
          }
          RESTPropertyTagCollectionItemV1 entity = new RESTPropertyTagCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTPropertyTagV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1", t);
        }
      }
      public String marshall(RESTPropertyTagCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(7296).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTPropertyTagV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTPropertyTagCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTBugzillaBugCollectionV1 = new Marshaller<RESTBugzillaBugCollectionV1>() {
      private RESTBugzillaBugCollectionV1[] EMPTY_ARRAY = new RESTBugzillaBugCollectionV1[0];
      public RESTBugzillaBugCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTBugzillaBugCollectionV1", t);
        }
      }
      public String marshall(RESTBugzillaBugCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTBugzillaBugCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTBugzillaBugCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTBugzillaBugCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTBugzillaBugCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTBugzillaBugV1 = new Marshaller<RESTBugzillaBugV1>() {
      private RESTBugzillaBugV1[] EMPTY_ARRAY = new RESTBugzillaBugV1[0];
      public RESTBugzillaBugV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTBugzillaBugCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTBugzillaBugV1", t);
        }
      }
      public String marshall(RESTBugzillaBugV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTBugzillaBugV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(5504).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTBugzillaBugV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"bugId\" : ").append(java_lang_Integer.marshall(a0.getBugId(), a1)).append(",").append("\"isOpen\" : ").append(java_lang_Boolean.marshall(a0.getIsOpen(), a1)).append(",").append("\"summary\" : ").append(java_lang_String.marshall(a0.getSummary(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTBugzillaBugCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTBugzillaBugV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTBugzillaBugV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTBugzillaBugCollectionItemV1 = new Marshaller<RESTBugzillaBugCollectionItemV1>() {
      private RESTBugzillaBugCollectionItemV1[] EMPTY_ARRAY = new RESTBugzillaBugCollectionItemV1[0];
      public RESTBugzillaBugCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTBugzillaBugCollectionItemV1.class;
      }
      public RESTBugzillaBugCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTBugzillaBugCollectionItemV1.class, objId);
          }
          RESTBugzillaBugCollectionItemV1 entity = new RESTBugzillaBugCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTBugzillaBugV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1", t);
        }
      }
      public String marshall(RESTBugzillaBugCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(5760).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTBugzillaBugV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTBugzillaBugCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1 = new Marshaller<RESTTranslatedTopicCollectionV1>() {
      private RESTTranslatedTopicCollectionV1[] EMPTY_ARRAY = new RESTTranslatedTopicCollectionV1[0];
      public RESTTranslatedTopicCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicCollectionV1", t);
        }
      }
      public String marshall(RESTTranslatedTopicCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_collections_join_RESTTagInCategoryCollectionV1 = new Marshaller<RESTTagInCategoryCollectionV1>() {
      private RESTTagInCategoryCollectionV1[] EMPTY_ARRAY = new RESTTagInCategoryCollectionV1[0];
      public RESTTagInCategoryCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTTagInCategoryCollectionV1.class;
      }
      public RESTTagInCategoryCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTagInCategoryCollectionV1.class, objId);
          }
          RESTTagInCategoryCollectionV1 entity = new RESTTagInCategoryCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1", t);
        }
      }
      public String marshall(RESTTagInCategoryCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_join_RESTTagInCategoryCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_entities_join_RESTCategoryInTagV1 = new Marshaller<RESTCategoryInTagV1>() {
      private RESTCategoryInTagV1[] EMPTY_ARRAY = new RESTCategoryInTagV1[0];
      public RESTCategoryInTagV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTCategoryInTagV1.class;
      }
      public RESTCategoryInTagV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTCategoryInTagV1.class, objId);
          }
          RESTCategoryInTagV1 entity = new RESTCategoryInTagV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("relationshipId")) && (!obj.get("relationshipId").isNull())) {
            entity.setRelationshipId(java_lang_Integer.demarshall(obj.get("relationshipId"), a1));
          }
          if ((obj.containsKey("relationshipSort")) && (!obj.get("relationshipSort").isNull())) {
            entity.setRelationshipSort(java_lang_Integer.demarshall(obj.get("relationshipSort"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTCategoryInTagCollectionV1.demarshall(obj.get("revisions"), a1));
          }
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
            entity.setTags(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTTagInCategoryCollectionV1.demarshall(obj.get("tags"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.join.RESTCategoryInTagV1", t);
        }
      }
      public String marshall(RESTCategoryInTagV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.join.RESTCategoryInTagV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(7168).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.join.RESTCategoryInTagV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"relationshipId\" : ").append(java_lang_Integer.marshall(a0.getRelationshipId(), a1)).append(",").append("\"relationshipSort\" : ").append(java_lang_Integer.marshall(a0.getRelationshipSort(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTCategoryInTagCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"mutuallyExclusive\" : ").append(java_lang_Boolean.marshall(a0.getMutuallyExclusive(), a1)).append(",").append("\"sort\" : ").append(java_lang_Integer.marshall(a0.getSort(), a1)).append(",").append("\"tags\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTTagInCategoryCollectionV1.marshall(a0.getTags(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.join.RESTCategoryInTagV1", org_jboss_pressgang_ccms_rest_v1_entities_join_RESTCategoryInTagV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTCategoryInTagCollectionItemV1 = new Marshaller<RESTCategoryInTagCollectionItemV1>() {
      private RESTCategoryInTagCollectionItemV1[] EMPTY_ARRAY = new RESTCategoryInTagCollectionItemV1[0];
      public RESTCategoryInTagCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTCategoryInTagCollectionItemV1.class;
      }
      public RESTCategoryInTagCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTCategoryInTagCollectionItemV1.class, objId);
          }
          RESTCategoryInTagCollectionItemV1 entity = new RESTCategoryInTagCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_join_RESTCategoryInTagV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1", t);
        }
      }
      public String marshall(RESTCategoryInTagCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(7424).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_join_RESTCategoryInTagV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTCategoryInTagCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTIntegerConstantCollectionV1 = new Marshaller<RESTIntegerConstantCollectionV1>() {
      private RESTIntegerConstantCollectionV1[] EMPTY_ARRAY = new RESTIntegerConstantCollectionV1[0];
      public RESTIntegerConstantCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTIntegerConstantCollectionV1.class;
      }
      public RESTIntegerConstantCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTIntegerConstantCollectionV1.class, objId);
          }
          RESTIntegerConstantCollectionV1 entity = new RESTIntegerConstantCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTIntegerConstantCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTIntegerConstantCollectionV1", t);
        }
      }
      public String marshall(RESTIntegerConstantCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTIntegerConstantCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTIntegerConstantCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTIntegerConstantCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTIntegerConstantCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicCollectionV1 = new Marshaller<RESTTopicCollectionV1>() {
      private RESTTopicCollectionV1[] EMPTY_ARRAY = new RESTTopicCollectionV1[0];
      public RESTTopicCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1", t);
        }
      }
      public String marshall(RESTTopicCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicCollectionV1);
    java_lang_StackTraceElement = new Marshaller<StackTraceElement>() {
      private StackTraceElement[] EMPTY_ARRAY = new StackTraceElement[0];
      public StackTraceElement[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTProjectCollectionItemV1 = new Marshaller<RESTProjectCollectionItemV1>() {
      private RESTProjectCollectionItemV1[] EMPTY_ARRAY = new RESTProjectCollectionItemV1[0];
      public RESTProjectCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTProjectCollectionItemV1.class;
      }
      public RESTProjectCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTProjectCollectionItemV1.class, objId);
          }
          RESTProjectCollectionItemV1 entity = new RESTProjectCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTProjectV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1", t);
        }
      }
      public String marshall(RESTProjectCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(6912).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTProjectV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTProjectCollectionItemV1);
    java_lang_StringIndexOutOfBoundsException = new Marshaller<StringIndexOutOfBoundsException>() {
      private StringIndexOutOfBoundsException[] EMPTY_ARRAY = new StringIndexOutOfBoundsException[0];
      public StringIndexOutOfBoundsException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.StringIndexOutOfBoundsException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.StringIndexOutOfBoundsException", java_lang_StringIndexOutOfBoundsException);
    java_util_EmptyStackException = new Marshaller<EmptyStackException>() {
      private EmptyStackException[] EMPTY_ARRAY = new EmptyStackException[0];
      public EmptyStackException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.util.EmptyStackException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.util.EmptyStackException", java_util_EmptyStackException);
    org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTAssignedPropertyTagCollectionItemV1 = new Marshaller<RESTAssignedPropertyTagCollectionItemV1>() {
      private RESTAssignedPropertyTagCollectionItemV1[] EMPTY_ARRAY = new RESTAssignedPropertyTagCollectionItemV1[0];
      public RESTAssignedPropertyTagCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTAssignedPropertyTagCollectionItemV1.class;
      }
      public RESTAssignedPropertyTagCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTAssignedPropertyTagCollectionItemV1.class, objId);
          }
          RESTAssignedPropertyTagCollectionItemV1 entity = new RESTAssignedPropertyTagCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_join_RESTAssignedPropertyTagV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1", t);
        }
      }
      public String marshall(RESTAssignedPropertyTagCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(7680).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_join_RESTAssignedPropertyTagV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTAssignedPropertyTagCollectionItemV1);
    java_lang_RuntimeException = new Marshaller<RuntimeException>() {
      private RuntimeException[] EMPTY_ARRAY = new RuntimeException[0];
      public RuntimeException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.RuntimeException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.RuntimeException", java_lang_RuntimeException);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterFieldV1 = new Marshaller<RESTFilterFieldV1>() {
      private RESTFilterFieldV1[] EMPTY_ARRAY = new RESTFilterFieldV1[0];
      public RESTFilterFieldV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterFieldV1.class;
      }
      public RESTFilterFieldV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterFieldV1.class, objId);
          }
          RESTFilterFieldV1 entity = new RESTFilterFieldV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("value")) && (!obj.get("value").isNull())) {
            entity.setValue(java_lang_String.demarshall(obj.get("value"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("filter")) && (!obj.get("filter").isNull())) {
            entity.setFilter(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1.demarshall(obj.get("filter"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterFieldCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1", t);
        }
      }
      public String marshall(RESTFilterFieldV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(10240).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"value\" : ").append(java_lang_String.marshall(a0.getValue(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"filter\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1.marshall(a0.getFilter(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterFieldCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterFieldV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTImageCollectionV1 = new Marshaller<RESTImageCollectionV1>() {
      private RESTImageCollectionV1[] EMPTY_ARRAY = new RESTImageCollectionV1[0];
      public RESTImageCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1", t);
        }
      }
      public String marshall(RESTImageCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTImageCollectionV1);
    java_lang_NullPointerException = new Marshaller<NullPointerException>() {
      private NullPointerException[] EMPTY_ARRAY = new NullPointerException[0];
      public NullPointerException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.NullPointerException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.NullPointerException", java_lang_NullPointerException);
    java_lang_NegativeArraySizeException = new Marshaller<NegativeArraySizeException>() {
      private NegativeArraySizeException[] EMPTY_ARRAY = new NegativeArraySizeException[0];
      public NegativeArraySizeException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.NegativeArraySizeException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.NegativeArraySizeException", java_lang_NegativeArraySizeException);
    org_jboss_errai_bus_client_api_base_TransportIOException = new Marshaller<TransportIOException>() {
      private TransportIOException[] EMPTY_ARRAY = new TransportIOException[0];
      public TransportIOException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3328).append("{\"^EncodedType\":\"org.jboss.errai.bus.client.api.base.TransportIOException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"errorCode\" : ").append(java_lang_Integer.marshall(a0.errorCode(), a1)).append(",").append("\"errorMessage\" : ").append(java_lang_String.marshall(a0.getErrorMessage(), a1)).append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.errai.bus.client.api.base.TransportIOException", org_jboss_errai_bus_client_api_base_TransportIOException);
    org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTPropertyCategoryInPropertyTagCollectionItemV1 = new Marshaller<RESTPropertyCategoryInPropertyTagCollectionItemV1>() {
      private RESTPropertyCategoryInPropertyTagCollectionItemV1[] EMPTY_ARRAY = new RESTPropertyCategoryInPropertyTagCollectionItemV1[0];
      public RESTPropertyCategoryInPropertyTagCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTPropertyCategoryInPropertyTagCollectionItemV1.class;
      }
      public RESTPropertyCategoryInPropertyTagCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTPropertyCategoryInPropertyTagCollectionItemV1.class, objId);
          }
          RESTPropertyCategoryInPropertyTagCollectionItemV1 entity = new RESTPropertyCategoryInPropertyTagCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_join_RESTPropertyCategoryInPropertyTagV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyCategoryInPropertyTagCollectionItemV1", t);
        }
      }
      public String marshall(RESTPropertyCategoryInPropertyTagCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyCategoryInPropertyTagCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(7168).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyCategoryInPropertyTagCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_join_RESTPropertyCategoryInPropertyTagV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyCategoryInPropertyTagCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTPropertyCategoryInPropertyTagCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicSourceUrlCollectionV1 = new Marshaller<RESTTopicSourceUrlCollectionV1>() {
      private RESTTopicSourceUrlCollectionV1[] EMPTY_ARRAY = new RESTTopicSourceUrlCollectionV1[0];
      public RESTTopicSourceUrlCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicSourceUrlCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1", t);
        }
      }
      public String marshall(RESTTopicSourceUrlCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicSourceUrlCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTTopicV1 = new Marshaller<RESTTopicV1>() {
      private RESTTopicV1[] EMPTY_ARRAY = new RESTTopicV1[0];
      public RESTTopicV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.setCreated((Date) java_lang_Object.demarshall(Date.class, obj.get("created"), a1));
          }
          if ((obj.containsKey("lastModified")) && (!obj.get("lastModified").isNull())) {
            entity.setLastModified((Date) java_lang_Object.demarshall(Date.class, obj.get("lastModified"), a1));
          }
          if ((obj.containsKey("bugzillaBugs_OTM")) && (!obj.get("bugzillaBugs_OTM").isNull())) {
            entity.setBugzillaBugs_OTM(org_jboss_pressgang_ccms_rest_v1_collections_RESTBugzillaBugCollectionV1.demarshall(obj.get("bugzillaBugs_OTM"), a1));
          }
          if ((obj.containsKey("translatedTopics_OTM")) && (!obj.get("translatedTopics_OTM").isNull())) {
            entity.setTranslatedTopics_OTM(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1.demarshall(obj.get("translatedTopics_OTM"), a1));
          }
          if ((obj.containsKey("outgoingRelationships")) && (!obj.get("outgoingRelationships").isNull())) {
            entity.setOutgoingRelationships(org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicCollectionV1.demarshall(obj.get("outgoingRelationships"), a1));
          }
          if ((obj.containsKey("incomingRelationships")) && (!obj.get("incomingRelationships").isNull())) {
            entity.setIncomingRelationships(org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicCollectionV1.demarshall(obj.get("incomingRelationships"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("locale")) && (!obj.get("locale").isNull())) {
            entity.setLocale(java_lang_String.demarshall(obj.get("locale"), a1));
          }
          if ((obj.containsKey("tags")) && (!obj.get("tags").isNull())) {
            entity.setTags(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.demarshall(obj.get("tags"), a1));
          }
          if ((obj.containsKey("sourceUrls_OTM")) && (!obj.get("sourceUrls_OTM").isNull())) {
            entity.setSourceUrls_OTM(org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicSourceUrlCollectionV1.demarshall(obj.get("sourceUrls_OTM"), a1));
          }
          if ((obj.containsKey("properties")) && (!obj.get("properties").isNull())) {
            entity.setProperties(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1.demarshall(obj.get("properties"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1", t);
        }
      }
      public String marshall(RESTTopicV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(10752).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"created\" : ").append(java_lang_Object.marshall(a0.getCreated(), a1)).append(",").append("\"lastModified\" : ").append(java_lang_Object.marshall(a0.getLastModified(), a1)).append(",").append("\"bugzillaBugs_OTM\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTBugzillaBugCollectionV1.marshall(a0.getBugzillaBugs_OTM(), a1)).append(",").append("\"translatedTopics_OTM\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1.marshall(a0.getTranslatedTopics_OTM(), a1)).append(",").append("\"outgoingRelationships\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicCollectionV1.marshall(a0.getOutgoingRelationships(), a1)).append(",").append("\"incomingRelationships\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicCollectionV1.marshall(a0.getIncomingRelationships(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"title\" : ").append(java_lang_String.marshall(a0.getTitle(), a1)).append(",").append("\"xml\" : ").append(java_lang_String.marshall(a0.getXml(), a1)).append(",").append("\"xmlErrors\" : ").append(java_lang_String.marshall(a0.getXmlErrors(), a1)).append(",").append("\"html\" : ").append(java_lang_String.marshall(a0.getHtml(), a1)).append(",").append("\"locale\" : ").append(java_lang_String.marshall(a0.getLocale(), a1)).append(",").append("\"tags\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.marshall(a0.getTags(), a1)).append(",").append("\"sourceUrls_OTM\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicSourceUrlCollectionV1.marshall(a0.getSourceUrls_OTM(), a1)).append(",").append("\"properties\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1.marshall(a0.getProperties(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTTopicV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicStringCollectionV1 = new Marshaller<RESTTranslatedTopicStringCollectionV1>() {
      private RESTTranslatedTopicStringCollectionV1[] EMPTY_ARRAY = new RESTTranslatedTopicStringCollectionV1[0];
      public RESTTranslatedTopicStringCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicStringCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicStringCollectionV1", t);
        }
      }
      public String marshall(RESTTranslatedTopicStringCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicStringCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicStringCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicStringCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicStringCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTTranslatedTopicV1 = new Marshaller<RESTTranslatedTopicV1>() {
      private RESTTranslatedTopicV1[] EMPTY_ARRAY = new RESTTranslatedTopicV1[0];
      public RESTTranslatedTopicV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.setTopic(org_jboss_pressgang_ccms_rest_v1_entities_RESTTopicV1.demarshall(obj.get("topic"), a1));
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
            entity.setHtmlUpdated((Date) java_lang_Object.demarshall(Date.class, obj.get("htmlUpdated"), a1));
          }
          if ((obj.containsKey("containsFuzzyTranslation")) && (!obj.get("containsFuzzyTranslation").isNull())) {
            _$227131749_containsFuzzyTranslation(entity, java_lang_Boolean.demarshall(obj.get("containsFuzzyTranslation"), a1));
          }
          if ((obj.containsKey("translatedTopicStrings")) && (!obj.get("translatedTopicStrings").isNull())) {
            _$227131749_translatedTopicStrings(entity, org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicStringCollectionV1.demarshall(obj.get("translatedTopicStrings"), a1));
          }
          if ((obj.containsKey("outgoingTranslatedRelationships")) && (!obj.get("outgoingTranslatedRelationships").isNull())) {
            entity.setOutgoingTranslatedRelationships(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1.demarshall(obj.get("outgoingTranslatedRelationships"), a1));
          }
          if ((obj.containsKey("incomingTranslatedRelationships")) && (!obj.get("incomingTranslatedRelationships").isNull())) {
            entity.setIncomingTranslatedRelationships(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1.demarshall(obj.get("incomingTranslatedRelationships"), a1));
          }
          if ((obj.containsKey("outgoingRelationships")) && (!obj.get("outgoingRelationships").isNull())) {
            entity.setOutgoingRelationships(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1.demarshall(obj.get("outgoingRelationships"), a1));
          }
          if ((obj.containsKey("incomingRelationships")) && (!obj.get("incomingRelationships").isNull())) {
            entity.setIncomingRelationships(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1.demarshall(obj.get("incomingRelationships"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("locale")) && (!obj.get("locale").isNull())) {
            entity.setLocale(java_lang_String.demarshall(obj.get("locale"), a1));
          }
          if ((obj.containsKey("tags")) && (!obj.get("tags").isNull())) {
            entity.setTags(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.demarshall(obj.get("tags"), a1));
          }
          if ((obj.containsKey("sourceUrls_OTM")) && (!obj.get("sourceUrls_OTM").isNull())) {
            entity.setSourceUrls_OTM(org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicSourceUrlCollectionV1.demarshall(obj.get("sourceUrls_OTM"), a1));
          }
          if ((obj.containsKey("properties")) && (!obj.get("properties").isNull())) {
            entity.setProperties(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1.demarshall(obj.get("properties"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1", t);
        }
      }
      public String marshall(RESTTranslatedTopicV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(15232).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"topic\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTTopicV1.marshall(a0.getTopic(), a1)).append(",").append("\"translatedTopicId\" : ").append(java_lang_Integer.marshall(a0.getTranslatedTopicId(), a1)).append(",").append("\"topicId\" : ").append(java_lang_Integer.marshall(a0.getTopicId(), a1)).append(",").append("\"topicRevision\" : ").append(java_lang_Integer.marshall(a0.getTopicRevision(), a1)).append(",").append("\"translationPercentage\" : ").append(java_lang_Integer.marshall(a0.getTranslationPercentage(), a1)).append(",").append("\"htmlUpdated\" : ").append(java_lang_Object.marshall(a0.getHtmlUpdated(), a1)).append(",").append("\"containsFuzzyTranslation\" : ").append(java_lang_Boolean.marshall(a0.getContainsFuzzyTranslation(), a1)).append(",").append("\"translatedTopicStrings\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicStringCollectionV1.marshall(_$227131749_translatedTopicStrings(a0), a1)).append(",").append("\"outgoingTranslatedRelationships\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1.marshall(a0.getOutgoingTranslatedRelationships(), a1)).append(",").append("\"incomingTranslatedRelationships\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1.marshall(a0.getIncomingTranslatedRelationships(), a1)).append(",").append("\"outgoingRelationships\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1.marshall(a0.getOutgoingRelationships(), a1)).append(",").append("\"incomingRelationships\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1.marshall(a0.getIncomingRelationships(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"title\" : ").append(java_lang_String.marshall(a0.getTitle(), a1)).append(",").append("\"xml\" : ").append(java_lang_String.marshall(a0.getXml(), a1)).append(",").append("\"xmlErrors\" : ").append(java_lang_String.marshall(a0.getXmlErrors(), a1)).append(",").append("\"html\" : ").append(java_lang_String.marshall(a0.getHtml(), a1)).append(",").append("\"locale\" : ").append(java_lang_String.marshall(a0.getLocale(), a1)).append(",").append("\"tags\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.marshall(a0.getTags(), a1)).append(",").append("\"sourceUrls_OTM\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicSourceUrlCollectionV1.marshall(a0.getSourceUrls_OTM(), a1)).append(",").append("\"properties\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1.marshall(a0.getProperties(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTTranslatedTopicV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTranslatedTopicCollectionItemV1 = new Marshaller<RESTTranslatedTopicCollectionItemV1>() {
      private RESTTranslatedTopicCollectionItemV1[] EMPTY_ARRAY = new RESTTranslatedTopicCollectionItemV1[0];
      public RESTTranslatedTopicCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTTranslatedTopicCollectionItemV1.class;
      }
      public RESTTranslatedTopicCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTranslatedTopicCollectionItemV1.class, objId);
          }
          RESTTranslatedTopicCollectionItemV1 entity = new RESTTranslatedTopicCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTTranslatedTopicV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicCollectionItemV1", t);
        }
      }
      public String marshall(RESTTranslatedTopicCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(15488).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTTranslatedTopicV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTranslatedTopicCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterLocaleV1 = new Marshaller<RESTFilterLocaleV1>() {
      private RESTFilterLocaleV1[] EMPTY_ARRAY = new RESTFilterLocaleV1[0];
      public RESTFilterLocaleV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterLocaleV1.class;
      }
      public RESTFilterLocaleV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterLocaleV1.class, objId);
          }
          RESTFilterLocaleV1 entity = new RESTFilterLocaleV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("locale")) && (!obj.get("locale").isNull())) {
            entity.setLocale(java_lang_String.demarshall(obj.get("locale"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          if ((obj.containsKey("filter")) && (!obj.get("filter").isNull())) {
            entity.setFilter(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1.demarshall(obj.get("filter"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterLocaleCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterLocaleV1", t);
        }
      }
      public String marshall(RESTFilterLocaleV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterLocaleV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(10112).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterLocaleV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"locale\" : ").append(java_lang_String.marshall(a0.getLocale(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append(",").append("\"filter\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1.marshall(a0.getFilter(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterLocaleCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterLocaleV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterLocaleV1);
    org_jboss_pressgang_ccms_rest_v1_entities_join_RESTPropertyTagInPropertyCategoryV1 = new Marshaller<RESTPropertyTagInPropertyCategoryV1>() {
      private RESTPropertyTagInPropertyCategoryV1[] EMPTY_ARRAY = new RESTPropertyTagInPropertyCategoryV1[0];
      public RESTPropertyTagInPropertyCategoryV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTPropertyTagInPropertyCategoryV1.class;
      }
      public RESTPropertyTagInPropertyCategoryV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTPropertyTagInPropertyCategoryV1.class, objId);
          }
          RESTPropertyTagInPropertyCategoryV1 entity = new RESTPropertyTagInPropertyCategoryV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("relationshipId")) && (!obj.get("relationshipId").isNull())) {
            entity.setRelationshipId(java_lang_Integer.demarshall(obj.get("relationshipId"), a1));
          }
          if ((obj.containsKey("relationshipSort")) && (!obj.get("relationshipSort").isNull())) {
            entity.setRelationshipSort(java_lang_Integer.demarshall(obj.get("relationshipSort"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyTagInPropertyCategoryCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
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
          if ((obj.containsKey("propertyCategories")) && (!obj.get("propertyCategories").isNull())) {
            entity.setPropertyCategories(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyCategoryInPropertyTagCollectionV1.demarshall(obj.get("propertyCategories"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyTagInPropertyCategoryV1", t);
        }
      }
      public String marshall(RESTPropertyTagInPropertyCategoryV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyTagInPropertyCategoryV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(7296).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyTagInPropertyCategoryV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"relationshipId\" : ").append(java_lang_Integer.marshall(a0.getRelationshipId(), a1)).append(",").append("\"relationshipSort\" : ").append(java_lang_Integer.marshall(a0.getRelationshipSort(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyTagInPropertyCategoryCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"regex\" : ").append(java_lang_String.marshall(a0.getRegex(), a1)).append(",").append("\"canBeNull\" : ").append(java_lang_Boolean.marshall(a0.getCanBeNull(), a1)).append(",").append("\"isUnique\" : ").append(java_lang_Boolean.marshall(a0.getIsUnique(), a1)).append(",").append("\"propertyCategories\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTPropertyCategoryInPropertyTagCollectionV1.marshall(a0.getPropertyCategories(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyTagInPropertyCategoryV1", org_jboss_pressgang_ccms_rest_v1_entities_join_RESTPropertyTagInPropertyCategoryV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTopicCollectionItemV1 = new Marshaller<RESTTopicCollectionItemV1>() {
      private RESTTopicCollectionItemV1[] EMPTY_ARRAY = new RESTTopicCollectionItemV1[0];
      public RESTTopicCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTTopicCollectionItemV1.class;
      }
      public RESTTopicCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTopicCollectionItemV1.class, objId);
          }
          RESTTopicCollectionItemV1 entity = new RESTTopicCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTTopicV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1", t);
        }
      }
      public String marshall(RESTTopicCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(11008).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTTopicV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTopicCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTRoleCollectionItemV1 = new Marshaller<RESTRoleCollectionItemV1>() {
      private RESTRoleCollectionItemV1[] EMPTY_ARRAY = new RESTRoleCollectionItemV1[0];
      public RESTRoleCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTRoleCollectionItemV1.class;
      }
      public RESTRoleCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTRoleCollectionItemV1.class, objId);
          }
          RESTRoleCollectionItemV1 entity = new RESTRoleCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTRoleV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTRoleCollectionItemV1", t);
        }
      }
      public String marshall(RESTRoleCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTRoleCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(5888).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTRoleCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTRoleV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTRoleCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTRoleCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTTopicSourceUrlV1 = new Marshaller<RESTTopicSourceUrlV1>() {
      private RESTTopicSourceUrlV1[] EMPTY_ARRAY = new RESTTopicSourceUrlV1[0];
      public RESTTopicSourceUrlV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicSourceUrlCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicSourceUrlV1", t);
        }
      }
      public String marshall(RESTTopicSourceUrlV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicSourceUrlV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(5504).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicSourceUrlV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"url\" : ").append(java_lang_String.marshall(a0.getUrl(), a1)).append(",").append("\"title\" : ").append(java_lang_String.marshall(a0.getTitle(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTopicSourceUrlCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicSourceUrlV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTTopicSourceUrlV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTopicSourceUrlCollectionItemV1 = new Marshaller<RESTTopicSourceUrlCollectionItemV1>() {
      private RESTTopicSourceUrlCollectionItemV1[] EMPTY_ARRAY = new RESTTopicSourceUrlCollectionItemV1[0];
      public RESTTopicSourceUrlCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTTopicSourceUrlCollectionItemV1.class;
      }
      public RESTTopicSourceUrlCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTopicSourceUrlCollectionItemV1.class, objId);
          }
          RESTTopicSourceUrlCollectionItemV1 entity = new RESTTopicSourceUrlCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTTopicSourceUrlV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicSourceUrlCollectionItemV1", t);
        }
      }
      public String marshall(RESTTopicSourceUrlCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicSourceUrlCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(5760).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicSourceUrlCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTTopicSourceUrlV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicSourceUrlCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTopicSourceUrlCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterLocaleCollectionItemV1 = new Marshaller<RESTFilterLocaleCollectionItemV1>() {
      private RESTFilterLocaleCollectionItemV1[] EMPTY_ARRAY = new RESTFilterLocaleCollectionItemV1[0];
      public RESTFilterLocaleCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterLocaleCollectionItemV1.class;
      }
      public RESTFilterLocaleCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterLocaleCollectionItemV1.class, objId);
          }
          RESTFilterLocaleCollectionItemV1 entity = new RESTFilterLocaleCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterLocaleV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterLocaleCollectionItemV1", t);
        }
      }
      public String marshall(RESTFilterLocaleCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterLocaleCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(10368).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterLocaleCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterLocaleV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterLocaleCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterLocaleCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTCategoryCollectionV1 = new Marshaller<RESTCategoryCollectionV1>() {
      private RESTCategoryCollectionV1[] EMPTY_ARRAY = new RESTCategoryCollectionV1[0];
      public RESTCategoryCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1", t);
        }
      }
      public String marshall(RESTCategoryCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTCategoryCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTCategoryV1 = new Marshaller<RESTCategoryV1>() {
      private RESTCategoryV1[] EMPTY_ARRAY = new RESTCategoryV1[0];
      public RESTCategoryV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTCategoryCollectionV1.demarshall(obj.get("revisions"), a1));
          }
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
            entity.setTags(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTTagInCategoryCollectionV1.demarshall(obj.get("tags"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1", t);
        }
      }
      public String marshall(RESTCategoryV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(6912).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTCategoryCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"mutuallyExclusive\" : ").append(java_lang_Boolean.marshall(a0.getMutuallyExclusive(), a1)).append(",").append("\"sort\" : ").append(java_lang_Integer.marshall(a0.getSort(), a1)).append(",").append("\"tags\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTTagInCategoryCollectionV1.marshall(a0.getTags(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTCategoryV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterCategoryV1 = new Marshaller<RESTFilterCategoryV1>() {
      private RESTFilterCategoryV1[] EMPTY_ARRAY = new RESTFilterCategoryV1[0];
      public RESTFilterCategoryV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterCategoryV1.class;
      }
      public RESTFilterCategoryV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterCategoryV1.class, objId);
          }
          RESTFilterCategoryV1 entity = new RESTFilterCategoryV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("category")) && (!obj.get("category").isNull())) {
            entity.setCategory(org_jboss_pressgang_ccms_rest_v1_entities_RESTCategoryV1.demarshall(obj.get("category"), a1));
          }
          if ((obj.containsKey("project")) && (!obj.get("project").isNull())) {
            entity.setProject(org_jboss_pressgang_ccms_rest_v1_entities_RESTProjectV1.demarshall(obj.get("project"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          if ((obj.containsKey("filter")) && (!obj.get("filter").isNull())) {
            entity.setFilter(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1.demarshall(obj.get("filter"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterCategoryCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterCategoryV1", t);
        }
      }
      public String marshall(RESTFilterCategoryV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterCategoryV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(16384).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterCategoryV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"category\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTCategoryV1.marshall(a0.getCategory(), a1)).append(",").append("\"project\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTProjectV1.marshall(a0.getProject(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append(",").append("\"filter\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1.marshall(a0.getFilter(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTFilterCategoryCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterCategoryV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterCategoryV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterCategoryCollectionItemV1 = new Marshaller<RESTFilterCategoryCollectionItemV1>() {
      private RESTFilterCategoryCollectionItemV1[] EMPTY_ARRAY = new RESTFilterCategoryCollectionItemV1[0];
      public RESTFilterCategoryCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterCategoryCollectionItemV1.class;
      }
      public RESTFilterCategoryCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterCategoryCollectionItemV1.class, objId);
          }
          RESTFilterCategoryCollectionItemV1 entity = new RESTFilterCategoryCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterCategoryV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCategoryCollectionItemV1", t);
        }
      }
      public String marshall(RESTFilterCategoryCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCategoryCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(16640).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCategoryCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterCategoryV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCategoryCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterCategoryCollectionItemV1);
    org_jboss_errai_bus_client_api_base_MessageDeliveryFailure = new Marshaller<MessageDeliveryFailure>() {
      private MessageDeliveryFailure[] EMPTY_ARRAY = new MessageDeliveryFailure[0];
      public MessageDeliveryFailure[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"org.jboss.errai.bus.client.api.base.MessageDeliveryFailure\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.errai.bus.client.api.base.MessageDeliveryFailure", org_jboss_errai_bus_client_api_base_MessageDeliveryFailure);
    org_jboss_pressgang_ccms_rest_v1_entities_join_RESTTagInCategoryV1 = new Marshaller<RESTTagInCategoryV1>() {
      private RESTTagInCategoryV1[] EMPTY_ARRAY = new RESTTagInCategoryV1[0];
      public RESTTagInCategoryV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTTagInCategoryV1.class;
      }
      public RESTTagInCategoryV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTagInCategoryV1.class, objId);
          }
          RESTTagInCategoryV1 entity = new RESTTagInCategoryV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("relationshipId")) && (!obj.get("relationshipId").isNull())) {
            entity.setRelationshipId(java_lang_Integer.demarshall(obj.get("relationshipId"), a1));
          }
          if ((obj.containsKey("relationshipSort")) && (!obj.get("relationshipSort").isNull())) {
            entity.setRelationshipSort(java_lang_Integer.demarshall(obj.get("relationshipSort"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTTagInCategoryCollectionV1.demarshall(obj.get("revisions"), a1));
          }
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("description")) && (!obj.get("description").isNull())) {
            entity.setDescription(java_lang_String.demarshall(obj.get("description"), a1));
          }
          if ((obj.containsKey("categories")) && (!obj.get("categories").isNull())) {
            entity.setCategories(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTCategoryInTagCollectionV1.demarshall(obj.get("categories"), a1));
          }
          if ((obj.containsKey("parentTags")) && (!obj.get("parentTags").isNull())) {
            entity.setParentTags(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.demarshall(obj.get("parentTags"), a1));
          }
          if ((obj.containsKey("childTags")) && (!obj.get("childTags").isNull())) {
            entity.setChildTags(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.demarshall(obj.get("childTags"), a1));
          }
          if ((obj.containsKey("projects")) && (!obj.get("projects").isNull())) {
            entity.setProjects(org_jboss_pressgang_ccms_rest_v1_collections_RESTProjectCollectionV1.demarshall(obj.get("projects"), a1));
          }
          if ((obj.containsKey("properties")) && (!obj.get("properties").isNull())) {
            entity.setProperties(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1.demarshall(obj.get("properties"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1", t);
        }
      }
      public String marshall(RESTTagInCategoryV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(9344).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"relationshipId\" : ").append(java_lang_Integer.marshall(a0.getRelationshipId(), a1)).append(",").append("\"relationshipSort\" : ").append(java_lang_Integer.marshall(a0.getRelationshipSort(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTTagInCategoryCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"categories\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTCategoryInTagCollectionV1.marshall(a0.getCategories(), a1)).append(",").append("\"parentTags\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.marshall(a0.getParentTags(), a1)).append(",").append("\"childTags\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTagCollectionV1.marshall(a0.getChildTags(), a1)).append(",").append("\"projects\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTProjectCollectionV1.marshall(a0.getProjects(), a1)).append(",").append("\"properties\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_join_RESTAssignedPropertyTagCollectionV1.marshall(a0.getProperties(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1", org_jboss_pressgang_ccms_rest_v1_entities_join_RESTTagInCategoryV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTTagInCategoryCollectionItemV1 = new Marshaller<RESTTagInCategoryCollectionItemV1>() {
      private RESTTagInCategoryCollectionItemV1[] EMPTY_ARRAY = new RESTTagInCategoryCollectionItemV1[0];
      public RESTTagInCategoryCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTTagInCategoryCollectionItemV1.class;
      }
      public RESTTagInCategoryCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTagInCategoryCollectionItemV1.class, objId);
          }
          RESTTagInCategoryCollectionItemV1 entity = new RESTTagInCategoryCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_join_RESTTagInCategoryV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1", t);
        }
      }
      public String marshall(RESTTagInCategoryCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(9600).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_join_RESTTagInCategoryV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTTagInCategoryCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTImageV1 = new Marshaller<RESTImageV1>() {
      private RESTImageV1[] EMPTY_ARRAY = new RESTImageV1[0];
      public RESTImageV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.setLanguageImages_OTM(org_jboss_pressgang_ccms_rest_v1_collections_RESTLanguageImageCollectionV1.demarshall(obj.get("languageImages_OTM"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTImageCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1", t);
        }
      }
      public String marshall(RESTImageV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(6528).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"description\" : ").append(java_lang_String.marshall(a0.getDescription(), a1)).append(",").append("\"languageImages_OTM\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTLanguageImageCollectionV1.marshall(a0.getLanguageImages_OTM(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTImageCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTImageV1);
    arrayOf_byte_D1 = new QualifyingMarshallerWrapper(new Marshaller<byte[]>() {
      public byte[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
    org_jboss_pressgang_ccms_rest_v1_entities_RESTLanguageImageV1 = new Marshaller<RESTLanguageImageV1>() {
      private RESTLanguageImageV1[] EMPTY_ARRAY = new RESTLanguageImageV1[0];
      public RESTLanguageImageV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.setImage(org_jboss_pressgang_ccms_rest_v1_entities_RESTImageV1.demarshall(obj.get("image"), a1));
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
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTLanguageImageCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageImageV1", t);
        }
      }
      public String marshall(RESTLanguageImageV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageImageV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(9344).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageImageV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"image\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTImageV1.marshall(a0.getImage(), a1)).append(",").append("\"imageData\" : ").append(arrayOf_byte_D1.marshall(a0.getImageData(), a1)).append(",").append("\"thumbnail\" : ").append(arrayOf_byte_D1.marshall(a0.getThumbnail(), a1)).append(",").append("\"imageDataBase64\" : ").append(arrayOf_byte_D1.marshall(a0.getImageDataBase64(), a1)).append(",").append("\"locale\" : ").append(java_lang_String.marshall(a0.getLocale(), a1)).append(",").append("\"filename\" : ").append(java_lang_String.marshall(a0.getFilename(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTLanguageImageCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageImageV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTLanguageImageV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTLanguageImageCollectionItemV1 = new Marshaller<RESTLanguageImageCollectionItemV1>() {
      private RESTLanguageImageCollectionItemV1[] EMPTY_ARRAY = new RESTLanguageImageCollectionItemV1[0];
      public RESTLanguageImageCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTLanguageImageCollectionItemV1.class;
      }
      public RESTLanguageImageCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTLanguageImageCollectionItemV1.class, objId);
          }
          RESTLanguageImageCollectionItemV1 entity = new RESTLanguageImageCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTLanguageImageV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1", t);
        }
      }
      public String marshall(RESTLanguageImageCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(9600).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTLanguageImageV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTLanguageImageCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTImageCollectionItemV1 = new Marshaller<RESTImageCollectionItemV1>() {
      private RESTImageCollectionItemV1[] EMPTY_ARRAY = new RESTImageCollectionItemV1[0];
      public RESTImageCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTImageCollectionItemV1.class;
      }
      public RESTImageCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTImageCollectionItemV1.class, objId);
          }
          RESTImageCollectionItemV1 entity = new RESTImageCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTImageV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1", t);
        }
      }
      public String marshall(RESTImageCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(6784).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTImageV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTImageCollectionItemV1);
    java_lang_ClassCastException = new Marshaller<ClassCastException>() {
      private ClassCastException[] EMPTY_ARRAY = new ClassCastException[0];
      public ClassCastException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.ClassCastException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.ClassCastException", java_lang_ClassCastException);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTCategoryCollectionItemV1 = new Marshaller<RESTCategoryCollectionItemV1>() {
      private RESTCategoryCollectionItemV1[] EMPTY_ARRAY = new RESTCategoryCollectionItemV1[0];
      public RESTCategoryCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTCategoryCollectionItemV1.class;
      }
      public RESTCategoryCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTCategoryCollectionItemV1.class, objId);
          }
          RESTCategoryCollectionItemV1 entity = new RESTCategoryCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTCategoryV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1", t);
        }
      }
      public String marshall(RESTCategoryCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(7168).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTCategoryV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTCategoryCollectionItemV1);
    java_lang_IndexOutOfBoundsException = new Marshaller<IndexOutOfBoundsException>() {
      private IndexOutOfBoundsException[] EMPTY_ARRAY = new IndexOutOfBoundsException[0];
      public IndexOutOfBoundsException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.IndexOutOfBoundsException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.IndexOutOfBoundsException", java_lang_IndexOutOfBoundsException);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTIntegerConstantV1 = new Marshaller<RESTIntegerConstantV1>() {
      private RESTIntegerConstantV1[] EMPTY_ARRAY = new RESTIntegerConstantV1[0];
      public RESTIntegerConstantV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTIntegerConstantV1.class;
      }
      public RESTIntegerConstantV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTIntegerConstantV1.class, objId);
          }
          RESTIntegerConstantV1 entity = new RESTIntegerConstantV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("value")) && (!obj.get("value").isNull())) {
            entity.setValue(java_lang_Integer.demarshall(obj.get("value"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTIntegerConstantCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTIntegerConstantV1", t);
        }
      }
      public String marshall(RESTIntegerConstantV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTIntegerConstantV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(5888).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTIntegerConstantV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"value\" : ").append(java_lang_Integer.marshall(a0.getValue(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTIntegerConstantCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTIntegerConstantV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTIntegerConstantV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTIntegerConstantCollectionItemV1 = new Marshaller<RESTIntegerConstantCollectionItemV1>() {
      private RESTIntegerConstantCollectionItemV1[] EMPTY_ARRAY = new RESTIntegerConstantCollectionItemV1[0];
      public RESTIntegerConstantCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTIntegerConstantCollectionItemV1.class;
      }
      public RESTIntegerConstantCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTIntegerConstantCollectionItemV1.class, objId);
          }
          RESTIntegerConstantCollectionItemV1 entity = new RESTIntegerConstantCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTIntegerConstantV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTIntegerConstantCollectionItemV1", t);
        }
      }
      public String marshall(RESTIntegerConstantCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTIntegerConstantCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(6144).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTIntegerConstantCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTIntegerConstantV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTIntegerConstantCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTIntegerConstantCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTPropertyTagInPropertyCategoryCollectionItemV1 = new Marshaller<RESTPropertyTagInPropertyCategoryCollectionItemV1>() {
      private RESTPropertyTagInPropertyCategoryCollectionItemV1[] EMPTY_ARRAY = new RESTPropertyTagInPropertyCategoryCollectionItemV1[0];
      public RESTPropertyTagInPropertyCategoryCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTPropertyTagInPropertyCategoryCollectionItemV1.class;
      }
      public RESTPropertyTagInPropertyCategoryCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTPropertyTagInPropertyCategoryCollectionItemV1.class, objId);
          }
          RESTPropertyTagInPropertyCategoryCollectionItemV1 entity = new RESTPropertyTagInPropertyCategoryCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_join_RESTPropertyTagInPropertyCategoryV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyTagInPropertyCategoryCollectionItemV1", t);
        }
      }
      public String marshall(RESTPropertyTagInPropertyCategoryCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyTagInPropertyCategoryCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(7552).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyTagInPropertyCategoryCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_join_RESTPropertyTagInPropertyCategoryV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyTagInPropertyCategoryCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_join_RESTPropertyTagInPropertyCategoryCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_RESTBlobConstantCollectionV1 = new Marshaller<RESTBlobConstantCollectionV1>() {
      private RESTBlobConstantCollectionV1[] EMPTY_ARRAY = new RESTBlobConstantCollectionV1[0];
      public RESTBlobConstantCollectionV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTBlobConstantCollectionV1.class;
      }
      public RESTBlobConstantCollectionV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTBlobConstantCollectionV1.class, objId);
          }
          RESTBlobConstantCollectionV1 entity = new RESTBlobConstantCollectionV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("items")) && (!obj.get("items").isNull())) {
            a1.setAssumedElementType("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBlobConstantCollectionItemV1");
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
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.RESTBlobConstantCollectionV1", t);
        }
      }
      public String marshall(RESTBlobConstantCollectionV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTBlobConstantCollectionV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.RESTBlobConstantCollectionV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"items\" : ").append(java_util_List.marshall(a0.getItems(), a1)).append(",").append("\"size\" : ").append(java_lang_Integer.marshall(a0.getSize(), a1)).append(",").append("\"expand\" : ").append(java_lang_String.marshall(a0.getExpand(), a1)).append(",").append("\"startExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getStartExpandIndex(), a1)).append(",").append("\"endExpandIndex\" : ").append(java_lang_Integer.marshall(a0.getEndExpandIndex(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.RESTBlobConstantCollectionV1", org_jboss_pressgang_ccms_rest_v1_collections_RESTBlobConstantCollectionV1);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTBlobConstantV1 = new Marshaller<RESTBlobConstantV1>() {
      private RESTBlobConstantV1[] EMPTY_ARRAY = new RESTBlobConstantV1[0];
      public RESTBlobConstantV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTBlobConstantV1.class;
      }
      public RESTBlobConstantV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTBlobConstantV1.class, objId);
          }
          RESTBlobConstantV1 entity = new RESTBlobConstantV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("name")) && (!obj.get("name").isNull())) {
            entity.setName(java_lang_String.demarshall(obj.get("name"), a1));
          }
          if ((obj.containsKey("value")) && (!obj.get("value").isNull())) {
            entity.setValue((byte[]) arrayOf_byte_D1.demarshall(obj.get("value"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTBlobConstantCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1", t);
        }
      }
      public String marshall(RESTBlobConstantV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(6272).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"name\" : ").append(java_lang_String.marshall(a0.getName(), a1)).append(",").append("\"value\" : ").append(arrayOf_byte_D1.marshall(a0.getValue(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTBlobConstantCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"selfLink\" : ").append(java_lang_String.marshall(a0.getSelfLink(), a1)).append(",").append("\"editLink\" : ").append(java_lang_String.marshall(a0.getEditLink(), a1)).append(",").append("\"deleteLink\" : ").append(java_lang_String.marshall(a0.getDeleteLink(), a1)).append(",").append("\"addLink\" : ").append(java_lang_String.marshall(a0.getAddLink(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTBlobConstantV1);
    org_jboss_errai_enterprise_client_cdi_events_BusReadyEvent = new Marshaller<BusReadyEvent>() {
      private BusReadyEvent[] EMPTY_ARRAY = new BusReadyEvent[0];
      public BusReadyEvent[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
    java_util_ConcurrentModificationException = new Marshaller<ConcurrentModificationException>() {
      private ConcurrentModificationException[] EMPTY_ARRAY = new ConcurrentModificationException[0];
      public ConcurrentModificationException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.util.ConcurrentModificationException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.util.ConcurrentModificationException", java_util_ConcurrentModificationException);
    org_jboss_pressgang_ccms_rest_v1_entities_RESTTranslatedTopicStringV1 = new Marshaller<RESTTranslatedTopicStringV1>() {
      private RESTTranslatedTopicStringV1[] EMPTY_ARRAY = new RESTTranslatedTopicStringV1[0];
      public RESTTranslatedTopicStringV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.setTranslatedTopic(org_jboss_pressgang_ccms_rest_v1_entities_RESTTranslatedTopicV1.demarshall(obj.get("translatedTopic"), a1));
          }
          if ((obj.containsKey("originalString")) && (!obj.get("originalString").isNull())) {
            entity.setOriginalString(java_lang_String.demarshall(obj.get("originalString"), a1));
          }
          if ((obj.containsKey("translatedString")) && (!obj.get("translatedString").isNull())) {
            entity.setTranslatedString(java_lang_String.demarshall(obj.get("translatedString"), a1));
          }
          if ((obj.containsKey("fuzzyTranslation")) && (!obj.get("fuzzyTranslation").isNull())) {
            entity.setFuzzyTranslation(java_lang_Boolean.demarshall(obj.get("fuzzyTranslation"), a1));
          }
          if ((obj.containsKey("revisions")) && (!obj.get("revisions").isNull())) {
            entity.setRevisions(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicStringCollectionV1.demarshall(obj.get("revisions"), a1));
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
          if ((obj.containsKey("expand")) && (!obj.get("expand").isNull())) {
            a1.setAssumedElementType("java.lang.String");
            entity.setExpand(java_util_List.demarshall(obj.get("expand"), a1));
            a1.setAssumedElementType(null);
          }
          if ((obj.containsKey("logDetails")) && (!obj.get("logDetails").isNull())) {
            entity.setLogDetails(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.demarshall(obj.get("logDetails"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicStringV1", t);
        }
      }
      public String marshall(RESTTranslatedTopicStringV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicStringV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(16512).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicStringV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"translatedTopic\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTTranslatedTopicV1.marshall(a0.getTranslatedTopic(), a1)).append(",").append("\"originalString\" : ").append(java_lang_String.marshall(a0.getOriginalString(), a1)).append(",").append("\"translatedString\" : ").append(java_lang_String.marshall(a0.getTranslatedString(), a1)).append(",").append("\"fuzzyTranslation\" : ").append(java_lang_Boolean.marshall(a0.getFuzzyTranslation(), a1)).append(",").append("\"revisions\" : ").append(org_jboss_pressgang_ccms_rest_v1_collections_RESTTranslatedTopicStringCollectionV1.marshall(a0.getRevisions(), a1)).append(",").append("\"id\" : ").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"revision\" : ").append(java_lang_Integer.marshall(a0.getRevision(), a1)).append(",").append("\"configuredParameters\" : ").append(java_util_List.marshall(a0.getConfiguredParameters(), a1)).append(",").append("\"expand\" : ").append(java_util_List.marshall(a0.getExpand(), a1)).append(",").append("\"logDetails\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_base_RESTLogDetailsV1.marshall(a0.getLogDetails(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicStringV1", org_jboss_pressgang_ccms_rest_v1_entities_RESTTranslatedTopicStringV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterCollectionItemV1 = new Marshaller<RESTFilterCollectionItemV1>() {
      private RESTFilterCollectionItemV1[] EMPTY_ARRAY = new RESTFilterCollectionItemV1[0];
      public RESTFilterCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterCollectionItemV1.class;
      }
      public RESTFilterCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterCollectionItemV1.class, objId);
          }
          RESTFilterCollectionItemV1 entity = new RESTFilterCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1", t);
        }
      }
      public String marshall(RESTFilterCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(9216).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTagCollectionItemV1 = new Marshaller<RESTTagCollectionItemV1>() {
      private RESTTagCollectionItemV1[] EMPTY_ARRAY = new RESTTagCollectionItemV1[0];
      public RESTTagCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTTagCollectionItemV1.class;
      }
      public RESTTagCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTagCollectionItemV1.class, objId);
          }
          RESTTagCollectionItemV1 entity = new RESTTagCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTTagV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1", t);
        }
      }
      public String marshall(RESTTagCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(8704).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTTagV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTagCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterFieldCollectionItemV1 = new Marshaller<RESTFilterFieldCollectionItemV1>() {
      private RESTFilterFieldCollectionItemV1[] EMPTY_ARRAY = new RESTFilterFieldCollectionItemV1[0];
      public RESTFilterFieldCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTFilterFieldCollectionItemV1.class;
      }
      public RESTFilterFieldCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTFilterFieldCollectionItemV1.class, objId);
          }
          RESTFilterFieldCollectionItemV1 entity = new RESTFilterFieldCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterFieldV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1", t);
        }
      }
      public String marshall(RESTFilterFieldCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(10496).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTFilterFieldV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTFilterFieldCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTUserCollectionItemV1 = new Marshaller<RESTUserCollectionItemV1>() {
      private RESTUserCollectionItemV1[] EMPTY_ARRAY = new RESTUserCollectionItemV1[0];
      public RESTUserCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTUserCollectionItemV1.class;
      }
      public RESTUserCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTUserCollectionItemV1.class, objId);
          }
          RESTUserCollectionItemV1 entity = new RESTUserCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTUserV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTUserCollectionItemV1", t);
        }
      }
      public String marshall(RESTUserCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTUserCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(3968).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTUserCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTUserV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTUserCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTUserCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTBlobConstantCollectionItemV1 = new Marshaller<RESTBlobConstantCollectionItemV1>() {
      private RESTBlobConstantCollectionItemV1[] EMPTY_ARRAY = new RESTBlobConstantCollectionItemV1[0];
      public RESTBlobConstantCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTBlobConstantCollectionItemV1.class;
      }
      public RESTBlobConstantCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTBlobConstantCollectionItemV1.class, objId);
          }
          RESTBlobConstantCollectionItemV1 entity = new RESTBlobConstantCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTBlobConstantV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBlobConstantCollectionItemV1", t);
        }
      }
      public String marshall(RESTBlobConstantCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBlobConstantCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(6528).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBlobConstantCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTBlobConstantV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBlobConstantCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTBlobConstantCollectionItemV1);
    org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTranslatedTopicStringCollectionItemV1 = new Marshaller<RESTTranslatedTopicStringCollectionItemV1>() {
      private RESTTranslatedTopicStringCollectionItemV1[] EMPTY_ARRAY = new RESTTranslatedTopicStringCollectionItemV1[0];
      public RESTTranslatedTopicStringCollectionItemV1[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
      public Class getTypeHandled() {
        return RESTTranslatedTopicStringCollectionItemV1.class;
      }
      public RESTTranslatedTopicStringCollectionItemV1 demarshall(EJValue a0, MarshallingSession a1) {
        try {
          if (a0.isNull()) {
            return null;
          }
          EJObject obj = a0.isObject();
          String objId = obj.get("^ObjectID").isString().stringValue();
          if (a1.hasObject(objId)) {
            return a1.getObject(RESTTranslatedTopicStringCollectionItemV1.class, objId);
          }
          RESTTranslatedTopicStringCollectionItemV1 entity = new RESTTranslatedTopicStringCollectionItemV1();
          a1.recordObject(objId, entity);
          if ((obj.containsKey("item")) && (!obj.get("item").isNull())) {
            entity.setItem(org_jboss_pressgang_ccms_rest_v1_entities_RESTTranslatedTopicStringV1.demarshall(obj.get("item"), a1));
          }
          if ((obj.containsKey("state")) && (!obj.get("state").isNull())) {
            entity.setState(java_lang_Integer.demarshall(obj.get("state"), a1));
          }
          return entity;
        } catch (Throwable t) {
          t.printStackTrace();
          throw new RuntimeException("error demarshalling entity: org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicStringCollectionItemV1", t);
        }
      }
      public String marshall(RESTTranslatedTopicStringCollectionItemV1 a0, MarshallingSession a1) {
        if (a0 == null) {
          return "null";
        }
        if (a1.hasObject(a0)) {
          String objId = a1.getObject(a0);
          return new StringBuilder(128).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicStringCollectionItemV1\"").append(",").append("\"^ObjectID\":\"").append(objId).append("\"}").toString();
        }
        String objId = a1.getObject(a0);
        a1.recordObject(objId, objId);
        return new StringBuilder(16768).append("{\"^EncodedType\":\"org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicStringCollectionItemV1\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"item\" : ").append(org_jboss_pressgang_ccms_rest_v1_entities_RESTTranslatedTopicStringV1.marshall(a0.getItem(), a1)).append(",").append("\"state\" : ").append(java_lang_Integer.marshall(a0.getState(), a1)).append("}").toString();
      }
    };
    marshallers.put("org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicStringCollectionItemV1", org_jboss_pressgang_ccms_rest_v1_collections_items_RESTTranslatedTopicStringCollectionItemV1);
    java_lang_ArithmeticException = new Marshaller<ArithmeticException>() {
      private ArithmeticException[] EMPTY_ARRAY = new ArithmeticException[0];
      public ArithmeticException[] getEmptyArray() {
        return EMPTY_ARRAY;
      }
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
            entity.initCause((Throwable) java_lang_Object.demarshall(Throwable.class, obj.get("cause"), a1));
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
        return new StringBuilder(3072).append("{\"^EncodedType\":\"java.lang.ArithmeticException\",\"^ObjectID\":\"").append(objId).append("\"").append(",").append("\"stackTrace\" : ").append(arrayOf_java_lang_StackTraceElement_D1.marshall(a0.getStackTrace(), a1)).append(",").append("\"message\" : ").append(java_lang_String.marshall(a0.getMessage(), a1)).append(",").append("\"cause\" : ").append(java_lang_Object.marshall(a0.getCause(), a1)).append("}").toString();
      }
    };
    marshallers.put("java.lang.ArithmeticException", java_lang_ArithmeticException);
    arrayOf_java_lang_Object_D1 = new QualifyingMarshallerWrapper(new Marshaller<Object[]>() {
      public java.lang.Object[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          return this._demarshall1(a0.isArray(), a1);
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
      public java.lang.String[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public int[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public long[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public double[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public float[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public short[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public boolean[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public char[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public java.lang.Integer[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public java.lang.Long[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public java.lang.Double[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public java.lang.Float[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public java.lang.Short[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public java.lang.Boolean[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public java.lang.Byte[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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
      public java.lang.Character[][] getEmptyArray() {
        throw new UnsupportedOperationException("Not implemented!");
      }

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
          sb.append(java_lang_Object.marshall(a0[i], a1));
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
          return this._demarshall1(a0.isArray(), a1);
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

  private static Boolean _$227131749_containsFuzzyTranslation(RESTTranslatedTopicV1 instance) {
    try {
      return (Boolean) _$227131749_containsFuzzyTranslation_fld.get(instance);
    } catch (Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private static void _$227131749_containsFuzzyTranslation(RESTTranslatedTopicV1 instance, Boolean value) {
    try {
      _$227131749_containsFuzzyTranslation_fld.set(instance, value);
    } catch (Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private static RESTTranslatedTopicStringCollectionV1 _$227131749_translatedTopicStrings(RESTTranslatedTopicV1 instance) {
    try {
      return (RESTTranslatedTopicStringCollectionV1) _$227131749_translatedTopicStrings_fld.get(instance);
    } catch (Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private static void _$227131749_translatedTopicStrings(RESTTranslatedTopicV1 instance, RESTTranslatedTopicStringCollectionV1 value) {
    try {
      _$227131749_translatedTopicStrings_fld.set(instance, value);
    } catch (Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public Marshaller getMarshaller(String a0, String a1) {
    return marshallers.get(a1);
  }
}