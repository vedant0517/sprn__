import java.util.Comparator;
import java.util.PriorityQueue;

public class Basics {
    static class Student implements Comparable<Student>{
        String name;
        int rank;

        public Student(String name,int rank){
            this.name=name;
            this.rank=rank;
        }

        @Override
        public int compareTo(Student s2){
            return this.rank-s2.rank;
        }
    }

    //if  this.rank-s2.rank;  == +ve    1st>2nd
    //if  this.rank-s2.rank;  == -ve    2nd>1st
    //if  this.rank-s2.rank;  == -ve    Equal
    

    public static void main(String[] args) {
        
        PriorityQueue <Student> pq=new PriorityQueue<>();

        pq.add(new Student("A", 4));//O(logn)
        pq.add(new Student("B", 5));
        pq.add(new Student("C", 2));
        pq.add(new Student("D", 12));


        while (!pq.isEmpty()) {
            System.out.println(pq.peek().name+"-> "+ pq.peek().rank);//O(1)
            pq.remove(); //O(logn)
        }
    }
    
}
