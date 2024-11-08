import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Abhayjeet Singh, Wesam Khalil, Pravin Harikrishnan
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * Tests constructor.
     */
    @Test
    public final void construtorTest() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);
    }

    /**
     * Tests remove with 1 pair.
     */
    @Test
    public final void testRemoveEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("blue", "red");
        Map<String, String> sExpected = this.createFromArgsRef();

        /*
         * Call method under test
         */
        Map.Pair<String, String> r = s.remove("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(r.key(), "blue");
        assertEquals(r.value(), "red");
        assertEquals(s, sExpected);

    }

    /**
     * Tests remove with multiple pairs pair.
     */
    @Test
    public final void testRemove() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("blue", "red", "green",
                "yellow");
        Map<String, String> sExpected = this.createFromArgsRef("green",
                "yellow");

        /*
         * Call method under test
         */
        Map.Pair<String, String> r = s.remove("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(r.key(), "blue");
        assertEquals(r.value(), "red");
        assertEquals(s, sExpected);

    }

    /**
     * Tests value with 1 pair.
     */
    @Test
    public final void testValueOnePair() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("blue", "red");
        Map<String, String> sExpected = this.createFromArgsRef("blue", "red");

        /*
         * Call method under test
         */
        String sValue = s.value("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sValue, "red");
        assertEquals(s, sExpected);
    }

    /**
     * Tests value with multiple pairs.
     */
    @Test
    public final void testValueMultiplePairs() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("blue", "red", "green",
                "yellow");
        Map<String, String> sExpected = this.createFromArgsRef("blue", "red",
                "green", "yellow");

        /*
         * Call method under test
         */
        String sValue = s.value("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sValue, "yellow");
        assertEquals(s, sExpected);
    }

    /**
     * Tests value with multiple pairs with same value.
     */
    @Test
    public final void testValueSameValue() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("blue", "red", "green",
                "red");
        Map<String, String> sExpected = this.createFromArgsRef("blue", "red",
                "green", "red");

        /*
         * Call method under test
         */
        String sValue = s.value("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sValue, "red");
        assertEquals(s, sExpected);
    }

    /**
     * Tests size with 2 pairs.
     */
    @Test
    public final void testSize() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("blue", "red", "green",
                "red");

        /*
         * Call method under test
         */
        int sValue = s.size();
        final int sExcpectedVal = 2;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sValue, sExcpectedVal);
        // assertEquals(s, sExpected);
    }

    /**
     * Tests size with more than 2 pairs.
     */
    @Test
    public final void testSizeBig() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("blue", "red", "green",
                "red", "yellow", "purple", "sky blue", "orange", "brown",
                "teal blue");

        /*
         * Call method under test
         */
        int sValue = s.size();
        final int sExcpectedVal = 5;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sValue, sExcpectedVal);
//        assertEquals(s, sExpected);
    }

    /**
     * Tests hasKey with multiple pairs including key.
     */
    @Test
    public final void testHaskey() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("blue", "red", "green",
                "red", "yellow", "purple", "sky blue", "orange", "brown",
                "teal blue");

        /*
         * Call method under test
         */
        Boolean sValue = s.hasKey("blue");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sValue, true);
//        assertEquals(s, sExpected);
    }

    /**
     * Tests hasKey with multiple pairs not including key.
     */
    @Test
    public final void testHaskeyButNoKey() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("blue", "red", "green",
                "red", "yellow", "purple", "sky blue", "orange", "brown",
                "teal blue");

        /*
         * Call method under test
         */
        Boolean sValue = s.hasKey("dark red");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sValue, false);
//        assertEquals(s, sExpected);
    }

    /**
     * Tests hasKey with one pair and no key.
     */
    @Test
    public final void testHaskeyButNoKeySingleEntry() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("blue", "red");

        /*
         * Call method under test
         */
        Boolean sValue = s.hasKey("yellow");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sValue, false);
