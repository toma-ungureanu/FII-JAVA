import javax.swing.*;
import java.awt.*;

/**
 * @author Toma-Florin Ungureanu
 */
public class Toolbar extends JPanel
{
    private final DrawingFrame frame;
    private JButton addButton = new JButton("Add a vertex");
    private JTextField size = new JTextField();

    public Toolbar(DrawingFrame frame)
    {
        this.frame = frame;
        init();
    }

    private void init()
    {
        size.setPreferredSize(new Dimension(250,26));
        add(addButton);
        add(size);
    }
}
