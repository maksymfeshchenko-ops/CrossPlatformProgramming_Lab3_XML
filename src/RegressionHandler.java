package Lab3;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class RegressionHandler extends DefaultHandler {
    private List<Lab3.Point> points = new ArrayList<>();
    private String currentElement = "";
    private double tempX, tempY;

    public List<Lab3.Point> getPoints() { return points; }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (value.isEmpty()) return;

        if (currentElement.equals("x")) tempX = Double.parseDouble(value);
        if (currentElement.equals("y")) tempY = Double.parseDouble(value);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("Point")) {
            points.add(new Lab3.Point(tempX, tempY));
        }
        currentElement = "";
    }
}
