package apachecommpkg;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by wei.ma on 2017/2/28.
 */
public class ReaderUtil_NoPool {
    public ReaderUtil_NoPool() {
    }

    /**
     * Dumps the contents of the {@link Reader} to a
     * String, closing the {@link Reader} when done.
     */
    public String readToString(Reader in) throws IOException {
        StringBuffer buf = new StringBuffer();
        try {
            for (int c = in.read(); c != -1; c = in.read()) {
                buf.append((char) c);
            }
            return buf.toString();
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                // ignored
            }
        }
    }

    public static void main(String[] args) {
        ReaderUtil_NoPool readerUtil_noPool = new ReaderUtil_NoPool();
        String in = "ssssssss";

    }
}
