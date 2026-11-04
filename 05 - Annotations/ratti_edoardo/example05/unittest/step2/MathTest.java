package example05.unittest.step2;

public class MathTest {

    // library implementation

    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static int abs(int a) {
        return (a > 0) ? a : -a;
    }

    // library test methods

    // @Test(description=...) opts this method into the test suite.
    @Test(testDescription = "Test the max method")
    public void testMax() {
        int max = max(1, 4);
        UnitTest.assertEquals(4, max);
    }

    // No @Test → framework skips this method entirely.
    public void testAbs() {
        int abs1 = abs(-128);
        UnitTest.assertEquals(128, abs1);

        int abs2 = abs(2);
        UnitTest.assertEquals(2, abs2);
    }

    @Test(testDescription = "Fail due to assertion")
    public void testFail() {
        UnitTest.assertEquals(4, 100);
    }

    @Test(testDescription = "Error due to exception")
    public void testError() {
        throw new RuntimeException("Sample error");
    }
}