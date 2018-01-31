package CitySearch;

import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.Scanner;

public class City {
    private String name;
    private Point2D place;
    private ArrayList<Neighbour> neighbours;

    City(String name, double x, double y) {
        this.name = name;
        this.place = new Point2D(x, y);
        this.neighbours = new ArrayList<>();
    }

    City(City c){
        this.name = c.name;
        this.place = new Point2D(c.place.getX(), c.place.getY());
        this.neighbours = new ArrayList<>();
    }

    public String toString(){
        return this.name;
    }

    public String getName(){
        return this.name;
    }

    public Point2D getPlace(){
        return this.place;
    }

    public ArrayList<Neighbour> getNeighbours(){
        return this.neighbours;
    }

    public void addNeighbour(City c, double realDistance, int index){
        this.neighbours.add(new Neighbour(c));
        this.neighbours.get(index).realDistance = realDistance;
        this.neighbours.get(index).linearDistance = c.place.distance(this.place);

    }

    public void addNeighbour(String s, double x, double y, double realDistance, int index) {
        this.neighbours.add(new Neighbour(s, x, y));
        this.neighbours.get(index).linearDistance = this.place.distance(this.neighbours.get(index).getPlace());
        this.neighbours.get(index).realDistance = realDistance;
    }

    public static int selectCity(ArrayList<City> cityList) {
        Scanner input = new Scanner(System.in);
        System.out.println("Select city from below:\n");
        for (int i = 0; i < cityList.size(); i++)
            System.out.println(i + 1 + "." + cityList.get(i).getName() + "\n");

        return input.nextInt()-1 ;
    }

    public static City reference(ArrayList<City> cityList, String cityName){
        int i ;
        for (i=0; i<cityList.size(); i++){
            if (cityName.equals(cityList.get(i).getName()))
                break;
        }
        return cityList.get(i);
    }

    public static ArrayList<City> menoufiaMap(){
        ArrayList<City> cityList = new ArrayList<>();
        cityList.add(new City("Tala",435,55));              //index0
        cityList.add(new City("Al-Shohadaa",400,150));      //index1
        cityList.add(new City("Birket Al-Sabaa",560,120));  //index2
        cityList.add(new City("Qwesna",600,215));           //index3
        cityList.add(new City("Shebin Al-Koum",480,200));   //index4
        cityList.add(new City("Menouf",419,285));           //index5
        cityList.add(new City("Sirs Al-Layan",405,304));    //index6
        cityList.add(new City("Al-Bagour",509,327));        //index7
        cityList.add(new City("Ashmoun",460,457));          //index8
        cityList.add(new City("AL-Sadat",62,405));          //index9


        cityList.get(0).addNeighbour(cityList.get(4), 16.3, 0);
        cityList.get(1).addNeighbour(cityList.get(5), 22.8, 0);
        cityList.get(2).addNeighbour(cityList.get(4), 14.3, 0);
        cityList.get(2).addNeighbour(cityList.get(3), 17.1, 1);
        cityList.get(3).addNeighbour(cityList.get(2), 17.1, 0);
        cityList.get(3).addNeighbour(cityList.get(4), 18.3, 1);
        cityList.get(4).addNeighbour(cityList.get(0), 16.3, 0);
        cityList.get(4).addNeighbour(cityList.get(2), 15.2, 1);
        cityList.get(4).addNeighbour(cityList.get(3), 18.3, 2);
        cityList.get(4).addNeighbour(cityList.get(5), 17.9, 3);
        cityList.get(5).addNeighbour(cityList.get(4), 17.9, 0);
        cityList.get(5).addNeighbour(cityList.get(6), 7.2,  1);
        cityList.get(5).addNeighbour(cityList.get(9), 57.6, 2);
        cityList.get(5).addNeighbour(cityList.get(1), 22.8, 3);
        cityList.get(6).addNeighbour(cityList.get(7), 8.3,  0);
        cityList.get(6).addNeighbour(cityList.get(5), 7.2,  1);
        cityList.get(7).addNeighbour(cityList.get(6), 8.3,  0);
        cityList.get(8).addNeighbour(cityList.get(9), 75,  0);
        cityList.get(9).addNeighbour(cityList.get(8), 75,  0);
        cityList.get(9).addNeighbour(cityList.get(5), 57.6,  1);

        /*for(int k=0; k<cityList.size(); k++){
            System.out.println(cityList.get(k).cityID+"\t\t\t\t"+cityList.get(k).name+"\t\t\t\t"+cityList.get(k).place.toString()+"\n");
            for(int i =0;i<cityList.get(k).neighbours.size();i++)
                System.out.println(cityList.get(k).neighbours.get(i).getName()+"\t"+
                        cityList.get(k).neighbours.get(i).realDistance+"\t"+
                        cityList.get(k).neighbours.get(i).linearDistance+
                        "\n");
        }*/
        return cityList;
    }

    public static void selectAlgorithm(ArrayList<City> cityList){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Algorithm: \n1.Depth First Search \n2.Breadth First Search");
        switch (scanner.nextInt()){
            case 1 : DepthFirstSearch.DFS(cityList);

            case 2 : BreadthFirstSearch.BFS(cityList);
        }
    }

    public static ArrayList<City> selectMap(){
        ArrayList<City> cityList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Map: \n1.Built-in Menoufia map. \n2.Enter map data. \n");
        if (scanner.nextInt()==1)
            cityList = City.menoufiaMap();

        else if (scanner.nextInt()==2)
            cityList = City.userMap();

        return cityList;
    }


    public static ArrayList<City> userMap(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<City> cityList = new ArrayList<>();
        System.out.println("How many Cities would you like to add?\n");
        int n = (scanner.nextInt() - 1);

        for (int i = 0; i <= n; i++) {
            System.out.println("Enter City " + (i + 1) + " Name:");
            String s = scanner.next();
            System.out.println("Enter City X-coordinate:");
            double a = scanner.nextDouble();
            System.out.println("Enter City Y-coordinate:");
            double b = scanner.nextDouble();
            cityList.add(new City(s, a, b));

            System.out.println("Add neighbour for " + cityList.get(i).name + " ?(1,0)");
            int ans1 = scanner.nextInt();
            if (ans1 == 1) {
                System.out.println("Enter no. of neighbours:");
                int in = (scanner.nextInt() - 1);
                cityList.get(i).neighbours = new ArrayList<>();
                for (int j = 0; j <= in; j++) {
                    System.out.println("Enter Neighbour " + (j + 1) + " Name:");
                    String sn = scanner.next();
                    System.out.println("Enter Neighbour X-coordinate:");
                    double an = scanner.nextDouble();
                    System.out.println("Enter Neighbour Y-coordinate:");
                    double bn = scanner.nextDouble();
                    System.out.println("Distance from " + cityList.get(i).name + "?");
                    double rDN = scanner.nextDouble();
                    cityList.get(i).addNeighbour(sn, an, bn, rDN, j);
                }
            }
        }
    return cityList;
    }

}



class Neighbour extends City{
    double realDistance;
    double linearDistance;

    Neighbour(String name, double x, double y) {
        super(name, x, y);
    }

    Neighbour(City c){
        super(c);
    }
}

