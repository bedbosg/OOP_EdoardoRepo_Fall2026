package example04.unittest.step1;

public class UnitTest {
    public static void assertEquals(Object expected, Object actual) {
        if ((expected == null) && (actual == null)) {
            return;
        }
        if ((expected != null) && expected.equals(actual)) {
            return;
        }
        throw new AssertionError("The object is not equal [expected: <" + expected + ">, actual: <" + actual + ">]");
    }
}