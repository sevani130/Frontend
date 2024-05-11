package intensive_61.Task2.src;
import intensive_61.Task2.IntensiveComponent_NikitaKudryashov;

@IntensiveComponent_NikitaKudryashov
public class SomeInterfaceImpl implements SomeInterface {
    @Override
    public void run() {
        System.out.println("The context is working correctly, the SomeInterface object was created and executed.");
    }
}