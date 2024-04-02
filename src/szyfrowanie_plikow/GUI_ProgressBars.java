package szyfrowanie_plikow;

import javax.swing.*;
import java.awt.*;

public class GUI_ProgressBars {

    private int liczbaPlikow;
    private JProgressBar[] progressBars;

    public GUI_ProgressBars(int liczbaPlikow) {
        this.liczbaPlikow = liczbaPlikow;
        this.progressBars = new JProgressBar[liczbaPlikow];

        //=====GUI=================================================================================================
        JPanel panelProgressBars = new JPanel();
        panelProgressBars.setLayout(new GridLayout(liczbaPlikow, 1));
        progressBars = new JProgressBar[liczbaPlikow];

        for (int i = 0; i < liczbaPlikow; i++) {
            progressBars[i] = new JProgressBar(0, 100);
            progressBars[i].setValue(0);
            progressBars[i].setStringPainted(true);
            panelProgressBars.add(progressBars[i]);
        }
    }

//    public JProgressBar[] getProgressBars (int i) {
//        return this.progressBars[i];
//    }

}
