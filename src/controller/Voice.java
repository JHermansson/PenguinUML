package controller;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

/**
 * Created by SodaSaft on 2016-04-12.
 */
public class Voice {

    //public ConfigurationManager cm = new ConfigurationManager("src/controller/helloworld.config.xml");
    //public Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
    //public Microphone microphone = (Microphone) cm.lookup("microphone");

    public ConfigurationManager cm = null;
    public Recognizer recognizer = null;
    public Microphone microphone = null;

    public String voice(){ //added
        System.out.println("Banan1");
        System.out.println("Banan2");

        if(cm == null) {
            cm = new ConfigurationManager("src/controller/helloworld.config.xml");
            System.out.println("Banan2,5");
        }

        if(recognizer == null) {
            recognizer = (Recognizer) cm.lookup("recognizer");
            recognizer.allocate();
        }

        System.out.println("Banan3");

        // start the microphone or exit if the programm if this is not possible
        if(microphone == null) {
            microphone = (Microphone) cm.lookup("microphone");
            if (!microphone.startRecording()) {
                //System.out.println("Cannot start microphone.");
                System.out.println("Banan4");
                recognizer.deallocate();
                System.exit(1);
            }
        }
        else {
            microphone.clear();
        }



        //System.out.println("FILL THIS IN LATER");

        // loop the recognition until the programm exits.
        while (true) {

            //System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();
            String resultText;

            // System.out.println("HEJSAN: " + result);
            if (result == null) {
                resultText = "";
            }
            else {
                resultText = result.getBestFinalResultNoFiller();
            }

            if (resultText.equals("create class")) {
                System.out.println("You said:  " + resultText + "\n");
                return resultText;
            }
            else if (resultText.equals("create edge")) {
                System.out.println("You said:  " + resultText + "\n");
                return resultText;
            }
            else if (resultText.equals("create package")) {
                System.out.println("You said:  " + resultText + "\n");
                return resultText;
            }
            else if (resultText.equals("choose draw")) {
                System.out.println("You said:  " + resultText + "\n");
                return resultText;
            }
            else if (resultText.equals("choose select")) {
                System.out.println("You said:  " + resultText + "\n");
                return resultText;
            }
            else if (resultText.equals("choose move")) {
                System.out.println("You said:  " + resultText + "\n");
            }
            else if (resultText.equals("undo")) {
                System.out.println("You said:  " + resultText + "\n");
                return resultText;
            }
            else if (resultText.equals("redo")) {
                System.out.println("You said:  " + resultText + "\n");
                return resultText;
            }
            else {
                System.out.println("NOPE " + resultText + "\n");
            }
        }

    }


    public String nameClassVoice(){ //added

        if(cm == null) {
            cm = new ConfigurationManager("src/controller/helloworld.config.xml");
            System.out.println("Banan2,5");
        }

        if(recognizer == null) {
            recognizer = (Recognizer) cm.lookup("recognizer");
            recognizer.allocate();
        }

        System.out.println("Banan3");

        // start the microphone or exit if the programm if this is not possible
        if(microphone == null) {
            microphone = (Microphone) cm.lookup("microphone");
            if (!microphone.startRecording()) {
                //System.out.println("Cannot start microphone.");
                System.out.println("Banan4");
                recognizer.deallocate();
                System.exit(1);
            }
        }
        else {
            microphone.clear();
        }



        //System.out.println("FILL THIS IN LATER");

        // loop the recognition until the programm exits.

        while (true) {

            //System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();
            String resultText;

            // System.out.println("HEJSAN: " + result);
            if (result == null) {
                resultText = "";
            }
            else {
                resultText = result.getBestFinalResultNoFiller();
            }

            if (resultText.equals("name car")) {
                System.out.println("You said: " + resultText + "\n");
                return "Car";
            } else if (resultText.equals("name bank")) {
                System.out.println("You said:  " + resultText + "\n");
                return "Bank";
            } else if (resultText.equals("create class")) {
                System.out.println("You said:  " + resultText + "\n");
            } else if (resultText.equals("create edge")) {
                System.out.println("You said:  " + resultText + "\n");
            } else if (resultText.equals("create package")) {
                System.out.println("You said:  " + resultText + "\n");
            } else if (resultText.equals("delete class car")) {
                System.out.println("You said:  " + resultText + "\n");
            } else if (resultText.equals("delete class bank")) {
                System.out.println("You said:  " + resultText + "\n");
            } else if (resultText.equals("choose draw")) {
                System.out.println("You said:  " + resultText + "\n");
            } else if (resultText.equals("choose select")) {
                System.out.println("You said:  " + resultText + "\n");
            } else if (resultText.equals("choose connect")) {
                System.out.println("You said:  " + resultText + "\n");
            } else if (resultText.equals("choose drag")) {
                System.out.println("You said:  " + resultText + "\n");
            } else if (resultText.equals("undo")) {
                System.out.println("You said:  " + resultText + "\n");
            } else if (resultText.equals("redo")) {
                System.out.println("You said:  " + resultText + "\n");
            } else {
                System.out.println("NOPE " + resultText + "\n");
            }
        }

    }

}
