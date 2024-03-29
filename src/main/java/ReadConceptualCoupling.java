/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 *
 * @author Cristiane
 */
public class ReadConceptualCoupling {

    public static void readFiles(String filePath) throws IOException {

        String SHAMerge;

        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return ((file.getName().endsWith(".txt")) && (!(file.getName().startsWith("Especial"))));
            }
        };
        filePath = filePath + File.separator + "Output" + File.separator;
        String filePathName = filePath + "MergeConceptualCoupling";
        FileWriter arquivo = null;

        File directory = new File(filePath);
        File files[] = directory.listFiles(filter);
        for (File file : files) {
            SHAMerge = file.getName().substring(file.getName().lastIndexOf("Coupling") + 8, file.getName().length() - 4);
            arquivo = new FileWriter(new File(filePathName + SHAMerge + ".txt"));

            FileInputStream stream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            Double threshold;
            String line = br.readLine();
            while (line != null) {
                String methodA = line.substring(0, line.indexOf(','));
                String methodB = line.substring(line.indexOf(',') + 1, line.lastIndexOf(','));
                String similarity = line.substring(line.lastIndexOf(',') + 1, line.length());
                line = br.readLine();

                threshold = Double.parseDouble(similarity);

                if ((methodA.startsWith("Left") && methodB.startsWith("Right"))) {
                    arquivo.write(SHAMerge + "," + methodA + "," + methodB + "," + threshold + "\n");
                }
            }
            arquivo.close();
        }
    }

    public static void createFinalResult(String filePath) throws IOException {

        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().startsWith("Merge");
            }
        };

        String filePath_output = filePath + File.separator + "Output" + File.separator;
        String filePathName = filePath_output + "FinalResultMergeConceptualCoupling.txt";
        try (FileWriter arquivo = new FileWriter(new File(filePathName))) {
            File directory = new File(filePath_output);
            File files[] = directory.listFiles(filter);
            for (File file : files) {
                FileInputStream stream = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader br = new BufferedReader(reader);
                Double threshold;
                Double total_intensity = 0.0;
                String SHAMerge = "";
                String similarity;
                String leftMethod;
                String rightMethod;
                int coupling = 0;
                SHAMerge = file.getName().substring(file.getName().lastIndexOf("Coupling") + 8, file.getName().length() - 4);
                String line = br.readLine();
                while (line != null) {
                    SHAMerge = line.substring(0, 40);
                    leftMethod = line.substring(41, line.indexOf(",R"));
                    rightMethod = line.substring(line.indexOf(",R") + 1, line.lastIndexOf(','));
                    similarity = line.substring(line.lastIndexOf(',') + 1, line.length());
                    line = br.readLine();
                    
                    arquivo.write(SHAMerge + "," + leftMethod + "," + rightMethod + "," + similarity + "\n");

                    //if (threshold >= 0.0) {// It calculates the intensity according to the threshold
                     //   coupling++; //It calculates the number of couplings according to the threshold
                     //   total_intensity = total_intensity + threshold; // It calculates the intensity according to the threshold
                   // }
                } //chunks is the set of modified methods, that is, number of text files
                String filePath_input = "";
                filePath_input = filePath + File.separator + "Output" + File.separator;
                String filePathName_input = filePath_input + "MergeConceptualCoupling" + SHAMerge + ".txt";

                //if (!(SHAMerge.isEmpty())) {
                File arquivoLeitura = new File(filePathName_input);
                long tamanhoArquivo = arquivoLeitura.length();
                FileInputStream fs = new FileInputStream(filePathName_input);
                DataInputStream in = new DataInputStream(fs);

                LineNumberReader lineRead = new LineNumberReader(new InputStreamReader(in));
                lineRead.skip(tamanhoArquivo);

                int pairs_cartesian_product = lineRead.getLineNumber();
                double normalized_coupling = 0;
                // double third = 0;

                //if (pairs_cartesian_product > 0) {
                //    normalized_coupling = total_intensity / pairs_cartesian_product;
                //}

                /*if (coupling > 0) {
                    third = total_intensity / coupling;
                }*/
                //arquivo.write(SHAMerge + "," + pairs_cartesian_product + "," + total_intensity + "," + normalized_coupling + "\n");
                
                //arquivo.write(SHAMerge + "," + pairs_cartesian_product + "," + total_intensity + "," + normalized_coupling + "\n");
                
                /*} else {
                    arquivo.write(SHAMerge + "," + total_intensity + "\n");
                }*/

            }
            arquivo.close();
        }

    }
}
