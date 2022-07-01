import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Chatbot } from 'src/app/model/chatbot.model';
import { ChatbotService } from 'src/app/services/chatbot.service';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css']
})
export class ChatbotComponent implements OnInit {
  chat : Chatbot = new Chatbot();
  messageDate !: any;
  messages = Array();
  responses = Array();

  constructor(private chatbotService: ChatbotService, private router: Router) { }

  ngOnInit(): void {
    
  }
  
  save() {
    this.chatbotService.getResponse(this.chat.message).subscribe(
      data => {
        this.responses.push(data);
        console.log(this.responses);
      }, 
      err => console.log(err)
    );
  }

  onSubmit() {
    this.messageDate = new Date();
    this.messages.push(this.chat.message);
    console.log(this.messages);
    this.save();
    this.chat.message = '';
  }

}
