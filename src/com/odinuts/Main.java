package com.odinuts;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<String> nonMRIInstructions = new ArrayList<String>() {{
        add("CLA");
        add("CLE");
        add("CMA");
        add("CME");
        add("CIR");
        add("CIL");
        add("INC");
        add("SPA");
        add("SNA");
        add("SZA");
        add("SZE");
        add("HLT");
        add("INP");
        add("OUT");
        add("SKI");
        add("SKO");
        add("ION");
        add("IOF");
    }};
    private static List<String> MRIInstructions = new ArrayList<String>() {{
        add("AND");
        add("ADD");
        add("LDA");
        add("STA");
        add("BUN");
        add("BSA");
        add("ISZ");
    }};
    private static List<String> instructions = readFile();
    private static Map<String, Integer> instructionsAddresses = new LinkedHashMap<>();
    private static Map<String, Integer> labelAddresses = new LinkedHashMap<>();
    private static Map<String, Integer> output = new HashMap<>();
    private static Map<Integer, String> readyOutput = new LinkedHashMap<>();
    private static int LC;

    public static void main(String[] args) {
        initLC(instructions);
        initInstructionsAddresses(instructions, instructionsAddresses);
        initLabelAddresses(instructions, labelAddresses);
        extractMRI(instructions);
        extractNonMRIs(instructions);
        extractPseudo(instructions);
        readyOutput = reverse(sortByValue(output));
        /*for (Map.Entry<Integer, String> entry : readyOutput.entrySet()) {
            StringBuilder line = new StringBuilder(entry.getValue());
            while (line.length() < 15) {
                line.insert(0, "0");
            }
            System.out.println(line);
        }*/
        writeFile();
    }

    private static void initLC(List<String> instructions) {
        if (instructions.get(0).contains("ORG")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 8; i < instructions.get(0).length(); i++) {
                sb.append(instructions.get(0).charAt(i));
                LC = Integer.parseInt(sb.toString());
            }
        } else {
            LC = 0;
        }
    }

    private static void initInstructionsAddresses(List<String> instructions, Map<String, Integer> instructionsAddresses) {
        for (int i = 1; i < instructions.size(); i++) {
            instructionsAddresses.put(instructions.get(i), LC);
            LC++;
        }
    }

    private static void initLabelAddresses(List<String> instructions, Map<String, Integer> labelAddresses) {
        for (String instruction : instructions) {
            if (instruction.contains(",")) {
                if (instruction.contains("DEC")) {
                    labelAddresses.put(instruction.substring(0, instruction.indexOf(","))
                            + "DEC", instructionsAddresses.get(instruction));
                } else if (instruction.contains("HEX")) {
                    labelAddresses.put(instruction.substring(0, instruction.indexOf(","))
                            + "HEX", instructionsAddresses.get(instruction));
                }
            }
        }
    }

    private static void extractNonMRIs(List<String> instructions) {
        for (String instruction : instructions) {
            for (String nonMRI : nonMRIInstructions) {
                if (instruction.contains(nonMRI)) {
                    output.put(
                            Instructions.NonMRI.getBinaryValue(nonMRI.toLowerCase()),
                            instructionsAddresses.get(instruction));
                }
            }
        }
    }

    private static void extractMRI(List<String> instructions) {
        List<String> labels = new ArrayList<>(labelAddresses.keySet());
        for (String instruction : instructions) {
            for (String mri : MRIInstructions) {
                if (instruction.contains(mri)) {
                    for (String label : labels) {
                        if (instruction.contains(label.substring(0, label.length() - 3))) {
                            output.put(
                                    convertToBinary(Instructions.MRI.getValue(mri.toLowerCase()).
                                            concat(getLabelAddress(label)), 16),
                                    instructionsAddresses.get(instruction));
                        }
                    }
                }
            }
        }
    }

    private static void extractPseudo(List<String> instructions) {
        for (String instruction : instructions) {
            if (instruction.contains("DEC")) {
                output.put(convertToBinary(getLabelValue(instruction), 10),
                        instructionsAddresses.get(instruction));
            } else if (instruction.contains("HEX")) {
                output.put(convertToBinary(getLabelValue(instruction), 16),
                        instructionsAddresses.get(instruction));
            }
        }
    }

    private static String getLabelValue(String instruction) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < instruction.length(); i++) {
            if (String.valueOf(instruction.charAt(i)).matches("^[0-9]*$")) {
                sb.append(instruction.charAt(i));
            }
        }
        return sb.toString();
    }

    private static String getLabelAddress(String address) {
        return labelAddresses.get(address).toString();
    }

    private static String convertToBinary(String s, int radix) {
        return new BigInteger(s, radix).toString(2);
    }

    private static List<String> readFile() {
        List<String> s = new ArrayList<>();

        try {
            try (BufferedReader br = new BufferedReader(new FileReader("IN.txt"))) {
                String line = br.readLine();
                while (line != null) {
                    s.add(line);
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    private static void writeFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("OUT.txt"));
            for (Map.Entry<Integer, String> entry : readyOutput.entrySet()) {
                StringBuilder line = new StringBuilder(entry.getValue());
                while (line.length() < 15) {
                    line.insert(0, "0");
                }
                bw.write(line.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    private static <K, V> LinkedHashMap<V, K> reverse(Map<K, V> map) {
        LinkedHashMap<V, K> rev = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet())
            rev.put(entry.getValue(), entry.getKey());
        return rev;
    }
}