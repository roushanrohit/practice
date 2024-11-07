package recursion;

public class GenericFunctions {

    public static <T> void print(T[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
    }

    public static <T extends Vehicle> void print(T[] a){
        System.out.println("Inside Vehicle print method");
        for(int i = 0; i < a.length; i++){
            a[i].print();
        }
    }

    public static <T extends PrintInterface> void print(T[] a){
        System.out.println("Inside Print Interface print method");
        for(int i = 0; i < a.length; i++){
            a[i].print();
        }
    }


    public static void main(String[] args) {

        Integer[] a = {1,2,3,4,5,6,7,8,9,10};
        String[] s = {"abc", "def", "ghi","jkl"};
        Character[] c = {'a', 'b', 'c', 'd'};
        print(a);
        System.out.println();
        print(s);
        System.out.println();
        print(c);
        System.out.println();

        Vehicle[] v = new Vehicle[10];
        for(int i = 0; i < 10; i++) v[i] = new Vehicle();
        print(v);

    }
}
