import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job; // attention a prendre toujours mapraduce et non pas mapred.
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * Created by od on 2/26/2016.
 */
public class OdDriver {

   public static void main (String [] args) throws IOException, ClassNotFoundException, InterruptedException {
       final Configuration configuration = new Configuration(); // Par default, le framework va prendre la bonne conf
       // Le job, c'est un peu le client du map-reduce
       final Job job = Job.getInstance(configuration, "sample-job");

       job.setJarByClass(OdDriver.class); //  Bonne vieille pratique bonne pour la compatibilité entre run-time

       job.setInputFormatClass(TextInputFormat.class);

       FileInputFormat.addInputPath(job, new Path("/tmp/test.in")); // Attention ne pas mettre de s à Path

       job.setMapperClass(OdMapper.class);

       job.submit();
   }
}
