package com.qburst.ai.fake_image_detection.neural_network.core;

import com.qburst.ai.fake_image_detection.neural_network.thread_sync.NotifyingThread;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.imgrec.ImageRecognitionPlugin;

public class neural_net_processor extends NotifyingThread {

    static BufferedImage image;
    static NeuralNetwork nnet;
    static ImageRecognitionPlugin imageRecognition;

    public neural_net_processor(BufferedImage image) throws FileNotFoundException {
        this.image = image;
        nnet = NeuralNetwork.load(new FileInputStream("/home/qbuser/NeurophProjects/BinaryClassification2/Neural Networks/CNN2.nnet")); // load trained neural network saved with Neuroph Studio
        imageRecognition = (ImageRecognitionPlugin) nnet.getPlugin(ImageRecognitionPlugin.class); // get the image recognition plugin from neural network
    }

    @Override
    public void doRun() {

    }

    public static void main(String[] args) {
        try {
            System.out.println("Loading Image....");
            image = ImageIO.read(new File("/home/afsal/Desktop/Screenshot 2016-07-24 12:55:50.png"));
            System.out.println("Loading NN....");
            File NNetwork = new File("nnet/CNN2.nnet");
            if(!NNetwork.exists())
            {
                System.err.println("Cant Find NN");
                return;
            }
            nnet = NeuralNetwork.load(new FileInputStream(NNetwork)); // load trained neural network saved with Neuroph Studio
            System.out.println("Load Image Recog Plugin....");
            imageRecognition = (ImageRecognitionPlugin) nnet.getPlugin(ImageRecognitionPlugin.class); // get the image recognition plugin from neural network
            System.out.println("Recognize Image....");
            HashMap<String, Double> output = imageRecognition.recognizeImage(image);
            System.out.println("Output is....");
            System.out.println(output.toString());
        } catch (IOException ex) {
            Logger.getLogger(neural_net_processor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}