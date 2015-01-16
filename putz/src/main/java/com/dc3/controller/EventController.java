package com.dc3.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dc3.aws.utils.AmazonBucketName;
import com.dc3.aws.utils.AmazonFileHandler;
import com.dc3.model.Event;
import com.dc3.service.EventService;
import com.dc3.service.exception.ServiceException;
import com.dc3.vo.EventListVO;

/**
 * Classe responsável por gerenciar as açoes de Evento
 * Exemplo JSON event
		 {
		"user":{
		"idUser": 1,
		"idState": null,
		"phone": null,
		"oauthId": null
		},
		"idEvent": null,
		"pathImage": null,
		"eventPlace": "Pedreira",
		"eventGenre":{
		"idGenre": 1,
		"genreName": null
		},
		"artistName": "David Guetta",
		"eventCity":{
		"state": null,
		"idCity": 1,
		"cityName": null
		},
		"eventName": "Show do David",
		"eventDate": 1421085803624
		}
 * @author Caio
 *
 */
@Controller
@RequestMapping(value = "/eventController")
public class EventController extends GenericController {

	@Autowired
	private EventService eventService;
	@Value("5")
    private int maxResults;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=UTF-8", value = "/create")
	public ResponseEntity<?> create(@RequestBody Event event, HttpServletRequest request) {
		try {
			eventService.save(event);
			return new ResponseEntity<Event>(event,HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity(getMessageSource().getMessage(e.getMsgResource(), null, null, request.getLocale()), HttpStatus.BAD_REQUEST);
		}

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

	@RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8", value = "/listAllEvents")
	public ResponseEntity<?> listAllEvents(@RequestParam int page) {
			 EventListVO vo = eventService.findAll(page, maxResults);
			 return new ResponseEntity<EventListVO>(vo, HttpStatus.OK);
	}

}
