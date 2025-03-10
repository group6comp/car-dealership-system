package carDealership;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The GenerateReportsPanel class is used to generate and display various reports for the dealership.
 */
public class GenerateReportsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Dealership m_dealership = Main.m_dealership;
    private JPanel report;

    /**
     * Create the panel.
     */
    public GenerateReportsPanel() {

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        // Add title label
        JLabel lblTitle = new JLabel("Manage Inventory");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        // Add scroll pane for the report
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        // Create the report panel
        report = new JPanel(new GridBagLayout());
        scrollPane.setViewportView(report);

        // Generate the report
        generateReport();

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add buttons to the button panel
        addButton("Save Report as Text File", buttonPanel, gbc, 0, 0, e -> saveReportAsTextFile(generateSalespersonReport()));
        addButton("Back", buttonPanel, gbc, 1, 0, e -> Main.showMainUI());
    }

    /**
     * Generate the report and add it to the report panel.
     */
    private void generateReport() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Section 1: Graphs
        JLabel lblGraphs = new JLabel("Sales Graphs");
        lblGraphs.setFont(new Font("Dubai Medium", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        report.add(lblGraphs, gbc);

        // Sales Over Time Graph
        JPanel salesOverTimeGraph = createSalesOverTimeGraph();
        gbc.gridy = 1;
        report.add(salesOverTimeGraph, gbc);

        // Sales by Manufacturer Graph
        JPanel salesByManufacturerGraph = createSalesByManufacturerGraph();
        gbc.gridy = 2;
        report.add(salesByManufacturerGraph, gbc);

        // Section 2: Salesperson Report
        JLabel lblSalespersonReport = new JLabel("Salesperson Report");
        lblSalespersonReport.setFont(new Font("Dubai Medium", Font.PLAIN, 18));
        gbc.gridy = 3;
        report.add(lblSalespersonReport, gbc);

        JTextArea salespersonReport = new JTextArea(generateSalespersonReport());
        salespersonReport.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(salespersonReport);
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        report.add(scrollPane, gbc);

        JScrollPane reportScrollPane = new JScrollPane(report);
        reportScrollPane.setPreferredSize(new Dimension(600, 400));

        add(reportScrollPane, BorderLayout.CENTER);
    }

    /**
     * Create the Sales Over Time graph panel.
     * 
     * @return the Sales Over Time graph panel
     */
    private JPanel createSalesOverTimeGraph() {
        Map<LocalDate, Integer> salesOverTime = new HashMap<>();
        for (Sale sale : m_dealership.getSales()) {
            LocalDate saleDate = sale.getSaleDate();
            salesOverTime.put(saleDate, salesOverTime.getOrDefault(saleDate, 0) + 1);
        }

        return new BarChartPanel(salesOverTime, "Sales Over Time", "Date", "Sales");
    }

    /**
     * Create the Sales by Manufacturer graph panel.
     * 
     * @return the Sales by Manufacturer graph panel
     */
    private JPanel createSalesByManufacturerGraph() {
        Map<String, Integer> salesByManufacturer = new HashMap<>();
        for (Sale sale : m_dealership.getSales()) {
            String manufacturer = sale.getVehicle().getMake();
            salesByManufacturer.put(manufacturer, salesByManufacturer.getOrDefault(manufacturer, 0) + 1);
        }

        return new BarChartPanel(salesByManufacturer, "Sales by Manufacturer", "Manufacturer", "Sales");
    }

    /**
     * Generate the salesperson report.
     * 
     * @return the salesperson report as a string
     */
    private String generateSalespersonReport() {
        List<Sale> sales = m_dealership.getSales();
        Map<String, SalespersonStats> statsMap = new HashMap<>();

        for (Sale sale : sales) {
            User salesperson = sale.getSalesperson();
            SalespersonStats stats = statsMap.getOrDefault(salesperson.toString(), new SalespersonStats());
            stats.incrementSalesCount();
            stats.addSalesAmount(sale.getVehicle().getPrice());
            statsMap.put(salesperson.toString(), stats);
        }

        StringBuilder report = new StringBuilder();
        report.append("Salesperson Report\n\n");
        for (Map.Entry<String, SalespersonStats> entry : statsMap.entrySet()) {
            report.append("Salesperson: ").append(entry.getKey()).append("\n")
                .append("Number of Sales: ").append(entry.getValue().getSalesCount()).append("\n")
                .append("Total Sales Amount: $").append(entry.getValue().getSalesAmount()).append("\n\n");
        }

        return report.toString();
    }

    /**
     * Save the report as a text file.
     * 
     * @param reportContent the content of the report
     */
    private void saveReportAsTextFile(String reportContent) {
        try (FileWriter writer = new FileWriter("SalesReport.txt")) {
            writer.write(reportContent);
            JOptionPane.showMessageDialog(this, "Report saved as SalesReport.txt", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving report as text file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Class to hold salesperson statistics.
     */
    private static class SalespersonStats {
        private int salesCount;
        private double salesAmount;

        public void incrementSalesCount() {
            salesCount++;
        }

        public void addSalesAmount(double amount) {
            salesAmount += amount;
        }

        public int getSalesCount() {
            return salesCount;
        }

        public double getSalesAmount() {
            return salesAmount;
        }
    }

    /**
     * Class to create a bar chart panel.
     */
    private static class BarChartPanel extends JPanel {
        private Map<?, Integer> data;
        private String title;
        private String xAxisLabel;
        private String yAxisLabel;

        public BarChartPanel(Map<?, Integer> data, String title, String xAxisLabel, String yAxisLabel) {
            this.data = data;
            this.title = title;
            this.xAxisLabel = xAxisLabel;
            this.yAxisLabel = yAxisLabel;
            setPreferredSize(new Dimension(500, 300));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int padding = 25;
            int labelPadding = 25;
            int barWidth = (width - 2 * padding - labelPadding) / data.size();
            int maxBarHeight = height - 2 * padding - labelPadding;

            int maxValue = data.values().stream().max(Integer::compare).orElse(1);

            // Draw title
            g2d.setFont(new Font("Dubai Medium", Font.PLAIN, 18));
            g2d.drawString(title, padding, padding - 5);

            // Draw x-axis label
            g2d.setFont(new Font("Dubai Medium", Font.PLAIN, 12));
            g2d.drawString(xAxisLabel, width / 2 - g2d.getFontMetrics().stringWidth(xAxisLabel) / 2, height - padding + labelPadding);

            // Draw y-axis label
            g2d.rotate(-Math.PI / 2);
            g2d.drawString(yAxisLabel, -height / 2 - g2d.getFontMetrics().stringWidth(yAxisLabel) / 2, padding - labelPadding);
            g2d.rotate(Math.PI / 2);

            // Draw bars
            int x = padding + labelPadding;
            for (Map.Entry<?, Integer> entry : data.entrySet()) {
                int barHeight = (int) ((double) entry.getValue() / maxValue * maxBarHeight);
                g2d.setColor(Color.BLUE);
                g2d.fillRect(x, height - padding - barHeight, barWidth, barHeight);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, height - padding - barHeight, barWidth, barHeight);

                // Draw x-axis labels
                String xAxisValue = entry.getKey().toString();
                int labelWidth = g2d.getFontMetrics().stringWidth(xAxisValue);
                g2d.drawString(xAxisValue, x + (barWidth - labelWidth) / 2, height - padding + labelPadding / 2);

                x += barWidth;
            }
        }
    }

    /**
     * Add a button to the specified panel.
     * 
     * @param text the text of the button
     * @param panel the panel to add the button to
     * @param gbc the GridBagConstraints for the button
     * @param x the x position of the button
     * @param y the y position of the button
     * @param actionListener the ActionListener for the button
     */
    private void addButton(String text, JPanel panel, GridBagConstraints gbc, int x, int y, ActionListener actionListener) {
        gbc.gridx = x;
        gbc.gridy = y;
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(241, 57, 83));
        panel.add(btn, gbc);
        btn.addActionListener(actionListener);
    }
}