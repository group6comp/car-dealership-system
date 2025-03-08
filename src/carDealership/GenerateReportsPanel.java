package carDealership;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateReportsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel parentPanel;
    private CardLayout cardLayout;
    private Dealership m_dealership = Main.m_dealership;

    /**
     * Create the panel.
     */
    public GenerateReportsPanel(JPanel parentPanel, CardLayout cardLayout) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new GridBagLayout());

        JLabel lblTitle = new JLabel("Generate Reports");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblTitle, gbc);

        JButton btnBack = new JButton("Back");
        addButton("Back", this, gbc, 1, 1, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(parentPanel, "mainPanel");
            }
        });

        generateReport();
    }

    private void generateReport() {
        JPanel reportPanel = new JPanel(new GridBagLayout());
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
        reportPanel.add(lblGraphs, gbc);

        // Sales Over Time Graph
        JPanel salesOverTimeGraph = createSalesOverTimeGraph();
        gbc.gridy = 1;
        reportPanel.add(salesOverTimeGraph, gbc);

        // Sales by Manufacturer Graph
        JPanel salesByManufacturerGraph = createSalesByManufacturerGraph();
        gbc.gridy = 2;
        reportPanel.add(salesByManufacturerGraph, gbc);

        // Section 2: Salesperson Report
        JLabel lblSalespersonReport = new JLabel("Salesperson Report");
        lblSalespersonReport.setFont(new Font("Dubai Medium", Font.PLAIN, 18));
        gbc.gridy = 3;
        reportPanel.add(lblSalespersonReport, gbc);

        JTextArea salespersonReport = new JTextArea(generateSalespersonReport());
        salespersonReport.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(salespersonReport);
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        reportPanel.add(scrollPane, gbc);

        JScrollPane reportScrollPane = new JScrollPane(reportPanel);
        reportScrollPane.setPreferredSize(new Dimension(600, 400));

        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 2;
        mainGbc.gridwidth = 2;
        mainGbc.fill = GridBagConstraints.BOTH;
        mainGbc.weightx = 1.0;
        mainGbc.weighty = 1.0;
        add(reportScrollPane, mainGbc);
    }

    private JPanel createSalesOverTimeGraph() {
        Map<LocalDate, Integer> salesOverTime = new HashMap<>();
        for (Sale sale : m_dealership.getSales()) {
            LocalDate saleDate = sale.getSaleDate();
            salesOverTime.put(saleDate, salesOverTime.getOrDefault(saleDate, 0) + 1);
        }

        return new BarChartPanel(salesOverTime, "Sales Over Time", "Date", "Sales");
    }

    private JPanel createSalesByManufacturerGraph() {
        Map<String, Integer> salesByManufacturer = new HashMap<>();
        for (Sale sale : m_dealership.getSales()) {
            String manufacturer = sale.getVehicle().getMake();
            salesByManufacturer.put(manufacturer, salesByManufacturer.getOrDefault(manufacturer, 0) + 1);
        }

        return new BarChartPanel(salesByManufacturer, "Sales by Manufacturer", "Manufacturer", "Sales");
    }

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

    private void saveReportAsTextFile(String reportContent) {
        try (FileWriter writer = new FileWriter("SalesReport.txt")) {
            writer.write(reportContent);
            JOptionPane.showMessageDialog(this, "Report saved as SalesReport.txt", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving report as text file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addButton(String text, JPanel panel, GridBagConstraints gbc, int x, int y, ActionListener actionListener) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(241, 57, 83));
        panel.add(btn, gbc);
        btn.addActionListener(actionListener);
    }

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
}