/*
 * Created by JFormDesigner on Mon Dec 26 00:12:14 MSK 2022
 */

package ru.Filatov.parcer.GUI;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import com.intellij.uiDesigner.core.*;
import net.miginfocom.swing.*;
import ru.Filatov.parcer.Database;
import ru.Filatov.parcer.model.CurrencyExchange;
import ru.Filatov.parcer.repository.CurrencyExchangeRepository;
import ru.Filatov.parcer.repository.CurrencyExchangeRepositorySqlitempl;
import ru.Filatov.parcer.repository.Update;
import ru.Filatov.parcer.serialization.CsvSerializer;
import ru.Filatov.parcer.serialization.JsonSerializer;

/**
 * @author nickl
 */
public class CurrExchangeGUI extends JFrame {
    private static final String[] COLUMNS = { "Id", "Code", "Name", "Nominal", "Value", "Date" };
    Database db = Database.getInstance();
    public CurrExchangeGUI() {
        initComponents();
    }

    public static void main(String[] args) {
        CurrExchangeGUI gui = new CurrExchangeGUI();
        gui.setVisible(true);
    }

    private void FileChoosing(ActionEvent e) {
        // TODO add your code here
    }

    private String[][] getData(){
        Database db = Database.getInstance();
        CurrencyExchangeRepository repo = new CurrencyExchangeRepositorySqlitempl(db);
        List<CurrencyExchange> exchangeList = repo.findAll();
        String[][] res = new String[exchangeList.size()][];
        for(int i = 0; i < exchangeList.size(); i++){
            res[i] = new String[]
                    {
                            String.valueOf(exchangeList.get(i).getId()),
                            exchangeList.get(i).getCurrencyCode(),
                            exchangeList.get(i).getCurrencyName(),
                            String.valueOf(exchangeList.get(i).getNominal()),
                            String.valueOf(exchangeList.get(i).getValue()),
                            exchangeList.get(i).getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    };
        }
        return res;
    }

    private JTable showData(){
        return new JTable(getData(), COLUMNS);
    }

    private void updateData(String date){
        CurrencyExchangeRepositorySqlitempl repo = new CurrencyExchangeRepositorySqlitempl(db);
        Update updater = new Update(repo);
        updater.UpdateDB(date);
        String[][] data = getData();
        for(int i = 0; i < table1.getRowCount(); i++){
            for(int j = 0; j < 6; j++){
                table1.setValueAt(data[i][j], i, j);
            }
        }
    }

    private void onUpdate(){
        updateData(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

    private void jsonSave(ActionEvent e) {
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".json");
                }
            }
            @Override
            public String getDescription() {
                return ".json";
            }
        });
        chooser.showSaveDialog(menuItem1);
        File file = chooser.getSelectedFile();
        JsonSerializer serializer = JsonSerializer.getInstance();
        String path = file.getAbsolutePath();
        CurrencyExchangeRepositorySqlitempl repo = new CurrencyExchangeRepositorySqlitempl(db);
        serializer.serialize(repo.findAll(), path);
    }

    private void csvSave(ActionEvent e) {
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".csv");
                }
            }
            @Override
            public String getDescription() {
                return ".csv";
            }
        });
        chooser.showSaveDialog(menuItem2);
        File file = chooser.getSelectedFile();
        String path = file.getPath();
        CsvSerializer serializer = CsvSerializer.getInstance();
        CurrencyExchangeRepositorySqlitempl repo = new CurrencyExchangeRepositorySqlitempl(db);
        serializer.serialize(repo.findAll(), path);
    }

    private void AboutInfo(ActionEvent e) {
        // TODO add your code here
    }

    private void showAbout(ActionEvent e){

        JDialog dialog = new JDialog();
        dialog.setVisible(true);
        dialog.setSize(200, 70);
        dialog.setLocationRelativeTo(panel1);
        JLabel label = new JLabel("Made By Me");
        dialog.add(label);
        dialog.setResizable(false);
    }

    private void buttonUpdate(ActionEvent e) {
        onUpdate();
    }

    private void table1PropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("props");
        menuBar1 = new JMenuBar();
        menu3 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu4 = new JMenu();
        panel1 = new JPanel();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = showData();
        chooser = new JFileChooser();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]"));

        //======== menuBar1 ========
        {

            //======== menu3 ========
            {
                menu3.setText(bundle.getString("CurrExchangeGUI.menu3.text_2"));

                //---- menuItem1 ----
                menuItem1.setText(bundle.getString("CurrExchangeGUI.menuItem1.text_2"));
                menuItem1.addActionListener(e -> FileChoosing(e));
                menu3.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText(bundle.getString("CurrExchangeGUI.menuItem2.text_3"));
                menuItem2.addActionListener(e -> csvSave(e));
                menu3.add(menuItem2);
            }
            menuBar1.add(menu3);

            //======== menu4 ========
            {
                menu4.setText(bundle.getString("CurrExchangeGUI.menu4.text_2"));
                menu4.addActionListener(e -> {
			AboutInfo(e);
			showAbout(e);
			showAbout(e);
		});
            }
            menuBar1.add(menu4);
        }
        contentPane.add(menuBar1, "cell 0 0");

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));

            //---- button1 ----
            button1.setText(bundle.getString("CurrExchangeGUI.button1.text_3"));
            button1.addActionListener(e -> buttonUpdate(e));
            panel1.add(button1, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.addPropertyChangeListener(e -> table1PropertyChange(e));
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, new GridConstraints(1, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                null, null, null));
        }
        contentPane.add(panel1, "cell 0 1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu menu3;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenu menu4;
    private JPanel panel1;
    private JFileChooser chooser;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
