package CitySearch;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch {
    private static Stack<String> stack = new Stack<>();
    private static ArrayList<String> visited = new ArrayList<>();

    public static void DFS(ArrayList<City> cities){
        int n;
        boolean selected;
        City initial = cities.get(City.selectCity(cities));
        City goal = cities.get(City.selectCity(cities));
        City temp;

        //initialize frontier(stack)
        temp = initial;
        stack.push(temp.getName());

        //clear visited
        visited.clear();

        do{
            selected = false;

            //goal-check
            if(temp.getName().equals(goal.getName())){

                //print path
                while (!stack.isEmpty())
                    System.out.println(stack.pop() + "\n");
                break;
            }

            //add to visited
            if (!visited.contains(temp.getName()))
                visited.add(temp.getName());

            //expand temp and push children
            for (n=0; n<temp.getNeighbours().size()&&!selected; n++) {
                if (!visited.contains(temp.getNeighbours().get(n).getName()) && !stack.contains(temp.getNeighbours().get(n).getName())) {
                    stack.push(temp.getNeighbours().get(n).getName());
                    selected = true;
                }
            }
            if (!selected)
                stack.pop();

            //select new temp
            temp = City.reference(cities, stack.peek());

        }while(!stack.isEmpty());
    }
}
