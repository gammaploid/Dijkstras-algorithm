import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author lewi0146
 * @author gammaploid
 */
public class GraphBuilder {


    public static Graph buildFromGraphML(String file) throws JDOMException, IOException {

        // Create a new graph instance
        MyGraph graph = new MyGraph();
        

        // the SAXBuilder is the easiest way to create the JDOM2 objects.
        SAXBuilder jdomBuilder = new SAXBuilder();

        // jdomDocument is the JDOM2 Object
        Document jdomDocument = jdomBuilder.build(file);

        // The root element is the root of the document
        Element graphxml = jdomDocument.getRootElement();
        Namespace ns = graphxml.getNamespace(); // Namespace.getNamespace("http://foo.com");
        Element graphElement  = graphxml.getChild("graph", ns);
        //System.out.println(graph.getName() + ": " + graph.getChildren().size());

        // First, add all nodes
        List<Element> nodes = graphElement.getChildren("node", ns);
        for (Element node : nodes) {
            String nodeId = node.getAttributeValue("id");
            graph.addVertex(new Vertex(nodeId));
        }

        // Then, add all edges with weights
        List<Element> edges = graphElement.getChildren("edge", ns);
        for (Element edge : edges) {
            String sourceId = edge.getAttributeValue("source");
            String targetId = edge.getAttributeValue("target");
            
            // Get the weight from the data element
            Element dataElement = edge.getChild("data", ns);
            int weight = Integer.parseInt(dataElement.getText());
            

            // if (nV > max) max = nV;
            // if (nV < min) min = nV;


            Vertex source = graph.getVertex(sourceId);
            Vertex target = graph.getVertex(targetId);
            
            graph.addEdge(source, target, weight);
        }

        return graph;
    }

}
