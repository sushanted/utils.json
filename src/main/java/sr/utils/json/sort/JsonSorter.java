package sr.utils.json.sort;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonSorter {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  /***
   *
   * @param object
   *          : any java object
   * @return sorted json string
   */
  public static String getSortedJson(final Object object) {
    return getSortedJson(toJson(object));
  }

  public static String getSortedJson(final String json) {
    return getSortedJsonString(toObject(json, Object.class));
  }

  @SuppressWarnings("unchecked")
  private static String getSortedJsonString(final Object object) {

    if (object instanceof Map<?, ?>) {
      return getSortedJsonString((Map<String, ?>) object);
    }

    if (object instanceof Collection<?>) {
      return getSortedJsonString((Collection<?>) object);
    }

    if (object instanceof String) {
      return toJson(object);
    }

    return String.valueOf(object);

  }

  // Sort maps only by key
  private static String getSortedJsonString(final Collection<?> list) {
    return String.format(//
        "[%s]", //
        list.stream()//
            .map(JsonSorter::getSortedJsonString)//
            .sorted()//
            .collect(Collectors.joining(","))//
    );
  }

  // Sort items in the list (items are sorted jsons)
  private static String getSortedJsonString(final Map<String, ?> map) {
    return String.format(//
        "{%s}",
        map.entrySet()//
            .stream()//
            .sorted(Comparator.comparing(Entry::getKey))//
            .map(JsonSorter::entryToJson)//
            .collect(Collectors.joining(","))//
    );
  }

  private static String entryToJson(//
      final Entry<String, ?> entry//
  ) {
    return getSortedJsonString(entry.getKey()) //
        + ":"//
        + getSortedJsonString(entry.getValue());
  }

  public static String toJson(final Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (final JsonProcessingException jpe) {
      throw new RuntimeException("Exception occurred while writing object into json", jpe);
    }
  }

  public static <T> T toObject(final String jsonString, final Class<? extends T> objectClass) {
    try {
      return objectMapper.readValue(jsonString, objectClass);
    } catch (final IOException ioe) {
      throw new RuntimeException("Exception while reading object from json", ioe);
    }
  }
}
