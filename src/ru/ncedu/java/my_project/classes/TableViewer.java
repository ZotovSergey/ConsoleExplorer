package ru.ncedu.java.my_project.classes;

import java.util.ArrayList;

/**
 * Created by Zotov S. on 21.12.2016.
 */
public class TableViewer {

    protected void alineTable(ArrayList<ArrayList<StringBuffer>> table) {
        int maxCountOfSymbols [] = new int[table.get(0).size()];
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                if (table.get(i).get(j).length() > maxCountOfSymbols[j]) {
                    maxCountOfSymbols[j] = table.get(i).get(j).length();
                }
            }
        }

        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                int countOfExtraTabs = (maxCountOfSymbols[j] - table.get(i).get(j).length());
                for (int k = 0; k < countOfExtraTabs; k++) {
                    table.get(i).get(j).append(" ");
                }
                table.get(i).get(j).append("\t\t");
            }
        }
        this.displayTable(table);
    }

    private void displayTable(ArrayList<ArrayList<StringBuffer>> table) {
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                System.out.print(table.get(i).get(j).toString());
            }
            System.out.println();
        }
    }
}
