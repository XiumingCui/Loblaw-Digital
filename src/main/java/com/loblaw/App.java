package com.loblaw;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
    private static Calendar calendar = Calendar.getInstance();
    public static void main( String[] args )
    {

        List<Data> data = readFile();
        System.out.println( "*****Question 1 *****" );
        question1(data);
        System.out.println( "*****Question 2 *****" );
        question2(data);
        System.out.println( "*****Question 3 *****" );
        question3(data);
        System.out.println( "*****Question 4 *****" );
        question4(data);
        System.out.println( "*****Question 5 is attached in the code *****" );

        System.out.println( "*****End of the assessment *****" );
    }

    private static List<Data> readFile() {
        List<Data> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(App.class.getClassLoader().getResourceAsStream("SampleSales2016.tsv")))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                Data data = convertToData(line);
                list.add(data);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        list.toString();
        return list;
    }

    private static Data convertToData(String line) {
        String[] cols = line.split("\\s");
        Data d = new Data();
        d.setId(cols[0]);
        try {
            d.setDate(format.parse(cols[1]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d.setStoreId(Integer.parseInt(cols[2]));
        d.setOrderValue(Double.valueOf(cols[3]));
        d.setOrderId(cols[4]);
        return d;
    }

    private static void question1(List<Data> table) {
        Map<Integer, Boolean> uniqueStores = new HashMap<>();
        for (Data d : table) {
            boolean hasOnline = !d.getOrderId().equals("NA");
            uniqueStores.put(d.getStoreId(), hasOnline || uniqueStores.getOrDefault(d.getStoreId(), false));
        }
        long count = uniqueStores.entrySet()
                .stream()
                .filter((k) -> k.getValue())
                .count();
        System.out.println("Total stores are: " + uniqueStores.size());
        System.out.println("Number of stores with online orders are: " + count);
    }

    private static void question2(List<Data> table) {
        Map<String, Boolean> uniqueCustomers = new HashMap<>();
        for (Data d : table) {
             boolean hasPcAccounts = d.hasPcAccount();
            uniqueCustomers.put(d.getUuid(), hasPcAccounts || uniqueCustomers.getOrDefault(d.getUuid(), false));
        }
        long withPcAccounts = uniqueCustomers.entrySet()
                .stream()
                .filter(k -> k.getValue())
                .count();
        System.out.println("The percentage of customers with linked PC accounts is: " + (withPcAccounts * 1.0 / uniqueCustomers.size() * 100) + "%");
    }

    private static void question3(List<Data> table) {
        Map<String, Double> monthlyAvenues = new HashMap<>();

        for (Data d : table) {
            Date date = d.getDate();
            calendar.setTime(date);
            String key = calendar.get(Calendar.MONTH) + 1 + "";
            monthlyAvenues.put(key, monthlyAvenues.getOrDefault(key, 0d) + d.getOrderValue());
        }
        System.out.println("Revenues by months are: ");
        monthlyAvenues.forEach((k, v) -> System.out.println("Month: " + k + "  Total revenue: " + v));
    }

    private static void question4(List<Data> table) {
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            List<Data> monthlyData = table
                    .stream()
                    .filter(data -> {
                        calendar.setTime(data.getDate());
                        return calendar.get(Calendar.MONTH) == finalI;
                    })
                    .collect(Collectors.toList());

            Map<Integer, Double> map = new HashMap<>();
            for (Data data : monthlyData) {
                map.put(data.getStoreId(), map.getOrDefault(data.getStoreId(), 0d) + data.getOrderValue());
            }
            Optional<Map.Entry<Integer, Double>> entry = map.entrySet().stream()
                    .max((o1, o2) -> o1.getValue().compareTo(o2.getValue()) );
            System.out.println("For month " + (i + 1) + ": the store with the most revenue is: " + entry.get().getKey() + " revenue is: " + entry.get().getValue());
        }
    }

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
}
