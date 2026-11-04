package example06.nested;

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

    public static void main2(String[] args) {
        OuterClass.externalStaticMethod();

        OuterClass outerClass = new OuterClass();
        outerClass.externalInstanceMethod();

        InnerClass innerClass;
        // innerClass = new OuterClass.InnerClass(); // Cannot be referenced from a static context
        innerClass = outerClass.new InnerClass(); // ???
        innerClass.nestedInstanceMethod();

        // Equivalent calls
        innerClass.nestedStaticMethod(); //
        InnerClass.nestedStaticMethod(); //

    }

    public static void main(String[] args) {

        // ============================================================
        // 1. OUTER CLASS
        // ============================================================

        // Static method:
        // Can be called through the class.
        OuterClass.externalStaticMethod();

        // Static method can also technically be called through an instance, but this is discouraged: use OuterClass.externalStaticMethod()
        OuterClass outerClass = new OuterClass();
        outerClass.externalStaticMethod(); // DON'T do this.

        // Instance method:
        // Requires an OuterClass object.
        outerClass.externalInstanceMethod();

        // OuterClass.externalInstanceMethod();
        // ERROR: Cannot call an instance method without an object.


        // ============================================================
        // 2. STATIC NESTED CLASS
        // ============================================================

        // StaticNestedClass is static, so it does NOT require an OuterClass object.
        StaticNestedClass staticNestedClass = new StaticNestedClass();

        // ---- Static method inside StaticNestedClass ----

        StaticNestedClass.internalStaticMethod();


        // Static method can also technically be called through an instance, but this is discouraged: use StaticNestedClass.internalStaticMethod()
        staticNestedClass.internalStaticMethod(); // DON'T do this.

        // ---- Instance method inside StaticNestedClass ----

        staticNestedClass.internalInstanceMethod();  // ✅

        // StaticNestedClass.internalInstanceMethod(); // ❌
        // Instance method
        // requires an object.


        // ============================================================
        // 3. NON-STATIC INNER CLASS
        // ============================================================

        // InnerClass is NOT static.
        //
        // Therefore, it must be associated with a particular
        // OuterClass instance.
        //
        // This is the important syntax:
        InnerClass innerClass = outerClass.new InnerClass();          // ✅

        // Equivalent fully-qualified form:
        InnerClass innerClass2 = outerClass.new InnerClass();          // ✅

        // ------------------------------------------------------------
        // Instance method of InnerClass
        // ------------------------------------------------------------

        // Requires an InnerClass object.
        innerClass.nestedInstanceMethod();            // ✅

        // Cannot call an instance method through the class:
        // OuterClass.InnerClass.nestedInstanceMethod(); // ❌


        // ------------------------------------------------------------
        // Static method of InnerClass
        // ------------------------------------------------------------

        // Because nestedStaticMethod() is static, it belongs to
        // InnerClass itself, not to an InnerClass object.
        InnerClass.nestedStaticMethod();   // ✅

        // You can also call it through an instance:
        //
        // This compiles, but is discouraged because the method is static.
        innerClass.nestedStaticMethod();              // ⚠️ Compiles
        // but prefer:
        // InnerClass.nestedStaticMethod()

        // In this context, these are equivalent:
        innerClass.nestedStaticMethod();
        InnerClass.nestedStaticMethod();
        // But the second form communicates the intention better.


        // ============================================================
        // 4. WHAT INNER CLASS METHODS CAN ACCESS
        // ============================================================

        // nestedStaticMethod() can access static members of OuterClass:
        InnerClass.nestedStaticMethod();   // ✅

        // Inside nestedStaticMethod():
        //
        // externalStaticMethod();                    // ✅
        //
        // But:
        //
        // externalInstanceMethod();                  // ❌
        //
        // Why?
        //
        // A static method doesn't have an OuterClass instance
        // (`this`) available.


        // nestedInstanceMethod(), however, has an associated
        // OuterClass instance.
        innerClass.nestedInstanceMethod();            // ✅

        // Inside nestedInstanceMethod():
        //
        // externalStaticMethod();                    // ✅
        // externalInstanceMethod();                  // ✅
        //
        // because the inner object is associated with `outerClass`.


        // ============================================================
        // 5. STATIC NESTED CLASS AND OUTER INSTANCE METHODS
        // ============================================================

        // StaticNestedClass does NOT automatically have an
        // OuterClass instance.
        //
        // Therefore, inside:
        //
        // internalStaticMethod()
        //
        // this is allowed:
        //
        // externalStaticMethod();                    // ✅
        //
        // but this is NOT:
        //
        // externalInstanceMethod();                  // ❌
        //
        // The same applies to internalInstanceMethod().
        //
        // A static nested class is not associated with an
        // OuterClass object.


        // ============================================================
        // 6. SUMMARY OF THE IMPORTANT INSTANTIATIONS
        // ============================================================

        // Normal OuterClass:
        OuterClass outer = new OuterClass();

        // Static nested class:
        StaticNestedClass staticNested = new StaticNestedClass();

        // Non-static inner class:
        InnerClass inner = outer.new InnerClass();

        // These are NOT valid:

        // OuterClass.InnerClass inner = new OuterClass.InnerClass();
        // ERROR: InnerClass requires an OuterClass instance.

        // OuterClass.StaticNestedClass staticNested = outer.new StaticNestedClass();
        // ERROR: StaticNestedClass is static. It is created independently of an OuterClass instance.
    }

}
