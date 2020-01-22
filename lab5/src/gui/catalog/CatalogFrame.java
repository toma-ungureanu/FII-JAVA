package gui.catalog;

import gui.controlpanel.ControlPanel;
import gui.graph.GraphForm;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

/**
 * @author Toma-Florin Ungureanu
 */
public class CatalogFrame extends JFrame
{
    private GraphForm form;
    public CatalogList list;
    private ControlPanel control;
    final JPopupMenu popupMenu = new JPopupMenu();

    /**
     *
     */
    public CatalogFrame()
    {
        super("Visual Graph Manager");
        init();
    }

    private void addPopupListener()
    {

        popupMenu.addPopupMenuListener(new PopupMenuListener()
        {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
                SwingUtilities.invokeLater(() ->
                {
                    int rowAtPoint = list.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), list));
                    if (rowAtPoint > -1)
                    {
                        list.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}


        });
    }

    /**
     *
     */
    private void init()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1200, 500);
        list = new CatalogList();
        control = new ControlPanel(this);
        form = new GraphForm(this);
        addPopupListener();
        JMenuItem checkBip = new JMenuItem("Check Bipartite");
        JMenuItem checkDiameter = new JMenuItem("Check Diameter");
        JMenuItem search = new JMenuItem("Search on the web");


        list.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int r = list.rowAtPoint(e.getPoint());
                if (r >= 0 && r < list.getRowCount())
                {
                    list.setRowSelectionInterval(r, r);
                } else
                {
                    list.clearSelection();
                }
            }
        });

        checkBip.addActionListener((ActionEvent e) ->
        {
            String graphName = (String) list.getValueAt(list.getSelectedRow(), 0);
            boolean biparite = false;
            if (biparite)
            {
                JOptionPane.showMessageDialog(null, "YEY!");
            } else
            {
                JOptionPane.showMessageDialog(null, "NEY!");
            }
            System.out.println(graphName);
        });

        checkDiameter.addActionListener((ActionEvent e) ->
        {
            String graphName = (String) list.getValueAt(list.getSelectedRow(), 0);
            int diameter = 2;
            JOptionPane.showMessageDialog(null, "Diameter is: " + diameter);
            System.out.println(graphName);
        });

        search.addActionListener((ActionEvent e) ->
        {
            try
            {
                Document doc = Jsoup.connect("https://www.google.com/search?q=" + (String) list.getValueAt(list.getSelectedRow(), 0) + "_graph")
                        .userAgent("Mozilla/5.0")
                        .timeout(5000)
                        .get();
                System.out.println(doc);

                try
                {
                    String html = doc.toString();
                    File searchFile = new File("C:\\Users\\thomi\\IdeaProjects\\lab5\\src");
                    searchFile.createNewFile();
                    FileOutputStream fileOut = new FileOutputStream(searchFile.getPath() + "\\file.html");
                    PrintWriter writer = new PrintWriter(fileOut);
                    writer.write(html.toString());
                    writer.close();
                    fileOut.close();
                    System.out.println("Report was saved in: " + searchFile.getPath());
                } catch (IOException excp)
                {
                    System.out.println(excp.getMessage() + " " + excp.getCause());
                    System.exit(1);
                }
            }
            catch (IOException excp)
            {
                System.exit(1);
            }
        });


        popupMenu.add(checkBip);
        popupMenu.add(checkDiameter);
        popupMenu.add(search);
        list.setComponentPopupMenu(popupMenu);
        this.getContentPane().add(new JScrollPane(list), BorderLayout.CENTER);
        this.getContentPane().add(form, BorderLayout.NORTH);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }


}