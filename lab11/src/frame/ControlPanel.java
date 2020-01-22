package frame;

import com.sun.jdi.InvocationException;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Toma-Florin Ungureanu
 */
public class ControlPanel extends JPanel
{
    private final MainFrame frame;
    private final JLabel classNameLabel = new JLabel("Class name");
    private final JTextField classNameField = new JTextField(30);
    private final JLabel textLabel = new JLabel("Default text");
    private final JTextField textField = new JTextField(10);
    private final JButton createButton = new JButton("Add component");

    public ControlPanel(MainFrame frame)
    {
        this.frame = frame;
        init();
    }

    private void init()
    {
        add(classNameLabel);
        add(classNameField);
        add(textLabel);
        add(textField);
        add(createButton);
        createButton.addActionListener(e ->
        {
            JComponent comp = createDynamicComponent(classNameField.getText());
            setComponentText(comp, textField.getText());
            frame.designPanel.addAtRandomLocation(comp);
        });
    }

    private JComponent createDynamicComponent(String className)
    {
        try
        {
            Class clazz = Class.forName(className);
            return (JComponent) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException |
                NoSuchMethodException |
                InstantiationException |
                IllegalArgumentException |
                InvocationTargetException |
                IllegalAccessException excp)
        {
            System.out.println(excp.getCause() + " " + excp.getStackTrace());
            System.exit(1);
            return null;
        }
    }

    private void setComponentText(JComponent comp, String text)
    {
        try
        {
            Method[] methods = comp.getClass().getMethods();
            for (Method method : methods)
            {
                if (method.getName().equals("setText"))
                {
                    method.invoke(comp, text);
                }
            }
        }
        catch (  IllegalArgumentException |
                InvocationTargetException |
                IllegalAccessException excp)
        {
            System.out.println(excp.getCause() + " " + excp.getStackTrace());
            System.exit(1);
        }
    }
}