package example06.nested.inner;

public class OuterClass {
    private static void externalStaticMethod() {
        System.out.println("Execute static operation");
    }

    private void externalInstanceMethod() {
        System.out.println("Execute instance operation");
    }

    private class InnerClass {
        private static void nestedStaticMethod() {
            externalStaticMethod();
            // externalInstanceMethod();
        }

        private void nestedInstanceMethod() {
            externalStaticMethod();
            externalInstanceMethod();
        }
    }
}
