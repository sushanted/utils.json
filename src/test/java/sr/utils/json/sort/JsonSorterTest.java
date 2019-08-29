package sr.utils.json.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.Test;

public class JsonSorterTest {
  @Test
  public void testPrimitives() {
    Assert.assertEquals("null", JsonSorter.getSortedJson(JsonSorter.toJson(null)));
    Assert.assertEquals("true", JsonSorter.getSortedJson(JsonSorter.toJson(true)));
    Assert.assertEquals("23", JsonSorter.getSortedJson(JsonSorter.toJson(23)));
    Assert.assertEquals("3.1415", JsonSorter.getSortedJson(JsonSorter.toJson(3.1415)));
    Assert.assertEquals("\"jsonString\"", JsonSorter.getSortedJson(JsonSorter.toJson("jsonString")));
    Assert.assertEquals("\"json\\\"String\\\"\"", JsonSorter.getSortedJson(JsonSorter.toJson("json\"String\"")));
  }

  @Test
  public void testHomogeneousLists() {
    Assert.assertEquals("[1,2,3,4]", JsonSorter.getSortedJson(JsonSorter.toJson(Arrays.asList(4, 2, 1, 3))));
    Assert.assertEquals("[false,true]", JsonSorter.getSortedJson(JsonSorter.toJson(Arrays.asList(true, false))));
    Assert.assertEquals(
        getJson("['1','A','B','a','b','b']"),
        JsonSorter.getSortedJson(JsonSorter.toJson(Arrays.asList("A", "1", "b", "a", "B", "b"))));
  }

  @Test
  public void testHetrogeneousLists() {
    Assert.assertEquals(
        getJson("['A',1,2,3.1415,false,true]"),
        JsonSorter.getSortedJson(JsonSorter.toJson(Arrays.asList(2, 3.1415, true, "A", false, 1))));
  }

  @SuppressWarnings("serial")
  @Test
  public void testMap() {
    Assert.assertEquals(
        getJson("{'A':4,'B':3,'C':2,'D':1}"),
        JsonSorter.getSortedJson(new LinkedHashMap<String, Integer>() {
          {
            put("D", 1);
            put("C", 2);
            put("B", 3);
            put("A", 4);
          }
        }));
  }

  @Test
  public void testMapsInList() {
    Assert.assertEquals(
        getJson("[{'A':4},{'B':3},{'C':2},{'D':1}]"),
        JsonSorter.getSortedJson(
            Arrays.asList(//
                Collections.singletonMap("D", 1),
                Collections.singletonMap("C", 2),
                Collections.singletonMap("B", 3),
                Collections.singletonMap("A", 4)//
            )));
  }

  @Test
  public void testListsInMap() {
    Assert.assertEquals(
        getJson("{'boolean':[false,true],'int':[1,2,3,4],'string':['A','B','C','D']}"),
        JsonSorter.getSortedJson(getJson("{'int':[4,2,1,3],'boolean':[true,false],'string':['D','A','C','B']}")));
  }

  @SuppressWarnings("serial")
  @Test
  public void testNestedJson() {
    Assert.assertEquals(
        getJson(
            "{'companyName':'ExampleSoft','employees':[{'address':{'line1':'shivajinagar','pin':411023},'name':'Aakash','phones':['887656776','9876543210']},{'address':{'line1':'shivajinagar','pin':411023},'name':'Vijay','phones':['887656776','9876543210']},{'address':{'line1':'wakadewadi\\\\sangamwadi','pin':411029},'name':'Anand','phones':['788656776','9870993210']}]}"),
        JsonSorter.getSortedJson(new LinkedHashMap<String, Object>() {
          {
            put("companyName", "ExampleSoft");
            put("employees", new ArrayList<Object>() {
              {
                add(new LinkedHashMap<String, Object>() {
                  {
                    put("name", "Vijay");
                    put("address", new LinkedHashMap<String, Object>() {
                      {
                        put("line1", "shivajinagar");
                        put("pin", 411023);
                      }
                    });
                    put("phones", Arrays.asList("9876543210", "887656776"));
                  }
                });
                add(new LinkedHashMap<String, Object>() {
                  {
                    put("name", "Aakash");
                    put("address", new LinkedHashMap<String, Object>() {
                      {
                        put("line1", "shivajinagar");
                        put("pin", 411023);
                      }
                    });
                    put("phones", Arrays.asList("9876543210", "887656776"));
                  }
                });
                add(new LinkedHashMap<String, Object>() {
                  {
                    put("name", "Anand");
                    put("phones", Arrays.asList("9870993210", "788656776"));
                    put("address", new LinkedHashMap<String, Object>() {
                      {
                        put("line1", "wakadewadi\\sangamwadi");
                        put("pin", 411029);
                      }
                    });
                  }
                });
              }
            });
          }
        }));
  }


  public String getJson(final String singleQuoteJson) {
    return singleQuoteJson.replace("'", "\"");
  }
}
