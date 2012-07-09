package javax.ws.rs.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * 
 * Copied here to provide an implementation that can be used with GWT
 */
@SuppressWarnings("serial")
public class MultivaluedMapImpl extends HashMap<String, List<String>> implements Map<String, List<String>>
{
   public void putSingle(String key, String value)
   {
      List<String> list = new ArrayList<String>();
      list.add(value);
      put(key, list);
   }

   public final void add(String key, String value)
   {
      getList(key).add(value);
   }


   public final void addMultiple(String key, Collection<String> values)
   {
      getList(key).addAll(values);
   }

   public String getFirst(String key)
   {
      List<String> list = get(key);
      return list == null ? null : list.get(0);
   }

   public final List<String> getList(String key)
   {
      List<String> list = get(key);
      if (list == null)
         put(key, list = new ArrayList<String>());
      return list;
   }

   public void addAll(MultivaluedMapImpl other)
   {
      for (Map.Entry<String, List<String>> entry : other.entrySet())
      {
         getList(entry.getKey()).addAll(entry.getValue());
      }
   }
}