public class MaxHeap {

    public static void main(String[] args){
        //int[] input = {3,5,6,23,7,9,2,1};
        int[] input = {4,1,3,2,16,9,10,14,8,7};
        MaxHeap sample = new MaxHeap(input);
        sample.buildMaxHeap();
    }

    int[] arr;
    int size;

    MaxHeap(int[] input){
        this.arr = input;
        this.size = input.length;
    }

    public int left(int indx){
        return 2*indx+1;

    }

    public int right(int indx){
        return 2*indx+2;

    }

    public void buildMaxHeap(){
        // always iterate from the mid to starting of the array. this is very important.
        for (int i = this.size/2; i >=0; i--) {
            this.maxHeapify(i);
        }
    }

    public void maxHeapify(int pos){

        int left = this.left(pos);
        int right = this.right(pos);
        int largest = -1;
        // the following code will compare the left, right and pos position data and pick the highest among them.
        if(left < this.size && this.arr[left] > this.arr[pos]){
            largest = left;
        }else{
            largest = pos;
        }

        if(right < this.size && this.arr[right] > this.arr[largest]){
            largest = right;
        }

        // if the present pos element is not the largest, then swap and run the max heapify from the largest position.
        if(largest != pos){
            this.swap(largest,pos);
            this.maxHeapify(largest);
        }
    }

    public void swap(int i, int j){
        int temp = this.arr[i];
        this.arr[i] = this.arr[j];
        this.arr[j] = temp;
    }
}
