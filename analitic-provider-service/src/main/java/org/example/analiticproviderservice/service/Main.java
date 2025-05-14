package org.example.analiticproviderservice.service;

import org.example.analiticproviderservice.model.RequestPercent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<String> requests = List.of("POST", "POST", "DELETE", "GET", "POST", "POST", "GET");
        RequestPercent requestPercent = new RequestPercent();
        Map<String, Integer> map = new HashMap<>();
        List<String> originals = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < requests.size(); i++) {
            if (!originals.contains(requests.get(i))) {
                originals.add(requests.get(i));
            }
        }
        for (int i = 0; i < originals.size(); i++) {
            int num = 0;
            for (int j = 0; j < requests.size(); j++) {
                if (originals.get(i).equals(requests.get(j))) {
                    num++;
                }
            }
            nums.add(num);
        }
        for (int i = 0; i < originals.size(); i++) {
            map.put(originals.get(i), nums.get(i));
        }
        System.out.println(map);
        System.out.println(originals);
        for (int i = 0;i < originals.size(); i++) {
            String requestType = originals.get(i);
            int numRequestType = map.get(requestType);
            System.out.println(numRequestType + "    " + requestType);
            switch (requestType) {
                case "POST":
                    requestPercent.setRequestPostPercent((double) (100 * numRequestType) / requests.size());
                    break;
                case "GET":
                    requestPercent.setRequestGetPercent((double) (100 * numRequestType) / requests.size());
                    break;
                case "DELETE":
                    requestPercent.setRequestDeletePercent((double) (100 * numRequestType) / requests.size());
                    break;
            }
        }
        System.out.println(requestPercent.getRequestPostPercent());
        System.out.println(requestPercent.getRequestGetPercent());
        System.out.println(requestPercent.getRequestDeletePercent());
    }
}
