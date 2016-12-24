package ru.ncedu.java.my_project.classes;

import ru.ncedu.java.my_project.interfaces.Constants;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Zotov S. on 21.12.2016.
 */
public class AdditionalMethods implements Constants {
    protected String toGB(long value) {
        return String.format( Locale.US, "%.2f", (double) value / GB_MODIFIER);
    }

    protected ArrayList<StringBuffer> copy(ArrayList<StringBuffer> title) {
        ArrayList<StringBuffer> copyOfTitle = new ArrayList<StringBuffer>();
        for (int i = 0; i < title.size(); i++) {
            copyOfTitle.add(new StringBuffer(title.get(i)));
        }
        return copyOfTitle;
    }
}
