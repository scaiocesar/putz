package com.dc3.aws.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.LogFactory;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

/**
 * Classe responsavel pela manipulação de arquivos no servico S3 (Simple Storage
 * Service) da Amazon Este serviço permite gravar e recuperar arquivos.
 * 
 * @author Caio
 * 
 */
public class AmazonFileHandler {

	private AmazonBucketName bucketName;
	private InputStream credentials;
	private PropertiesCredentials awsCredentials;

	/**
	 * Inicializa a classe com o buckt desejado
	 * 
	 * @param bucketName
	 */
	public AmazonFileHandler(AmazonBucketName bucketName) {
		this.bucketName = bucketName;
		credentials = this.getClass().getClassLoader().getResourceAsStream("amazon/AwsCredentials.properties");
	}

	/**
	 * Adiciona um arquivo ao S3
	 * 
	 * @param fileName
	 *            Nome do arquivo que sera gravado
	 * @param file
	 * @return Caso o arquivo seja gravado no S3 sera retornado <b>true</>
	 */
	public Boolean addFile(String fileName, File file) {
		LogFactory.getLog(this.getClass()).debug("Iniciando gravacao de arquivo no S3");
		try {
			// Recupera a chave de acesso ao S3 do propertie
			awsCredentials = new PropertiesCredentials(credentials);
			AmazonS3 s3 = new AmazonS3Client(awsCredentials);
			PutObjectResult result;
			result = s3.putObject(new PutObjectRequest(bucketName.toString(), fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
			LogFactory.getLog(this.getClass()).debug("Arquivo gravado com sucesso no S3");
			return true;
		} catch (IOException e) {
			LogFactory.getLog(this.getClass()).debug("Erro ao gravar arquivo no S3");
			LogFactory.getLog(this.getClass()).error(e);
		}
		return false;
	}
	
	/**
	 * Adiciona um arquivo ao S3
	 * 
	 * @param fileName
	 *            Nome do arquivo que sera gravado
	 * @param file
	 * @return Caso o arquivo seja gravado no S3 sera retornado <b>true</>
	 */
	public Boolean addFile(String fileName, InputStream is){
		Boolean ret;
		try {
			File f = saveInputStreamIntoDisk(is,fileName);
			ret = addFile(fileName, f);
			return ret;
		} catch (Exception e) {
			LogFactory.getLog(this.getClass()).error(e);
		}finally{
			try {
				deleteFileFromDisk(fileName);
			} catch (Exception e) {
				LogFactory.getLog(this.getClass()).error(e);
			}
		}
		return false;
	}

	/**
	 * Remove um arquivo do S3
	 * 
	 * @param fileName
	 *            Nome do arquivo a ser removido do S3
	 */

	public Boolean delFile(String fileName) {
		LogFactory.getLog(this.getClass()).debug("Iniciando remocao de arquivo no S3");
		try {
			// Recupera a chave de acesso ao S3 do propertie
			awsCredentials = new PropertiesCredentials(credentials);
			AmazonS3 s3 = new AmazonS3Client(awsCredentials);
			s3.deleteObject(bucketName.toString(), fileName);
			LogFactory.getLog(this.getClass()).debug("Arquivo removido com sucesso no S3");
			return true;
		} catch (IOException e) {
			LogFactory.getLog(this.getClass()).debug("Erro ao remover arquivo no S3");
			LogFactory.getLog(this.getClass()).error(e);
		}
		return false;
	}

	private File saveInputStreamIntoDisk(InputStream inputStream,  String fileName) throws Exception {

		LogFactory.getLog(this.getClass()).debug("Saving file into disk.");

		OutputStream outputStream = null;

		try {
			File f = new File("/tmp/" + fileName);
			outputStream = new FileOutputStream(f);

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

			LogFactory.getLog(this.getClass()).debug("File saved successfully");
			return f;

		} catch (IOException e) {
			LogFactory.getLog(this.getClass()).error("Error saving file.", e);
			throw e;
		} finally {

			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					LogFactory.getLog(this.getClass()).error("Error closing InputStream.", e);
					throw e;
				}
			}

			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					LogFactory.getLog(this.getClass()).error("Error closing OutputStream.", e);
					throw e;
				}

			}
		}
	}

	private void deleteFileFromDisk(String fileName) throws Exception {

		LogFactory.getLog(this.getClass()).debug("Deleting file from disk.");

		try {
			File file = new File("/tmp/" + fileName);

			if (file.delete()) {
				LogFactory.getLog(this.getClass()).debug("File " + "/tmp/" + fileName + " delete successfully");
			} else {
				LogFactory.getLog(this.getClass()).debug("File " + "/tmp/" + fileName + " delete failed ");
			}

		} catch (Exception e) {
			LogFactory.getLog(this.getClass()).error("Error deleting file: " + "/tmp/" + fileName, e);
			throw e;
		}
	}
	/*
	 * public String getFile(){
	 * 
	 * }
	 */

}
