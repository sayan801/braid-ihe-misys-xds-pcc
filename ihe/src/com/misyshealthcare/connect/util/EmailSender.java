/* Copyright 2008 Misys PLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License. 
 */
package com.misyshealthcare.connect.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.smime.SMIMECapabilitiesAttribute;
import org.bouncycastle.asn1.smime.SMIMECapability;
import org.bouncycastle.asn1.smime.SMIMECapabilityVector;
import org.bouncycastle.asn1.smime.SMIMEEncryptionKeyPreferenceAttribute;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMEEnvelopedGenerator;
import org.bouncycastle.mail.smime.SMIMEException;
import org.bouncycastle.mail.smime.SMIMESignedGenerator;
import org.bouncycastle.util.Strings;

//import com.misyshealthcare.connect.ConnectConstants;
//import com.misyshealthcare.connect.config.ConfigManager;
import com.misyshealthcare.connect.net.MailConnection;



/**
 * Send emails
 * 
 * For s/mime, installation of "Java(TM) Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files" for
 * higher key encryption is likely needed.
 * 
 * @author Michael Traum
 * @version 2.0, 11/10/2006
 */
public class EmailSender {
	protected final static Logger log = Logger.getLogger(EmailSender.class);
	
	/** pkcs12 Keystore */
	private String senderKeystoreFile;
	private String senderKeystorePassword;
	private String senderKeyAlias;

	private String recipientCertFile;
	
	private String smtpServer;
	
	private boolean isSecure = false;
	
	private String fromAddress;
	private String toAddress;
	
	private String subject;
	
	private String bodyText;
	
	private Session session;
	
	private Message message = null;
	
/*	public EmailSender() {
		smtpServer = ConfigManager.getInstance().getStringProperty(ConnectConstants.sSmtpServer);
		fromAddress = ConfigManager.getInstance().getStringProperty(ConnectConstants.sFromAddress);
		senderKeystoreFile = ConfigManager.getInstance().getStringProperty(ConnectConstants.sSenderKeystoreFile);
		senderKeystorePassword = ConfigManager.getInstance().getStringProperty(ConnectConstants.sSenderKeystorePassword);
		senderKeyAlias = ConfigManager.getInstance().getStringProperty(ConnectConstants.sSenderKeyAlias);
		
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpServer);
        session = Session.getDefaultInstance(props, null);
	}*/
	
	public EmailSender(MailConnection mailConnection) {
		session = mailConnection.getSession();
		senderKeystoreFile = mailConnection.getSenderKeystoreFile();
		senderKeystorePassword = mailConnection.getSenderKeystorePassword();
		senderKeyAlias = mailConnection.getSenderKeyAlias();
	}
	
	public void send() throws EmailException {
		if (isSecure) {
			sendSecure();
		}
		else {
			throw new EmailException("Insecure emails are not currently supported");
		}
	}
	
	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public boolean isSecure() {
		return isSecure;
	}

	public void setSecure(boolean isSecure) {
		this.isSecure = isSecure;
	}

	public String getRecipientCertFile() {
		return recipientCertFile;
	}

