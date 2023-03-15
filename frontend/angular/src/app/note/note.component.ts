import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {

  @Output() dismiss = new EventEmitter();
  @Output() focusout = new EventEmitter();
  constructor() {}
  ngOnInit(): void {
    // throw new Error('Method not implemented.');
  }
  
  onDismiss(event: any){
    this.dismiss.emit(event);
  }
  
  onFocusOut(event: any){
    this.focusout.emit(event)
  }
}
