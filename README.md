# Loblaw-Digital
BI Engineer Assessment Nov. 2019

######## Environment
Java 1.8
Maven

######## Deployment
1. Import project
2. Run App.java

######## Directory
├── Readme.md                  
├── .idea                       
├── src                     
│   ├── main
│   |   ├── java                
│   |   |   └── com.loblaw
│   |   |       ├── App              
│   |   |       └── Data              
│   |   └── resources         
│   |       └── SampleSales2016.tsv   // Data Source
|   └── test
├── target
└── pom.xml


######## Questions & Answers
1. How many stores are there in the data file? How many stores have online orders?

There are 256 stores in total.
38 stores have online orders.

*****************************
2. Find the percentage of customers with linked PC accounts.

The percentage of customers with linked PC accounts is: 99.95708154506437%

*****************************
3. Find the monthly revenues for the retail and online business (all stores combined),
respectively.

Revenues by months are: 
Month: 1  Total revenue: 930277.6700000009
Month: 2  Total revenue: 931773.9499999994
Month: 3  Total revenue: 976559.9500000017
Month: 4  Total revenue: 375520.54000000015

*****************************
4. What is the best performing store in terms of average monthly revenue (retail and online
combined)?

For month 1: the store with the most revenue is: 1565 revenue is: 86276.52999999997
For month 2: the store with the most revenue is: 1565 revenue is: 82918.28999999995
For month 3: the store with the most revenue is: 1565 revenue is: 85046.01999999995
For month 4: the store with the most revenue is: 1565 revenue is: 37212.36999999999

Therefore, the best performing store in terms of average monthly revenue (retail and online
combined) is 1565.

*****************************
5. Given a list of aggregated total daily sales S, return a list such that, for each day in the
input, tells you how many days you would have to wait until a higher sale. If there is no
future day for which this is possible, put 0 instead.
For example, given the list of sales S = [730.50, 741.36, 756.83, 718.64, 698.90, 723.63,
767.37, 738.94], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

private static int[] question5(List<Double> list) {
        Stack<Integer> stk = new Stack();
        int n = list.size();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && list.get(stk.peek()) < list.get(i)) {
                int lastIndex = stk.pop();
                res[lastIndex] = i - lastIndex;
            }
            stk.push(i);
        }
        return res;
    }


######## End of Assessment, Thank you.
Xiuming Cui
Nov. 2019
########
