package org.sniff.cache.map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sniff.cache.template.CacheTemplate;
import org.sniff.mode.Staff;
import org.sniff.mode.StaffBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * @auth snifferhu
 * @date 2017/4/20 22:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
/** 声明spring主配置文件位置，注意：以当前测试类的位置为基准,有多个配置文件以字符数组声明 **/
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class CacheMapImplTest {
    @Autowired
    private CacheTemplate template;

    private static Staff staff;

    private static final String cacheKey = "staff#Leo";

    @BeforeClass
    public static void init() {
        staff = new StaffBuilder()
                .setId(1000000L)
                .setName("Leo")
                .setWage(1800.001D)
                .setCreateTime(new Date(1492817472001L))
                .createStaff();

    }


    @Test
    public void setCacheKey() throws Exception {
        template.getMapOperation(cacheKey).clear();
        boolean result = template.getMapOperation(cacheKey).put("id", staff.getId());
        assertTrue("setCacheKey", result);
    }

    @Test
    public void getCacheKey() throws Exception {
        Long id = template.getMapOperation(cacheKey).get("id", Long.class);
        assertEquals("getCacheKey", staff.getId(), id);
    }


    @Test
    public void size() throws Exception {
        Long size = template.getMapOperation(cacheKey).size();
        assertEquals("size", 1L, size);
    }

    @Test
    public void isEmpty() throws Exception {
        Boolean isEmpty = template.getMapOperation(cacheKey).isEmpty();
        assertTrue("isEmpty", isEmpty);
    }

    @Test
    public void containsKey() throws Exception {
        Boolean isContain = template.getMapOperation(cacheKey).containsKey("id");
        assertTrue("containsKey", isContain);
    }

    @Test
    public void put() throws Exception {
        boolean result = template.getMapOperation(cacheKey).put("leo", staff);
        assertTrue("put", result);
    }

    @Test
    public void get() throws Exception {
        Staff result = template.getMapOperation(cacheKey).get("leo", Staff.class);
        assertEquals("get object staff id", staff.getId(), result.getId());
        assertEquals("get object staff name", staff.getName(), result.getName());
        assertEquals("get object staff wage", staff.getWage(), result.getWage());
    }

    @Test
    public void get1() throws Exception {
        assertNotNull("get string", template.getMapOperation(cacheKey).get("leo"));
    }


    @Test
    public void remove() throws Exception {
        boolean result = template.getMapOperation(cacheKey).remove("leo");
        assertTrue("remove", result);
    }

    @Test
    public void putAll() throws Exception {
        template.getMapOperation(cacheKey).clear();
        template.getMapOperation(cacheKey).putAllForObj(staff);
        Staff result = template.getMapOperation(cacheKey).getAllForObj(staff.getClass());
        assertEquals("get object staff id", staff.getId(), result.getId());
        assertEquals("get object staff name", staff.getName(), result.getName());
        assertEquals("get object staff wage", staff.getWage(), result.getWage());
        assertEquals("get object staff time", staff.getCreateTime().getTime(), result.getCreateTime().getTime());
    }


    @Test
    public void keySet() throws Exception {
        Set<String> keySet = template.getMapOperation(cacheKey).keySet();
        for (String key : keySet) {
            System.out.println(key);
        }
    }

    @Test
    public void keySet1() throws Exception {
        Set<String> keySet = template.getMapOperation(cacheKey).keySet(String.class);
        for (String key : keySet) {
            System.out.println(key);
        }
    }

    @Test
    public void values() throws Exception {
        Collection<String> values = template.getMapOperation(cacheKey).values();
        for (String value : values) {
            System.out.println(value);
        }
    }

    @Test
    public void values1() throws Exception {
        template.getMapOperation(cacheKey).clear();
        template.getMapOperation(cacheKey).put(1, staff);
        template.getMapOperation(cacheKey).put(2, staff);
        Collection<Staff> list = template.getMapOperation(cacheKey).values(Staff.class);
        assertEquals("", 2, list.size());
        template.getMapOperation(cacheKey).clear();
    }

    @Test
    public void containsValue() throws Exception {
        boolean isContain = template.getMapOperation(cacheKey).containsValue(staff.getCreateTime());
        System.out.println(isContain);
    }

    @Test
    public void getAll() throws Exception {
        List<String> results = template.getMapOperation(cacheKey).getAll(String.class, "id", "name");
        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void putIfAbsent() throws Exception {
        boolean ifn = template.getMapOperation(cacheKey).put("id", 12);
        System.out.println(ifn);
    }

    @Test
    public void clear() throws Exception {
        template.getMapOperation(cacheKey).clear();
    }

    @Test
    public void testTimeUtils() {
        System.out.println(TimeUnit.DAYS.toSeconds(1));
    }

    @Test
    public void testIncrby(){
        System.out.println(template.getMapOperation(cacheKey).incrBy("12",1));
    }
}