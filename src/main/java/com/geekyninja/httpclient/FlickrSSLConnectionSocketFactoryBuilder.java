package com.geekyninja.httpclient;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;

public class FlickrSSLConnectionSocketFactoryBuilder {

    private static FlickrSSLConnectionSocketFactoryBuilder instance = new FlickrSSLConnectionSocketFactoryBuilder();

    public static FlickrSSLConnectionSocketFactoryBuilder getInstance() {
        return instance;
    }

    public SSLConnectionSocketFactory getSSLSocket() {
        SSLContext sslcontext = null;
        SSLConnectionSocketFactory sslsf = null;
        try {
            sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustStrategy() {

                        public boolean isTrusted(X509Certificate[] arg0,
                                String arg1) throws CertificateException {
                            return true;
                        }
                    }).build();

            sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] {
                    "TLSv1", "TLSv1.1", "TLSv1.2" }, null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        } catch (KeyStoreException e) {
        	e.printStackTrace();
        }

        return sslsf;
    }

}
