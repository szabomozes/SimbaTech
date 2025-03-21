import core.Resources;
import panels.Window;

public class Main {
    public static void main(String[] args) {
        Resources.Instance.load();
        new Window();
    }
}