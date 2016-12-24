package ru.ncedu.java.my_project.classes;

import ru.ncedu.java.my_project.interfaces.Constants;
import ru.ncedu.java.my_project.interfaces.ProjectObjects;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Stack;

/**
 * Created by Zotov S. on 06.12.2016.
 */
public class ConsoleExplorer implements ProjectObjects, Constants {

    private File currentDirectory = null;
    private File[] filesInConsole = File.listRoots();
    private boolean finishFlag = false;
    private Stack history = new Stack();

    protected File getCurrentDirectory() {
        return currentDirectory;
    }

    protected void setCurrentDirectory(File file) {
        currentDirectory = file;
    }

    protected void setFilesInConsole (File[] files) {
        filesInConsole = files;
        history.push(files);
    }

    protected File[] getFilesInConsole () {
        return filesInConsole;
    }

    protected void toFinishProgram () {
        finishFlag = true;
    }

    public void setUp() {
        do {
            displayFilesInExplorer();
        } while(!select());
    }

    protected boolean select() {
        System.out.print("Write command:\n");
        if (scanner.hasNextInt()) {
            selectFileByNumber();
        }
        else {
            toCommand();
        }
        return finishFlag;
    }

    private void selectFileByNumber() {
        boolean select = select();
        try {
            int fileTableNumber = scanner.nextInt() - 1;
            if (fileTableNumber == Constants.CONSTANT_NUMBER_OF_PARENT_DIRECTORY_COMMAND) {
                openParent();
            }
            else {
                open(filesInConsole[fileTableNumber]);
            }
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Wrong number of file");
            select;
        }
        catch (IllegalArgumentException e) {
            System.out.println("This file can not be read");
        }
    }

    protected void openParent() {
        currentDirectory = currentDirectory.getParentFile();
        setFilesInConsole(openDirectory(currentDirectory));
    }

    protected void open(File file) {
        if (file.isDirectory()) {
            setFilesInConsole(openDirectory(file));
        }
        else {
            openFile(file);
        }
    }

    private File[] openDirectory(File directory) {
        try {
            currentDirectory = directory;
            return directory.listFiles();
        }
        catch(NullPointerException e) {
            return File.listRoots();
        }
    }

    private void openFile(File file) {
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        try {
            desktop.open(file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void toCommand() {
        String[] inputWords = scanner.nextLine().split(" ");
        String command = inputWords[0];
        Class clazz = explorerFunctions.getClass();
        if (inputWords.length == 1) {
            toInvokeCommandWithoutArguments(clazz, command);
        }
        else {
            String args[] = new String[inputWords.length - 1];
            System.arraycopy(inputWords, 1, args, 0, inputWords.length - 1);
            toInvokeCommandWithArguments(clazz, command, args);
        }
    }

    private void toInvokeCommandWithoutArguments(Class clazz, String command) {
        try {
            Method method = clazz.getDeclaredMethod(command);
            method.invoke(explorerFunctions);
        } catch (NoSuchMethodException e) {
            System.out.println("No such command");
            select();
        } catch (InvocationTargetException | IllegalAccessException e) {}
    }

    private void toInvokeCommandWithArguments(Class clazz, String command, String [] args) {
        Class[] paramTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            paramTypes[i] = String.class;
        }
        try {
            Method method = clazz.getDeclaredMethod(command, paramTypes);
            method.invoke(explorerFunctions, args);
        } catch (NoSuchMethodException e) {
            System.out.println("No such command");
            select();
        }
        catch (InvocationTargetException | IllegalAccessException e) {}
    }

    private void displayFilesInExplorer() {
        try {
            System.out.println("Current address: " + getCurrentDirectory().getPath());
            viewFiles();
        }
        catch (NullPointerException e)  {
            viewRoots();
        }
    }

    private void viewFiles() {
        ArrayList<ArrayList<StringBuffer>> table = new ArrayList<ArrayList<StringBuffer>>();
        table.add(additionalMethods.copy(FILES_TABLE_TITLE));
        table.add(new ArrayList<StringBuffer>(Arrays.asList(
                new StringBuffer("0"),
                new StringBuffer("\\..")
        )));

        for (int fileCollectionNumber = 0; fileCollectionNumber < filesInConsole.length; fileCollectionNumber++) {
            int fileTableNumber = fileCollectionNumber + 2;
            File displayedFile = filesInConsole[fileCollectionNumber];

            table.add(new ArrayList<StringBuffer>());

            table.get(fileTableNumber).add(new StringBuffer(new Integer(fileTableNumber).toString()));
            table.get(fileTableNumber).add(new StringBuffer(displayedFile.getName()));
            if (displayedFile.isDirectory()) {
                table.get(fileTableNumber).add(new StringBuffer("Directory"));
            }
            else {
                table.get(fileTableNumber).add(new StringBuffer("File"));
            }
            table.get(fileTableNumber).add(new StringBuffer(DATE_FORMAT_OF_LAST_MODIFIED.format(new Date(filesInConsole[fileCollectionNumber].lastModified())).toString()));
            if (displayedFile.isFile()) {
                table.get(fileTableNumber).add(new StringBuffer(new Long(displayedFile.length()).toString()).append(" kB"));
            }
        }
        tableViewer.alineTable(table);
    }

    private void viewRoots() {
        ArrayList<ArrayList<StringBuffer>> table = new ArrayList<ArrayList<StringBuffer>>();
        table.add(additionalMethods.copy(ROOT_TABLE_TITLE));
        for (int fileNumber = 1; fileNumber <= filesInConsole.length; fileNumber++) {
            File displayedFile = filesInConsole[fileNumber - 1];

            table.add(new ArrayList<StringBuffer>());

            table.get(fileNumber).add(new StringBuffer(new Integer(fileNumber).toString()));
            table.get(fileNumber).add(new StringBuffer(displayedFile.getPath()));
            if (displayedFile.canRead()) {
                table.get(fileNumber).add(new StringBuffer("Can be read"));
            }
            else {
                table.get(fileNumber).add(new StringBuffer("Can not be read"));
            }
            table.get(fileNumber).add(new StringBuffer(additionalMethods.toGB(displayedFile.getTotalSpace())).append(" GB"));
            table.get(fileNumber).add(new StringBuffer(additionalMethods.toGB(displayedFile.getFreeSpace())).append(" GB"));
        }
        tableViewer.alineTable(table);
    }
}
