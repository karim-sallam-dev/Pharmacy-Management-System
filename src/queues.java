public class queues {
    public int maxSize;
    public int[] id;
    public String[] name;
    public double[] price;
    public int[] Quantity;
    public int front;
    public int rear;
    public int nItems;





    public queues(int size) {
        maxSize = size;
        id = new int[maxSize];
        name = new String[maxSize];
        price = new double[maxSize];
        Quantity = new int[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(int i,String n,double p,int q) {
        if (rear == maxSize - 1)
            rear = -1;
        ++rear;
        id[rear] = i;
        name[rear] = n;
        price[rear] = p;
        Quantity[rear] = q;
        nItems++;
    }


    public double sum() {
        double sum = 0;
        for (int i = 0; i < nItems; i++) {
            sum += price[i] * Quantity[i];
        }
        return sum;
    }


}
