README.txt - This file.
keystore.key - initial unsigned generated key, saved and never touched.  In case something goes FUBAR.
EHR_MISYS.key - copy of key, for uniform naming.  Used to generate csr.
EHR_MISYS.csr - signing request.  Sent to CA.
EHR_MISYS.cer - signed cert made by CA from .csr.  Recieved from CA.
EHR_MISYS_KEY.key - Keystore with root certs and signed master key.
Identrus_Test_Root.cer - Root cert.
Wells_CA.cer - Intermediate cert.
EHR_MISYS_TRUST.key - keystore for holding cert that signed all other certs.

EMR_MISYS_08_KEY.p12 - self-signed certificate keystore
EMR_MISYS_08_TRUST.jsk - truststore 

to generate a p12(pkcs12) key:
openssl pkcs12 -export -out EHR_ALLSCRIPTS_CONNECT_09_KEY.pkcs12 -in EHR_ALLSCRIPTS_CONNECT.cert.pem -inkey EHR_ALLSCRIPTS_CONNECT.key.pem

to convert p12 key to jks key (need to have a jetty installation):
java -cp c:\tools\jetty\jetty-6.1.14\lib\jetty-6.1.14.jar org.mortbay.jetty.security.PKCS12Import EHR_ALLSCRIPTS_CONNECT_09_KEY.pkcs12 EHR_ALLSCRIPTS_CONNECT_09_KEY.jks

to generate truststore:

keytool -import -alias mesa -file mesa.cert -keystore TrustStore


