package Lab3;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. SAX Parsing
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            Lab3.RegressionHandler handler = new Lab3.RegressionHandler();

            File inputFile = new File("data.xml"); // Створіть цей файл у корені проекту
            saxParser.parse(inputFile, handler);

            // 2. Calculation
            double[] result = Lab3.RegressionCalculator.calculate(handler.getPoints());
            System.out.printf("Result: y = %.4fx + %.4f%n", result[0], result[1]);

            // 3. DOM Writing
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("RegressionResult");
            doc.appendChild(rootElement);

            Element coeffs = doc.createElement("Coefficients");
            coeffs.setAttribute("k", String.valueOf(result[0]));
            coeffs.setAttribute("b", String.valueOf(result[1]));
            rootElement.appendChild(coeffs);

            // Save to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File("result.xml"));
            transformer.transform(source, streamResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
