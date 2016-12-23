package ru.ncedu.java.my_project;

import java.util.Scanner;

/**
 * Created by Zotov S. on 11.12.2016.
 */
public interface ProjectObjects {
    AdditionalMethods additionalMethods = new AdditionalMethods();
    ConsoleExplorer console = new ConsoleExplorer();
    ExplorerFunctions explorerFunctions = new ExplorerFunctions();
    TableViewer tableViewer = new TableViewer();
    Scanner scanner = new Scanner(System.in);
}
