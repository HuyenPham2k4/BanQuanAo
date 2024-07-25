/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author letnp
 */
public class PieChart {
    DefaultPieDataset pieDataSet;
    JFreeChart pieChart;
    PiePlot piePlot;
    ChartPanel chartPanel;
    String title;
    public PieChart() {
        this.pieDataSet = new DefaultPieDataset();
    }
    public void setValue(String name, Number value){
        this.pieDataSet.setValue(name, value);
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void drawChart(JPanel jpanel){
        title = this.title;
        pieChart = ChartFactory.createPieChart(this.title, this.pieDataSet,true,true,false);
        piePlot = (PiePlot) pieChart.getPlot();
        chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(600, 500)); // Set preferred size
        jpanel.removeAll();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(chartPanel, BorderLayout.CENTER);
        jpanel.validate();
        jpanel.repaint();
    }
}
