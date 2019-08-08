import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String inputPath = "";
        for (; ; ) {
            try {
                long length;
                inputPath = scanner.nextLine();
                File folder = new File(inputPath.trim());
                if (folder.isDirectory()) {
                    length = getFolderLength(folder);
                } else {
                    length = folder.length();
                }
                printLength(length);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (inputPath.compareTo("exit") == 0) {
                break;
            }
        }
    }

    public static long getFolderLength(File folder) {
        long length = 0;
        File[] files = folder.listFiles();
        Optional<Long> optionalLong = Arrays.stream(files).
                map(file -> file.isDirectory() ? getFolderLength(file) : file.length()).
                reduce((s1, s2) -> s1 + s2);
        if (optionalLong.isPresent())
            length = optionalLong.get().longValue();
        return length;
    }

    public static void printLength(long lengthLongInBytes) {
        double lengthInByte = lengthLongInBytes;
        if (lengthInByte > 1024) {
            double lengthInKByte = lengthInByte / 1024;
            if (lengthInKByte > 1024) {
                double lengthInMByte = lengthInKByte / 1024;
                if (lengthInMByte > 1024) {
                    double lengthInGByte = lengthInMByte / 1024;
                    System.out.printf("length = %.2f GB\n", lengthInGByte);
                } else {
                    System.out.printf("length = %.2f MB\n", lengthInMByte);
                }
            } else {
                System.out.printf("length = %.2f KB\n", + lengthInKByte);
            }

        } else {
            System.out.printf("length = %.2f Bytes\n", + lengthInByte);
        }
    }
}
