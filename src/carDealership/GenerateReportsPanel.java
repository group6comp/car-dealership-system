package carDealership;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

public class GenerateReportsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel parentPanel;
    private CardLayout cardLayout;

    /**
     * Create the panel.
     */
    public GenerateReportsPanel(JPanel parentPanel, CardLayout cardLayout) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lblTitle = new JLabel("Generate Reports");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(250, 20, 150, 30);
        add(lblTitle);

        JLabel lblReportSummary = new JLabel("Report Summary: Total Sales, Profits, etc.");
        lblReportSummary.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblReportSummary.setBounds(50, 70, 550, 30);
        add(lblReportSummary);

        JButton btnExport = new JButton("Export Report");
        btnExport.setBounds(250, 300, 150, 30);
        add(btnExport);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(50, 350, 150, 30);
        add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(parentPanel, "mainPanel");
            }
        });
    }
}