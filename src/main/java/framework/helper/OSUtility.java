package framework.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OSUtility {

    public static String getOSName() {
        return System.getProperty("os.name");
    }

    public static boolean isMac() {
        return getOSName().toLowerCase().startsWith("mac");
    }

    public static boolean isWindows() {
        return getOSName().toLowerCase().startsWith("win");
    }

    public static String getOSBits() {
        return System.getProperty("os.arch");
    }

    public static boolean is32() {
        return getOSBits().equals("x86");
    }

    public static boolean is64() {
        if (isWindows()) {
            return (System.getenv("ProgramW6432") != null);
        } else {
            return !getOSBits().equals("x86");
        }
    }

    private static List<String> executeCommand(final String cmd) {
        List<String> output = new ArrayList<String>();
        Process p;
        try {
            p = Runtime.getRuntime().exec(cmd);
        } catch (IOException e1) {
            return output;
        }
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()), 8 * 1024);
        String s = null;
        try {
            while ((s = stdInput.readLine()) != null) {
                output.add(s);
            }
        } catch (IOException e) {
            return output;
        }

        return output;
    }


}
