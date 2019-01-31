import { Injectable } from '@angular/core';

enum MessageType {
  success = "alert alert-success",
  danger = "alert alert-danger",
  warning = "alert alert-warning",
  info = "alert alert-info"
}

@Injectable({
  providedIn: 'root'
})

export class MessageService {
  public message: string;
  public messageType : string;

  constructor() { }

  set(message: string, messageType = "info"){
    this.message = message;
    this.messageType = MessageType[messageType];
  }
  
  clear(){
    this.message = "";
  }
}
