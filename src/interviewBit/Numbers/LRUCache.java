package interviewBit.Numbers;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.*;

public class LRUCache {
    public class DNode{
        int data = 0;
        int key = 0;
        DNode left;
        DNode right;
        public DNode(int key,int val){
            this.data = val;
            this.key = key;
        }
    }
    public static void main(String[] args){
        LRUCache sample = new LRUCache(2);
        System.out.println(sample.get(2));
        sample.set(2, 6);
        System.out.println(sample.get(1));
        sample.set(1, 5);

        sample.set(1, 2);
        System.out.println(sample.get(1));
        System.out.println(sample.get(2));
    }
    final int capacity;
    HashMap<Integer,DNode> mapping = new HashMap<>();
    DNode lru = null;
    DNode mru = null;
    public LRUCache(int capacity){
        this.capacity = capacity;
    }

    public int get(int key) {
        if(this.mapping.size() > 0){
            if(this.mapping.get(key) == null){
                return -1;
            }else{
                DNode ele = this.mapping.get(key);
                if (ele == this.mru){

                }else if(ele == this.lru){
                    this.mru.right = ele;
                    this.lru = ele.right;
                    ele.left = this.mru;
                    ele.right = null;
                    this.lru.left = null;
                    this.mru = ele;

                }else{
                    this.mru.right = ele;
                    ele.left.right = ele.right;
                    ele.right.left = ele.left;
                    ele.left = this.mru;
                    ele.right = null;
                    this.mru = ele;
                }
                return ele.data;
            }
        }else{
            return -1;
        }

    }

    public void set(int key, int value) {
        if(this.mapping.get(key) == null && this.mapping.size() == this.capacity){
            this.mapping.remove(this.lru.key);
            this.lru = this.lru.right;

            if(this.lru == null){
                this.mru = null;
            }else{
                this.lru.left = null;
            }
        }
        if(this.mapping.size() > 0){

            if(this.mapping.get(key) == null){
                DNode ele = new DNode(key,value);
                ele.left = this.mru;
                this.mru.right = ele;
                this.mru = ele;
                this.mapping.put(key,this.mru);
            }else{
                DNode ele = this.mapping.get(key);
                if(ele == this.mru){
                    this.mru.data = value;
                }else if(ele == this.lru){
                    this.mru.right = ele;
                    this.lru = ele.right;
                    ele.left = this.mru;
                    ele.right = null;
                    this.lru.left = null;
                    this.mru = ele;
                    this.mru.data = value;
                }else {
                    this.mru.right = ele;
                    ele.left.right = ele.right;
                    ele.right.left = ele.left;
                    ele.left = this.mru;
                    ele.right = null;
                    this.mru = ele;
                    this.mru.data = value;
                }
            }

        }else{
            this.lru = new DNode(key,value);
            this.mru = this.lru;
            this.mapping.put(key,this.mru);
        }

    }


}
