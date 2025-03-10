package carDealership;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * The UserTableModel class represents the table model for displaying user data.
 * It extends the AbstractTableModel class and provides custom functionality for handling users.
 */
public class UserTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private final String[] columnNames = {"Username", "Password", "Role"};
    private final List<User> users;

    /**
     * Constructor for creating a UserTableModel with a list of users.
     * 
     * @param users the list of users to be displayed in the table
     */
    public UserTableModel(List<User> users) {
        this.users = users;
    }

    /**
     * Get the number of rows in the table.
     * 
     * @return the number of rows
     */
    @Override
    public int getRowCount() {
        return users.size();
    }

    /**
     * Get the number of columns in the table.
     * 
     * @return the number of columns
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Get the value at the specified row and column.
     * 
     * @param rowIndex the row index
     * @param columnIndex the column index
     * @return the value at the specified row and column
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.getUsername();
            case 1:
                return user.getPassword().replaceAll(".", "*"); // Blur the password with stars
            case 2:
                return user.getRole();
            default:
                return null;
        }
    }

    /**
     * Get the name of the column at the specified index.
     * 
     * @param column the column index
     * @return the name of the column
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Get the user at the specified row.
     * 
     * @param rowIndex the row index
     * @return the User object at the specified row
     */
    public User getUserAt(int rowIndex) {
        return users.get(rowIndex);
    }
}