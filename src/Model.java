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
        if (classifier == "RF") {
            randomForest(options, trainingData, testingData);
        } else if (classifier == "LR") {
            logisticRegression(options, trainingData, testingData);
        } else if (classifier == "NB") {
            NaiveBayes(options, trainingData, testingData);
        } else if (classifier == "MLP") {
            multilayerPerceptron(options, trainingData, testingData);
        } else if (classifier == "vote") {
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
