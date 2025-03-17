import core.Resources;
import panels.Window;
import panels.game.ToolBarPanel;

public class Main {
    public static void main(String[] args) {
        Resources.Instance.load();
        new Window();
        //new ToolBarPanel();
    }
}