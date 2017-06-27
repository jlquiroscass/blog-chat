package jlquiros.blog.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jlquiros.blog.config.CustomUserDetails;
import jlquiros.blog.model.Message;
import jlquiros.blog.model.OutputMessage;
import jlquiros.blog.model.Post;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ChatController {

	@MessageMapping("/chat/{topic}")
	@SendTo("/topic/messages")
	public OutputMessage send(
	       Message message)
	        throws Exception {
	    return new OutputMessage(message.getFrom(), message.getText());
	}
    
    @RequestMapping(value = "/live", method = RequestMethod.GET)
    public String chat(Model model,Authentication authentication) {
    	if ( authentication == null )
    		model.addAttribute("author","Anonymous");
    	else
    		model.addAttribute("author",((CustomUserDetails) authentication.getPrincipal()).getUsername());
        return "live";
    }

}