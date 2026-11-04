package example06.nested.staticnested;

public class OuterClass {
    private static void externalStaticMethod() {
        System.out.println("Execute static operation");
    }

    private void externalInstanceMethod() {
        System.out.println("Execute instance operation");
    }

    private static class StaticNestedClass {
        private static void internalStaticMethod() {
            externalStaticMethod();
            // externalInstanceMethod();
        }

        private void internalInstanceMethod() {
            externalStaticMethod();
            // externalInstanceMethod();
        }
    }
}
