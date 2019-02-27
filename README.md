# alexa-demo

follow the steps in odrder to make .jar file which you are ging to upload on aws lambda

1> download this project as zip file or clone it<br>
2> do import as maven in eclipse<br>
3> do whaterever changes you want to do in code<br>
4> goto maven >> update project<br>
5> now run as maven install<br>
6> now run as maven build using goal as "assembly:assembly -DdescriptorId=jar-with-dependencies package"<br>
7> see in target folder of your project there will a jar file which name contains "jar-with-dependencies"<br>
8> upload that jar file to lambda<br>
