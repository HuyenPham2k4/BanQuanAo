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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author letnp
 */
public class ColumnChart {
    DefaultCategoryDataset categoryDataset;
    JFreeChart columnChart;
    CategoryPlot categoryPlot;
    ChartPanel chartPanel;
    String title;
    String category;
    String value;
    
    public ColumnChart(String title, String category, String value) {
        this.categoryDataset = new DefaultCategoryDataset();
        this.title = title;
        this.category = category;
        this.value = value;
    }

    public void setValue(String category, Number value) {
        this.categoryDataset.setValue(value, 1, category);
    }
    public void drawChart(JPanel jpanel) {
        title = this.title;
        columnChart = ChartFactory.createBarChart(this.title, this.category, this.value, categoryDataset);
        categoryPlot = columnChart.getCategoryPlot();
        chartPanel = new ChartPanel(columnChart);
        chartPanel.setPreferredSize(new Dimension(jpanel.getWidth(), jpanel.getHeight())); // Set preferred size
        jpanel.removeAll();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(chartPanel, BorderLayout.CENTER);
        jpanel.validate();
        jpanel.repaint();
    }
}
