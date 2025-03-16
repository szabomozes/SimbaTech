import core.Resource;
import panels.game.ToolBarPanel;

public class Main {
    public static void main(String[] args) {
        Resource.Instance.load();
        //new Window();
        new ToolBarPanel();
    }
}