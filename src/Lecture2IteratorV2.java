import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// Node
class NodeList<T>{
    private T value;
    private NodeList next;
    NodeList(T value){
        this.value = value;
        this.next = null;
        }
    NodeList<T> next(){
        return next;
    }
    T value(){
        return value;
    }
    void setNext(NodeList<T> node){
        next = node;
    }
}

class MYList<T> implements Iterable, Iterator {
    private NodeList<T> head;
    private NodeList<T> tail;
    private int length;
    private NodeList<T> currentElem;
    private NodeList<T> prevElem;
    private int currentPosition;

    public boolean hasNext(){
        return (currentPosition < length -1) ? true : false; 
    }
    public T next() throws IndexOutOfBoundsException {
        if (currentPosition >= length - 1){
            throw new IndexOutOfBoundsException("End of list."); 
        }
        currentPosition++;
        if (currentPosition != 0){
            prevElem = currentElem;
            currentElem = currentElem.next();              
        }  else{
            currentElem = head;
        }              
        return currentElem.value(); 
    }

    public void remove() throws IndexOutOfBoundsException{
        if (length == 0) 
            throw new IndexOutOfBoundsException("Error remove. End of list.");
        if (currentPosition == length - 1){
            tail = prevElem;
        }
        if (currentPosition == 0){
            head = head.next();
            currentElem = head;
          }
        else {
            prevElem.setNext(currentElem.next());
            currentElem = prevElem;
        }
        currentPosition --;
        length--;
    }

    public void resetIterator(){
        currentElem = null;
        prevElem = null;
        currentPosition = -1;
    }

    int size(){
        return length;
    }
   
    NodeList<T> getHead(){
        return head;
    }
    public void removeHead(){
        if (head != null){
            if(currentElem == head)
                currentElem = head.next();                  
           head = head.next();
           currentPosition--;
           length--;
        }
    }
 
    void add(T value){
        NodeList<T> currentNode = new NodeList(value);  
        if (head == null){
             head = currentNode;
             currentElem = head;
             currentPosition = -1;
        }
        else{
            tail.setNext(currentNode);
        }
        tail = currentNode;
        length++;
    }
    public MyListIterator<T> iterator(){
        return new MyListIterator(this);
    } 
    private void decrenentLength() throws IndexOutOfBoundsException {
        if (length > 0)
            length--;
        else throw new IndexOutOfBoundsException("length < 0");
    }
    private void changeTail(NodeList<T> newTail){
        tail = newTail;
    }
    MYList(){      
        length = 0;
        currentPosition = -1;
    }

    private class MyListIterator<T> implements Iterator{
        private int position;
        private ArrayList<NodeList<T>> elems;
        private MYList<T> myList;
        private int length;
        
        public boolean hasNext(){
            return (position > 0) ? true: false;
        }
        public T next(){
            return elems.get(--position).value();
        }
        public void remove() {       
            if (position == 0){
                myList.removeHead();        
            } else{
                elems.get(position - 1).setNext(elems.get(position).next());
                myList.decrenentLength();
               if (position == length - 1)
                     myList.changeTail(elems.get(position - 1));
            }
            length --;        
        }
    
        MyListIterator(MYList<T> myList){
            this.myList = myList;
            elems = new ArrayList<NodeList<T>>(myList.size());
            length = myList.size();
            position = length;
            NodeList<T> current = myList.getHead();
            for (int i = 0; i < myList.size(); ++i){
                elems.add(current);
                current = current.next();
            }
        }
    }
}



public class Lecture2IteratorV2 {    
    public static void main(String[] args) throws IOException {
     /*   MyList<String> myList = new MyList(){{
            add("ZLO");
            add("NEUDACHA");
            add("ZLO"); 
            add("DOBRO");
            add("UDACHA"); 
            add("NEUDACHA");
            add("ZLO");
            add("ZLO"); 
            add("NEUDACHA");
            add("KRASOTA"); 
            add("ZLO");
            add("NEUDACHA");
            add("ZLO"); 
        }};   
        
        while (myList.hasNext()) {
            System.out.println(myList.next());
        }       
        myList.resetIterator();               
       
        while (myList.hasNext()) {
            String curStr = myList.next();
            if (curStr.equals("ZLO") || curStr.equals("NEUDACHA")) {
                myList.remove();            
            }         
        }  

        myList.resetIterator();        
        System.out.println("------------------------ after delete \"ZLO\"");
        while (myList.hasNext()) {
            System.out.println(myList.next());
        } */
        
        MYList<String> myList2 = new MYList(){{
            add("ZLO");
            add("NEUDACHA");
            add("ZLO"); 
            add("DOBRO");
            add("UDACHA"); 
            add("NEUDACHA");
            add("ZLO");
            add("ZLO"); 
            add("NEUDACHA");
            add("KRASOTA"); 
            add("ZLO");
            add("NEUDACHA");
            add("ZLO"); 
        }};   
        Iterator<String> iter = myList2.iterator();        
        System.out.println("Reverse order");
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }     
        iter = myList2.iterator(); 
        while (iter.hasNext()) {
            String curStr = iter.next();
            if (curStr.equals("ZLO") || curStr.equals("NEUDACHA")) {
                iter.remove();        
            }         
        }  
        myList2.add("POBEDA");
        iter = myList2.iterator();   
        System.out.println(myList2.size());     
        System.out.println("- after delete \"ZLO\" in reverse order" );
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
       // System.out.println("FOR GIT"); 
    } 
    
}
