#Windows
#1、生成证书
keytool -genkey -alias tomcat -keypass changeit -keyalg RSA -validity 3650 -keystore tomcat.keystore
#2、导出数字证书
keytool -export -alias tomcat -file tomcat.crt -keystore tomcat.keystore -validity 3650
#3、将数字证书导入jdk下的jre里
keytool -import -keystore "C:\Program Files\Java\jdk1.8.0_191\jre\lib\security\cacerts" -file tomcat.crt -alias tomcat -storepass changeit

#配置Tomcat server.xml
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
       maxThreads="200" SSLEnabled="true" scheme="https"
       secure="true" clientAuth="false" sslProtocol="TLS"
       keystoreFile="F:\etc\cert\tomcat.keystore"
       keystorePass="changeit"/>