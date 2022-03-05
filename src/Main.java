import java.util.ArrayList;
import java.util.Arrays;

public class Main {     
    public static void main(final String[] args) throws Exception { 	
    	String trainLabelDate = "2021-05-16";	
        testRun(trainLabelDate, "LR","");     
        testRun(trainLabelDate, "MLP","");	
    	testRun(trainLabelDate, "RF","");
        testRun(trainLabelDate, "NB","");
	testRun(trainLabelDate, "vote","AVG");
	testRun(trainLabelDate, "vote","PROD");
	testRun(trainLabelDate, "vote","MIN");
	testRun(trainLabelDate, "vote","MAX");
    }
          
    @SuppressWarnings("static-access")
     public static void testRun(String date, String classifier, String voteParam) throws Exception {
    	// Set best found hyperparameter values depending on given classifier
        ArrayList<String> optionArr = new ArrayList<String>();
        if (classifier == "LR") {
        	/*
        	 * Ridge (R) = 10
        	 */
            optionArr = new ArrayList<>(Arrays.asList("-R", "10"));
        } else if (classifier == "MLP") {
        	/*
        	 * Learning rate (L) = 0.1 
        	 * Momentum rate (M) = 0.2
        	 * Number of epochs (N) = 500
        	 * Number of nodes on each layer (H) = (attributes + classes) / 2
        	 * Presence of learning rate decay = false
        	 * Number of consecutive errors(E) = 15
        	 */
            optionArr = new ArrayList<>(Arrays.asList("-L", "0.1","-M","0.2","-N","500","-H","a","-E","15"));
        } else if (classifier == "RF") {
        	/*
        	 * Bag size (P) = 100
        	 * Number of iterations (I) = 10
        	 * Number of attributes (K) = 10
        	 */
            optionArr = new ArrayList<>(Arrays.asList("-P", "100","-I","10","-K","10"));
        } else if (classifier == "NB") {
        	/*
        	 * Presence of supervised discretization = true
        	 */
        	optionArr = new ArrayList<>(Arrays.asList("-D"));
        } else if (classifier == "vote") {
        	optionArr = new ArrayList<>(Arrays.asList("-R", voteParam, "-B", "weka.classifiers.functions.Logistic -R 10.0", "-B", "weka.classifiers.functions.MultilayerPerceptron -L 0.1 -M 0.2 -N 500 -H a -E 15", "-B", "weka.classifiers.trees.RandomForest -P 100 -I 10 -K 10" ,"-B","weka.classifiers.bayes.NaiveBayes -D"));
 
        }
        
        // Convert options from ArrayList to Array
        String[] options = new String[optionArr.size()];
        for (int i = 0; i < optionArr.size(); ++i) {
            options[i] = optionArr.get(i);
        }
                
        // Train and test on files and compute AUC
        Model merge = new Model("train_2021-05-16.arff", "test_2021-05-17.arff", classifier, options, voteParam);
        System.out.println("AUC Score for "+classifier+voteParam+": "+ merge.getAUC());
        System.out.println();
    } 
}
