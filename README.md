# diktats
Command line tool to convert Latvian Language Worldwide Test (raksti.org) data to owner requested format

There is a yearly open Latvian Language Test event, that allows participants to test their Latvian language knowledge.
The test results are stored in the project database, while this tool converts data from exported CSV format to the ZIP-file containing:
- MS Word files with test content, one file per participant;
- MS Excel index file holding participants data.

The input CSV file is like this:

"id";"message";"name";"surname";"email";"age";"language_id";"language_other";"type_id";"studylevel_id";"studynumber_id";"studyended_id";"country";"city";"in_time";"forced";"created_at";"updated_at";"interpunction_errors";"ortography_errors";"phone";"gender";"language";"level";"degree"
"2";"Rudens rīts aug silts un valgs. ";"name1-1";"name1-2";"email1";"23";"1";"";"0";"3";"1";"0";"Polija";"Warsaw";"1";"0";"2019-11-09 12:18:30";"2019-11-09 12:18:30";\t;\t;"phone_number";"m";"Latviešu";"Augstākā izglītība";"Bakalaurs"
"4";"Sēņu diktāts.Rudens rīts aust pirmais saules stars";"name2-1";"name2-2";"email2";"19";"1";"";"0";"2";"0";"0";"Latvija";"Rīga";"1";"0";"2019-11-09 12:18:51";"2019-11-09 12:18:51";\t;\t;"phone_number";"f";"Latviešu";"Vidējā izglītība";\t

Usage:

Option A. Start a program without parameters: java -jar ekzamens-1.0-SNAPSHOT-all.jar
I opens a file-open dialog, select input CSV file, the result file will be created in the same directory;

Option B. Start a program with an input file as a command line parameter: java -jar ekzamens-1.0-SNAPSHOT-all.jar input_file.csv
