package com.deep.arr;

public class ListDistinct {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 1, 1, 1, 1, 3, 3, 3, 3, 5, 5, 5, 8, 8, 8, 9, 9, 9, 12, 13, 42, 45, 89);

        ClientTest clientTest = new ClientTest();
        clientTest.del(list);
//        System.out.println(clientTest.test0(list));
    }


    public void del(List<Integer> list){

        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }

//        for (Integer i : list) {
//            list.remove(i);
//        }
        System.out.println(list);

    }

    // n
    public List<Integer> test1(List<Integer> list) {

        if (list.isEmpty()) {
            return list;
        }

        Iterator<Integer> iterator = list.iterator();
        Integer currentValue = iterator.next();
        while (iterator.hasNext()) {
            Integer nextValue = iterator.next();
            if (!Objects.equals(nextValue, currentValue)) {
                currentValue = nextValue;
            } else {
                iterator.remove();
            }
        }

        return list;
    }

    //
    public List<Integer> test0(List<Integer> list) {

        if (list.isEmpty()) {
            return list;
        }

        int size = list.size();
        Integer maxValue = list.get(size - 1);
        byte[] finalArr = new byte[maxValue + 1];
        for (Integer i : list) {
            byte value = finalArr[i];
            if (value == 0) {
                finalArr[i]++;
            }
        }
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < finalArr.length; i++) {
            if (finalArr[i] != 0) {
                resultList.add(i);
            }

        }
        return resultList;
    }

}
