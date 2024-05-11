package intensive_61.Task2.src;

import intensive_61.Task2.IntensiveContext_NikitaKudryashov;

public class Main {
    public static void main(String[] args) {
        try {
            IntensiveContext_NikitaKudryashov context = new IntensiveContext_NikitaKudryashov("task2.test");
            SomeInterface obj = context.getObject(SomeInterface.class);

            obj.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
