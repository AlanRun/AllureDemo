package framework.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableElement extends HtmlElement {
    private List<WebElement> rows = null;
    private List<WebElement> columns = null;

    public TableElement(final String label, final By by) {
        super(label, by);
    }

    public TableElement(final String label, final By by, int index) {
        super(label, by, index);
    }

    public void findElement() {
        findElement();
        try {
            rows = element.findElements(By.tagName("tr"));
        } catch (NotFoundException e) { }

    }

    public int getColumnCount() {
        if (rows == null) {
            findElement();
        }

        // Need to check whether rows is null AND whether or not the list of rows is empty
        if (rows != null && !rows.isEmpty()) {
            try {
                columns = rows.get(0).findElements(By.tagName("td"));
            } catch (NotFoundException e) { }

            if (columns == null || columns.size() == 0) {

                try {
                    if (rows.size() > 1) {
                        columns = rows.get(1).findElements(By.tagName("td"));
                    } else {
                        columns = rows.get(0).findElements(By.tagName("th"));
                    }
                } catch (NotFoundException e) { }
            }
        }

        if (columns != null) {
            return columns.size();
        }

        return 0;
    }

    public List<WebElement> getColumns() {
        return columns;
    }

    /**
     * Get table cell content.
     *
     * @param  row     Starts from 1
     * @param  column  Starts from 1
     */
    public String getContent(final int row, final int column) {
        if (rows == null) {
            findElement();
        }

        if (rows != null && !rows.isEmpty()) {
            try {
                columns = rows.get(row - 1).findElements(By.tagName("td"));
            } catch (NotFoundException e) { }

            if (columns == null || columns.size() == 0) {
                try {
                    columns = rows.get(row - 1).findElements(By.tagName("th"));
                } catch (NotFoundException e) { }
            }

            return columns.get(column - 1).getText();
        }

        return null;
    }

    public int getRowCount() {
        if (rows == null) {
            findElement();
        } else {
            return rows.size();
        }

        int count = element.findElements(By.xpath("tbody/tr")).size();
        if (count == 0) {
            count = element.findElements(By.xpath("tr")).size();
        }

        return count;
    }

    public List<WebElement> getRows() {
        return rows;
    }

    public boolean isHasBody() {
        return getRows().size() > 0;
    }
}
