package ru.ncedu.java.my_project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Zotov S. on 11.12.2016.
 */
public interface Constants {
    int COUNT_OF_SPACES_IN_TAB = 4;

    int CONSTANT_NUMBER_OF_PARENT_DIRECTORY_COMMAND = -1;

    int GB_MODIFIER = 1073741824;

    SimpleDateFormat DATE_FORMAT_OF_LAST_MODIFIED = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    ArrayList<StringBuffer> ROOT_TABLE_TITLE = new ArrayList<StringBuffer>(Arrays.asList(
            new StringBuffer("N"),
            new StringBuffer("NAME"),
            new StringBuffer("CONDITION"),
            new StringBuffer("TOTAL SPACE"),
            new StringBuffer("FREE SPACE")));

    ArrayList<StringBuffer> FILES_TABLE_TITLE = new ArrayList<StringBuffer>(Arrays.asList(
            new StringBuffer("N"),
            new StringBuffer("NAME"),
            new StringBuffer("FILE'S TYPE"),
            new StringBuffer("LAST MODIFIED"),
            new StringBuffer("SIZE")
    ));
}
