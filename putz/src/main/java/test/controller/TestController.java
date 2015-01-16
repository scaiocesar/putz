package test.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import test.vo.SampleVO;

import com.dc3.aws.utils.AmazonBucketName;
import com.dc3.aws.utils.AmazonFileHandler;
import com.dc3.controller.GenericController;
import com.dc3.model.City;
import com.dc3.model.Event;
import com.dc3.model.Genre;
import com.dc3.model.User;

@Controller
@RequestMapping(value = "/testController")
public class TestController extends GenericController {

	@Autowired
	private MessageSource messageSource;

	@Value("5")
	private int maxResults;

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", value = "/welcome")
	public ResponseEntity<Event> welcome(Event event) {
		Event evt = new Event();
		evt.setArtistName("David Guetta");
		City city = new City();
		city.setIdCity(1);
		evt.setEventCity(city);
		evt.setEventDate(new Date());
		Genre g = new Genre();
		g.setIdGenre(1);
		evt.setEventGenre(g);
		evt.setEventName("Show do David");
		evt.setEventPlace("Pedreira");
		User u = new User();
		u.setIdUser(1);
		evt.setUser(u);
		
		return new ResponseEntity<Event>(evt, HttpStatus.OK);

	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
	public @ResponseBody
	String handleFileUpload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				/*
				 * byte[] bytes = file.getBytes(); BufferedOutputStream stream =
				 * new BufferedOutputStream(new FileOutputStream(new
				 * File("c:\\tmp\\"+file.getName()))); stream.write(bytes);
				 * stream.close();
				 */
				AmazonFileHandler afh = new AmazonFileHandler(AmazonBucketName.PUTZ);
				afh.addFile(file.getName() + ".jpg", file.getInputStream());

				return "You successfully uploaded " + file.getName() + ".jpg" + "!";
			} catch (Exception e) {
				return "You failed to upload " + file.getName() + ".jpg" + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + file.getName() + ".jpg" + " because the file was empty.";
		}
	}
	
	@RequestMapping(value = "/upload2", method = RequestMethod.POST)
	public @ResponseBody
	String handleFileUpload2(@RequestBody SampleVO file) {
			try {
				byte[]   bytesEncoded = Base64.encodeBase64(file.getNome().getBytes());
				InputStream myInputStream = new ByteArrayInputStream(bytesEncoded); 
				
				AmazonFileHandler afh = new AmazonFileHandler(AmazonBucketName.PUTZ);
				afh.addFile("xuxu" + ".jpg", myInputStream);
				return "OK";
			} catch (Exception e) {
				return "NOK";
			}
	}


}