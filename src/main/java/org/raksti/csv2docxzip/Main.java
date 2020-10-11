package org.raksti.csv2docxzip;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Main {

    public static void main(String... args) throws Exception {
        Controller controller = new Controller();

        if (args.length != 1) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV exported data", "csv"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("All files", "*.*"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setDialogTitle("Select exported CSV data file");

            int selected = fileChooser.showDialog(null, "Convert");
            if (selected == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                controller.process(file);
            }
        } else {
            File file = new File(args[0]);
            controller.process(file);
        }
    }

}
