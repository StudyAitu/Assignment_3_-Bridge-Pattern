public class Main {
    private static int passed;

    public static void main(String[] args) {
        if (args.length != 1 || !"--demo".equals(args[0])) {
            System.out.println("Usage: java -cp out Main --demo");
            return;
        }

        passed = 0;
        test("T1", "Circle + VectorRenderer", "VECTOR circle radius=2",
                new Circle("C1", 2, new VectorRenderer()).execute());

        test("T2", "Circle + RasterRenderer", "RASTER circle radius=2",
                new Circle("C1", 2, new RasterRenderer()).execute());

        test("T3", "Square + VectorRenderer", "VECTOR square side=3",
                new Square("S1", 3, new VectorRenderer()).execute());

        test("T4", "Square + RasterRenderer", "RASTER square side=3",
                new Square("S1", 3, new RasterRenderer()).execute());

        Circle circle = new Circle("C5", 2, new VectorRenderer());
        Shape originalReference = circle;
        String before = circle.execute();
        String originalId = circle.getId();
        int originalRadius = circle.getRadius();
        circle.setImplementation(new RasterRenderer());
        Shape afterReference = circle;
        String after = circle.execute();
        boolean sameObject = originalReference == afterReference;
        boolean stateUnchanged = originalId.equals(circle.getId()) && originalRadius == circle.getRadius();
        boolean resultChanged = !before.equals(after);
        boolean t5Pass = sameObject && stateUnchanged
                && "VECTOR circle radius=2".equals(before)
                && "RASTER circle radius=2".equals(after)
                && resultChanged;
        if (t5Pass) {
            passed++;
        }
        printResult("T5", t5Pass, "Circle runtime switch",
                "sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged
                        + " | before=" + before + " | after=" + after);

        test("T6", "Circle + AsciiRenderer", "ASCII circle radius=2",
                new Circle("C6", 2, new AsciiRenderer()).execute());

        test("T7", "Square + AsciiRenderer", "ASCII square side=3",
                new Square("S7", 3, new AsciiRenderer()).execute());

        System.out.println("SUMMARY: " + passed + "/7 PASS");
    }

    private static void test(String id, String classes, String expected, String actual) {
        boolean ok = expected.equals(actual);
        if (ok) {
            passed++;
        }
        printResult(id, ok, classes, "result=" + actual + (ok ? "" : " | expected=" + expected));
    }

    private static void printResult(String id, boolean ok, String details, String result) {
        System.out.println(id + " " + (ok ? "PASS" : "FAIL") + " | " + details + " | " + result);
    }
}