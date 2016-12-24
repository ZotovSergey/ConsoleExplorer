package ru.ncedu.java.my_project.interfaces;

import ru.ncedu.java.my_project.classes.AdditionalMethods;
import ru.ncedu.java.my_project.classes.ConsoleExplorer;
import ru.ncedu.java.my_project.classes.ExplorerFunctions;
import ru.ncedu.java.my_project.classes.TableViewer;

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
