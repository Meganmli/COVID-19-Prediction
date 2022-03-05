# COVID-19 County-Level Case Number Change Prediction by Combining Demographic Characteristics and Social Distancing Policies

Authors
---------------	
Megan Mun Li1, and Tsung-Ting Kuo, PhD2
1 Division of Biological Sciences, University of California San Diego, La Jolla, CA, USA
2 UCSD Health Department of Biomedical Informatics, University of California San Diego, La Jolla, CA, USA

## Introduction
This code predicts whether the next day would have an increase or decrease in the case number change of COVID-19 relative to the previous date for United States counties.

## Data
The following two files used in our models represent the last date in our evaluation phase. Both files have 3,142 rows, which represents counties, and 201 columns, which includes 200 features and 1 label.
1. train_2021-05-16.arff
2. test_2021-05-17.arff

## Installation
Internet connection is needed for downloading required components.
1. Prerequisites
   - Ubuntu (64-bit 18.04) and macOS v11.5.2
   - Java (1.8)
2. Libraries 
   - Weka v3.8.4

## Running
1. Download the Weka v3.8.4 zip file from Installation step (2) above, and unzip it manually. Copy the weka.jar file from the unzipped folder into the lib folder within the COVID-19-Prediction folder.
2. To run COVID-19-Prediction, open a terminal window with superuser privilege and switch to the COVID-19-Prediction folder.
3. Make sure you are in the COVID-19-Prediction directory, and set permissions on the scripts provided by running:

       `chmod 0755 compile.sh`
  
       `chmod 0755 run.sh`
4. To compile the code, run:

       `./compile.sh`
5. To run all 8 classifiers, run:

       `./run.sh`

The code will print out the hyperparameter values and AUC scores for each classifier along with storing this output in an output log file, “Main_output_log.txt”

## Acknowledgement
The use of the UCSD Campus AWS cloud network was supported by Michael Hogarth, MD. The authors MML and T-TK were funded by the U.S. National Institutes of Health (NIH) (R00HG009680, R01HL136835, R01GM118609, R01HG011066, U24LM013755, and T15LM011271). The content is solely the responsibility of the author and does not necessarily represent the official views of the NIH. The funders had no role in study design, data collection and analysis, decision to publish, or preparation of the manuscript.

## Contact 
Thank you for using our software. If you have any questions or suggestions, please kindly contact Megan Li at meganmli18@gmail.com.

## DOI
