/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Carlos
 */
public class script_conceptual {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        String input = "C:\\Users\\Carlos\\projects\\shapes";
      
        /*List<String> projectsPath = new ArrayList<>();

        final Options options = new Options();

        String input = "";
     
        try {
            options.addOption("i", true, "input directory");

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("conceptual-coupling", options, true);

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                input = cmd.getOptionValue("i");
            }
        } catch (ParseException ex) {
            Logger.getLogger(script_conceptual.class.getName()).log(Level.SEVERE, null, ex);

        }*/
        File directory = new File(input + File.separator);

        String projectName = input.substring(input.lastIndexOf(File.separator) + 1, input.length());

        System.out.println("Processing files ...  " + projectName);
        //ReadConceptualCoupling.readFiles(path);
        ReadConceptualCoupling.createFinalResult(input);

        System.out.println("successfully concluded!" + projectName);
    }

}
