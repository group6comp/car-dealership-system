package carDealership;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private final String[] columnNames = {"Username", "Password", "Role"};
    private final List<User> users;

    public UserTableModel(List<User> users) {
        this.users = users;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

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

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public User getUserAt(int rowIndex) {
        return users.get(rowIndex);
    }
}