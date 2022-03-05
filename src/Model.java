import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.Vote;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class Model {
    public static double AUCscore;
    
    public Model(String trainName, String testName, String classifier, String[] options, String voteParam) throws Exception {
        Instances trainingData = new Instances((Reader)new BufferedReader(new FileReader("./data/" + trainName)));
        Instances testingData = new Instances((Reader)new BufferedReader(new FileReader("./data/" + testName)));
        if (classifier == "rf") {
            System.out.println("Random Forest");
            randomForest(options, trainingData, testingData);
        } else if (classifier == "lr") {
	    System.out.println("Logistic Regression");
            logisticRegression(options, trainingData, testingData);
        } else if (classifier == "nb") {
	    System.out.println("Naive Bayes");
            NaiveBayes(options, trainingData, testingData);
        } else if (classifier == "mlp") {
	    System.out.println("Multi-layer Perceptron");
            multilayerPerceptron(options, trainingData, testingData);
        } else if (classifier == "vote") {
	    if (voteParam == "avg") {
              System.out.println("Average Ensemble");
            } else if (voteParam == "prod") {
	      System.out.println("Product Ensemble");
            } else if (voteParam == "min") {
	      System.out.println("Minimum Ensemble");
            } else {
	      System.out.println("Maximum Ensemble");
            }
            vote(options, voteParam, trainingData, testingData);
        }
    }
    
    public static void logisticRegression(String[] options, Instances train, Instances test) throws Exception {        
    	// Set hyperparameter values and train the model
    	String[] copiedOptions = options.clone();
        train.setClassIndex(train.numAttributes() - 1);
        Logistic logisticClassifier = new Logistic();
        logisticClassifier.setOptions(copiedOptions);
        logisticClassifier.buildClassifier(train);
        
        // Verify options were set correctly
	System.out.print("Hyper-Parameters: ");
        String[] getOptions = logisticClassifier.getOptions();
        for (String opt : getOptions) {
        	 System.out.print(opt+" ");
        }
        System.out.println(); 
        
        // Test model
        Evaluation eval = new Evaluation(train);      
        test.setClassIndex(test.numAttributes() - 1);
        eval.evaluateModel((Classifier)logisticClassifier, test, new Object[0]);
        
        // Retrieve and store AUC score 
        Model.AUCscore = eval.areaUnderROC(1);
    }
    
    public static void NaiveBayes(String[] options, Instances train, Instances test) throws Exception {
    	// Set hyperparameter values and train the model
    	String[] copiedOptions = options.clone();
        train.setClassIndex(train.numAttributes() - 1);
        NaiveBayes NB = new NaiveBayes();
        NB.setOptions(copiedOptions);
        NB.buildClassifier(train);
        
        // Verify options were set correctly
	System.out.print("Hyper-Parameters: ");
        String[] getOptions = NB.getOptions();
        for (String opt : getOptions) {
        	 System.out.print(opt+" ");
        }
        System.out.println(); 
        
        // Test model
        Evaluation eval = new Evaluation(train);
        test.setClassIndex(test.numAttributes() - 1);
        eval.evaluateModel((Classifier)NB, test, new Object[0]);
        
        // Retrieve and store AUC score 
        Model.AUCscore = eval.areaUnderROC(1);
    }
    
    public static void randomForest(String[] options, Instances train, Instances test) throws Exception {
    	// Set hyperparameter values and train the model
        train.setClassIndex(train.numAttributes() - 1);
        RandomForest RF = new RandomForest();
        RF.setBagSizePercent(100);
        RF.setNumIterations(10);
        RF.setNumFeatures(10);
        RF.buildClassifier(train);

        // Verify options were set correctly
	System.out.print("Hyper-Parameters: ");
        String[] getOptions = RF.getOptions();
        for (String opt : getOptions) {
        	 System.out.print(opt+" ");
        }
        System.out.println(); 
        
        // Test model
        Evaluation eval = new Evaluation(train);
        test.setClassIndex(test.numAttributes() - 1);
        eval.evaluateModel((Classifier)RF, test, new Object[0]);
        
       // Retrieve and store AUC score 
        Model.AUCscore = eval.areaUnderROC(1);
    }
    
    public static void multilayerPerceptron(String[] options, Instances train, Instances test) throws Exception {
    	// Set hyperparameter values and train the model
    	String[] copiedOptions = options.clone();
        train.setClassIndex(train.numAttributes() - 1);
        MultilayerPerceptron MLP = new MultilayerPerceptron();
        MLP.setOptions(copiedOptions);
        MLP.buildClassifier(train);
        
        // Verify options were set correctly
	System.out.print("Hyper-Parameters: ");
        String[] getOptions = MLP.getOptions();
        for (String opt : getOptions) {
        	 System.out.print(opt+" ");
        }
        System.out.println(); 
        
        // Test model
        Evaluation eval = new Evaluation(train);
        test.setClassIndex(test.numAttributes() - 1);
        eval.evaluateModel((Classifier)MLP, test, new Object[0]);
        
        // Retrieve and store AUC score 
        Model.AUCscore = eval.areaUnderROC(1);
    }
    
    public static void vote(String[] options, String voteParam, Instances train, Instances test) throws Exception {
    	// Set hyperparameter values and train the model
    	String[] copiedOptions = options.clone();
        train.setClassIndex(train.numAttributes() - 1);
        Vote vote = new Vote();
        vote.setOptions(copiedOptions);
        vote.buildClassifier(train);
        
        // Verify options were set correctly
	System.out.print("Hyper-Parameters: ");
        String[] getOptions = vote.getOptions();
        for (String opt : getOptions) {
        	 System.out.print(opt+" ");
        }
        System.out.println(); 
        
        // Test model
        Evaluation eval = new Evaluation(train);
        test.setClassIndex(test.numAttributes() - 1);
        eval.evaluateModel((Classifier)vote, test, new Object[0]);
        
        // Retrieve and store AUC score 
        Model.AUCscore = eval.areaUnderROC(1);
    }
    
    public static double getAUC() throws Exception {
        return Model.AUCscore;
    }
    
}
