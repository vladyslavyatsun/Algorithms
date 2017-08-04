import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dog on 8/1/17.
 */
public class GraphTest {
    @Test
    public void strongConnectedComponentTest() throws Exception {
        Graph graph = new Graph("src/test/data/graph1.txt");
        List<Integer> result = graph.strongConnectedComponent();
        assertEquals(434821, Collections.max(result).longValue());
    }

    @Test
    public void dijkstraTest() throws Exception {
        Graph graph = new Graph("src/test/data/graph.txt");
        System.out.println(graph.dijkstra(100562, 1070345));
    }

}