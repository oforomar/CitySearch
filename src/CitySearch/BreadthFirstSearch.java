package CitySearch;

import java.util.ArrayList;

public class BreadthFirstSearch {
    private static ArrayList<String> queue = new ArrayList<>();
    private static ArrayList<String> visited = new ArrayList<>();

    public static void BFS(ArrayList<City> cities){
        int n;
        City temp;
        City initial = cities.get(City.selectCity(cities));
        City goal = cities.get(City.selectCity(cities));

        //initialize frontier(queue)
        temp = initial;
        queue.add(temp.getName());

        //clear visited
        visited.clear();

        do{
            //add to visited
            if (!visited.contains(temp.getName()))
                visited.add(temp.getName());

            //goal-check
            if(temp.getName().equals(goal.getName())){
                //print path (visited for now)
                System.out.println(visited.toString());
                break;
            }

            //remove current node from frontier
            queue.remove(0);

            //expand temp and push children
            for (n=0; n<temp.getNeighbours().size(); n++) {
                if (!visited.contains(temp.getNeighbours().get(n).getName()) && !queue.contains(temp.getNeighbours().get(n).getName()))
                    queue.add(temp.getNeighbours().get(n).getName());
            }

            //select new temp
            temp = City.reference(cities, queue.get(0));

        }while(!queue.isEmpty());
    }
}



