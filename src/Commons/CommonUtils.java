package Commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CommonUtils {

    public static BufferedReader createBufferedReader(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        return new BufferedReader(fileReader);
    }

}
