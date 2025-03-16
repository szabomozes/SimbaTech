import core.Resource;
import panels.ToolBarPanel;
import panels.Window;

public class Main {
    public static void main(String[] args) {
        Resource.Instance.load();
        //new Window();
        new ToolBarPanel();
    }
}