//        assertEquals(s, sExpected);
    }

    /**
     * Tests hasKey with one pair one includes key.
     */
    @Test
    public final void testHaskeyKeySingleEntry() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("blue", "red");

        /*
         * Call method under test
         */
        Boolean sValue = s.hasKey("blue");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sValue, true);
//        assertEquals(s, sExpected);
    }

    /**
     * Boundary test case, adding single key and value to map with zero entries.
     */
    @Test
    public final void testAddToEmptyMap() {

        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef("McDonald",
                "Burger");

        s.add("McDonald", "Burger");

        assertEquals(s, sExpected);
    }

    /**
     * Routine test case, adding single key and value to map with single
     * entries.
     */
    @Test
    public final void testAddToMapWithOnePair() {

        Map<String, String> s = this.createFromArgsTest("McDonald", "Burger");
        Map<String, String> sExpected = this.createFromArgsRef("McDonald",
                "Burger", "KCF", "Chicken");

        s.add("KCF", "Chicken");

        assertEquals(s, sExpected);
    }

    /**
     * Routine test case, adding single key and value to map with many entries.
     */
    @Test
    public final void testAddToMapWithManyPairs() {

        Map<String, String> s = this.createFromArgsTest("McDonald", "Burger",
                "KCF", "Chicken", "Taco Bell", "Taco");
        Map<String, String> sExpected = this.createFromArgsRef("McDonald",
                "Burger", "KCF", "Chicken", "Taco Bell", "Taco", "Dominos",
                "Pizza");

        s.add("Dominos", "Pizza");

        assertEquals(s, sExpected);
    }

    /**
     * Routine test case, adding multiple keys and values to map with zero
     * entries.
     */
    @Test
    public final void testAddMultipleAddsToEmpty() {

        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef("McDonald",
                "Burger", "KCF", "Chicken", "Taco Bell", "Taco", "Dominos",
                "Pizza");

        s.add("McDonald", "Burger");
        s.add("KCF", "Chicken");
        s.add("Taco Bell", "Taco");
        s.add("Dominos", "Pizza");

        assertEquals(s, sExpected);
    }

    /**
     * Routine test case, adding multiple keys and values to map with one
     * entries.
     */
    @Test
    public final void testAddMultipleAddsOnePairMap() {

        Map<String, String> s = this.createFromArgsTest("Dominos", "Pizza");
        Map<String, String> sExpected = this.createFromArgsRef("McDonald",
                "Burger", "KCF", "Chicken", "Taco Bell", "Taco", "Dominos",
                "Pizza");

        s.add("McDonald", "Burger");
        s.add("KCF", "Chicken");
        s.add("Taco Bell", "Taco");

        assertEquals(s, sExpected);
    }

    /**
     * Routine test case, adding multiple keys and values to map with many
     * entries.
     */
    @Test
    public final void testAddMultipleAddsManyPairMap() {

        Map<String, String> s = this.createFromArgsTest("McDonald", "Burger",
                "KCF", "Chicken", "Taco Bell", "Taco", "Dominos", "Pizza");
        Map<String, String> sExpected = this.createFromArgsRef("McDonald",
                "Burger", "KCF", "Chicken", "Taco Bell", "Taco", "Dominos",
                "Pizza", "Subway", "Sandwich", "Starbucks", "Coffee",
                "Olive Garden", "Salad");

        s.add("Subway", "Sandwich");
        s.add("Starbucks", "Coffee");
        s.add("Olive Garden", "Salad");

        assertEquals(s, sExpected);
    }

    /**
     * Challenging test case, adding keys and same value to empty map.
     */
    @Test
    public final void testAddTwoSameValuesToEmpty() {

        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef("Dominos",
                "Pizza", "Pizza Hut", "Pizza");

        s.add("Dominos", "Pizza");
        s.add("Pizza Hut", "Pizza");

        assertEquals(s, sExpected);
    }

    /**
     * Routine test case, adding single key and same value to multiple entries
     * map with same values.
     */
    @Test
    public final void testAddOneSameValuesToOnePairMap() {

        Map<String, String> s = this.createFromArgsTest("Dominos", "Pizza");
        Map<String, String> sExpected = this.createFromArgsRef("Dominos",
                "Pizza", "Pizza Hut", "Pizza");

        s.add("Pizza Hut", "Pizza");

        assertEquals(s, sExpected);
    }

    /**
     * Routine test case, adding multiple keys with same values.
     */
    @Test
    public final void testAddManySameValuesToManyPairMap() {

        Map<String, String> s = this.createFromArgsTest("Dominos", "Pizza",
                "Pizza Hut", "Pizza", "Papa John's", "Pizza");
        Map<String, String> sExpected = this.createFromArgsRef("Dominos",
                "Pizza", "Pizza Hut", "Pizza", "Papa John's", "Pizza",
                "Papa Murphy's", "Pizza", "Marco's Pizza", "Pizza",
                "Little Caesars", "Pizza");

        s.add("Little Caesars", "Pizza");
        s.add("Papa Murphy's", "Pizza");
        s.add("Marco's Pizza", "Pizza");

        assertEquals(s, sExpected);
    }

    /**
     * Boundary test case, where removing single key and value from one entry
     * map.
     */
    @Test
    public final void testRemoveAnyFromOnePairMap() {

        Map<String, String> s = this.createFromArgsTest("McDonald", "Burger");
        Map<String, String> sExpected = this.createFromArgsRef();

        Map.Pair<String, String> sPair = s.removeAny();

        assertEquals(sPair.key(), "McDonald");
        assertEquals(sPair.value(), "Burger");
        assertEquals(s, sExpected);
    }

    /**
     * Routine test case, where removing single key and value form multiple
     * entries map.
     */
    @Test
    public final void testRemoveAnyFromMultiplePairMap() {

        //Variables
        Map<String, String> s = this.createFromArgsTest("McDonald", "Burger",
                "Dominos", "Pizza", "Pizza Hut", "Pizza");
        Map<String, String> sExpected = this.createFromArgsRef("McDonald",
                "Burger", "Dominos", "Pizza", "Pizza Hut", "Pizza");

        //Call
        Map.Pair<String, String> sPair = s.removeAny();

        //Assertion if sExpected has that key
        assertEquals(sExpected.hasKey(sPair.key()), true);

        //Removing the key
        Map.Pair<String, String> sPairExpected = sExpected.remove(sPair.key());

        //Assertion
        assertEquals(sPair, sPairExpected);

        assertEquals(s, sExpected);
    }

    /**
     * Routine test case, where removing multiple keys and values.
     */
    @Test
    public final void testRemoveAnyFromMultipleManyPairsMap() {

        //Variables
        Map<String, String> s = this.createFromArgsTest("McDonald", "Burger",
                "Dominos", "Pizza", "Pizza Hut", "Pizza", "Subway", "Sandwich");
        Map<String, String> sExpected = this.createFromArgsRef("McDonald",
                "Burger", "Dominos", "Pizza", "Pizza Hut", "Pizza", "Subway",
                "Sandwich");

        //Call
        Map.Pair<String, String> sPair = s.removeAny();

        //Assertion if sExpected has that key
        assertEquals(sExpected.hasKey(sPair.key()), true);

        //Removing the key
        Map.Pair<String, String> sPairExpected = sExpected.remove(sPair.key());

        //Assertion
        assertEquals(sPair, sPairExpected);

        //Call
        Map.Pair<String, String> s2Pair = s.removeAny();

        //Assertion if sExpected has that key
        assertEquals(sExpected.hasKey(s2Pair.key()), true);

        //Removing the key
        Map.Pair<String, String> s2PairExpected = sExpected
                .remove(s2Pair.key());

        //Assertion
        assertEquals(s2Pair, s2PairExpected);

        assertEquals(s, sExpected);
    }

}