	public void setRecipientCertFile(String recipientCertFile) {
		this.recipientCertFile = recipientCertFile;
	}
	
	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}

	private void sendSecure() throws EmailException {
		// TODO need a check to see that the proper variables are set
        try
        {
        	Message encryptedMessage = getEncryptedMessage();
            Transport.send(encryptedMessage);
        }
        catch (Exception e)
        {
        	log.error(e);
        	throw new EmailException(e);
        }
	}
	
	public Message getEncryptedMessage()  throws EmailException {
        try {
        	if (true) return encryptMessage(signMessage(message));
			MailcapCommandMap mailcap = (MailcapCommandMap)CommandMap
			.getDefaultCommandMap();

			mailcap
			        .addMailcap("application/pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_signature");
			mailcap
			        .addMailcap("application/pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_mime");
			mailcap
			        .addMailcap("application/x-pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_signature");
			mailcap
			        .addMailcap("application/x-pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_mime");
			mailcap
			        .addMailcap("multipart/signed;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.multipart_signed");
			
			CommandMap.setDefaultCommandMap(mailcap);
			
			/* Add BC */
			Security.addProvider(new BouncyCastleProvider());
			
			/* Open the keystores */
			KeyStore senderKeystore = KeyStore.getInstance("PKCS12", "BC");
			senderKeystore.load(new FileInputStream(senderKeystoreFile), senderKeystorePassword.toCharArray());
			
			/* Get the private key to sign the message with */
			PrivateKey privateKey = (PrivateKey)senderKeystore.getKey(senderKeyAlias,
					senderKeystorePassword.toCharArray());
			if (privateKey == null)
			{
			    throw new Exception("cannot find private key: "+senderKeyAlias);
			}
			Certificate[] senderChain = senderKeystore.getCertificateChain(senderKeyAlias);
			
			// loading certificate chain
			InputStream inStream  = new FileInputStream(recipientCertFile);
			CertificateFactory cf = CertificateFactory.getInstance("X.509","BC");
			X509Certificate recipientCert = (X509Certificate)cf.generateCertificate(inStream);
			inStream.close();
			
			Message body;
			/* Create the message to sign and encrypt */
			if (message == null) {
				body = buildMimeMessage(session);
			}
			else {
				body = message;
			}
			
			/* Create the SMIMESignedGenerator */
			SMIMECapabilityVector capabilities = new SMIMECapabilityVector();
			capabilities.addCapability(SMIMECapability.dES_EDE3_CBC);
			capabilities.addCapability(SMIMECapability.rC2_CBC, 128);
			capabilities.addCapability(SMIMECapability.dES_CBC);
//			capabilities.addCapability(SMIMECapability.aES128_CBC);
			
			ASN1EncodableVector attributes = new ASN1EncodableVector();
			attributes.add(new SMIMEEncryptionKeyPreferenceAttribute(
			        new IssuerAndSerialNumber(
			                new X509Name(((X509Certificate)senderChain[0])
			                        .getIssuerDN().getName()),
			                ((X509Certificate)senderChain[0]).getSerialNumber())));
			attributes.add(new SMIMECapabilitiesAttribute(capabilities));
			
			SMIMESignedGenerator signer = new SMIMESignedGenerator();
			signer
			        .addSigner(
			                privateKey,
			                (X509Certificate)senderChain[0],
			                "DSA".equals(privateKey.getAlgorithm()) ? SMIMESignedGenerator.DIGEST_SHA1
			                        : SMIMESignedGenerator.DIGEST_MD5,
			                new AttributeTable(attributes), null);
			
			/* Add the list of certs to the generator */
			List certList = new ArrayList();
			certList.add(senderChain[0]);
			CertStore certs = CertStore.getInstance("Collection",
			        new CollectionCertStoreParameters(certList), "BC");
			signer.addCertificatesAndCRLs(certs);
			
			/* Sign the message */
			MimeMultipart mm = signer.generate((MimeMessage)body, "BC");
			MimeMessage signedMessage = new MimeMessage(session);
			
			/* Set all original MIME headers in the signed message */
			Enumeration headers = ((MimeMessage)body).getAllHeaderLines();
			while (headers.hasMoreElements())
			{
			    signedMessage.addHeaderLine((String)headers.nextElement());
			}
			
			/* Set the content of the signed message */
			signedMessage.setContent(mm);
			signedMessage.saveChanges();
			
			/* Create the encrypter */
			SMIMEEnvelopedGenerator encrypter = new SMIMEEnvelopedGenerator();
			//encrypter.addKeyTransRecipient((X509Certificate)recipientChain[0]);
			encrypter.addKeyTransRecipient(recipientCert);
			
			/* Encrypt the message */
			MimeBodyPart encryptedPart = encrypter.generate(signedMessage,
			        SMIMEEnvelopedGenerator.RC2_CBC, "BC");
			
			/*
			 * Create a new MimeMessage that contains the encrypted and signed
			 * content
			 */
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			encryptedPart.writeTo(out);
			
			MimeMessage encryptedMessage = new MimeMessage(session,
			        new ByteArrayInputStream(out.toByteArray()));
			
			/* Set all original MIME headers in the encrypted message */
			headers = ((MimeMessage)body).getAllHeaderLines();
			while (headers.hasMoreElements())
			{
			    String headerLine = (String)headers.nextElement();
			    /*
			     * Make sure not to override any content-* headers from the
			     * original message
			     */
			    if (!Strings.toLowerCase(headerLine).startsWith("content-"))
			    {
			        encryptedMessage.addHeaderLine(headerLine);
			    }
			}
			
			return encryptedMessage;
        }
        catch (SMIMEException e)
        {
        	log.error(e);
        	throw new EmailException(e);
        }
        catch (Exception e)
        {
        	log.error(e);
        	throw new EmailException(e);
        }

        
	}
	
	private MimeMessage buildMimeMessage(Session session) throws EmailException {
        MimeMessage body = new MimeMessage(session);
        try {
			body.setFrom(new InternetAddress(fromAddress));
			body.setRecipient(Message.RecipientType.TO, new InternetAddress(
			        toAddress));
			body.setSubject(subject);
			// TODO need to deal with attachements and their mime types
			body.setContent(bodyText, "text/plain");
			body.saveChanges();
			return body;
		} catch (AddressException e) {
			throw new EmailException(e);
		} catch (MessagingException e) {
			throw new EmailException(e);
		}
	}

	public void setSenderKeyAlias(String senderKeyAlias) {
		this.senderKeyAlias = senderKeyAlias;
	}

	public void setSenderKeystoreFile(String senderKeystoreFile) {
		this.senderKeystoreFile = senderKeystoreFile;
	}

	public void setSenderKeystorePassword(String senderKeystorePassword) {
		this.senderKeystorePassword = senderKeystorePassword;
	}
	
	
	public Message encryptMessage(Message message) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		KeyStore    ks = KeyStore.getInstance("PKCS12", "BC");
		ks.load(new FileInputStream(senderKeystoreFile), senderKeystorePassword.toCharArray());

		Enumeration e = ks.aliases();
		String      keyAlias = null;

		while (e.hasMoreElements())
		{
			String  alias = (String)e.nextElement();

			if (ks.isKeyEntry(alias))
			{
				keyAlias = alias;
			}
		}

		if (keyAlias == null)
		{
			System.err.println("can't find a private key!");
			System.exit(0);
		}

		Certificate[]   chain = ks.getCertificateChain(keyAlias);

		//
		// create the generator for creating an smime/encrypted message
		//
		SMIMEEnvelopedGenerator  gen = new SMIMEEnvelopedGenerator();
	              
		gen.addKeyTransRecipient((X509Certificate)chain[0]);

		//
		// create a subject key id - this has to be done the same way as
		// it is done in the certificate associated with the private key
		// version 3 only.
		//
		/*
		 MessageDigest           dig = MessageDigest.getInstance("SHA1", "BC");
		 
		 dig.update(cert.getPublicKey().getEncoded());
	                  
	     gen.addKeyTransRecipient(cert.getPublicKey(), dig.digest());
	     */
	             
		//
		// create the base for our message
		//
		MimeBodyPart    msg = new MimeBodyPart();

		msg.setText("Hello world!");

		MimeBodyPart mp = gen.generate((MimeMessage) message, SMIMEEnvelopedGenerator.RC2_CBC, "BC");
		//
		// Get a Session object and create the mail message
		//
		Properties props = System.getProperties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage body = new MimeMessage(session);
		body.setFrom(message.getFrom()[0]);
		body.setRecipient(Message.RecipientType.TO, message.getAllRecipients()[0]);
		body.setSubject(message.getSubject());
		body.setContent(mp.getContent(), mp.getContentType());
		body.saveChanges();

		return body;
	}
	
	public Message signMessage(Message message) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		
		/* Open the keystores */
		KeyStore senderKeystore = KeyStore.getInstance("PKCS12", "BC");
		senderKeystore.load(new FileInputStream(senderKeystoreFile), senderKeystorePassword.toCharArray());
		
		/* Get the private key to sign the message with */
		PrivateKey privateKey = (PrivateKey)senderKeystore.getKey(senderKeyAlias,
				senderKeystorePassword.toCharArray());
		if (privateKey == null)
		{
		    throw new Exception("cannot find private key: "+senderKeyAlias);
		}
		Certificate[] senderChain = senderKeystore.getCertificateChain(senderKeyAlias);
		
		// loading certificate chain
		InputStream inStream  = new FileInputStream(recipientCertFile);
		CertificateFactory cf = CertificateFactory.getInstance("X.509","BC");
		X509Certificate recipientCert = (X509Certificate)cf.generateCertificate(inStream);
		inStream.close();
		
		/* Response vector */
		ASN1EncodableVector attributes = new ASN1EncodableVector();

		/* Add capabilities for encrypted response */
		SMIMECapabilityVector capabilities = new SMIMECapabilityVector();
		capabilities.addCapability(SMIMECapability.dES_EDE3_CBC);
		capabilities.addCapability(SMIMECapability.rC2_CBC, 128);
		capabilities.addCapability(SMIMECapability.dES_CBC);
