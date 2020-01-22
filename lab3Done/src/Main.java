import com.buildings.*;
import com.graphrelated.AdjacencyMatrix;
import com.interfaces.*;
import com.travelmap.TravelMap;

/**
 * @author Toma-Florin Ungureanu
 */
public class Main
{
    public static void main(String[] args)
    {
        Hotel v1 = new Hotel("California");
        Museum v2 = new Museum("Louvre");
        Museum v3 = new Museum("Ermitage");
        Church v4 = new Church("Sagrada Familia");
        Church v5 = new Church("Notre-Dame de Paris");
        Restaurant v6 = new Restaurant("Oscar");

        v1.setRank(2);

        v2.setOpenHour();
        v2.setCloseHour();
        v2.setEntryFee(20);

        v3.setEntryFee(15);
        v3.setOpenHour("10:30");
        v3.setCloseHour();

        v4.setOpenHour("10:00");
        v4.setCloseHour("19:00");

        v5.setOpenHour();
        v5.setCloseHour("20:30");

        v6.setRank(1);

        IVisitable.getVisitingDuration(v5);
        IVisitable.getVisitingDuration(v2);
        IVisitable.getVisitingDuration(v3);

        TravelMap map = new TravelMap();
        map.addNode(v1);
        map.addNode(v2);
        map.addNode(v3);
        map.addNode(v4);
        map.addNode(v5);
        map.addNode(v6);
        map.addEdge(v1, v2, 15);
        map.addEdge(v1, v3, 10);
        map.addEdge(v3, v2, 1, false);
        map.addEdge(v3, v4, 2);
        map.addEdge(v4, v5, 1);
        map.addEdge(v5, v5, 1);
        map.addEdge(v2, v6, 10);

        //  System.out.println("The map is: \n" + map.getNodes());
        //  System.out.println(map);

        //map.displayVisitableNotPayable();
        //map.displayAveragePayable();
        AdjacencyMatrix matrix = new AdjacencyMatrix(map);
        matrix.shortestPathTwoDistinct(0,4);
        matrix.shortestPathEveryDistinctPair();
    }
}
