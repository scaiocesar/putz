package com.dc3.aws.utils;


/**
 * Armazena os Buckets da amazon
 * @author Caio
 *
 */
public enum AmazonBucketName {
    PUTZ("putz-nao-vou");

    private String value;

    AmazonBucketName(String str) {
        value = str;
    }

    public String toString() {
        return value;
    }
    
}
