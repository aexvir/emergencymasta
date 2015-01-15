cd ..\data
@java -classpath ../lib/hsqldb.jar org.hsqldb.Server -database.0 file:%1 -dbname.0 %2 