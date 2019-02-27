# alexa-demo

follow the steps in odrder to make .jar file which you are ging to upload on aws lambda

1> download this project as zip file or clone it
2> do import as maven in eclipse
3> do whaterever changes you want to do in code
4> goto maven >> update project
5> now run as maven install
6> now run as maven build using goal as "assembly:assembly -DdescriptorId=jar-with-dependencies package"
7> see in target folder of your project there will a jar file which name contains "jar-with-dependencies"
8> upload that jar file to lambda
