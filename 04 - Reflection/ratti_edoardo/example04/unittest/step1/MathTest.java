package example04.unittest.step1;

public class MathTest {

    // library implementation

    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static int abs(int a) {
        return (a > 0) ? a : -a;
    }

    // library test methods

    public void testMax() {
        int max = max(1, 4);
        UnitTest.assertEquals(4, max);
    }

    public void testAbs() {
        int abs1 = abs(-128);
        UnitTest.assertEquals(128, abs1);

        int abs2 = abs(2);
        UnitTest.assertEquals(2, abs2);
    }

    public void testFail() {
        UnitTest.assertEquals(4, 100);
    }

    public void testError() {
        throw new RuntimeException("Sample error");
    }
}