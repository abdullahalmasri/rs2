import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Note } from '../note';
import { NoteServices } from '../note.services';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {
  @Input() public note: Note ;
  @Output() passEntry: EventEmitter<any> = new EventEmitter();

  @Input() fromParent: any;

  constructor(
    public activeModal: NgbActiveModal,private noteService: NoteServices
  ) {
    this.note={title:"",content:"",labelEntity:[]}
   }

  ngOnInit() {
    // this.note=this.fromParent;
    console.log('how i am ? :',this.fromParent);
    /* Output:
     {prop1: "Some Data", prop2: "From Parent Component", content: "This Can be anything"}
    */
  }

  closeModal(sendData: any) {
    this.activeModal.close(sendData);
  }



  saveClick(){
    console.log('object ',this.note);
    this.noteService.addNote(this.note).subscribe(
      (savedNote)=>{
        this.closeModal(savedNote)
      }
    )
    // const modalRef = this.modalService.open(PageDataComponent);
    // console.log('the data is ',this.note);
  }

}