//		capabilities.addCapability(SMIMECapability.aES128_CBC);
		attributes.add(new SMIMECapabilitiesAttribute(capabilities));
		
		/* Add key preference for encrypted response */
		X509Certificate senderCert = (X509Certificate) senderChain[0];
		X509Name issuer = new X509Name(senderCert.getIssuerDN().getName());
		IssuerAndSerialNumber isn = new IssuerAndSerialNumber(issuer, senderCert.getSerialNumber());
		attributes.add(new SMIMEEncryptionKeyPreferenceAttribute(isn));
		
		/* Get signing algorithm from encryption algorightm (set by key) */
		String hash = SMIMESignedGenerator.DIGEST_MD5;
		if (privateKey.getAlgorithm().equals("DSA")) hash = SMIMESignedGenerator.DIGEST_SHA1;
		/* Create signing generator */
		SMIMESignedGenerator signer = new SMIMESignedGenerator();
		signer.addSigner(privateKey, senderCert, hash, new AttributeTable(attributes), null);
		
		/* Add the list of certs to the generator */
		List certList = new ArrayList();
		certList.add(senderCert);
		CertStore certs = CertStore.getInstance("Collection",
		        new CollectionCertStoreParameters(certList), "BC");
		signer.addCertificatesAndCRLs(certs);
		
		/* Sign the message */
		MimeMultipart mm = signer.generate((MimeMessage) message, "BC");
		//
		// Get a Session object and create the mail message
		//
		Properties props = System.getProperties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage body = new MimeMessage(session);
		body.setFrom(message.getFrom()[0]);
		body.setRecipient(Message.RecipientType.TO, message.getAllRecipients()[0]);
		body.setSubject(message.getSubject());
		body.setContent(mm);
		body.saveChanges();

		return body;
	}
}
