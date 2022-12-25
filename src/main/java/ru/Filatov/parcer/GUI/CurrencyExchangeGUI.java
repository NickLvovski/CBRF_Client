package ru.Filatov.parcer.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import com.intellij.uiDesigner.core.*;
import ru.Filatov.parcer.Database;
import ru.Filatov.parcer.model.CurrencyExchange;
import ru.Filatov.parcer.repository.CurrencyExchangeRepository;
import ru.Filatov.parcer.repository.CurrencyExchangeRepositorySqlitempl;
import ru.Filatov.parcer.repository.Update;
import ru.Filatov.parcer.serialization.CsvSerializer;
import ru.Filatov.parcer.serialization.JsonSerializer;

public class CurrencyExchangeGUI extends JFrame{
    private static final String[] COLUMNS = { "Id", "Code", "Name", "Nominal", "Value", "Date" };
    Database db = Database.getInstance();
    public CurrencyExchangeGUI() {
        initComponents();
    }
    public static void main(String[] args){
        CurrencyExchangeGUI a = new CurrencyExchangeGUI();
        a.setVisible(true);
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
    private void buttonUpdate(ActionEvent e) {
        onUpdate();
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
    private void showAbout(ActionEvent e){

        JDialog dialog = new JDialog();
        dialog.setVisible(true);
        dialog.setSize(200, 70);
        dialog.setLocationRelativeTo(panel1);
        JLabel label = new JLabel("Made By Me");
        dialog.add(label);
        dialog.setResizable(false);
    }
    //GEN-BEGIN:variables  @formatter:off
    private void initComponents() {
        ResourceBundle bundle = ResourceBundle.getBundle("props");
        chooser = new JFileChooser();
        menuBar1 = new JMenuBar();
        menu3 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        aboutItem = new JMenuItem("Show info...");
        menu4 = new JMenu();
        panel1 = new JPanel();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = showData();
        //======== this ========
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {
            //======== menu3 ========
            {
                menu3.setText(bundle.getString("menu3.text"));

                //---- menuItem1 ----
                menuItem1.setText(bundle.getString("menuItem1.text"));
                menuItem1.addActionListener(this::jsonSave);
                menu3.add(menuItem1);
                //---- menuItem2 ----
                menuItem2.setText(bundle.getString("menuItem2.text"));
                menuItem2.addActionListener(this::csvSave);
                menu3.add(menuItem2);
            }
            menuBar1.add(menu3);

            //======== menu4 ========
            {
                menu4.setText(bundle.getString("menu4.text"));
                aboutItem.addActionListener(this::showAbout);
                menu4.add(aboutItem);
            }
            menuBar1.add(menu4);
        }
        setJMenuBar(menuBar1);
        //======== panel1 ========
        {
            panel1.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));

            //---- button1 ----
            button1.setText(bundle.getString("button1.text"));
            button1.addActionListener(this::buttonUpdate);
            panel1.add(button1, new GridConstraints(0, 0, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

            //======== scrollPane1 ========
            {
                //---- table1 ----
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, new GridConstraints(1, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu menu3;
    private JFileChooser chooser;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem aboutItem;
    private JMenu menu4;
    private JPanel panel1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
