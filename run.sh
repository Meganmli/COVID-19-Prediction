java -cp .:lib/weka.jar:bin  Main $1 2>&1 | tee main_output_log_$1.txt
