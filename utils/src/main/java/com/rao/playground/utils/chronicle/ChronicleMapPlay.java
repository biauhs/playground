package com.rao.playground.utils.chronicle;


import net.openhft.chronicle.map.ChronicleMap;

import java.io.File;
import java.io.IOException;

public class ChronicleMapPlay {

    public static void main (String... args) throws IOException {
        File file = new File("chronicle.map");
        ChronicleMap<String, String> chronicleMap = ChronicleMap
                .of(String.class, String.class)
                .averageKey("Amsterdam")
                .averageValue("lsjdhgljsfglfkglkfjg")
                .entries(50)
                .createOrRecoverPersistedTo(file, false);


        chronicleMap.putIfAbsent("Hello", "ITS ME CALLING");

        System.out.println(chronicleMap.get("Hello"));

    }

}
