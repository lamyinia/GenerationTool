package org.com.javafactory.builder;

import org.com.javafactory.bean.Constants;
import org.com.javafactory.bean.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuildComment {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void buildClassComment(BufferedWriter bw, String text, boolean writeAuthor, boolean writeTime) throws IOException {
        bw.write("\n\n/**");
        if (writeAuthor){
            bw.write("\n * @author: " + Constants.COMMENT_AUTHOR);
        }
        bw.write("\n * @description: " + text);
        if (writeTime){
            bw.write("\n * @time: " + sdf.format(new Date()));
        }
        bw.write("\n*/\n");
    }

    public static void buildBeanComment(BufferedWriter bw, String text) throws IOException {
        bw.write("\n\t /**");
        bw.write("\n\t * " + text);
        bw.write("\n\t */");
    }
}
