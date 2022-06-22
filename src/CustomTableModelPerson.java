import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class CustomTableModelPerson extends AbstractTableModel {
    private String[] headers;
    private int[] indexes;
    private Vector<Person> data;
    public Vector<Person> getlist() {
        return data;
    }

    public CustomTableModelPerson(String[] headers, int[] indexes) {
        this.headers = new String[headers.length];
        for (int i = 0; i < headers.length; i++) {
            this.headers[i] = headers[i];
        }
        this.indexes = new int[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            this.indexes[i] = indexes[i];
        }
        data = new Vector<>();
    }

    @Override
    public String getColumnName(int column) {
        if (column >= 0 || column < headers.length) {
            return headers[column];
        } else {
            return "";
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= data.size() || columnIndex < 0 || columnIndex >= headers.length) {
            return null;
        }
        Person person = data.get(rowIndex);
        switch (indexes[columnIndex]) {
            case 0:
                return person.getID();
            case 1:
                return person.getName();
            case 2:
                return person.getGender();
            case 3:
                return person.getPhone();
            case 4:
                return person.getEmail();
            case 5:
                return person.getAddress();
        }
        return null;
    }

}
