# COVID-19 County-Level Case Number Change Prediction by Combining Demographic Characteristics and Social Distancing Policies

## Authors
Megan Mun Li<sup>1</sup>, and Tsung-Ting Kuo, PhD<sup>2</sup>

<sup>1</sup>Division of Biological Sciences, University of California San Diego, La Jolla, CA, USA

<sup>2</sup>UCSD Health Department of Biomedical Informatics, University of California San Diego, La Jolla, CA, USA

## Introduction
This code predicts whether the next day would have an increase or decrease in the change of  COVID-19 case numbers relative to the previous date for United States counties.

## Data
The following two files used in our models represent the last date in our evaluation phase. Both files have 3,142 rows, which represent counties, and 201 columns, which include 200 features and 1 label.
1. train_2021-05-16.arff
2. test_2021-05-17.arff

## Installation
Internet connection is needed for downloading required components.
1. Prerequisites
   - Ubuntu (64-bit 18.04) or macOS v11
   - Java (1.8)
2. Libraries 
   - [Weka v3.8.4](https://sourceforge.net/projects/weka/files/weka-3-8/3.8.4/weka-3-8-4.zip/download?use_mirror=iweb)

## Running
1. To access our code, first clone our code using git or download the zip file of our code and unzip it.
2. Download the Weka v3.8.4 zip file from step (2) of the Installation steps above, and unzip it manually. Copy the weka.jar file from the unzipped folder into the lib folder within the COVID-19-Prediction folder.
3. To run COVID-19-Prediction, open a terminal window with superuser privileges and switch into the COVID-19-Prediction folder.
4. Set permissions on the scripts (allows everyone to read and execute the files) provided by running:

       chmod 0755 compile.sh
       chmod 0755 run.sh
4. To compile the code, run:

       ./compile.sh
5. To run desired classifier(s), run this below:

       ./run.sh <classifier>
       
    The possible ways to run the classifiers are displayed below:

       ./run.sh lr   # runs Logistic Regression (LR)
       ./run.sh nb   # runs Naive Bayes (NB)
       ./run.sh mlp  # runs Multi-Layer Perceptron (MLP)
       ./run.sh rf   # runs Random Forest (RF)
       ./run.sh avg  # runs Average Ensemble
       ./run.sh prod # runs Product Ensemble
       ./run.sh min  # runs Minimum Ensemble
       ./run.sh max  # runs Maximum Ensemble

The code will print out the Hyper-Parameter values and AUC scores for each classifier. It will also store this output in an log file, “main_output_log_<classifier>.txt”. For example, the output file for LR classifier is “main_output_log_lr.txt”.

In general, MLP and Ensemble methods (which include LR, NB, MLP, and RF) each take around 10 minutes to run, and the other classifiers each take less than 1 minute to run (the actual speed could depend on each users' computer specifications).
   
## Acknowledgement
The authors MML and T-TK were funded by the U.S. National Institutes of Health (NIH) (R00HG009680, R01HL136835, R01GM118609, R01HG011066, U24LM013755, and T15LM011271). The content is solely the responsibility of the author and does not necessarily represent the official views of the NIH. The funders had no role in study design, data collection and analysis, decision to publish, or preparation of the manuscript. The use of the UCSD Campus AWS cloud network was supported by Michael Hogarth, MD. 

## Contact 
Thank you for using our software. If you have any questions or suggestions, please kindly contact Megan Li at meganmli18@gmail.com.

## DOI
