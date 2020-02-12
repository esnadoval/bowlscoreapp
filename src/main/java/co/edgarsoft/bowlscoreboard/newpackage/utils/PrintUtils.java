/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.newpackage.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class with methods for printing on screen
 *
 * @author ed.sandoval1644
 */
public class PrintUtils {

    public static PrintUtils getInstance() {
        return new PrintUtils();
    }

    /**
     * Method used for print a table in row, column format Taken from
     * https://codereview.stackexchange.com/questions/213208/printing-out-a-table-in-console
     *
     * @param rows list of strings on column
     * @param spacing column spacing (spaces beetween)
     * @return Formated table of inputs
     */
    public String printTable(List<List<String>> rows, int spacing) {
        List<Integer> maxLengths = findMaxLengths(rows);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows.get(0).size(); i++) {
            for (int j = 0; j < rows.size(); j++) {
                String currentValue = rows.get(j).get(i);
                sb.append(currentValue);
                for (int k = 0; k < (maxLengths.get(j) - currentValue.length() + spacing); k++) {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    /**
     * Adds leading spaces to a text
     *
     * @param leadingSpaces nomber of leading spaces
     * @param str string to lead
     * @return
     */
    public String getFormatedLeadingSpace(int leadingSpaces, String str) {

        return String.format("%1$" + leadingSpaces + "s", str);
    }

    /**
     * Adds trailing spaces to a text
     *
     * @param trailSpaces nomber of leading spaces
     * @param str string to trail
     * @return
     */
    public String getFormatedTrailingSpace(int trailSpaces, String str) {

        return String.format("%1$-" + trailSpaces + "s", str);
    }

    /**
     * Auxiliary method to find mac lengths of rows and columns
     *
     * @param rows 2 dimension list
     * @return max lengths
     */
    private List<Integer> findMaxLengths(List<List<String>> rows) {
        List<Integer> maxLengths = new ArrayList<Integer>();
        for (List<String> row : rows) {
            int maxLength = 0;
            for (String value : row) {
                if (value.length() > maxLength) {
                    maxLength = value.length();
                }
            }
            maxLengths.add(maxLength);
        }
        return maxLengths;
    }

}
